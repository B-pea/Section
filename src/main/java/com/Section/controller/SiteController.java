package com.Section.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.Section.model.Site;
import com.Section.service.OrganizationService;
import com.Section.service.SiteService;
import com.Section.util.Constants;
import com.Section.util.PageParameter;

/**
 * @ClassName: SiteController 
 * @Description: 站点信息登记 
 * @author jianglibo 
 * @date 2016年11月23日 下午1:36:25
 */
@Controller
@RequestMapping("/site")
public class SiteController {

	private static Logger logger = Logger.getLogger(SiteController.class);
	@Autowired
	private SiteService siteService;
	@Autowired
	private OrganizationService organizationService;
	
	@RequestMapping(value = "/getPageSite", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getPageSite(Integer page,Integer rows,Site site,HttpServletRequest request) throws Exception, IOException{
		
		try {  
        	//设置当前页
            int intPage=page==null||page<=0?1:page;
            //设置每页显示的数量
            int intPageSize=rows==null||rows<=0?10:rows;
            
    		Map<String,Object> reMap= new HashMap<String,Object>();
    		
    		Session session = SecurityUtils.getSubject().getSession();
			String orgcode = (String) session.getAttribute(Constants.ORGCODE);
			
    		PageParameter pagination = this.siteService.getPager(orgcode, site,intPage, intPageSize);
    		
    		reMap.put("page", pagination.getCurrentPage());//当前页数
    		reMap.put("total", pagination.getTotalPage());//总页数
    		reMap.put("rows", pagination.getList());//列表信息
    		reMap.put("records", pagination.getTotalCount() );// 当前页的数据
    		
            return reMap;
        } catch (Exception e) {  
            e.printStackTrace(); 
            logger.debug("错误信息："+e);
        }  
        return null; 
	}
	
	@RequestMapping(value="/updateSite")
	@ResponseBody
	public void updateSite(Site site,HttpServletRequest request,HttpServletResponse response){
		site.setUpdateType('M');
		site.setLastUpdateDate(new Date());
		String reply="";
		try {
			siteService.updateSite(site);
			reply = "{\"result\":true}";
			response.getWriter().write(reply);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			reply="错误信息" + e.getMessage();
		}finally {
			System.out.println(reply);
		}
	}

	@RequestMapping(value="/addSite")
	@ResponseBody
	public void addSite(Site site,HttpServletRequest request,HttpServletResponse response){
		site.setUpdateType('A');
		site.setLastUpdateDate(new Date());
		String reply="";
		try {
			siteService.addSite(site);
			reply = "{\"result\":true}";
			response.getWriter().write(reply);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			reply="错误信息" + e.getMessage();
		}finally {
			System.out.println(reply);
		}
	}
	@RequestMapping(value="/delSite")
	@ResponseBody
	public void delSite(Site site,HttpServletRequest request,HttpServletResponse response){
		site.setUpdateType('D');
		site.setLastUpdateDate(new Date());
		String reply="";
		try {
			siteService.delSite(site);
			reply = "{\"result\":true}";
			response.getWriter().write(reply);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			reply="错误信息" + e.getMessage();
		}finally {
			System.out.println(reply);
		}
	}
	/**
	 * 获取机构下站点列表
	 */
	@RequestMapping(value = "/getAllSite", method = RequestMethod.POST)
	@ResponseBody
	public String getAllSite(HttpServletRequest request,String name) throws Exception, IOException{
		Map<Object,Object> condMap = new HashMap<>();
		condMap.put("orgcode", name);
		List<Site> siteList = siteService.getSiteByUser(condMap);
		Gson gson = new Gson();
		return gson.toJson(siteList);
	}
	/**
	* @Title: siteList 
	* @Description: TODO站点列表
	* @param @return
	* @return String    返回类型 
	* @throws
    */
	@RequestMapping(value = "/siteList", method = RequestMethod.GET)
	public String siteList() {
		return "jsp/baseInfo/site";
	}
	
	@RequestMapping(value = "/getSiteNoRoad", method = RequestMethod.POST)
	@ResponseBody
	public String getSiteNoRoad() throws Exception, IOException{
		Gson gson = new Gson();
		List<Site> siteList = siteService.getSiteNoRoad();
		return gson.toJson(siteList);
	}
	
}
