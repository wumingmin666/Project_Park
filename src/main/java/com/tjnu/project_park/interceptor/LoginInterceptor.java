package com.tjnu.project_park.interceptor;

import com.tjnu.project_park.entity.User;
import com.tjnu.project_park.mapper.UserMapper;
import com.tjnu.project_park.util.JWTUtil;
import com.tjnu.project_park.util.ex.ResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @param
 * @return
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private UserMapper userMapper;
    /**
     * 检测全局session对象是否有uid数据，如果有则放行，如果没有重定向到登录页面
     * @param request 请求对象
     * @param response 响应对象
     * @param handler 处理器（url+Contraller:映射）
     * @return 如果返回值为true表示放行当前请求，如果返回值为false则表示拦截当前请求
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //Object obj=request.getParameter("access_token");
        Object obj=request.getHeader("access_token");
        if(obj==null){
            return false;
        }
        JWTUtil jwtUtil=new JWTUtil();
        try {
            String username=jwtUtil.vaildToken((String) obj);
            //判断token中的username是否存在，若存在则放行
            User user=userMapper.findByUsername(username);
            if (user!=null){
                //放行
                return true;
            }

        }catch (ResultException e){
            e.printStackTrace();
            //重定向到登录页面
        }
        return false;
    }
}
