package com.tjnu.project_park.service;

import com.tjnu.project_park.entity.DeviceToPark;
import com.tjnu.project_park.mapper.DeviceToParkMapper;
import com.tjnu.project_park.util.ParkToStatue;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
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
public class DeviceToParkServiceTest {
    @Autowired
    private DeviceToParkService deviceToParkService;
    @Test
    public void initial(){
        List<ParkToStatue> list=deviceToParkService.initialization(1);
        for (ParkToStatue parkToStatue:list) {
            System.out.println(parkToStatue);
        }
    }
    @Test
    public void getStatue(){
        System.out.println(deviceToParkService.getStatue("5",1));
    }
}
