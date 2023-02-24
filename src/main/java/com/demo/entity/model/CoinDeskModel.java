package com.demo.entity.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
public class CoinDeskModel implements Serializable {
    private Instant time;
    private BpiModel bpi;
    private String chartName;
    private String disclaimer;
}
