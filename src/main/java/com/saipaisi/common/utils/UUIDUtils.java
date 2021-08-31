package com.saipaisi.common.utils;


import org.apache.commons.lang3.RandomStringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @Author alfred.zong
 * @Date 2020/4/21 8:49
 * @Descriptionuuid生成
 */
public final class UUIDUtils {

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }

    public static String getlinkNo() {
        String linkNo = "";
        // 用字符数组的方式随机
        String model = "0123456789";
        char[] m = model.toCharArray();
        for (int j = 0; j < 6; j++) {
            char c = m[(int) (Math.random() * 10)];
            // 保证六位随机数之间没有重复的
            if (linkNo.contains(String.valueOf(c))) {
                j--;
                continue;
            }
            linkNo = linkNo + c;
        }
        return linkNo;
    }

    public static String getOrderIdByUUId() {
        int machineId = 1;//最大支持1-9个集群机器部署
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {//有可能是负数
            hashCodeV = -hashCodeV;
        }
//         0 代表前面补充0     
//         4 代表长度为4     
//         d 代表参数为正数型
        return machineId + String.format("%015d", hashCodeV);
    }

    public static String getOrderIdByTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDate = sdf.format(new Date());
        String result = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            result += random.nextInt(10);
        }
        return newDate + result;
    }
    public static String getOrderNumber(){
        String time=DateUtils.format(new Date(),"yyyyMMddHHmmss");
        String randomNumeric= RandomStringUtils.randomNumeric(8);
        return time+randomNumeric;
    }

    public static String cardId(Long id){
        long time=System.currentTimeMillis();
        String card=time+""+id;
        return card;
    }

    public static void main(String[] args) {
        System.out.println(getOrderNumber());
    }

}
