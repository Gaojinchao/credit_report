package com.saipaisi.project.system.domain;

import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 行业指标对象 behavioral_indicator
 * 
 * @author alfredzong
 * @date 2021-05-25
 */
@Data
public class BehavioralIndicator extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 编号 */
    @Excel(name = "编号")
    private String code;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 营业增长率 */
    @Excel(name = "营业增长率")
    private String operatingGrowthRate;

    /** 营业利润增长率 */
    @Excel(name = "营业利润增长率")
    private String growthRateOperatingProfit;

    /** 发展能力的资本积累率 */
    @Excel(name = "发展能力的资本积累率")
    private String developCapitalAccumulation;

    /** 资产负债率 */
    @Excel(name = "资产负债率")
    private String assetLiabilityRatio;

    /** 现金流动负债比率 */
    @Excel(name = "现金流动负债比率")
    private String cashFlowLiabilityRatio;

    /** 速动比率 */
    @Excel(name = "速动比率")
    private String quickRatio;

    /** 利息保障倍数 */
    @Excel(name = "利息保障倍数")
    private String timesInterestEarned;

    /** 总资产周转率 */
    @Excel(name = "总资产周转率")
    private String turnoverTotalCapital;

    /** 应收账款周转率 */
    @Excel(name = "应收账款周转率")
    private String turnoverAccountReceivable;

    /** 流动资产周转率 */
    @Excel(name = "流动资产周转率")
    private String velocityLiquidAssets;

    /** 净资产收益率 */
    @Excel(name = "净资产收益率")
    private String returnEquity;

    /** 销售（营业）利润率 */
    @Excel(name = "销售", readConverterExp = "营=业")
    private String salesOperatingProfit;

    /** 盈余现金保障倍数 */
    @Excel(name = "盈余现金保障倍数")
    private String earnedProtectionMultiple;

    /** 销售（营业）增长率 */
    @Excel(name = "销售", readConverterExp = "营=业")
    private String salesBusinessGrowthRate;

    /** 销售（营业）利润增长率 */
    @Excel(name = "销售", readConverterExp = "营=业")
    private String salesOperatingGrowthRate;

    /** 资本积累率 */
    @Excel(name = "资本积累率")
    private String rateCapitalAccumulation;


}
