package com.saipaisi.project.basic.mapper;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicPromiseEnterprise;

/**
 * 失信企业Mapper接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicPromiseEnterpriseMapper 
{
    /**
     * 查询失信企业
     * 
     * @param id 失信企业ID
     * @return 失信企业
     */
    public BasicPromiseEnterprise selectBasicPromiseEnterpriseById(Long id);

    /**
     * 查询失信企业列表
     * 
     * @param basicPromiseEnterprise 失信企业
     * @return 失信企业集合
     */
    public List<BasicPromiseEnterprise> selectBasicPromiseEnterpriseList(BasicPromiseEnterprise basicPromiseEnterprise);

    /**
     * 新增失信企业
     * 
     * @param basicPromiseEnterprise 失信企业
     * @return 结果
     */
    public int insertBasicPromiseEnterprise(BasicPromiseEnterprise basicPromiseEnterprise);

    /**
     * 修改失信企业
     * 
     * @param basicPromiseEnterprise 失信企业
     * @return 结果
     */
    public int updateBasicPromiseEnterprise(BasicPromiseEnterprise basicPromiseEnterprise);

    /**
     * 删除失信企业
     * 
     * @param id 失信企业ID
     * @return 结果
     */
    public int deleteBasicPromiseEnterpriseById(Long id);

    /**
     * 批量删除失信企业
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasicPromiseEnterpriseByIds(Long[] ids);
}
