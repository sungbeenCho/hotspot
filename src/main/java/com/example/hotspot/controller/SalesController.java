package com.example.hotspot.controller;

import com.example.hotspot.dto.SalesResponse;
import com.example.hotspot.service.SalesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@Tag(name = "매출 조회 API", description = "상권 및 업종별 매출 데이터를 조회하는 API")
@RestController
@RequestMapping("/areas")
@RequiredArgsConstructor
public class SalesController {

    private final SalesService salesService;

    @Operation(
            summary = "상권 매출 조회",
            description = """
                지정된 상권 ID를 기반으로 매출 데이터를 조회 
                - `categoryCode` 파라미터가 주어지면 해당 업종만 반환하고, 
                - 없으면 상권 내 모든 업종의 매출 데이터가 리스트로 반환
                """
    )
    @GetMapping("/{areaId}/sales")
    public List<SalesResponse> getSalesDetails(
            @Parameter(description = "상권 ID")
            @PathVariable String areaId,

            @Parameter(description = "업종 코드")
            @RequestParam(required = false) String categoryCode
    ) {
        return salesService.getSales(areaId, categoryCode);
    }
}
