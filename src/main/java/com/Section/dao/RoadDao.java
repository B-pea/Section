package com.Section.dao;

import java.util.List;
import java.util.Map;

import com.Section.model.Road;

/**
 * @ClassName: RoadDao
 * @Description: 公路信息登记dao
 * @author shijin
 * @date 2016年11月17日 下午2:04:18
 * 
 */
public interface RoadDao {
	int addRoad(Road road);

	int deleteRoad(Road road);

	int updateRoad(Road road);

	List<Road> getAllRoad(String name);

	List<Road> getRoadList(Map condMap);

	Road findById(String id);

	Road findDetail(String id);

	//String getPager(Map condMap, int pageIndex, int pageSize) throws Exception;
	public List<Road> getListByPage(Map condMap);

	/**
	 * Description: 详情解说请参照服务接口说明
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 * @see Note:
	 */
	Road selectBySiteId(String siteId);

	/**
	 * Description: 输入公路代码获取公路信息
	 * 
	 * @param
	 * @return
	 * @throws Exception
	 * @see Note:
	 */
	List<Road> selectByRoadCode(String roadCode);

	/**
	 * @Description:查询出公路信息表中所有的公路code
	 * @param:无
	 * @return:返回公路code的list集合
	 * @throws Exception
	 * @see Note:
	 */
	List<String> selectRoadCodeByAll();
	/**
	 * @Description:根据公路code 查询出公路的点
	 * @param:公路code
	 * @return:返回公路code对应的点集合
	 * @throws Exception
	 * @see Note:
	 */
	List<String> selectRoadPointByCode(String code);
	
	/**
	 * 
		 * Description:根据公路code查询出公路ID 
		 * @param 公路code
		 * @return
		 * @return List<String>公路ID的集合
		 * @throws Exception
		 * @see Note:
	 */
	List<String> selectRoadIdByCode(String code);

	Road getLastRoadById();
}