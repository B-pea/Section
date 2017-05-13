package com.Section.model;

import java.io.Serializable;
import java.util.Date;

public class RoadRailway implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String railwayName;

    private String lastRailway;

    private String nextRailway;

    private String startLocation;

    private String endLocation;

    private String miles;
    
    private String url;

    private String descriribe;

    private String status;

    private Date lastUpdateDate;

    private String updateType;
    
    private String line_point;
    
    private String line_point_back;

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRailwayName() {
        return railwayName;
    }

    public void setRailwayName(String railwayName) {
        this.railwayName = railwayName == null ? null : railwayName.trim();
    }

    public String getLastRailway() {
        return lastRailway;
    }

    public void setLastRailway(String lastRailway) {
        this.lastRailway = lastRailway == null ? null : lastRailway.trim();
    }

    public String getNextRailway() {
        return nextRailway;
    }

    public void setNextRailway(String nextRailway) {
        this.nextRailway = nextRailway == null ? null : nextRailway.trim();
    }

    public String getStartLocation() {
		return startLocation;
	}

	public void setStartLocation(String startLocation) {
		this.startLocation = startLocation;
	}

	public String getEndLocation() {
		return endLocation;
	}

	public void setEndLocation(String endLocation) {
		this.endLocation = endLocation;
	}

	public String getMiles() {
		return miles;
	}

	public void setMiles(String miles) {
		this.miles = miles;
	}

	public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getDescriribe() {
        return descriribe;
    }

    public void setDescriribe(String descriribe) {
        this.descriribe = descriribe == null ? null : descriribe.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType == null ? null : updateType.trim();
    }

	public String getLine_point() {
		return line_point;
	}

	public void setLine_point(String line_point) {
		this.line_point = line_point;
	}

	public String getLine_point_back() {
		return line_point_back;
	}

	public void setLine_point_back(String line_point_back) {
		this.line_point_back = line_point_back;
	}
}