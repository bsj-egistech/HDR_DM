package com.defr.hotdealradar.batch;


import com.defr.hotdealradar.common.StoredValue;
import com.defr.hotdealradar.crawler.FmkoCrawler;
import com.defr.hotdealradar.crawler.PpomppuCrawler;
import com.defr.hotdealradar.crawler.QuasarCrawler;
import com.defr.hotdealradar.service.DealManageService;
import com.defr.hotdealradar.vo.DealVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.HashMap;

@SpringBootApplication
@EnableScheduling
public class ScheduleTask {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    PpomppuCrawler pomppuCrawler;
    @Autowired
    FmkoCrawler fmkoCrawler;
    @Autowired
    QuasarCrawler QuasarCrawler;

    @Autowired
    DealManageService dealManageService;

    static ArrayList<HashMap<String, String>> storedListPomppu = new ArrayList<HashMap<String, String>>();
    static ArrayList<HashMap<String, String>> storedListFmko = new ArrayList<HashMap<String, String>>();

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


    @Scheduled(cron = "0/30 * * * * *")
    private void crawDealPomppu() {
        logger.info("뽐뿌 데이터 메모리 값 : " + storedListPomppu);
        logger.info(storedListPomppu.size() + " 건");

        if(storedListPomppu.size() == 0) {
            logger.info("뽐뿌 데이터 최초 메모리 적재 실행");
            ArrayList<HashMap<String, String>> crawListPomppu = pomppuCrawler.crawData();
            int resultCntPomppu = 0;

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
                resultCntPomppu += resultCnt;
                storedListPomppu.add(map);
            }
            logger.info("뽐뿌 " + resultCntPomppu + " 건 삽입 / 수정 완료");
        } else {
            logger.info("뽐뿌 데이터 기존 메모리 값과 비교적재 실행");
            ArrayList<HashMap<String, String>> crawListPomppu = pomppuCrawler.crawData();
            int resultCntPomppu = 0;
            ArrayList<HashMap<String, String>> differentList = findDifferentList(storedListPomppu, crawListPomppu);
            logger.info("뽐뿌 데이터 비교 시 다른 값 : " + differentList);
            logger.info(differentList.size() + " 건");

            if(differentList.size() > 0) {
                for(HashMap<String, String> map : differentList) {
                    DealVo vo = new DealVo();
                    vo.setId( map.get("post_number") + "_pomppu" );
                    vo.setNumber( map.get("post_number") );
                    vo.setSite( "pomppu" );
                    vo.setTitle( map.get("post_title") );
                    vo.setSeller( map.get("post_author") );
                    vo.setRegi_date( map.get("post_date") );
                    vo.setLink( map.get("post_link") );

                    logger.info("다른 항목 : " + map);

                    int resultCnt = dealManageService.insertDeal(vo);
                    resultCntPomppu += resultCnt;
                }
                storedListPomppu = crawListPomppu;
                logger.info("뽐뿌 " + resultCntPomppu + " 건 삽입 / 수정 완료");
            }
        }
    }

    @Scheduled(cron = "0/20 * * * * *")
    private void crawDealFmko() {
        logger.info("펨코 데이터 메모리 값 : " + storedListFmko);
        logger.info(storedListFmko.size() + " 건");

        if(storedListFmko.size() == 0) {
            logger.info("펨코 데이터 최초 메모리 적재 실행");
            ArrayList<HashMap<String, String>> crawListFmko = fmkoCrawler.crawData();
            int resultCntPomppu = 0;

            for(HashMap<String, String> map : crawListFmko) {
                DealVo vo = new DealVo();
                vo.setId( map.get("post_number") + "_fmko" );
                vo.setNumber( map.get("post_number") );
                vo.setSite( "fmko" );
                vo.setTitle( map.get("post_title") );
                vo.setSeller( map.get("post_author") );
                vo.setRegi_date( map.get("post_date") );
                vo.setLink( map.get("post_link") );

                int resultCnt = dealManageService.insertDeal(vo);
                resultCntPomppu += resultCnt;
                storedListFmko.add(map);
            }
            logger.info("펨코 " + resultCntPomppu + " 건 삽입 / 수정 완료");
        } else {
            logger.info("펨코 데이터 기존 메모리 값과 비교적재 실행");
            ArrayList<HashMap<String, String>> crawListFmko = fmkoCrawler.crawData();
            int resultCntPomppu = 0;
            ArrayList<HashMap<String, String>> differentList = findDifferentList(storedListFmko, crawListFmko);
            logger.info("펨코 데이터 비교 시 다른 값 : " + differentList);
            logger.info(differentList.size() + " 건");

            if(differentList.size() > 0) {
                for(HashMap<String, String> map : differentList) {
                    DealVo vo = new DealVo();
                    vo.setId( map.get("post_number") + "_fmko" );
                    vo.setNumber( map.get("post_number") );
                    vo.setSite( "fmko" );
                    vo.setTitle( map.get("post_title") );
                    vo.setSeller( map.get("post_author") );
                    vo.setRegi_date( map.get("post_date") );
                    vo.setLink( map.get("post_link") );

                    logger.info("다른 항목 : " + map);

                    int resultCnt = dealManageService.insertDeal(vo);
                    resultCntPomppu += resultCnt;
                }
                storedListFmko = crawListFmko;
                logger.info("펨코 " + resultCntPomppu + " 건 삽입 / 수정 완료");
            }
        }
    }

    public ArrayList<HashMap<String, String>> findDifferentList(ArrayList<HashMap<String, String>> oriList, ArrayList<HashMap<String, String>> newList) {
        ArrayList<HashMap<String, String>> returnList = new ArrayList<HashMap<String, String>>();

        for (HashMap<String, String> newMap : newList) {
            if (!oriList.contains(newMap)) {
                returnList.add(newMap);
            }
        }

        return returnList;
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
