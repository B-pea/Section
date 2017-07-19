package com.Section.dao;

import java.util.List;

import com.Section.model.walker;

public interface walkerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(walker record);

    int insertSelective(walker record);

    walker selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(walker record);

    int updateByPrimaryKeyWithBLOBs(walker record);

    int updateByPrimaryKey(walker record);

	List<walker> selectWalkerTailAll();
}