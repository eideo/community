package com.forestry.dao.sys;

import java.util.List;

import base.dao.BaseDao;

import com.forestry.model.sys.Forestry;

/**
 * @author Yang Tian
 * @email 1298588579@qq.com
 */
public interface ForestryDao extends BaseDao<Forestry> {
	
	List<Object[]> queryExportedForestry(Long[] ids);

}
