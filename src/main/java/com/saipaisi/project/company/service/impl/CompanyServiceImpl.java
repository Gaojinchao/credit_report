package com.saipaisi.project.company.service.impl;

import java.util.List;
import com.saipaisi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.saipaisi.project.company.mapper.CompanyMapper;
import com.saipaisi.project.company.domain.Company;
import com.saipaisi.project.company.service.CompanyService;

/**
 * 公司Service业务层处理
 * 
 * @author alfred.zong
 * @date 2021-05-13
 */
@Service
public class CompanyServiceImpl implements CompanyService
{
    @Autowired
    private CompanyMapper companyMapper;

    /**
     * 查询公司
     * 
     * @param id 公司ID
     * @return 公司
     */
    @Override
    public Company selectCompanyById(Long id)
    {
        return companyMapper.selectCompanyById(id);
    }

    /**
     * 查询公司列表
     * 
     * @param company 公司
     * @return 公司
     */
    @Override
    public List<Company> selectCompanyList(Company company)
    {
        return companyMapper.selectCompanyList(company);
    }

    /**
     * 新增公司
     * 
     * @param company 公司
     * @return 结果
     */
    @Override
    public int insertCompany(Company company)
    {
        company.setCreateTime(DateUtils.getNowDate());
        return companyMapper.insertCompany(company);
    }

    /**
     * 修改公司
     * 
     * @param company 公司
     * @return 结果
     */
    @Override
    public int updateCompany(Company company)
    {
        company.setUpdateTime(DateUtils.getNowDate());
        return companyMapper.updateCompany(company);
    }

    /**
     * 批量删除公司
     * 
     * @param ids 需要删除的公司ID
     * @return 结果
     */
    @Override
    public int deleteCompanyByIds(Long[] ids)
    {
        return companyMapper.deleteCompanyByIds(ids);
    }

    /**
     * 删除公司信息
     * 
     * @param id 公司ID
     * @return 结果
     */
    @Override
    public int deleteCompanyById(Long id)
    {
        return companyMapper.deleteCompanyById(id);
    }
}
