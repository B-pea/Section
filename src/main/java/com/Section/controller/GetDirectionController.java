package com.Section.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.servlet.RequestToViewNameTranslator;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.lowagie.text.Section;
import com.mysql.fabric.xmlrpc.base.Data;
import com.Section.dao.DataRouteMapper;
import com.Section.dao.DataRouteSectionMapper;
import com.Section.dao.DataSectionMapper;
import com.Section.model.DataRoute;
import com.Section.model.DataRouteSection;
import com.Section.model.DataSection;
import com.Section.model.PubRoadRoute;
import com.Section.model.PubRoadSetion;
import com.Section.model.Road;
import com.Section.model.Site;
import com.Section.service.SiteService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.Section.service.PubRoadRouteService;
import com.Section.service.PubRoadSetionService;
import com.Section.service.RoadService;

@Controller
@RequestMapping("/direction")
public class GetDirectionController {
	
	@Autowired
	private PubRoadRouteService pubRoadRouteService; // 路线
	@Autowired
	private PubRoadSetionService pubRoadSetionService; // 路段
	@Autowired  
    private SiteService siteService; // 站点
	@Autowired  
    private DataSectionMapper dataSectionMapper; // 站点
	@Autowired  
    private DataRouteMapper dataRouteMapper; // 站点
	@Autowired  
    private DataRouteSectionMapper dataRouteSectionMapper; // 站点
	@Autowired
	private RoadService roadService;
	
	Gson gson = new Gson();
	ArrayList<Object> arrList = new ArrayList<Object>();
	ArrayList<Object> arrList1 = new ArrayList<Object>();
	ArrayList<Object> arrList2 = new ArrayList<Object>();
	int[] list_1_2 = {1,2};
	int[] list_60_66_75 = {60,66,75};
	int[] list_0_1 = {0,1};
	int[] list_1_2_3_4 = {1,2,3,4};
	int[] list_10_12_13_15 = {10,12,13,15};
	int[] list_8_10_12 = {8,10,12};
	Random rand = new Random();
	
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
		StringBuffer roadSetion = new StringBuffer();
		
		System.out.println("进入");
		System.out.println("--------------------------------------------");
		arrList = gson.fromJson(array, ArrayList.class);
		String str = arrList.get(0).toString();
		arrList1 = gson.fromJson(str, ArrayList.class);
		Integer size = 0;
		
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
			String line_points = arrList2.get(6).toString();
			if(line_points.equals("[x]")) {
				pubRoadSetion.setLine_points("");
			}else {
				String[] linePointsList = line_points.split(",");
				line_points = "";
				for(int k=0;k<linePointsList.length;k+=2) {
					line_points+=linePointsList[k]+","+linePointsList[k+1]+";";
				}
				line_points = line_points.substring(1, line_points.length()-2);
				pubRoadSetion.setLine_points(line_points.replace(" ", ""));
			}
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
		String a1[] = arrList.get(2).toString().split(",");
		String a2[] = arrList.get(3).toString().split(",");
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
		
		arrList.clear();
		arrList1.clear();
		arrList2.clear();
		
		return "成功";
	}
	
	@RequestMapping(value = "/createRouteAndSection", method = RequestMethod.POST)
	@ResponseBody
	public void createRouteAndSection(String startSiteCode,String endSiteCode,String api,String policy)throws Exception{
		Site startSite = new Site();
		startSite.setOrgCode(startSiteCode);
		startSite = siteService.getSite(startSite);
		
		Site endSite = new Site();
		endSite.setOrgCode(endSiteCode);
		endSite = siteService.getSite(endSite);
		
		JSONObject json = (JSONObject) JSONObject.parse(api);
		
		// 1 插入路段表
		JSONArray routes = json.getJSONObject("result").getJSONArray("routes");	// 获取路径数量
		double distance = 0;	// 此时单位米
		DecimalFormat df = new DecimalFormat("########0.000000");
		List<Integer> sectionList = new ArrayList<>();
		for(int i=0;i<routes.size();i++) {
			JSONObject eachRoute = (JSONObject) routes.get(i);
			distance += eachRoute.getDoubleValue("distance");
			JSONArray steps = eachRoute.getJSONArray("steps");
			for(int j=0;j<steps.size();j++) {
				JSONObject eachStep = (JSONObject) steps.get(j);
				DataSection dataSection = new DataSection();
				dataSection.setStartLongtude(Double.parseDouble(df.format(Double.parseDouble(eachStep.getJSONObject("stepOriginLocation").getString("lng")))));
				dataSection.setStartLatitude(Double.parseDouble(df.format(Double.parseDouble(eachStep.getJSONObject("stepOriginLocation").getString("lat")))));
				dataSection.setEndLongtude(Double.parseDouble(df.format(Double.parseDouble(eachStep.getJSONObject("stepDestinationLocation").getString("lng")))));
				dataSection.setEndLatitude(Double.parseDouble(df.format(Double.parseDouble(eachStep.getJSONObject("stepDestinationLocation").getString("lat")))));
				dataSection.setMiles(eachStep.getDouble("distance")/1000);
				int setion_type = 1;//list_1_2[rand.nextInt(2)];
				int sect_avg_speed = list_60_66_75[rand.nextInt(3)];
				int is_asphalt_road = list_0_1[rand.nextInt(2)];
				int road_num = list_1_2_3_4[rand.nextInt(4)];
				int road_sect_limit = 0;
				int stand_axle_load = 0;
				if(is_asphalt_road == 0){	// 是否沥青
					road_sect_limit = list_10_12_13_15[rand.nextInt(4)];
					switch(road_sect_limit){
					case 10:stand_axle_load=200;break;
					case 12:stand_axle_load=300;break;
					case 13:stand_axle_load=350;break;
					case 15:stand_axle_load=400;break;
					default:stand_axle_load=-1;break;
					}
				}else if(is_asphalt_road == 1){
					road_sect_limit = list_8_10_12[rand.nextInt(3)];
					switch(road_sect_limit){
					case 8:stand_axle_load=100;break;
					case 10:stand_axle_load=150;break;
					case 12:stand_axle_load=250;break;
					default:stand_axle_load=-1;break;
					}
				}				
				dataSection.setSetionType(setion_type+"");
				dataSection.setSectAvgSpeed(sect_avg_speed);
				dataSection.setIsAsphaltRoad(is_asphalt_road);
				dataSection.setRoadNum(road_num);
				dataSection.setRoadSectLimit(road_sect_limit);
				dataSection.setStandAxleLoad(stand_axle_load);
				dataSection.setLinePoints(eachStep.getString("path"));
				List<DataSection> sameSection = dataSectionMapper.isHaveSection(dataSection);
				if(sameSection.size() == 0) {
					dataSectionMapper.insert(dataSection);
					sectionList.add(dataSection.getId());
				}else {
					sectionList.add(sameSection.get(0).getId());
				}
			}
		}
		// 2 插入路径表
		DataRoute dataRoute = new DataRoute();
		dataRoute.setStartSite(startSiteCode);
		dataRoute.setStartLongtude(Double.parseDouble(startSite.getLongitude()));
		dataRoute.setStartLatitude(Double.parseDouble(startSite.getLatitude()));
		dataRoute.setEndSite(endSiteCode);
		dataRoute.setEndLongtude(Double.parseDouble(endSite.getLatitude()));
		dataRoute.setEndLatitude(Double.parseDouble(endSite.getLongitude()));
		dataRoute.setMiles(distance*100/100000);
		dataRoute.setType(policy);
		String sectionIdList = "";
		for(Integer sectionId : sectionList) {
			sectionIdList += sectionId + ",";
		}
		sectionIdList = sectionIdList.substring(0,sectionIdList.length()-1);
		dataRoute.setRoadSetions(sectionIdList);
		dataRouteMapper.insert(dataRoute);
		System.out.println(dataRoute.getId()+"+1");
		// finish
	}
	
	// 重新获取站点路径
	@RequestMapping(value = "/siteCodeReGet", method = RequestMethod.POST)
	@ResponseBody
	public String siteCodeReGet(String siteCode) throws Exception{
		// 先删除
		List<DataRoute> routeList = dataRouteMapper.selectBySiteCode(siteCode);
		for(int i=0;i<routeList.size();i++) {
			dataRouteSectionMapper.deleteByRouteId(routeList.get(i).getId());
			dataRouteMapper.deleteByPrimaryKey(routeList.get(i).getId());
		}
		// 再让前端获取
		return siteCode;
	}
	
	// 显示该站点的全部轨迹
	@RequestMapping(value = "/showRouteBySite", method = RequestMethod.POST)
	@ResponseBody
	public String showRouteBySite(String siteCode) throws Exception{
		List<DataRoute> routeList = dataRouteMapper.selectBySiteCode(siteCode);
		JSONObject backJson = new JSONObject();
		int i=0;
		for(DataRoute route : routeList) {
			String[] sectionIdList = route.getRoadSetions().split(",");
			String points = "";
			for(String sectionId : sectionIdList) {
				DataSection section = dataSectionMapper.selectByPrimaryKey(Integer.parseInt(sectionId));
				points += section.getLinePoints()+";";
			}
			points = points.substring(0,points.length()-1);
			backJson.put(i+"", points);
			i++;
		}
		backJson.put("length", backJson.size());
		return backJson.toJSONString();
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
	
	@RequestMapping(value = "/sleepTime", method = RequestMethod.POST)
	@ResponseBody
	public String sleepTime(long time,String order)throws Exception{
		Thread.sleep(time);
		List<Site> siteList = siteService.getSiteAll();
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("i", order);
		jsonObject.put("dataKey", siteList);
		Gson gson = new Gson();
		return gson.toJson(jsonObject);
	}

	@RequestMapping(value = "/isFullRoute", method = RequestMethod.POST)
	@ResponseBody
	public String isFullRoute(String siteCode)throws Exception{
		PubRoadRoute pubRoadRoute = new PubRoadRoute();
		pubRoadRoute.setStartSite(siteCode);
		int num = pubRoadRouteService.isFullRoute(pubRoadRoute);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("siteCode", siteCode);
		jsonObject.put("num", num);
		Gson gson = new Gson();
		return gson.toJson(jsonObject);
	}
	
	// 这对站点间的路径是否存在
	@RequestMapping(value = "/isHaveOurRoute", method = RequestMethod.POST)
	@ResponseBody
	public String isHaveOurRoute(DataRoute route)throws Exception{
		int totalNum = dataRouteMapper.getByStartEndSite(route);
		JSONObject back = new JSONObject();
		if(totalNum  == 0) {
			back.put("result", "false");
			back.put("startSite", route.getStartSite());
			back.put("endSite", route.getEndSite());
			back.put("startPoint", route.getStartLongtude()+","+route.getStartLatitude());
			back.put("endPoint", route.getEndLongtude()+","+route.getEndLatitude());
		}else {
			back.put("result", "true");
		}
		return gson.toJson(back);
	}
	
	// 路段匹配道路，概率匹配
	@RequestMapping(value = "/sectionMatchRoad", method = RequestMethod.POST)
	@ResponseBody
	public String sectionMatchRoad() throws Exception{
		List<DataSection> sectionList = dataSectionMapper.selectAllSection();
		List<Road> roadList = roadService.getAllRoad("");
		for(Road road : roadList) {
			String[] roadPoints = road.getLine_points().split(";");
			for(DataSection section : sectionList) {
				String[] sectionPoints = section.getLinePoints().split(";");
				double distancePrime =  getPrimaryDistance(roadPoints,sectionPoints);
				if(distancePrime > 1) {
					System.out.println("初始匹配太远，下一个");
					continue;
				}else {
					double sameRatio = getSameRatio(roadPoints,sectionPoints);
					if(sameRatio>0.5) {
						section.setRoadrailwayId(road.getId());
						dataSectionMapper.updateByPrimaryKey(section);
					}
				}
			}
		}
		return "true";
	}
	
	// 路段的第一个点与道路的距离
	public double getPrimaryDistance(String[] roadPoints,String[] sectionPoints) {
		double smallDistance = 100; // 100公里
		for(String roadPoint: roadPoints) {
			double tempDistance = getDistance(Double.parseDouble(roadPoint.split(",")[1])
					,Double.parseDouble(roadPoint.split(",")[0])
					,Double.parseDouble(sectionPoints[0].split(",")[1])
					,Double.parseDouble(sectionPoints[0].split(",")[0]));
			if(tempDistance < smallDistance) {
				smallDistance = tempDistance;
			}
		}
		return smallDistance;
	}

	// 获取相似概率，0-1.0
	public double getSameRatio(String[] roadPoints,String[] sectionPoints) {
		int sameNum = 0;
		for(String sectionPoint: sectionPoints) {
			for(String roadPoint:roadPoints) {
				double minDistance = getDistance(Double.parseDouble(roadPoint.split(",")[1])
						,Double.parseDouble(roadPoint.split(",")[0])
						,Double.parseDouble(sectionPoint.split(",")[1])
						,Double.parseDouble(sectionPoint.split(",")[0]));
				if(minDistance < 0.1) {
					sameNum++;
				}
			}
		}
		double sameRatio = sameNum*1.0/sectionPoints.length;
		return sameRatio;
	}
	
	public double EARTH_RADIUS = 6378.137;    
    
	public double rad(double d) {    
        return d * Math.PI / 180.0;    
    }    
    
    /**   
     * 通过经纬度获取距离(单位：公里)   
     * @param lat1   
     * @param lng1   
     * @param lat2   
     * @param lng2   
     * @return   
     */    
	public double getDistance(double lat1, double lng1, double lat2,    
                                     double lng2) {    
        double radLat1 = rad(lat1);    
        double radLat2 = rad(lat2);    
        double a = radLat1 - radLat2;    
        double b = rad(lng1) - rad(lng2);    
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)    
                + Math.cos(radLat1) * Math.cos(radLat2)    
                * Math.pow(Math.sin(b / 2), 2)));    
        s = s * EARTH_RADIUS;    
        s = Math.round(s * 10000d) / 10000d;    
        s = s;    
        return s;    
    }   
	
	
	// 路段匹配道路
	@RequestMapping(value = "/getSectionHaveRoad", method = RequestMethod.POST)
	@ResponseBody
	public String getSectionHaveRoad() throws Exception{
		List<DataSection> sectionList = dataSectionMapper.getSectionHaveRoad();
		return gson.toJson(sectionList);
	}
	
}
