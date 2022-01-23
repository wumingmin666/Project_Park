package com.tjnu.project_park.service;

import com.tjnu.project_park.entity.Park;

import java.util.HashMap;
import java.util.List;

/**
 * @param
 * @return
 */
public interface ParkService {
    public HashMap<String,Park> findParkByDistance(String location);
    public String getUrlByPid(Integer pid);
}
