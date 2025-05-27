package com.example.hotspot.repository;

import com.example.hotspot.domain.OpenCloseStat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpenCloseStatRepository extends JpaRepository<OpenCloseStat, Long> {
    List<OpenCloseStat> findByAreaId(String areaId);
    List<OpenCloseStat> findByAreaIdAndCategoryCode(String areaId, String categoryCode);
}
