package com.saipaisi.project.basic.domain;

import com.alibaba.fastjson.JSONObject;
import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 行业地位对象 basic_position_industry
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Data
public class BasicPositionIndustry extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 项目 */
    @Excel(name = "项目")
    private String project;

    /** 年份 */
    @Excel(name = "年份")
    private String year;

    /** 内容 */
    @Excel(name = "内容")
    private String context;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;

    private String one;
    private String two;
    private String three;



    public static void main(String[] args) {
        BasicPositionIndustry basicPositionIndustry = new BasicPositionIndustry();
        basicPositionIndustry.setProject("项目");
        basicPositionIndustry.setYear("2021");
        basicPositionIndustry.setContext("开发开发");
        basicPositionIndustry.setOrderSn("18503c1d1db445a9a0efe91b2c827c27");
        String string = JSONObject.toJSONString(basicPositionIndustry);
        System.out.println(string);
    }

}
