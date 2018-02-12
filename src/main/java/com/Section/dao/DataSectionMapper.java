package com.Section.dao;

import java.util.List;

import com.Section.model.DataSection;

public interface DataSectionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataSection record);

    int insertSelective(DataSection record);

    DataSection selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataSection record);

    int updateByPrimaryKeyWithBLOBs(DataSection record);

    int updateByPrimaryKey(DataSection record);

    // 根据起止点坐标、距离判断是否有重复的路段
    List<DataSection> isHaveSection(DataSection dataSection);

	List<DataSection> selectAllSection();

	List<DataSection> getSectionHaveRoad();
}