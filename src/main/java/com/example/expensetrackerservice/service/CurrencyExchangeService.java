package com.example.expensetrackerservice.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.example.expensetrackerservice.entity.ExchangeRate;
import com.example.expensetrackerservice.repository.ExchangeRateRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CurrencyExchangeService {
    private final ExchangeRateRepository exchangeRateRepository;
    private final CurrencyDataLookUpService dataLookUpService;

    public BigDecimal exchange(BigDecimal money, String from, String to) {
        if (from.equals(to)) return money;
        Pageable pageRequest = PageRequest.of(0, 5, Sort.by("date"));
        List<String> symbols = List.of(from + "/" + to, to + "/" + from);
        List<ExchangeRate> exchangeRates = exchangeRateRepository
                .findBySymbolIn(symbols, pageRequest);
        Optional<ExchangeRate> lastClosedExchangeRate = exchangeRates.stream().findFirst();
        if (!lastClosedExchangeRate.isPresent()) {
            throw new RuntimeException("No exchange rate for " + from + "/" + to + " currency pair");
        }

        BigDecimal rateValue = lastClosedExchangeRate.get().getOnCloseValue();
        if (lastClosedExchangeRate.get().getSymbol().startsWith(to)) {
            return money.divide(rateValue, 2, RoundingMode.HALF_UP);
        }
        return rateValue.multiply(money);
    }

    @Async
    @Scheduled(cron = "${currency-data-retriever.cron-interval}")
    public void retrieveCurrencyData() {
        log.info("Trying to look up currency exchange rate data");
        dataLookUpService.loadDataFor(List.of("USD/KZT", "USD/RUB"));
    }
}
