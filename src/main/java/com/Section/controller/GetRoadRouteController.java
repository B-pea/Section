package com.Section.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.Section.dao.DataRouteSectionMapper;
import com.Section.dao.RouteSectionMapper;
import com.Section.model.PubRoadRoute;
import com.Section.model.PubRoadSetion;
import com.Section.model.PubRoadSetionArea;
import com.Section.model.Site;
import com.Section.service.SiteService;
import com.Section.service.PubRoadRouteService;
import com.Section.service.PubRoadSetionAreaService;
import com.Section.service.PubRoadSetionService;
import com.Section.util.BigDataInterFacesClient;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/getroad")
public class GetRoadRouteController {
	@Autowired
	private PubRoadRouteService pubRoadRouteService; // 路线
	@Autowired
	private PubRoadSetionService pubRoadSetionService; // 路段
	@Autowired
	private SiteService siteService;
	@Autowired
	private PubRoadSetionAreaService pubRoadSetionAreaService; // 路段归属市
	@Autowired
	private RouteSectionMapper routeSectionMapper;
	
	
	@RequestMapping(value = "/getroutInfo", method = RequestMethod.POST)
	@ResponseBody
	public String getSiteInfo() throws Exception {
		List<PubRoadSetion> routall = pubRoadSetionService.getRoutAll();
		List<Map<String, String>> list = new ArrayList<>();
		for (PubRoadSetion route : routall) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", route.getId().toString());
			map.put("StartLatitude", route.getStartLatitude());
			map.put("StartLongtude", route.getStartLongtude());

			map.put("EndLatitude", route.getEndLatitude());
			map.put("EndLongtude", route.getEndLongtude());
			double dd = route.getMiles();
			map.put("miles", Double.toString(dd));
			map.put("RoadRailwayID", route.getRoadRailway_id());
			map.put("line_points", ""/*route.getLine_points()*/);
			list.add(map);
		}
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	
	@RequestMapping(value = "/getSetionPolicy", method = RequestMethod.POST)
	@ResponseBody
	public String getSetionPolicy(Integer setion_id) throws Exception{
		// 根据所含路段ID，找出该条记录
		PubRoadRoute temp = new PubRoadRoute();
		temp.setId(setion_id);
		PubRoadRoute routall=pubRoadRouteService.getSetionPolicy(temp);
		String type = routall.getType();
		return type;
	}
		
		
	
	/**
	 * 找到已匹配路段的集合
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getroutMated", method = RequestMethod.POST)
	@ResponseBody
	public String getroutMated() throws Exception{
		List<PubRoadSetion> setions=pubRoadSetionService.getroutMated();
		Gson gson = new Gson();
		return gson.toJson(setions);
	}
	
	/**
	 * 根据新的setion规则，更新20站点的数据，2017-5-6 09:50:30
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateSection", method = RequestMethod.POST)
	@ResponseBody
	public void updateSection() throws Exception{
		List<PubRoadSetion> setions=pubRoadSetionService.getRoutAll();
		int[] list_1_2 = {1,2};
		int[] list_60_66_75 = {60,66,75};
		int[] list_0_1 = {0,1};
		int[] list_1_2_3_4 = {1,2,3,4};
		int[] list_10_12_13_15 = {10,12,13,15};
		int[] list_8_10_12 = {8,10,12};
		Random rand = new Random();
		// 读取每条setion数据
		for(int i=0;i<setions.size();i++){
			// 准备setion_type数据
			int setion_type = 1;//list_1_2[rand.nextInt(2)];
			int sect_avg_speed = list_60_66_75[rand.nextInt(3)];
			int is_asphalt_road = list_0_1[rand.nextInt(2)];
			int road_num = list_1_2_3_4[rand.nextInt(4)];
			int road_sect_limit = 0;
			int stand_axle_load = 0;
			if(setion_type == 0){
				road_sect_limit = list_10_12_13_15[rand.nextInt(4)];
				switch(road_sect_limit){
				case 10:stand_axle_load=200;break;
				case 12:stand_axle_load=300;break;
				case 13:stand_axle_load=350;break;
				case 15:stand_axle_load=400;break;
				default:stand_axle_load=-1;break;
				}
			}else if(setion_type == 1){
				road_sect_limit = list_8_10_12[rand.nextInt(3)];
				switch(road_sect_limit){
				case 8:stand_axle_load=100;break;
				case 10:stand_axle_load=150;break;
				case 12:stand_axle_load=250;break;
				default:stand_axle_load=-1;break;
				}
			}
			// 设置传入值
			PubRoadSetion setion = new PubRoadSetion();
			setion.setId(setions.get(i).getId());
			setion.setSetionType(Integer.toString(setion_type));
			setion.setSect_avg_speed(sect_avg_speed);
			setion.setIs_asphalt_road(is_asphalt_road);
			setion.setRoad_num(road_num);
			setion.setRoad_sect_limit(road_sect_limit);
			setion.setStand_axle_load(stand_axle_load);
			pubRoadSetionService.updateSection(setion);
		}
	}
	
	@RequestMapping(value = "/updateLinePoint", method = RequestMethod.POST)
	@ResponseBody
	public void updateLinePoint(int id,String line_points,Double distance) throws Exception{
		PubRoadSetion setion = new PubRoadSetion();
		setion.setId(id);
		setion.setLine_points(line_points);
		setion.setMiles(distance);
		pubRoadSetionService.updateLinePoint(setion);
	}
	
	/**
	 * 更新路段的归属市ownArea
	 * @param setion_id
	 * @param org_code
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateOwnArea", method = RequestMethod.POST)
	@ResponseBody
	public void updateOwnArea(int setion_id,String org_code) throws Exception{
		PubRoadSetion setion = new PubRoadSetion();
		setion.setId(setion_id);
		setion.setOwn_area(org_code);
		pubRoadSetionService.updateByPrimaryKeySelective(setion);
	}
	
	/**
	 * 获取所有的路段ID及其点集（用于画图；以后有所属要求）
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAllLinePoint", method = RequestMethod.POST)
	@ResponseBody
	public String getAllLinePoint(){
		List<PubRoadSetion> linePointAll=pubRoadSetionService.getAllLinePoint();
		Gson gson = new Gson();
		String result = gson.toJson(linePointAll);
		return result;
	}
	
	@RequestMapping(value = "/getAllSetion", method = RequestMethod.POST)
	@ResponseBody
	public String getAllSetion(){
		List<PubRoadSetion> allSetion=pubRoadSetionService.getRoutAll();
		Gson gson = new Gson();
		String result = gson.toJson(allSetion);
		return result;
	}
	
	@RequestMapping(value = "/getSingleLinePoint", method = RequestMethod.POST)
	@ResponseBody
	public String getSingleLinePoint(int id) throws Exception{
		PubRoadSetion slq = new PubRoadSetion();
		slq.setId(id);
		PubRoadSetion singleSetionLinepoint=pubRoadSetionService.getSingleLinePoint(slq);
		Gson gson = new Gson();
		return gson.toJson(singleSetionLinepoint);
	}
	
	/**
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateRoute", method = RequestMethod.POST)
	@ResponseBody
	public void updateRoute() throws Exception{
		// 获取所有站点路线
		List<PubRoadRoute> routall=pubRoadRouteService.getRoutAll();
		for(int i=0;i<routall.size();i++){
			PubRoadRoute  route = routall.get(i);
			String roadSetions = route.getRoadSetions();
			String[] eachSetion = roadSetions.split(",");
			int all_speed = 0;
			for(int j=0;j<eachSetion.length;j++){
				PubRoadSetion setion = pubRoadSetionService.selectByPrimaryKey(Integer.parseInt(eachSetion[j]));
				int speed = setion.getSect_avg_speed();
				all_speed+=speed;
			}
			int avg_speed = all_speed/eachSetion.length;
			PubRoadRoute up_route = new PubRoadRoute();
			up_route.setId(route.getId());
			up_route.setRoad_avg_speed(avg_speed);
			pubRoadRouteService.updateSpeedByID(up_route);
		}
	}
	
	
	//@RequestMapping(value= "/getcarNum"  method = RequestMethod.post)
	//@ResponseBody
	/**
	 * 查询车辆数
	 * @return
	 */
	@RequestMapping(value = "/getcarNum", method = RequestMethod.POST)
	@ResponseBody
	public void getcarNum(HttpServletRequest request, HttpServletResponse response){
		
		List<PubRoadSetion> routall=pubRoadSetionService.getRoutAll();
		
		
		try{
			String FORM_ACTION2 = "http://192.168.100.11:8087/baseinfo"; //请求地址
			Gson gson = new Gson();
			Map<String,Object> map =new HashMap<String,Object>();
			map.put("report", "getcarNum_info");
		  for(int i=0;i<routall.size();i++){
			  map.put("id", routall.get(0).getId());
			  map.put("sql_num", "sql1");
		  }
		 
		    String parms = gson.toJson(map);
			System.out.println(parms);
			
			String bigData = BigDataInterFacesClient.requestStatisPost(FORM_ACTION2, parms);
			System.out.println(bigData);
			response.getWriter().write(bigData);
	} catch (Exception e) {
		e.printStackTrace();
		//logger.error("错误信息" + e.getMessage());
	} finally {
		//logger.info("--------adminDivisionTransfiniteRankListByDivision----------");
	}
	}
	
	
/**
 * 
 * 单击事件
 * @param request
 * @param response
 */
	@RequestMapping(value = "/getInfo", method = RequestMethod.POST)
	@ResponseBody
	public void getInfo(String id,HttpServletRequest request, HttpServletResponse response){
		
		try{
			String FORM_ACTION2 = "http://192.168.100.11:8087/baseinfo"; //请求地址
			Gson gson = new Gson();
			Map<String,Object> map =new HashMap<String,Object>();
			map.put("report", "getSetion");
			map.put("id", id);
			map.put("sql_num", "sql1");
		    String parms = gson.toJson(map);
			System.out.println(parms);
			
			String bigData = BigDataInterFacesClient.requestStatisPost(FORM_ACTION2, parms);
		 	System.out.println(bigData);
		 	response.getWriter().write(bigData);
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error("错误信息" + e.getMessage());
		} finally {
			//logger.info("--------adminDivisionTransfiniteRankListByDivision----------");
		}
	}
	
	
	/**
	 * 获得所有站点的所有记录
	 */
	@RequestMapping(value="/ssss",method=RequestMethod.POST)
	@ResponseBody
   public String ssss(){
		List<Site> siteList = siteService.getSiteAll();
		Gson son=new Gson();
		return son.toJson(siteList);
	}
	
	/**
	 * 根据传入ID，将相同路段的ID更改为传入ID
	 * @throws Exception
	 */
	@RequestMapping(value = "/updataRouteInfo", method = RequestMethod.POST)
	@ResponseBody
	public void updateRouteInfo(String targetId,String ncId) throws Exception{
		// 根据所含路段ID，找出该条记录
		PubRoadRoute routall=pubRoadRouteService.getRoutAllSetion(ncId);
		if(routall == null){
			return;
		}
		// 取出road_setion
		String setion = routall.getRoadSetions();
		// 正则替换
		String temp_str = ncId+",";
		if(setion.indexOf(temp_str)!=-1){
			String target_str = targetId+",";
			setion = setion.replace(temp_str, target_str);
		}
		temp_str = ","+ncId+",";
		if(setion.indexOf(temp_str)!=-1){
			String target_str = ","+targetId+",";
			setion = setion.replace(temp_str, target_str);
		}
		temp_str = ","+ncId;
		if(setion.indexOf(temp_str)!=-1){
			String target_str = ","+targetId;
			setion = setion.replace(temp_str, target_str);
		}
		// 更新该条记录
		routall.setRoadSetions(setion);
		pubRoadRouteService.updateByPrimaryKey(routall);
	}
	
	/**
	 * 根据id将路段所属道路的值更新
	 * @param railway_id
	 * @param setion_id
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateRailwayID", method = RequestMethod.POST)
	@ResponseBody
	public void updateRailwayID(String railway_id,Integer setion_id) throws Exception{
		PubRoadSetion setion = new PubRoadSetion();
		setion.setId(setion_id);
		setion.setRoadRailway_id(railway_id);
		pubRoadSetionService.updateRailwayID(setion);
	}
	
	@RequestMapping(value = "/updateRoadToSetionID", method = RequestMethod.POST)
	@ResponseBody
	public void updateRoadToSetionID(String road_id,Integer setion_id) throws Exception{
		PubRoadSetion setion = new PubRoadSetion();
		setion.setId(setion_id);
		setion.setRoadRailway_id(road_id);	// 目前，当Railway_id是road_id,2017-6-19 21:25:51
		pubRoadSetionService.updateRailwayID(setion);
	}
	
	@RequestMapping(value = "/insertRouteSection", method = RequestMethod.POST)
	@ResponseBody
	public void insertRouteSection() throws Exception{
		List<PubRoadRoute> list = pubRoadRouteService.getRoutAll();
		for(int i=0;i<list.size();i++) {
			String sectionList = list.get(i).getRoadSetions();
			String[] str = sectionList.split(",");
			for(int j=0;j<str.length;j++) {
				pubRoadRouteService.insertRouteSection(list.get(i).getId(),Integer.parseInt(str[j]));
			}
		}
	}
	
	
	/**
	 * 根据传入ID，将相似的路段ID更改为传入ID
	 * @throws Exception
	 */
	@RequestMapping(value = "/updataRouteInfoNear", method = RequestMethod.POST)
	@ResponseBody
	public void updataRouteInfoNear(String targetId,String ncId) throws Exception{
		// 根据所含路段ID，找出该条记录
		List<PubRoadRoute> routall=pubRoadRouteService.updataRouteInfoNear(ncId);
		if(routall == null){
			return;
		}
		for(int i=0;i<routall.size();i++){
			// 取出road_setion
			String setion = routall.get(i).getRoadSetions();
			// 正则替换
			String temp_str = ncId+",";
			if(setion.indexOf(temp_str)!=-1){
				String target_str = targetId+",";
				setion = setion.replace(temp_str, target_str);
			}
			temp_str = ","+ncId+",";
			if(setion.indexOf(temp_str)!=-1){
				String target_str = ","+targetId+",";
				setion = setion.replace(temp_str, target_str);
			}
			temp_str = ","+ncId;
			if(setion.indexOf(temp_str)!=-1){
				String target_str = ","+targetId;
				setion = setion.replace(temp_str, target_str);
			}
			// 更新该条记录
			routall.get(i).setRoadSetions(setion);
			pubRoadRouteService.updateByPrimaryKey( routall.get(i));
		}
		
	}
	
	@RequestMapping(value = "/getAllSite", method = RequestMethod.POST)
	@ResponseBody
	public String getAllSite() throws Exception{
		List<Site> siteAll=siteService.getAllSite();
		Gson son=new Gson();
		return son.toJson(siteAll);
	}
	
	// 找到所有路段的id（不重复）
	@RequestMapping(value = "/getAllRouteId", method = RequestMethod.POST)
	@ResponseBody
	public String getAllRouteId() throws Exception{
		// 找到所有路段
		List<PubRoadRoute> routall=pubRoadRouteService.getRoutAll();
		if(routall == null){
			return "";
		}
		List<String> id_list = new ArrayList<String>();	// 总的无重复路段id集合
		
		for(int i=0;i<routall.size();i++){
			// System.out.println(i);
			// 取出road_setion
			String setion = routall.get(i).getRoadSetions();
			// 分割setion得到单个id
			String[] temp_list =  setion.split(",");	// 当前setion的路段id集合
			for(int j=0;j<temp_list.length;j++){
				boolean put_flag = false;
				if(id_list != null){
					for(int k=0;k<id_list.size();k++){
						if(temp_list[j] == id_list.get(k)){
							put_flag = true;
						}
					}
				}				
				if(!put_flag){
					id_list.add(temp_list[j]);
				}
			}
		}
		/*Map<String, Object> back = new HashMap<>();
		back.put("list", id_list);*/
		
		Gson son=new Gson();
		String aString = son.toJson(id_list);
		return aString;
	}
	
	// 根据id找到路段的所有信息
	@RequestMapping(value = "/selectByPrimaryKey", method = RequestMethod.POST)
	@ResponseBody
	public String selectByPrimaryKey(Integer id) throws Exception{
		PubRoadSetion prs = pubRoadSetionService.selectByPrimaryKey(id);
		if(prs == null){
			System.out.println(id);
		}
		Map<String, Object> back = new HashMap<>();
		back.put("startLongtude", prs.getStartLongtude());
		back.put("startLatitude", prs.getStartLatitude());
		back.put("endLongtude", prs.getEndLongtude());
		back.put("endLatitude", prs.getEndLatitude());
		back.put("id", prs.getId());
		back.put("miles", prs.getMiles());
		Gson son=new Gson();
		String aString = son.toJson(back);
		return aString;
	}
	
	// 在路线表清除相同的setion，策略优先级12>11>10>13
	@RequestMapping(value = "/clearRouteSetion", method = RequestMethod.POST)
	@ResponseBody
	public void clearRouteSetion() throws Exception{
		// 获取所有站点id
		List<Site> siteList = siteService.getSiteAll();
		// 双重循环，获取四个策略的setion
		for(int i=0;i<siteList.size();i++){
			System.out.println("在路线表清除相同的setion"+i+"/"+siteList.size());
			for(int j=0;j<siteList.size();j++){
				if(i!=j){
					String startSite = siteList.get(i).getOrgCode();
					String endSite = siteList.get(j).getOrgCode();
					PubRoadRoute pubRoadRoute = new PubRoadRoute();
					pubRoadRoute.setStartSite(startSite);
					pubRoadRoute.setEndSite(endSite);
					List<PubRoadRoute> routall=pubRoadRouteService.getRouteBySE(pubRoadRoute);
					if(routall == null){
						continue;
					}
					String setion1 = null;
					String setion2 = null;
					String setion3 = null;
					String setion4 = null;
					for(int k=0;k<routall.size();k++){
						String type = routall.get(k).getType();
						if(type.equals("10")){
							setion1 = routall.get(k).getRoadSetions();
						}else if(type.equals("11")){
							setion2 = routall.get(k).getRoadSetions();
						}else if(type.equals("12")){
							setion3 = routall.get(k).getRoadSetions();
						}else if(type.equals("13")){
							setion4 = routall.get(k).getRoadSetions();
						}
					}
					// 获得相同setion的路线ID及策略值
					List<String> deleteType = new ArrayList<String>();
					if(setion3 != null){
						if(setion3.equals(setion1)){
							// 删除setion1
							deleteType.add("10");
						}
						if(setion3.equals(setion2)){
							// 删除setion2
							deleteType.add("11");
						}
						if(setion3.equals(setion4)){
							// 删除setion4
							deleteType.add("13");
						}
					}
					if(setion2 != null){
						if(setion2.equals(setion1)){
							// 删除setion1
							if(deleteType.indexOf("10")<0){
								deleteType.add("10");
							}
						}
						if(setion2.equals(setion4)){
							// 删除setion4
							if(deleteType.indexOf("13")<0){
								deleteType.add("13");
							}
						}
					}
					if(setion1 != null){
						if(setion1.equals(setion4)){
							// 删除setion4
							if(deleteType.indexOf("13")<0){
								deleteType.add("13");
							}
						}
					}					
					// 根据优先级，删除低优先级的路线
					for(int l=0;l<deleteType.size();l++){
						pubRoadRoute.setType(deleteType.get(l));
						pubRoadRouteService.deleteBySET(pubRoadRoute);//根据起止站点和type删除
					}
				}
			}
		}
	}
	
	// 在路段表清除重复和相似的路段
	@RequestMapping(value = "/deleteSetionByID", method = RequestMethod.POST)
	@ResponseBody
	public void deleteSetionByID(int id) throws Exception{
		pubRoadSetionService.deleteByPrimaryKey(id);
	}
	
	// 获取路段车流量数据
	@RequestMapping(value = "/getSetionCarNum", method = RequestMethod.POST)
	@ResponseBody
	public void getSetionCarNum(int id, HttpServletResponse response) throws Exception{
		try{
			String FORM_ACTION2 = "http://192.168.100.11:8087/baseinfo"; //请求地址
			Gson gson = new Gson();
			Map<String,Object> map =new HashMap<String,Object>();
			map.put("report", "getSetion");
			map.put("id", id);
			map.put("sql_num", "sql1");
		    String parms = gson.toJson(map);
			System.out.println(parms);
			
			String bigData = BigDataInterFacesClient.requestStatisPost(FORM_ACTION2, parms);
		 	System.out.println(bigData);
		 	response.getWriter().write(bigData);
		} catch (Exception e) {
			e.printStackTrace();
			//logger.error("错误信息" + e.getMessage());
		} finally {
			//logger.info("--------adminDivisionTransfiniteRankListByDivision----------");
		}
	}
	
	// 在路段表清除重复和相似的路段
	@RequestMapping(value = "/insertSetionArea", method = RequestMethod.POST)
	@ResponseBody
	public void insertSetionArea(String area_code,String ply_points) throws Exception{
		PubRoadSetionArea area = new PubRoadSetionArea();
		area.setArea_code(area_code);
		area.setPly_points(ply_points);
		int a = pubRoadSetionAreaService.insertSetionArea(area);
	}
	
	// 搜索路径表所有不重复的站点编码
	@RequestMapping(value = "/getSiteCodeOld", method = RequestMethod.POST)
	@ResponseBody
	public String getSiteCodeOld() throws Exception{
		List<PubRoadRoute> list = pubRoadRouteService.getSiteCodeOld();
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	
	// 根据所选站点，搜索路径表
	@RequestMapping(value = "/showRoute", method = RequestMethod.POST)
	@ResponseBody
	public String showRoute(String siteStart,String siteEnd) throws Exception{
		List<PubRoadRoute> list = pubRoadRouteService.showRoute(siteStart,siteEnd);
		String linePoints = "";
		JSONObject back = new JSONObject();
		Gson gson = new Gson();
		for(int i=0;i<list.size();i++) {
			String idListSetion = list.get(i).getRoadSetions();
			String[] idList = idListSetion.split(",");
			for(int j=0;j<idList.length;j++) {
				PubRoadSetion setion = pubRoadSetionService.selectByPrimaryKey(Integer.parseInt(idList[j]));
				linePoints += setion.getLine_points()+";";
			}
			linePoints = linePoints.substring(0,linePoints.length()-1);
			back.put(i+"", linePoints);
			linePoints = "";
		}
		back.put("length", list.size());
		return gson.toJson(back);
	}
	// 根据所选站点，搜索路径表
	@RequestMapping(value = "/showRouteSite", method = RequestMethod.POST)
	@ResponseBody
	public String showRouteSite(String siteStart) throws Exception{
		PubRoadRoute route = pubRoadRouteService.showRouteSite(siteStart);
		Gson gson = new Gson();
		return gson.toJson(route);
	}
	
	// 清理重复的路段和路径
	@RequestMapping(value = "/killRouteSetionByMiles", method = RequestMethod.POST)
	@ResponseBody
	public void killRouteSetionByMiles() throws Exception{
		// 1——根据起止点和公里数，去除多余的路径及其路段
	/*	List<PubRoadRoute> routeAll = pubRoadRouteService.getRoutAll();
		List<Integer> alreadyDelete = new ArrayList<>();
		for(int i=0,size = routeAll.size();i< size;i++) {
			System.out.println("清理重复路径："+i+"/"+size);
			if(alreadyDelete.contains(i)) {	// 如果已经被删了
				continue;
			}
			for(int j=i+1;j<size;j++) {	// 向后比较
				if(routeAll.get(i).getStartSite().equals(routeAll.get(j).getStartSite()) 
						&& routeAll.get(i).getEndSite().equals(routeAll.get(j).getEndSite())
						&& routeAll.get(i).getMiles().equals(routeAll.get(j).getMiles())) {	// 如果起止点和公里数相同
					alreadyDelete.add(j);
					String[] idList = routeAll.get(j).getRoadSetions().split(",");
					for(int k=0;k<idList.length;k++) {	// 删掉这个路径的所有路段
						pubRoadSetionService.deleteByPrimaryKey(Integer.parseInt(idList[k]));
					}
					pubRoadRouteService.deleteByPrimaryKey(routeAll.get(j).getId());	// 删除这个路径
				}
			}
		}*/
		// 1.5——清理空路段
		/*List<PubRoadSetion> setionEmpty = pubRoadSetionService.getRoutAllEmptyPoints();
		for(PubRoadSetion setion : setionEmpty) {
			updateRouteByEmptySetion(setion);
			pubRoadSetionService.deleteByPrimaryKey(setion.getId());
		}*/
		
		// 2——制作链表，获取重复路段
		List<PubRoadSetion> setionAll = pubRoadSetionService.getRoutAllWithoutPoints();
		Map<String, Object> sameSetion = new HashMap<>();	// 以唯一id为索引，重复的id做成list为值
		List<String> alreadyHave = new ArrayList<>();	// 所有重复的id集合
		
		StopWatch stopwatch = new StopWatch();
		for(int i=0,total=setionAll.size();i<total;i++) {
			System.out.println("制作链表："+i+"/"+total);
			List<String> eachSameSetionList = new ArrayList<>();	// 这个唯一id的所有重复id
			if(alreadyHave.contains(setionAll.get(i).getId()+"")) {
				continue;
			}
			boolean hasSame = false;
			for(int j=i+1;j<total;j++) {
				if(isSameSetion(setionAll.get(i),setionAll.get(j))) {
					eachSameSetionList.add(setionAll.get(j).getId()+"");
					alreadyHave.add(setionAll.get(j).getId()+"");
					hasSame = true;
					System.out.println("重复路段："+setionAll.get(i).getId()+"——"+setionAll.get(j).getId());
				}
			}
			if(hasSame) {
				sameSetion.put(setionAll.get(i).getId()+"", eachSameSetionList);
			}
		}
		// 3——路径中，使用链表进行替换，更换后清理路段
		int clearRoute = 0;
		int totalClearRoute = sameSetion.size();
		for (String key : sameSetion.keySet()) {
			System.out.println("替换路径和清除路段："+clearRoute+"/"+totalClearRoute);
			clearRoute++;
			@SuppressWarnings("unchecked")
			List<String> sameList = (List<String>) sameSetion.get(key);
			for(String needChangeId : sameList) {
				//updateRouteInfo(key,needChangeId);
				routeSectionMapper.updateBySectionId(Integer.parseInt(key),Integer.parseInt(needChangeId));
				pubRoadSetionService.deleteByPrimaryKey(Integer.parseInt(needChangeId));
			}
		} 	 			
		/*stopwatch.start("清理重复路径");
		// 5——清理重复的路径
		clearRouteSetion();
		stopwatch.stop();*/
		System.out.println(stopwatch.prettyPrint());
	}
	

	private void updateRouteByEmptySetion(PubRoadSetion setion) {
		// TODO Auto-generated method stub
		PubRoadRoute route = pubRoadRouteService.getRouteBySetion(setion);
		// 取出road_setion
		String setionList = route.getRoadSetions();
		// 正则替换
		setionList = setionList.replaceAll(","+setion.getId(), "");
		// 更新该条记录
		route.setRoadSetions(setionList);
		pubRoadRouteService.updateByPrimaryKey(route);
	}

	// 比较两个setion是否相同
	public boolean isSameSetion(PubRoadSetion setionOne,PubRoadSetion setionTwo) {
		if(setionOne.getStartLongtude().equals(setionTwo.getStartLongtude()) &&
				setionOne.getStartLatitude().equals(setionTwo.getStartLatitude()) &&
				setionOne.getEndLongtude().equals(setionTwo.getEndLongtude()) &&
				setionOne.getEndLatitude().equals(setionTwo.getEndLatitude()) &&
				(setionOne.getMiles()+"").equals(setionTwo.getMiles()+"")) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	
}
