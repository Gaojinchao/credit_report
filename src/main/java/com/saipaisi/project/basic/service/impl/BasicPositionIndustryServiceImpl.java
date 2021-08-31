package com.saipaisi.project.basic.service.impl;

import java.util.List;
import com.saipaisi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.basic.mapper.BasicPositionIndustryMapper;
import com.saipaisi.project.basic.domain.BasicPositionIndustry;
import com.saipaisi.project.basic.service.BasicPositionIndustryService;

/**
 * 行业地位Service业务层处理
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Service
public class BasicPositionIndustryServiceImpl implements BasicPositionIndustryService
{
    @Autowired
    private BasicPositionIndustryMapper basicPositionIndustryMapper;

    /**
     * 查询行业地位
     * 
     * @param id 行业地位ID
     * @return 行业地位
     */
    @Override
    public BasicPositionIndustry selectBasicPositionIndustryById(Long id)
    {
        return basicPositionIndustryMapper.selectBasicPositionIndustryById(id);
    }

    @Override
    public List<BasicPositionIndustry> selectBasicPositionIndustryByOrdersn(String ordersn) {
        return basicPositionIndustryMapper.selectBasicPositionIndustryByOrdersn(ordersn);
    }

    /**
     * 查询行业地位列表
     * 
     * @param basicPositionIndustry 行业地位
     * @return 行业地位
     */
    @Override
    public List<BasicPositionIndustry> selectBasicPositionIndustryList(BasicPositionIndustry basicPositionIndustry)
    {
        return basicPositionIndustryMapper.selectBasicPositionIndustryList(basicPositionIndustry);
    }

    /**
     * 新增行业地位
     * 
     * @param basicPositionIndustry 行业地位
     * @return 结果
     */
    @Override
    public int insertBasicPositionIndustry(BasicPositionIndustry basicPositionIndustry)
    {
        basicPositionIndustry.setCreateTime(DateUtils.getNowDate());
        return basicPositionIndustryMapper.insertBasicPositionIndustry(basicPositionIndustry);
    }

    /**
     * 修改行业地位
     * 
     * @param basicPositionIndustry 行业地位
     * @return 结果
     */
    @Override
    public int updateBasicPositionIndustry(BasicPositionIndustry basicPositionIndustry)
    {
        basicPositionIndustry.setUpdateTime(DateUtils.getNowDate());
        return basicPositionIndustryMapper.updateBasicPositionIndustry(basicPositionIndustry);
    }

    /**
     * 批量删除行业地位
     * 
     * @param ids 需要删除的行业地位ID
     * @return 结果
     */
    @Override
    public int deleteBasicPositionIndustryByIds(Long[] ids)
    {
        return basicPositionIndustryMapper.deleteBasicPositionIndustryByIds(ids);
    }

    /**
     * 删除行业地位信息
     * 
     * @param id 行业地位ID
     * @return 结果
     */
    @Override
    public int deleteBasicPositionIndustryById(Long id)
    {
        return basicPositionIndustryMapper.deleteBasicPositionIndustryById(id);
    }

    @Override
    public int deleteBasicPositionIndustryByOrdersn(String ordersn) {
        return basicPositionIndustryMapper.deleteBasicPositionIndustryByOrdersn(ordersn);
    }
}
