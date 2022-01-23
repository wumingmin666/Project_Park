package com.tjnu.project_park.mapper;

import com.tjnu.project_park.entity.Park;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
public class ParkMapperTest {
    @Autowired
    private ParkMapper parkMapper;

    @Test
    public void findAllPark(){
        List<Park> list=parkMapper.findAllPark();
        System.out.println(list);
    }
    @Test
    public void findParkByPid(){
        Park park=parkMapper.findParkByPid(2);
        System.out.println(park);
    }
}
