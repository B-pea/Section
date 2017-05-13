package com.Section.dao;

import java.util.List;

import com.Section.model.PubRoadRoute;


public interface PubRoadRouteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PubRoadRoute record);

    int insertSelective(PubRoadRoute record);

    PubRoadRoute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PubRoadRoute record);

    int updateByPrimaryKey(PubRoadRoute record);

	PubRoadRoute getRoutAllSetion(String needChangeId);

	List<PubRoadRoute> updataRouteInfoNear(String ncId);

	List<PubRoadRoute> getRoutAll();

	List<PubRoadRoute> getRouteBySE(PubRoadRoute pubRoadRoute);

	void deleteBySET(PubRoadRoute pubRoadRoute);

	void updateSpeedByID(PubRoadRoute up_route);

	PubRoadRoute getSetionPolicy(PubRoadRoute setion_id);

}