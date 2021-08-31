package com.saipaisi.project.basic.service;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicHistoryClass;

/**
 * 历史等级Service接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicHistoryClassService
{
    /**
     * 查询历史等级
     * 
     * @param id 历史等级ID
     * @return 历史等级
     */
    public BasicHistoryClass selectBasicHistoryClassById(Long id);

    /**
     * 查询历史等级列表
     * 
     * @param basicHistoryClass 历史等级
     * @return 历史等级集合
     */
    public List<BasicHistoryClass> selectBasicHistoryClassList(BasicHistoryClass basicHistoryClass);

    /**
     * 新增历史等级
     * 
     * @param basicHistoryClass 历史等级
     * @return 结果
     */
    public int insertBasicHistoryClass(BasicHistoryClass basicHistoryClass);

    /**
     * 修改历史等级
     * 
     * @param basicHistoryClass 历史等级
     * @return 结果
     */
    public int updateBasicHistoryClass(BasicHistoryClass basicHistoryClass);

    /**
     * 批量删除历史等级
     * 
     * @param ids 需要删除的历史等级ID
     * @return 结果
     */
    public int deleteBasicHistoryClassByIds(Long[] ids);

    /**
     * 删除历史等级信息
     * 
     * @param id 历史等级ID
     * @return 结果
     */
    public int deleteBasicHistoryClassById(Long id);
}
