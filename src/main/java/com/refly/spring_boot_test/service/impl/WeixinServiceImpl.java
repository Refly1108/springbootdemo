package com.refly.spring_boot_test.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.refly.spring_boot_test.config.WeiXinConfig;
import com.refly.spring_boot_test.model.SignatureResponse;
import com.refly.spring_boot_test.service.WeixinService;
import com.refly.spring_boot_test.service.WishService;
import com.refly.spring_boot_test.util.SHACreater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class WeixinServiceImpl implements WeixinService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public SignatureResponse getSignature(String timeStap) {
        Map<String, Object> tokenBody,signatureBody;
        ObjectMapper mapper = new ObjectMapper();
        SignatureResponse signatureResponse = new SignatureResponse();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "text/plain");
        String tokenUrl = WeiXinConfig.Token_URL+WeiXinConfig.grant_type+"&appid="+WeiXinConfig.appid+"&secret="+WeiXinConfig.secret;
        HttpEntity<String> httpEntity = new HttpEntity((MultiValueMap)httpHeaders);
        ResponseEntity<String> tokenResponseEntity = restTemplate.exchange(tokenUrl, HttpMethod.GET,httpEntity,String.class,new Object[0]);
        try {
            tokenBody = mapper.readValue(tokenResponseEntity.getBody(),Map.class);
            System.out.println(tokenBody.toString());
            System.out.println(tokenBody.get("access_token"));
            if (tokenBody.get("access_token")== "" || tokenBody.get("access_token") == null){
                signatureResponse.setStatus(Integer.valueOf(500));
                return signatureResponse;

            }

            String signatureUrl = WeiXinConfig.API_Ticket_URL + tokenBody.get("access_token") +"&type=jsapi";
            System.out.println(signatureUrl);
            ResponseEntity<String> signatureResponseEntity = restTemplate.exchange(signatureUrl, HttpMethod.GET, httpEntity, String.class, new Object[0]);
            signatureBody = mapper.readValue(signatureResponseEntity.getBody(), Map.class);
            System.out.println(signatureBody.toString());
            System.out.println(signatureBody.get("ticket"));
            if (signatureBody.get("ticket") == "" || signatureBody.get("ticket") == null) {
                signatureResponse.setStatus((Integer.valueOf(500)));
                return signatureResponse;
            }
            signatureResponse.setSignature(SHACreater.generateSignatureSHA1((String)signatureBody.get("ticket"),timeStap));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }


        return signatureResponse;
    }
//
//    //获取accesstoken
//    String accessTokenResult= HttpUtil.get("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=xxxxxxx&corpsecret=xxxxxxxxx");
//
//    JSONObject accessTokenResultJson = JSONUtil.parseObj(accessTokenResult);
//
//
//    //通过accesstoken获取jsapi_ticket
//    String jsapiTicketResult = HttpUtil.get("https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token="+accessTokenResultJson.get("access_token"));
//
//    JSONObject jsapiTicketResultJson = JSONUtil.parseObj(jsapiTicketResult);
//
//
//    //拼接字符串
//    String str = "jsapi_ticket="+jsapiTicketResultJson.get("ticket")+"&noncestr=const&timestamp="+timeStap+"&url=http://htjoa.nrnet.cn/dc14/const/sign";
//
//    //可以在微信企业号开发网页中验证  <https://developer.work.weixin.qq.com/document/path/90506>
//
//    JSONObject jsonObject = JSONUtil.createObj();
//        jsonObject.putOnce("code", "200");
//        jsonObject.putOnce("msg", "成功");
//        jsonObject.putOnce("jsapi_ticket",jsapiTicketResultJson.get("ticket"));
//        jsonObject.putOnce("str",str);
//        jsonObject.putOnce("signature",getSha1(str));  //调用上述的加密算法
//        return jsonObject;
}
