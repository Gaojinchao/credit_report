package com.saipaisi.common.constant;

/**
 * @Author
 * @Date 2020/4/15 14:42
 * @Description 小程序 常用常量
 */
public class WxUserConstant {

    // 请求的网址
    public static final String WX_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    // 你的appid
    public static final String WX_LOGIN_APPID = "wx400036713c117bfa";

    // 你的密匙
    public static final String WX_LOGIN_SECRET = "1bd4f0d7487279ce79bf266e86924c53";

    // 固定参数
    public static final String WX_LOGIN_GRANT_TYPE = "authorization_code";

    //key
    public static final String WX_LOGIN_KEY="anhuitaochuwangbulazejava9637411";

    //商户号
    public static final String WX_LOGIN_MCH_ID="1497676422";

    //支付回调地址
    public static final String WX_PAY_NOTIFY_URL="http://youlin.taochu.com:8081/order/success";

    //充值回调地址
    public static final String WX_RECHARGE_NOTIFY_URL="http://youlin.taochu.com:8081/order/recharge/success";

    //获取直播间列表
    public static final String WX_BROADCAST_ROOM_LIST="http://api.weixin.qq.com/wxa/business/getliveinfo?access_token=";

    //发送订阅消息url
    public static final String WX_SUBSCRIBE_SEND="https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token=";
}
