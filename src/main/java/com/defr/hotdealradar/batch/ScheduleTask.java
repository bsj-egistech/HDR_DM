package com.defr.hotdealradar.batch;


import com.defr.hotdealradar.common.StoredValue;
import com.defr.hotdealradar.crawler.PpomppuCrawler;
import com.defr.hotdealradar.service.DealManageService;
import com.defr.hotdealradar.vo.DealVo;
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
    PpomppuCrawler pomppuCrawler;

    @Autowired
    DealManageService dealManageService;

    //@Scheduled(cron = "0/10 * * * * *")
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

        putInStoredValue(pomppuCrawler.crawData());

        logger.info("hotDealData : " + StoredValue.hotDealData);
    }

    @Scheduled(cron = "0/10 * * * * *")
    private void crawDealPomppu() {
        ArrayList<HashMap<String, String>> crawListPomppu = pomppuCrawler.crawData();
        //logger.info("crawListPomppu : " + crawListPomppu);
        //logger.info("크롤링 데이터 총 " + crawListPomppu.size() + "건");
        
        for(HashMap<String, String> map : crawListPomppu) {
            DealVo vo = new DealVo();
            vo.setId( map.get("post_number") + "_pomppu" );
            vo.setNumber( map.get("post_number") );
            vo.setSite( "pomppu" );
            vo.setTitle( map.get("post_title") );
            vo.setSeller( map.get("post_author") );
            vo.setRegi_date( map.get("post_date") );
            vo.setLink( map.get("post_link") );

            int resultCnt = dealManageService.insertDeal(vo);
            //logger.info(resultCnt + "건 삽입/수정 완료");
        }
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
