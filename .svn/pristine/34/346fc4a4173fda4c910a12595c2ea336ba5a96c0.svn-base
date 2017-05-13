package com.Section.serviceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Section.dao.RoadRailwayDao;
import com.Section.model.RoadRailway;
import com.Section.service.RoadRailwayService;
import com.Section.util.PageParameter;
/**
 * @Description: 公路路段service接口 实现
 * @Author: jianglibo
 * @Version: 1.0
 * @Create Date Time: 2017年1月10日 上午9:36:25
 * @Update Date Time: 
 * @see 
 */
@Service("roadRailwayService")
public class RoadRailwayServiceImpl implements RoadRailwayService{

	@Autowired
	private RoadRailwayDao roadRailwayDao;
	
	@Override
	public int deleteByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insert(RoadRailway record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertSelective(RoadRailway record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public RoadRailway selectByPrimaryKey(String id) {
		// TODO Auto-generated method stub
		return roadRailwayDao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RoadRailway record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateByPrimaryKey(RoadRailway record) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<RoadRailway> getRoadRailwayByRoadId(String roadId) {
		// TODO Auto-generated method stub
		Map<String, Object> condMap = new HashMap<String, Object>();
		condMap.put("roadId", roadId);
		return roadRailwayDao.getRoadRailwayByRoadId(condMap);
	}

	@Override
	public List<RoadRailway> getRoadRailway() {
		// TODO Auto-generated method stub
		return roadRailwayDao.getRoadRailway();
	}

	@Override
	public PageParameter getRoadRailwayList(Map<String, Object> condMap, int pageNO, int pageSize) {
		// TODO Auto-generated method stub
		try {
			PageParameter page = new PageParameter();
			page.setCurrentPage(pageNO);
			page.setPageSize(pageSize);
			
			condMap.put("page", page); //分页参数
			List<RoadRailway> list =this.roadRailwayDao.getRoadRailwayPage(condMap);
			for (RoadRailway roadRailwayList:list) {
				String lastRailway = roadRailwayList.getLastRailway();
				if(lastRailway!=null&&lastRailway.length()!=0){
					String[] lastRailwayArray=lastRailway.split(",");
					//RoadRailway lastRailwayTemp = new RoadRailway();
					String temp="";
					for(int i=0;i<lastRailwayArray.length;i++){
						temp=temp+roadRailwayDao.selectByPrimaryKey(lastRailwayArray[i]).getRailwayName();
						if(i!=lastRailwayArray.length-1){
							temp=temp+",";
						}
					}
					roadRailwayList.setLastRailway(temp);
				}
			}
			for(RoadRailway roadRailwayList:list){
				String nextRailway = roadRailwayList.getNextRailway();
				if(nextRailway!=null&&nextRailway.length()!=0){
					String[] nextRailwayArray =nextRailway.split(",");
					String temp="";
					for(int i=0 ; i<nextRailwayArray.length;i++){
						temp=temp+roadRailwayDao.selectByPrimaryKey(nextRailwayArray[i]).getRailwayName();
						if(i!=nextRailwayArray.length-1){
							temp = temp+",";
						}
					}
					roadRailwayList.setNextRailway(temp);
				}
				
			}
			page.setList(list);
			return page;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<RoadRailway> getRoadRailwayByName(String roadPoint) {
		// TODO Auto-generated method stub
		return roadRailwayDao.getRoadRailwayByName(roadPoint);
	}

	@Override
	public void updateLinePointRailway(RoadRailway railway) {
		// TODO Auto-generated method stub
		roadRailwayDao.updateLinePointRailway(railway);
	}

	@Override
	public List<RoadRailway> getAllRoadRailwayLinePoint() {
		// TODO Auto-generated method stub
		return roadRailwayDao.getAllRoadRailwayLinePoint();
	}

	@Override
	public void updateLinePointRailwayBack(RoadRailway railway) {
		// TODO Auto-generated method stub
		roadRailwayDao.updateLinePointRailwayBack(railway);
	}
}