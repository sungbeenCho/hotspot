package com.example.hotspot.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "open_close_stats")
@Getter
@Setter
@NoArgsConstructor
public class OpenCloseStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String areaId;
    private String areaName;

    private String categoryCode;
    private String categoryName;

    private int openCount;
    private int closeCount;
    private double openRate;
    private double closeRate;
    private int totalStores;
    private int similarCategoryStores;
    private int franchiseStores;
}
