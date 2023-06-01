package com.defr.hotdealradar.batch;


import com.defr.hotdealradar.common.StoredValue;
import com.defr.hotdealradar.crawler.PpomppuCrawler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.ArrayList;
import java.util.HashMap;

@SpringBootApplication
@EnableScheduling
public class ScheduleTask {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PpomppuCrawler pCraw;

    @Scheduled(cron = "0/10 * * * * *")
    private void makeHotDealData() {
        System.out.println("make hot deal data");
        //System.out.println("hot deal data in memory : " + StoredValue.hotDealData);

        //사이트별 크롤링 & 정제 클래스
        //크롤링시 리스트 따로 만들지 말고 리스트 반복문 돌려서 아래 형태로 만들기

        /*ArrayList<HashMap<String, String>> pomppuData = new ArrayList<HashMap<String, String>>();

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

        StoredValue.hotDealData.put("pomppu", pomppuData);*/

        //System.out.println("한글");
        //logger.info("한글");
//        StoredValue.hotDealData.put("pomppu", pCraw.crawData());

        putInStoredValue(pCraw.crawData());

        logger.info("hotDealData : " + StoredValue.hotDealData);
    }

    // 기존 값과 비교해서 중복값 없이 새로운 값만 넣도록
    public void putInStoredValue(ArrayList<HashMap<String, String>> putObj) {

        ArrayList<HashMap<String, String>> targetList = (ArrayList<HashMap<String, String>>) StoredValue.hotDealData.get("pomppu");
        ArrayList<HashMap<String, String>> newList = new ArrayList<HashMap<String, String>>();
        ArrayList<HashMap<String, String>> resultList = new ArrayList<HashMap<String, String>>();

        //logger.info("targetList : " + targetList);

        for(int i = 0 ; i < putObj.size() ; i++) {
            //logger.info("putObj [" +i + "] : " + putObj.get(i));

            if(targetList != null) {
                for(HashMap<String, String> map : targetList) {
                    boolean isPutIn = false;

                    if(!map.get("post_number").equals(putObj.get(i).get("post_number"))) {
                        logger.info("새로운 값!");
                        isPutIn = true;
                        newList.add(putObj.get(i));
                    } else {
                        logger.info("똑같은 거닌깐 거름");
                    }

                    if(isPutIn) {
                        break;
                    }

                }
            } else {
                logger.info("기존값이 null이므로 새 값을 넣습니다.");
                newList.add(putObj.get(i));
            }




        }

        //resultList + targetList 합쳐서 뽐뿌에 넣기
        logger.info("newList : " + newList);
        logger.info("targetList : " + targetList);

        resultList.addAll(newList);
        if(targetList != null) resultList.addAll(targetList);

        StoredValue.hotDealData.put("pomppu", resultList);
    }



}
