package com.example.expensetrackerservice.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.expensetrackerservice.mapper.CurrencyDataMapper;
import com.example.expensetrackerservice.repository.ExchangeRateRepository;
import com.example.expensetrackerservice.service.CurrencyDataLookUpService;
import com.example.expensetrackerservice.service.TwelvedataLookUpService;

@Configuration
@EnableScheduling
@EnableAsync
@ConditionalOnProperty(name = "currency-data-retriever.enable", matchIfMissing = true)
public class SchedulerConfig {
    @Bean
    @Primary
    public CurrencyDataLookUpService twelveDataLookUpService(ExchangeRateRepository exchangeRateRepository,
            CurrencyDataMapper mapper) {
        return new TwelvedataLookUpService(exchangeRateRepository, mapper);
    }
}
