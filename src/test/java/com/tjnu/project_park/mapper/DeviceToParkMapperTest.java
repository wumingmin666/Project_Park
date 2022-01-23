package com.tjnu.project_park.mapper;

import com.tjnu.project_park.entity.DeviceToPark;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @param
 * @return
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@MapperScan("com.tjnu.project_park.mapper")
public class DeviceToParkMapperTest {
    @Autowired
    private DeviceToParkMapper deviceToParkMapper;
    @Test
    public void findDeviceByPid(){
        List<DeviceToPark> list=deviceToParkMapper.findDeviceByPid(1);
        for (DeviceToPark dp:list) {
            System.out.println(dp);
        }
    }
    @Test
    public void findDeviceByParkName(){
        DeviceToPark deviceToPark=deviceToParkMapper.findDeviceByParkId(2,1);
        System.out.println(deviceToPark);
    }
    @Test
    public void updateStatueByParkId(){
        Integer rows=deviceToParkMapper.updateStatueByParkId(7,1);
        System.out.println(rows);
    }

}
