package com.saipaisi.project.basic.controller;

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
import com.saipaisi.project.basic.domain.BasicPromiseEnterprise;
import com.saipaisi.project.basic.service.BasicPromiseEnterpriseService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 失信企业Controller
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@RestController
@RequestMapping("/basic/promise/enterprise")
public class BasicPromiseEnterpriseController extends BaseController
{
    @Autowired
    private BasicPromiseEnterpriseService basicPromiseEnterpriseService;

    /**
     * 查询失信企业列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BasicPromiseEnterprise basicPromiseEnterprise)
    {
        startPage();
        List<BasicPromiseEnterprise> list = basicPromiseEnterpriseService.selectBasicPromiseEnterpriseList(basicPromiseEnterprise);
        return getDataTable(list);
    }

    /**
     * 导出失信企业列表
     */
    @Log(title = "失信企业", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicPromiseEnterprise basicPromiseEnterprise)
    {
        List<BasicPromiseEnterprise> list = basicPromiseEnterpriseService.selectBasicPromiseEnterpriseList(basicPromiseEnterprise);
        ExcelUtil<BasicPromiseEnterprise> util = new ExcelUtil<BasicPromiseEnterprise>(BasicPromiseEnterprise.class);
        return util.exportExcel(list, "enterprise");
    }

    /**
     * 获取失信企业详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(basicPromiseEnterpriseService.selectBasicPromiseEnterpriseById(id));
    }

    /**
     * 新增失信企业
     */
    @Log(title = "失信企业", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicPromiseEnterprise basicPromiseEnterprise)
    {
        return toAjax(basicPromiseEnterpriseService.insertBasicPromiseEnterprise(basicPromiseEnterprise));
    }

    /**
     * 修改失信企业
     */
    @Log(title = "失信企业", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicPromiseEnterprise basicPromiseEnterprise)
    {
        return toAjax(basicPromiseEnterpriseService.updateBasicPromiseEnterprise(basicPromiseEnterprise));
    }

    /**
     * 删除失信企业
     */
    @Log(title = "失信企业", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(basicPromiseEnterpriseService.deleteBasicPromiseEnterpriseByIds(ids));
    }
}
