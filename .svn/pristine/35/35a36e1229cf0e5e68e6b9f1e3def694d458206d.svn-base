package com.Section.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.Section.model.Operator;

/**
 * @Description: 用户记录处理
 * @Author: zhangyongyou
 * @Version: 1.0
 * @Create Date Time: 2016年11月25日 下午5:07:52.
 * @Update Date Time:
 * @see
 */
public interface OperatorMapper {
	/**
	 * 
	 * Description: 删除用户
	 * 
	 * @param 用户表id或者用户代码
	 * @return 删除条数
	 * @throws Exception
	 * @see Note:
	 */
	int deleteByPrimaryKey(String idOrCode);

	/**
	 * Description:新增用户-判断字段是否为空
	 * 
	 * @param 新增用户对象
	 * @return 插入条数
	 * @throws Exception
	 * @see Note:
	 */
	int insertSelective(Operator record);

	/**
	 * Description: 创建用户角色关系
	 * 
	 * @param 用户id，角色id数组
	 * @return 插入条数
	 * @throws Exception
	 * @see Note:
	 */
	int insertRoleRelationByPrimaryKey(@Param("oid") Integer oid, @Param("rid") Integer rid);

	/**
	 * Description: 查询单用户
	 * 
	 * @param 用户表id或用户代码
	 * @return 返回查询用回对象
	 * @throws Exception
	 * @see Note:
	 */
	Operator selectByPrimaryKey(String idOrCode);

	/**
	 * Description: 更新用户-判字段值是否为空，不为空的才会更新，推荐使用
	 * 
	 * @param 待更新的对象
	 * @return 返回更新条数
	 * @throws Exception
	 * @see Note:
	 */
	int updateByPrimaryKeySelective(Operator record);

	/**
	 * @Description:查询用户所在机构及其下级机构的所有用户，
	 * @param: map参数需要有：<br>
	 *             orgid:用户所在机构的ID<br>
	 *             status:用户状态和机构状态<br>
	 *             page:页面的起始页码，如果不分页的情况下，请输入0<br>
	 *             size:页面显示条数，如果不分页的情况下，请输入Integer.Max_Value<br>
	 * @return:
	 * @throws Exception
	 * @see Note:
	 */
	List<Operator> selectOperatorByMap(Map<String, Object> map);

	/**
	 * @Description:删除原来的关系
	 * @param:
	 * @return:
	 * @throws Exception
	 * @see Note:
	 */
	int deleteRoleRelationByPrimaryKey(Integer id);

	/**
	 * @Description:根据机构代码到操作员表中查询用到此机构代码的用户数量
	 * @param:机构代码
	 * @return:用户数量
	 * @throws Exception
	 * @see Note:
	 */
	int selectOperatorByOrgcode(String orgcode);

	/**
	 * @Description:根据要删除的角色id去判断是否有用户在使用，以及使用者的总人数
	 * @param:角色ID
	 * @return:使用当前角色的用户数量
	 * @throws Exception
	 * @see Note:
	 */
	int isAvaliableRole(String rid);
}