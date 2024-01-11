package com.springboot.WeatherSensorRestApi.services;

import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public  class MeasurementSender {
    private static final String BASE_URL = "http://localhost:8080/measurements/add";
    private final RestTemplate restTemplate;

    public MeasurementSender(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void sendMeasurements(int count) {
        for (int i = 0; i < count; i++) {
            double temperature = generateRandomTemperature();
            boolean raining = generateRandomRaining();

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("value", temperature);
            requestBody.put("raining", raining);

            Map<String, Object> sensor = new HashMap<>();
            sensor.put("name", "sensor1");

            requestBody.put("sensor", sensor);
            restTemplate.postForObject(BASE_URL, requestBody, String.class);
        }
    }

    private boolean generateRandomRaining() {
        Random random = new Random();
        return random.nextBoolean();
    }

    private double generateRandomTemperature() {
        Random random = new Random();
        return -100 + (200 * random.nextDouble());
    }
}