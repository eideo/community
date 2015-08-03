package com.forestry.dao.sys;

import java.util.List;

import com.forestry.model.sys.SensorData;
import commnuity.base.dao.BaseDao;

/**
 * @author Yang Tian
 * @email 1298588579@qq.com
 */
public interface SensorDataDao extends BaseDao<SensorData> {

	List<Object[]> doGetSensorDataStatistics(Short sensorType);

}
