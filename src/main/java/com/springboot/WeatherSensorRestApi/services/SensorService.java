package com.springboot.WeatherSensorRestApi.services;

import com.springboot.WeatherSensorRestApi.models.Sensor;
import com.springboot.WeatherSensorRestApi.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    public boolean isNameUnique(String name) {
        // Проверка уникальности имени
        Optional<Sensor> existingSensor = sensorRepository.findByName(name);
        return existingSensor.isEmpty();
    }

    public boolean isSensorExists(String name) {
        // Проверка существования сенсора
        Optional<Sensor> existingSensor = sensorRepository.findByName(name);
        return existingSensor.isPresent();
    }

}
