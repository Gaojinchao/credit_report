package com.saipaisi.project.order.mapper;

import java.util.List;
import com.saipaisi.project.order.domain.OrderForm;

/**
 * 订单Mapper接口
 * 
 * @author alfred.zong
 * @date 2021-05-13
 */
public interface OrderFormMapper 
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
     * 删除订单
     * 
     * @param id 订单ID
     * @return 结果
     */
    public int deleteOrderFormById(Long id);

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOrderFormByIds(Long[] ids);

    /**
     * 根据订单编号查询订单详情
     * @param ordersn
     * @return
     */
    public OrderForm selectByOrdersn(String ordersn);
}
