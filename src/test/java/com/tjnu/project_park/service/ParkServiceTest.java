package com.tjnu.project_park.service;

import com.tjnu.project_park.entity.Park;
import com.tjnu.project_park.mapper.ParkMapper;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;

/**
 * @param
 * @return
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class ParkServiceTest {
    @Autowired
    private ParkService parkService;
    @Test
    public void fun1(){
        HashMap<String, Park> re=parkService.findParkByDistance("15");
        System.out.println(re);
    }
}
