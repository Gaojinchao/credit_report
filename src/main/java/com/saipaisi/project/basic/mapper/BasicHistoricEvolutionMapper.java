package com.saipaisi.project.basic.mapper;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicHistoricEvolution;

/**
 * 历史沿革Mapper接口
 * 
 * @author alfredzong
 * @date 2021-05-20
 */
public interface BasicHistoricEvolutionMapper 
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
     * 删除历史沿革
     * 
     * @param id 历史沿革ID
     * @return 结果
     */
    public int deleteBasicHistoricEvolutionById(Long id);

    /**
     * 批量删除历史沿革
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasicHistoricEvolutionByIds(Long[] ids);
}
