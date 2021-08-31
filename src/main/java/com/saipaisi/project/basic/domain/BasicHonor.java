package com.saipaisi.project.basic.domain;

import com.alibaba.fastjson.JSONObject;
import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 荣誉对象 basic_honor
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Data
public class BasicHonor extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 奖项名称 */
    @Excel(name = "奖项名称")
    private String awardName;

    /** 颁奖单位 */
    @Excel(name = "颁奖单位")
    private String prizeUnit;

    /** 获奖时间 */
    @Excel(name = "获奖时间")
    private String winningTime;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;



    public static void main(String[] args) {
        BasicHonor basicHonor = new BasicHonor();
        basicHonor.setAwardName("科学奖");
        basicHonor.setPrizeUnit("科学研究院");
        basicHonor.setWinningTime("2021-06-06");
        basicHonor.setOrderSn("18503c1d1db445a9a0efe91b2c827c27");
        String string = JSONObject.toJSONString(basicHonor);
        System.out.println(string);
    }

}
