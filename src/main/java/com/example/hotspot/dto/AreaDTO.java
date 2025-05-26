package com.example.hotspot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "상권 정보")
public class AreaDTO {

    @Schema(description = "상권 ID", example = "3110382")
    private String areaId;

    @Schema(description = "상권 이름", example = "솔밭공원역 1번")
    private String areaName;

    @Schema(description = "위도", example = "37.655861")
    private Double latitude;

    @Schema(description = "경도", example = "127.013939")
    private Double longitude;
}
