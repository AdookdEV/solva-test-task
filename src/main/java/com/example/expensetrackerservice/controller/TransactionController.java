package com.example.expensetrackerservice.controller;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensetrackerservice.dto.TransactionRequest;
import com.example.expensetrackerservice.dto.TransactionResponse;
import com.example.expensetrackerservice.service.CurrencyExchangeService;
import com.example.expensetrackerservice.service.TransactionService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final CurrencyExchangeService currencyExchangeService;
    private final TransactionService transactionService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponse receiveTransaction(@RequestBody TransactionRequest transactionRequest) {
        return transactionService.saveTransaction(transactionRequest);
    }

    @GetMapping("/convert")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal receiveTransaction(
            @RequestParam("value") String value,
            @RequestParam("from") String from,
            @RequestParam("to") String to) {
        var res = currencyExchangeService.exchange(BigDecimal.valueOf(Double.parseDouble(value)), from, to);
        return res;
    }
}
