package com.defr.hotdealradar.batch;


import com.defr.hotdealradar.common.StoredValue;
import com.defr.hotdealradar.crawler.PpomppuCrawler;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.HashMap;

@SpringBootApplication
@EnableScheduling
public class ScheduleTask {



    @Scheduled(cron = "0/10 * * * * *")
    private void makeHotDealData() {
        System.out.println("make hot deal data");
        System.out.println("hot deal data in memory : " + StoredValue.hotDealData);

        //사이트별 크롤링 & 정제 클래스
        //크롤링시 리스트 따로 만들지 말고 리스트 반복문 돌려서 아래 형태로 만들기

        ArrayList<HashMap<String, String>> pomppuData = new ArrayList<HashMap<String, String>>();

        HashMap<String, String> map0 = new HashMap<String, String>();
        map0.put("title", "제목0");
        map0.put("price", "1000원");
        pomppuData.add(map0);

        HashMap<String, String> map1 = new HashMap<String, String>();
        map1.put("title", "제목1");
        map1.put("price", "500원");
        pomppuData.add(map1);

        HashMap<String, String> map2 = new HashMap<String, String>();
        map2.put("title", "제목2");
        map2.put("price", "3000원");
        pomppuData.add(map2);

        StoredValue.hotDealData.put("pomppu", pomppuData);


        new PpomppuCrawler().crawData();




    }




}
