package com.tjnu.project_park.huawei;

import com.alibaba.fastjson.JSONObject;
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

    public String getToken(){
        String url="https://iam.cn-north-4.myhuaweicloud.com/v3/auth/tokens";
        HttpHeaders header=new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        String body="{\"auth\":{\"identity\": {\"methods\": [\"password\"],\"password\": {\"user\": {\"name\": \"wmm01\",\"password\": \"13850982142wmm.\",\"domain\": {\"name\": \"hw_008615059997639_01\"}}}},\"scope\": {\"project\": {\"name\": \"cn-north-4\"}}}}";
        HttpEntity<String> request=new HttpEntity<>(body,header);
        ResponseEntity<String> responseEntity=restTemplate.postForEntity(url,request,String.class);
        HttpHeaders responseHeader = responseEntity.getHeaders();
        String token = responseHeader.get("X-Subject-Token").get(0);
        return token;
    }

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

    public void amqpPush() throws Exception {
        HwIotAmqpJavaClientDemo hwIotAmqpJavaClientDemo = new HwIotAmqpJavaClientDemo();
        hwIotAmqpJavaClientDemo.amqp();

    }
}
