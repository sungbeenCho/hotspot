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

    private String quarter; // 기준_년분기_코드
    private String categoryCode; // 서비스_업종_코드
    private String categoryName; // 서비스_업종_코드_명

    private int totalStores; // 점포_수
    private int similarCategoryStores; // 유사_업종_점포_수
    private double openRate; // 개업_율
    private int openCount; // 개업_점포_수
    private double closeRate; // 폐업_률
    private int closeCount; // 폐업_점포_수
    private int franchiseStores; // 프랜차이즈_점포_수
}
