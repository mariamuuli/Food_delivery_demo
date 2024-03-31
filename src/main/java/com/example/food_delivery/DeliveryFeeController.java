package com.example.food_delivery;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DeliveryFeeController {
    @GetMapping("/deliveryFee")
    public ResponseEntity<Double> getDeliveryFee(@RequestParam String city, @RequestParam String vehicleType) {
        try {
            DeliveryFeeCalculator deliveryFeeCalculator = new DeliveryFeeCalculator(city, vehicleType);
            deliveryFeeCalculator.calculateBaseFee();
            WeatherService weatherService = new WeatherService();
            Weather weather = weatherService.getWeatherData(city);
            deliveryFeeCalculator.calculateTotalExtraFee(weather.getTemperature(), weather.getWindSpeed(), weather.getWeatherPhenomenon());
            double totalFee = deliveryFeeCalculator.totalFee();
            return ResponseEntity.ok(totalFee);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}

