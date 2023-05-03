package com.defr.hotdealradar.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class PpomppuCrawler {

    //사이트 데이터 불러오기 & 정제
    public void crawData() {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.ppomppu.co.kr/zboard/zboard.php?id=ppomppu").get();
        } catch(Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }

        //System.out.println("doc html : " + doc.html());
        //System.out.println("doc text : " + doc.text());
        //System.out.println("doc query : " + doc.select("#revolution_main_table tr[class^=list]:not(#page_show_noti_1)"));

        Elements els = doc.select("#revolution_main_table tr[class^=list]:not(#page_show_noti_1)");

        System.out.println("els : " + els);
        els.size();




    }



}
