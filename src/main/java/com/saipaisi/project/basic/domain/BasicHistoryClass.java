package com.saipaisi.project.basic.domain;

import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 历史等级对象 basic_history_class
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Data
public class BasicHistoryClass extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 年份 */
    @Excel(name = "年份")
    private String year;

    /** 等级 */
    @Excel(name = "等级")
    private String grade;

    /** 评级公司 */
    @Excel(name = "评级公司")
    private String ratingFirms;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;


}
