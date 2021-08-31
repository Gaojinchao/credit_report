package com.saipaisi.project.order.service.impl;

import java.util.List;
import java.util.UUID;

import com.saipaisi.common.utils.DateUtils;
import com.saipaisi.project.basic.domain.BasicMainManager;
import com.saipaisi.project.basic.service.*;
import com.saipaisi.project.order.domain.AllMainInfoReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.order.mapper.OrderFormMapper;
import com.saipaisi.project.order.domain.OrderForm;
import com.saipaisi.project.order.service.OrderFormService;

/**
 * 订单Service业务层处理
 * 
 * @author alfred.zong
 * @date 2021-05-13
 */
@Service
public class OrderFormServiceImpl implements OrderFormService
{
    @Autowired
    private OrderFormMapper orderFormMapper;

    @Autowired
    private BasicBranchOrgService basicBranchOrgService;

    @Autowired
    private BasicCopyrightService basicCopyrightService;

    @Autowired
    private BasicEmbranchmentOrgService basicEmbranchmentOrgService;

    @Autowired
    private BasicExecutedEnterpriseService basicExecutedEnterpriseService;

    @Autowired
    private BasicHistoricEvolutionService basicHistoricEvolutionService;

    @Autowired
    private BasicInformationService basicInformationService;

    @Autowired
    private BasicJudicativePaperService basicJudicativePaperService;

    @Autowired
    private BasicPatentInformationService basicPatentInformationService;

    @Autowired
    private BasicPromiseEnterpriseService basicPromiseEnterpriseService;

    @Autowired
    private BasicQualificationInformationService basicQualificationInformationService;

    @Autowired
    private BasicShareholderService basicShareholderService;

    @Autowired
    private BasicSoftwareCopyrightService basicSoftwareCopyrightService;

    @Autowired
    private BasicMainManagerService basicMainManagerService;

    /**
     * 查询订单
     * 
     * @param id 订单ID
     * @return 订单
     */
    @Override
    public OrderForm selectOrderFormById(Long id)
    {
        return orderFormMapper.selectOrderFormById(id);
    }

    /**
     * 查询订单列表
     * 
     * @param orderForm 订单
     * @return 订单
     */
    @Override
    public List<OrderForm> selectOrderFormList(OrderForm orderForm)
    {
        return orderFormMapper.selectOrderFormList(orderForm);
    }

    /**
     * 新增订单
     * 
     * @param orderForm 订单
     * @return 结果
     */
    @Override
    public int insertOrderForm(OrderForm orderForm)
    {
        String uuid=UUID.randomUUID().toString().replaceAll("-","");
        orderForm.setOrderSn(uuid);
        orderForm.setCreateTime(DateUtils.getNowDate());
        orderFormMapper.insertOrderForm(orderForm);
        qxbdiaoAll(orderForm.getEnterprise(),uuid);
        return 1;
    }

    /**
     * 修改订单
     * 
     * @param orderForm 订单
     * @return 结果
     */
    @Override
    public int updateOrderForm(OrderForm orderForm)
    {
        orderForm.setUpdateTime(DateUtils.getNowDate());
        return orderFormMapper.updateOrderForm(orderForm);
    }

    /**
     * 批量删除订单
     * 
     * @param ids 需要删除的订单ID
     * @return 结果
     */
    @Override
    public int deleteOrderFormByIds(Long[] ids)
    {
        return orderFormMapper.deleteOrderFormByIds(ids);
    }

    /**
     * 删除订单信息
     * 
     * @param id 订单ID
     * @return 结果
     */
    @Override
    public int deleteOrderFormById(Long id)
    {
        return orderFormMapper.deleteOrderFormById(id);
    }

    @Override
    public OrderForm selectByOrdersn(String ordersn) {
        return orderFormMapper.selectByOrdersn(ordersn);
    }

    @Override
    public int submit(AllMainInfoReq allMainInfoReq) {
        String orderSn=allMainInfoReq.getBasicInformation().getOrderSn();
        //主信息修改
        basicInformationService.updateBasicInformation(allMainInfoReq.getBasicInformation());
        List<BasicMainManager> list=allMainInfoReq.getBasicMainManager();
        /**
         * 主要管理者信息对象
         */
        list.forEach(e->{
            if (e.getId()!=null){
                basicMainManagerService.updateBasicMainManager(e);
            }else {
                basicMainManagerService.insertBasicMainManager(e);
            }
        });
        //
        return 0;
    }


    private void qxbdiaoAll(String keyord,String ordersn){
        //基本信息接口
        basicInformationService.qxbInfoSearch(keyord,ordersn);
        //分支机构查询
        basicBranchOrgService.getBranchsInfo(keyord,ordersn);
        //著作权
        basicCopyrightService.getCopyrightByName(keyord,ordersn);
        //获取对外投资
        basicEmbranchmentOrgService.getInvestmentByNameInfo(keyord,ordersn);
        //被执行企业
        basicExecutedEnterpriseService.getExecutedpersonListByName(keyord,ordersn);
        //获取变更记录
        basicHistoricEvolutionService.getChangeRecordsInfo(keyord,ordersn);
        //查询第三方裁判文书接口
        basicJudicativePaperService .getLawsuitListByName(keyord,ordersn);
        //专利信息qxb查询
        basicPatentInformationService.getPatentListByNameInfo(keyord,ordersn);
        //失信被执行企业
        basicPromiseEnterpriseService.getExecutionListByName(keyord,ordersn);
        //资质证书
        basicQualificationInformationService.getCertificateByNameInfo(keyord,ordersn);
        //股东组成Service接口
        basicShareholderService.getPartnersInfo(keyord,ordersn);
        //软件著作权
        basicSoftwareCopyrightService.getCopyrightSoftByNameInfo(keyord,ordersn);
    }
}
