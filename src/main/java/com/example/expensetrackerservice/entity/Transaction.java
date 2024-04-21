package com.example.expensetrackerservice.entity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import com.example.expensetrackerservice.enums.ExpenseCategory;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "t_transactions")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "t_transactions_id_seq")
    @Column(name = "id")
    private Long id;
    private String accountFrom;
    private String accountTo;
    private BigDecimal sum;
    private String currencyShortname;
    private ZonedDateTime datetime;
    @Enumerated(EnumType.STRING)
    private ExpenseCategory expenseCategory;
    private Boolean limitExceeded;
    @ManyToOne
    @JoinColumn(name="limit_id", nullable = false)
    private ExpenseLimit limit;
}
