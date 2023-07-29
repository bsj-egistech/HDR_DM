package com.defr.hotdealradar.crawler;

import com.defr.hotdealradar.util.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;

@Component
public class PpomppuCrawler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    //사이트 데이터 불러오기 & 정제
    public ArrayList crawData() {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://www.ppomppu.co.kr/zboard/zboard.php?id=ppomppu").get();
        } catch(Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }

        //System.out.println("doc html : " + doc.html());
        //System.out.println("doc text : " + doc.text());
        //System.out.println("doc query : " + doc.select("#revolution_main_table tr[class^=list]:not(#page_show_noti_1)"));

        Elements els = doc.select("#revolution_main_table tr[class^=\"common-list\"]");

        //System.out.println("els : " + els);

        ArrayList<HashMap<String, String>> pomppuData = new ArrayList<HashMap<String, String>>();



        for(int i = 0 ; i < els.size() ; i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            //logger.info(i + "번째 요소");
            //System.out.println("title : " + els.get(i).select(".list_title"));
            //글 번호
            //값이 없는 경우 쇼핑 포럼 등 아이콘으로 들어가있음, 한번더 셀렉트 해서 이미지 alt값으로 구분
            //logger.info("글 번호 : " + els.get(i).select("td.eng.list_vspace:nth-child(1)").text());

            if(!els.get(i).select("td.eng.list_vspace:nth-child(1)").text().trim().equals("")) {
                map.put("post_number", els.get(i).select("td.eng.list_vspace:nth-child(1)").text());
                //작성자
                //logger.info("작성자 : " + els.get(i).select(".list_name > .list_name").text());
                map.put("post_author", els.get(i).select(".list_name > .list_name").text());

                //제목
                //logger.info("제목 : " + els.get(i).select("table tr td[valign=middle] a font").text());
                map.put("post_title", els.get(i).select("table tr td[valign=middle] a font").text());

                //11:36:54
                //날짜
                //logger.info("날짜 : " + els.get(i).select("td:nth-child(4) .list_vspace").text());
                map.put("post_date", convertDate(els.get(i).select("td:nth-child(4) .list_vspace").text()));
                pomppuData.add(map);
            } else {
                //logger.info("게시글 번호가 없으니 이 행 제외");
            }


        }

        //logger.info("pomppuData : " + pomppuData);

        return pomppuData;
    }


    /**
     * 날짜 데이터 파싱
     * 뽐뿌의 경우 크롤링한 날짜 데이터는 yyyy/MM/dd 또는 HH:mm:ss
     * /을 1개 이상 포함 한 경우 yyyy/MM/dd 00:00:00 으로 반환
     * :을 1개 이상 포함한 경우 yyyy/MM/dd HH:mm:ss 으로 반환
     * @param date 크롤링한 날짜 데이터
     * @return 파싱된 날짜 데이터
     */
    private String convertDate(String date) {
        String returnDate = "";
        if(date.indexOf("/") > -1) {
            returnDate = date + " " + "00:00:00";
        } else if(date.indexOf(":") > -1) {
            returnDate = StringUtil.getDatePattern("yyyy-MM-dd") + " " + date;
        } else {
            returnDate = StringUtil.getDatePattern("yyyy-MM-dd HH:mm:ss");
        }
        return returnDate;
    }





}
