package com.defr.hotdealradar.service;

import com.defr.hotdealradar.mapper.DealManageMapper;
import com.defr.hotdealradar.vo.DealVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class DealManageService {

    @Autowired
    DealManageMapper dealManageMapper;

    public List<DealVo> selectDealAll() {
        return dealManageMapper.selectDealAll();
    }
    public int insertDeal(DealVo vo) {
        return dealManageMapper.insertDeal(vo);
    }

}
