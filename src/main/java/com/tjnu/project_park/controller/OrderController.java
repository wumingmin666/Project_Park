package com.tjnu.project_park.controller;

import com.tjnu.project_park.entity.Order;
import com.tjnu.project_park.service.OrderService;
import com.tjnu.project_park.service.ParkService;
import com.tjnu.project_park.util.JWTUtil;
import com.tjnu.project_park.util.JsonResult;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * @param
 * @return
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BaseController{
    @Autowired
    private OrderService orderService;
    @Autowired
    private ParkService parkService;

    /**
     *
     * @param bookingStartTimeString
     * @param bookingEndTimeString
     * @param plateNumber
     * @param parkName
     * @param pid
     * @param httpServletRequest
     * @return
     */
    @ResponseBody
    @RequestMapping("/booking")
    public JsonResult<Void> booking(@RequestParam(value = "booking_start_time") String bookingStartTimeString,
                                    @RequestParam(value = "booking_end_time") String bookingEndTimeString,
                                    @RequestParam(value = "plate_number") String plateNumber,
                                    @RequestParam(value = "name") String parkName, Integer pid, HttpServletRequest httpServletRequest) {

        //通过token获取username
        JWTUtil jwtUtil=new JWTUtil();
        Object token=httpServletRequest.getHeader("access_token");
        String username=jwtUtil.vaildToken((String) token);
        //将所有参数传给业务层
        String result=orderService.booking(parkName,pid,username,plateNumber,bookingStartTimeString,bookingEndTimeString);
        //添加支付功能后对结果result进行处理
        return new JsonResult<>(OK);
    }

    @RequestMapping("/my_booking")
    public ModelAndView myBooking(HttpServletRequest httpServletRequest) throws ServletException, IOException {
        JWTUtil jwtUtil=new JWTUtil();
        Object token=httpServletRequest.getHeader("access_token");
        String username=jwtUtil.vaildToken((String) token);
        HashMap<String,Object> data=orderService.myBooking(username);

        ModelAndView mav=new ModelAndView();
        Integer pid=(Integer) data.get("pid");
        String parkName=(String) data.get("parkName");
        //获取pid对应地图地址
        String url=parkService.getUrlByPid(pid);
        int first = url.indexOf("/");
        int last = url.lastIndexOf("/");
        String map = url.substring(first+1, last);

        mav.addObject("parkName",parkName);
        mav.setViewName("map/"+map);

        return mav;
    }
}
