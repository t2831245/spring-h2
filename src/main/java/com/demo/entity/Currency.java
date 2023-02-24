package com.demo.entity;

import com.demo.entity.model.CurrencyNameModel;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "currency")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String rate;
    private String symbol;
    private String description;
    @Column(name = "rate_float")
    private float rateFloat;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "code", referencedColumnName = "ename")
    private CurrencyName currencyName;

}
