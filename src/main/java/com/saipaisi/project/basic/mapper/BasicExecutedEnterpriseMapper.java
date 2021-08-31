package com.saipaisi.project.basic.mapper;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicExecutedEnterprise;

/**
 * 被执行企业Mapper接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicExecutedEnterpriseMapper 
{
    /**
     * 查询被执行企业
     * 
     * @param id 被执行企业ID
     * @return 被执行企业
     */
    public BasicExecutedEnterprise selectBasicExecutedEnterpriseById(Long id);

    /**
     * 查询被执行企业列表
     * 
     * @param basicExecutedEnterprise 被执行企业
     * @return 被执行企业集合
     */
    public List<BasicExecutedEnterprise> selectBasicExecutedEnterpriseList(BasicExecutedEnterprise basicExecutedEnterprise);

    /**
     * 新增被执行企业
     * 
     * @param basicExecutedEnterprise 被执行企业
     * @return 结果
     */
    public int insertBasicExecutedEnterprise(BasicExecutedEnterprise basicExecutedEnterprise);

    /**
     * 修改被执行企业
     * 
     * @param basicExecutedEnterprise 被执行企业
     * @return 结果
     */
    public int updateBasicExecutedEnterprise(BasicExecutedEnterprise basicExecutedEnterprise);

    /**
     * 删除被执行企业
     * 
     * @param id 被执行企业ID
     * @return 结果
     */
    public int deleteBasicExecutedEnterpriseById(Long id);

    /**
     * 批量删除被执行企业
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasicExecutedEnterpriseByIds(Long[] ids);
}
