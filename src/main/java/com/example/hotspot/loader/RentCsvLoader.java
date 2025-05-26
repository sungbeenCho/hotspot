package com.example.hotspot.loader;

import com.example.hotspot.domain.Rent;
import com.example.hotspot.repository.RentRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class RentCsvLoader {

    private final RentRepository rentRepository;

    @PostConstruct
    public void load() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("도봉구_임대료 (22년~24년).csv")),
                StandardCharsets.UTF_8))) {

            String line;
            reader.readLine(); // skip header

            while ((line = reader.readLine()) != null) {
                String[] t = line.split(",");

                Rent rent = new Rent();
                rent.setAreaId(t[0].trim());
                rent.setAreaName(t[1].trim());
                rent.setQuarter(t[2].trim());
                rent.setRent(Long.parseLong(t[3].replace(",", "").trim()));

                rentRepository.save(rent);
            }

            System.out.println("[RentCsvLoader] Load completed.");

        } catch (Exception e) {
            System.err.println("[RentCsvLoader] ERROR:");
            e.printStackTrace();
        }
    }
}
