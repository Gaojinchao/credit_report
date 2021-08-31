package com.saipaisi.project.basic.mapper;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicBranchOrg;

/**
 * 分支机构Mapper接口
 * 
 * @author alfred.zong
 * @date 2021-05-25
 */
public interface BasicBranchOrgMapper 
{
    /**
     * 查询分支机构
     * 
     * @param id 分支机构ID
     * @return 分支机构
     */
    public BasicBranchOrg selectBasicBranchOrgById(Long id);

    /**
     * 查询分支机构列表
     * 
     * @param basicBranchOrg 分支机构
     * @return 分支机构集合
     */
    public List<BasicBranchOrg> selectBasicBranchOrgList(BasicBranchOrg basicBranchOrg);

    /**
     * 新增分支机构
     * 
     * @param basicBranchOrg 分支机构
     * @return 结果
     */
    public int insertBasicBranchOrg(BasicBranchOrg basicBranchOrg);

    /**
     * 修改分支机构
     * 
     * @param basicBranchOrg 分支机构
     * @return 结果
     */
    public int updateBasicBranchOrg(BasicBranchOrg basicBranchOrg);

    /**
     * 删除分支机构
     * 
     * @param id 分支机构ID
     * @return 结果
     */
    public int deleteBasicBranchOrgById(Long id);

    /**
     * 批量删除分支机构
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasicBranchOrgByIds(Long[] ids);
}
