package com.springboot.WeatherSensorRestApi.util;

import com.springboot.WeatherSensorRestApi.DTO.SensorDTO;
import com.springboot.WeatherSensorRestApi.services.SensorService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;

    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SensorDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "field.required", "Sensor name should not empty");

        if (sensorDTO.getName().length() < 3 || sensorDTO.getName().length() > 30) {
            errors.rejectValue("name", "field.length", "Sensor name length must be between 3 and 30 characters");
        }
        if (!sensorService.isNameUnique(sensorDTO.getName())) {
            errors.rejectValue("name", "field.unique", "Sensor name must be unique");
        }
    }
}

