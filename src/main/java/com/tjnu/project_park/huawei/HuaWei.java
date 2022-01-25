package com.tjnu.project_park.huawei;

import com.alibaba.fastjson.JSONObject;
import com.tjnu.project_park.huawei.ex.GetTokenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @param
 * @return
 */
@Component
@Slf4j
public class HuaWei {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 向华为云平台发送请求获取token
     * @return 返回token
     */
    public String getToken(){
        //url
        String url="https://iam.cn-north-4.myhuaweicloud.com/v3/auth/tokens";
        //添加请求头
        HttpHeaders header=new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        //添加请求体
        String body="{\"auth\":{\"identity\": {\"methods\": [\"password\"],\"password\": {\"user\": {\"name\": \"wmm01\",\"password\": \"13850982142wmm.\",\"domain\": {\"name\": \"hw_008615059997639_01\"}}}},\"scope\": {\"project\": {\"name\": \"cn-north-4\"}}}}";
        HttpEntity<String> request=new HttpEntity<>(body,header);
        //发送请求
        ResponseEntity<String> responseEntity=restTemplate.postForEntity(url,request,String.class);
        //从响应头中获取token
        HttpHeaders responseHeader = responseEntity.getHeaders();
        String token = responseHeader.get("X-Subject-Token").get(0);
        if(token==null){
            throw new GetTokenException("获取token失败");
        }
        return token;
    }

    /**
     * h获取项目id
     * @return
     */
    public String getProjectId(){
        String url="https://iam.cn-north-4.myhuaweicloud.com/v3/auth/projects";
        String token=stringRedisTemplate.opsForValue().get("X-Auth-Token");

        HttpHeaders header=new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.set("X-Auth-Token",token);
        header.set("Content-Type","application/json");

        HttpEntity<String> request=new HttpEntity<>(null,header);
        ResponseEntity<String> responseEntity=restTemplate.exchange(url, HttpMethod.GET,request,String.class);
        String projectBody=responseEntity.getBody();
        JSONObject jsonObject=JSONObject.parseObject(projectBody);
        List<JSONObject> projects=(List<JSONObject>) jsonObject.get("projects");
        for(int i=0;i<projects.size();i++){
            if(projects.get(i).get("name").equals("cn-north-4")){
                return (String)projects.get(i).get("id");
            }
        }
        return null;
    }

    /**
     * amqp通道推送
     * @throws Exception
     */
    public void amqpPush() throws Exception {
        HwIotAmqpJavaClientDemo hwIotAmqpJavaClientDemo = new HwIotAmqpJavaClientDemo();
        hwIotAmqpJavaClientDemo.amqp();
    }
}
