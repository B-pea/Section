package com.Section.service;

import java.util.List;

import com.Section.model.walker;

public interface WalkerService {

	void insertSelective(walker walker);

	List<com.Section.model.walker> selectWalkerTailAll();

}
