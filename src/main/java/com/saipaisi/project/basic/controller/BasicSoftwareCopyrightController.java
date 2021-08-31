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
import com.saipaisi.project.basic.domain.BasicSoftwareCopyright;
import com.saipaisi.project.basic.service.BasicSoftwareCopyrightService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 软件著作权Controller
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@RestController
@RequestMapping("/basic/soft/copyright")
public class BasicSoftwareCopyrightController extends BaseController
{
    @Autowired
    private BasicSoftwareCopyrightService basicSoftwareCopyrightService;

    /**
     * 查询软件著作权列表
     */
    @PreAuthorize("@ss.hasPermi('basic:copyright:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasicSoftwareCopyright basicSoftwareCopyright)
    {
        startPage();
        List<BasicSoftwareCopyright> list = basicSoftwareCopyrightService.selectBasicSoftwareCopyrightList(basicSoftwareCopyright);
        return getDataTable(list);
    }

    /**
     * 导出软件著作权列表
     */
    @PreAuthorize("@ss.hasPermi('basic:copyright:export')")
    @Log(title = "软件著作权", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicSoftwareCopyright basicSoftwareCopyright)
    {
        List<BasicSoftwareCopyright> list = basicSoftwareCopyrightService.selectBasicSoftwareCopyrightList(basicSoftwareCopyright);
        ExcelUtil<BasicSoftwareCopyright> util = new ExcelUtil<BasicSoftwareCopyright>(BasicSoftwareCopyright.class);
        return util.exportExcel(list, "copyright");
    }

    /**
     * 获取软件著作权详细信息
     */
    @PreAuthorize("@ss.hasPermi('basic:copyright:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(basicSoftwareCopyrightService.selectBasicSoftwareCopyrightById(id));
    }

    /**
     * 新增软件著作权
     */
    @PreAuthorize("@ss.hasPermi('basic:copyright:add')")
    @Log(title = "软件著作权", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicSoftwareCopyright basicSoftwareCopyright)
    {
        return toAjax(basicSoftwareCopyrightService.insertBasicSoftwareCopyright(basicSoftwareCopyright));
    }

    /**
     * 修改软件著作权
     */
    @PreAuthorize("@ss.hasPermi('basic:copyright:edit')")
    @Log(title = "软件著作权", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicSoftwareCopyright basicSoftwareCopyright)
    {
        return toAjax(basicSoftwareCopyrightService.updateBasicSoftwareCopyright(basicSoftwareCopyright));
    }

    /**
     * 删除软件著作权
     */
    @PreAuthorize("@ss.hasPermi('basic:copyright:remove')")
    @Log(title = "软件著作权", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(basicSoftwareCopyrightService.deleteBasicSoftwareCopyrightByIds(ids));
    }
}
