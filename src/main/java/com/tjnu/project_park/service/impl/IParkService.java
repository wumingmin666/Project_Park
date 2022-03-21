package com.tjnu.project_park.service.impl;

import com.tjnu.project_park.entity.Park;
import com.tjnu.project_park.mapper.ParkMapper;
import com.tjnu.project_park.service.ParkService;
import com.tjnu.project_park.service.ex.ParkNotFoundByPidServiceException;
import com.tjnu.project_park.service.ex.ParkNotFoundServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @param
 * @return
 */
@Service
public class IParkService implements ParkService {
    @Autowired
    private ParkMapper parkMapper;

    @Override
    public HashMap<String,Park> findParkByDistance(String longitude,String latitude) {
        List<Park> parkList=parkMapper.findAllPark();
        //地图信息为空
        if(parkList==null){
            throw new ParkNotFoundServiceException("地图信息为空异常");
        }
        //根据location计算距离最短的5个停车场
//        HashMap<Park,Long> distances=null;
//        for (Park park:parkList) {
//            //计算距离
//            Long distance=getDistance(park.getLocation(),location);
//            distances.put(park,distance);
//        }
        HashMap<String,Park> parkMin=new HashMap<>();
        //获取distances中值最小的5个键值对,存入parkMin中(之后补充)

        //模拟数据
        parkMin.put("15",parkList.get(0));
        parkMin.put("14",parkList.get(1));

        return parkMin;
    }

    @Override
    public String getUrlByPid(Integer pid) {
        Park park=parkMapper.findParkByPid(pid);
        if(park==null){
            throw new ParkNotFoundByPidServiceException("通过Pid无法找到对应的地图");
        }
        String url=park.getUrl();
        return url;
    }


    /**
     * 计算用户与停车场位置的方法
     * @param l1 停车场位置（经纬度）
     * @param l2 用户位置（经纬度）
     * @return 返回距离
     */
    public Long getDistance(String l1,String l2){
        return null;
    }
}
