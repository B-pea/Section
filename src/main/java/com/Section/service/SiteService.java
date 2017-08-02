package com.Section.service;


import java.util.List;
import java.util.Map;

import com.Section.model.Site;
import com.Section.util.PageParameter;

public interface SiteService {
	
	void addSite(Site site);
	
	void updateSite(Site site);

	void delSite(Site site);
	
	public Site getSite(Site site);
	
	PageParameter getPager(String orgcode,Site site, int pageIndex, int pageSize) throws Exception;
	
	List<Site> getSiteAll();
	
	List<Site> getSiteName(String orgcod);
	
	/**
	 * 
		* 
		* @Title: getSiteByUser 
		* @Description: TODO 根据权限查询站点
		* @param @param condMap
		* @param @return
		* @param @throws Exception    设定文件 
		* @return String    返回类型 
		* @throws
	 */
	List<Site> getSiteByUser(Map condMap) throws Exception;
	
	/**
	 * 查询状态为0的站点名称列表
	 * @return
	 * @throws Exception
	 */
	List<String> getSiteStatus() throws Exception;

	List<Site> getAllSite();

	List<Site> getSiteNoRoad();

	List<Site> getAllSitePolicy();

}
