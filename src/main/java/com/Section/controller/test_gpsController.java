package com.Section.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Section.model.Road;
import com.Section.model.test_gps;
import com.Section.service.RoadService;
import com.Section.service.test_gpsService;
import com.google.gson.Gson;

@Controller
@RequestMapping("/test_gps")
public class test_gpsController {
	
	@Autowired
	private test_gpsService test_gps_service;
	
	Gson mapper = new Gson();
	
	@RequestMapping(value="/getAlltest_gps")
	@ResponseBody
	public String getAlltest_gps(HttpServletRequest request,HttpServletResponse response){
		List<test_gps> list = test_gps_service.getAlltest_gps();
		return mapper.toJson(list);
	}
	
	@RequestMapping(value="/updateByPrimaryKeySelective")
	@ResponseBody
	public int updateByPrimaryKeySelective(test_gps test_gps,HttpServletRequest request,HttpServletResponse response){
		int a = test_gps_service.updateByPrimaryKeySelective(test_gps);
		return a;
	}
	
	@RequestMapping(value="/selectBySomething")
	@ResponseBody
	public String selectBySomething(test_gps test_gps,HttpServletRequest request,HttpServletResponse response){
		List<test_gps> list = test_gps_service.selectBySomething(test_gps);
		return mapper.toJson(list);
	}
	
	
}
