package com.example.food_delivery;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double temperature;
    private String location;
    private String wmoCodeOfStation;
    private Double windSpeed;
    private String weatherPhenomenon;

    public Weather() {
    }
}