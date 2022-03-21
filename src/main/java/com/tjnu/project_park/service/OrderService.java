package com.tjnu.project_park.service;

import java.util.Date;
import java.util.HashMap;

/**
 * @param
 * @return
 */
public interface OrderService {
    /**
     * 添加订单方法，暂时不完整，之后需配合支付等功能进行完善
     * @param parkName
     * @param pid
     * @param username
     * @param plateName
     * @param bookingStartTimeString
     * @param bookingEndTimeString
     * @return 暂时返回null
     */
    public String booking(String parkName, Integer pid, String username, String plateName, String bookingStartTimeString,String bookingEndTimeString,String payMethod,String price);

    /**
     * 获取订单有关信息（地图界面）
     * @param username 用户名称
     * @return 停车场id,和车位id构成的映射集合
     */
    public HashMap myBooking(String username);
}
