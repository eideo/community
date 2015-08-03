package com.forestry.dao.sys;

import java.util.List;

import base.dao.BaseDao;

import com.forestry.model.sys.MonitorLog;

/**
 * @author Yang Tian
 * @email 1298588579@qq.com
 */
public interface MonitorLogDao extends BaseDao<MonitorLog> {

	List<MonitorLog> querySensorMonitorLog();

}
