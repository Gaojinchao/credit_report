package com.saipaisi.project.company.service;

import java.util.List;
import com.saipaisi.project.company.domain.Company;

/**
 * 公司Service接口
 * 
 * @author alfred.zong
 * @date 2021-05-13
 */
public interface CompanyService
{
    /**
     * 查询公司
     * 
     * @param id 公司ID
     * @return 公司
     */
    public Company selectCompanyById(Long id);

    /**
     * 查询公司列表
     * 
     * @param company 公司
     * @return 公司集合
     */
    public List<Company> selectCompanyList(Company company);

    /**
     * 新增公司
     * 
     * @param company 公司
     * @return 结果
     */
    public int insertCompany(Company company);

    /**
     * 修改公司
     * 
     * @param company 公司
     * @return 结果
     */
    public int updateCompany(Company company);

    /**
     * 批量删除公司
     * 
     * @param ids 需要删除的公司ID
     * @return 结果
     */
    public int deleteCompanyByIds(Long[] ids);

    /**
     * 删除公司信息
     * 
     * @param id 公司ID
     * @return 结果
     */
    public int deleteCompanyById(Long id);
}
