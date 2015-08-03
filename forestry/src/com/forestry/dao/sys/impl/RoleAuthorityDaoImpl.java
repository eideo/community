package com.forestry.dao.sys.impl;

import org.springframework.stereotype.Repository;

import base.dao.BaseDaoImpl;

import com.forestry.dao.sys.RoleAuthorityDao;
import com.forestry.model.sys.RoleAuthority;

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
