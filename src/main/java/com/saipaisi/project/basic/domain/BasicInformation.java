package com.saipaisi.project.basic.domain;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSONObject;
import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 基本信息对象 basic_information
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Data
public class BasicInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 公司名称 */
    @Excel(name = "公司名称")
    private String name;

    /** 成立时间 */
    @Excel(name = "成立时间")
    private String startDate;

    /** 法人 */
    @Excel(name = "法人")
    private String operName;

    /** 社会统一信用号 */
    @Excel(name = "社会统一信用号")
    private String creditNo;

    /** 注册资金 */
    @Excel(name = "注册资金")
    private BigDecimal regCapiDesc;

    /** 企业状态 */
    @Excel(name = "企业状态")
    private String status;

    /** 企业住所 */
    @Excel(name = "企业住所")
    private String address;

    /** 经营范围 */
    @Excel(name = "经营范围")
    private String scope;

    /** 行业 */
    @Excel(name = "行业")
    private String industy;

    /** 开户行 */
    @Excel(name = "开户行")
    private String openBank;

    /** 贷款账号 */
    @Excel(name = "贷款账号")
    private String creditNumber;

    /** 账号 */
    @Excel(name = "账号")
    private String accout;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;

    /**
     * 企业类型
     */
    private String econKind;

    /**
     * 实缴资本
     */
    private BigDecimal actualCapi;

    /**
     * 高新企业
     */
    private Boolean highTech;

    /**
     * 公司规模
     * 1，微型企业
     * 2，小型企业
     * 3，中型企业
     * 4，大型企业
     */
    private String scale;

    /**
     * 是否有架构图
     * 1，否
     * 2是
     */
    private String architecture;

    /**
     * 图片
     */
    private String picture;

    /**
     * 发展战略
     * 1近三年发展规划未制定
     * 2,近三年发展规划切实可行且执行一般，得1分
     * 3近三年发展规划切实可行且执行一般，得1分
     */
    private String strategy;


    private String companyPhone;


    public static void main(String[] args) {
        BasicInformation basicInformation = new BasicInformation();
        basicInformation.setName("红米科技有限责任公司");
        basicInformation.setStartDate("2021-06-06");
        basicInformation.setOperName("雷军");
        basicInformation.setCreditNo("91110108551385082Q");

        int i = 185000;
        BigDecimal regCapiDesc = new BigDecimal(i);
        basicInformation.setRegCapiDesc(regCapiDesc);

        basicInformation.setStatus("在业");
        basicInformation.setAddress("北京市海淀区西二旗中路33号院6号楼6层006号");
        basicInformation.setScope("技术开发；货物进出口、技术进出口、代理进出口；");
        basicInformation.setIndusty("M7590");
        basicInformation.setOpenBank("中国银行股份有限公司西安阿房路支行");
        basicInformation.setAccout("3201240271010000005");
        basicInformation.setOrderSn("18503c1d1db445a9a0efe91b2c827c27");
        basicInformation.setEconKind("有限责任公司");

        int a = 5000;
        BigDecimal actualCapi = new BigDecimal(a);
        basicInformation.setActualCapi(actualCapi);

        basicInformation.setHighTech(true);
        basicInformation.setCompanyPhone("13166666666");
        String string = JSONObject.toJSONString(basicInformation);
        System.out.println(string);
    }

}
