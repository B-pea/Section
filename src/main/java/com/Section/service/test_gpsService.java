package com.Section.service;

import java.util.List;

import com.Section.model.test_gps;

public interface test_gpsService {

	List<test_gps> getAlltest_gps();

	int updateByPrimaryKeySelective(test_gps test_gps);

	List<com.Section.model.test_gps> selectBySomething(test_gps test_gps);

}
