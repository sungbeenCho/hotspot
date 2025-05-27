package com.example.hotspot.service;

import com.example.hotspot.domain.OpenCloseStat;
import com.example.hotspot.dto.OpenCloseStatResponse;
import com.example.hotspot.repository.OpenCloseStatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OpenCloseStatService {

    private final OpenCloseStatRepository repository;

    public List<OpenCloseStatResponse> getStats(String areaId, String categoryCode) {
        List<OpenCloseStat> stats;

        if (categoryCode == null) {
            stats = repository.findByAreaId(areaId);
        } else {
            stats = repository.findByAreaIdAndCategoryCode(areaId, categoryCode);
        }

        return stats.stream().map(stat -> {
            OpenCloseStatResponse dto = new OpenCloseStatResponse();
            dto.setAreaId(stat.getAreaId());
            dto.setAreaName(stat.getAreaName());
            dto.setCategoryCode(stat.getCategoryCode());
            dto.setCategoryName(stat.getCategoryName());
            dto.setOpenCount(stat.getOpenCount());
            dto.setCloseCount(stat.getCloseCount());
            dto.setOpenRate(stat.getOpenRate());
            dto.setCloseRate(stat.getCloseRate());
            dto.setTotalStores(stat.getTotalStores());
            dto.setSimilarCategoryStores(stat.getSimilarCategoryStores());
            dto.setFranchiseStores(stat.getFranchiseStores());
            return dto;
        }).collect(Collectors.toList());
    }
}
