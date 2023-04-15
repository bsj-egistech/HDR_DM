package com.defr.hotdealradar.controller;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HotdealradarController {

    //핫딜 데이터를 반환
    @ResponseBody
    @RequestMapping(value = "/getHotdealData", method = RequestMethod.POST)
    public Map getHotdealData(HttpServletRequest req, @RequestBody Map<String, Object> reqParam) {
        System.out.println("getHotdealData");
        System.out.println("requestURL : " + req.getRequestURL());

        System.out.println("reqParam : " + reqParam);



        Map result = new HashMap<String, Object>();
        result.put("test1", "test1");
        result.put("test2", "test2");

        return result;
    }





}



