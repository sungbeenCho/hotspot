package com.example.hotspot.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "populations")
@Getter
@Setter
@NoArgsConstructor
public class Population {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String areaId;
    private String areaName;
    private int year;
    private int month;
    private boolean isWeekend;
    private boolean isForecast;

    // 시간대별
    private double hour00;
    private double hour01;
    private double hour02;
    private double hour03;
    private double hour04;
    private double hour05;
    private double hour06;
    private double hour07;
    private double hour08;
    private double hour09;
    private double hour10;
    private double hour11;
    private double hour12;
    private double hour13;
    private double hour14;
    private double hour15;
    private double hour16;
    private double hour17;
    private double hour18;
    private double hour19;
    private double hour20;
    private double hour21;
    private double hour22;
    private double hour23;

    // 남성 연령대
    private double male10;
    private double male20;
    private double male30;
    private double male40;
    private double male50;
    private double male60;

    // 여성 연령대
    private double female10;
    private double female20;
    private double female30;
    private double female40;
    private double female50;
    private double female60;
}
