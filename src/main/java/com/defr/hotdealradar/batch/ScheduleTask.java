package com.defr.hotdealradar.batch;


import com.defr.hotdealradar.common.StoredValue;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class ScheduleTask {



    @Scheduled(cron = "0/10 * * * * *")
    private void makeHotDealData() {
        System.out.println("핫딜 데이터 제작");
        System.out.println("메모리 핫딜 데이터 : " + StoredValue.hotDealData);

        //사이트별 크롤링 & 정제 클래스

        StoredValue.hotDealData.put("pomppu", new Object());






    }




}
