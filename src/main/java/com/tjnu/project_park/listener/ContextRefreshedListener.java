package com.tjnu.project_park.listener;

import com.alibaba.fastjson.JSONObject;
import com.tjnu.project_park.huawei.HuaWei;
import com.tjnu.project_park.huawei.ex.GetTokenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


/**
 * @param
 * @return
 */
@Component
public class ContextRefreshedListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private HuaWei huaWei;

    /**
     *设置监听，在服务器启动时，向华为云平台获取token，设置推送等信息。
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //获取token
        try{
            String token=huaWei.getToken();
            //保存到redis中
            stringRedisTemplate.opsForValue().set("X-Auth-Token",token);
        } catch (GetTokenException e){
            e.printStackTrace();
        }


        //获取项目id
        String projectId= huaWei.getProjectId();
        //保存到redis中
        stringRedisTemplate.opsForValue().set("project_id",projectId);

        try {
            //设置amqp自动推送
            huaWei.amqpPush();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("设置推送失败");
        }
    }
}
