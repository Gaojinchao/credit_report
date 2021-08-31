package com.saipaisi.project.basic.service.impl;

import java.util.List;
import com.saipaisi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.basic.mapper.BasicEmployeeConditionMapper;
import com.saipaisi.project.basic.domain.BasicEmployeeCondition;
import com.saipaisi.project.basic.service.BasicEmployeeConditionService;

/**
 * 从业人员情况Service业务层处理
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@Service
public class BasicEmployeeConditionServiceImpl implements BasicEmployeeConditionService
{
    @Autowired
    private BasicEmployeeConditionMapper basicEmployeeConditionMapper;

    /**
     * 查询从业人员情况
     * 
     * @param id 从业人员情况ID
     * @return 从业人员情况
     */
    @Override
    public BasicEmployeeCondition selectBasicEmployeeConditionById(Long id)
    {
        return basicEmployeeConditionMapper.selectBasicEmployeeConditionById(id);
    }

    @Override
    public List<BasicEmployeeCondition> selectBasicEmployeeConditionByOrdersn(String ordersn) {
        return basicEmployeeConditionMapper.selectBasicEmployeeConditionByOrdersn(ordersn);
    }

    /**
     * 查询从业人员情况列表
     * 
     * @param basicEmployeeCondition 从业人员情况
     * @return 从业人员情况
     */
    @Override
    public List<BasicEmployeeCondition> selectBasicEmployeeConditionList(BasicEmployeeCondition basicEmployeeCondition)
    {
        return basicEmployeeConditionMapper.selectBasicEmployeeConditionList(basicEmployeeCondition);
    }

    /**
     * 新增从业人员情况
     * 
     * @param basicEmployeeCondition 从业人员情况
     * @return 结果
     */
    @Override
    public int insertBasicEmployeeCondition(BasicEmployeeCondition basicEmployeeCondition)
    {
        basicEmployeeCondition.setCreateTime(DateUtils.getNowDate());
        return basicEmployeeConditionMapper.insertBasicEmployeeCondition(basicEmployeeCondition);
    }

    /**
     * 修改从业人员情况
     * 
     * @param basicEmployeeCondition 从业人员情况
     * @return 结果
     */
    @Override
    public int updateBasicEmployeeCondition(BasicEmployeeCondition basicEmployeeCondition)
    {
        basicEmployeeCondition.setUpdateTime(DateUtils.getNowDate());
        return basicEmployeeConditionMapper.updateBasicEmployeeCondition(basicEmployeeCondition);
    }

    /**
     * 批量删除从业人员情况
     * 
     * @param ids 需要删除的从业人员情况ID
     * @return 结果
     */
    @Override
    public int deleteBasicEmployeeConditionByIds(Long[] ids)
    {
        return basicEmployeeConditionMapper.deleteBasicEmployeeConditionByIds(ids);
    }

    /**
     * 删除从业人员情况信息
     * 
     * @param id 从业人员情况ID
     * @return 结果
     */
    @Override
    public int deleteBasicEmployeeConditionById(Long id)
    {
        return basicEmployeeConditionMapper.deleteBasicEmployeeConditionById(id);
    }

    @Override
    public int deleteBasicEmployeeConditionByOrdersn(String ordersn) {
        return basicEmployeeConditionMapper.deleteBasicEmployeeConditionByOrdersn(ordersn);
    }


}
