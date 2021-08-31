package com.saipaisi.project.basic.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.saipaisi.common.exception.CustomException;
import com.saipaisi.common.utils.DateUtils;
import com.saipaisi.project.basic.domain.api.SearchApiEntity;
import com.saipaisi.project.basic.fegin.QXBHttps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.basic.mapper.BasicPromiseEnterpriseMapper;
import com.saipaisi.project.basic.domain.BasicPromiseEnterprise;
import com.saipaisi.project.basic.service.BasicPromiseEnterpriseService;
import org.springframework.web.context.annotation.ApplicationScope;

/**
 * 失信企业Service业务层处理
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Service
public class BasicPromiseEnterpriseServiceImpl implements BasicPromiseEnterpriseService
{
    @Autowired
    private BasicPromiseEnterpriseMapper basicPromiseEnterpriseMapper;

    @Autowired
    private QXBHttps qxbHttps;
    /**
     * 查询失信企业
     * 
     * @param id 失信企业ID
     * @return 失信企业
     */
    @Override
    public BasicPromiseEnterprise selectBasicPromiseEnterpriseById(Long id)
    {
        return basicPromiseEnterpriseMapper.selectBasicPromiseEnterpriseById(id);
    }

    /**
     * 查询失信企业列表
     * 
     * @param basicPromiseEnterprise 失信企业
     * @return 失信企业
     */
    @Override
    public List<BasicPromiseEnterprise> selectBasicPromiseEnterpriseList(BasicPromiseEnterprise basicPromiseEnterprise)
    {
        return basicPromiseEnterpriseMapper.selectBasicPromiseEnterpriseList(basicPromiseEnterprise);
    }

    /**
     * 新增失信企业
     * 
     * @param basicPromiseEnterprise 失信企业
     * @return 结果
     */
    @Override
    public int insertBasicPromiseEnterprise(BasicPromiseEnterprise basicPromiseEnterprise)
    {
        basicPromiseEnterprise.setCreateTime(DateUtils.getNowDate());
        return basicPromiseEnterpriseMapper.insertBasicPromiseEnterprise(basicPromiseEnterprise);
    }

    /**
     * 修改失信企业
     * 
     * @param basicPromiseEnterprise 失信企业
     * @return 结果
     */
    @Override
    public int updateBasicPromiseEnterprise(BasicPromiseEnterprise basicPromiseEnterprise)
    {
        basicPromiseEnterprise.setUpdateTime(DateUtils.getNowDate());
        return basicPromiseEnterpriseMapper.updateBasicPromiseEnterprise(basicPromiseEnterprise);
    }

    /**
     * 批量删除失信企业
     * 
     * @param ids 需要删除的失信企业ID
     * @return 结果
     */
    @Override
    public int deleteBasicPromiseEnterpriseByIds(Long[] ids)
    {
        return basicPromiseEnterpriseMapper.deleteBasicPromiseEnterpriseByIds(ids);
    }

    /**
     * 删除失信企业信息
     * 
     * @param id 失信企业ID
     * @return 结果
     */
    @Override
    public int deleteBasicPromiseEnterpriseById(Long id)
    {
        return basicPromiseEnterpriseMapper.deleteBasicPromiseEnterpriseById(id);
    }

    @Override
    public int getExecutionListByName(String name, String ordersn) {
        SearchApiEntity searchApiEntity=new SearchApiEntity();
        searchApiEntity.setKeyword(name);
        Map<String,Object> map=qxbHttps.getExecutionListByName(searchApiEntity);
        if ("200".equals(map.get("status"))) {
            Map<String, Object> reslut = (Map<String, Object>) map.get("data");
            List<Map<String, Object>> lists = (List<Map<String, Object>>) reslut.get("items");
            List<BasicPromiseEnterprise> list = new ArrayList<>();
            lists.forEach(e->{
                BasicPromiseEnterprise basicPromiseEnterprise=new BasicPromiseEnterprise();
                basicPromiseEnterprise.setCaseCode((String) e.get("case_number"));
                basicPromiseEnterprise.setObligation((String) e.get("final_duty"));
                basicPromiseEnterprise.setPerformcondition((String) e.get("execution_status"));
                basicPromiseEnterprise.setDateis((String) e.get("date"));
                basicPromiseEnterprise.setReferenceNumber((String) e.get("doc_number"));
                basicPromiseEnterprise.setOrderSn(ordersn);
                list.add(basicPromiseEnterprise);
            });
            list.stream().forEach(e->{
                basicPromiseEnterpriseMapper.insertBasicPromiseEnterprise(e);
            });
        }else {
            throw new CustomException((String) map.get("message"));
        }
        return 1;
    }
}
