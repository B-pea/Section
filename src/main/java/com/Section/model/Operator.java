package com.Section.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: 用户表，最新版本
 * @Author: zhangyongyou
 * @Version: 1.0
 * @Create Date Time: 2016年11月25日 上午10:28:40.
 * @Update Date Time:
 * @see
 */
public class Operator implements Serializable {
	/**
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String opName;

	private String loginName;

	private String gender;

	private String address;

	private String workLocation;

	private String idCode;

	private String opCode;

	private String opTel;

	private String opEmail;

	private String password;

	private String passwordSalt;

	private String orgCode;

	private String orgName;

	private String status;

	private Date createTime;

	private Date loginTime;

	private String lastIp;

	private Date lastTime;

	private String isonline;

	private Date lastUpdateDate;

	private String updateType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName == null ? null : opName.trim();
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName == null ? null : loginName.trim();
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender == null ? null : gender.trim();
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address == null ? null : address.trim();
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation == null ? null : workLocation.trim();
	}

	public String getIdCode() {
		return idCode;
	}

	public void setIdCode(String idCode) {
		this.idCode = idCode == null ? null : idCode.trim();
	}

	public String getOpCode() {
		return opCode;
	}

	public void setOpCode(String opCode) {
		this.opCode = opCode == null ? null : opCode.trim();
	}

	public String getOpTel() {
		return opTel;
	}

	public void setOpTel(String opTel) {
		this.opTel = opTel == null ? null : opTel.trim();
	}

	public String getOpEmail() {
		return opEmail;
	}

	public void setOpEmail(String opEmail) {
		this.opEmail = opEmail == null ? null : opEmail.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getPasswordSalt() {
		return passwordSalt;
	}

	public void setPasswordSalt(String passwordSalt) {
		this.passwordSalt = passwordSalt == null ? null : passwordSalt.trim();
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode == null ? null : orgCode.trim();
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName == null ? null : orgName.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getLastIp() {
		return lastIp;
	}

	public void setLastIp(String lastIp) {
		this.lastIp = lastIp == null ? null : lastIp.trim();
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public String getIsonline() {
		return isonline;
	}

	public void setIsonline(String isonline) {
		this.isonline = isonline == null ? null : isonline.trim();
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

	public String getCredentialsSalt() {
		return this.opCode + this.passwordSalt;
	}

	@Override
	public String toString() {
		return "id=" + id + ", opName=" + opName + ", gender=" + gender + ", address=" + address + ", workLocation="
				+ workLocation + ", idCode=" + idCode + ", opCode=" + opCode + ", opTel=" + opTel + ", opEmail="
				+ opEmail + ", orgCode=" + orgCode + ", orgName=" + orgName;
	}
}