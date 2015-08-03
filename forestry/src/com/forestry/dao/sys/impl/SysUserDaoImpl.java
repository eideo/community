package com.forestry.dao.sys.impl;

import org.springframework.stereotype.Repository;

import base.dao.BaseDaoImpl;

import com.forestry.dao.sys.SysUserDao;
import com.forestry.model.sys.SysUser;

/**
 * @author Yang Tian
 * @email 1298588579@qq.com
 */
@Repository
public class SysUserDaoImpl extends BaseDaoImpl<SysUser> implements SysUserDao {

	public SysUserDaoImpl() {
		super(SysUser.class);
	}

}
