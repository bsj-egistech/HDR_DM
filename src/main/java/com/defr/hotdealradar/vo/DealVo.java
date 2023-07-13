package com.defr.hotdealradar.vo;

import lombok.Data;

@Data
public class DealVo {

    private String id;
    private String number;
    private String site;

    private String title;
    private String seller;

    private String price_ori;
    private String price_type;
    private String price;

    private String regi_date;
    private String author;
    private String etc;
}
