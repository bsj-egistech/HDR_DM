package com.defr.hotdealradar.controller;

import com.defr.hotdealradar.service.DealManageService;
import com.defr.hotdealradar.vo.DealVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;

@Controller
public class ServletController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    DealManageService dealManageService;

    @RequestMapping("/test")
    public ModelAndView test() {
        logger.info("test page 진입");

        ModelAndView mav = new ModelAndView();
        mav.setViewName("test");

        return mav;
    }

    @RequestMapping("/main")
    public ModelAndView main() {
        logger.info("main page 진입");

        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");

        HashMap map = new HashMap();
        map.put("limit", 20);
        map.put("offset", 0);

        List<DealVo> list = dealManageService.selectDeal(map);
        mav.addObject("list", list);




        return mav;
    }

}





