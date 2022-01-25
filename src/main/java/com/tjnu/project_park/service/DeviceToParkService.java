package com.tjnu.project_park.service;

import com.tjnu.project_park.util.ParkToStatue;

import java.util.HashMap;
import java.util.List;

/**
 * @param
 * @return
 */
public interface DeviceToParkService {
    /**
     * 通过pid(停车场id)获取该停车场的所有车位信息的实体类集合，并进行处理筛选后返回
     * @param pid 停车场id
     * @return 返回ParkToStatue（车位到状态的映射实体类）集合
     */
    public List<ParkToStatue> initialization(Integer pid);

    /**
     *通过停车场id和车位的id（parkName->parkId）获取对应的状态
     * @param parkName 车位的id(字符串之后转为parkId为整型)
     * @param pid 停车场id
     * @return 返回该车位的状态
     */
    public Integer getStatue(String parkName,Integer pid);
}
