package com.example.hotspot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "업종 정보")
public class CategoryDTO {

    @Schema(description = "서비스 업종 코드", example = "CS1001")
    private String categoryCode;

    @Schema(description = "서비스 업종 이름", example = "한식")
    private String categoryName;
}
