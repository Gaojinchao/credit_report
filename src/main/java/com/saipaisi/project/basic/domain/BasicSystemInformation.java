package com.saipaisi.project.basic.domain;

import com.alibaba.fastjson.JSONObject;
import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.web.domain.BaseEntity;
import lombok.Data;

/**
 * 体系信息对象 basic_system_information
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Data
public class BasicSystemInformation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 认证项目 */
    @Excel(name = "认证项目")
    private String certificationProgram;

    /** 证书编号 */
    @Excel(name = "证书编号")
    private String certificationCode;

    /** 有限期 */
    @Excel(name = "有限期")
    private String effectiveCutoff;

    /** 认证机构 */
    @Excel(name = "认证机构")
    private String certificationBody;

    /** 认证标准 */
    @Excel(name = "认证标准")
    private String standard;

    /** 认证范围 */
    @Excel(name = "认证范围")
    private String certificationScope;

    /** 类型1,1、安全管理体系 ,2,2、质量管理体系3、环境管理体系*/
    @Excel(name = "类型")
    private String type;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderSn;



    public static void main(String[] args) {
        BasicSystemInformation basicSystemInformation = new BasicSystemInformation();
        basicSystemInformation.setCertificationProgram("认证项目");
        basicSystemInformation.setCertificationCode("证书编号");
        basicSystemInformation.setEffectiveCutoff("有限期");
        basicSystemInformation.setCertificationBody("认证机构");
        basicSystemInformation.setStandard("认证标准");
        basicSystemInformation.setCertificationScope("认证范围");
        basicSystemInformation.setType("质量管理体系");
        basicSystemInformation.setOrderSn("18503c1d1db445a9a0efe91b2c827c27");
        String string = JSONObject.toJSONString(basicSystemInformation);
        System.out.println(string);
    }

}
