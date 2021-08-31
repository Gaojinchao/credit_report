package com.saipaisi.project.system.service;

import java.util.List;
import com.saipaisi.project.system.domain.BehavioralIndicator;

/**
 * 行业指标Service接口
 * 
 * @author alfredzong
 * @date 2021-05-25
 */
public interface BehavioralIndicatorService
{
    /**
     * 查询行业指标
     * 
     * @param id 行业指标ID
     * @return 行业指标
     */
    public BehavioralIndicator selectBehavioralIndicatorById(Long id);

    /**
     * 查询行业指标
     *
     * @param id 行业指标ID
     * @return 行业指标
     */
    public BehavioralIndicator selectBehavioralIndicatorByCode(String code);

    /**
     * 查询行业指标列表
     * 
     * @param behavioralIndicator 行业指标
     * @return 行业指标集合
     */
    public List<BehavioralIndicator> selectBehavioralIndicatorList(BehavioralIndicator behavioralIndicator);

    /**
     * 新增行业指标
     * 
     * @param behavioralIndicator 行业指标
     * @return 结果
     */
    public int insertBehavioralIndicator(BehavioralIndicator behavioralIndicator);

    /**
     * 修改行业指标
     * 
     * @param behavioralIndicator 行业指标
     * @return 结果
     */
    public int updateBehavioralIndicator(BehavioralIndicator behavioralIndicator);

    /**
     * 批量删除行业指标
     * 
     * @param ids 需要删除的行业指标ID
     * @return 结果
     */
    public int deleteBehavioralIndicatorByIds(Long[] ids);

    /**
     * 删除行业指标信息
     * 
     * @param id 行业指标ID
     * @return 结果
     */
    public int deleteBehavioralIndicatorById(Long id);
}
