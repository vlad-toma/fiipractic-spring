package com.fiipractic.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "request_history")
public class RequestHistory {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String lat;

    @Column(nullable = false)
    private String lon;

    @Column
    private Boolean q;

    @Column
    private Boolean aqi;

    @Column
    private Boolean alerts;

    @Column
    private Integer days;

    @Column(nullable = false, length = 2047)
    private String response;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public RequestHistory() {

    }

    public RequestHistory(Long id, String lat, String lon, Boolean q, Boolean aqi, Boolean alerts, Integer days, String response, User user) {
        this.id = id;
        this.lat = lat;
        this.lon = lon;
        this.q = q;
        this.aqi = aqi;
        this.alerts = alerts;
        this.days = days;
        this.response = response;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
