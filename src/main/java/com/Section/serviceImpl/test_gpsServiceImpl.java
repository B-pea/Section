package com.Section.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Section.dao.SiteDao;
import com.Section.dao.test_gpsMapper;
import com.Section.model.test_gps;
import com.Section.service.test_gpsService;

@Service("test_gpsService")
public class test_gpsServiceImpl implements test_gpsService{

	@Autowired
	private test_gpsMapper test_gps_dao;
	
	@Override
	public List<test_gps> getAlltest_gps() {
		// TODO Auto-generated method stub
		return test_gps_dao.getAlltest_gps();
	}

	@Override
	public int updateByPrimaryKeySelective(test_gps test_gps) {
		// TODO Auto-generated method stub
		return test_gps_dao.updateByPrimaryKeySelective(test_gps);
	}

	@Override
	public List<test_gps> selectBySomething(test_gps test_gps) {
		// TODO Auto-generated method stub
		return test_gps_dao.selectBySomething(test_gps);
	}

}
