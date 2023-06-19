package com.defr.hotdealradar.mapper;

import com.defr.hotdealradar.vo.DealVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface DealManageMapper {
    List<DealVo> selectDealAll();
    int insertDeal(DealVo vo);
}
