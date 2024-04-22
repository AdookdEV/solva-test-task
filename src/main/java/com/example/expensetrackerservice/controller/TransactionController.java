package com.example.expensetrackerservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetrackerservice.dto.TransactionRequest;
import com.example.expensetrackerservice.dto.TransactionResponse;
import com.example.expensetrackerservice.service.TransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;


@Tag(name="TransactionController", description="Контроллер выставляет флаг лимита и так же возвращает его")
@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @Operation(summary = "Сохранение транзакции в базе", description = "Проверяет каждую транзакцию на превышение лимита расхода. Возвращает транзакцию с лимитом.")
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponse receiveTransaction(@RequestBody TransactionRequest transactionRequest) {
        return transactionService.saveTransaction(transactionRequest);
    }
}
