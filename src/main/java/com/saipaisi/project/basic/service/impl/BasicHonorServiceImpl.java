package com.saipaisi.project.basic.service.impl;

import java.util.List;
import com.saipaisi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.basic.mapper.BasicHonorMapper;
import com.saipaisi.project.basic.domain.BasicHonor;
import com.saipaisi.project.basic.service.BasicHonorService;

/**
 * 荣誉Service业务层处理
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Service
public class BasicHonorServiceImpl implements BasicHonorService
{
    @Autowired
    private BasicHonorMapper basicHonorMapper;

    /**
     * 查询荣誉
     * 
     * @param id 荣誉ID
     * @return 荣誉
     */
    @Override
    public BasicHonor selectBasicHonorById(Long id)
    {
        return basicHonorMapper.selectBasicHonorById(id);
    }

    @Override
    public List<BasicHonor> selectBasicHonorByordersn(String ordersn) {
        return basicHonorMapper.selectBasicHonorByordersn(ordersn);
    }

    /**
     * 查询荣誉列表
     * 
     * @param basicHonor 荣誉
     * @return 荣誉
     */
    @Override
    public List<BasicHonor> selectBasicHonorList(BasicHonor basicHonor)
    {
        return basicHonorMapper.selectBasicHonorList(basicHonor);
    }

    /**
     * 新增荣誉
     * 
     * @param basicHonor 荣誉
     * @return 结果
     */
    @Override
    public int insertBasicHonor(BasicHonor basicHonor)
    {
        basicHonor.setCreateTime(DateUtils.getNowDate());
        return basicHonorMapper.insertBasicHonor(basicHonor);
    }

    /**
     * 修改荣誉
     * 
     * @param basicHonor 荣誉
     * @return 结果
     */
    @Override
    public int updateBasicHonor(BasicHonor basicHonor)
    {
        basicHonor.setUpdateTime(DateUtils.getNowDate());
        return basicHonorMapper.updateBasicHonor(basicHonor);
    }

    /**
     * 批量删除荣誉
     * 
     * @param ids 需要删除的荣誉ID
     * @return 结果
     */
    @Override
    public int deleteBasicHonorByIds(Long[] ids)
    {
        return basicHonorMapper.deleteBasicHonorByIds(ids);
    }

    /**
     * 删除荣誉信息
     * 
     * @param id 荣誉ID
     * @return 结果
     */
    @Override
    public int deleteBasicHonorById(Long id)
    {
        return basicHonorMapper.deleteBasicHonorById(id);
    }

    @Override
    public int deleteBasicHonorByOrdersn(String ordersn) {
        return basicHonorMapper.deleteBasicHonorByOrdersn(ordersn);
    }
}
