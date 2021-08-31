package com.saipaisi.project.basic.domain;

import com.alibaba.fastjson.JSONObject;
import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 从业人员情况对象 basic_employee_condition
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Data
public class BasicEmployeeCondition extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 人员总数 */
    @Excel(name = "人员总数")
    private String personSum;

    /** 高中及以下 */
    @Excel(name = "高中及以下")
    private Long seniorSchool;

    /** 专科 */
    @Excel(name = "专科")
    private String junior;

    /** 本科 */
    @Excel(name = "本科")
    private String regular;

    /** 一两年经验 */
    @Excel(name = "一两年经验")
    private Long oneTwoYears;

    /** 三到五年 */
    @Excel(name = "三到五年")
    private Long threeFiveYears;

    /** 五年更多 */
    @Excel(name = "五年更多")
    private Long moreThanFiveYear;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;



    public static void main(String[] args) {
        BasicEmployeeCondition basicEmployeeCondition = new BasicEmployeeCondition();
        basicEmployeeCondition.setPersonSum("科学奖");
        basicEmployeeCondition.setSeniorSchool(0L);
        basicEmployeeCondition.setJunior("5");
        basicEmployeeCondition.setRegular("15");
        basicEmployeeCondition.setOneTwoYears(5L);
        basicEmployeeCondition.setThreeFiveYears(10L);
        basicEmployeeCondition.setMoreThanFiveYear(5L);
        basicEmployeeCondition.setOrderSn("18503c1d1db445a9a0efe91b2c827c27");
        String string = JSONObject.toJSONString(basicEmployeeCondition);
        System.out.println(string);
    }

}
