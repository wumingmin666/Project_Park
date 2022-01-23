package com.tjnu.project_park.mapper;

import com.tjnu.project_park.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @param
 * @return
 */
//@SpringBootTest:表示标注当前的类是一个测试类，不会随同项目一块打包
@SpringBootTest
//@RunWith：表示启动这个单元测试类（单元测试类是不能够运行的），需要传递一个参数，必须是SpringRuner的实例类型
@RunWith(SpringRunner.class)
@MapperScan("com.tjnu.project_park.mapper")
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert(){
        User user=new User();
        user.setUsername("wll");
        user.setPassword("wmm");
        user.setCreateTime(new Date());
        System.out.println(userMapper.insert(user));
    }
    @Test
    public void findByUsername(){
//        User user=userMapper.findByUsername("wll");
//        System.out.println(user);
        String str="/tjnu01/ddd";
        int first = str.indexOf("/");
        int last = str.lastIndexOf("/");
        String aa = str.substring(first+1, last);
        System.out.println(aa);
    }
}
