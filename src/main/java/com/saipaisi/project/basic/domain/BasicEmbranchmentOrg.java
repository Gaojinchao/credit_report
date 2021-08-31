package com.saipaisi.project.basic.domain;

import java.math.BigDecimal;
import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 对外投资企业 basic_embranchment_org
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Data
public class BasicEmbranchmentOrg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 法人 */
    @Excel(name = "法人")
    private String legalPerson;

    /** 投资金额 */
    @Excel(name = "投资金额")
    private BigDecimal realCapil;

    /** 投资比例 */
    @Excel(name = "投资比例")
    private String realCapilScale;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;

    /**
     * 公司名称
     */
    private String orgName;


}
