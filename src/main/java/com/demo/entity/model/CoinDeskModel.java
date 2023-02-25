package com.demo.entity.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@ToString
public class CoinDeskModel implements Serializable {
    private Time time;
    private BpiModel bpi;
    private String chartName;
    private String disclaimer;
}
