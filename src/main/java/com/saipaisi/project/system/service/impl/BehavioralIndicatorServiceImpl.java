package com.saipaisi.project.system.service.impl;

import java.util.List;
import com.saipaisi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.system.mapper.BehavioralIndicatorMapper;
import com.saipaisi.project.system.domain.BehavioralIndicator;
import com.saipaisi.project.system.service.BehavioralIndicatorService;

/**
 * 行业指标Service业务层处理
 * 
 * @author alfredzong
 * @date 2021-05-25
 */
@Service
public class BehavioralIndicatorServiceImpl implements BehavioralIndicatorService
{
    @Autowired
    private BehavioralIndicatorMapper behavioralIndicatorMapper;

    /**
     * 查询行业指标
     * 
     * @param id 行业指标ID
     * @return 行业指标
     */
    @Override
    public BehavioralIndicator selectBehavioralIndicatorById(Long id)
    {
        return behavioralIndicatorMapper.selectBehavioralIndicatorById(id);
    }

    @Override
    public BehavioralIndicator selectBehavioralIndicatorByCode(String code) {
        return behavioralIndicatorMapper.selectBehavioralIndicatorByCode(code);
    }

    /**
     * 查询行业指标列表
     * 
     * @param behavioralIndicator 行业指标
     * @return 行业指标
     */
    @Override
    public List<BehavioralIndicator> selectBehavioralIndicatorList(BehavioralIndicator behavioralIndicator)
    {
        return behavioralIndicatorMapper.selectBehavioralIndicatorList(behavioralIndicator);
    }

    /**
     * 新增行业指标
     * 
     * @param behavioralIndicator 行业指标
     * @return 结果
     */
    @Override
    public int insertBehavioralIndicator(BehavioralIndicator behavioralIndicator)
    {
        behavioralIndicator.setCreateTime(DateUtils.getNowDate());
        return behavioralIndicatorMapper.insertBehavioralIndicator(behavioralIndicator);
    }

    /**
     * 修改行业指标
     * 
     * @param behavioralIndicator 行业指标
     * @return 结果
     */
    @Override
    public int updateBehavioralIndicator(BehavioralIndicator behavioralIndicator)
    {
        behavioralIndicator.setUpdateTime(DateUtils.getNowDate());
        return behavioralIndicatorMapper.updateBehavioralIndicator(behavioralIndicator);
    }

    /**
     * 批量删除行业指标
     * 
     * @param ids 需要删除的行业指标ID
     * @return 结果
     */
    @Override
    public int deleteBehavioralIndicatorByIds(Long[] ids)
    {
        return behavioralIndicatorMapper.deleteBehavioralIndicatorByIds(ids);
    }

    /**
     * 删除行业指标信息
     * 
     * @param id 行业指标ID
     * @return 结果
     */
    @Override
    public int deleteBehavioralIndicatorById(Long id)
    {
        return behavioralIndicatorMapper.deleteBehavioralIndicatorById(id);
    }
}
