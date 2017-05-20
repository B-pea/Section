package com.Section.service;

import java.util.List;
import java.util.Map;

import com.Section.model.OneOrganization;
import com.Section.model.Organization;

/**
 * @Description:机构管理的服务接口
 * @Author: zhangyongyou
 * @Version: 1.0
 * @Create Date Time: Dec 15, 2016 4:17:36 PM
 * @Update Date Time: Dec 15, 2016 4:17:36 PM
 * @see
 */
public interface OrganizationService {
	/**
	 * @Description:插入机构
	 * @param:
	 * @return:
	 * @throws Exception
	 * @see Note:
	 */
	int insertSelective(Organization organ);

	/**
	 * @Description:更新机构
	 * @param:
	 * @return:
	 * @throws Exception
	 * @see Note:
	 */
	int updateByPrimaryKeySelective(Organization organ);

	/**
	 * @Description:删除机构，这个一般不要使用，调用此方法前， 先检测机构内有没有用户、检测有没有下级机构，如果两者任何一个有，都不能删除，进行提示
	 * @param:
	 * @return:
	 * @throws Exception
	 * @see Note:
	 */
	int deleteByPrimaryKey(String idOrCode);

	/**
	 * @Description:查询一个机构
	 * @param:待查询机构的ID或者机构代码，因为这两个在机构表中都是唯一，所以，使用哪个查询都是相同效果
	 * @return:
	 * @throws Exception
	 * @see Note:
	 */
	Organization selectByPrimaryKey(String idOrCode);

	/**
	 * @Description:查询当前用户所在机构和下级机构的机构结果集，支持分页，支持树形（需要结合Common中的工具方法，这样做的好处是比SQL查询效率高）
	 * @param:
	 * @return:
	 * @throws Exception
	 * @see Note:
	 */
	List<Organization> selectByMap(Map<String, Object> map);

	/**
	 * @Description:查询省级机构
	 * @param:
	 * @return:
	 * @throws Exception
	 * @see Note:
	 */
	String selectOrganizationProvince();

	/**
	 * @Description:查询下级机构是否存在
	 * @param:map的参数必须要有的为<br> orgcode
	 *                             可为空<br>
	 *                             category 可为空<br>
	 *                             page 大于等于0的自然数<br>
	 *                             size 大于0的自然数<br>
	 * @return:
	 * @throws Exception
	 * @see Note:
	 */
	List<Organization> selectOneByMap(Map<String, Object> map);

	/**
	 * @Description:插入一维机构表orgcode机构及其下级机构，批量插入
	 * @param:
	 * @return:
	 * @throws Exception
	 * @see Note:
	 */
	int insertOneSelective(List<OneOrganization> list);

	/**
	 * @Description:删除一维机构表orgcode机构及其下级机构
	 * @param:
	 * @return:
	 * @throws Exception
	 * @see Note:
	 */
	int deleteOneByCode(String orgcode);

	/**
	 * @Description:创建一维机构表关系，此方法会批量调用insertOneSelective
	 * @param:
	 * @return:
	 * @throws Exception
	 * @see Note:
	 */
	String createOneTable(Organization organization);

	/**
	 * @Description:当机构信息修改时有两种情况发生 <br>
	 *                              1、当机构状态被修改时讲调用二维机构表和一维机构表 <br>
	 *                              2、当机构状态没有被修改时，那就是普通的机构修改，只需要修改二维机构表中的信息即可
	 * @param:
	 * @return:
	 * @throws Exception
	 * @see Note:
	 */
	String updateOneTable(Organization organization);
	
	/**
	 * @Description:查询站点机构的信息
	 * @param:
	 * @return:
	 * @throws Exception
	 * @see Note:
	 */
	List<Organization> selectSiteList(Map<String, Object> map);
	
	Map<String, Object> getOrgData();

	Organization getOrgNameByCode(String orgCode);

	List<Organization> getAllName();
}
