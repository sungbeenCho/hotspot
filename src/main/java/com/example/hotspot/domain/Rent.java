package com.example.hotspot.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rents")
@Getter
@Setter
@NoArgsConstructor
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String areaId;
    private String areaName;
    private String quarter; // 예: 20221

    private long rent; // 단위: 원
}
