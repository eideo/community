package com.forestry.dao.sys;

import java.util.List;

import com.forestry.model.sys.Forestry;
import commnuity.base.dao.BaseDao;

/**
 * @author Yang Tian
 * @email 1298588579@qq.com
 */
public interface ForestryDao extends BaseDao<Forestry> {
	
	List<Object[]> queryExportedForestry(Long[] ids);

}
