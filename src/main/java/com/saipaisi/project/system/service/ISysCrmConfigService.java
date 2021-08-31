package com.saipaisi.project.system.service;

import java.util.List;
import com.saipaisi.project.system.domain.SysCrmConfig;

/**
 * crm系统配置Service接口
 * 
 * @author saipaisi
 * @date 2020-08-19
 */
public interface ISysCrmConfigService
{
    /**
     * 查询crm系统配置
     * 
     * @param id crm系统配置ID
     * @return crm系统配置
     */
    public SysCrmConfig selectSysCrmConfigById(Long id);

    /**
     * 查询crm系统配置列表
     * 
     * @param sysCrmConfig crm系统配置
     * @return crm系统配置集合
     */
    public List<SysCrmConfig> selectSysCrmConfigList(SysCrmConfig sysCrmConfig);

    /**
     * 新增crm系统配置
     * 
     * @param sysCrmConfig crm系统配置
     * @return 结果
     */
    public int insertSysCrmConfig(SysCrmConfig sysCrmConfig);

    /**
     * 修改crm系统配置
     * 
     * @param sysCrmConfig crm系统配置
     * @return 结果
     */
    public int updateSysCrmConfig(SysCrmConfig sysCrmConfig);

    /**
     * 批量删除crm系统配置
     * 
     * @param ids 需要删除的crm系统配置ID
     * @return 结果
     */
    public int deleteSysCrmConfigByIds(Long[] ids);

    /**
     * 删除crm系统配置信息
     * 
     * @param id crm系统配置ID
     * @return 结果
     */
    public int deleteSysCrmConfigById(Long id);

    /**
     * 登陆时间限制
     */
    boolean loginTimeLimit();
}
