package com.example.hotspot.controller;

import com.example.hotspot.dto.RentResponse;
import com.example.hotspot.service.RentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "임대료 조회 API", description = "상권별 평균 임대료 조회하는 API")
@RestController
@RequestMapping("/rents")
@RequiredArgsConstructor
public class RentController {

    private final RentService rentService;

    @Operation(summary = "전체 상권 임대료 조회", description = "분기별 임대료 제공 (1평당(3.3m2) 기준)")
    @GetMapping
    public List<RentResponse> getAllRents() {
        return rentService.getAllRents();
    }
}
