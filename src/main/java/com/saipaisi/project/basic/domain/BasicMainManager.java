package com.saipaisi.project.basic.domain;

import com.alibaba.fastjson.JSONObject;
import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 主要管理者信息对象 basic_main_manager
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Data
public class BasicMainManager extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 姓名 */
    @Excel(name = "姓名")
    private String mainManagerName;

    /** 职务 */
    @Excel(name = "职务")
    private String mainManagerPost;

    /** 年龄 */
    @Excel(name = "年龄")
    private Long mainManagerAge;

    /**
     * 信用记录
     */
    private String creditRecord;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;



    public static void main(String[] args) {
        BasicMainManager basicMainManager = new BasicMainManager();
        basicMainManager.setMainManagerName("雷雷");
        basicMainManager.setMainManagerPost("CEO");
        basicMainManager.setMainManagerAge(18L);
        basicMainManager.setCreditRecord("信用记录");
        basicMainManager.setOrderSn("18503c1d1db445a9a0efe91b2c827c27");
        String string = JSONObject.toJSONString(basicMainManager);
        System.out.println(string);
    }

}
