package com.Section.dao;

import org.apache.ibatis.annotations.Param;

import com.Section.model.RouteSection;

public interface RouteSectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RouteSection record);

    int insertSelective(RouteSection record);

    RouteSection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RouteSection record);

    int updateByPrimaryKey(RouteSection record);

	void updateBySectionId(@Param("targetId")int targetId,@Param("oldId") int oldId);

}