package com.saipaisi.project.order.service;

import java.util.List;

import com.saipaisi.project.order.domain.AllMainInfoReq;
import com.saipaisi.project.order.domain.OrderForm;

/**
 * 订单Service接口
 * 
 * @author alfred.zong
 * @date 2021-05-13
 */
public interface OrderFormService
{
    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    public OrderForm selectOrderFormById(Long id);

    /**
     * 查询订单列表
     * 
     * @param orderForm 订单
     * @return 订单集合
     */
    public List<OrderForm> selectOrderFormList(OrderForm orderForm);

    /**
     * 新增订单
     * 
     * @param orderForm 订单
     * @return 结果
     */
    public int insertOrderForm(OrderForm orderForm);

    /**
     * 修改订单
     * 
     * @param orderForm 订单
     * @return 结果
     */
    public int updateOrderForm(OrderForm orderForm);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的订单ID
     * @return 结果
     */
    public int deleteOrderFormByIds(Long[] ids);

    /**
     * 删除订单信息
     * 
     * @param id 订单ID
     * @return 结果
     */
    public int deleteOrderFormById(Long id);

    /*
    *订单编号
     */
    public OrderForm selectByOrdersn(String ordersn);

    /**
     * 提交
     * @return
     */
    public int submit(AllMainInfoReq allMainInfoReq);
}
