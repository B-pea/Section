package com.Section.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.Section.model.Organization;
import com.Section.service.OperatorService;
import com.Section.service.OrganizationService;
import com.Section.util.Common;
import com.Section.util.Constants;
import com.Section.util.TypeConversion;

/**
 * 
 * @Description:机构管理控制器
 * @Author: lizhiyan
 * @Version: 1.0
 * @Create Date Time: 2016年11月23日 下午5:35:59.
 * @Update Date Time:
 * @see
 */
@Controller
@RequestMapping("/orgs")
public class OrganizationController {
	@Autowired
	private OrganizationService organizationService;
	@Autowired
	private OperatorService operatorService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getIndex() {
		return "jsp/system/org/index";
	}

	@ResponseBody
	@RequestMapping(value = "/indexlist", method = RequestMethod.POST)
	public Map<String, Object> postIndex() {
		Map<String, Object> map = new HashMap<String, Object>();
		Session session = SecurityUtils.getSubject().getSession();
		String orgcode = session.getAttribute(Constants.ORGCODE) + "";
		Organization org = organizationService.selectByPrimaryKey(orgcode);
		map.put("orgid", org.getId());
		map.put("status", "0");
		map.put("category", "");
		map.put("page", 0);
		map.put("size", Integer.MAX_VALUE);
		List<Organization> orgs = organizationService.selectByMap(map);
		List<Map<String, Object>> lists = new ArrayList<>();
		Map<String, Object> orgMap = null;
		for (Organization o : orgs) {
			orgMap = new HashMap<>();
			orgMap.put("id", o.getId());
			orgMap.put("pId", o.getPid());
			orgMap.put("code", o.getOrgcode());
			orgMap.put("name", o.getOrgname());
			orgMap.put("open", true);
			lists.add(orgMap);
		}
		Map<String,Object> back=new HashMap<>();
		back.put("list", lists);
		back.put("id", org.getId());
		return back;
	}

	@ResponseBody
	@RequestMapping(value = "/details", method = RequestMethod.POST)
	public Map<String, Object> details(HttpServletRequest request) {
		String sid = request.getParameter("id");
		String pageSize = request.getParameter("pageSize");
		String currentPage = request.getParameter("currentPage");
		int page = 0, size = 10;
		if (!Common.isEmpty(pageSize) || !Common.isEmpty(currentPage)) {
			page = TypeConversion.strToInt(currentPage);
			size = TypeConversion.strToInt(pageSize);
			page = (page - 1) * size;
			page = page >= page ? page : 0;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		if (Common.isEmpty(sid)) {
			Session session = SecurityUtils.getSubject().getSession();
			String orgcode = session.getAttribute(Constants.ORGCODE) + "";
			Organization org = organizationService.selectByPrimaryKey(orgcode);
			sid = org.getId() + "";
		}
		map.put("orgid", sid);
		map.put("status", "0");
		map.put("category", "");
		map.put("page", page);
		map.put("size", size);
		List<Organization> orgs = organizationService.selectByMap(map);
		map.put("page", 0);
		map.put("size", Integer.MAX_VALUE);
		List<Organization> totalList = organizationService.selectByMap(map);
		Map<String, Object> back=new HashMap<>();
		back.put("list", orgs);
		back.put("total", totalList.size());
		back.put("size", size);
		return back;
	}

	@ResponseBody
	@RequestMapping(value = "/onedetail", method = RequestMethod.POST)
	public Map<String, Object> onedetail(String id) {
		Map<String, Object> map = new HashMap<>();
		if (Common.isEmpty(id)) {
			map.put("error", "参数为空");
			return map;
		}
		Organization org = organizationService.selectByPrimaryKey(id);
		if (org == null) {
			map.put("error", "查询结果为空");
		}
		map.put("org", org);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String edit(@ModelAttribute Organization organization) {
		Gson gson = new Gson();
		if (organization == null) {
			return gson.toJson("参数为空");
		}
		String checkStatus = checkStatus(organization);// 这是修改状态的情况
		if (checkStatus != null) {
			return gson.toJson(checkStatus);
		}
		String createOneMsg = organizationService.updateOneTable(organization);
		return gson.toJson(createOneMsg);
	}

	private String checkStatus(Organization organization) {
		String orgname = organization.getOrgname();
		if (Common.isEmpty(orgname)) {
			return "机构名不能为空";
		}
		if ("1".equals(organization.getStatus())) {// 第一，查询当前机构中是否有用户，第二查询当前机构是否有下级机构，这两者满足其一都不删除
			String orgcode = organization.getOrgcode();
			Session session = SecurityUtils.getSubject().getSession();
			String sorgcode = session.getAttribute(Constants.ORGCODE) + "";
			if (sorgcode.equals(orgcode) || sorgcode == orgcode) {
				return "禁止修改当前机构状态";
			} else if ("4".equals(organization.getCategory())) {
				return "禁止修改省级机构的状态";
			} else {
				Map<String, Object> map = new HashMap<>();
				map.put("orgcode", orgcode);
				map.put("category", null);
				map.put("page", 0);
				map.put("size", Integer.MAX_VALUE);
				List<Organization> orgList = organizationService.selectOneByMap(map);
				if (orgList != null && orgList.size() > 0) {
					return "下属机构不为空，不能禁用此机构";
				}
				int size = operatorService.selectOperatorByOrgcode(orgcode);
				if (size > 0) {
					return "当前机构的用户不为空，不能禁用此机构";
				}
				return null;
			}
		} else {
			return null;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String add(@ModelAttribute Organization organization) {
		Gson gson = new Gson();
		if (organization == null) {
			return gson.toJson("参数为空");
		}
		String createOneMsg = organizationService.createOneTable(organization);
		return gson.toJson(createOneMsg);
	}

	@ResponseBody
	@RequestMapping(value = "/isexit", method = RequestMethod.POST)
	public String isexit(String orgisexit, Boolean flag) {
		Gson gson = new Gson();
		if (Common.isEmpty(orgisexit)) {
			return gson.toJson("参数为空");
		} else {
			Organization organization = organizationService.selectByPrimaryKey(orgisexit);
			if (organization != null) {
				if (flag) {
					return gson.toJson("机构代码不能重复");
				} else {
					return gson.toJson("机构名称不能重复");
				}
			} else {
				if (flag) {
					return gson.toJson("机构代码可用");
				} else {
					return gson.toJson("机构名称可用");
				}
			}
		}
	}
	
	// 根据机构ID 获取机构名称
	@ResponseBody
	@RequestMapping(value = "/getOrgNameByCode", method = RequestMethod.POST)
	public String getOrgNameByCode(String orgCode) {
		Organization org = organizationService.getOrgNameByCode(orgCode);
		Gson gson = new Gson();
		String aa = gson.toJson(org);
		return aa;
	}
	
	// 根据机构ID 获取机构名称
		@ResponseBody
		@RequestMapping(value = "/getAllName", method = RequestMethod.POST)
		public String getAllName() {
			List<Organization> orgs = organizationService.getAllName();
			Gson gson = new Gson();
			String aa = gson.toJson(orgs);
			return aa;
		}
}