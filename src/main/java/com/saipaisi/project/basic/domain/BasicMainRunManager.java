package com.saipaisi.project.basic.domain;

import com.alibaba.fastjson.JSONObject;
import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 主要经营者管理者信息对象 basic_main_run_manager
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Data
public class BasicMainRunManager extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /**
     * 主要经营者名称
     */
    private String mainRunName;

    /** 姓名 */
    @Excel(name = "岗位")
    private String post;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long age;

    /** 学历
     * 1,小学
     * 2，初中
     * 3，高中
     * 4，中专
     * 5大专
     * 6，本科
     * 7，硕士
     * 8，博士
     * */
    @Excel(name = "学历")
    private Integer education;

    /** 职称
     * 1，初级
     * 2中级
     * 3高级
     * */
    @Excel(name = "职称")
    private String rank;

    /** 工作年限 */
    @Excel(name = "工作年限")
    private Long entirEperiod;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;



    public static void main(String[] args) {
        BasicMainRunManager basicMainRunManager = new BasicMainRunManager();
        basicMainRunManager.setMainRunName("红米");
        basicMainRunManager.setPost("技术开发");
        basicMainRunManager.setAge(18L);
        basicMainRunManager.setEducation(8);
        basicMainRunManager.setRank("中级");
        basicMainRunManager.setEntirEperiod(10L);
        basicMainRunManager.setOrderSn("18503c1d1db445a9a0efe91b2c827c27");
        String string = JSONObject.toJSONString(basicMainRunManager);
        System.out.println(string);
    }

}
