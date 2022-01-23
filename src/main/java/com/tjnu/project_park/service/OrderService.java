package com.tjnu.project_park.service;

import java.util.Date;
import java.util.HashMap;

/**
 * @param
 * @return
 */
public interface OrderService {
    public String booking(String parkName, Integer pid, String username, String plateName, String bookingStartTimeString,String bookingEndTimeString);
    public HashMap myBooking(String username);
}
