package com.Section.model;

public class walker {
    private Integer id;

    private String walkerName;

    private String walkerPoints;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWalkerName() {
        return walkerName;
    }

    public void setWalkerName(String walkerName) {
        this.walkerName = walkerName == null ? null : walkerName.trim();
    }

    public String getWalkerPoints() {
        return walkerPoints;
    }

    public void setWalkerPoints(String walkerPoints) {
        this.walkerPoints = walkerPoints == null ? null : walkerPoints.trim();
    }
}