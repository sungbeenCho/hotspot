package com.example.hotspot.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OpenCloseStatResponse {
    private String quarter;
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
