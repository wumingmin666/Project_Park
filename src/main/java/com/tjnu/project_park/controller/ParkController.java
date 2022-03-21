package com.tjnu.project_park.controller;

import com.tjnu.project_park.entity.Park;
import com.tjnu.project_park.service.ParkService;
import com.tjnu.project_park.util.JsonResult;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @param
 * @return
 */
@RestController
@RequestMapping("/park")
public class ParkController extends BaseController{
    @Autowired
    private ParkService parkService;

    @RequestMapping("/park_descride")
    public JsonResult<Object> parkDescride(String longitude,String latitude){
        HashMap<String,Park> result=parkService.findParkByDistance(longitude,latitude);
        Object parkArray[]=result.values().toArray();
        System.out.println(parkArray[0]);
        System.out.println(parkArray[1]);
        return new JsonResult<Object>(OK,parkArray);
    }



}
