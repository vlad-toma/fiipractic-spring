package com.fiipractic.DTO;

public class WeatherRequestDTO {
    private String lat = "12";
    private String lon = "12";
    private String aqi = "no";
    private String days = "1";
    private String alerts = "no";

    public WeatherRequestDTO() {
    }
    public WeatherRequestDTO(String lat, String lon, String aqi, String days, String alerts) {
        this.lat = lat;
        this.lon = lon;
        this.aqi = aqi;
        this.days = days;
        this.alerts = alerts;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getAqi() {
        return aqi;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getAlerts() {
        return alerts;
    }

    public void setAlerts(String alerts) {
        this.alerts = alerts;
    }
}
