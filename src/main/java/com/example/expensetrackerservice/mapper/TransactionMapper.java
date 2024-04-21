package com.example.expensetrackerservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.example.expensetrackerservice.dto.TransactionRequest;
import com.example.expensetrackerservice.dto.TransactionResponse;
import com.example.expensetrackerservice.entity.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "limitExceeded", ignore = true)
    @Mapping(target = "limit", ignore = true)
    Transaction mapToTransaction(TransactionRequest dto);

    @Mapping(target = "limitSum", source="entity.limit.sum")
    @Mapping(target = "limitDatetime", source="entity.limit.date")
    @Mapping(target = "limitCurrencyShortname", source="entity.limit.currency")
    TransactionResponse mapToTransactionResponse(Transaction entity);
}
