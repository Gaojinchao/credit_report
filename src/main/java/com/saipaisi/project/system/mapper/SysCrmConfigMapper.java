package com.saipaisi.project.system.mapper;

import java.util.List;
import com.saipaisi.project.system.domain.SysCrmConfig;
import org.apache.ibatis.annotations.Param;

/**
 * crm系统配置Mapper接口
 * 
 * @author saipaisi
 * @date 2020-08-19
 */
public interface SysCrmConfigMapper 
{
    /**
     * 查询crm系统配置
     * 
     * @param id crm系统配置ID
     * @return crm系统配置
     */
    public SysCrmConfig selectSysCrmConfigById(Long id);

    /***
     * 查询系统配置
     * @return
     */
    public SysCrmConfig selectSysCrmConfigBy(@Param("companyId") Long companyId,@Param("name") String name);

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
     * 删除crm系统配置
     * 
     * @param id crm系统配置ID
     * @return 结果
     */
    public int deleteSysCrmConfigById(Long id);

    /**
     * 批量删除crm系统配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysCrmConfigByIds(Long[] ids);
}
