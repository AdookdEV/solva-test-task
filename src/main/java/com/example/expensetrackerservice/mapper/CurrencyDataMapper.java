package com.example.expensetrackerservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.expensetrackerservice.dto.TwelvedataCurrencyDTO;
import com.example.expensetrackerservice.entity.ExchangeRate;

@Mapper(componentModel = "spring")
public interface CurrencyDataMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "symbol", source="dto.meta.symbol")
    @Mapping(target = "onCloseValue", source="dto.lastValue.close")
    @Mapping(target = "date", source="dto.lastValue.datetime")
    ExchangeRate mapFrom(TwelvedataCurrencyDTO dto);
}
