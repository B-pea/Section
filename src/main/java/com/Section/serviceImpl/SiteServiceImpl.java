package com.Section.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Section.dao.SiteDao;
import com.Section.model.Site;
import com.Section.service.SiteService;
import com.Section.util.PageParameter;



@Service("siteService")
public class SiteServiceImpl implements SiteService{

	@Autowired
	private SiteDao siteDao;
	
	@Override
	public PageParameter getPager(String orgcode,Site site, int pageNO, int pageSize) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			PageParameter page = new PageParameter();
			page.setCurrentPage(pageNO);
			page.setPageSize(pageSize);
			// store.setPage(page);

			Map<String, Object> condMap = new HashMap<String, Object>();
			condMap.put("orgcode", orgcode); 
			condMap.put("orgCode", site.getOrgCode());//dealRegister
			condMap.put("page", page); // 分页参数
			
			List<Site> list =this.siteDao.getSiteByPage(condMap);
			page.setList(list);
			return page;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void updateSite(Site site) {
		// TODO Auto-generated method stub
		siteDao.updateSite(site);
	}

	@Override
	public void addSite(Site site) {
		// TODO Auto-generated method stub
		siteDao.addSite(site);
	}

	@Override
	public void delSite(Site site) {
		// TODO Auto-generated method stub
		siteDao.delSite(site);
	}

	@Override
	public Site getSite(Site site) {
		// TODO Auto-generated method stub
		
		return siteDao.getSite(site);
	}

	@Override
	public List<Site> getSiteAll() {
		// TODO Auto-generated method stub
		return siteDao.getAllSite();
	}

	@Override
	public List<Site> getSiteByUser(Map condMap) throws Exception {
		// TODO Auto-generated method stub
		return siteDao.getSiteByUser(condMap);
	}

	

	@Override
	public List<Site> getSiteName(String orgcod) {
		// TODO Auto-generated method stub
		return siteDao.getSiteName(orgcod);
	}

	@Override
	public List<String> getSiteStatus() throws Exception {
		
		return siteDao.getSiteStatus();
	}

	@Override
	public List<Site> getAllSite() {
		// TODO Auto-generated method stub
		return siteDao.getAllSite();
	}

	@Override
	public List<Site> getSiteNoRoad() {
		// TODO Auto-generated method stub
		return siteDao.getSiteNoRoad();
	}

}
