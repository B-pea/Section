package com.Section.dao;

import java.util.List;

import com.Section.model.test_gps;

public interface test_gpsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(test_gps record);

    int insertSelective(test_gps record);

    test_gps selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(test_gps record);

    int updateByPrimaryKey(test_gps record);

	List<test_gps> getAlltest_gps();

	List<com.Section.model.test_gps> selectBySomething(test_gps test_gps);
}