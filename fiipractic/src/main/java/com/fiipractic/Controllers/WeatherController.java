package com.fiipractic.Controllers;

import com.fiipractic.DTO.ResponseDTO;
import com.fiipractic.DTO.WeatherRequestDTO;
import com.fiipractic.Services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

@RequestMapping("weather")
@RestController
public class WeatherController {
    @Autowired
    private WeatherService weatherService;
    private final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
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

    @GetMapping("/details3")
    private ResponseEntity<?> getDetails3(
            @RequestBody List<WeatherRequestDTO> requests
    ) {
        List<Future<ResponseDTO>> futures = new ArrayList<>();

        for (WeatherRequestDTO req : requests) {
            Callable<ResponseDTO> task = () -> weatherService.getDetails(
                    req.getLat(), req.getLon(), req.getAqi(), req.getDays(), req.getAlerts()
            );

            Callable<ResponseDTO> securedTask = new DelegatingSecurityContextCallable<>(task);
            futures.add(executor.submit(securedTask));
        }

        List<ResponseDTO> responses = new ArrayList<>();

        try {
            for (Future<ResponseDTO> future : futures) {
                responses.add(future.get());
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
