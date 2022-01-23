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
    public User login(String username,String password);
}
