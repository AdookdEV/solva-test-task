package com.example.expensetrackerservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.expensetrackerservice.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @Query("select t from Transaction t where t.limitExceeded = true and t.accountFrom = :account")
    Page<Transaction> findByAccountWithExceededLimit(@Param("account") String account, Pageable pageable);
}
