package com.example.hotspot.repository;

import com.example.hotspot.domain.OpenCloseStat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpenCloseStatRepository extends JpaRepository<OpenCloseStat, Long> {
}
