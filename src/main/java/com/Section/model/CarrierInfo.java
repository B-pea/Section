package com.Section.model;


public class CarrierInfo {
    private Integer vehrierId;

    private String name;

    private String orgCode;
    
    private String orgName;

    private Float longitude;

    private Float latitude;

    private String address;

    private String zipcode;

    private String phonenum;

    private String legalRepName;

    private String legalRepIdvehd;

    private String legalRepPhonenum;

    private String tel;

    private String email;

    private String qq;

    private String wechat;

    private String status;

    private String lastUpdateDate;

    private String updateType;

    public Integer getVehrierId() {
        return vehrierId;
    }

    public void setVehrierId(Integer vehrierId) {
        this.vehrierId = vehrierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? "" : name.trim();
    }

    public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode == null ? "" : orgCode.trim();
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? "" : address.trim();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode == null ? "" : zipcode.trim();
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum == null ? "" : phonenum.trim();
    }

    public String getLegalRepName() {
        return legalRepName;
    }

    public void setLegalRepName(String legalRepName) {
        this.legalRepName = legalRepName == null ? "" : legalRepName.trim();
    }

    public String getLegalRepIdvehd() {
        return legalRepIdvehd;
    }

    public void setLegalRepIdvehd(String legalRepIdvehd) {
        this.legalRepIdvehd = legalRepIdvehd == null ? "" : legalRepIdvehd.trim();
    }

    public String getLegalRepPhonenum() {
        return legalRepPhonenum;
    }

    public void setLegalRepPhonenum(String legalRepPhonenum) {
        this.legalRepPhonenum = legalRepPhonenum == null ? "" : legalRepPhonenum.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? "" : tel.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? "" : email.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? "" : qq.trim();
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat == null ? "" : wechat.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? "" : status.trim();
    }

    public String getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(String lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getUpdateType() {
        return updateType;
    }

    public void setUpdateType(String updateType) {
        this.updateType = updateType == null ? "" : updateType.trim();
    }
}