package com.fiipractic.Services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiipractic.DTO.ResponseDTO;
import com.fiipractic.DTO.ResponseDTO.*;
import com.fiipractic.Entity.User;
import com.fiipractic.Entity.UserProfile;
import com.fiipractic.Util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

@Service
public class WeatherService {
    @Autowired
    private RequestHistoryService requestHistoryService;

    @Autowired
    private SecurityUtil securityUtil;

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private EmailService emailService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public ResponseDTO callLink(String Url) throws IOException {
        URL url = new URL(Url);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();

        JsonNode rootNode = objectMapper.readTree(content.toString());

        ResponseDTO responseDTO = new ResponseDTO();

        JsonNode locationNode = rootNode.path("location");
        LocationDTO location = objectMapper.treeToValue(locationNode, LocationDTO.class);
        responseDTO.setLocation(location);

        JsonNode currentNode = rootNode.path("current");
        CurrentDTO current = objectMapper.treeToValue(currentNode, CurrentDTO.class);
        if (currentNode.has("air_quality")) {
            AirQualityDTO airQuality = objectMapper.treeToValue(currentNode.path("air_quality"), AirQualityDTO.class);
            current.setAir_quality(airQuality);
        }
        responseDTO.setCurrent(current);

        JsonNode forecastNode = rootNode.path("forecast").path("forecastday");
        ForecastDTO forecastDTO = new ForecastDTO();
        if (forecastNode.isArray()) {
            for (JsonNode dayNode : forecastNode) {
                ForecastDayDTO dayDTO = objectMapper.treeToValue(dayNode, ForecastDayDTO.class);
                forecastDTO.getForecastday().add(dayDTO);
            }
        }
        responseDTO.setForecast(forecastDTO);

        JsonNode alertsNode = rootNode.path("alerts").path("alert");
        AlertsDTO alertsDTO = new AlertsDTO();
        if (alertsNode.isArray()) {
            for (JsonNode alertNode : alertsNode) {
                AlertDTO alert = objectMapper.treeToValue(alertNode, AlertDTO.class);
                alertsDTO.getAlert().add(alert);
            }
        }
        responseDTO.setAlerts(alertsDTO);

        return responseDTO;
    }

    public ResponseDTO getDetails(String lat, String lon, String aqi, String days, String alerts) throws Exception {
        User currentUser = securityUtil.getCurrentUser();
        Optional<UserProfile> userProfile = userProfileService.getUserProfileByUsername(currentUser.getId());
        if (userProfile.isEmpty()) {
            throw new Exception("User not found");
        }

        ResponseDTO rez;
        try {
            rez = callLink("http://api.weatherapi.com/v1/forecast.json?key="+userProfile.get().getWeatherApiKey()+
                    "&q="+lat+","+lon+"&days="+days+"&aqi="+aqi+"&alerts="+alerts);

        } catch(Exception e) {
            throw new Exception(e.getMessage());
        }
        /*requestHistoryService.createRequestHistory(new RequestHistory(
                null, lat, lon, true, aqi.equals("yes"), alerts.equals("yes"),
                Integer.parseInt(days), rez.toString(),
                currentUser
        ));*/

        if (Boolean.TRUE.equals(userProfile.get().getEmailNotification())) {
            emailService.sendEmail(
                    userProfile.get().getEmail(),
                    "Weather API Response",
                    rez.toString()
            );
        }

        return rez;
    }
}
