package com.Section.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Section.dao.PubRoadRouteMapper;
import com.Section.model.PubRoadRoute;
import com.Section.model.PubRoadSetion;
import com.Section.service.PubRoadRouteService;

@Service("pubRoadRouteService")
public class PubRoadRouteServiceImpl implements PubRoadRouteService {
	
	@Autowired
	private PubRoadRouteMapper pubRoadRouteMapper;
	
	@Override
	public int insert(PubRoadRoute record) {
		// TODO Auto-generated method stub
		return pubRoadRouteMapper.insert(record);
	}

	@Override
	public PubRoadRoute getRoutAllSetion(String needChangeId) {
		// TODO Auto-generated method stub
		return pubRoadRouteMapper.getRoutAllSetion(needChangeId);
	}

	@Override
	public void updateByPrimaryKey(PubRoadRoute routall) {
		// TODO Auto-generated method stub
		pubRoadRouteMapper.updateByPrimaryKey(routall);
	}

	@Override
	public List<PubRoadRoute> updataRouteInfoNear(String ncId) {
		// TODO Auto-generated method stub
		return pubRoadRouteMapper.updataRouteInfoNear(ncId);
	}

	@Override
	public List<PubRoadRoute> getRoutAll() {
		// TODO Auto-generated method stub
		return pubRoadRouteMapper.getRoutAll();
	}

	@Override
	public List<PubRoadRoute> getRouteBySE(PubRoadRoute pubRoadRoute) {
		// TODO Auto-generated method stub
		return pubRoadRouteMapper.getRouteBySE(pubRoadRoute);
	}

	@Override
	public void deleteBySET(PubRoadRoute pubRoadRoute) {
		// TODO Auto-generated method stub
		pubRoadRouteMapper.deleteBySET(pubRoadRoute);
	}

	@Override
	public void updateSpeedByID(PubRoadRoute up_route) {
		// TODO Auto-generated method stub
		pubRoadRouteMapper.updateSpeedByID(up_route);
	}

	@Override
	public PubRoadRoute getSetionPolicy(PubRoadRoute setion_id) {
		// TODO Auto-generated method stub
		return pubRoadRouteMapper.getSetionPolicy(setion_id);
	}

	@Override
	public List<PubRoadRoute> getSiteCodeOld() {
		// TODO Auto-generated method stub
		return pubRoadRouteMapper.getSiteCodeOld();
	}

	@Override
	public List<PubRoadRoute> showRoute(String siteStart, String siteEnd) {
		// TODO Auto-generated method stub
		return pubRoadRouteMapper.showRoute(siteStart,siteEnd);
	}

	@Override
	public PubRoadRoute showRouteSite(String siteStart) {
		// TODO Auto-generated method stub
		return pubRoadRouteMapper.showRouteSite(siteStart);
	}

	@Override
	public void deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		pubRoadRouteMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PubRoadRoute getRouteBySetion(PubRoadSetion setion) {
		// TODO Auto-generated method stub
		return pubRoadRouteMapper.getRouteBySetion(setion);
	}

}
