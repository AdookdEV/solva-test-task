package com.example.expensetrackerservice.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.example.expensetrackerservice.enums.ExpenseCategory;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionResponse {
    private String accountFrom;
    private String accountTo;
    private String currencyShortname;
    private BigDecimal sum;
    private ZonedDateTime datetime;
    private ExpenseCategory expenseCategory;
    private BigDecimal limitSum;
    private ZonedDateTime limitDatetime;
    private String limitCurrencyShortname;
    private Boolean limitExceeded;
}
