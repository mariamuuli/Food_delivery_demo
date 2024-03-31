package com.example.food_delivery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


@SpringBootApplication
@EnableScheduling
public class FoodDeliveryApplication {


    public static void main(String[] args) {
        SpringApplication.run(FoodDeliveryApplication.class, args);

        // Initial setup
        String location = "Pärnu";
        String vechicle = "Bike";

        // 1. Mina valin äpist need valikud
        DeliveryFeeCalculator deliveryFeeCalculator = new DeliveryFeeCalculator(location, vechicle);
        deliveryFeeCalculator.calculateBaseFee();

        // 2. Võta andmebaasist kõige viimatisemad ilmastiku andmed
        WeatherService w = new WeatherService();
        Weather weather = w.getWeatherData(location);

        // 3. Arvuta weather based extra fee
        deliveryFeeCalculator.calculateTotalExtraFee(weather.getTemperature(), weather.getWindSpeed(), weather.getWeatherPhenomenon());

        // 4. Arvuta total fee
        deliveryFeeCalculator.totalFee();

    }

//    @Scheduled(cron = "15* * * *")
//    public void getWeatherData() {
//        WeatherService w = new WeatherService();
//        w.getWeatherData("Tallinn-Harku");
//    }


}