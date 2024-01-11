package com.springboot.WeatherSensorRestApi.util;


import com.springboot.WeatherSensorRestApi.services.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.springboot.WeatherSensorRestApi.DTO.MeasurementDTO;

@Component
public class MeasurementValidator implements Validator {


    private final SensorService sensorService;

    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return MeasurementDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;
        Double value = measurementDTO.getValue();
        if (!sensorService.isSensorExists(measurementDTO.getSensor().getName())) {
            errors.rejectValue("sensor.name", "field.notfound", "This sensor is not registered in the system");
        }
        if (value < -100 || value > 100) {
            errors.rejectValue("value", "field.range", "The value should be between -100 and 100");
        }

    }
}

