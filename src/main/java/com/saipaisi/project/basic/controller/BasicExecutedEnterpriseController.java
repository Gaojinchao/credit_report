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
import com.saipaisi.project.basic.domain.BasicExecutedEnterprise;
import com.saipaisi.project.basic.service.BasicExecutedEnterpriseService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 被执行企业Controller
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@RestController
@RequestMapping("/basic/enterprise")
public class BasicExecutedEnterpriseController extends BaseController
{
    @Autowired
    private BasicExecutedEnterpriseService basicExecutedEnterpriseService;

    /**
     * 查询被执行企业列表
     */
    @PreAuthorize("@ss.hasPermi('basic:enterprise:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasicExecutedEnterprise basicExecutedEnterprise)
    {
        startPage();
        List<BasicExecutedEnterprise> list = basicExecutedEnterpriseService.selectBasicExecutedEnterpriseList(basicExecutedEnterprise);
        return getDataTable(list);
    }

    /**
     * 导出被执行企业列表
     */
    @PreAuthorize("@ss.hasPermi('basic:enterprise:export')")
    @Log(title = "被执行企业", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicExecutedEnterprise basicExecutedEnterprise)
    {
        List<BasicExecutedEnterprise> list = basicExecutedEnterpriseService.selectBasicExecutedEnterpriseList(basicExecutedEnterprise);
        ExcelUtil<BasicExecutedEnterprise> util = new ExcelUtil<BasicExecutedEnterprise>(BasicExecutedEnterprise.class);
        return util.exportExcel(list, "enterprise");
    }

    /**
     * 获取被执行企业详细信息
     */
    @PreAuthorize("@ss.hasPermi('basic:enterprise:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(basicExecutedEnterpriseService.selectBasicExecutedEnterpriseById(id));
    }

    /**
     * 新增被执行企业
     */
    @PreAuthorize("@ss.hasPermi('basic:enterprise:add')")
    @Log(title = "被执行企业", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicExecutedEnterprise basicExecutedEnterprise)
    {
        return toAjax(basicExecutedEnterpriseService.insertBasicExecutedEnterprise(basicExecutedEnterprise));
    }

    /**
     * 修改被执行企业
     */
    @PreAuthorize("@ss.hasPermi('basic:enterprise:edit')")
    @Log(title = "被执行企业", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicExecutedEnterprise basicExecutedEnterprise)
    {
        return toAjax(basicExecutedEnterpriseService.updateBasicExecutedEnterprise(basicExecutedEnterprise));
    }

    /**
     * 删除被执行企业
     */
    @PreAuthorize("@ss.hasPermi('basic:enterprise:remove')")
    @Log(title = "被执行企业", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(basicExecutedEnterpriseService.deleteBasicExecutedEnterpriseByIds(ids));
    }
}
