package com.saipaisi.project.basic.domain;

import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 著作权对象 basic_copyright
 * 
 * @author alfredzong
 * @date 2021-05-26
 */
@Data
public class BasicCopyright extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 注册号 */
    @Excel(name = "注册号")
    private String number;

    /** 登记时间 */
    @Excel(name = "登记时间")
    private String firstDate;

    /** 著作权名称 */
    @Excel(name = "著作权名称")
    private String name;

    /** 软件著作人 */
    @Excel(name = "软件著作人")
    private String company;

    /** 发布时间 */
    @Excel(name = "发布时间")
    private String approvalDate;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;


}
