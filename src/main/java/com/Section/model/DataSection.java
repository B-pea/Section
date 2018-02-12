package com.Section.model;

public class DataSection {
    private Integer id;

    private Double startLongtude;

    private Double startLatitude;

    private Double endLongtude;

    private Double endLatitude;

    private Double miles;

    private String setionType;

    private Integer sectAvgSpeed;

    private Integer isAsphaltRoad;

    private Integer roadNum;

    private Integer roadSectLimit;

    private Integer standAxleLoad;

    private String roadrailwayId;

    private String ownArea;

    private String linePoints;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getStartLongtude() {
        return startLongtude;
    }

    public void setStartLongtude(Double startLongtude) {
        this.startLongtude = startLongtude;
    }

    public Double getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(Double startLatitude) {
        this.startLatitude = startLatitude;
    }

    public Double getEndLongtude() {
        return endLongtude;
    }

    public void setEndLongtude(Double endLongtude) {
        this.endLongtude = endLongtude;
    }

    public Double getEndLatitude() {
        return endLatitude;
    }

    public void setEndLatitude(Double endLatitude) {
        this.endLatitude = endLatitude;
    }

    public Double getMiles() {
        return miles;
    }

    public void setMiles(Double miles) {
        this.miles = miles;
    }

    public String getSetionType() {
        return setionType;
    }

    public void setSetionType(String setionType) {
        this.setionType = setionType == null ? null : setionType.trim();
    }

    public Integer getSectAvgSpeed() {
        return sectAvgSpeed;
    }

    public void setSectAvgSpeed(Integer sectAvgSpeed) {
        this.sectAvgSpeed = sectAvgSpeed;
    }

    public Integer getIsAsphaltRoad() {
        return isAsphaltRoad;
    }

    public void setIsAsphaltRoad(Integer isAsphaltRoad) {
        this.isAsphaltRoad = isAsphaltRoad;
    }

    public Integer getRoadNum() {
        return roadNum;
    }

    public void setRoadNum(Integer roadNum) {
        this.roadNum = roadNum;
    }

    public Integer getRoadSectLimit() {
        return roadSectLimit;
    }

    public void setRoadSectLimit(Integer roadSectLimit) {
        this.roadSectLimit = roadSectLimit;
    }

    public Integer getStandAxleLoad() {
        return standAxleLoad;
    }

    public void setStandAxleLoad(Integer standAxleLoad) {
        this.standAxleLoad = standAxleLoad;
    }

    public String getRoadrailwayId() {
        return roadrailwayId;
    }

    public void setRoadrailwayId(String roadrailwayId) {
        this.roadrailwayId = roadrailwayId == null ? null : roadrailwayId.trim();
    }

    public String getOwnArea() {
        return ownArea;
    }

    public void setOwnArea(String ownArea) {
        this.ownArea = ownArea == null ? null : ownArea.trim();
    }

    public String getLinePoints() {
        return linePoints;
    }

    public void setLinePoints(String linePoints) {
        this.linePoints = linePoints == null ? null : linePoints.trim();
    }
}