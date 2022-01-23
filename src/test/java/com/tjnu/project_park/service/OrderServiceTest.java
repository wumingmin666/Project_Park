package com.tjnu.project_park.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @param
 * @return
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;
    @Test
    public void booking(){
        orderService.booking("12",1,"whh","HS4562","2022-01-17 16:30:51","2022-01-18 15:30:51");
    }
    @Test
    public void myBooking(){
        System.out.println(orderService.myBooking("wh"));
    }
}
