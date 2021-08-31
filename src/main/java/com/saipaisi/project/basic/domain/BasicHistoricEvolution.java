package com.saipaisi.project.basic.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 历史沿革对象 basic_historic_evolution
 * 
 * @author alfredzong
 * @date 2021-05-20
 */
@Data
public class BasicHistoricEvolution extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 变更时间 */
    @Excel(name = "变更时间")
    private Date changeDate;

    /** 变更内容 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "变更内容", width = 30, dateFormat = "yyyy-MM-dd")
    private String afterContent;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;


}
