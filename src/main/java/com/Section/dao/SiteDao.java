package com.Section.dao;

import java.util.List;
import java.util.Map;

import com.Section.model.Site;

/**
 * @ClassName: SiteDao 
 * @Description: 站点信息登记dao
 * @author jianglibo 
 * @date 2016年11月23日 下午1:39:25
 */
public interface SiteDao {
	
	void addSite(Site site);
	
	void updateSite(Site site);
	
	void delSite(Site site);
	
	List<Site> getSiteByPage(Map condMap);
	//String getPager(Map condMap, int pageIndex, int pageSize) throws Exception;

	public Site getSite(Site site);
	
	List<Site> getAllSite();
	
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
	 */
	List<String> getSiteStatus();

	List<Site> getSiteNoRoad();

	List<Site> isFullRoute(String siteCode);

}
