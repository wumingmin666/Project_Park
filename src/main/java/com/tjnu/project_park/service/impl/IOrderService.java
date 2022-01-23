package com.tjnu.project_park.service.impl;

import com.tjnu.project_park.entity.Order;
import com.tjnu.project_park.mapper.DeviceToParkMapper;
import com.tjnu.project_park.mapper.OrderMapper;
import com.tjnu.project_park.service.OrderService;
import com.tjnu.project_park.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @param
 * @return
 */
@Service
public class IOrderService implements OrderService {
    @Autowired
    private DeviceToParkMapper deviceToParkMapper;
    @Autowired
    private OrderMapper orderMapper;
    //暂时返回ok,之后根据付费需求返回
    @Override
    public String booking(String parkName, Integer pid, String username, String plateName, String bookingStartTimeString, String bookingEndTimeString) {
        //1、修改车位状态为预定,之后需要添加更新失败的异常
        Integer parkId=Integer.valueOf(parkName);
        deviceToParkMapper.updateStatueByParkId(parkId,pid);
        //2、添加订单
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date bookingEndTime= null;
        Date bookingStartTime=null;
        try {
            bookingEndTime = format.parse(bookingEndTimeString);
            bookingStartTime = format.parse(bookingStartTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Order order=new Order();
        order.setBookingEndTime(bookingEndTime);
        order.setBookingStartTime(new Date());
        order.setCreatedTime(new Date());
        order.setPid(pid);
        order.setBookingUser(username);
        order.setIsExist(1);
        order.setParkId(parkId);
        order.setPlateNumber(plateName);
        //之后添加异常
        orderMapper.insert(order);
        //3、根据需求返回信息
        return null;
    }

    @Override
    public HashMap myBooking(String username) {
        Order myOrder=orderMapper.findOrderByUsername(username);
        //1、判断订单是否过期存在，考虑通过时间或is_delete(暂时省略，之后写该步骤，还需添加异常)
        //2、获取Park_id和pid
        Integer parkId=myOrder.getParkId();
        Integer pid=myOrder.getPid();
        String parkName=parkId.toString();
        HashMap<String,Object> result=new HashMap<>();
        result.put("pid",pid);
        result.put("parkName",parkName);
        return result;
    }
}
