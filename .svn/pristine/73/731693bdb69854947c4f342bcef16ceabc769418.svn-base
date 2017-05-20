package com.Section.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Section.dao.OrganizationMapper;
import com.Section.model.OneOrganization;
import com.Section.model.Organization;
import com.Section.model.Site;
import com.Section.service.OrganizationService;
import com.Section.service.SiteService;

/**
 * @Description:机构管理的服务接口
 * @Author: zhangyongyou
 * @Version: 1.0
 * @Create Date Time: Dec 15, 2016 4:17:36 PM
 * @Update Date Time: Dec 15, 2016 4:17:36 PM
 * @see
 */
@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {
	@Autowired
	private OrganizationMapper organizationMapper;
	@Autowired
	private SiteService siteService;

	/**
	 * @Description:插入机构
	 * @param:
	 * @return:
	 * @throws Exception
	 * @see Note:
	 */
	@Override
	public int insertSelective(Organization organ) {
		int count = organizationMapper.insertSelective(organ);

		return count;
	}

	/**
	 * @Description:更新机构
	 * @param:
	 * @return:
	 * @throws Exception
	 * @see Note:
	 */
	@Override
	public int updateByPrimaryKeySelective(Organization organ) {
		int count = organizationMapper.updateByPrimaryKeySelective(organ);

		return count;
	}

	/**
	 * @Description:删除机构，这个一般不要使用，调用此方法前， 先检测机构内有没有用户、检测有没有下级机构，如果两者任何一个有，都不能删除，进行提示
	 * @param:
	 * @return:
	 * @throws Exception
	 * @see Note:
	 */
	@Override
	public int deleteByPrimaryKey(String idOrCode) {
		int count = organizationMapper.deleteByPrimaryKey(idOrCode);

		return count;
	}

	/**
	 * @Description:查询一个机构
	 * @param:待查询机构的ID或者机构代码，因为这两个在机构表中都是唯一，所以，使用哪个查询都是相同效果
	 * @return:
	 * @throws Exception
	 * @see Note:
	 */
	@Override
	public Organization selectByPrimaryKey(String idOrCode) {
		return organizationMapper.selectByPrimaryKey(idOrCode);
	}

	/**
	 * @Description:查询当前用户所在机构和下级机构的机构结果集，支持分页，支持树形（需要结合Common中的工具方法，这样做的好处是比SQL查询效率高）
	 * @param:
	 * @return:
	 * @throws Exception
	 * @see Note:
	 */
	@Override
	public List<Organization> selectByMap(Map<String, Object> map) {
		return organizationMapper.selectByMap(map);
	}

	@Override
	public String selectOrganizationProvince() {
		return organizationMapper.selectOrganizationProvince();
	}

	/**
	 * @Description:查询下级机构是否存在
	 * @param:
	 * @return:
	 * @throws Exception
	 * @see Note:
	 */
	@Override
	public List<Organization> selectOneByMap(Map<String, Object> map) {
		return organizationMapper.selectOneByMap(map);
	}

	/**
	 * @Description:插入一维机构表orgcode机构及其下级机构，批量插入
	 * @param:
	 * @return:
	 * @throws Exception
	 * @see Note:
	 */
	@Override
	public int insertOneSelective(List<OneOrganization> list) {
		return organizationMapper.insertOneSelective(list);
	}

	/**
	 * @Description:删除一维机构表orgcode机构及其下级机构
	 * @param:
	 * @return:
	 * @throws Exception
	 * @see Note:
	 */
	@Override
	public int deleteOneByCode(String orgcode) {
		return organizationMapper.deleteOneByCode(orgcode);
	}

	/**
	 * @Description:创建一维机构表关系，此方法会批量调用insertOneSelective
	 * @param:
	 * @return:
	 * @throws Exception
	 * @see Note:这个需要开启事物
	 */
	@Override
	@Transactional
	public String createOneTable(Organization organization) {
		try {
			Organization cone = organization;
			insertSelective(cone);
			String site_category = organization.getCategory();
			if ("1".equals(site_category)) {
				Site site = new Site();
				site.setName(organization.getOrgname());
				site.setOrgCode(organization.getOrgcode());
				String site_status = organization.getStatus();
				if ("0".equals(site_status)) {
					site.setStatus('1');
				} else {
					site.setStatus('0');
				}
				siteService.addSite(site);
			}
			OneOrganization one = new OneOrganization(organization.getOrgcode(), organization.getOrgcode(),
					organization.getOrgname(), organization.getCategory(), organization.getStatus());// 把自己也要维护进一维表中
			List<OneOrganization> list = new ArrayList<>();
			list.add(one);
			one = null;
			while (cone != null) {
				Organization pone = selectByPrimaryKey(cone.getPid() + "");
				if (pone != null) {
					one = new OneOrganization(pone.getOrgcode(), organization.getOrgcode(), organization.getOrgname(),
							organization.getCategory(), organization.getStatus());
					list.add(one);
					one = null;
				}
				cone = pone;
			}
			insertOneSelective(list);
			return "新增机构成功";
		} catch (Exception e) {
			System.out.println(e.toString());
			return "新增机构异常，请联系管理员";
		}
	}

	@Override
	public String updateOneTable(Organization organization) {
		try {
			if (isUpdate(organization)) {
				OneOrganization one = new OneOrganization();
				one.setCorgcode(organization.getOrgcode());
				one.setOrgname(organization.getOrgname());
				one.setStatus(organization.getStatus());
				updateOneByPrimaryKeySelective(one);
			}
			updateByPrimaryKeySelective(organization);
			return "修改机构成功";
		} catch (Exception e) {
			return "修改机构异常，请联系管理员";
		}
	}

	/**
	 * 
	 * @Description:更新时如果发现机构状态或者机构名称有变更时就更新机构的一维表，否则不更新一维机构表
	 * @param:
	 * @return:
	 * @throws Exception
	 * @see Note:
	 */
	private boolean isUpdate(Organization organization) {
		Organization org = selectByPrimaryKey(organization.getOrgcode());
		String status = organization.getStatus();
		organization.setPid(org.getPid());
		String orgname = org.getOrgname();
		String orgname2 = organization.getOrgname();
		if ("1".equals(status)) {
			return true;
		} else if (orgname != orgname2 || !orgname.equals(orgname2)) {
			return true;
		}
		return false;
	}

	private int updateOneByPrimaryKeySelective(OneOrganization one) {
		int count = organizationMapper.updateOneByPrimaryKeySelective(one);

		return count;
	}

	@Override
	public List<Organization> selectSiteList(Map<String, Object> map) {
		return organizationMapper.selectSiteList(map);
	}

	@Override
	public Map<String, Object> getOrgData() {
		return organizationMapper.getOrgData();
	}

	@Override
	public Organization getOrgNameByCode(String orgCode) {
		// TODO Auto-generated method stub
		return organizationMapper.getOrgNameByCode(orgCode);
	}

	@Override
	public List<Organization> getAllName() {
		// TODO Auto-generated method stub
		return organizationMapper.getAllName();
	}
}