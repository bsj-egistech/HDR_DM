package com.defr.hotdealradar.crawler;

import com.defr.hotdealradar.util.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 사용하지 않음
 * 퀘이사존의 경우 비인가된 크롤링을 차단함.
 * 크롤링 시행할 서버 IP에 대해 허가를 받아야 사용가능
 */
@Component
public class QuasarCrawler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //https://quasarzone.com/bbs/qb_saleinfo/views/1378854

    String dealLinkPre = "https://quasarzone.com/bbs/qb_saleinfo/views/";

    //사이트 데이터 불러오기 & 정제
    public ArrayList crawData() {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://quasarzone.com/bbs/qb_saleinfo")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36")
                    .header("scheme", "https")
                    .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8")
                    .header("accept-encoding", "gzip, deflate, br")
                    .header("accept-language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7,es;q=0.6")
                    .header("cache-control", "no-cache")
                    .header("pragma", "no-cache")
                    .header("upgrade-insecure-requests", "1")
                    .get();
        } catch(Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }

        //System.out.println("doc html : " + doc.html());
        //System.out.println("doc text : " + doc.text());
        //System.out.println("doc query : " + doc.select("#revolution_main_table tr[class^=list]:not(#page_show_noti_1)"));

        Elements els = doc.select(".market-type-list tbody tr");

        //System.out.println("els : " + els);

        ArrayList<HashMap<String, String>> resultData = new ArrayList<HashMap<String, String>>();



        for(int i = 0 ; i < els.size() ; i++) {
            HashMap<String, String> map = new HashMap<String, String>();

            String[] hrefArr = els.get(i).select(".market-info-list-cont .tit a").attr("href").split("/");
            String postNumber = hrefArr[hrefArr.length - 1];

            //게시글 번호
            map.put("post_number", postNumber );
            //게시글 링크
            map.put("post_link", dealLinkPre + postNumber );
            //작성자
            map.put("post_author", els.get(i).select(".market-info-sub .user-nick-text").text());
            //제목
            map.put("post_title", els.get(i).select(".market-info-list-cont .tit a > span:nth-child(1)").text());
            //날짜
            map.put("post_date", convertDate(els.get(i).select(".market-info-sub .date").text()));
            resultData.add(map);
        }

        //logger.info("pomppuData : " + pomppuData);

        return resultData;
    }


    /**
     * 날짜 데이터 파싱
     * 퀘이사존의 경우 크롤링한 날짜 데이터는 MM-dd 또는 HH:mm
     * -을 1개 이상 포함 한 경우 yyyy/MM/dd 00:00:00 으로 반환
     * :을 1개 이상 포함한 경우 yyyy/MM/dd HH:mm:ss 으로 반환
     * @param date 크롤링한 날짜 데이터
     * @return 파싱된 날짜 데이터
     */
    private String convertDate(String date) {
        String returnDate = "";
        if(date.indexOf("-") > -1) {
            returnDate = date + " " + "00:00:00";
        } else if(date.indexOf(":") > -1) {
            returnDate = StringUtil.getDatePattern("yyyy-MM-dd") + " " + date;
        } else {
            returnDate = StringUtil.getDatePattern("yyyy-MM-dd HH:mm:ss");
        }
        return returnDate;
    }





}
