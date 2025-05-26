package com.example.hotspot.loader;

import com.example.hotspot.domain.Area;
import com.example.hotspot.repository.AreaRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AreaCsvLoader {

    private final AreaRepository areaRepository;

    @PostConstruct
    public void loadAreaData() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("도봉구_상권(wgs84).csv")),
                StandardCharsets.UTF_8))) {

            String line;
            reader.readLine(); // skip header

            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(",");

                Area area = new Area();
                area.setAreaId(tokens[0].trim());
                area.setAreaName(tokens[1].trim());
                area.setLongitude(Double.parseDouble(tokens[2].trim()));
                area.setLatitude(Double.parseDouble(tokens[3].trim()));

                areaRepository.save(area);
            }

            System.out.println("[AreaCsvLoader] Area data loaded successfully.");

        } catch (Exception e) {
            System.err.println("[AreaCsvLoader] ERROR while loading area data:");
            e.printStackTrace();
        }
    }
}
