package com.example.expensetrackerservice.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.example.expensetrackerservice.enums.ExpenseCategory;
import com.example.expensetrackerservice.utils.CustomJsonDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TransactionRequest {
    private String accountFrom;
    private String accountTo;
    private String currencyShortname;
    private BigDecimal sum;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ssX")
    @JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private ZonedDateTime datetime;
    private ExpenseCategory expenseCategory;
}
