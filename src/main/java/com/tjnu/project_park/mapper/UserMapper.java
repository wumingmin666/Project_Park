package com.tjnu.project_park.mapper;

import com.tjnu.project_park.entity.User;
import com.tjnu.project_park.util.JsonResult;

/**
 * @param
 * @return
 */
public interface UserMapper {
    /**
     * 插入用户数据到数据库
     * @param user 用户信息实体类
     * @return 返回受影响的行数
     */
    public Integer insert(User user);

    /**
     * 通过用户名称查询用户的信息
     * @param username 用户名称
     * @return 返回查询到的用户信息映射为实体类
     */
    public User findByUsername(String username);
}
