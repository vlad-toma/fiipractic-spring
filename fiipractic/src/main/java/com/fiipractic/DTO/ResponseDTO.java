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
        private double lat;
        private double lon;
        private String localtime;

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

        public String getLocaltime() {
            return localtime;
        }

        public void setLocaltime(String localtime) {
            this.localtime = localtime;
        }

        public double getLat() {
            return lat;
        }

        public void setLat(double lat) {
            this.lat = lat;
        }

        public double getLon() {
            return lon;
        }

        public void setLon(double lon) {
            this.lon = lon;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class CurrentDTO {
        private String last_updated;
        private double temp_c;
        private ConditionDTO condition;
        private double wind_kph;
        private String wind_dir;
        private double precip_mm;
        private double humidity;
        private double aqco;

        public ConditionDTO getCondition() {
            return condition;
        }

        public void setCondition(ConditionDTO condition) {
            this.condition = condition;
        }

        public String getLast_updated() {
            return last_updated;
        }

        public void setLast_updated(String last_updated) {
            this.last_updated = last_updated;
        }

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

        public double getHumidity() {
            return humidity;
        }

        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }

        public double getAqco() {
            return aqco;
        }

        public void setAqco(double aqco) {
            this.aqco = aqco;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AirQualityDTO {
        private double co;

        public double getCo() {
            return co;
        }

        public void setCo(double co) {
            this.co = co;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ConditionDTO {
        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
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
        private double min_day;
        private double min_night;
        private double max_day;
        private double max_night;
        private double avg_day;
        private double avg_night;

        public double getMin_day() {
            return min_day;
        }

        public void setMin_day(double min_day) {
            this.min_day = min_day;
        }

        public double getMin_night() {
            return min_night;
        }

        public void setMin_night(double min_night) {
            this.min_night = min_night;
        }

        public double getMax_day() {
            return max_day;
        }

        public void setMax_day(double max_day) {
            this.max_day = max_day;
        }

        public double getMax_night() {
            return max_night;
        }

        public void setMax_night(double max_night) {
            this.max_night = max_night;
        }

        public double getAvg_day() {
            return avg_day;
        }

        public void setAvg_day(double avg_day) {
            this.avg_day = avg_day;
        }

        public double getAvg_night() {
            return avg_night;
        }

        public void setAvg_night(double avg_night) {
            this.avg_night = avg_night;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if (location != null) {
            sb.append("  Location:\n");
            sb.append("  - Name: ").append(location.getName()).append("\n");
            sb.append("  - Region: ").append(location.getRegion()).append("\n");
            sb.append("  - Country: ").append(location.getCountry()).append("\n");
            sb.append("  - Latitude: ").append(location.getLat()).append("\n");
            sb.append("  - Longitude: ").append(location.getLon()).append("\n");
            sb.append("  - Local Time: ").append(location.getLocaltime()).append("\n\n");
        }

        if (current != null) {
            sb.append("   Current Weather:\n");
            sb.append("  - Last Updated: ").append(current.getLast_updated()).append("\n");
            sb.append("  - Temperature (C): ").append(current.getTemp_c()).append("\n");
            sb.append("  - Wind Speed (kph): ").append(current.getWind_kph()).append("\n");
            sb.append("  - Wind Direction: ").append(current.getWind_dir()).append("\n");
            sb.append("  - Precipitation (mm): ").append(current.getPrecip_mm()).append("\n");
            sb.append("  - Humidity (%): ").append(current.getHumidity()).append("\n");
            if (current.getCondition() != null) {
                sb.append("  - Condition: ").append(current.getCondition().getText()).append("\n");
            }
            sb.append("\n");
        }

        if (forecast != null && forecast.getForecastday() != null && !forecast.getForecastday().isEmpty()) {
            sb.append("  Forecast:\n");
            for (ForecastDayDTO day : forecast.getForecastday()) {
                sb.append("  - Date: ").append(day.getDate()).append("\n");
                if (day.getDay() != null) {
                    sb.append("    * Min Day Temp: ").append(day.getDay().getMin_day()).append("°C\n");
                    sb.append("    * Min Night Temp: ").append(day.getDay().getMin_night()).append("°C\n");
                    sb.append("    * Max Day Temp: ").append(day.getDay().getMax_day()).append("°C\n");
                    sb.append("    * Max Night Temp: ").append(day.getDay().getMax_night()).append("°C\n");
                    sb.append("    * Avg Day Temp: ").append(day.getDay().getAvg_day()).append("°C\n");
                    sb.append("    * Avg Night Temp: ").append(day.getDay().getAvg_night()).append("°C\n");
                }
                sb.append("\n");
            }
        }

        if (alerts != null && alerts.getAlert() != null && !alerts.getAlert().isEmpty()) {
            sb.append("  Alerts:\n");
            for (AlertDTO alert : alerts.getAlert()) {
                sb.append("  - Event: ").append(alert.getEvent()).append("\n");
                sb.append("    * Severity: ").append(alert.getSeverity()).append("\n");
                sb.append("    * Certainty: ").append(alert.getCertainty()).append("\n");
                sb.append("    * Effective: ").append(alert.getEffective()).append("\n");
                sb.append("    * Expires: ").append(alert.getExpires()).append("\n");
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}

