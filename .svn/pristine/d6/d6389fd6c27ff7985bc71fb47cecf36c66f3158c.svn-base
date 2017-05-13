package com.Section.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.Section.model.PubRoadRoute;
import com.Section.model.PubRoadSetion;
import com.Section.model.Site;
import com.Section.service.SiteService;
import com.Section.service.PubRoadRouteService;
import com.Section.service.PubRoadSetionService;

@Controller
@RequestMapping("/direction")
public class GetDirectionController {
	
	@Autowired
	private PubRoadRouteService pubRoadRouteService; // 路线
	@Autowired
	private PubRoadSetionService pubRoadSetionService; // 路段
	@Autowired  
    private SiteService siteService; // 站点
	
	/**
	 * 
		* 
		* @Title: getDirectionList 
		* @Description: TODO 根据输入的两个站点获取路线和路段
		* @param @param busiNum
		* @param @return
		* @param @throws Exception    设定文件 
		* @return String    返回类型 
		* @throws
	 */
	@RequestMapping(value = "/getDirectionList", method = RequestMethod.POST)
	@ResponseBody
	public String getDirectionList(String array,String type)throws Exception{
		System.out.println("进入");
		System.out.println(array);
		Gson gson = new Gson();
		ArrayList<Object> arrList = new ArrayList<Object>();
		ArrayList<Object> arrList1 = new ArrayList<Object>();
		ArrayList<Object> arrList2 = new ArrayList<Object>();
		System.out.println("--------------------------------------------");
		arrList = gson.fromJson(array, ArrayList.class);
		String str = arrList.get(0).toString();
		System.out.println(str);
		System.out.println("--------------------------------------------");
		arrList1 = gson.fromJson(str, ArrayList.class);
		StringBuffer roadSetion = new StringBuffer();
		Integer size = 0;
		
		int[] list_1_2 = {1,2};
		int[] list_60_66_75 = {60,66,75};
		int[] list_0_1 = {0,1};
		int[] list_1_2_3_4 = {1,2,3,4};
		int[] list_10_12_13_15 = {10,12,13,15};
		int[] list_8_10_12 = {8,10,12};
		Random rand = new Random();
		
		// 将百度返回的路段信息插入路段表(pub_road_setion)
		for(int i=0; i<arrList1.size();i++){
			String str1 = arrList1.get(i).toString();
			arrList2 = gson.fromJson(str1, ArrayList.class);
			PubRoadSetion pubRoadSetion = new PubRoadSetion();
			
			Double mile = (Double) arrList2.get(0) / 1000;
			pubRoadSetion.setMiles(mile);
			pubRoadSetion.setStartLongtude(arrList2.get(1).toString());
			pubRoadSetion.setStartLatitude(arrList2.get(2).toString());
			pubRoadSetion.setEndLongtude(arrList2.get(3).toString());
			pubRoadSetion.setEndLatitude(arrList2.get(4).toString());
			String line_points = (String) arrList2.get(6);
			pubRoadSetion.setLine_points(line_points);
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
			pubRoadSetion.setSetionType(Integer.toString(setion_type));
			pubRoadSetion.setSect_avg_speed(sect_avg_speed);
			pubRoadSetion.setIs_asphalt_road(is_asphalt_road);
			pubRoadSetion.setRoad_num(road_num);
			pubRoadSetion.setRoad_sect_limit(road_sect_limit);
			pubRoadSetion.setStand_axle_load(stand_axle_load);
			
			pubRoadSetionService.insert(pubRoadSetion);
		}
		// 将百度返回的路线信息，插入路线表pub_road_route
		List<Integer> idList = pubRoadSetionService.selectPubRoadSetionBySize(arrList1.size());
		for(int k=0,size_k=idList.size();k<size_k;k++){
			roadSetion.insert(0, idList.get(k));
			if(k != size_k-1){
				roadSetion.insert(0, ",");
			}			
		}
		System.out.println(arrList.get(1));
		String a1[] = arrList.get(2).toString().split(",");
		System.out.println(a1[0]+":"+a1[1]);
		String a2[] = arrList.get(3).toString().split(",");
		System.out.println(a2[0]+":"+a2[1]);
		PubRoadRoute pubRoadRoute = new PubRoadRoute();
		pubRoadRoute.setStartLongtude(a1[1]);
		pubRoadRoute.setStartLatitude(a1[0]);
		pubRoadRoute.setEndLongtude(a2[1]);
		pubRoadRoute.setEndLatitude(a2[0]);
		
		pubRoadRoute.setStartSite(arrList.get(4).toString());
		pubRoadRoute.setEndSite(arrList.get(5).toString());
		String milesss = arrList.get(1).toString();
		Double mile1 = Double.parseDouble(milesss) / 1000;
		pubRoadRoute.setMiles(mile1);
		pubRoadRoute.setRoadSetions(roadSetion.toString());
		pubRoadRoute.setType(type);
		
		pubRoadRouteService.insert(pubRoadRoute);
		
		System.out.println("--------------------------------------------");		
		System.out.println("结束");
		return "成功";
	}
	
	
	@RequestMapping(value = "/getSiteInfo", method = RequestMethod.POST)
	@ResponseBody
	public String getSiteInfo() throws Exception{
		List<Site> siteList = siteService.getSiteAll();
		
		/*List<Object> map = new ArrayList<>(); 
		
		for(int i=0; i<siteList.size();i++ ){
			for(int j = i+1; j<siteList.size();j++){
				Map<String, Object> map1 = new HashMap<>();
				//System.out.println(siteList.get(i).getName()+":"+siteList.get(j).getName());
				map1.put("startlat", siteList.get(j).getLatitude());
				map1.put("startlog", siteList.get(j).getLongitude());
				map1.put("startOrg", siteList.get(j).getOrgCode());
				map1.put("endlat", siteList.get(i).getLatitude());
				map1.put("endlog", siteList.get(i).getLongitude());	
				map1.put("endOrg", siteList.get(i).getOrgCode());
				map.add(map1);
			}
		}*/
		Gson gson = new Gson();
		return gson.toJson(siteList);
	}

	@RequestMapping(value = "/getSiteInfos", method = RequestMethod.GET)
	@ResponseBody
	public String getSiteInfos() throws Exception{
		List<Site> siteList = siteService.getSiteAll();
		for(int i=0; i<siteList.size();i++ ){
			for(int j = i+1; j<siteList.size();j++){
				System.out.println(siteList.get(j).getName()+"++++++"+siteList.get(i).getName());
			}
		}
		return null;
	}

}
