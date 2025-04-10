package com.fiipractic.Controllers;

import com.fiipractic.DTO.ResponseDTO;
import com.fiipractic.Services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("weather")
@RestController
public class WeatherController {
    @Autowired
    private WeatherService weatherService;

    @GetMapping("/details")
    private ResponseEntity<?> getDetails(
            @RequestParam(defaultValue = "12") String lat,
            @RequestParam(defaultValue = "12") String lon,
            @RequestParam(defaultValue = "no") String aqi,
            @RequestParam(defaultValue = "1") String days,
            @RequestParam(defaultValue = "no") String alerts
    ) {
        ResponseDTO responseDTO;
        try {
            responseDTO = weatherService.getDetails(lat, lon, aqi, days, alerts);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
