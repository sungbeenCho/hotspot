package com.example.hotspot.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "areas")
@Getter
@Setter
@NoArgsConstructor
public class Area {

    @Id
    private String areaId;

    private String areaName;

    private Double latitude;

    private Double longitude;
}
