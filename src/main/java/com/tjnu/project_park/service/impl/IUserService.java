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

import java.util.Date;

/**
 * @param
 * @return
 */
@Service
public class IUserService implements UserService {



    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册方法
     * @param user 用户信息实体类，除了username和password外其它为null
     */
    @Override
    public void register(User user) {
        //判断用户有是否已被占用
        User result=userMapper.findByUsername(user.getUsername());
        if(result!=null){
            throw new UsernameDuplicatedServiceException("用户名冲突异常");
        }
        //补充实体类信息：创建时间
        user.setCreateTime(new Date());
        //将用户信息插入到数据库
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
        if(!result.getPassword().equals(password)){   //密码错误
            throw new PasswordErrorServiceException("密码错误异常");
        }

        return result;
    }



}
