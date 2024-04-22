package com.example.expensetrackerservice.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.example.expensetrackerservice.enums.ExpenseCategory;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionRequest {
    private String accountFrom;
    private String accountTo;
    private String currencyShortname;
    private BigDecimal sum;
    // @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private ZonedDateTime datetime;
    private ExpenseCategory expenseCategory;
}
