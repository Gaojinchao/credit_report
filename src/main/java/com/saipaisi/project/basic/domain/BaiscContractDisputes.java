package com.saipaisi.project.basic.domain;

import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 合同纠纷对象 baisc_contract_disputes
 *
 * @author sss
 * @date 2021-05-20
 */
@Data
public class BaiscContractDisputes extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 判决时间 */
    @Excel(name = "判决时间")
    private String decisonTime;

    /** 裁判文书编号 */
    @Excel(name = "裁判文书编号")
    private String decisonCode;

    /** 被告 */
    @Excel(name = "被告")
    private String defendant;

    /** 结果 */
    @Excel(name = "结果")
    private String result;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;
    public static void main(String[] args) {


    }

}
