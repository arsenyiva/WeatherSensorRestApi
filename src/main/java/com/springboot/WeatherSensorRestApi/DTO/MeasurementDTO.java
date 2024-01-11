package com.springboot.WeatherSensorRestApi.DTO;

import com.springboot.WeatherSensorRestApi.models.Sensor;
import java.sql.Timestamp;


public class MeasurementDTO {

    private Double value;

    private Boolean raining;

    private Sensor sensor;

    private Timestamp timestamp;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
