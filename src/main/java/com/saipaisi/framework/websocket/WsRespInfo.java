package com.saipaisi.framework.websocket;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: lin.shi
 * @Date: 2020/6/16 9:29 上午
 * @Describe: ws 返回类封装
 */
@Data
@Builder
public class WsRespInfo  {
    private int code;
    private int type;
    private String message;
    private Object Data;
}
