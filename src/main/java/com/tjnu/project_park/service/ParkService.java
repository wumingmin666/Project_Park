package com.tjnu.project_park.service;

import com.tjnu.project_park.entity.Park;

import java.util.HashMap;
import java.util.List;

/**
 * @param
 * @return
 */
public interface ParkService {
    /**
     * 根据传入的位置信息获取距离该位置最近的几个地图
     * @param location 用户的位置信息
     * @return 返回地图实体类的集合
     */
    public HashMap<String,Park> findParkByDistance(String location);

    /**
     * 根据地图的pid获取地图的url
     * @param pid 地图的pid
     * @return 返回地图的url字符串
     */
    public String getUrlByPid(Integer pid);
}
