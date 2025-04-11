package com.fiipractic.DTO;

public class RequestHistoryDTO {
    private Long id;

    private String lat;

    private String lon;

    private Boolean q;

    private Boolean aqi;

    private Boolean alerts;

    private Integer days;

    private String response;

    private String username;

    public RequestHistoryDTO(Long id, String lat, String lon, Boolean q, Boolean aqi, Boolean alerts, Integer days, String response, String username) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.q = q;
        this.aqi = aqi;
        this.alerts = alerts;
        this.days = days;
        this.response = response;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean getQ() {
        return q;
    }

    public void setQ(Boolean q) {
        this.q = q;
    }

    public Boolean getAqi() {
        return aqi;
    }

    public void setAqi(Boolean aqi) {
        this.aqi = aqi;
    }

    public Boolean getAlerts() {
        return alerts;
    }

    public void setAlerts(Boolean alerts) {
        this.alerts = alerts;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
