package com.saipaisi.common.utils.wx;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Zhenchunfeng
 * @Date 2020/5/11 10:03
 * @Description
 */
public class WxPayUtils {



//    //微信统一下单
//    public static ResultVO wxPay(Map<String,String> data) {
//        ResultVO resultVO = new ResultVO(0, "失败！");
//        Map resultMap = new HashMap();
//        WXPay wxpay = null;
//        try {
//            WXPayConfig config = new MyConfig();
//            wxpay = new WXPay(config, "", false, false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        //生成的随机字符串
//        String nonce_str = WXPayUtil.generateNonceStr();
//        //获取客户端的ip地址
//        //获取本机的ip地址
//        InetAddress addr = null;
//        try {
//            addr = InetAddress.getLocalHost();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//        String spbill_create_ip = addr.getHostAddress();
//        //统一下单接口参数
//        data.put("spbill_create_ip", spbill_create_ip);
//        data.put("trade_type", "JSAPI");
//        try {
//            Map<String, String> rMap = wxpay.unifiedOrder(data);
//            System.out.println("统一下单接口返回: " + rMap);
//            String return_code = (String) rMap.get("return_code");
//            String result_code = (String) rMap.get("result_code");
//            String nonceStr = WXPayUtil.generateNonceStr();
//            resultMap.put("nonceStr", nonceStr);
//            Long timeStamp = System.currentTimeMillis() / 1000;
//            if ("SUCCESS".equals(return_code) && return_code.equals(result_code)) {
//                String prepayid = rMap.get("prepay_id");
//                resultMap.put("package", "prepay_id=" + prepayid);
//                resultMap.put("signType", WXPayConstants.HMACSHA256);
//                //这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
//                resultMap.put("timeStamp", timeStamp + "");
//                //再次签名，这个签名用于小程序端调用wx.requesetPayment方法
//                resultMap.put("appId", UserConstant.WX_LOGIN_APPID);
//                String sign = WXPayUtil.generateSignature(resultMap, UserConstant.WX_LOGIN_KEY, WXPayConstants.SignType.HMACSHA256);
//                resultMap.put("paySign", sign);
//                System.out.println("生成的签名paySign : " + sign);
//                resultMap.remove("appId");
//                resultVO.setData(resultMap);
//                resultVO.setCode(1);
//                resultVO.setMsg("调用成功");
//                return resultVO;
//            } else {
//                return resultVO;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return resultVO;
//        }
//    }


}
