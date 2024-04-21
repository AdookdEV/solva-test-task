package com.example.expensetrackerservice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.expensetrackerservice.dto.TwelvedataCurrencyDTO;
import com.example.expensetrackerservice.mapper.CurrencyDataMapper;
import com.example.expensetrackerservice.repository.ExchangeRateRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class TwelvedataLookUpService implements CurrencyDataLookUpService {
    @Value("${app.twelvedata-api-url}")
    private String apiUrl;
    @Value("${app.twelvedata-api-key}")
    private String apiKey;
    private final ExchangeRateRepository exchangeRateRepository;
    private final CurrencyDataMapper mapper;

    @Override
    @Transactional
    public void loadDataFor(List<String> symbols) {
        WebClient client = WebClient.create(apiUrl);
        Map<String, TwelvedataCurrencyDTO> result = client.get()
                .uri("/time_series", uriBuilder -> uriBuilder
                        .queryParam("apikey", apiKey)
                        .queryParam("interval", "1day")
                        .queryParam("symbol", String.join(",", symbols))
                        .queryParam("outputsize", "5")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, TwelvedataCurrencyDTO>>() {
                }).block();
        var res = result.values()
                .stream()
                .map(mapper::mapFrom).toList();
        exchangeRateRepository.saveAll(res);
        log.info("Saved currency data: {}", res);
    }
}
