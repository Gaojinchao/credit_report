package com.saipaisi.project.basic.domain;

import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 被执行企业对象 basic_executed_enterprise
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Data
public class BasicExecutedEnterprise extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 日期 */
    @Excel(name = "日期")
    private String judgmentDate;

    /** 被执行人 */
    @Excel(name = "被执行人")
    private String personSubject;

    /** 案件号 */
    @Excel(name = "案件号")
    private String caseCode;

    /** 执行标准 */
    @Excel(name = "执行标准")
    private String executive;

    /** 法院 */
    @Excel(name = "法院")
    private String court;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;


}
