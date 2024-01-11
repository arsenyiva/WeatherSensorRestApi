package com.springboot.WeatherSensorRestApi.repositories;

import com.springboot.WeatherSensorRestApi.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

}
