package com.Section.service;

import java.util.List;
import java.util.Map;

import com.Section.model.RoadRailway;
import com.Section.util.PageParameter;

/**
 * @Description: 公路路段service接口
 * @Author: jianglibo
 * @Version: 1.0
 * @Create Date Time: 2017年1月10日 上午9:31:35
 * @Update Date Time: 
 * @see 
 */
public interface RoadRailwayService {

	int deleteByPrimaryKey(String id);

    int insert(RoadRailway record);

    int insertSelective(RoadRailway record);

    RoadRailway selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoadRailway record);

    int updateByPrimaryKey(RoadRailway record);
    
    /**
	 * Description: 根据道路ID获取道路路段列表(用于下拉列表)
     * @param roadId 
	 * @param 站点ID
	 * @return 返回站点对应的公路信息
	 * @throws Exception
	 * @see Note:
	 */
	List<RoadRailway> getRoadRailwayByRoadId(String roadId);
	/**
	 * Description: 获取所有的路段
     * @param roadId 
	 * @param 站点ID
	 * @return 返回站点对应的公路信息
	 * @throws Exception
	 * @see Note:
	 */
	List<RoadRailway> getRoadRailway();
	

	/**
	 * Description: 根据道路ID获取道路路段列表(用于page显示)
     * @param roadId 
	 * @param 站点ID
	 * @return 返回站点对应的公路信息
	 * @throws Exception
	 * @see Note:
	 */
	PageParameter getRoadRailwayList(Map<String, Object> condMap, int intPage, int intPageSize);
	
	/**
	 * 
		* 
		* @Title: getRoadRailwayByName 
		* @Description: TODO  根据输入的路段名称查找路段 
		* @param @param roadPoint
		* @param @return
		* @param @throws Exception    设定文件 
		* @return String    返回类型 
		* @throws
	 */
	List<RoadRailway> getRoadRailwayByName(String roadPoint);

	void updateLinePointRailway(RoadRailway railway);

	List<RoadRailway> getAllRoadRailwayLinePoint();

	void updateLinePointRailwayBack(RoadRailway railway);
}
