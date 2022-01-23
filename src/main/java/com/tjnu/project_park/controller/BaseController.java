package com.tjnu.project_park.controller;

import com.tjnu.project_park.service.ex.*;
import com.tjnu.project_park.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @param
 * @return
 */
public class BaseController {
    //操作成功的状态码
    public static final int OK=200;

    //请求处理方法。这个方法的返回值就是需要传递给前端的数据
    //自动将异常对象传递给此方法的参数列表上
    //当前项目中产生了异常。被统一拦截到此方法中，这个方法此时就充当的是请求处理方法，方法的返回值直接给到前端
    @ExceptionHandler(ServiceException.class) //用于统一处理抛出异常
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> result=new JsonResult<>(e);
        if(e instanceof InsertServiceException){
            result.setState(4000);
            result.setMessage("插入数据异常");
        }else if(e instanceof UsernameDuplicatedServiceException){
            result.setState(4001);
            result.setMessage("用户名冲突异常");
        }else if(e instanceof UsernameNotFoundServiceException){
            result.setState(4002);
            result.setMessage("用户名不存在");
        }else if(e instanceof PasswordErrorServiceException){
            result.setState(4003);
            result.setMessage("密码错误异常");
        }
        return result;
    }
}
