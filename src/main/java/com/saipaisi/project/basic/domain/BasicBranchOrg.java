package com.saipaisi.project.basic.domain;

import java.math.BigDecimal;
import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 分支机构对象 basic_branch_org
 * 
 * @author alfred.zong
 * @date 2021-05-25
 */
@Data
public class BasicBranchOrg extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 企业名称 */
    @Excel(name = "企业名称")
    private String branchOrgName;

    /** 地址 */
    @Excel(name = "地址")
    private String branchAddress;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private BigDecimal branchPhone;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;


}
