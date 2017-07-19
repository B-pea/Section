package com.Section.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Section.model.Site;
import com.Section.model.walker;
import com.Section.service.SiteService;
import com.Section.service.WalkerService;

@Controller
@RequestMapping("/walker")
public class WalkerController {

	private static Logger logger = Logger.getLogger(WalkerController.class);
	
	@Autowired
	private WalkerService WalkerService;
	
	/**
	 * 插入walker记录
	 * @param walker
	 * @param request
	 * @throws Exception
	 * @throws IOException
	 */
	@RequestMapping(value = "/insertSelective", method = RequestMethod.POST)
	@ResponseBody
	public String insertSelective(walker walker,HttpServletRequest request) throws Exception, IOException{
		WalkerService.insertSelective(walker);
		return "1";
	}
	
	@RequestMapping(value = "/getPersonTail", method = RequestMethod.POST)
	@ResponseBody
	public  Map<String, Object> getPersonTail(walker walker,HttpServletRequest request) throws Exception, IOException{
		Map<String,Object> reMap= new HashMap<String,Object>();
		List<walker> walkerTail = WalkerService.selectWalkerTailAll();
		reMap.put("walker", walkerTail);
		return reMap;
	}
	
}
