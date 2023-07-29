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
    public List<DealVo> selectDeal(HashMap map) {
        return dealManageMapper.selectDeal(map);
    }
    public Integer selectDealCnt() {
        return dealManageMapper.selectDealCnt();
    }
    public int insertDeal(DealVo vo) {
        return dealManageMapper.insertDeal(vo);
    }

}
