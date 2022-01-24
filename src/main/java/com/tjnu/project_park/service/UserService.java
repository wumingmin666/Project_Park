package com.tjnu.project_park.service;

import com.tjnu.project_park.entity.User;
import org.springframework.stereotype.Service;

/**
 * @param
 * @return
 */
public interface UserService {
    /**
     * 用户注册方法
     * @param user 用户信息实体类，除了username和password外其它为null
     */
    public void register(User user);

    /**
     * 用户登录方法
     * @param username 用户名
     * @param password 用户密码
     * @return 当密码和用户名正确时方法返回对应的用户信息实体类
     */
    public User login(String username,String password);
}
