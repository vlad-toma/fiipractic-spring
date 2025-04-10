package com.fiipractic.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseDTO {

    private LocationDTO location;
    private CurrentDTO current;
    private ForecastDTO forecast;
    private AlertsDTO alerts;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class LocationDTO {
        private String name;
        private String region;
        private String country;
        private String tz_id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getTz_id() {
            return tz_id;
        }

        public void setTz_id(String tz_id) {
            this.tz_id = tz_id;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CurrentDTO {
        private double temp_c;
        private double wind_kph;
        private String wind_dir;
        private double precip_mm;
        private AirQualityDTO air_quality;

        public double getTemp_c() {
            return temp_c;
        }

        public void setTemp_c(double temp_c) {
            this.temp_c = temp_c;
        }

        public double getWind_kph() {
            return wind_kph;
        }

        public void setWind_kph(double wind_kph) {
            this.wind_kph = wind_kph;
        }

        public String getWind_dir() {
            return wind_dir;
        }

        public void setWind_dir(String wind_dir) {
            this.wind_dir = wind_dir;
        }

        public double getPrecip_mm() {
            return precip_mm;
        }

        public void setPrecip_mm(double precip_mm) {
            this.precip_mm = precip_mm;
        }

        public AirQualityDTO getAir_quality() {
            return air_quality;
        }

        public void setAir_quality(AirQualityDTO air_quality) {
            this.air_quality = air_quality;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AirQualityDTO {
        private double co;
        private int gb_defra_index;

        public double getCo() {
            return co;
        }

        public void setCo(double co) {
            this.co = co;
        }

        public int getGb_defra_index() {
            return gb_defra_index;
        }

        public void setGb_defra_index(int gb_defra_index) {
            this.gb_defra_index = gb_defra_index;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ForecastDTO {
        private List<ForecastDayDTO> forecastday = new ArrayList<>();

        public List<ForecastDayDTO> getForecastday() {
            return forecastday;
        }

        public void setForecastday(List<ForecastDayDTO> forecastday) {
            this.forecastday = forecastday;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ForecastDayDTO {
        private String date;
        private DayDTO day;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public DayDTO getDay() {
            return day;
        }

        public void setDay(DayDTO day) {
            this.day = day;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DayDTO {
        private double maxtemp_c;
        private double mintemp_c;
        private double maxwind_kph;
        private double totalprecip_mm;

        public double getMaxtemp_c() {
            return maxtemp_c;
        }

        public void setMaxtemp_c(double maxtemp_c) {
            this.maxtemp_c = maxtemp_c;
        }

        public double getMintemp_c() {
            return mintemp_c;
        }

        public void setMintemp_c(double mintemp_c) {
            this.mintemp_c = mintemp_c;
        }

        public double getMaxwind_kph() {
            return maxwind_kph;
        }

        public void setMaxwind_kph(double maxwind_kph) {
            this.maxwind_kph = maxwind_kph;
        }

        public double getTotalprecip_mm() {
            return totalprecip_mm;
        }

        public void setTotalprecip_mm(double totalprecip_mm) {
            this.totalprecip_mm = totalprecip_mm;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AlertsDTO {
        private List<AlertDTO> alert = new ArrayList<>();

        public List<AlertDTO> getAlert() {
            return alert;
        }

        public void setAlert(List<AlertDTO> alert) {
            this.alert = alert;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AlertDTO {
        private String severity;
        private String certainty;
        private String event;
        private String effective;
        private String expires;

        public String getSeverity() {
            return severity;
        }

        public void setSeverity(String severity) {
            this.severity = severity;
        }

        public String getCertainty() {
            return certainty;
        }

        public void setCertainty(String certainty) {
            this.certainty = certainty;
        }

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

        public String getEffective() {
            return effective;
        }

        public void setEffective(String effective) {
            this.effective = effective;
        }

        public String getExpires() {
            return expires;
        }

        public void setExpires(String expires) {
            this.expires = expires;
        }
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public CurrentDTO getCurrent() {
        return current;
    }

    public void setCurrent(CurrentDTO current) {
        this.current = current;
    }

    public ForecastDTO getForecast() {
        return forecast;
    }

    public void setForecast(ForecastDTO forecast) {
        this.forecast = forecast;
    }

    public AlertsDTO getAlerts() {
        return alerts;
    }

    public void setAlerts(AlertsDTO alerts) {
        this.alerts = alerts;
    }

    public String toString() {
        String rez = "";
        rez += "Location:\n";
        rez += "    Name: " + location.getName() + "\n";
        rez += "    Region: " + location.getRegion() + "\n";
        rez += "    Country: " + location.getCountry() + "\n";
        rez += "    TZ ID: " + location.getTz_id() + "\n";
        rez += "Current:\n";
        rez += "    Temperature C: " + String.valueOf(current.getTemp_c()) + "\n";
        rez += "    Wind KPH: " + String.valueOf(current.getWind_kph()) + "\n";
        rez += "    Wind Dir: " + current.getWind_dir() + "\n";
        rez += "    Precip mm: " + String.valueOf(current.getPrecip_mm()) + "\n";
        if (this.current.getAir_quality() != null) {
            rez += "    Air Quality:\n";
            rez += "        CO: " + String.valueOf(this.current.getAir_quality().getCo()) + "\n";
            rez += "        Gb_defra_index: " + String.valueOf(this.current.getAir_quality().getGb_defra_index()) + "\n";
        }
        rez += "Forecast:\n";
        for (ForecastDayDTO f: this.forecast.getForecastday()) {
            rez += "        Forecast Day:\n";
            rez += "            Date: " + f.getDate() + "\n";
            rez += "            Min Temp: " + String.valueOf(f.getDay().getMintemp_c()) + "\n";
            rez += "            Max Temp: " + String.valueOf(f.getDay().getMaxtemp_c()) + "\n";
            rez += "            Max Wind KPH: " + String.valueOf(f.getDay().getMaxwind_kph()) + "\n";
            rez += "            Total Precip mm: " + String.valueOf(f.getDay().getTotalprecip_mm()) + "\n";
        }
        if (!this.alerts.getAlert().isEmpty()) {
            rez += "Alerts:\n";
            for (AlertDTO a: this.alerts.getAlert()) {
                rez += "    Alert:\n";
                rez += "        Severity: " + a.getSeverity() + "\n";
                rez += "        Certainty: " + a.getCertainty() + "\n";
                rez += "        Event: " + a.getEvent() + "\n";
                rez += "        Effective: " + a.getEffective() + "\n";
                rez += "        Expires: " + a.getExpires() + "\n";
            }
        }
        return rez;
    }
}

