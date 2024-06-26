package com.example.expensetrackerservice.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetrackerservice.dto.CreateLimitDTO;
import com.example.expensetrackerservice.dto.TransactionResponse;
import com.example.expensetrackerservice.entity.ExpenseLimit;
import com.example.expensetrackerservice.repository.ExpenseLimitRepository;
import com.example.expensetrackerservice.service.ExpenseLimitService;
import com.example.expensetrackerservice.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "LimitController", description = "Котроллер для работы с лимитами. Позволяет получать транзакции, превысившие лимит, создавать и получать лимиты счета")
@RestController
@RequestMapping("/api/limit")
@RequiredArgsConstructor
@Slf4j
public class LimitController {
    private final ExpenseLimitService expenseLimitService;
    private final TransactionService transactionService;
    private final ExpenseLimitRepository expenseLimitRepository;

    @Operation(summary = "Создание лимита", description = "Создает отдельный лимит для счета и категории расхода.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createLimit(@RequestBody CreateLimitDTO createLimitRequest) {
        log.debug("Received a create limit request {}", createLimitRequest);
        expenseLimitService.createLimit(createLimitRequest);
    }

    @Operation(summary = "Возвращает все лимиты счета клиента")
    @GetMapping("/all/{account}")
    @ResponseStatus(HttpStatus.OK)
    public List<ExpenseLimit> getAllLimits(
            @PathVariable("account") @Parameter(description = "Счет отправителя") String account) {
        log.debug("Request to get all limits for account {}", account);
        var res = expenseLimitRepository.findByAccount(account);
        return res;
    }

    @Operation(summary = "Возвращает транзакции, превысившие месячный лимит")
    @GetMapping("/transactions/{account}")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionResponse> getLimitExceededTransactions(
            @PathVariable("account") @Parameter(description = "Счет отправителя") String account,
            @RequestParam(name = "page-number", defaultValue = "0", required = false) String pageNumber,
            @RequestParam(name = "page-size", defaultValue = "10", required = false) String pageSize) {
        log.debug("Request to get transactions with exceeded limit for account {}", account);
        return transactionService
                .getTransactionsWithExceededLimit(account, Integer.parseInt(pageNumber), Integer.parseInt(pageSize));
    }
}