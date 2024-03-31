package com.example.food_delivery;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.net.URL;


public class WeatherService {


    public Weather getWeatherData(String location) {
        System.out.println("Getting weather data..");
        if (location.equals("Tallinn")) {
            location = "Tallinn-Harku";
        }
        if (location.equals("Tartu")) {
            location = "Tartu-Tõravere";
        }

        try {
            URL url = new URL("https://www.ilmateenistus.ee/ilma_andmed/xml/observations.php");
            InputStream is = url.openStream();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(is);
            doc.getDocumentElement().normalize();

            // Pärin kõik "station" objektid XML-ist
            NodeList stationNodes = doc.getElementsByTagName("station");

            // Siin on muutujad, mida küsitud on
            String nameOfStation = ""; //Name of the station
            String wmoCodeOfStation = ""; // WMO code of the station
            String airTemperature = ""; // Air temperature
            String windSpeed = ""; // Wind speed
            String weatherPhenomenon = ""; // Weather phenomenon
            Long timestamp = 0L; // Timestamp of the observations

            // Itereerin üle "station" objektide
            for (int i = 0; i < stationNodes.getLength(); i++) {
                Node stationNode = stationNodes.item(i);
                // System.out.println(stationNode.getNodeName());
                NodeList stationElements = stationNode.getChildNodes();

                // Filter out only the stations that we are interested in:
                for (int j = 0; j < stationElements.getLength(); j++) {
                    Node nameElement = stationElements.item(j);
                    if (location.equals(nameElement.getTextContent())) {
                        for (int k = 0; k < stationElements.getLength(); k++) {

                            Node nameElementInner = stationElements.item(k);
                            if (nameElementInner.getNodeName().equals("wmocode")) {
                                wmoCodeOfStation = nameElementInner.getTextContent();
                            }
                            if (nameElementInner.getNodeName().equals("airtemperature")) {
                                airTemperature = nameElementInner.getTextContent();
                            }
                            if (nameElementInner.getNodeName().equals("windspeed")) {
                                windSpeed = nameElementInner.getTextContent();
                            }
                            if (nameElementInner.getNodeName().equals("phenomenon")) {
                                weatherPhenomenon = nameElementInner.getTextContent();
                            }
                        }

                        System.out.println("Found the station element: " + nameElement.getTextContent());
                        System.out.println("Found the wmoCodeOfStation element: " + wmoCodeOfStation);
                        System.out.println("Found the airTemperature element: " + airTemperature);
                        System.out.println("Found the windSpeed element: " + windSpeed);
                        System.out.println("Found the weatherPhenomenon element: " + weatherPhenomenon);
                        System.out.println();
                        Weather weather = new Weather();
                        weather.setLocation(location);
                        weather.setTemperature(Double.valueOf(airTemperature));
                        weather.setWmoCodeOfStation(wmoCodeOfStation);
                        weather.setWindSpeed(Double.valueOf(windSpeed));
                        weather.setWeatherPhenomenon(weatherPhenomenon);
                        return weather;
                        //weatherRepository.save(weather);

                        //repository.save(weather);
                        //repository.findWeatherByLocation("Tallinn-Harku");
                        //System.out.println(WeatherRepository.findWeatherByLocation("Tallinn-Harku");
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}