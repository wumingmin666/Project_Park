package com.tjnu.project_park.controller;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.tjnu.project_park.util.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @param
 * @return
 */
@RestController
@RequestMapping("/tests")
public class testcontroller extends BaseController{

    @RequestMapping("/test1")
    public JsonResult<String> test01(){
        String APP_ID="2021000119611386";
        String APP_PRIVATE_KEY="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCCJ+4D1VNtwd0IXOwxVkwWmTTN8Yf8EkpzL682XkyTk3ceQohdI0ecfs4V/17mGR2K6rDC3blHcXA3jidFM/1mqyM31cSpUzA5YV8JEmKLZvjhCJ6S6dFPixHzkUSQhgy9tP2HLrv9Baz+DbnElDWOcz/MXScp6MuR3zG49OLjvMzveL4/ECVZQ3H8cMwUZpUd4oyuAhnKUv/hmYBuOpgH88PzM8vEFzUTzxSAqRROztOU0RLTXga51ONpFkdgVvstMZmixQOnwuBe8956ow3rVOQP+vm6PdC0q7ASjxGhSySWWpEQikBuGIw+beun9Q/nKJRwX0dxCzatM6nGvzXlAgMBAAECggEAJmvHFRaaBCJgLyHPD1lcKKedMNYFwGFd3NbMsA3yKPY1CZe2TVgmwYmlKFU0HFR5phJVj5CIKxDPgXbTHNiWCwKl6MuPvDQ0XhviQ/lLYb9J5JP4y4F+Ki7xY6cUSF1p0W5mEMu47p4ITQ6cMe1WSfC3eopdD0ALJ8ptER4pA/TBMYc3EIrcrlhDFciC+gMQSxMHpRO9tD0ADUfbB9L39VpXwAWEM51jb3LRwfkdD70vO2YvxHI2NOUutyVZJoziuwxxWOaKO7c+i4XwATexDE4mFzIsM6Pgy4UwWctfI0qNZq5pIJLeR3xhMToqoJDRDc5zeSqISKdZN7liVMXUAQKBgQDOHyN7grPs97dqv2rtmfZlMSF4Yb5tlZYXPfan9XWI0CHTihIcnGDIJN3CCECdXCvbUNzRxC/gOzg8RW71uqMV3YJiRp1oBjVFqYd7mMZbzqAn/Tj9vPwcnUM6JBRTcy72hiacXve7xpeFTpyklHoXGCU8cO28DwP8DTDTYmiqgQKBgQChpts4hfmKfL2vWJrwTx0ieoMPsQa9K61sMfEgTZrMC2g4vGAB3v21n2/qsF7zKkpFdRTlo62Snh0lzGVxTR9a2FYaGRD90Hu101lxedSeytvsubK4xQQ+uarxidySvdkN8cUsk1lQq+o+oA8n3RntocpLOFgXjfc4GL0FXbFxZQKBgQC4gIj7WsCBoi8tP1CQhFtYswS5xAx3/QkPnuh16bKel2df+lxB/fxnyxAxSb/E26dqNlSi89DDD19EHAa5sKmgvdmi7ICjh8MRqzFaAiO0NB2KDhGAlzS4zFBL79W96QGlq+lN2Xg5PgeNTRPMAhfTKkrScmdrCanQaWJGJPV5gQKBgB6H/zurwjfv23u0xn7A0oXikcJy8wzjpPucayhov+Xt+Z9NpOx1i4G0PVUcPrK6uBBqTqoYwf3BM0wiUL6XjGaCGbEQLu9hYxwycOBH3GqpceRCJolTXLvkIW9BVJG6nbZOhakno2TjM3jkjjg/QFhriGOwnqLEQ38q3PiYe50pAoGABnwn7JmPUQDJOSGZdE6oh03AccVM/2X6TfUnH/dKGUTGmcKJ3PFFawdE+ElSLyyK19o1IsYJpT90LY/AjebPlqWHAAsfPwkH9GsUQQ4t8rE0iRH2xI3j9CMNMr7ByoiLsku6x0G8Z89FkL4One+7RwSOx8hzg/nFOXnwnriNqUY=";
        String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAiTffq83rZxA+0BaaBOsWps1bVnAXf1/yhDWrKyeXa76TcjJ4aNpkrk+ILNlbn6f7VNVp8T3CzFhvNRaMI522VedsxCXoHmXqlGYR75BQZWB7q11hSa3x8gJEFfFBwMS0vy6m8UAymTRaeTsvvfezOkJMAMkzp8wzJHjxISenoRgHCPkMbQVGyEDyfesTs0wpv1t+J+iTgbLpdPuFgzpa7xkocrjwUoQ/HLWzZmNCOlNcmQK8JvJGnpZ+PBPlBP8fjYUKVpaDo+wj7yL4Bs+POM8K+RAKO9cF7Sn2GUYo80wxGTSw0UKAISRRa42tzX+wJ5QbRe+a9hw128kKpK2O6wIDAQAB";
        String CHARSET="UTF-8";
        String outTradeNo="70501111111S001111119";
        String totalAmount="125";
        String subject="预定车位";
        String serverUrl="https://openapi.alipaydev.com/gateway.do";
        String notifyUrl="http://wmm.vaiwan.com/tests/test2";

        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl,APP_ID,APP_PRIVATE_KEY,"json",CHARSET,ALIPAY_PUBLIC_KEY,"RSA2");
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
            System.out.println("调用成功");
        } else {
            System.out.println("调用失败");
        }
        System.out.println(response.getBody());
        return new JsonResult<>(OK,response.getBody());
    }

    @RequestMapping("/test2")
    public String test2(){
        //String CHARSET="UTF-8";
        //String ALIPAY_PUBLIC_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkV6d4SDZzljcx1d/b3UEy3QxcVHISSG6ixlzjl88w3fV0q/Oj6R/ojwk3rGP0FxONYgXVDJ179pbxensIrdDqEPK7su8IYKIfs+eJy9UTrIqrPCqrekvB8ZbmrVx3wAnkDb6S29zOL62ybeU9h6O3c1zhUNXh2dyfPRVGwNvNbRg6XijSzIfEepgVQPon3EexZ0b+w6O88ADa4CF1skwoW3KMTGS1oizbUDKzyRyahAEqqvlYI77dlKPpWkigzYTrKpdDMu6AYR/viBF3NTk0RUJWBzDRkYoXG5jitcqvTCwu0LzYdMilKIc5Mcki3WG8Pe2XkzIjKln+H3yASDdFwIDAQAB";
        //Boolean flag = AlipaySignature.rsaCheckV1(result, ALIPAY_PUBLIC_KEY, CHARSET, "RSA2", true);
        System.out.println("=======");
        Boolean flag=true;
        if(flag){
            return "success";
        }else {
            return "failure";
        }
    }
}
