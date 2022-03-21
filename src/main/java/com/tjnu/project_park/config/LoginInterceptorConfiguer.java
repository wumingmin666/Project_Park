package com.tjnu.project_park.config;

import com.tjnu.project_park.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @param
 * @return
 */
@Configuration
public class LoginInterceptorConfiguer implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor interceptor;
    //配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //创建自定义的拦截器对象
        //HandlerInterceptor interceptor=new LoginInterceptor();
        //配置白名单：存放在一个List集合中
        List<String> patterns=new ArrayList<>();
        patterns.add("/user/register");
        patterns.add("/user/login");
        patterns.add("/user/wxlogin");
        patterns.add("/device_to_park/**");
        patterns.add("/park/park_descride");
        patterns.add("/order/**");
        patterns.add("/map/**");
        //patterns.add("/order/**");

//        patterns.add("/tjnu01/data/**");
//        patterns.add("/tjnu01/image/**");
//        patterns.add("/tjnu01/index.html");
//        patterns.add("/tjnu01/tjnu01.html");
        patterns.add("/css/**");
        patterns.add("/image/**");
        patterns.add("/lib/**");
        patterns.add("/tjnu01/**");
        patterns.add("/tjnu02/**");
        


        //完成拦截器的注册
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(interceptor);
        //添加拦截名单
        interceptorRegistration.addPathPatterns("/**").excludePathPatterns(patterns);
    }
}
