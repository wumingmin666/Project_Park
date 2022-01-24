package com.tjnu.project_park.controller;

import com.tjnu.project_park.entity.Park;
import com.tjnu.project_park.entity.User;
import com.tjnu.project_park.huawei.HuaWei;
import com.tjnu.project_park.service.ParkService;
import com.tjnu.project_park.service.UserService;
import com.tjnu.project_park.util.JWTUtil;
import com.tjnu.project_park.util.JsonResult;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @param
 * @return
 */
@RestController
@RequestMapping("/user")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;
    @Autowired
    private ParkService parkService;
    @Autowired
    private HuaWei huaWei;

    /**
     * 用户注册方法
     * @param user 用户信息实体类：包括username和password
     * @return Json数据，成功时返回状态码200
     */
    @RequestMapping("/register")
    public JsonResult<Void> register(User user){
        userService.register(user);
        return new JsonResult<>(OK);
    }

    /**
     * 用户登入方法
     * @param username 用户名
     * @param password 密码
     * @return 返回token,及用户相关的车位及地图信息
     */
    @RequestMapping("/login")
    public JsonResult<JSONObject> login(String username,String password, String location){
        //判断用户是否存在及密码是否正确
        User user=userService.login(username,password);
        //用户存在且密码正确，生成token
        String token=new JWTUtil().createJWT(user.getUsername());
        //将token放入返回的参数中
        HashMap<String,String> json=new HashMap<>();
        json.put("access_token",token);
        //添加地图信息
        HashMap<String,Park> parks=parkService.findParkByDistance(location);
        String parksJson = new JSONObject(parks).toJSONString();
        json.put("parks",parksJson);

        //转为json格式
        JSONObject jsonObj = new JSONObject(json);
        //返回参数
        return new JsonResult<JSONObject>(OK,jsonObj);
    }


}
