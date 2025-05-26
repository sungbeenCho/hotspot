package com.example.hotspot.repository;

import com.example.hotspot.domain.Population;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PopulationRepository extends JpaRepository<Population, Long> {
    boolean existsByAreaIdAndYearAndMonthAndIsWeekend(String areaId, int year, int month, boolean isWeekend);
    List<Population> findByAreaIdAndIsForecast(String areaId, boolean isForecast);
}
