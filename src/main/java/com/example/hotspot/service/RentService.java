package com.example.hotspot.service;

import com.example.hotspot.domain.Rent;
import com.example.hotspot.dto.RentResponse;
import com.example.hotspot.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RentService {

    private final RentRepository rentRepository;

    public List<RentResponse> getAllRents() {
        List<Rent> rents = rentRepository.findAll();
        Map<String, RentResponse> responseMap = new LinkedHashMap<>();

        for (Rent rent : rents) {
            String key = rent.getAreaId();

            RentResponse response = responseMap.computeIfAbsent(key, id -> {
                RentResponse r = new RentResponse();
                r.setAreaId(rent.getAreaId());
                r.setAreaName(rent.getAreaName());
                r.setMonthlyRents(new LinkedHashMap<>());
                return r;
            });

            response.getMonthlyRents().put(rent.getQuarter(), rent.getRent());
        }

        return new ArrayList<>(responseMap.values());
    }
}
