package com.tjnu.project_park.controller;

import com.tjnu.project_park.service.DeviceToParkService;
import com.tjnu.project_park.util.JsonResult;
import com.tjnu.project_park.util.ParkToStatue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * 已测试通过
 * @param
 * @return
 */
@RestController
@RequestMapping("/device_to_park")
public class DeviceToParkController extends BaseController{
    @Autowired
    private DeviceToParkService deviceToParkService;

    @RequestMapping("/initialization")
    public JsonResult<List> deviceToPark(Integer pid){
        List<ParkToStatue> data=deviceToParkService.initialization(pid);
        return new JsonResult<>(OK,data);
    }
    @RequestMapping("/get_statue")
    public JsonResult<Integer> getStatue(Integer pid,@RequestParam(value = "name") String parkName){
        Integer statue=deviceToParkService.getStatue(parkName,pid);
        return new JsonResult<>(OK,statue);
    }
}
