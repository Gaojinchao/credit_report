package com.saipaisi.common.utils.word;

import cn.afterturn.easypoi.entity.ImageEntity;

import java.util.HashMap;

/**
 * @Author alfred.zong
 * @Date 2021/4/19 9:49
 * @Description
 */
public class test {

    public static void main(String[] args) {
        HashMap<String, Object> map = new HashMap<>();

        //模拟饼状图数据
        HashMap<String, Integer> datas = new HashMap<>(3);
//        datas.put("一号",10);
//        datas.put("二号",20);
//        datas.put("三号",40);
//        ImageEntity imageEntity = JfreeUtil.pieChart("测试",datas, 500, 300);
//        map.put("picture", imageEntity);

         ImageEntity imageEntity=new ImageEntity( JfreeUtil.imgToByPathByte("E:\\abcx.jpg"),500,300);
         map.put("images",imageEntity);
        //模拟其它普通数据
        map.put("companyName", "深圳怡丰自动化科技有限公司");
        map.put("makeDate", "2021年5月10日");
        map.put("conclusionPrompt", "深圳怡丰自动化科技有限公司成立于2003年7月，目前主要从事智能立体停车设备的设计、制造、安装及维护，拥有研发、设计、制造、安装等专业人才。公司作为国家高新技术企业，拥有多项专利及软件著作权，同时通过了ISO9001质量管理体系认证、ISO14001环境管理体系认证、OHSAS18001职业健康安全管理体系认证等。\n" +
                "公司管理人员行业从业时间较长，经验丰富；员工年龄结构合理，整体素质较好；公司组织架构清晰，分工明确；公司制定了各项管理制度，且严格执行；公司履约能力较强。");
        map.put("businessCircumstance", "公司负债规模一般，偿债能力较强，主要资产周转能力一般，整体营运能力一般；从盈利主要财务指标来看，公司盈利能力较强，盈利增长能力较好；近两年，业务规模扩张显著，主营业务收入增速明显，经营增长能力较强；公司经营获现能力较强。");
        map.put("supervisionInformation", "近三年，未发现公司在注册地省、市两级公共信用信息中心、税务、建设、人社、环保等行政主管部门有不良记录。");
        map.put("bidder", "经核查，近三年，未发现公司在招投标监管方面存在不良招投标信用记录。");

//        //模拟表格数据
//        ArrayList<HashMap<String, String>> list = new ArrayList<>(2);
//        HashMap<String, String> temp = new HashMap<>(3);
//        temp.put("sn","1");
//        temp.put("name","第一个人");
//        temp.put("age","23");
//        list.add(temp);
//        temp = new HashMap<>(3);
//        temp.put("sn","2");
//        temp.put("name","第二个人");
//        temp.put("age","24");
//        list.add(temp);
//        map.put("persionlist",list);
        //word模板相对路径、word生成路径、word生成的文件名称、数据源
        WordUtil.exportWord("E:\\原始项目\\世纪中启2021版报告模板.docx", "E:/", "生成文件.docx", map);
    }
}
