package com.saipaisi.project.basic.domain;

import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 资质信息对象 basic_qualification_information
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Data
public class BasicQualificationInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 等级 */
    @Excel(name = "等级")
    private String grade;

    /** 编号 */
    @Excel(name = "编号")
    private String serialNumber;

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
