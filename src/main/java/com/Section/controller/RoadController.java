package com.Section.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.Section.model.Road;
import com.Section.service.RoadService;
import com.Section.util.BigDataInterFacesClient;
import com.Section.util.Common;
import com.Section.util.PageParameter;
import com.Section.util.PropUtil;


/** 
* @ClassName: RoadController 
* @Description: 公路信息登记 
* @author shijin 
* @date 2016年11月17日 下午1:55:29 
*  
*/
@Controller
@RequestMapping("/road")
public class RoadController {
	
	private static Logger logger = Logger.getLogger(RoadController.class);
	
	@Autowired
	private RoadService roadService;
	
	
	Gson mapper = new Gson();
	
	/**
	 * 
		 * Description: 新增公路
		 * @param road
		 * @param request
		 * @return
		 * @return String
		 * @throws Exception
		 * @see Note:
	 */
	@RequestMapping(value="/insertRoad")
	@ResponseBody
	public int inserRoad(Road road,HttpServletRequest request,HttpServletResponse response){
		try {
			road.setSetTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			road.setLastUpdateDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			roadService.addRoad(road);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("错误信息" + e.getMessage());
			return 0;
		} finally {			
			System.out.println("公路信息登记 :inserRoad");
		}  
	}
	
	@RequestMapping(value = "/getAllUser", method = RequestMethod.POST)
	@ResponseBody
	public String getAddInfoAll(HttpServletRequest request,String name) throws Exception, IOException{
			
		    List<Road> list = roadService.getAllRoad(name);
//			System.out.println(list);
//			request.setAttribute("list", list);
		
			return mapper.toJson(list);
		
	}
	/**
	 * 分页查询
	 * @param request
	 * @return
	 * @throws Exception
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	/*@RequestMapping(value = "/getPageUser", method = RequestMethod.POST)
	@ResponseBody
	public String getAllByPage(String pageSize,String userName,HttpServletRequest request) throws Exception, JsonMappingException, IOException{
		if(pageSize==null) pageSize="1";
		int pageNum = Integer.parseInt(pageSize.trim());
		PageBean pager = baseService.getAllByPage(pageNum,userName);
		
		
		return mapper.writeValueAsString(pager);
		
	}*/
	
	/**
	 * 
		 * Description: 查询并显示公路信息
		 * @param page
		 * @param rows
		 * @param name
		 * @param request
		 * @return
		 * @throws Exception
		 * @throws IOException
		 * @return String
		 * @throws Exception
		 * @see Note:
	 */
	@RequestMapping(value = "/getPageRoad", method = RequestMethod.POST)
	@ResponseBody
	public 	Map<String,Object> getPageRoad(HttpServletRequest request,HttpServletResponse response,Road road,Integer page,Integer rows) throws Exception{
		//设置当前页
        int intPage=page==null||page<=0?1:page;
        //设置每页显示的数量
        int intPageSize=rows==null||rows<=0?10:rows;
        
        Map<String,Object> reMap= new HashMap<String,Object>();
        
        PageParameter pagination = this.roadService.getListByPage(road,intPage, intPageSize);
        
        reMap.put("page", pagination.getCurrentPage());//当前页数
		reMap.put("total", pagination.getTotalPage());//总页数
		reMap.put("rows", pagination.getList());//列表信息
		reMap.put("records", pagination.getTotalCount() );// 当前页的数据
		logger.error("获取公路信息列表，方法名称为：getPageRoad");
		return reMap;
	}
	
	/**
	 * 
		 * Description: 通过id查看公路信息明细
		 * @param id
		 * @param request
		 * @return
		 * @return String
		 * @throws Exception
		 * @see Note:
	 */
	@RequestMapping("findDetail")
	@ResponseBody
	public String findDetail(String id,HttpServletRequest request){
		try {			
			Road r = roadService.findDetail(id);
			return mapper.toJson(r);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("InfoMessage", "错误信息" + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 
		 * Description: 删除公路信息
		 * @param road
		 * @param request
		 * @return
		 * @return String
		 * @throws Exception
		 * @see Note:
	 */
	@RequestMapping("deleteRoad")
	@ResponseBody
	public int delRoad(Road road,HttpServletRequest request,HttpServletResponse response){
		try {
			 road.setLastUpdateDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			 roadService.deleteRoad(road);
			 return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("错误信息" + e.getMessage());
			return 0;
		} finally {			
			System.out.println("公路信息登记 :delRoad");
		} 
	}
	@RequestMapping("/getUserById")
	@ResponseBody
	public String modify(String tid,HttpServletRequest request){
		try {			
			Road r = roadService.findById(tid);
//			request.setAttribute("add", add);
			return mapper.toJson(r);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("InfoMessage", "错误信息" + e.getMessage());
			return null;
		}
	}
	
	/**
	 * 
		 * Description: 修改公路信息
		 * @param road
		 * @param request
		 * @return
		 * @return String
		 * @throws Exception
		 * @see Note:
	 */
	@RequestMapping("/updateRoad")
	@ResponseBody
	public int updRoad(Road road,HttpServletRequest request,HttpServletResponse response){
		try {
			road.setLastUpdateDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			roadService.updateRoad(road);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("错误信息" + e.getMessage());
			return 0;
		} finally {			
			System.out.println("公路信息登记 :updRoad");
		} 
	}
	
	@RequestMapping("/updateRoadLinepoints")
	@ResponseBody
	public int updateRoadLinepoints(String id,String line_points,HttpServletRequest request,HttpServletResponse response){
		try {
			Road road = new Road();
			road.setId(id);
			road.setLine_points(line_points);
			road.setLastUpdateDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			roadService.updateRoad(road);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("错误信息" + e.getMessage());
			return 0;
		} finally {			
			System.out.println("公路信息登记 :updRoad");
		} 
	}
	
	/**
	 * 
		 * Description: 用来做页面跳转
		 * @return
		 * @return String
		 * @throws Exception
		 * @see Note:
	 */
	@RequestMapping(value = "/roadControlle", method = RequestMethod.GET)
	public String roadControlle() {
		return "jsp/baseInfo/roadInfo";
	}
	
	/**
	 * 获取某条道路上，某年的车流量
	 * @param request
	 * @param response
	 * @param road_id
	 * @param time
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	@RequestMapping(value = "/getCar_flow", method = RequestMethod.POST)
	@ResponseBody
	public String getCar_flow(HttpServletRequest request,HttpServletResponse response,String road_id,String time) throws Exception, IOException{
		String FORM_ACTION2 = PropUtil.getTargetProps().getProperty("FORM_ACTION2");//请求地址
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("report", "line_display");
		String sql_num = null;
		sql_num = "sql6";
		map.put("road_id", road_id);
		map.put("Time", time);
		map.put("sql_num", sql_num);
		String parms = gson.toJson(map);
		System.out.println(parms);
		String bigData = BigDataInterFacesClient.requestStatisPost(FORM_ACTION2, parms);
		System.out.println(bigData);
		response.getWriter().write(bigData);
		return null;
	}
	
	/**
	 * 获取某条道路上，某年的货运量
	 * @param request
	 * @param response
	 * @param road_id
	 * @param time
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	@RequestMapping(value = "/getWeight_flow", method = RequestMethod.POST)
	@ResponseBody
	public String getWeigth_flow(HttpServletRequest request,HttpServletResponse response,String road_id,String time) throws Exception, IOException{
		String FORM_ACTION2 = PropUtil.getTargetProps().getProperty("FORM_ACTION2");//请求地址
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("report", "line_display");
		String sql_num = null;
		sql_num = "sql7";
		map.put("road_id", road_id);
		map.put("Time", time);
		map.put("sql_num", sql_num);
		String parms = gson.toJson(map);
		System.out.println(parms);
		String bigData = BigDataInterFacesClient.requestStatisPost(FORM_ACTION2, parms);
		System.out.println(bigData);
		response.getWriter().write(bigData);
		return null;
	}
	
	/**
	 * 获取某条道路上，某年的货运量
	 * @param request
	 * @param response
	 * @param road_id
	 * @param time
	 * @return
	 * @throws Exception
	 * @throws IOException
	 */
	@RequestMapping(value = "/getPeccancy_flow", method = RequestMethod.POST)
	@ResponseBody
	public String getPeccancy_flow(HttpServletRequest request,HttpServletResponse response,String road_id,String time) throws Exception, IOException{
		String FORM_ACTION2 = PropUtil.getTargetProps().getProperty("FORM_ACTION2");//请求地址
		Gson gson = new Gson();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("report", "line_display");
		String sql_num = null;
		sql_num = "sql8";
		map.put("road_id", road_id);
		map.put("Time", time);
		map.put("sql_num", sql_num);
		String parms = gson.toJson(map);
		System.out.println(parms);
		String bigData = BigDataInterFacesClient.requestStatisPost(FORM_ACTION2, parms);
		System.out.println(bigData);
		response.getWriter().write(bigData);
		return null;
	}
	
	// 获取道路在道路伤害的数据（车流量，货运量，空车数，空车率，超限车辆，超限比率）
	@RequestMapping(value = "/getDamage_road", method = RequestMethod.POST)
	@ResponseBody
	public String getCar_peccancy_road(HttpServletRequest request,HttpServletResponse response,String road_id,String time) throws Exception, IOException{
		String FORM_ACTION2 = PropUtil.getTargetProps().getProperty("FORM_ACTION2");//请求地址
		Gson gson =  new GsonBuilder()
			    .setPrettyPrinting()
			    .disableHtmlEscaping()
			    .create();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("report", "line_display");
		map.put("road_id", road_id);	// in 查询，多条
		map.put("Time", time);
		map.put("sql_num", "sql11");
		String parms = gson.toJson(map);
		System.out.println(parms);
		String bigData = BigDataInterFacesClient.requestStatisPost(FORM_ACTION2, parms);
		System.out.println(bigData);
		response.getWriter().write(bigData);
		return null;
	}
	
	// 获取道路在道路伤害的数据（车流量，货运量，空车数，空车率，超限车辆，超限比率）// 按类型排序取前10
		@RequestMapping(value = "/getDamage_road_group", method = RequestMethod.POST)
		@ResponseBody
		public String getDamage_road_group(HttpServletRequest request,HttpServletResponse response,String type,String time) throws Exception, IOException{
			String FORM_ACTION2 = PropUtil.getTargetProps().getProperty("FORM_ACTION2");//请求地址
			Gson gson = new GsonBuilder()
				    .setPrettyPrinting()
				    .disableHtmlEscaping()
				    .create();
			Map<String, Object> map = new HashMap<String, Object>();
			String param = "all_sum";
			map.put("report", "line_display");
			map.put("sql_num", "sql12");
			map.put("Time", time);		
		    switch(type){
				case "1": param = "all_sum"; 
					break; 
				case "2": param = "weight_sum"; 
			        break; 
				case "3": param = "empty_sum"; 
			        break; 
				case "4": param = "empty_rate"; 
		        	break; 
				case "5": param = "peccancy_sum"; 
				    break;
				case "6": param = "peccancy_rate"; 
			    	break;
				default: break; 
	        }
	        map.put("param", param);
			String bigData = BigDataInterFacesClient.requestStatisPost(FORM_ACTION2, gson.toJson(map));

			JSONObject json = JSONObject.parseObject(bigData);
			
			for(int i = 0;i < json.getIntValue("msg");i++){
				JSONObject row = json.getJSONObject("data").getJSONObject(i + "");
				
				String road_id = row.getString("road_id");
				if(!Common.isEmpty(road_id)){
				  Road road =roadService.findById(road_id);
				  if(road != null){
					  row.put("road_name", road.getName());
				  }
				}
			}
			response.getWriter().write(bigData);
			return null;
		}
		
		// 获取道路在道路伤害的数据（车流量，货运量，空车数，空车率，超限车辆，超限比率）// 按类型排序取前10
		@RequestMapping(value = "/getDamage_road_group_asc", method = RequestMethod.POST)
		@ResponseBody
		public String getDamage_road_group_asc(HttpServletRequest request,HttpServletResponse response,String type,String time) throws Exception, IOException{
			String FORM_ACTION2 = PropUtil.getTargetProps().getProperty("FORM_ACTION2");//请求地址
			Gson gson = new GsonBuilder()
				    .setPrettyPrinting()
				    .disableHtmlEscaping()
				    .create();
			Map<String, Object> map = new HashMap<String, Object>();
			String param = "all_sum";
			map.put("report", "line_display");
			map.put("sql_num", "sql16");
			map.put("Time", time);		
		    switch(type){
				case "1": param = "all_sum"; 
					break; 
				case "2": param = "weight_sum"; 
			        break; 
				case "3": param = "empty_sum"; 
			        break; 
				case "4": param = "empty_rate"; 
		        	break; 
				case "5": param = "peccancy_sum"; 
				    break;
				case "6": param = "peccancy_rate"; 
			    	break;
				default: break; 
	        }
	        map.put("param", param);
			String bigData = BigDataInterFacesClient.requestStatisPost(FORM_ACTION2, gson.toJson(map));

			JSONObject json = JSONObject.parseObject(bigData);
			
			for(int i = 0;i < json.getIntValue("msg");i++){
				JSONObject row = json.getJSONObject("data").getJSONObject(i + "");
				
				String road_id = row.getString("road_id");
				if(!Common.isEmpty(road_id)){
				  Road road =roadService.findById(road_id);
				  if(road != null){
					  row.put("road_name", road.getName());
				  }
				}
			}
			response.getWriter().write(bigData);
			return null;
		}
}
