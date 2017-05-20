package com.Section.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Section.dao.OperatorMapper;
import com.Section.model.Operator;
import com.Section.service.OperatorService;

/**
 * @Description:用户服务接口实现
 * @Author: zhangyongyou
 * @Version: 1.0
 * @Create Date Time: 2016年11月25日 下午5:13:47.
 * @Update Date Time:
 * @see
 */
@Service("operatorService")
public class OperatorServiceImpl implements OperatorService {
	@Autowired
	private OperatorMapper operatorMapper;

	@Override
	public int deleteByPrimaryKey(String idOrCode) {
		return operatorMapper.deleteByPrimaryKey(idOrCode);
	}

	@Override
	public int insertSelective(Operator record) {
		return operatorMapper.insertSelective(record);
	}

	@Override
	public int insertRoleRelationByPrimaryKey(Integer id, Integer[] arr) {
		operatorMapper.deleteRoleRelationByPrimaryKey(id);
		if (arr != null) {
			for (Integer i : arr) {
				operatorMapper.insertRoleRelationByPrimaryKey(id, i);
			}
		}
		return 0;
	}

	@Override
	public Operator selectByPrimaryKey(String idOrCode) {
		return operatorMapper.selectByPrimaryKey(idOrCode);
	}

	@Override
	public int updateByPrimaryKeySelective(Operator record) {
		return operatorMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<Operator> selectOperatorByMap(Map<String, Object> map) {
		return operatorMapper.selectOperatorByMap(map);
	}

	@Override
	public int selectOperatorByOrgcode(String orgcode) {
		return operatorMapper.selectOperatorByOrgcode(orgcode);
	}

	@Override
	public int isAvaliableRole(String rid) {
		return operatorMapper.isAvaliableRole(rid);
	}
}