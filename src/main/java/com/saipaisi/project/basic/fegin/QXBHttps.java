package com.saipaisi.project.basic.fegin;

import com.saipaisi.project.basic.domain.api.SearchApiEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Author alfred.zong
 * @Date 2021/5/24 14:05
 * @Description 启信宝调用
 */
@FeignClient(name = "QXBHttps",url = "${qxinbao.prod-url}")
public interface QXBHttps {


    /**
     * 工商照面,企业基本信息
     * get请求
     * @return
     */
    @RequestMapping(value = "/enterprise/getBasicInfo",method = RequestMethod.GET)
    public Map<String,Object> getBasicInfo(SearchApiEntity searchApiEntity);

    /**
     * 变更记录
     */
    @RequestMapping(value = "/enterprise/getChangeRecords",method = RequestMethod.GET)
    public Map<String,Object> getChangeRecords(SearchApiEntity searchApiEntity);


    /**
     * 工商股东信息
     * @return
     */
    @RequestMapping(value = "/enterprise/getPartners",method = RequestMethod.GET)
    public Map<String,Object> getPartners(SearchApiEntity searchApiEntity);

    /**
     * 分支机构
     * @return
     */
    @RequestMapping(value = "/enterprise/getBranchs",method = RequestMethod.GET)
    public Map<String,Object> getBranchs(SearchApiEntity searchApiEntity);

    /**
     * 对外投资
     * @return
     */
    @RequestMapping(value = "/enterprise/getInvestmentByName",method = RequestMethod.GET)
    public Map<String,Object> getInvestmentByName(Map<String,String> stringMap);

    /**
     * 专利信息
     * @return
     */
    @RequestMapping(value = "/enterprise/getPatentListByName",method = RequestMethod.GET)
    public Map<String,Object> getPatentListByName(Map<String,String> stringMap);

    /**
     * 软件著作权
     * @return
     */
    @RequestMapping(value = "/copyright/getCopyrightSoftByName",method = RequestMethod.GET)
    public Map<String,Object> getCopyrightSoftByName(Map<String,String> stringMap);

    /**
     * 著作权
     * @return
     */
    @RequestMapping(value = "/copyright/getCopyrightByName",method = RequestMethod.GET)
    public Map<String,Object> getCopyrightByName(SearchApiEntity searchApiEntity);

    /**
     * 资质信息
     * @return
     */
    @RequestMapping(value = "/certificate/getCertificateByName",method = RequestMethod.GET)
    public Map<String,Object> getCertificateByName(Map<String,String> stringMap);

    /**
     * 裁判文书列表
     * @return
     */
    @RequestMapping(value = "/lawsuit/getLawsuitListByName",method = RequestMethod.GET)
    public Map<String,Object> getLawsuitListByName(Map<String,String> stringMap);

    /**
     * 被执行企业
     * @return
     */
    @RequestMapping(value = "/execution/getExecutedpersonListByName",method = RequestMethod.GET)
    public Map<String,Object> getExecutedpersonListByName(Map<String,String> stringMap);

    /**
     * 行政处罚
     * @return
     */
    @RequestMapping(value = "/adminPunish/getAdminPunishByName",method = RequestMethod.GET)
    public Map<String,Object> getAdminPunishByName(SearchApiEntity searchApiEntity);

    /**
     * 失信被执行企业
     * @return
     */
    @RequestMapping(value = "/execution/getExecutionListByName",method = RequestMethod.GET)
    public Map<String,Object>  getExecutionListByName(SearchApiEntity searchApiEntity);

}
