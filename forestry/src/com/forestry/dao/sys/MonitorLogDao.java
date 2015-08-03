package com.forestry.dao.sys;

import java.util.List;

import com.forestry.model.sys.MonitorLog;
import commnuity.base.dao.BaseDao;

/**
 * @author Yang Tian
 * @email 1298588579@qq.com
 */
public interface MonitorLogDao extends BaseDao<MonitorLog> {

	List<MonitorLog> querySensorMonitorLog();

}
