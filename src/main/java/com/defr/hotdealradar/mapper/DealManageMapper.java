package com.defr.hotdealradar.mapper;

import com.defr.hotdealradar.vo.DealVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface DealManageMapper {
    List<DealVo> selectDealAll();
    List<DealVo> selectDeal(HashMap map);
    int insertDeal(DealVo vo);
}
