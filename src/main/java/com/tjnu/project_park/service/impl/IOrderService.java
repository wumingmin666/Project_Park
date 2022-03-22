package com.tjnu.project_park.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.tjnu.project_park.entity.Order;
import com.tjnu.project_park.mapper.DeviceToParkMapper;
import com.tjnu.project_park.mapper.OrderMapper;
import com.tjnu.project_park.service.OrderService;
import com.tjnu.project_park.service.ParkService;
import com.tjnu.project_park.service.ex.InsertServiceException;
import com.tjnu.project_park.service.ex.OrderNotFoundServiceException;
import com.tjnu.project_park.service.ex.UpdateStatueServiceException;
import com.tjnu.project_park.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * @param
 * @return
 */
@Service
public class IOrderService implements OrderService {
    @Autowired
    private DeviceToParkMapper deviceToParkMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public String booking(String parkName, Integer pid, String username, String plateName, String bookingStartTimeString, String bookingEndTimeString,String payMethod,String price) {
    //对接收参数先进行统一处理
        Integer parkId=Integer.valueOf(parkName);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date bookingEndTime= null;
        Date bookingStartTime=null;
        try {
            bookingEndTime = format.parse(bookingEndTimeString);
            bookingStartTime = format.parse(bookingStartTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //预定的时间长度，单位毫秒
        //Long timeLength=bookingEndTime.getTime()-bookingStartTime.getTime();
    //订单处理
        //一、支付
        //1、参数处理（前4个参数，如果正式上线需改）。
        //appId
        String APP_ID="2021000119611386";
        //应用私钥
        String APP_PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCCJ+4D1VNtwd0IXOwxVkwWmTTN8Yf8EkpzL682XkyTk3ceQohdI0ecfs4V/17mGR2K6rDC3blHcXA3jidFM/1mqyM31cSpUzA5YV8JEmKLZvjhCJ6S6dFPixHzkUSQhgy9tP2HLrv9Baz+DbnElDWOcz/MXScp6MuR3zG49OLjvMzveL4/ECVZQ3H8cMwUZpUd4oyuAhnKUv/hmYBuOpgH88PzM8vEFzUTzxSAqRROztOU0RLTXga51ONpFkdgVvstMZmixQOnwuBe8956ow3rVOQP+vm6PdC0q7ASjxGhSySWWpEQikBuGIw+beun9Q/nKJRwX0dxCzatM6nGvzXlAgMBAAECggEAJmvHFRaaBCJgLyHPD1lcKKedMNYFwGFd3NbMsA3yKPY1CZe2TVgmwYmlKFU0HFR5phJVj5CIKxDPgXbTHNiWCwKl6MuPvDQ0XhviQ/lLYb9J5JP4y4F+Ki7xY6cUSF1p0W5mEMu47p4ITQ6cMe1WSfC3eopdD0ALJ8ptER4pA/TBMYc3EIrcrlhDFciC+gMQSxMHpRO9tD0ADUfbB9L39VpXwAWEM51jb3LRwfkdD70vO2YvxHI2NOUutyVZJoziuwxxWOaKO7c+i4XwATexDE4mFzIsM6Pgy4UwWctfI0qNZq5pIJLeR3xhMToqoJDRDc5zeSqISKdZN7liVMXUAQKBgQDOHyN7grPs97dqv2rtmfZlMSF4Yb5tlZYXPfan9XWI0CHTihIcnGDIJN3CCECdXCvbUNzRxC/gOzg8RW71uqMV3YJiRp1oBjVFqYd7mMZbzqAn/Tj9vPwcnUM6JBRTcy72hiacXve7xpeFTpyklHoXGCU8cO28DwP8DTDTYmiqgQKBgQChpts4hfmKfL2vWJrwTx0ieoMPsQa9K61sMfEgTZrMC2g4vGAB3v21n2/qsF7zKkpFdRTlo62Snh0lzGVxTR9a2FYaGRD90Hu101lxedSeytvsubK4xQQ+uarxidySvdkN8cUsk1lQq+o+oA8n3RntocpLOFgXjfc4GL0FXbFxZQKBgQC4gIj7WsCBoi8tP1CQhFtYswS5xAx3/QkPnuh16bKel2df+lxB/fxnyxAxSb/E26dqNlSi89DDD19EHAa5sKmgvdmi7ICjh8MRqzFaAiO0NB2KDhGAlzS4zFBL79W96QGlq+lN2Xg5PgeNTRPMAhfTKkrScmdrCanQaWJGJPV5gQKBgB6H/zurwjfv23u0xn7A0oXikcJy8wzjpPucayhov+Xt+Z9NpOx1i4G0PVUcPrK6uBBqTqoYwf3BM0wiUL6XjGaCGbEQLu9hYxwycOBH3GqpceRCJolTXLvkIW9BVJG6nbZOhakno2TjM3jkjjg/QFhriGOwnqLEQ38q3PiYe50pAoGABnwn7JmPUQDJOSGZdE6oh03AccVM/2X6TfUnH/dKGUTGmcKJ3PFFawdE+ElSLyyK19o1IsYJpT90LY/AjebPlqWHAAsfPwkH9GsUQQ4t8rE0iRH2xI3j9CMNMr7ByoiLsku6x0G8Z89FkL4One+7RwSOx8hzg/nFOXnwnriNqUY=";
        //支付宝公钥
        String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiTffq83rZxA+0BaaBOsWps1bVnAXf1/yhDWrKyeXa76TcjJ4aNpkrk+ILNlbn6f7VNVp8T3CzFhvNRaMI522VedsxCXoHmXqlGYR75BQZWB7q11hSa3x8gJEFfFBwMS0vy6m8UAymTRaeTsvvfezOkJMAMkzp8wzJHjxISenoRgHCPkMbQVGyEDyfesTs0wpv1t+J+iTgbLpdPuFgzpa7xkocrjwUoQ/HLWzZmNCOlNcmQK8JvJGnpZ+PBPlBP8fjYUKVpaDo+wj7yL4Bs+POM8K+RAKO9cF7Sn2GUYo80wxGTSw0UKAISRRa42tzX+wJ5QbRe+a9hw128kKpK2O6wIDAQAB";
        //支付宝服务端网关
        String serverUrl="https://openapi.alipaydev.com/gateway.do";
        //商户网站唯一订单号由车牌号（plateName）加UUID构成
        String outTradeNo=plateName+UUID.randomUUID().toString().replace("-","");;
        //支付金额,每小时5.5元
        //String totalAmount=String.valueOf((timeLength/3600)*5.5);
        String totalAmount=price;
        //商品的标题/交易标题/订单标题/订单关键字等
        String subject="预定车位";
        //支付宝服务器主动通知商户服务器里指定的页面http/https路径。
        String notifyUrl="http://39.105.152.242:8081/order/sync_notice";
        //创建alipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, APP_ID, APP_PRIVATE_KEY, "json","UTF-8",ALIPAY_PUBLIC_KEY,"RSA2");
        String orderInfo="";
        //2、调用支付接口
        if(payMethod=="App"){
            //调用支付接口,App支付
            orderInfo=aliPayApp(alipayClient,outTradeNo,totalAmount,subject,notifyUrl);
        }else if(payMethod=="H5"){
            //调用接口，H5支付
            orderInfo=aliPayH5(alipayClient,outTradeNo,totalAmount,subject,notifyUrl);
        }

        //二、修改车位状态为预定
        try{
            Integer row=deviceToParkMapper.updateStatueByParkId(parkId,pid);
        }catch (RuntimeException e){
            throw new UpdateStatueServiceException("更新状态异常");
        }

        //三、添加订单
        //补充订单实体类信息
        Order order=new Order();
        order.setBookingEndTime(bookingEndTime);
        order.setBookingStartTime(new Date());
        order.setCreatedTime(new Date());
        order.setPid(pid);
        order.setBookingUser(username);
        order.setIsExist(1);
        order.setParkId(parkId);
        order.setPlateNumber(plateName);
        order.setOutTradeNo(outTradeNo);
        //插入并捕获异常抛出
        try{
            orderMapper.insert(order);
        }catch (Exception e){
            throw new InsertServiceException("插入异常");
        }

        //四、返回订单信息
        return orderInfo;
    }

    @Override
    public HashMap myBooking(String username) {
        //通过用户名查询订单
        Order myOrder=orderMapper.findOrderByUsername(username);
        if(myOrder==null){
            throw new OrderNotFoundServiceException("订单不存在异常");
        }
        //1、判断订单是否过期存在，考虑通过时间或is_delete(暂时省略，之后写该步骤，还需添加异常)
        //2、获取Park_id和pid
        Integer parkId=myOrder.getParkId();
        Integer pid=myOrder.getPid();
        String parkName=parkId.toString();
        HashMap<String,Object> result=new HashMap<>();
        result.put("pid",pid);
        result.put("parkName",parkName);
        return result;
    }

    private String aliPayApp(AlipayClient alipayClient,String outTradeNo,String totalAmount,String subject,String notifyUrl){
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        request.setNotifyUrl(notifyUrl);
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", outTradeNo);
        bizContent.put("total_amount", totalAmount);
        bizContent.put("subject", subject);
        request.setBizContent(bizContent.toString());
        AlipayTradeAppPayResponse response = null;
        try {
            response = alipayClient.sdkExecute(request);
        } catch (AlipayApiException e) {
            e.printStackTrace();
            System.out.println("=====支付出现异常=====");
        }
        if(response.isSuccess()){
            System.out.println(response.getBody());
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        return response.getBody();
    }
    private String aliPayH5(AlipayClient alipayClient,String outTradeNo,String totalAmount,String subject,String notifyUrl){
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
        alipayRequest.setReturnUrl("");
        alipayRequest.setNotifyUrl(notifyUrl);//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent("{" +
                " \"out_trade_no\":"+outTradeNo+"," +
                " \"total_amount\":"+totalAmount+"," +
                " \"subject\":"+subject+"," +
                " \"product_code\":\"QUICK_WAP_WAY\"" +
                " }");//填充业务参数
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
            return form;
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        return "支付失败";
    }

}
