package com.saipaisi.project.basic.service;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicHistoricEvolution;

/**
 * 历史沿革Service接口
 * 
 * @author alfredzong
 * @date 2021-05-20
 */
public interface BasicHistoricEvolutionService
{
    /**
     * 查询历史沿革
     * 
     * @param id 历史沿革ID
     * @return 历史沿革
     */
    public BasicHistoricEvolution selectBasicHistoricEvolutionById(Long id);

    /**
     * 查询历史沿革列表
     * 
     * @param basicHistoricEvolution 历史沿革
     * @return 历史沿革集合
     */
    public List<BasicHistoricEvolution> selectBasicHistoricEvolutionList(BasicHistoricEvolution basicHistoricEvolution);

    /**
     * 新增历史沿革
     * 
     * @param basicHistoricEvolution 历史沿革
     * @return 结果
     */
    public int insertBasicHistoricEvolution(BasicHistoricEvolution basicHistoricEvolution);

    /**
     * 修改历史沿革
     * 
     * @param basicHistoricEvolution 历史沿革
     * @return 结果
     */
    public int updateBasicHistoricEvolution(BasicHistoricEvolution basicHistoricEvolution);

    /**
     * 批量删除历史沿革
     * 
     * @param ids 需要删除的历史沿革ID
     * @return 结果
     */
    public int deleteBasicHistoricEvolutionByIds(Long[] ids);

    /**
     * 删除历史沿革信息
     * 
     * @param id 历史沿革ID
     * @return 结果
     */
    public int deleteBasicHistoricEvolutionById(Long id);

    /**
     *获取变更记录
     * @return
     */
    public int getChangeRecordsInfo(String keyword,String ordersn);
}
