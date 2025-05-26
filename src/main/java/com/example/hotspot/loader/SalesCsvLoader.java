package com.example.hotspot.loader;

import com.example.hotspot.domain.Sales;
import com.example.hotspot.repository.SalesRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SalesCsvLoader {

    private final SalesRepository salesRepository;

    @PostConstruct
    public void loadSalesData() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("도봉구_상권(wgs84).csv")),
                StandardCharsets.UTF_8))) {

            // Step 1: Load existing keys from DB into a Set
            Set<String> existingKeys = salesRepository.findAll().stream()
                    .map(s -> s.getAreaId() + "_" + s.getCategoryCode() + "_" + s.getQuarter())
                    .collect(Collectors.toSet());

            int insertedCount = 0;
            int skippedCount = 0;

            String line;
            reader.readLine(); // skip header

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                String areaId = tokens[0].trim();
                String categoryCode = tokens[5].trim();
                String quarter = tokens[4].trim();
                String key = areaId + "_" + categoryCode + "_" + quarter;

                if (existingKeys.contains(key)) {
                    skippedCount++;
                    continue;
                }

                Sales sales = new Sales();
                sales.setAreaId(areaId);
                sales.setAreaName(tokens[1].trim());
                sales.setCategoryCode(categoryCode);
                sales.setCategoryName(tokens[6].trim());
                sales.setQuarter(quarter);
                sales.setTotalSales(parseLong(tokens[7]));

                sales.setMonSales(parseLong(tokens[8]));
                sales.setTueSales(parseLong(tokens[9]));
                sales.setWedSales(parseLong(tokens[10]));
                sales.setThuSales(parseLong(tokens[11]));
                sales.setFriSales(parseLong(tokens[12]));
                sales.setSatSales(parseLong(tokens[13]));
                sales.setSunSales(parseLong(tokens[14]));

                sales.setTime00_06Sales(parseLong(tokens[15]));
                sales.setTime06_11Sales(parseLong(tokens[16]));
                sales.setTime11_14Sales(parseLong(tokens[17]));
                sales.setTime14_17Sales(parseLong(tokens[18]));
                sales.setTime17_21Sales(parseLong(tokens[19]));
                sales.setTime21_24Sales(parseLong(tokens[20]));

                sales.setMaleSales(parseLong(tokens[21]));
                sales.setFemaleSales(parseLong(tokens[22]));

                sales.setAge10Sales(parseLong(tokens[23]));
                sales.setAge20Sales(parseLong(tokens[24]));
                sales.setAge30Sales(parseLong(tokens[25]));
                sales.setAge40Sales(parseLong(tokens[26]));
                sales.setAge50Sales(parseLong(tokens[27]));
                sales.setAge60Sales(parseLong(tokens[28]));

                salesRepository.save(sales);
                insertedCount++;
            }

            System.out.println("[SalesCsvLoader] Load completed. Inserted: " + insertedCount + ", Skipped (already exists): " + skippedCount);

        } catch (Exception e) {
            System.err.println("[SalesCsvLoader] ERROR while loading sales data:");
            e.printStackTrace();
        }
    }

    private Long parseLong(String str) {
        try {
            return Long.parseLong(str.trim());
        } catch (NumberFormatException e) {
            return 0L;
        }
    }
}
