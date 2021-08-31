package com.saipaisi.project.basic.domain;

import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 失信企业对象 basic_promise_enterprise
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Data
public class BasicPromiseEnterprise extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 日期 */
    @Excel(name = "日期")
    private String dateis;

    /** 执行文号 */
    @Excel(name = "执行文号")
    private String referenceNumber;

    /** 案件号 */
    @Excel(name = "案件号")
    private String caseCode;

    /** 义务 */
    @Excel(name = "义务")
    private String obligation;

    /** 履行情况 */
    @Excel(name = "履行情况")
    private String performcondition;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;


}
