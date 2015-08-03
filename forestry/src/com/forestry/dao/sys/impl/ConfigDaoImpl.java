package com.forestry.dao.sys.impl;

import org.springframework.stereotype.Repository;

import base.dao.BaseDaoImpl;

import com.forestry.dao.sys.ConfigDao;
import com.forestry.model.sys.Config;

/**
 * @author Yang Tian
 * @email 1298588579@qq.com
 */
@Repository
public class ConfigDaoImpl extends BaseDaoImpl<Config> implements ConfigDao {

	public ConfigDaoImpl() {
		super(Config.class);
	}

}
