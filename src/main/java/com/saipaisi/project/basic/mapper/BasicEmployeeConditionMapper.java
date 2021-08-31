package com.saipaisi.project.basic.mapper;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicEmployeeCondition;

/**
 * 从业人员情况Mapper接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicEmployeeConditionMapper 
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
     * 删除从业人员情况
     * 
     * @param id 从业人员情况ID
     * @return 结果
     */
    public int deleteBasicEmployeeConditionById(Long id);

    /**
     * 批量删除从业人员情况
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasicEmployeeConditionByIds(Long[] ids);

    /**
     * 删除从业人员批量根据订单编号删除
     * @param ordersn
     * @return
     */
    public int deleteBasicEmployeeConditionByOrdersn(String ordersn);
}
