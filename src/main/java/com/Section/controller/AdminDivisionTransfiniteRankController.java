package com.Section.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.Section.util.BigDataInterFacesClient;


/**
 * @Description:行政区划超限运输排名
 * @Author: lizhiyan
 * @Version: 1.0
 * @Create Date Time: 2017年2月17日 下午2:18:32.
 * @Update Date Time:
 * @see
 */
@Controller
@RequestMapping("/adminDivisionTransfiniteRank")
public class AdminDivisionTransfiniteRankController {
	private static Logger logger = Logger.getLogger(AdminDivisionTransfiniteRankController.class);
	
	/**
	 * Description: 行政区域超限运输统计页面跳转
	 * @return
	 * @return String
	 * @throws Exception
	 * @see Note:
	 */
	@RequestMapping(value = "/adminDivisionTransfiniteRankList", method = RequestMethod.GET)
	public String adminDivisionTransfiniteRankList() {

		return "jsp/statistic/adminDivisionTransfiniteRankList";
	}
	/**
	* 行政区域超限运输统计报表查询
	* @Title: adminDivisionTransfiniteRankListByDivision 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param @param orgCode
	* @param @param startTime
	* @param @param endTime
	* @param @param request
	* @param @param response    设定文件 
	* @return void    返回类型 
	* @throws
	*/
	@RequestMapping(value = "/adminDivisionTransfiniteRankListByDivision")
	@ResponseBody
	public void adminDivisionTransfiniteRankListByDivision(String orgCode,String startTime,String endTime, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String FORM_ACTION2 = "http://192.168.100.11:8087/baseinfo"; //请求地址
			Gson gson = new Gson();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("report", "administrative_divisions_rankings");
			map.put("start_time", startTime.replace("-", ""));
			map.put("end_time", endTime.replace("-", ""));
			if(orgCode != null){
				String code=orgCode.substring(orgCode.length()-4,orgCode.length());//截取机构代码后4位
				if("000000".equals(orgCode)){ //查询省
					map.put("sql_num", "sql1");
					map.put("province_code", orgCode);
				}else if("0000".equals(code)){ //查询市
					map.put("sql_num", "sql2");
					map.put("city_code", orgCode);
					}else{  //查询区
					map.put("sql_num", "sql3");
					map.put("area_code", orgCode);
				}
			}
			String parms = gson.toJson(map);
			
			String bigData = BigDataInterFacesClient.requestStatisPost(FORM_ACTION2, parms);
			response.getWriter().write(bigData);

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("错误信息" + e.getMessage());
		} finally {
			logger.info("--------adminDivisionTransfiniteRankListByDivision----------");
		}
	}	
}
