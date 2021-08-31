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
import com.saipaisi.project.basic.mapper.BasicBranchOrgMapper;
import com.saipaisi.project.basic.domain.BasicBranchOrg;
import com.saipaisi.project.basic.service.BasicBranchOrgService;

/**
 * 分支机构Service业务层处理
 * 
 * @author alfred.zong
 * @date 2021-05-25
 */
@Service
public class BasicBranchOrgServiceImpl implements BasicBranchOrgService
{
    @Autowired
    private BasicBranchOrgMapper basicBranchOrgMapper;

    @Autowired
    private QXBHttps qxbHttps;

    /**
     * 查询分支机构
     * 
     * @param id 分支机构ID
     * @return 分支机构
     */
    @Override
    public BasicBranchOrg selectBasicBranchOrgById(Long id)
    {
        return basicBranchOrgMapper.selectBasicBranchOrgById(id);
    }

    /**
     * 查询分支机构列表
     * 
     * @param basicBranchOrg 分支机构
     * @return 分支机构
     */
    @Override
    public List<BasicBranchOrg> selectBasicBranchOrgList(BasicBranchOrg basicBranchOrg)
    {
        return basicBranchOrgMapper.selectBasicBranchOrgList(basicBranchOrg);
    }

    /**
     * 新增分支机构
     * 
     * @param basicBranchOrg 分支机构
     * @return 结果
     */
    @Override
    public int insertBasicBranchOrg(BasicBranchOrg basicBranchOrg)
    {
        basicBranchOrg.setCreateTime(DateUtils.getNowDate());
        return basicBranchOrgMapper.insertBasicBranchOrg(basicBranchOrg);
    }

    /**
     * 修改分支机构
     * 
     * @param basicBranchOrg 分支机构
     * @return 结果
     */
    @Override
    public int updateBasicBranchOrg(BasicBranchOrg basicBranchOrg)
    {
        basicBranchOrg.setUpdateTime(DateUtils.getNowDate());
        return basicBranchOrgMapper.updateBasicBranchOrg(basicBranchOrg);
    }

    /**
     * 批量删除分支机构
     * 
     * @param ids 需要删除的分支机构ID
     * @return 结果
     */
    @Override
    public int deleteBasicBranchOrgByIds(Long[] ids)
    {
        return basicBranchOrgMapper.deleteBasicBranchOrgByIds(ids);
    }

    /**
     * 删除分支机构信息
     * 
     * @param id 分支机构ID
     * @return 结果
     */
    @Override
    public int deleteBasicBranchOrgById(Long id)
    {
        return basicBranchOrgMapper.deleteBasicBranchOrgById(id);
    }

    @Override
    public int getBranchsInfo(String keyword,String ordersn) {
        SearchApiEntity searchApiEntity=new SearchApiEntity();
        searchApiEntity.setKeyword(keyword);
        Map<String,Object> map=qxbHttps.getBranchs(searchApiEntity);
        if ("200".equals(map.get("status"))){
            Map<String,Object> reslut=( Map<String, Object>) map.get("data");
            List<Map<String,Object>> lists= (List<Map<String, Object>>) reslut.get("items");
            List<BasicBranchOrg> list=new ArrayList<>();
            lists.stream().forEach(e->{
                BasicBranchOrg basicBranchOrg=new BasicBranchOrg();
                basicBranchOrg.setBranchOrgName((String) e.get("name"));
                basicBranchOrg.setOrderSn(ordersn);
                list.add(basicBranchOrg);
            });
            list.forEach(e->{
                basicBranchOrgMapper.insertBasicBranchOrg(e);
            });
        }else {
            throw new CustomException((String) map.get("message"));
        }
        return 1;
    }
}
