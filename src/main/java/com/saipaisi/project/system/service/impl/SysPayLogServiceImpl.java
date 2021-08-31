package com.saipaisi.project.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.system.mapper.SysPayLogMapper;
import com.saipaisi.project.system.domain.SysPayLog;
import com.saipaisi.project.system.service.SysPayLogService;

/**
 * 付款记录Service业务层处理
 * 
 * @author alfred.zong
 * @date 2020-12-11
 */
@Service
public class SysPayLogServiceImpl implements SysPayLogService
{
    @Autowired
    private SysPayLogMapper sysPayLogMapper;

    /**
     * 查询付款记录
     * 
     * @param id 付款记录ID
     * @return 付款记录
     */
    @Override
    public SysPayLog selectSysPayLogById(Long id)
    {
        return sysPayLogMapper.selectSysPayLogById(id);
    }

    /**
     * 查询付款记录列表
     * 
     * @param sysPayLog 付款记录
     * @return 付款记录
     */
    @Override
    public List<SysPayLog> selectSysPayLogList(SysPayLog sysPayLog)
    {
        return sysPayLogMapper.selectSysPayLogList(sysPayLog);
    }

    /**
     * 新增付款记录
     * 
     * @param sysPayLog 付款记录
     * @return 结果
     */
    @Override
    public int insertSysPayLog(SysPayLog sysPayLog)
    {
        return sysPayLogMapper.insertSysPayLog(sysPayLog);
    }

    /**
     * 修改付款记录
     * 
     * @param sysPayLog 付款记录
     * @return 结果
     */
    @Override
    public int updateSysPayLog(SysPayLog sysPayLog)
    {
        return sysPayLogMapper.updateSysPayLog(sysPayLog);
    }

    /**
     * 批量删除付款记录
     * 
     * @param ids 需要删除的付款记录ID
     * @return 结果
     */
    @Override
    public int deleteSysPayLogByIds(Long[] ids)
    {
        return sysPayLogMapper.deleteSysPayLogByIds(ids);
    }

    /**
     * 删除付款记录信息
     * 
     * @param id 付款记录ID
     * @return 结果
     */
    @Override
    public int deleteSysPayLogById(Long id)
    {
        return sysPayLogMapper.deleteSysPayLogById(id);
    }
}
