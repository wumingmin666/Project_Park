package com.tjnu.project_park.controller;

import com.tjnu.project_park.entity.Order;
import com.tjnu.project_park.service.OrderService;
import com.tjnu.project_park.service.ParkService;
import com.tjnu.project_park.service.ex.OrderNotFoundServiceException;
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
@RestController
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
     * @param plateNumber 车牌号
     * @param parkName
     * @param pid
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/booking")
    public JsonResult<String> booking(@RequestParam(value = "booking_start_time") String bookingStartTimeString, @RequestParam(value = "booking_end_time") String bookingEndTimeString, @RequestParam(value = "plate_number") String plateNumber, @RequestParam(value = "name") String parkName, Integer pid,@RequestParam(value = "price") String price, HttpServletRequest httpServletRequest) {

        //通过token获取username
        JWTUtil jwtUtil=new JWTUtil();
        Object token=httpServletRequest.getHeader("access_token");
        String username=jwtUtil.vaildToken((String) token);
        //判断支付方式
        String payMethod="App";
        //将所有参数传给业务层
        String result=orderService.booking(parkName,pid,username,plateNumber,bookingStartTimeString,bookingEndTimeString,payMethod,price);
        //添加支付功能后对结果result进行处理

//        httpResponse.setContentType("text/html;charset=" + "UTF-8");
//        httpResponse.getWriter().write(form);//直接将完整的表单html输出到页面
//        httpResponse.getWriter().flush();
//        httpResponse.getWriter().close();
        return new JsonResult<String>(OK,result);
    }


    @RequestMapping("/my_book")
    public JsonResult<JSONObject> myBook(HttpServletRequest httpServletRequest) throws ServletException, IOException {
        //解析token
        JWTUtil jwtUtil=new JWTUtil();
        Object token=httpServletRequest.getHeader("access_token");
        String username=jwtUtil.vaildToken((String) token);

        try {
            //根据用户名获取地图信息
            HashMap<String,Object> data=orderService.myBooking(username);
            Integer pid=(Integer) data.get("pid");
            String parkName=(String) data.get("parkName");

            //获取pid对应地图地址
            String url=parkService.getUrlByPid(pid);
            String map = "map"+url;
            HashMap<String,String> hashMap=new HashMap<>();
            hashMap.put("url",map);
            hashMap.put("parkId",parkName);
            JSONObject jsonObject=new JSONObject(hashMap);
            return new JsonResult<JSONObject>(OK,jsonObject);
        }catch (OrderNotFoundServiceException e){
            System.out.println("订单不存在异常");
        }
        return new JsonResult<JSONObject>(50090,null);

    }

    /**
     * 接收异步通知并处理
     * @return
     */
    @RequestMapping("/sync_notice")
    public String syncNotice(){
        System.out.println("===异步通知====");
        Boolean flag=true;
        if(flag){
            return "success";
        }else {
            return "failure";
        }
    }
}
