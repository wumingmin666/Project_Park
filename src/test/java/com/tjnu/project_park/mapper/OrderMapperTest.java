package com.tjnu.project_park.mapper;

import com.tjnu.project_park.entity.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @param
 * @return
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@MapperScan("com.tjnu.project_park.mapper")
public class OrderMapperTest {
    @Autowired
    private OrderMapper orderMapper;
    @Test
    public void insert(){
        Order order=new Order();
        order.setBookingEndTime(new Date());
        order.setBookingStartTime(new Date());
        order.setCreatedTime(new Date());
        order.setPid(1);
        order.setBookingUser("xiaolll");
        order.setIsExist(1);
        order.setParkId(1);
        order.setPlateNumber("HD123546");
        order.setOutTradeNo("1589562");
        Integer row=orderMapper.insert(order);
        System.out.println(row);
    }
    @Test
    public void findOrderByUsername(){
        Order order=orderMapper.findOrderByUsername("wh");
        System.out.println(order);
    }
}
