package com.forestry.dao.sys.impl;

import org.springframework.stereotype.Repository;

import com.forestry.dao.sys.RoleAuthorityDao;
import com.forestry.model.sys.RoleAuthority;
import commnuity.base.dao.BaseDaoImpl;

/**
 * @author Yang Tian
 * @email 1298588579@qq.com
 */
@Repository
public class RoleAuthorityDaoImpl extends BaseDaoImpl<RoleAuthority> implements RoleAuthorityDao {

	public RoleAuthorityDaoImpl() {
		super(RoleAuthority.class);
	}
}
