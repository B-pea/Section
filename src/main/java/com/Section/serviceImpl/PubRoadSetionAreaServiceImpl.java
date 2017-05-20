package com.Section.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Section.dao.PubRoadSetionAreaMapper;
import com.Section.model.PubRoadSetionArea;
import com.Section.service.PubRoadSetionAreaService;

@Service("pubRoadSetionAreaService")
public class PubRoadSetionAreaServiceImpl implements PubRoadSetionAreaService{

	@Autowired
	private PubRoadSetionAreaMapper pubRoadSetionAreaMapper;
	
	@Override
	public int insertSetionArea(PubRoadSetionArea area) {
		// TODO Auto-generated method stub
		return pubRoadSetionAreaMapper.insertSetionArea(area);
	}

}
