package com.forestry.dao.sys.impl;

import org.springframework.stereotype.Repository;

import com.forestry.dao.sys.DepartmentDao;
import com.forestry.model.sys.Department;

import community.base.dao.BaseDaoImpl;

/**
 * @author Yang Tian
 * @email 1298588579@qq.com
 */
@Repository
public class DepartmentDaoImpl extends BaseDaoImpl<Department> implements DepartmentDao {

	public DepartmentDaoImpl() {
		super(Department.class);
	}

}
