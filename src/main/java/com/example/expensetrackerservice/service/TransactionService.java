package com.example.expensetrackerservice.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.expensetrackerservice.dto.TransactionRequest;
import com.example.expensetrackerservice.dto.TransactionResponse;
import com.example.expensetrackerservice.entity.ExpenseLimit;
import com.example.expensetrackerservice.entity.Transaction;
import com.example.expensetrackerservice.mapper.TransactionMapper;
import com.example.expensetrackerservice.repository.TransactionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    private final ExpenseLimitService expenseLimitService;
    private final CurrencyExchangeService currencyExchangeService;

    @Transactional
    public TransactionResponse saveTransaction(TransactionRequest transactionRequest) {
        log.debug("Save transaction: {}", transactionRequest);
        var transaction = transactionMapper.mapToTransaction(transactionRequest);

        var activeLimit = getActiveLimit(transaction);
        log.debug("Active limit: {}", activeLimit);
        transaction.setLimitExceeded(false);
        transaction.setLimit(activeLimit);
        BigDecimal tranSum = currencyExchangeService.exchange(transaction.getSum(),
                transaction.getCurrencyShortname(), "USD");
        log.debug("Exchanged transaction sum from {} {} to {} USD", transaction.getSum(),
                transaction.getCurrencyShortname(), tranSum);
        var difference = activeLimit.getRemainingSum().subtract(tranSum);
        if (difference.compareTo(BigDecimal.ZERO) < 0) {
            transaction.setLimitExceeded(true);
        }
        transactionRepository.save(transaction);
        log.debug("Saved transaction: {}", transaction);
        expenseLimitService.updateRemainingSum(activeLimit.getId(), difference);
        var transactionResponse = transactionMapper.mapToTransactionResponse(transaction);
        return transactionResponse;

    }

    private ExpenseLimit getActiveLimit(Transaction transaction) {
        var lastLimit = expenseLimitService.getLastLimit(
                transaction.getAccountFrom(),
                transaction.getExpenseCategory(),
                transaction.getDatetime());
        if (!lastLimit.isPresent()) {
            log.debug("Created new limit");
            return expenseLimitService
                    .createDefaultLimit(
                            transaction.getAccountFrom(),
                            transaction.getExpenseCategory());
        }
        return lastLimit.get();
    }

    public List<TransactionResponse> getTransactionsWithExceededLimit(String account, Integer pageNumber,
            Integer pageSize) {
        return transactionRepository
                .findByAccountWithExceededLimit(account, PageRequest.of(pageNumber, pageSize))
                .stream()
                .map(transactionMapper::mapToTransactionResponse)
                .toList();
    }
}
