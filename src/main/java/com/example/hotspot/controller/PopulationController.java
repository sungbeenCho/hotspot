package com.example.hotspot.controller;

import com.example.hotspot.dto.PopulationResponse;
import com.example.hotspot.service.PopulationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "유동인구 API", description = "과거 및 예측 유동인구 데이터를 조회하는 API")
@RestController
@RequestMapping("/areas/{areaId}/population")
@RequiredArgsConstructor
public class PopulationController {

    private final PopulationService service;

    @Operation(summary = "과거 유동인구 조회", description = "2024년 유동인구 조회")
    @GetMapping("/history")
    public List<PopulationResponse> getHistory(@PathVariable String areaId) {
        return service.getPopulationByArea(areaId, false);
    }

    @Operation(summary = "예측 유동인구 조회", description = "2025년 유동인구 조회")
    @GetMapping("/forecast")
    public List<PopulationResponse> getForecast(@PathVariable String areaId) {
        return service.getPopulationByArea(areaId, true);
    }
}
