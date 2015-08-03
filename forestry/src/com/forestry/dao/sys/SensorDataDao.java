package com.forestry.dao.sys;

import java.util.List;

import base.dao.BaseDao;

import com.forestry.model.sys.SensorData;

/**
 * @author Yang Tian
 * @email 1298588579@qq.com
 */
public interface SensorDataDao extends BaseDao<SensorData> {

	List<Object[]> doGetSensorDataStatistics(Short sensorType);

}
