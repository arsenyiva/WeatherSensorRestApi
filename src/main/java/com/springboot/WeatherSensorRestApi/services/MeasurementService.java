package com.springboot.WeatherSensorRestApi.services;


import com.springboot.WeatherSensorRestApi.models.Measurement;
import com.springboot.WeatherSensorRestApi.models.Sensor;
import com.springboot.WeatherSensorRestApi.repositories.MeasurementRepository;
import com.springboot.WeatherSensorRestApi.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorRepository sensorRepository) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Measurement measurement) {
        // Проверяем, существует ли сенсор в базе данных
        // Если сенсор уже существует, добавляем измерения к нему
        Optional<Sensor> existingSensor = sensorRepository.findByName(measurement.getSensor().getName());
        if (existingSensor.isPresent()) {
            measurement.setSensor(existingSensor.get());
            measurementRepository.save(measurement);
        }
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    public Integer countOfRainyDays() {
        int count = 0;
        for (Measurement measurement : measurementRepository.findAll()) {
            if (measurement.getRaining()) {
                count++;
            }
        }
        return count;
    }
}

