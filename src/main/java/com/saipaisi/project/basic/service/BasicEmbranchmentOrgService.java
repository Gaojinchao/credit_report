package com.saipaisi.project.basic.service;

import java.util.List;
import com.saipaisi.project.basic.domain.BasicEmbranchmentOrg;

/**
 * 对外投资Service接口
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
public interface BasicEmbranchmentOrgService
{
    /**
     * 查询对外投资
     * 
     * @param id 分支机构ID
     * @return 分支机构
     */
    public BasicEmbranchmentOrg selectBasicEmbranchmentOrgById(Long id);

    /**
     * 查询对外投资列表
     * 
     * @param basicEmbranchmentOrg 分支机构
     * @return 分支机构集合
     */
    public List<BasicEmbranchmentOrg> selectBasicEmbranchmentOrgList(BasicEmbranchmentOrg basicEmbranchmentOrg);

    /**
     * 新增对外投资
     * 
     * @param basicEmbranchmentOrg 分支机构
     * @return 结果
     */
    public int insertBasicEmbranchmentOrg(BasicEmbranchmentOrg basicEmbranchmentOrg);

    /**
     * 修改对外投资
     * 
     * @param basicEmbranchmentOrg 分支机构
     * @return 结果
     */
    public int updateBasicEmbranchmentOrg(BasicEmbranchmentOrg basicEmbranchmentOrg);

    /**
     * 批量删除对外投资
     * 
     * @param ids 需要删除的分支机构ID
     * @return 结果
     */
    public int deleteBasicEmbranchmentOrgByIds(Long[] ids);

    /**
     * 删除对外投资信息
     * 
     * @param id 分支机构ID
     * @return 结果
     */
    public int deleteBasicEmbranchmentOrgById(Long id);

    /**
     * 获取对外投资
     * @return
     */
    public int getInvestmentByNameInfo(String keyword,String ordersn);
}
