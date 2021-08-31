package com.saipaisi.project.basic.service.impl;

import java.util.List;
import com.saipaisi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.basic.mapper.BasicHistoryClassMapper;
import com.saipaisi.project.basic.domain.BasicHistoryClass;
import com.saipaisi.project.basic.service.BasicHistoryClassService;

/**
 * 历史等级Service业务层处理
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Service
public class BasicHistoryClassServiceImpl implements BasicHistoryClassService
{
    @Autowired
    private BasicHistoryClassMapper basicHistoryClassMapper;

    /**
     * 查询历史等级
     * 
     * @param id 历史等级ID
     * @return 历史等级
     */
    @Override
    public BasicHistoryClass selectBasicHistoryClassById(Long id)
    {
        return basicHistoryClassMapper.selectBasicHistoryClassById(id);
    }

    /**
     * 查询历史等级列表
     * 
     * @param basicHistoryClass 历史等级
     * @return 历史等级
     */
    @Override
    public List<BasicHistoryClass> selectBasicHistoryClassList(BasicHistoryClass basicHistoryClass)
    {
        return basicHistoryClassMapper.selectBasicHistoryClassList(basicHistoryClass);
    }

    /**
     * 新增历史等级
     * 
     * @param basicHistoryClass 历史等级
     * @return 结果
     */
    @Override
    public int insertBasicHistoryClass(BasicHistoryClass basicHistoryClass)
    {
        basicHistoryClass.setCreateTime(DateUtils.getNowDate());
        return basicHistoryClassMapper.insertBasicHistoryClass(basicHistoryClass);
    }

    /**
     * 修改历史等级
     * 
     * @param basicHistoryClass 历史等级
     * @return 结果
     */
    @Override
    public int updateBasicHistoryClass(BasicHistoryClass basicHistoryClass)
    {
        basicHistoryClass.setUpdateTime(DateUtils.getNowDate());
        return basicHistoryClassMapper.updateBasicHistoryClass(basicHistoryClass);
    }

    /**
     * 批量删除历史等级
     * 
     * @param ids 需要删除的历史等级ID
     * @return 结果
     */
    @Override
    public int deleteBasicHistoryClassByIds(Long[] ids)
    {
        return basicHistoryClassMapper.deleteBasicHistoryClassByIds(ids);
    }

    /**
     * 删除历史等级信息
     * 
     * @param id 历史等级ID
     * @return 结果
     */
    @Override
    public int deleteBasicHistoryClassById(Long id)
    {
        return basicHistoryClassMapper.deleteBasicHistoryClassById(id);
    }
}
