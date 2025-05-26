package com.example.hotspot.service;

import com.example.hotspot.domain.Area;
import com.example.hotspot.domain.Population;
import com.example.hotspot.dto.HourlyFlowEntry;
import com.example.hotspot.dto.PopulationResponse;
import com.example.hotspot.repository.AreaRepository;
import com.example.hotspot.repository.PopulationRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PopulationService {

    private final PopulationRepository repository;
    private final AreaRepository areaRepository;

    private final Map<String, String> areaNameCache = new HashMap<>();

    @PostConstruct
    public void initAreaNameCache() {
        areaNameCache.putAll(
                areaRepository.findAll().stream()
                        .collect(Collectors.toMap(Area::getAreaId, Area::getAreaName))
        );
    }

    public List<PopulationResponse> getPopulationByArea(String areaId, boolean isForecast) {
        return repository.findByAreaIdAndIsForecast(areaId, isForecast).stream()
                .map(p -> {
                    PopulationResponse res = new PopulationResponse();
                    res.setAreaId(p.getAreaId());
                    res.setAreaName(areaNameCache.getOrDefault(p.getAreaId(), ""));
                    res.setMonth(String.format("%d-%02d", p.getYear(), p.getMonth()));
                    res.setIsWeekend(p.isWeekend() ? 1 : 0);
                    res.setHourlyFlow(buildHourlyList(p));
                    res.setGenderAgeFlow(buildGenderMap(p));
                    return res;
                })
                .collect(Collectors.toList());
    }

    private List<HourlyFlowEntry> buildHourlyList(Population p) {
        List<HourlyFlowEntry> list = new ArrayList<>();
        list.add(new HourlyFlowEntry("00", p.getHour00()));
        list.add(new HourlyFlowEntry("01", p.getHour01()));
        list.add(new HourlyFlowEntry("02", p.getHour02()));
        list.add(new HourlyFlowEntry("03", p.getHour03()));
        list.add(new HourlyFlowEntry("04", p.getHour04()));
        list.add(new HourlyFlowEntry("05", p.getHour05()));
        list.add(new HourlyFlowEntry("06", p.getHour06()));
        list.add(new HourlyFlowEntry("07", p.getHour07()));
        list.add(new HourlyFlowEntry("08", p.getHour08()));
        list.add(new HourlyFlowEntry("09", p.getHour09()));
        list.add(new HourlyFlowEntry("10", p.getHour10()));
        list.add(new HourlyFlowEntry("11", p.getHour11()));
        list.add(new HourlyFlowEntry("12", p.getHour12()));
        list.add(new HourlyFlowEntry("13", p.getHour13()));
        list.add(new HourlyFlowEntry("14", p.getHour14()));
        list.add(new HourlyFlowEntry("15", p.getHour15()));
        list.add(new HourlyFlowEntry("16", p.getHour16()));
        list.add(new HourlyFlowEntry("17", p.getHour17()));
        list.add(new HourlyFlowEntry("18", p.getHour18()));
        list.add(new HourlyFlowEntry("19", p.getHour19()));
        list.add(new HourlyFlowEntry("20", p.getHour20()));
        list.add(new HourlyFlowEntry("21", p.getHour21()));
        list.add(new HourlyFlowEntry("22", p.getHour22()));
        list.add(new HourlyFlowEntry("23", p.getHour23()));
        return list;
    }

    private Map<String, Map<String, Double>> buildGenderMap(Population p) {
        Map<String, Double> male = new LinkedHashMap<>();
        male.put("10", p.getMale10());
        male.put("20", p.getMale20());
        male.put("30", p.getMale30());
        male.put("40", p.getMale40());
        male.put("50", p.getMale50());
        male.put("60", p.getMale60());

        Map<String, Double> female = new LinkedHashMap<>();
        female.put("10", p.getFemale10());
        female.put("20", p.getFemale20());
        female.put("30", p.getFemale30());
        female.put("40", p.getFemale40());
        female.put("50", p.getFemale50());
        female.put("60", p.getFemale60());

        Map<String, Map<String, Double>> result = new LinkedHashMap<>();
        result.put("male", male);
        result.put("female", female);

        return result;
    }
}
