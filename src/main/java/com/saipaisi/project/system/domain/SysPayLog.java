package com.saipaisi.project.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 付款记录对象 sys_pay_log
 * 
 * @author alfred.zong
 * @date 2020-12-11
 */
@Data
public class SysPayLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long uId;

    /** 支付方式1微信2支付宝，3余额 */
    @Excel(name = "支付方式1微信2支付宝，3余额")
    private String payType;

    /** 付款编号 */
    @Excel(name = "付款编号")
    private String paySn;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderSn;

    /** 总金额 */
    @Excel(name = "总金额")
    private BigDecimal totalMoney;

    /** 优惠金额 */
    @Excel(name = "优惠金额")
    private BigDecimal discountsMoney;

    /** 购买类型，1为咨询公司，2为终端客户 */
    @Excel(name = "购买类型，1为咨询公司，2为终端客户")
    private String buyType;

    /** 支付时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "支付时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date payTime;


}
