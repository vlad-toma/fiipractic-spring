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
import java.util.concurrent.*;

@RequestMapping("weather")
@RestController
public class WeatherController {
    private final WeatherService weatherService;
    private final ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

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

    @GetMapping("/details3virtual")
    private ResponseEntity<?> getDetails3Virtual(
            @RequestBody List<WeatherRequestDTO> requests
    ) {
        List<ResponseDTO> responses = new ArrayList<>();

        try (ExecutorService virtualExecutor = Executors.newVirtualThreadPerTaskExecutor()) {
            List<Future<ResponseDTO>> futures = new ArrayList<>();

            for (WeatherRequestDTO req : requests) {
                Callable<ResponseDTO> task = () -> weatherService.getDetails(
                        req.getLat(), req.getLon(), req.getAqi(), req.getDays(), req.getAlerts()
                );

                Callable<ResponseDTO> securedTask = new DelegatingSecurityContextCallable<>(task);
                futures.add(virtualExecutor.submit(securedTask));
            }

            for (Future<ResponseDTO> future : futures) {
                responses.add(future.get());
            }

            return new ResponseEntity<>(responses, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
