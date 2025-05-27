package com.example.hotspot.loader;

import com.example.hotspot.domain.OpenCloseStat;
import com.example.hotspot.repository.OpenCloseStatRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OpenCloseCsvLoader {

    private final OpenCloseStatRepository repository;

    @PostConstruct
    public void loadOpenCloseData() {
        String fileName = "도봉구_상권-업종별 개폐업.csv";

        // ✅ DB에서 이미 존재하는 areaId + categoryCode 조합 불러오기
        Set<String> existingKeys = repository.findAll().stream()
                .map(s -> s.getAreaId().trim() + "_" + s.getCategoryCode().trim())
                .collect(Collectors.toSet());

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(fileName)),
                StandardCharsets.UTF_8))) {

            String line;
            reader.readLine(); // skip header
            int inserted = 0;
            int skipped = 0;

            while ((line = reader.readLine()) != null) {
                String[] t = line.split(",");

                if (t.length < 11) {
                    System.err.println("[OpenCloseCsvLoader] Invalid line: " + line);
                    continue;
                }

                String areaId = t[0].trim();
                String categoryCode = t[2].trim();
                String key = areaId + "_" + categoryCode;

                if (existingKeys.contains(key)) {
                    skipped++;
                    continue;
                }
                existingKeys.add(key);

                OpenCloseStat stat = new OpenCloseStat();
                stat.setAreaId(areaId);
                stat.setAreaName(t[1].trim());
                stat.setCategoryCode(categoryCode);
                stat.setCategoryName(t[3].trim());
                stat.setTotalStores(parseInt(t[4]));
                stat.setSimilarCategoryStores(parseInt(t[5]));
                stat.setOpenRate(parseDouble(t[6]));
                stat.setOpenCount(parseInt(t[7]));
                stat.setCloseRate(parseDouble(t[8]));
                stat.setCloseCount(parseInt(t[9]));
                stat.setFranchiseStores(parseInt(t[10]));

                repository.save(stat);
                inserted++;
            }

            System.out.printf("[OpenCloseCsvLoader] Load completed. Inserted: %d, Skipped (duplicates): %d%n", inserted, skipped);

        } catch (Exception e) {
            System.err.println("[OpenCloseCsvLoader] ERROR while loading file:");
            e.printStackTrace();
        }
    }

    private int parseInt(String s) {
        try {
            return Integer.parseInt(s.trim());
        } catch (Exception e) {
            return 0;
        }
    }

    private double parseDouble(String s) {
        try {
            return Double.parseDouble(s.trim());
        } catch (Exception e) {
            return 0.0;
        }
    }
}
