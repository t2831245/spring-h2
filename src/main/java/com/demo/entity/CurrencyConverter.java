package com.demo.entity;

import com.demo.entity.model.CurrencyModel;

public class CurrencyConverter {

    public static Currency modelToEntity(CurrencyModel m, Currency e){
        return Currency
                .builder()
                .id(e.getId())
                .currencyName(e.getCurrencyName())
                .rateFloat(m.getRate_float())
                .symbol(m.getSymbol())
                .rate(m.getRate())
                .description(m.getDescription())
                .build();
    }
}
