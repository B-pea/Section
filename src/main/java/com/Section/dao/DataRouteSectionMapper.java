package com.Section.dao;

import java.util.List;

import com.Section.model.DataRouteSection;

public interface DataRouteSectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataRouteSection record);

    int insertSelective(DataRouteSection record);

    DataRouteSection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataRouteSection record);

    int updateByPrimaryKey(DataRouteSection record);

	void deleteByRouteId(Integer routeId);

	List<DataRouteSection> selectByRouteId(Integer routeId);
}