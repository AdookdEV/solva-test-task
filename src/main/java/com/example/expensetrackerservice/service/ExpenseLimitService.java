package com.example.expensetrackerservice.service;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.expensetrackerservice.dto.CreateLimitDTO;
import com.example.expensetrackerservice.entity.ExpenseLimit;
import com.example.expensetrackerservice.enums.ExpenseCategory;
import com.example.expensetrackerservice.mapper.ExpenseLimitMapper;
import com.example.expensetrackerservice.repository.ExpenseLimitRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenseLimitService {
    private final ExpenseLimitRepository expenseLimitRepository;
    private final ExpenseLimitMapper expenseLimitMapper;
    
    @Transactional
    public ExpenseLimit createLimit(CreateLimitDTO createLimitDTO) {
        log.debug("Create limit: {}", createLimitDTO);
        ExpenseLimit expenseLimit = expenseLimitMapper.mapToExpenseLimit(createLimitDTO);
        expenseLimit.setDate(ZonedDateTime.now(ZoneId.of("Asia/Almaty")));
        var lastLimit = getLastLimit(
                expenseLimit.getAccount(),
                expenseLimit.getExpenseCategory(),
                expenseLimit.getDate());

        var limitRemainingSum = expenseLimit.getSum();
        if (lastLimit.isPresent()) {
            var newLimitSum = expenseLimit.getSum();
            var lastLimitSum = lastLimit.get().getSum();
            limitRemainingSum = newLimitSum.subtract(lastLimitSum).add(lastLimit.get().getRemainingSum());
            log.debug("Last limit: {}", lastLimit);
        }
        expenseLimit.setRemainingSum(limitRemainingSum);

        log.debug("Save new limit: {}", expenseLimit);
        return expenseLimitRepository.save(expenseLimit);
    }

    public ExpenseLimit createDefaultLimit(String account, ExpenseCategory expenseCategory) {
        return createLimit(CreateLimitDTO
                .builder()
                .account(account)
                .sum(BigDecimal.valueOf(1000))
                .accountCurrency("USD")
                .expenseCategory(expenseCategory)
                .build());
    }

    public List<ExpenseLimit> getAllLimitsByAccount(String account) {
        return expenseLimitRepository.findByAccount(account);
    }

    public Optional<ExpenseLimit> getLastLimit(String account, ExpenseCategory category, ZonedDateTime date) {
        return expenseLimitRepository
                .findByAccountAndCategory(account, category)
                .stream()
                .filter(l -> l.getDate().getMonthValue() == date.getMonthValue()
                        && l.getDate().getYear() == date.getYear())
                .findFirst();
    }

    @Transactional
    public void updateRemainingSum(Long id, BigDecimal value) {
        var limit = expenseLimitRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No limit with id " + id));
        limit.setRemainingSum(value);
    }
}
