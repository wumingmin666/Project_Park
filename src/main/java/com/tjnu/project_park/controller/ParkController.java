package com.tjnu.project_park.controller;

import com.tjnu.project_park.entity.Park;
import com.tjnu.project_park.service.ParkService;
import com.tjnu.project_park.util.JsonResult;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public JsonResult<JSONObject> parkDescride(String location){
        //HashMap<Park,Long> result=parkService.findParkByDistance(location);
        return null;
    }



}
