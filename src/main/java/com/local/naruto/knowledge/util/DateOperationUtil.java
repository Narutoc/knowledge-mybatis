package com.local.naruto.knowledge.util;

import java.util.Calendar;

public class DateOperationUtil {

    public static int daysCountOfMonth(String dateString) {
        //截取出年份，并将其转化为int
        int year = Integer.parseInt(dateString.substring(0, 4));
        //截去除月份，并将其转为int
        int month = Integer.parseInt(dateString.substring(5, 7));

        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
