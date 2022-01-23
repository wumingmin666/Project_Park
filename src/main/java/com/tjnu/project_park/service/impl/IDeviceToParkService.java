package com.tjnu.project_park.service.impl;

import com.tjnu.project_park.entity.DeviceToPark;
import com.tjnu.project_park.mapper.DeviceToParkMapper;
import com.tjnu.project_park.service.DeviceToParkService;
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
    @Override
    public List<ParkToStatue> initialization(Integer pid) {
        List<DeviceToPark> data=deviceToParkMapper.findDeviceByPid(pid);
        Integer parkId=null;
        Integer statue=null;
        String parkName=null;
        List<ParkToStatue> result=new ArrayList<>();
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
        Integer parkId=Integer.valueOf(parkName);
        DeviceToPark deviceToPark=deviceToParkMapper.findDeviceByParkId(parkId,pid);
        Integer statue=deviceToPark.getStatue();
        return statue;
    }

}
