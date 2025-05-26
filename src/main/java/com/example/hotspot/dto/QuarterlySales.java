package com.example.hotspot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "분기별 매출 상세 정보")
public class QuarterlySales {

    @Schema(description = "분기 (예: 20241)")
    private String quarter;

    @Schema(description = "해당 분기의 총 매출 금액")
    private Long totalSales;

    @Schema(description = "성별별 매출 금액 (male, female)")
    private Map<String, Long> genderSales;

    @Schema(description = "연령대별 매출 금액 (age_10 ~ age_60)")
    private Map<String, Long> ageSales;

    @Schema(description = "요일별 매출 금액 (mon ~ sun)")
    private Map<String, Long> salesByDay;

    @Schema(description = "시간대별 매출 금액 (00_06 ~ 21_24)")
    private Map<String, Long> salesByTime;
}
