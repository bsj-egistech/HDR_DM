package com.defr.hotdealradar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ServletController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/test")
    public ModelAndView test() {
        logger.info("test page 진입");

        ModelAndView mav = new ModelAndView();
        mav.setViewName("test");

        return mav;
    }



}
