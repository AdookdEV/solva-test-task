package com.example.expensetrackerservice.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.expensetrackerservice.entity.ExchangeRate;

import java.util.Collection;
import java.util.List;


public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {
    List<ExchangeRate> findBySymbolIn(Collection<String> symbols, Pageable pageRequest);
}
