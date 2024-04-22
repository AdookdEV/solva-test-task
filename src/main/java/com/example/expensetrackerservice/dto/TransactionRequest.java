package com.example.expensetrackerservice.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.example.expensetrackerservice.enums.ExpenseCategory;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionRequest {
    @Schema(description = "Счет отправителя")
    private String accountFrom;
    @Schema(description = "Счет получателя")
    private String accountTo;
    @Schema(description = "Валюта")
    private String currencyShortname;
    @Schema(description = "Сумма транзакции")
    private BigDecimal sum;
    @Schema(description = "Дата транзакции в формате yyyy-MM-dd HH:mm:ssX")
    private ZonedDateTime datetime;
    @Schema(description = "Категория рассхода")
    private ExpenseCategory expenseCategory;
}
