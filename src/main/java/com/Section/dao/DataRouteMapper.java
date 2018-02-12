package com.Section.dao;

import java.util.List;

import com.Section.model.DataRoute;

public interface DataRouteMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataRoute record);

    int insertSelective(DataRoute record);

    DataRoute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataRoute record);

    int updateByPrimaryKey(DataRoute record);

	List<DataRoute> selectBySiteCode(String siteCode);

	List<DataRoute> getAll();

	List<String> getWhichNotFull();

	void deleteByStartSite(String startSite);

	int getByStartEndSite(DataRoute route);
}