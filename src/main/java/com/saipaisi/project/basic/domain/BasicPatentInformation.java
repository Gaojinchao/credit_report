package com.saipaisi.project.basic.domain;

import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 专利信息对象 basic_patent_information
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Data
public class BasicPatentInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 专利名称 */
    @Excel(name = "专利名称")
    private String name;

    /** 公开号 */
    @Excel(name = "公开号")
    private String pubCode;

    /** 发证机关 */
    @Excel(name = "发证机关")
    private String licenceissuingAuth;

    /** 有效期 */
    @Excel(name = "有效期")
    private String periodValidity;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;


}
