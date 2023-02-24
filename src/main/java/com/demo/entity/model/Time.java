package com.demo.entity.model;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
public class Time {

    private Instant updated;
    private Instant updatedISO;
    private Instant updateduk;
}
