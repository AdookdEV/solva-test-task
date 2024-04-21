package com.example.expensetrackerservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.example.expensetrackerservice.dto.CreateLimitDTO;
import com.example.expensetrackerservice.entity.ExpenseLimit;

@Mapper(componentModel = "spring")
public interface ExpenseLimitMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "date", ignore = true)
    @Mapping(target = "remainingSum", ignore = true)
    @Mapping(target = "currency", source = "dto.accountCurrency")
    ExpenseLimit mapToExpenseLimit(CreateLimitDTO dto);
}
