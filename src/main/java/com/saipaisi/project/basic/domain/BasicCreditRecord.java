package com.saipaisi.project.basic.domain;

import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 信用记录对象 basic_credit_record
 * 
 * @author alfredzong
 * @date 2021-05-20
 */
@Data
public class BasicCreditRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 处罚信息 */
    @Excel(name = "处罚信息")
    private String punishInfo;

    /** 处罚类型 */
    @Excel(name = "处罚类型")
    private String punishType;

    /** 内容 */
    @Excel(name = "内容")
    private String context;

    /** 认证时间 */
    @Excel(name = "认证时间")
    private String dcertificateDate;

    /** 移除时间 */
    @Excel(name = "移除时间")
    private String moveDate;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;


}
