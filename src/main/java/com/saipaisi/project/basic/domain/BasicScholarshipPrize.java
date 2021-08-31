package com.saipaisi.project.basic.domain;

import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 获奖情况对象 basic_scholarship_prize
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Data
public class BasicScholarshipPrize extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 颁奖单位 */
    @Excel(name = "颁奖单位")
    private String theUnit;

    /** 获奖时间 */
    @Excel(name = "获奖时间")
    private String winingTime;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;


}
