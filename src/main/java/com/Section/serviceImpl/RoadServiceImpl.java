package com.Section.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Section.dao.RoadDao;
import com.Section.model.Road;
import com.Section.service.RoadService;
import com.Section.util.PageParameter;

/**
 * @Description:
 * @Author: zhangyongyou
 * @Version: 1.0
 * @Create Date Time: 2016年11月28日 上午9:15:57.
 * @Update Date Time:
 * @see com.wanji.hwms.service.RoadService
 */
@Service("roadService")
public class RoadServiceImpl implements RoadService {
	
	private static Logger logger = Logger.getLogger(RoadRailwayServiceImpl.class);
	
	@Autowired
	private RoadDao roadDao;

	@Override
	public int addRoad(Road road) {
		return roadDao.addRoad(road);
	}

	@Override
	public int deleteRoad(Road road) {
		return roadDao.deleteRoad(road);
	}

	@Override
	public int updateRoad(Road road) {
		return roadDao.updateRoad(road);
	}

	@Override
	public List<Road> getAllRoad(String name) {
		return roadDao.getAllRoad(name);
	}
	
	@Override
	public List<Road> getRoadList(Map condMap) {
		return roadDao.getRoadList(condMap);
	}

	@Override
	public Road findById(String id) {
		return roadDao.findById(id);
	}
	
	@Override
	public PageParameter getListByPage(Road road, int pageNO, int pageSize) throws Exception {
		try {
			PageParameter page = new PageParameter();
			page.setCurrentPage(pageNO);
			page.setPageSize(pageSize);
			
			Map<String, Object> condMap = new HashMap<String, Object>();
			condMap.put("name", road.getName());
			condMap.put("roadCode", road.getRoadCode());
			condMap.put("page", page); //分页参数
			
			List<Road> list =this.roadDao.getListByPage(condMap);
			page.setList(list);
			return page;
		} catch (Exception e) {
			logger.error("错误信息："+ e);
			return null;
		}
	}

	@Override
	public Road selectBySiteId(String siteId) {
		return roadDao.selectBySiteId(siteId);
	}

	@Override
	public Road findDetail(String id) {
		
		return roadDao.findDetail(id);
	}

	@Override
	public List<Road> selectByRoadCode(String roadCode) {
		return roadDao.selectByRoadCode(roadCode);
	}

	@Override
	public List<String> selectRoadCodeByAll() {
		return roadDao.selectRoadCodeByAll();
	}

	@Override
	public List<String> selectRoadPointByCode(String code) {
		return roadDao.selectRoadPointByCode(code);
	}

	@Override
	public List<String> selectRoadIdByCode(String code) {
		return roadDao.selectRoadIdByCode(code);
	}
}