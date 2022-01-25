package com.tjnu.project_park.service.impl;

import com.tjnu.project_park.entity.DeviceToPark;
import com.tjnu.project_park.entity.Park;
import com.tjnu.project_park.mapper.DeviceToParkMapper;
import com.tjnu.project_park.mapper.ParkMapper;
import com.tjnu.project_park.service.DeviceToParkService;
import com.tjnu.project_park.service.ex.ParkIdNotFountServiceException;
import com.tjnu.project_park.service.ex.ParkNotFoundByPidServiceException;
import com.tjnu.project_park.util.ParkToStatue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @param
 * @return
 */
@Service
public class IDeviceToParkService implements DeviceToParkService {
    @Autowired
    private DeviceToParkMapper deviceToParkMapper;
    @Autowired
    private ParkMapper parkMapper;

    @Override
    public List<ParkToStatue> initialization(Integer pid) {
        List<DeviceToPark> data=deviceToParkMapper.findDeviceByPid(pid);
        if(data==null){
            throw new ParkNotFoundByPidServiceException("停车场信息不存在异常");
        }
        Integer parkId=null;  //车位id
        Integer statue=null;  //车位状态
        String parkName=null; //车位name(id的字符串，地图的name属性对应数据库的id属性)
        List<ParkToStatue> result=new ArrayList<>();
        //对车位信息进行筛选处理，加入到新的实体类ParkToStatue中
        for (DeviceToPark device:data) {
            parkId=device.getParkId();
            parkName=parkId.toString();
            statue=device.getStatue();

            ParkToStatue parkToStatue=new ParkToStatue();
            parkToStatue.setParkName(parkName);
            parkToStatue.setStatue(statue);
            result.add(parkToStatue);
        }
        return result;
    }

    @Override
    public Integer getStatue(String parkName,Integer pid) {
        //判断停车场是否存在
        Park park=parkMapper.findParkByPid(pid);
        if(park==null){
            throw new ParkNotFoundByPidServiceException("停车场不存在异常");
        }
        //获取车位
        Integer parkId=Integer.valueOf(parkName);
        DeviceToPark deviceToPark=deviceToParkMapper.findDeviceByParkId(parkId,pid);
        //判断车位是否存在
        if(deviceToPark==null){
            throw new ParkIdNotFountServiceException("车位不存在异常");
        }
        //获取车位状态
        Integer statue=deviceToPark.getStatue();
        return statue;
    }

}
