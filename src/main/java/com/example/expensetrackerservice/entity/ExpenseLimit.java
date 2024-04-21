package com.example.expensetrackerservice.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.example.expensetrackerservice.enums.ExpenseCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "t_expense_limits")
public class ExpenseLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_expense_limits_id_seq")
    private Long id;
    private BigDecimal sum;
    private ZonedDateTime date;
    private String account;
    private String currency;
    private BigDecimal remainingSum;
    @Enumerated(EnumType.STRING)
    private ExpenseCategory expenseCategory;
}
