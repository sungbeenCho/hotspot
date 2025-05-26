package com.example.hotspot.service;

import com.example.hotspot.domain.Sales;
import com.example.hotspot.dto.QuarterlySales;
import com.example.hotspot.dto.SalesResponse;
import com.example.hotspot.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SalesService {

    private final SalesRepository salesRepository;

    public List<SalesResponse> getSales(String areaId, String categoryCode) {
        List<Sales> salesList;

        if (categoryCode != null && !categoryCode.isBlank()) {
            salesList = salesRepository.findByAreaIdAndCategoryCode(areaId, categoryCode);
            if (salesList.isEmpty()) {
                throw new ResponseStatusException(NOT_FOUND, "해당 상권/업종에 대한 매출 데이터 없음.");
            }
            return List.of(buildSalesResponse(salesList));
        } else {
            salesList = salesRepository.findByAreaId(areaId);
            if (salesList.isEmpty()) {
                throw new ResponseStatusException(NOT_FOUND, "해당 상권에 대한 매출 데이터 없음.");
            }
            return salesList.stream()
                    .collect(Collectors.groupingBy(Sales::getCategoryCode))
                    .values()
                    .stream()
                    .map(this::buildSalesResponse)
                    .collect(Collectors.toList());
        }
    }

    private SalesResponse buildSalesResponse(List<Sales> salesList) {
        Sales sample = salesList.get(0);

        List<QuarterlySales> quarters = salesList.stream()
                .map(s -> QuarterlySales.builder()
                        .quarter(s.getQuarter())
                        .totalSales(s.getTotalSales())

                        // 순서 고정: gender
                        .genderSales(new LinkedHashMap<>() {{
                            put("female", s.getFemaleSales());
                            put("male", s.getMaleSales());
                        }})

                        // 순서 고정: age
                        .ageSales(new LinkedHashMap<>() {{
                            put("age_10", s.getAge10Sales());
                            put("age_20", s.getAge20Sales());
                            put("age_30", s.getAge30Sales());
                            put("age_40", s.getAge40Sales());
                            put("age_50", s.getAge50Sales());
                            put("age_60", s.getAge60Sales());
                        }})

                        // 순서 고정: day of week
                        .salesByDay(new LinkedHashMap<>() {{
                            put("mon", s.getMonSales());
                            put("tue", s.getTueSales());
                            put("wed", s.getWedSales());
                            put("thu", s.getThuSales());
                            put("fri", s.getFriSales());
                            put("sat", s.getSatSales());
                            put("sun", s.getSunSales());
                        }})

                        // 순서 고정: time ranges
                        .salesByTime(new LinkedHashMap<>() {{
                            put("00_06", s.getTime00_06Sales());
                            put("06_11", s.getTime06_11Sales());
                            put("11_14", s.getTime11_14Sales());
                            put("14_17", s.getTime14_17Sales());
                            put("17_21", s.getTime17_21Sales());
                            put("21_24", s.getTime21_24Sales());
                        }})
                        .build())
                .sorted(Comparator.comparing(QuarterlySales::getQuarter))
                .collect(Collectors.toList());

        return SalesResponse.builder()
                .areaId(sample.getAreaId())
                .areaName(sample.getAreaName())
                .categoryCode(sample.getCategoryCode())
                .categoryName(sample.getCategoryName())
                .quarters(quarters)
                .build();
    }
}
