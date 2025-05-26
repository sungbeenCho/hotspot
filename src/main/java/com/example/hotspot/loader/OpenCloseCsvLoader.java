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
        String fileName = "도봉구_점포 개폐업(19~24년).csv";

        // ✅ DB에서 이미 존재하는 quarter + categoryCode 조합 불러오기
        Set<String> existingKeys = repository.findAll().stream()
                .map(s -> s.getQuarter().trim() + "_" + s.getCategoryCode().trim())
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

                if (t.length < 10) {
                    System.err.println("[OpenCloseCsvLoader] Invalid line: " + line);
                    continue;
                }

                String quarter = t[0].trim();
                String categoryCode = t[1].trim();
                String key = quarter + "_" + categoryCode;

                if (existingKeys.contains(key)) {
                    skipped++;
                    continue;
                }
                existingKeys.add(key);

                OpenCloseStat stat = new OpenCloseStat();
                stat.setQuarter(quarter);
                stat.setCategoryCode(categoryCode);
                stat.setCategoryName(t[2].trim());
                stat.setTotalStores(parseInt(t[3]));
                stat.setSimilarCategoryStores(parseInt(t[4]));
                stat.setOpenRate(parseDouble(t[5]));
                stat.setOpenCount(parseInt(t[6]));
                stat.setCloseRate(parseDouble(t[7]));
                stat.setCloseCount(parseInt(t[8]));
                stat.setFranchiseStores(parseInt(t[9]));

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
