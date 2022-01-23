package com.tjnu.project_park.service;

import com.tjnu.project_park.util.ParkToStatue;

import java.util.HashMap;
import java.util.List;

/**
 * @param
 * @return
 */
public interface DeviceToParkService {
    public List<ParkToStatue> initialization(Integer pid);
    public Integer getStatue(String parkName,Integer pid);
}
