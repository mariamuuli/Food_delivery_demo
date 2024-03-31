package com.example.food_delivery;

import lombok.Data;

@Data
public class DeliveryFeeCalculator {
    private String city;
    private double regionalBaseFee;

    private double totalExtraFee;

    private String vehicleType;

    public DeliveryFeeCalculator(String city, String vehicleType) {
        this.city = city;
        this.vehicleType = vehicleType;
    }

    public Double calculateBaseFee() {
        if (city.equalsIgnoreCase("Tallinn") && vehicleType.equalsIgnoreCase("Car")) {
            regionalBaseFee = 4;
        } else if (city.equalsIgnoreCase("Tallinn") && vehicleType.equalsIgnoreCase("Scooter")) {
            regionalBaseFee = 3.5;
        } else if (city.equalsIgnoreCase("Tallinn") && vehicleType.equalsIgnoreCase("Bike")) {
            regionalBaseFee = 3;
        } else if (city.equalsIgnoreCase("Tartu") && vehicleType.equalsIgnoreCase("Car")) {
            regionalBaseFee = 3.5;
        } else if (city.equalsIgnoreCase("Tartu") && vehicleType.equalsIgnoreCase("Scooter")) {
            regionalBaseFee = 3;
        } else if (city.equalsIgnoreCase("Tartu") && vehicleType.equalsIgnoreCase("Bike")) {
            regionalBaseFee = 2.5;
        } else if (city.equalsIgnoreCase("Pärnu") && vehicleType.equalsIgnoreCase("Car")) {
            regionalBaseFee = 3;
        } else if (city.equalsIgnoreCase("Pärnu") && vehicleType.equalsIgnoreCase("Scooter")) {
            regionalBaseFee = 2.5;
        } else if (city.equalsIgnoreCase("Pärnu") && vehicleType.equalsIgnoreCase("Bike")) {
            regionalBaseFee = 2;
        }
        System.out.println("Regional base fee is: " + getRegionalBaseFee());
        return regionalBaseFee;
    }

    public void calculateExtraFee() {
        double airTemperature = 0, windSpeed = 0;
        String weatherPhenomenon = "Snow";
        double ATEF = 0, WSEF = 0, WPEF = 0;
        if (airTemperature < -10 && vehicleType.equalsIgnoreCase("Bike") || vehicleType.equalsIgnoreCase("Scooter")) {
            ATEF = 1;
        } else if (airTemperature <= 0 && airTemperature <= -10 && vehicleType.equalsIgnoreCase("Bike") || vehicleType.equalsIgnoreCase("Scooter")) {
            ATEF = 0.5;
        }
        if (windSpeed >= 10 && windSpeed <= 20 && vehicleType.equalsIgnoreCase("Bike")) {
            WSEF = 0.5;
        } else if (windSpeed > 20 && vehicleType.equalsIgnoreCase("Bike")) {
            System.out.println("Usage of selected vehicle type is forbidden");
        }
        if (weatherPhenomenon.equalsIgnoreCase("Snow") || weatherPhenomenon.equalsIgnoreCase("Sleet") && vehicleType.equalsIgnoreCase("Bike") || vehicleType.equalsIgnoreCase("Scooter")) {
            WPEF = 1;
        } else if (weatherPhenomenon.equalsIgnoreCase("Rain") && vehicleType.equalsIgnoreCase("Bike") || vehicleType.equalsIgnoreCase("Scooter")) {
            WPEF = 0.5;
        } else if (weatherPhenomenon.equalsIgnoreCase("Glaze") || weatherPhenomenon.equalsIgnoreCase("Hail") || weatherPhenomenon.equalsIgnoreCase("Thunder")) {
            System.out.println("Usage of selected vehicle type is forbidden");
        }
        double extraFee = ATEF + WSEF + WPEF;
        System.out.println("extraFee is: " + extraFee);
    }

    public double airTemperatureExtraFee(double airTemperature) {
        double ATEF = 0.0;
        if (airTemperature < -10 && (vehicleType.equalsIgnoreCase("Bike") || vehicleType.equalsIgnoreCase("Scooter"))) {
            ATEF = 1;
        } else if (airTemperature <= 0 && airTemperature >= -10 && (vehicleType.equalsIgnoreCase("Bike") || vehicleType.equalsIgnoreCase("Scooter"))) {
            ATEF = 0.5;
        }
        return ATEF;
    }

    public double windSpeedExtraFee(double windSpeed) {
        double WSEF = 0;
        // TODO: Exctract Bike eraldi conditioniks, sest kehtib mõlemale.
        if (windSpeed >= 10 && windSpeed <= 20 && vehicleType.equalsIgnoreCase("Bike")) {
            WSEF = 0.5;
        } else if (windSpeed > 20 && vehicleType.equalsIgnoreCase("Bike")) {
            System.out.println("Usage of selected vehicle type is forbidden");
        }
        return WSEF;
    }

    public double weatherPhenomenonExtraFee(String weatherPhenomenon) {
        double WPEF = 0;
        // TODO: Extract Bike & Scooter
        if (weatherPhenomenon.equalsIgnoreCase("Snow") || weatherPhenomenon.equalsIgnoreCase("Sleet") && (vehicleType.equalsIgnoreCase("Bike") || vehicleType.equalsIgnoreCase("Scooter"))) {
            WPEF = 1;
        } else if (weatherPhenomenon.equalsIgnoreCase("Rain") && (vehicleType.equalsIgnoreCase("Bike") || vehicleType.equalsIgnoreCase("Scooter"))) {
            WPEF = 0.5;
        } else if (weatherPhenomenon.equalsIgnoreCase("Glaze") || weatherPhenomenon.equalsIgnoreCase("Hail") || weatherPhenomenon.equalsIgnoreCase("Thunder")) {
            System.out.println("Usage of selected vehicle type is forbidden");
        }
        return WPEF;
    }

    public double calculateTotalExtraFee(double airTemperature, double windSpeed, String phenomenon) {
        System.out.println("Calculating extra fee for Air temp: " + airTemperature);
        double airTempFee = airTemperatureExtraFee(airTemperature);
        System.out.println("Air temp fee: " + airTempFee);

        System.out.println("Calculating extra fee for wind speed: " + windSpeed);
        double windSpeedFee = windSpeedExtraFee(windSpeed);
        System.out.println("Wind speed fee: " + windSpeedFee);

        System.out.println("Calculating extra fee for phenomenon: " + phenomenon);
        double phenomenonFee = weatherPhenomenonExtraFee(phenomenon);
        System.out.println("Phenomenon fee: " + phenomenonFee);

        totalExtraFee = airTempFee + windSpeedFee + phenomenonFee;
        System.out.println("ExtraFee is: " + totalExtraFee);
        return totalExtraFee;
    }

    public double totalFee() {
        double totalFee = totalExtraFee + regionalBaseFee;
        System.out.println("Your delivery total fee is: " + totalFee);
        return totalFee;
    }
}