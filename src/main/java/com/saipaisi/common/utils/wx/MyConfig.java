package com.saipaisi.common.utils.wx;

import com.saipaisi.common.constant.WxUserConstant;
import java.io.*;

/**
 * @Author Zhenchunfeng
 * @Date 2020/4/22 16:16
 * @Description
 */
public class MyConfig extends WXPayConfig {

    public static String PATH1 = "/home/taochu/applet/ack/apiclient_cert.p12";

    private byte[] certData;


    @Override
    String getAppID() {
        return WxUserConstant.WX_LOGIN_APPID;
    }

    @Override
    String getMchID() {
        return WxUserConstant.WX_LOGIN_MCH_ID;
    }

    @Override
    String getKey() {
        return WxUserConstant.WX_LOGIN_KEY;
    }

    @Override
    InputStream getCertStream() {
        File file = new File(PATH1);
        InputStream input = null;
        try {
            input = new FileInputStream(file);
            try {
                this.certData= new byte[input.available()];
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return input;
    }

    @Override
    IWXPayDomain getWXPayDomain() {
        return new IWXPayDomain() {
            @Override
            public void report(String domain, long elapsedTimeMillis, Exception ex) {
            }

            @Override
            public DomainInfo getDomain(WXPayConfig config) {
                return new DomainInfo("api.mch.weixin.qq.com", false);
            }
        };
    }

    @Override
    public int getHttpConnectTimeoutMs() {
        return 8000;
    }

    @Override
    public int getHttpReadTimeoutMs() {
        return 10000;
    }
}
