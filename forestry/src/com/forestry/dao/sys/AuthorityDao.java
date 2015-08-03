package com.forestry.dao.sys;

import java.util.List;

import base.dao.BaseDao;

import com.forestry.model.sys.Authority;

/**
 * @author Yang Tian
 * @email 1298588579@qq.com
 */
public interface AuthorityDao extends BaseDao<Authority> {

	List<Authority> queryByParentIdAndRole(Short role);

	List<Authority> queryChildrenByParentIdAndRole(Long parentId, Short role);

}
