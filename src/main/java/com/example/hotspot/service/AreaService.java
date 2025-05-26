package com.example.hotspot.service;

import com.example.hotspot.dto.AreaDTO;
import com.example.hotspot.repository.AreaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaService {

    private final AreaRepository areaRepository;

    public List<AreaDTO> getAllAreas() {
        return areaRepository.findAll().stream()
                .map(a -> new AreaDTO(
                        a.getAreaId(),
                        a.getAreaName(),
                        a.getLatitude(),
                        a.getLongitude()
                ))
                .toList();
    }
}
