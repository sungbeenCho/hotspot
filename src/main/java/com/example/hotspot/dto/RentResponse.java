package com.example.hotspot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@Schema(description = "임대료 응답 DTO")
public class RentResponse {

    @Schema(description = "상권 ID")
    private String areaId;

    @Schema(description = "상권 이름")
    private String areaName;

    @Schema(description = "분기별 임대료 (ex. 20221)")
    private Map<String, Long> monthlyRents;
}
