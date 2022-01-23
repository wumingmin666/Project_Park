package com.tjnu.project_park.mapper;

import com.tjnu.project_park.entity.DeviceToPark;

import java.util.List;

/**
 * @param
 * @return
 */
public interface DeviceToParkMapper {
    public List<DeviceToPark> findDeviceByPid(Integer pid);
    public DeviceToPark findDeviceByParkId(Integer parkId,Integer pid);
    public Integer updateStatueByParkId(Integer parkId,Integer pid);
}
