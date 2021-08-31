package com.saipaisi.project.system.mapper;

import java.util.List;
import com.saipaisi.project.system.domain.SysPayLog;

/**
 * 付款记录Mapper接口
 * 
 * @author alfred.zong
 * @date 2020-12-11
 */
public interface SysPayLogMapper 
{
    /**
     * 查询付款记录
     * 
     * @param id 付款记录ID
     * @return 付款记录
     */
    public SysPayLog selectSysPayLogById(Long id);

    /**
     * 查询付款记录列表
     * 
     * @param sysPayLog 付款记录
     * @return 付款记录集合
     */
    public List<SysPayLog> selectSysPayLogList(SysPayLog sysPayLog);

    /**
     * 新增付款记录
     * 
     * @param sysPayLog 付款记录
     * @return 结果
     */
    public int insertSysPayLog(SysPayLog sysPayLog);

    /**
     * 修改付款记录
     * 
     * @param sysPayLog 付款记录
     * @return 结果
     */
    public int updateSysPayLog(SysPayLog sysPayLog);

    /**
     * 删除付款记录
     * 
     * @param id 付款记录ID
     * @return 结果
     */
    public int deleteSysPayLogById(Long id);

    /**
     * 批量删除付款记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysPayLogByIds(Long[] ids);
}
