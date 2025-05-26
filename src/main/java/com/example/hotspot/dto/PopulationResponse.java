package com.example.hotspot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "유동인구 응답")
public class PopulationResponse {

    @Schema(description = "상권 ID")
    private String areaId;

    @Schema(description = "상권 이름")
    private String areaName;

    @Schema(description = "조회 년월", example = "2025-01")
    private String month;

    @Schema(description = "주말 여부 (0=평일, 1=주말)")
    private int isWeekend;

    @Schema(description = "시간대별 유동인구")
    private List<HourlyFlowEntry> hourlyFlow;

    @Schema(description = "성별/연령별 유동인구")
    private Map<String, Map<String, Double>> genderAgeFlow;
}
