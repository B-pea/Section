package com.Section.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Section.dao.walkerMapper;
import com.Section.model.walker;
import com.Section.service.WalkerService;


@Service("WalkerService")
public class WalkerServiceImpl implements WalkerService{

	@Autowired
	private walkerMapper walkerdao;
	
	@Override
	public void insertSelective(walker walker) {
		// TODO Auto-generated method stub
		walkerdao.insertSelective(walker);
	}

	@Override
	public List<walker> selectWalkerTailAll() {
		// TODO Auto-generated method stub
		return walkerdao.selectWalkerTailAll();
	}

}
