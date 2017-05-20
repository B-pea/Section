package com.Section.model;

/**
 * @Description:组织机构的一维表
 * @Author: zhangyongyou
 * @Version: 1.0
 * @Create Date Time: Dec 28, 2016 4:28:07 PM
 * @Update Date Time: Dec 28, 2016 4:28:07 PM
 * @see
 */
public class OneOrganization {
	private String porgcode;
	private String corgcode;
	private String orgname;
	private String category;
	private String status;

	public String getPorgcode() {
		return porgcode;
	}

	public void setPorgcode(String porgcode) {
		this.porgcode = porgcode;
	}

	public String getCorgcode() {
		return corgcode;
	}

	public void setCorgcode(String corgcode) {
		this.corgcode = corgcode;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OneOrganization() {
		super();
	}
	public OneOrganization(String porgcode, String corgcode, String orgname, String category, String status) {
		super();
		this.porgcode = porgcode;
		this.corgcode = corgcode;
		this.orgname = orgname;
		this.category = category;
		this.status = status;
	}
}