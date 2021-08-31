package com.saipaisi.project.basic.service;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicEmployeeCondition;

/**
 * 从业人员情况Service接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicEmployeeConditionService
{
    /**
     * 查询从业人员情况
     * 
     * @param id 从业人员情况ID
     * @return 从业人员情况
     */
    public BasicEmployeeCondition selectBasicEmployeeConditionById(Long id);

    /**
     * 查询从业人员情况
     *
     * @param ordersn 从业人员情况ID
     * @return 从业人员情况
     */
    public List<BasicEmployeeCondition> selectBasicEmployeeConditionByOrdersn(String ordersn);

    /**
     * 查询从业人员情况列表
     * 
     * @param basicEmployeeCondition 从业人员情况
     * @return 从业人员情况集合
     */
    public List<BasicEmployeeCondition> selectBasicEmployeeConditionList(BasicEmployeeCondition basicEmployeeCondition);

    /**
     * 新增从业人员情况
     * 
     * @param basicEmployeeCondition 从业人员情况
     * @return 结果
     */
    public int insertBasicEmployeeCondition(BasicEmployeeCondition basicEmployeeCondition);

    /**
     * 修改从业人员情况
     * 
     * @param basicEmployeeCondition 从业人员情况
     * @return 结果
     */
    public int updateBasicEmployeeCondition(BasicEmployeeCondition basicEmployeeCondition);

    /**
     * 批量删除从业人员情况
     * 
     * @param ids 需要删除的从业人员情况ID
     * @return 结果
     */
    public int deleteBasicEmployeeConditionByIds(Long[] ids);

    /**
     * 删除从业人员情况信息
     * 
     * @param id 从业人员情况ID
     * @return 结果
     */
    public int deleteBasicEmployeeConditionById(Long id);

    /**
     *
     * @return
     */
    public int deleteBasicEmployeeConditionByOrdersn(String ordersn);
}
