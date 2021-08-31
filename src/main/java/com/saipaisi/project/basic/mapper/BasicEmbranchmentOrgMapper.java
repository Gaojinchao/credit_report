package com.saipaisi.project.basic.mapper;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicEmbranchmentOrg;

/**
 * 分支机构Mapper接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicEmbranchmentOrgMapper 
{
    /**
     * 查询分支机构
     * 
     * @param id 分支机构ID
     * @return 分支机构
     */
    public BasicEmbranchmentOrg selectBasicEmbranchmentOrgById(Long id);

    /**
     * 查询分支机构列表
     * 
     * @param basicEmbranchmentOrg 分支机构
     * @return 分支机构集合
     */
    public List<BasicEmbranchmentOrg> selectBasicEmbranchmentOrgList(BasicEmbranchmentOrg basicEmbranchmentOrg);

    /**
     * 新增分支机构
     * 
     * @param basicEmbranchmentOrg 分支机构
     * @return 结果
     */
    public int insertBasicEmbranchmentOrg(BasicEmbranchmentOrg basicEmbranchmentOrg);

    /**
     * 修改分支机构
     * 
     * @param basicEmbranchmentOrg 分支机构
     * @return 结果
     */
    public int updateBasicEmbranchmentOrg(BasicEmbranchmentOrg basicEmbranchmentOrg);

    /**
     * 删除分支机构
     * 
     * @param id 分支机构ID
     * @return 结果
     */
    public int deleteBasicEmbranchmentOrgById(Long id);

    /**
     * 批量删除分支机构
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasicEmbranchmentOrgByIds(Long[] ids);


}
