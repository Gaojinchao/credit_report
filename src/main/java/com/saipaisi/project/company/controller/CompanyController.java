package com.saipaisi.project.company.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.saipaisi.framework.aspectj.lang.annotation.Log;
import com.saipaisi.framework.aspectj.lang.enums.BusinessType;
import com.saipaisi.project.company.domain.Company;
import com.saipaisi.project.company.service.CompanyService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 公司Controller
 * 
 * @author alfred.zong
 * @date 2021-05-13
 */
@RestController
@RequestMapping("/company/company")
public class CompanyController extends BaseController
{
    @Autowired
    private CompanyService companyService;

    /**
     * 查询公司列表
     */
    @GetMapping("/list")
    public TableDataInfo list(Company company)
    {
        startPage();
        List<Company> list = companyService.selectCompanyList(company);
        return getDataTable(list);
    }

    /**
     * 导出公司列表
     */
    @Log(title = "公司", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Company company)
    {
        List<Company> list = companyService.selectCompanyList(company);
        ExcelUtil<Company> util = new ExcelUtil<Company>(Company.class);
        return util.exportExcel(list, "company");
    }

    /**
     * 获取公司详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(companyService.selectCompanyById(id));
    }

    /**
     * 新增公司
     */
    @Log(title = "公司", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Company company)
    {
        return toAjax(companyService.insertCompany(company));
    }

    /**
     * 修改公司
     */
    @Log(title = "公司", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Company company)
    {
        return toAjax(companyService.updateCompany(company));
    }

    /**
     * 删除公司
     */
    @Log(title = "公司", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(companyService.deleteCompanyByIds(ids));
    }
}
