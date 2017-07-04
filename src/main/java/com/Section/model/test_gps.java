package com.Section.model;

import java.util.Date;

public class test_gps {
    private Integer id;

    private String vehPlate;

    private Date insertTime;

    private String wgsLng;

    private String wgsLat;

    private Double speed;

    private String direction;

    private String carStatus;

    private String bdLng;

    private String bdLat;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVehPlate() {
        return vehPlate;
    }

    public void setVehPlate(String vehPlate) {
        this.vehPlate = vehPlate == null ? null : vehPlate.trim();
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getWgsLng() {
        return wgsLng;
    }

    public void setWgsLng(String wgsLng) {
        this.wgsLng = wgsLng == null ? null : wgsLng.trim();
    }

    public String getWgsLat() {
        return wgsLat;
    }

    public void setWgsLat(String wgsLat) {
        this.wgsLat = wgsLat == null ? null : wgsLat.trim();
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction == null ? null : direction.trim();
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus == null ? null : carStatus.trim();
    }

    public String getBdLng() {
        return bdLng;
    }

    public void setBdLng(String bdLng) {
        this.bdLng = bdLng == null ? null : bdLng.trim();
    }

    public String getBdLat() {
        return bdLat;
    }

    public void setBdLat(String bdLat) {
        this.bdLat = bdLat == null ? null : bdLat.trim();
    }
}