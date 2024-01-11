package com.springboot.WeatherSensorRestApi;


import com.springboot.WeatherSensorRestApi.services.MeasurementSender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;




@SpringBootApplication
public class WeatherSensorRestApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherSensorRestApiApplication.class, args);
        MeasurementSender measurementSender = measurementSender();
        measurementSender.sendMeasurements(10);
    }
    @Bean
    public static RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public static MeasurementSender measurementSender() {
        return new MeasurementSender(restTemplate());
    }



}