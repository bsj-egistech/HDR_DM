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

@Component
public class FmkoCrawler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //https://www.ppomppu.co.kr/zboard/view.php?id=ppomppu&no=476577

    String dealLinkPre = "https://www.fmkorea.com/";

    //사이트 데이터 불러오기 & 정제
    public ArrayList crawData() {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.fmkorea.com/hotdeal").get();
        } catch(Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }

        //System.out.println("doc html : " + doc.html());
        //System.out.println("doc text : " + doc.text());
        //System.out.println("doc query : " + doc.select("#revolution_main_table tr[class^=list]:not(#page_show_noti_1)"));

        Elements els = doc.select(".fm_best_widget._bd_pc ul li");

        //System.out.println("els : " + els);

        ArrayList<HashMap<String, String>> hotDealData = new ArrayList<HashMap<String, String>>();



        for(int i = 0 ; i < els.size() ; i++) {
            HashMap<String, String> map = new HashMap<String, String>();

            //게시글 번호
            map.put("post_number", els.get(i).select(".li > a").attr("href").replace("/", ""));
            //게시글 링크
            map.put("post_link", dealLinkPre + map.get("post_number"));
            //작성자
            map.put("post_author", els.get(i).select(".li .author").text());
            //제목
            map.put("post_title", els.get(i).select(".li .title > a").text());
            //날짜
            map.put("post_date", convertDate(els.get(i).select(".li .regdate").text()));
            hotDealData.add(map);



        }

        //logger.info("pomppuData : " + pomppuData);

        return hotDealData;
    }


    /**
     * 날짜 데이터 파싱
     * 펨코의 경우 크롤링한 날짜 데이터는 yyyy.MM.dd 또는 HH:mm
     * .을 1개 이상 포함 한 경우 yyyy/MM/dd 00:00:00 으로 반환
     * :을 1개 이상 포함한 경우 yyyy/MM/dd HH:mm:ss 으로 반환
     * @param date 크롤링한 날짜 데이터
     * @return 파싱된 날짜 데이터
     */
    private String convertDate(String date) {
        String returnDate = "";
        if(date.indexOf(".") > -1) {
            returnDate = date + " " + "00:00:00";
        } else if(date.indexOf(":") > -1) {
            returnDate = StringUtil.getDatePattern("yyyy-MM-dd") + " " + date + ":00";
        } else {
            returnDate = StringUtil.getDatePattern("yyyy-MM-dd HH:mm:ss");
        }
        return returnDate;
    }





}
