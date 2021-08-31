package com.saipaisi.project.order.service;

import com.saipaisi.project.order.domain.AllMainInfoReq;

import java.io.FileNotFoundException;

/**
 * @Author alfred.zong
 * @Date 2021/5/26 19:27
 * @Description 提交信息接口
 */
public interface SubmitInformationService {


    /**
     * 提交所有信息
     */
    public void submitInfoAll(AllMainInfoReq allMainInfoReq) throws FileNotFoundException;

    /**
     * 根据订单号
     * @param ordersn
     * @return
     */
    public AllMainInfoReq selectAll(String ordersn);

}
