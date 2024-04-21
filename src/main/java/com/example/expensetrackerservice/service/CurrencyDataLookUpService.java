package com.example.expensetrackerservice.service;

import java.util.List;

public interface CurrencyDataLookUpService {
    void loadDataFor(List<String> symbols);
}
