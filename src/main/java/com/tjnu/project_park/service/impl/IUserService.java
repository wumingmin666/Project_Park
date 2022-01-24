package com.tjnu.project_park.service.impl;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.tjnu.project_park.entity.User;
import com.tjnu.project_park.mapper.UserMapper;
import com.tjnu.project_park.service.UserService;
import com.tjnu.project_park.service.ex.InsertServiceException;
import com.tjnu.project_park.service.ex.PasswordErrorServiceException;
import com.tjnu.project_park.service.ex.UsernameDuplicatedServiceException;
import com.tjnu.project_park.service.ex.UsernameNotFoundServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.UUID;

/**
 * 1、用户业务层实现类
 * 2、接口的实现方法的注释到接口处查看
 */
@Service
public class IUserService implements UserService {



    @Autowired
    private UserMapper userMapper;

    @Override
    public void register(User user) {
        //判断用户有是否已被占用
        User result=userMapper.findByUsername(user.getUsername());
        if(result!=null){
            throw new UsernameDuplicatedServiceException("用户名冲突异常");
        }

        //密码加密处理的实现：md5算法
        //串+password+串--------》md5算法加密,连续加密三次
        //盐值+password+盐值-------盐值就是一个随机的字符串
        String oldPassword=user.getPassword();
        //获取盐值
        String salt= UUID.randomUUID().toString().toUpperCase();
        //补全数据：盐值的记录
        user.setSalt(salt);
        //将密码和盐值作为一个整体进行加密,忽略原有密码强度提升了数据的安全性
        String md5Password=getMD5Password(oldPassword,salt);
        //将加密之后的密码重新补全设置到user对象中
        user.setPassword(md5Password);
        //补充实体类信息：创建时间
        user.setCreatedTime(new Date());
        user.setModifiedTime(new Date());

        //将用户信息插入到数据库、row==1时正常
        Integer rows=userMapper.insert(user);
        if(rows!=1){
            throw new InsertServiceException("插入用户信息异常");
        }
    }

    @Override
    public User login(String username,String password) {
        User result=userMapper.findByUsername(username);

        //用户不存在
        if(result==null){
            throw new UsernameNotFoundServiceException("用户名不存在");
        }

        //用户存在，进行密码验证
        String salt=result.getSalt();
        String newPassword=getMD5Password(password,salt);
        if(!result.getPassword().equals(newPassword)){   //密码错误
            throw new PasswordErrorServiceException("密码错误异常");
        }

        return result;
    }



    /**
     * 定义一个md5算法的加密处理
     */
    public String getMD5Password(String password,String salt){
        //md5加密算法方法的调用（加密三次）
        for(int i=0; i<3;i++) {
            password= DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }

}
