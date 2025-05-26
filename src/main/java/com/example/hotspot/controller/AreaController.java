package com.example.hotspot.controller;

import com.example.hotspot.dto.AreaDTO;
import com.example.hotspot.service.AreaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "상권 조회 API", description = "상권 목록을 조회하는 API")
@RestController
@RequestMapping("/areas")
@RequiredArgsConstructor
public class AreaController {

    private final AreaService areaService;

    @Operation(
            summary = "상권 목록 조회",
            description = "상권 정보를 모두 조회, 위도와 경도도 포함"
    )
    @GetMapping
    public List<AreaDTO> getAllAreas() {
        return areaService.getAllAreas();
    }
}
