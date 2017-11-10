package com.Section.service;

import java.util.List;
import com.Section.model.PubRoadRoute;
import com.Section.model.PubRoadSetion;

public interface PubRoadRouteService {
	int insert(PubRoadRoute record);

	PubRoadRoute getRoutAllSetion(String ncId);

	void updateByPrimaryKey(PubRoadRoute routall);

	List<PubRoadRoute> updataRouteInfoNear(String ncId);

	List<PubRoadRoute> getRoutAll();

	List<PubRoadRoute> getRouteBySE(PubRoadRoute pubRoadRoute);

	void deleteBySET(PubRoadRoute pubRoadRoute);

	void updateSpeedByID(PubRoadRoute up_route);

	PubRoadRoute getSetionPolicy(PubRoadRoute temp);

	List<PubRoadRoute> getSiteCodeOld();

	List<PubRoadRoute> showRoute(String siteStart, String siteEnd);

	PubRoadRoute showRouteSite(String siteStart);

	void deleteByPrimaryKey(Integer id);

	PubRoadRoute getRouteBySetion(PubRoadSetion setion);


}
