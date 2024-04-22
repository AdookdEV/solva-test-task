package com.example.expensetrackerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.expensetrackerservice.entity.ExpenseLimit;
import com.example.expensetrackerservice.enums.ExpenseCategory;

import java.util.List;

public interface ExpenseLimitRepository extends JpaRepository<ExpenseLimit, Long> {

    List<ExpenseLimit> findByAccount(@Param("account") String account);

    @Query("select l from ExpenseLimit l where l.account = :account and l.expenseCategory = :category order by l.date desc")
    List<ExpenseLimit> findByAccountAndCategory(
            @Param("account") String account,
            @Param("category") ExpenseCategory category);

    @Query("select l from ExpenseLimit l where MONTH(l.date) = :month and YEAR(l.date) = :year order by l.date desc")
    List<ExpenseLimit> findByMonthAndYearSorted(@Param("month") int month, @Param("year") int year);
}