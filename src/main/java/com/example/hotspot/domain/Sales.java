package com.example.hotspot.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "sales", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"areaId", "categoryCode", "quarter"})
})
@Getter
@Setter
@NoArgsConstructor
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String areaId;
    private String areaName;

    private String categoryCode;
    private String categoryName;

    private String quarter;
    private Long totalSales;

    private Long maleSales;
    private Long femaleSales;

    private Long age10Sales;
    private Long age20Sales;
    private Long age30Sales;
    private Long age40Sales;
    private Long age50Sales;
    private Long age60Sales;

    private Long monSales;
    private Long tueSales;
    private Long wedSales;
    private Long thuSales;
    private Long friSales;
    private Long satSales;
    private Long sunSales;

    private Long time00_06Sales;
    private Long time06_11Sales;
    private Long time11_14Sales;
    private Long time14_17Sales;
    private Long time17_21Sales;
    private Long time21_24Sales;
}
