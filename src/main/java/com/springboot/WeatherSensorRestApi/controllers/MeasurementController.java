package com.springboot.WeatherSensorRestApi.controllers;

import com.springboot.WeatherSensorRestApi.DTO.MeasurementDTO;
import com.springboot.WeatherSensorRestApi.models.Measurement;
import com.springboot.WeatherSensorRestApi.services.MeasurementService;
import com.springboot.WeatherSensorRestApi.util.MeasurementValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;

    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementController(MeasurementService measurementService, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.measurementValidator = measurementValidator;
    }

    @PostMapping("/add")
    public ResponseEntity<String> add(@RequestBody @Valid MeasurementDTO measurementDTO,
                                      BindingResult bindingResult) {
        measurementValidator.validate(measurementDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errorList = bindingResult.getFieldErrors();
            for (FieldError error : errorList) {
                errorMessage.append("Error in field ")
                        .append(error.getField())
                        .append(" - ").append(error.getDefaultMessage());
            }
            errorMessage.append(" Date: " + LocalDateTime.now());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage.toString());
        }
        measurementService.save(convertToMeasurements(measurementDTO));
        return ResponseEntity.ok("Measurements registration successful " + "Date: " + LocalDateTime.now());
    }

    @GetMapping
    @ResponseBody
    public List<Measurement> findAll() {
        return measurementService.findAll();
    }

    @GetMapping("/rainyDaysCount")
    @ResponseBody
    public int countOfRainyDays(){
        return  measurementService.countOfRainyDays();
    }

    private Measurement convertToMeasurements(MeasurementDTO measurementDTO) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(measurementDTO, Measurement.class);
    }
}
