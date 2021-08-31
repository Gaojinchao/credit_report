package com.saipaisi.framework.websocket;

import com.alibaba.fastjson.JSON;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * @Author: lin.shi
 * @Date: 2020/6/3 4:30 下午
 * @Describe:
 */
public class WebSocketEncoder implements Encoder.Text<WsRespInfo> {
    @Override
    public String encode(WsRespInfo resp) {
            return JSON.toJSONString(resp);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
