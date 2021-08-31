package com.saipaisi.project.basic.domain;

import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 软件著作权对象 basic_software_copyright
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Data
public class BasicSoftwareCopyright extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 登记号 */
    @Excel(name = "登记号")
    private String registerNum;

    /** 分类号 */
    @Excel(name = "分类号")
    private String classlyCode;

    /** 软件名称 */
    @Excel(name = "软件名称")
    private String softwareName;

    /** 软件著作人 */
    @Excel(name = "软件著作人")
    private String copyrightName;

    /** 登记日期 */
    @Excel(name = "登记日期")
    private String recordDate;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;


}
