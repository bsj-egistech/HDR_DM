package com.defr.hotdealradar.controller;

import com.defr.hotdealradar.service.DealManageService;
import com.defr.hotdealradar.vo.DealVo;
import com.defr.hotdealradar.vo.PagingVO;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
        //logger.info("test page 진입");

        ModelAndView mav = new ModelAndView();
        mav.setViewName("test");

        return mav;
    }

    @RequestMapping("/main")
    public ModelAndView main(HttpServletRequest request, @ModelAttribute("pagingVO") PagingVO pagingVO) {
        //logger.info("main page 진입");

        logger.info("pageIdx : " + request.getParameter("pageIdx"));
        int pageIdx = request.getParameter("pageIdx") == null ? 1 : Integer.parseInt(request.getParameter("pageIdx"));


        ModelAndView mav = new ModelAndView();
        mav.setViewName("main");

        HashMap map = new HashMap();
        map.put("limit", pagingVO.getDisplayRow());
        map.put("offset", pagingVO.getStart(pageIdx));

        List<DealVo> list = dealManageService.selectDeal(map);
        int dealCnt = dealManageService.selectDealCnt();
        logger.info("dealCnt : " + dealCnt);
        pagingVO.setTotalCount(dealCnt);

        mav.addObject("list", list);
        mav.addObject("pageIdx", pageIdx);

        return mav;
    }

}





