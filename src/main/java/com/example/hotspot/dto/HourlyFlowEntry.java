package com.example.hotspot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HourlyFlowEntry {
    private String hour;
    private double value;
}
