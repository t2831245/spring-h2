package com.demo.entity;

import com.demo.entity.model.CurrencyNameModel;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "currency_name")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyName implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name="ename")
    private CurrencyNameModel ename;
    @Column(name="cname")
    private String cname;
}
