package com.example.hotspot.service;

import com.example.hotspot.dto.CategoryDTO;
import com.example.hotspot.repository.SalesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final SalesRepository salesRepository;

    public List<CategoryDTO> getAllCategories() {
        return salesRepository.findDistinctCategories();
    }
}
