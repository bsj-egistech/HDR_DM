package com.defr.hotdealradar.controller;

import com.defr.hotdealradar.common.StoredValue;
import com.defr.hotdealradar.service.DealManageService;
import com.defr.hotdealradar.vo.DealVo;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HotdealradarController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    DealManageService dealManageService;


    //핫딜 데이터를 반환
    @ResponseBody
    @RequestMapping(value = "/getHotdealData", method = RequestMethod.POST)
    public HashMap getHotdealData(HttpServletRequest req, @RequestBody HashMap<String, Object> reqParam) {
        System.out.println("getHotdealData");
        System.out.println("requestURL : " + req.getRequestURL());

        System.out.println("reqParam : " + reqParam);

        /*Map result = new HashMap<String, Object>();
        result.put("test1", "test1");
        result.put("test2", "test2");*/

        return StoredValue.hotDealData;
    }

    @ResponseBody
    @RequestMapping(value = "/getDealAll", method = RequestMethod.POST)
    public List<DealVo> getDealAll(HttpServletRequest req, @RequestBody HashMap<String, Object> reqParam) {
        System.out.println("getDealAll");
        System.out.println("requestURL : " + req.getRequestURL());

        List<DealVo> resultMap = dealManageService.selectDealAll();

        logger.info("resultMap : " + resultMap);


        return resultMap;
    }

    @ResponseBody
    @RequestMapping(value = "/insertDeal", method = RequestMethod.POST)
    public int insertDeal(HttpServletRequest req, @RequestBody HashMap<String, Object> reqParam) {
        System.out.println("insertDeal");
        System.out.println("requestURL : " + req.getRequestURL());

        logger.info("reqParam : " + reqParam);

        DealVo vo = new DealVo();
        vo.setId( (String) reqParam.get("id") );
        vo.setNumber( (String) reqParam.get("number") );
        vo.setSite( (String) reqParam.get("site") );
        vo.setTitle( (String) reqParam.get("title") );
        vo.setSeller( (String) reqParam.get("seller") );

        int resultCnt = dealManageService.insertDeal(vo);

        logger.info("resultCnt : " + resultCnt);

        return resultCnt;
    }

}



