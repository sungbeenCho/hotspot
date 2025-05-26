package com.example.hotspot.controller;

import com.example.hotspot.dto.CategoryDTO;
import com.example.hotspot.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "업종 조회 API", description = "서비스 업종 목록을 조회하는 API")
@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(
            summary = "업종 목록 조회",
            description = "모든 서비스 업종 목록을 조회"
    )
    @GetMapping
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }
}
