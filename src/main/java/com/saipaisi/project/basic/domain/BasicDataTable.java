package com.saipaisi.project.basic.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 数据对象 basic_data_table
 * 
 * @author saaa
 * @date 2021-05-20
 */
@Data
public class BasicDataTable extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 年份 */
    @ExcelProperty(index = 0)
    private String title;

    /** 排序 */
    private int sortIndex;

    /** 近一年 */
    @ExcelProperty(index = 1)
    @Excel(name = "近一年")
    private BigDecimal one=BigDecimal.ZERO;

    /** 近两年 */
    @Excel(name = "近两年")
    @ExcelProperty(index = 2)
    private BigDecimal two=BigDecimal.ZERO;

    /** 近三年 */
    @Excel(name = "近三年")
    @ExcelProperty(index = 3)
    private BigDecimal three=BigDecimal.ZERO;

    /** 近四年 */
    @Excel(name = "近四年")
    @ExcelProperty(index = 4)
    private BigDecimal four=BigDecimal.ZERO;

    /**
     * 1,现金，2利润，3负债，4计算的总表，5偿债能力，6运营能力，7盈利能力，8经营增长能力，9现金流量分析，10
     * 类型 */
    @Excel(name = "类型")
    private String type;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;


}
