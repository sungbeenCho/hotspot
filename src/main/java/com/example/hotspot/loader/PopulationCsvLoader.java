package com.example.hotspot.loader;

import com.example.hotspot.domain.Population;
import com.example.hotspot.repository.PopulationRepository;
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
public class PopulationCsvLoader {

    private final PopulationRepository repository;

    @PostConstruct
    public void loadPopulationData() {
        // 🔹 중복 데이터 미리 조회해서 키셋 생성
        Set<String> existingKeys = repository.findAll().stream()
                .map(p -> p.getAreaId() + "_" + p.getYear() + "_" + p.getMonth() + "_" + p.isWeekend())
                .collect(Collectors.toSet());

        loadFile("도봉구 유동인구_24년.csv", false, existingKeys);
        loadFile("도봉구 유동인구_25년.csv", true, existingKeys);
    }

    private void loadFile(String fileName, boolean isForecast, Set<String> existingKeys) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(fileName)),
                StandardCharsets.UTF_8))) {

            String line;
            reader.readLine(); // skip header

            while ((line = reader.readLine()) != null) {
                String[] t = line.split(",");

                int year = Integer.parseInt(t[0].trim());
                int month = Integer.parseInt(t[1].trim());
                boolean isWeekend = Integer.parseInt(t[2].trim()) == 1;
                String areaId = t[3].trim();
                String key = areaId + "_" + year + "_" + month + "_" + isWeekend;

                if (existingKeys.contains(key)) {
                    continue;
                }

                Population p = new Population();
                p.setYear(year);
                p.setMonth(month);
                p.setWeekend(isWeekend);
                p.setForecast(isForecast);
                p.setAreaId(areaId);
                p.setAreaName(t[4].trim());

                // 시간대별 유동인구
                for (int i = 0; i <= 23; i++) {
                    double value = Double.parseDouble(t[5 + i].trim());
                    Population.class.getMethod("setHour" + String.format("%02d", i), double.class).invoke(p, value);
                }

                // 남성 연령대
                p.setMale10(Double.parseDouble(t[29].trim()));
                p.setMale20(Double.parseDouble(t[30].trim()));
                p.setMale30(Double.parseDouble(t[31].trim()));
                p.setMale40(Double.parseDouble(t[32].trim()));
                p.setMale50(Double.parseDouble(t[33].trim()));
                p.setMale60(Double.parseDouble(t[34].trim()));

                // 여성 연령대
                p.setFemale10(Double.parseDouble(t[35].trim()));
                p.setFemale20(Double.parseDouble(t[36].trim()));
                p.setFemale30(Double.parseDouble(t[37].trim()));
                p.setFemale40(Double.parseDouble(t[38].trim()));
                p.setFemale50(Double.parseDouble(t[39].trim()));
                p.setFemale60(Double.parseDouble(t[40].trim()));

                repository.save(p);
            }

            System.out.println("[PopulationCsvLoader] Loaded file: " + fileName);

        } catch (Exception e) {
            System.err.println("[PopulationCsvLoader] Error loading: " + fileName);
            e.printStackTrace();
        }
    }
}
