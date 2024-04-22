package com.example.expensetrackerservice.dto;

import java.math.BigDecimal;
import com.example.expensetrackerservice.enums.ExpenseCategory;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class CreateLimitDTO {
    private BigDecimal sum;
    private String account;
    private String accountCurrency;
    private ExpenseCategory expenseCategory;
}
