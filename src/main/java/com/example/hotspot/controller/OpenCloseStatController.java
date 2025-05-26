package com.example.hotspot.controller;

import com.example.hotspot.dto.OpenCloseStatResponse;
import com.example.hotspot.service.OpenCloseStatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "개폐업 통계 조회 API", description = "개폐업 통계를 조회하는 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/open-close-stats")
public class OpenCloseStatController {

    private final OpenCloseStatService service;

    @Operation(summary = "개폐업 통계 전체 조회", description = "업종별 분기 기준 개폐업 통계 반환")
    @GetMapping
    public List<OpenCloseStatResponse> getAll() {
        return service.getAllStats();
    }
}
