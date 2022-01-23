package com.tjnu.project_park.listener;

import com.alibaba.fastjson.JSONObject;
import com.tjnu.project_park.huawei.HuaWei;
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
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        //获取token
        String token=huaWei.getToken();
        //保存到redis中
        stringRedisTemplate.opsForValue().set("X-Auth-Token",token);

        //获取项目id
        String projectId= huaWei.getProjectId();
        //保存到redis中
        stringRedisTemplate.opsForValue().set("project_id",projectId);

        try {
            huaWei.amqpPush();
            //System.out.println("111");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
