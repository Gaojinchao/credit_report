package com.saipaisi.project.order.domain;

import com.saipaisi.project.basic.domain.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author alfred.zong
 * @Date 2021/5/26 16:16
 * @Description
 */
@Data
public class AllMainInfoReq implements Serializable {

    /**
     * 基本信息
     */
    private BasicInformation basicInformation;

    /**
     * 主要经营者信息
     */
    private List<BasicMainRunManager> basicMainRunManager;

    /**
     * 行业地位
     */
    private List<BasicPositionIndustry> basicPositionIndustry;

    /**
     * 主要管理者信息
     */
    private List<BasicMainManager> basicMainManager;

    /**
     * 荣誉
     */
    private List<BasicHonor> basicHonor;

    /**
     * 从业人员信息
     */
    private List<BasicEmployeeCondition> basicEmployeeCondition;

    /**
     * 体系信息
     */
    private List<BasicSystemInformation> basicSystemInformations;

    /**
     * 公司架构图
     */
    private String architecture;
    /**
     * 中标履约
     */
    private List<BasicWinBidding> basicWinBidding;

    /**
     * 现金表
     */
    private String cashStatement;

    /**
     * 负债表
     */
    private String balanceSheet;

    /**
     * 利润表
     */
    private String incomeStatement;

    private String oneDate;

    private String twoDate;

    private String threeDate;


}