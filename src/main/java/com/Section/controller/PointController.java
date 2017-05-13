package com.Section.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.Section.model.Road;
import com.Section.model.RoadRailway;
import com.Section.service.RoadRailwayService;
import com.Section.service.RoadService;

@Controller
@RequestMapping("/point")
public class PointController {
	@Autowired
	private RoadRailwayService roadRailwayService;
	@Autowired
	private RoadService roadService;

	/**
	 * 将前端传回的数值存入数据库
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertPoint", method = RequestMethod.POST)
	@ResponseBody
	public void insertPoint(String jsonmodel, String output, String addComp, HttpServletRequest request)
			throws Exception {
		jsonmodel = "{" + "G330-5" + ":" + jsonmodel + "}";
		System.out.println("前端传入后端的jsonmodel:" + jsonmodel);
		System.out.println("前端传入后端的output:" + output);
		System.out.println("前端传入后端的addComp:" + addComp);
		System.out.println("前端传入后端的output:" + output);
		/*
		 * Gson gson = new Gson(); Map<String, Object> map =
		 * gson.fromJson(jsonmodel, Map.class); Set<String> keySet =
		 * map.keySet(); for (String key : keySet) { ArrayList<Double> list =
		 * (ArrayList<Double>) map.get(key); WjJwd wjJwd = new WjJwd();
		 * wjJwd.setCurrentLongitude(list.get(1));
		 * wjJwd.setCurrentLatitude(list.get(0)); ArrayList<Double> list1 =
		 * (ArrayList<Double>) map.get((Integer.parseInt(key)+1)+""); if(null !=
		 * list1.get(0)){ wjJwd.setNextLongitude(list1.get(1));
		 * wjJwd.setNextLatitude(list1.get(0)); }
		 * wjJwd.setRoadCode("台州下岭站点-丽水下风化站点-6"); wjJwdService.insert(wjJwd); }
		 */
		/*
		 * WjDistance wjDistance = new WjDistance();
		 * wjDistance.setDistiance((Integer.parseInt(output)));
		 * wjDistanceService.insert(wjDistance);
		 */
		OutputStreamWriter osw = new OutputStreamWriter(
		new FileOutputStream("C:/Users/Administrator/Desktop/GIS路线数据/JSON文件/Gis.json", true));
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write(jsonmodel);
		bw.flush();
		bw.close();
	}

	/**
	 * 将数据传到前端
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findPoint", method = RequestMethod.POST)
	@ResponseBody
	public String findPoint(String roadCode, String roadPoint, HttpServletRequest request) throws Exception {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		System.out.println(basePath);
		String path1 = request.getSession().getServletContext().getRealPath("");
		System.out.println(path1);
		System.out.println(roadPoint);
		System.out.println(roadCode);
		List<Road> roadList = roadService.selectByRoadCode(roadCode);
		List<RoadRailway> roadRailwayListAll = new ArrayList<RoadRailway>();
		for (int i = 0; i < roadList.size(); i++) {
			List<RoadRailway> roadRailwayList = roadRailwayService.getRoadRailwayByRoadId(roadList.get(i).getId());
			roadRailwayListAll.addAll(roadRailwayList);
		}
		RoadRailway roadRailway = new RoadRailway();
		Set<String> h = new HashSet<String>();
		for (int i = 0; i < roadRailwayListAll.size(); i++) {
			roadRailway = roadRailwayListAll.get(i);
			if(roadPoint == null || "".equals(roadPoint)){
				h.add(roadRailway.getRailwayName());
			}else{
				h.add(roadPoint);
			}
		}
		String file = roadRailway.getUrl();
		BufferedReader bre = new BufferedReader(new FileReader(path1 + "/" + file));
		String str;
		String str1 = "";
		StringBuffer sb = new StringBuffer("{");
		str = bre.readLine();
		while (str != null) {// 判断最后一行不存在，为空结束循环
			int num = str.indexOf('}') + 1;
			str1 = str.substring(1, num);
			sb.append(str1);
			// System.out.println(str1);
			str = bre.readLine();
			if (str != null) {
				sb.append(',');
			}
		}
		;
		bre.close();
		sb.append('}');
		String sb1 = sb.toString();
		// System.out.println(sb1);
		Gson gson = new Gson();
		String json = gson.toJson(sb1);
		Map<String, Object> fromJson = gson.fromJson(sb1, Map.class);
		/*
		 * Set<String> keySet = fromJson.keySet(); System.out.println(keySet);
		 */
		Iterator<String> iterator = h.iterator();
		/* Map<String, Object> */
		List<Object> list = new ArrayList<>();
		while (iterator.hasNext()) {
			Object object = (Object) iterator.next();
			// System.out.println(gson.toJson(fromJson.get(object)));
			list.add(gson.toJson(fromJson.get(object)));
		}
		return gson.toJson(list);
	}

	/**
	 * 显示出所有的路线
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findPointAll", method = RequestMethod.POST)
	@ResponseBody
	public String findPointAll(HttpServletRequest request) throws Exception {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path
				+ "/";
		System.out.println(basePath);
		String path1 = request.getSession().getServletContext().getRealPath("");
		System.out.println(path1);
		List<RoadRailway> roadRailwayList = roadRailwayService.getRoadRailway();
		RoadRailway roadRailway = new RoadRailway();
		Set<String> h = new HashSet<String>();
		for (int i = 0; i < roadRailwayList.size(); i++) {
			roadRailway = roadRailwayList.get(i);
			h.add(roadRailway.getRailwayName());
		}
		String file = roadRailway.getUrl();
		BufferedReader bre = new BufferedReader(new FileReader(path1 + "/" + file));
		String str;
		String str1 = "";
		StringBuffer sb = new StringBuffer("{");
		str = bre.readLine();
		while (str != null) {// 判断最后一行不存在，为空结束循环
			int num = str.indexOf('}') + 1;
			str1 = str.substring(1, num);
			sb.append(str1);
			// System.out.println(str1);
			str = bre.readLine();
			if (str != null) {
				sb.append(',');
			}
		}
		;
		bre.close();
		sb.append('}');
		String sb1 = sb.toString();
		// System.out.println(sb1);
		Gson gson = new Gson();
		String json = gson.toJson(sb1);
		Map<String, Object> fromJson = gson.fromJson(sb1, Map.class);
		/*
		 * Set<String> keySet = fromJson.keySet(); System.out.println(keySet);
		 */
		Iterator<String> iterator = h.iterator();
		/* Map<String, Object> */
		List<Object> list = new ArrayList<>();
		while (iterator.hasNext()) {
			Object object = (Object) iterator.next();
			// System.out.println(gson.toJson(fromJson.get(object)));
			list.add(gson.toJson(fromJson.get(object)));
		}
		list.addAll(roadRailwayList);
		return gson.toJson(list);
	}
	
	/**
	 * 获取所有路段的所有记录
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAllRoadRailway", method = RequestMethod.POST)
	@ResponseBody
	public String getAllRoadRailway(HttpServletRequest request) throws Exception {
		List<RoadRailway> roadRailwayList = roadRailwayService.getRoadRailway();
		Gson gson = new Gson();
		return gson.toJson(roadRailwayList);
	}
	
	/**
	 * 获得所有线路的id和line_point
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAllRoadRailwayLinePoint", method = RequestMethod.POST)
	@ResponseBody
	public String getAllRoadRailwayLinePoint(HttpServletRequest request) throws Exception {
		List<RoadRailway> roadRailwayList = roadRailwayService.getAllRoadRailwayLinePoint();
		Gson gson = new Gson();
		return gson.toJson(roadRailwayList);
	}
	
	/**
	 * 更新当前id线路的line_point，即正向路线点集
	 * @param id
	 * @param line_point
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateLinePointRailway", method = RequestMethod.POST)
	@ResponseBody
	public void updateLinePointRailway(String id,String line_point,HttpServletRequest request) throws Exception {
		RoadRailway railway = new RoadRailway();
		railway.setId(id);
		railway.setLine_point(line_point);
		roadRailwayService.updateLinePointRailway(railway);
	}
	
	/**
	 * 更新当前id线路的line_point_back，即反向路线点集
	 * @param id
	 * @param line_point_back
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateLinePointRailwayBack", method = RequestMethod.POST)
	@ResponseBody
	public void updateLinePointRailwayBack(String id,String line_point_back,HttpServletRequest request) throws Exception {
		RoadRailway railway = new RoadRailway();
		railway.setId(id);
		railway.setLine_point_back(line_point_back);
		roadRailwayService.updateLinePointRailwayBack(railway);
	}

	@RequestMapping(value = "/roadCode", method = RequestMethod.POST)
	@ResponseBody
	public List<String> getRoad() {
		List<String> all = roadService.selectRoadCodeByAll();
		return all;
	}

	@RequestMapping(value = "/roadCodePoint", method = RequestMethod.POST)
	@ResponseBody
	public List<String> getRoadPoint(String code) {
		List<String> allPoint = roadService.selectRoadPointByCode(code);
		return allPoint;
	}
	/**
		* @Title: administrativeReviewList 
		* @Description: TODO行政区域划分
		* @param @return
		* @return String    返回类型 
		* @throws
	 */
	@RequestMapping(value = "/administrativeregion", method = RequestMethod.GET)
	public String administrativeregion() {
		return "GIS/administrative_region";
	}
	/**
		* @Title: administrativeReviewList 
		* @Description: TODO超限公路
		* @param @return
		* @return String    返回类型 
		* @throws
	 */
	@RequestMapping(value = "/gisinsertpoint", method = RequestMethod.GET)
	public String gisinsertpoint() {
		return "GIS/gis_insertpoint";
	}
	/**
		* @Title: administrativeReviewList 
		* @Description: TODO站点分布
		* @param @return
		* @return String    返回类型 
		* @throws
	 */
	@RequestMapping(value = "/guiZhouRoadPart", method = RequestMethod.GET)
	public String guiZhouRoadPart() {
		return "GIS/ZhejiangSiteDistribution";
	}
}