package com.saipaisi.project.basic.domain;

import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 裁判文书对象 basic_judicative_paper
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Data
public class BasicJudicativePaper extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 裁判日期 */
    @Excel(name = "裁判日期")
    private String judgmentDate;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 案件号 */
    @Excel(name = "案件号")
    private String caseCode;

    /** 原因 */
    @Excel(name = "原因")
    private String cause;

    /** 法院 */
    @Excel(name = "法院")
    private String court;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;


}
