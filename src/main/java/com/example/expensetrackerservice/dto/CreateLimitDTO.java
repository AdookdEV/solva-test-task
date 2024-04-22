package com.example.expensetrackerservice.dto;

import java.math.BigDecimal;
import com.example.expensetrackerservice.enums.ExpenseCategory;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Сумма лимита")
    private BigDecimal sum;
    @Schema(description = "Счет для которого нужно создать лимит")
    private String account;
    @Schema(description = "Валюта счета")
    private String accountCurrency;
    @Schema(description = "Категория расхода на который действует лимит")
    private ExpenseCategory expenseCategory;
}
