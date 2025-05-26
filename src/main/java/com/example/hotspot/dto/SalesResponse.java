package com.example.hotspot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@Schema(description = "상권 내 하나의 업종에 대한 매출 상세 응답")
public class SalesResponse {

    @Schema(description = "상권 ID", example = "3110382")
    private String areaId;

    @Schema(description = "상권 이름", example = "솔밭공원역 1번")
    private String areaName;

    @Schema(description = "서비스 업종 코드", example = "CS1001")
    private String categoryCode;

    @Schema(description = "서비스 업종 이름", example = "한식")
    private String categoryName;

    @Schema(description = "분기별 매출 상세 리스트")
    private List<QuarterlySales> quarters;
}
