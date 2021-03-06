package com.Section.model;

public class PubRoadSetion {
    private Integer id;

    private String startLongtude;

    private String startLatitude;

    private String endLongtude;

    private String endLatitude;

    private Double miles;
    
    private String setionType;
    
    private Integer sect_avg_speed;
    
    private Integer is_asphalt_road;
    
    private Integer road_num;
    
    private Integer road_sect_limit;
    
    private Integer stand_axle_load;

    private String roadRailway_id;
    
    private String line_points;
    
    private String own_area;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartLongtude() {
        return startLongtude;
    }

    public void setStartLongtude(String startLongtude) {
        this.startLongtude = startLongtude == null ? null : startLongtude.trim();
    }

    public String getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(String startLatitude) {
        this.startLatitude = startLatitude == null ? null : startLatitude.trim();
    }

    public String getEndLongtude() {
        return endLongtude;
    }

    public void setEndLongtude(String endLongtude) {
        this.endLongtude = endLongtude == null ? null : endLongtude.trim();
    }

    public String getEndLatitude() {
        return endLatitude;
    }

    public void setEndLatitude(String endLatitude) {
        this.endLatitude = endLatitude == null ? null : endLatitude.trim();
    }

    public Double getMiles() {
        return miles;
    }

    public void setMiles(Double miles) {
        this.miles = miles;
    }

	public String getRoadRailway_id() {
		return roadRailway_id;
	}

	public void setRoadRailway_id(String roadRailway_id) {
		this.roadRailway_id = roadRailway_id;
	}

	public String getSetionType() {
		return setionType;
	}

	public void setSetionType(String setionType) {
		this.setionType = setionType;
	}

	public Integer getSect_avg_speed() {
		return sect_avg_speed;
	}

	public void setSect_avg_speed(Integer sect_avg_speed) {
		this.sect_avg_speed = sect_avg_speed;
	}

	public Integer getIs_asphalt_road() {
		return is_asphalt_road;
	}

	public void setIs_asphalt_road(Integer is_asphalt_road) {
		this.is_asphalt_road = is_asphalt_road;
	}

	public Integer getRoad_num() {
		return road_num;
	}

	public void setRoad_num(Integer road_num) {
		this.road_num = road_num;
	}

	public Integer getRoad_sect_limit() {
		return road_sect_limit;
	}

	public void setRoad_sect_limit(Integer road_sect_limit) {
		this.road_sect_limit = road_sect_limit;
	}

	public Integer getStand_axle_load() {
		return stand_axle_load;
	}

	public void setStand_axle_load(Integer stand_axle_load) {
		this.stand_axle_load = stand_axle_load;
	}

	public String getLine_points() {
		return line_points;
	}

	public void setLine_points(String line_points) {
		this.line_points = line_points;
	}

	public String getOwn_area() {
		return own_area;
	}

	public void setOwn_area(String own_area) {
		this.own_area = own_area;
	}

}