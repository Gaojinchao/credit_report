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
import com.saipaisi.project.basic.domain.BasicEmbranchmentOrg;
import com.saipaisi.project.basic.service.BasicEmbranchmentOrgService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 分支机构Controller
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@RestController
@RequestMapping("/basic/org")
public class BasicEmbranchmentOrgController extends BaseController
{
    @Autowired
    private BasicEmbranchmentOrgService basicEmbranchmentOrgService;

    /**
     * 查询分支机构列表
     */
    @PreAuthorize("@ss.hasPermi('basic:org:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasicEmbranchmentOrg basicEmbranchmentOrg)
    {
        startPage();
        List<BasicEmbranchmentOrg> list = basicEmbranchmentOrgService.selectBasicEmbranchmentOrgList(basicEmbranchmentOrg);
        return getDataTable(list);
    }

    /**
     * 导出分支机构列表
     */
    @PreAuthorize("@ss.hasPermi('basic:org:export')")
    @Log(title = "分支机构", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicEmbranchmentOrg basicEmbranchmentOrg)
    {
        List<BasicEmbranchmentOrg> list = basicEmbranchmentOrgService.selectBasicEmbranchmentOrgList(basicEmbranchmentOrg);
        ExcelUtil<BasicEmbranchmentOrg> util = new ExcelUtil<BasicEmbranchmentOrg>(BasicEmbranchmentOrg.class);
        return util.exportExcel(list, "org");
    }

    /**
     * 获取分支机构详细信息
     */
    @PreAuthorize("@ss.hasPermi('basic:org:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(basicEmbranchmentOrgService.selectBasicEmbranchmentOrgById(id));
    }

    /**
     * 新增分支机构
     */
    @PreAuthorize("@ss.hasPermi('basic:org:add')")
    @Log(title = "分支机构", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicEmbranchmentOrg basicEmbranchmentOrg)
    {
        return toAjax(basicEmbranchmentOrgService.insertBasicEmbranchmentOrg(basicEmbranchmentOrg));
    }

    /**
     * 修改分支机构
     */
    @PreAuthorize("@ss.hasPermi('basic:org:edit')")
    @Log(title = "分支机构", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicEmbranchmentOrg basicEmbranchmentOrg)
    {
        return toAjax(basicEmbranchmentOrgService.updateBasicEmbranchmentOrg(basicEmbranchmentOrg));
    }

    /**
     * 删除分支机构
     */
    @PreAuthorize("@ss.hasPermi('basic:org:remove')")
    @Log(title = "分支机构", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(basicEmbranchmentOrgService.deleteBasicEmbranchmentOrgByIds(ids));
    }
}
