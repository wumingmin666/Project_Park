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
    public User findByUsername(String username);
}
