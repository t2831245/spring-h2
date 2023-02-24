package com.demo.entity.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyModel {

    private String code;
    private String symbol;
    private String rate;
    private String description;
    private float rate_float;
}
