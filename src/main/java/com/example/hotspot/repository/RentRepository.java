package com.example.hotspot.repository;

import com.example.hotspot.domain.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentRepository extends JpaRepository<Rent, Long> {
    List<Rent> findAll();
}
