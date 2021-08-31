package com.saipaisi.common.utils;

import java.math.BigDecimal;

/**
 * @Author: lin.shi
 * @Date: 2020/8/20 2:09 下午
 * @Describe: 处理一些数字计算的工具类
 */
public class CountUtils {

    // a integer to xx:xx:xx

    /**
     * 将秒数转化成时:分:秒
     * @param time
     * @return
     */
    public static String secToTime(int time) {
        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            } else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {
        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }

    /**
     * 将传进来的数字保留两位有效数据 （四舍五入）
     * @param num
     * @return
     */
    public static BigDecimal keepTwoDecimals(Object num){
        BigDecimal   b   =   new   BigDecimal(num.toString());
       return b.setScale(2,   BigDecimal.ROUND_HALF_UP);
    }

    public static void main(String[] args) {
        Double d = 1.1d;
        System.out.println( keepTwoDecimals(d));
    }
}
