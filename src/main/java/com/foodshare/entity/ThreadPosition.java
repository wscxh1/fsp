package com.foodshare.entity;

import java.io.Serializable;

public class ThreadPosition implements Serializable {
    private Long threadId;

    private String location;

    private Double longitude;

    private Double latitude;

    private Byte hasCoordinate;

    private Integer shopId;

    public Long getThreadId() {
        return threadId;
    }

    public void setThreadId(Long threadId) {
        this.threadId = threadId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Byte getHasCoordinate() {
        return hasCoordinate;
    }

    public void setHasCoordinate(Byte hasCoordinate) {
        this.hasCoordinate = hasCoordinate;
    }

    public Integer getShopId() {
        return shopId;
    }

    public void setShopId(Integer shopId) {
        this.shopId = shopId;
    }
}