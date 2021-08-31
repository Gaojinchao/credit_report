package com.saipaisi.project.order.domain;

import java.math.BigDecimal;
import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 订单对象 order_form
 * 
 * @author alfred.zong
 * @date 2021-05-13
 */
@Data
public class OrderForm extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String enterprise;

    /** 评估等级 */
    @Excel(name = "评估等级")
    private String assessmentLevel;

    /** 地区 */
    @Excel(name = "地区")
    private String region;

    /** 领域 */
    @Excel(name = "领域")
    private String territory;

    /** 行业 */
    @Excel(name = "行业")
    private String industry;

    /** 联系人 */
    @Excel(name = "联系人")
    private String linkman;

    /** 电话 */
    @Excel(name = "电话")
    private BigDecimal phone;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;

    /** 进单状态 */
    @Excel(name = "进单状态")
    private String singleState;

    /** 订单状态 1新建，2提交，3待审核，4驳回，5已通过，6已完成*/
    @Excel(name = "订单状态")
    private String state;

    /** 评估结果 */
    @Excel(name = "评估结果")
    private String assessmentResult;

    /** 原因 */
    @Excel(name = "原因")
    private String cause;

    /** 创建人ID */
    @Excel(name = "创建人ID")
    private Long userId;

    /** 部门id */
    @Excel(name = "部门id")
    private Long deptId;

    /** 公司id */
    @Excel(name = "公司id")
    private Long companyId;

    /**
     * 现金表
     */
    private String cashStatement;

    /**
     * 负债表
     */
    private String balanceSheet;

    /**
     * 利润表
     */
    private String incomeStatement;

    private String oneDate;

    private String twoDate;

    private String threeDate;


}
