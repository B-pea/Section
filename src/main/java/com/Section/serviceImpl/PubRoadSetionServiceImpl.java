package com.Section.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Section.dao.PubRoadRouteMapper;
import com.Section.dao.PubRoadSetionMapper;
import com.Section.model.PubRoadSetion;
import com.Section.service.PubRoadSetionService;

@Service("pubRoadSetionService")
public class PubRoadSetionServiceImpl implements PubRoadSetionService {

	@Autowired
	private PubRoadSetionMapper pubRoadSetionMapper;
	
	@Override
	public int insert(PubRoadSetion record) {
		// TODO Auto-generated method stub	
		return pubRoadSetionMapper.insert(record);
	}

	@Override
	public List<Integer> selectPubRoadSetionBySize(Integer size) throws Exception {
		// TODO Auto-generated method stub
		return pubRoadSetionMapper.selectPubRoadSetionBySize(size);
	}

	@Override
	public List<PubRoadSetion> getRoutAll()  {
		// TODO Auto-generated method stub
	    return pubRoadSetionMapper.getRoutAll();
	}

	@Override
	public PubRoadSetion selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return pubRoadSetionMapper.selectByPrimaryKey(id);
	}

	@Override
	public void deleteByPrimaryKey(int id) {
		// TODO Auto-generated method stub
		pubRoadSetionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateRailwayID(PubRoadSetion setion) {
		// TODO Auto-generated method stub
		pubRoadSetionMapper.updateRailwayID(setion);
	}

	@Override
	public List<PubRoadSetion> getroutMated() {
		// TODO Auto-generated method stub
		return pubRoadSetionMapper.getroutMated();
	}

	@Override
	public void updateSection(PubRoadSetion setion) {
		// TODO Auto-generated method stub
		pubRoadSetionMapper.updateSection(setion);
	}

	@Override
	public List<PubRoadSetion> getAllLinePoint() {
		// TODO Auto-generated method stub
		return pubRoadSetionMapper.getAllLinePoint();
	}

	@Override
	public void updateLinePoint(PubRoadSetion setion) {
		// TODO Auto-generated method stub
		pubRoadSetionMapper.updateLinePoint(setion);
	}

	@Override
	public PubRoadSetion getSingleLinePoint(PubRoadSetion slq) {
		// TODO Auto-generated method stub
		return pubRoadSetionMapper.getSingleLinePoint(slq);
	}

	@Override
	public void updateOwnArea(PubRoadSetion setion) {
		// TODO Auto-generated method stub
		pubRoadSetionMapper.updateOwnArea(setion);
	}

	@Override
	public void updateByPrimaryKeySelective(PubRoadSetion setion) {
		// TODO Auto-generated method stub
		pubRoadSetionMapper.updateByPrimaryKeySelective(setion);
	}

	@Override
	public List<PubRoadSetion> getRoutAllEmptyPoints() {
		// TODO Auto-generated method stub
		return pubRoadSetionMapper.getRoutAllEmptyPoints();
	}

	@Override
	public List<PubRoadSetion> getRoutAllWithoutPoints() {
		// TODO Auto-generated method stub
		return pubRoadSetionMapper.getRoutAllWithoutPoints();
	}


	
	
}
