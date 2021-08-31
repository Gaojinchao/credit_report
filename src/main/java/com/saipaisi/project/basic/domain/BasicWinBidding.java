package com.saipaisi.project.basic.domain;

import com.alibaba.fastjson.JSONObject;
import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 中标履约对象 basic_win_bidding
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Data
public class BasicWinBidding extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 项目 */
    @Excel(name = "项目")
    private String project;

    /** 业主方 */
    @Excel(name = "业主方")
    private String proprietor;

    /** 调查时间 */
    @Excel(name = "调查时间")
    private String surveytime;

    /** 调查方式 */
    @Excel(name = "调查方式")
    private String surveyway;

    /** 是否履约 */
    @Excel(name = "是否履约")
    private String state;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;



    public static void main(String[] args) {
        BasicWinBidding basicWinBidding = new BasicWinBidding();
        basicWinBidding.setProject("项目");
        basicWinBidding.setProprietor("业主方");
        basicWinBidding.setSurveytime("调查时间");
        basicWinBidding.setSurveyway("调查方式");
        basicWinBidding.setState("是否履约");
        basicWinBidding.setOrderSn("18503c1d1db445a9a0efe91b2c827c27");
        String string = JSONObject.toJSONString(basicWinBidding);
        System.out.println(string);
    }

}
