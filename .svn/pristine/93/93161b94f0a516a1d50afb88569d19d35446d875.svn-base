package com.Section.dao;

import java.util.List;
import java.util.Map;

import com.Section.model.RoadRailway;

public interface RoadRailwayDao {
    int deleteByPrimaryKey(String id);

    int insert(RoadRailway record);

    int insertSelective(RoadRailway record);

    RoadRailway selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoadRailway record);

    int updateByPrimaryKey(RoadRailway record);

    /**
	 * Description: 根据道路ID获取道路路段列表(用于下拉列表)
     * @param condMap 
	 * @param 站点ID
	 * @return 返回站点对应的公路列表
	 * @throws Exception
	 * @see Note:
	 */
	List<RoadRailway> getRoadRailwayByRoadId(Map<String, Object> condMap);
	
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
	List<RoadRailway> getRoadRailwayByName(String railwayName);
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
     * @param condMap 
	 * @param 站点ID
	 * @return 返回站点对应的公路列表
	 * @throws Exception
	 * @see Note:
	 */
	List<RoadRailway> getRoadRailwayPage(Map<String, Object> condMap);

	void updateLinePointRailway(RoadRailway railway);

	List<RoadRailway> getAllRoadRailwayLinePoint();

	void updateLinePointRailwayBack(RoadRailway railway);
}