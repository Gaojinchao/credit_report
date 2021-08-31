package com.saipaisi.project.basic.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.saipaisi.common.exception.CustomException;
import com.saipaisi.common.utils.DateUtils;
import com.saipaisi.project.basic.domain.api.SearchApiEntity;
import com.saipaisi.project.basic.fegin.QXBHttps;
import com.saipaisi.project.common.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.basic.mapper.BasicExecutedEnterpriseMapper;
import com.saipaisi.project.basic.domain.BasicExecutedEnterprise;
import com.saipaisi.project.basic.service.BasicExecutedEnterpriseService;

/**
 * 被执行企业Service业务层处理
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Service
public class BasicExecutedEnterpriseServiceImpl implements BasicExecutedEnterpriseService
{
    @Autowired
    private BasicExecutedEnterpriseMapper basicExecutedEnterpriseMapper;

    @Autowired
    private QXBHttps qxbHttps;

    /**
     * 查询被执行企业
     * 
     * @param id 被执行企业ID
     * @return 被执行企业
     */
    @Override
    public BasicExecutedEnterprise selectBasicExecutedEnterpriseById(Long id)
    {
        return basicExecutedEnterpriseMapper.selectBasicExecutedEnterpriseById(id);
    }

    /**
     * 查询被执行企业列表
     * 
     * @param basicExecutedEnterprise 被执行企业
     * @return 被执行企业
     */
    @Override
    public List<BasicExecutedEnterprise> selectBasicExecutedEnterpriseList(BasicExecutedEnterprise basicExecutedEnterprise)
    {
        return basicExecutedEnterpriseMapper.selectBasicExecutedEnterpriseList(basicExecutedEnterprise);
    }

    /**
     * 新增被执行企业
     * 
     * @param basicExecutedEnterprise 被执行企业
     * @return 结果
     */
    @Override
    public int insertBasicExecutedEnterprise(BasicExecutedEnterprise basicExecutedEnterprise)
    {
        basicExecutedEnterprise.setCreateTime(DateUtils.getNowDate());
        return basicExecutedEnterpriseMapper.insertBasicExecutedEnterprise(basicExecutedEnterprise);
    }

    /**
     * 修改被执行企业
     * 
     * @param basicExecutedEnterprise 被执行企业
     * @return 结果
     */
    @Override
    public int updateBasicExecutedEnterprise(BasicExecutedEnterprise basicExecutedEnterprise)
    {
        basicExecutedEnterprise.setUpdateTime(DateUtils.getNowDate());
        return basicExecutedEnterpriseMapper.updateBasicExecutedEnterprise(basicExecutedEnterprise);
    }

    /**
     * 批量删除被执行企业
     * 
     * @param ids 需要删除的被执行企业ID
     * @return 结果
     */
    @Override
    public int deleteBasicExecutedEnterpriseByIds(Long[] ids)
    {
        return basicExecutedEnterpriseMapper.deleteBasicExecutedEnterpriseByIds(ids);
    }

    /**
     * 删除被执行企业信息
     * 
     * @param id 被执行企业ID
     * @return 结果
     */
    @Override
    public int deleteBasicExecutedEnterpriseById(Long id)
    {
        return basicExecutedEnterpriseMapper.deleteBasicExecutedEnterpriseById(id);
    }

    @Override
    public int getExecutedpersonListByName(String keyword, String ordersn) {
        /*SearchApiEntity searchApiEntity=new SearchApiEntity();
        searchApiEntity.setName(keyword);*/

        Map<String,String> stringMap = new HashMap<>();
        stringMap.put("name",keyword);
        stringMap.put("appkey", Constant.APPKEY);
        stringMap.put("secret_key",Constant.SECRET_KEY);
        Map<String,Object> map=qxbHttps.getExecutedpersonListByName(stringMap);
        if ("200".equals(map.get("status"))) {
            Map<String, Object> reslut = (Map<String, Object>) map.get("data");
            List<Map<String, Object>> lists = (List<Map<String, Object>>) reslut.get("items");
            List<BasicExecutedEnterprise> list = new ArrayList<>();
            lists.stream().forEach(e->{
                BasicExecutedEnterprise basicExecutedEnterprise=new BasicExecutedEnterprise();
                basicExecutedEnterprise.setJudgmentDate((String) e.get("case_date"));
                basicExecutedEnterprise.setCaseCode("--");
                basicExecutedEnterprise.setCourt((String) e.get("court"));
                basicExecutedEnterprise.setExecutive("--");
                basicExecutedEnterprise.setPersonSubject("--");
                basicExecutedEnterprise.setOrderSn(ordersn);
                list.add(basicExecutedEnterprise);
            });
            list.stream().forEach(e->{
                basicExecutedEnterpriseMapper.insertBasicExecutedEnterprise(e);
            });
        }else {
            //throw new CustomException((String) map.get("message"));
            return 0;
        }
        return 1;
    }
}
