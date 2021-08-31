package com.saipaisi.project.company.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 公司对象 company
 * 
 * @author alfred.zong
 * @date 2021-05-13
 */
@Data
public class Company extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String companyNameCn;

    /** 英文名字 */
    @Excel(name = "英文名字")
    private String companyNameEn;

    /** logo */
    @Excel(name = "logo")
    private String logo;

    /** 联系人 */
    @Excel(name = "联系人")
    private String linkman;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private String phone;

    /** 余额 */
    @Excel(name = "余额")
    private BigDecimal balance;

    /** 单价 */
    @Excel(name = "单价")
    private BigDecimal unitPrice;

    /** 截止日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截止日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date expirationDate;

    /** 开户行 */
    @Excel(name = "开户行")
    private String bankDeposit;

    /** 开户账号 */
    @Excel(name = "开户账号")
    private String accountNumber;


}
