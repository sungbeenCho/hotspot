package com.example.hotspot.repository;

import com.example.hotspot.domain.Sales;
import com.example.hotspot.dto.CategoryDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SalesRepository extends JpaRepository<Sales, Long> {

    boolean existsByAreaIdAndCategoryCodeAndQuarter(String areaId, String categoryCode, String quarter);

    List<Sales> findByAreaId(String areaId);

    List<Sales> findByAreaIdAndCategoryCode(String areaId, String categoryCode);

    // ✅ 여기에 아래 쿼리를 추가해줘!
    @Query("""
    SELECT DISTINCT new com.example.hotspot.dto.CategoryDTO(s.categoryCode, s.categoryName)
    FROM Sales s
    ORDER BY s.categoryCode ASC
""")
    List<CategoryDTO> findDistinctCategories();

}
