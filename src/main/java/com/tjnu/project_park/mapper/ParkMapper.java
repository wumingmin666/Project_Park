package com.tjnu.project_park.mapper;

import com.tjnu.project_park.entity.Park;

import java.util.List;

/**
 * @param
 * @return
 */
public interface ParkMapper {
    public List<Park> findAllPark();
    public Park findParkByPid(Integer pid);
}
