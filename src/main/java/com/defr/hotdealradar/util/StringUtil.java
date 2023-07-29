package com.defr.hotdealradar.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StringUtil {

    public static String getDatePattern(String pattern) {
        // 현재 날짜 가져오기
        LocalDateTime today = LocalDateTime.now();

        // 원하는 형식으로 날짜를 문자열로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String formattedDatetime = today.format(formatter);
        return formattedDatetime;
    }




}
