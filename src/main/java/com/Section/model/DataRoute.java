package com.Section.model;

public class DataRoute {
    private Integer id;

    private String startSite;

    private Double startLongtude;

    private Double startLatitude;

    private String endSite;

    private Double endLongtude;

    private Double endLatitude;

    private Double miles;

    private String roadSetions;
    
    private String type;

    private Integer roadAvgSpeed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartSite() {
        return startSite;
    }

    public void setStartSite(String startSite) {
        this.startSite = startSite == null ? null : startSite.trim();
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

    public String getEndSite() {
        return endSite;
    }

    public void setEndSite(String endSite) {
        this.endSite = endSite == null ? null : endSite.trim();
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getRoadAvgSpeed() {
        return roadAvgSpeed;
    }

    public void setRoadAvgSpeed(Integer roadAvgSpeed) {
        this.roadAvgSpeed = roadAvgSpeed;
    }

	public String getRoadSetions() {
		return roadSetions;
	}

	public void setRoadSetions(String roadSetions) {
		this.roadSetions = roadSetions;
	}
}