package com.saipaisi.project.basic.domain;

import java.math.BigDecimal;
import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 股东组成对象 basic_shareholder
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Data
public class BasicShareholder extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 投资人或者企业 */
    @Excel(name = "投资人或者企业")
    private String name;

    /** 认筹出资 */
    @Excel(name = "认筹出资")
    private BigDecimal shouldCapi;

    /** 实际出资 */
    @Excel(name = "实际出资")
    private BigDecimal realCapil;

    /** 认筹出资比例 */
    @Excel(name = "认筹出资比例")
    private BigDecimal shouldScale;

    /** 实际出资比例 */
    @Excel(name = "实际出资比例")
    private BigDecimal realScale;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;


}
