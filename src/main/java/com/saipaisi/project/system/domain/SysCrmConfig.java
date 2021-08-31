package com.saipaisi.project.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * crm系统配置对象 sys_crm_config
 * 
 * @author saipaisi
 * @date 2020-08-19
 */
@Data
public class SysCrmConfig
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 公司id */
    @Excel(name = "公司id")
    private Long companyId;

    /** 配置字段1 */
    @Excel(name = "配置字段1")
    private String field1;

    /** 配置字段2 */
    @Excel(name = "配置字段2")
    private String field2;

    /** 配置字段3 */
    @Excel(name = "配置字段3")
    private String field3;

    /** 功能 */
    @Excel(name = "功能")
    private String name;

    /** 功能描述 */
    @Excel(name = "功能描述")
    private String des;

    /**
     * 创建日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;

    /**
     * 更新日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
}
