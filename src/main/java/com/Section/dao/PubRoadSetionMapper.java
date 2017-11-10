package com.Section.dao;

import java.util.List;

import com.Section.model.PubRoadSetion;


public interface PubRoadSetionMapper {  
    int deleteByPrimaryKey(Integer id);

    int insert(PubRoadSetion record);

    int insertSelective(PubRoadSetion record);

    PubRoadSetion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PubRoadSetion record);

    int updateByPrimaryKey(PubRoadSetion record);
    
    
    List<Integer> selectPubRoadSetionBySize(Integer size);
    
    public List<PubRoadSetion> getRoutAll();

	void updateRailwayID(PubRoadSetion setion);

	List<PubRoadSetion> getroutMated();

	void updateSection(PubRoadSetion setion);

	List<PubRoadSetion> getAllLinePoint();

	void updateLinePoint(PubRoadSetion setion);

	PubRoadSetion getSingleLinePoint(PubRoadSetion slq);

	void updateOwnArea(PubRoadSetion setion);

	List<PubRoadSetion> getRoutAllEmptyPoints(); 
  
}