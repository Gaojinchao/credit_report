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
import com.saipaisi.project.basic.domain.BasicMainRunManager;
import com.saipaisi.project.basic.service.BasicMainRunManagerService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 主要经营者管理者信息Controller
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@RestController
@RequestMapping("/basic/manager")
public class BasicMainRunManagerController extends BaseController
{
    @Autowired
    private BasicMainRunManagerService basicMainRunManagerService;

    /**
     * 查询主要经营者管理者信息列表
     */
    @PreAuthorize("@ss.hasPermi('basic:manager:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasicMainRunManager basicMainRunManager)
    {
        startPage();
        List<BasicMainRunManager> list = basicMainRunManagerService.selectBasicMainRunManagerList(basicMainRunManager);
        return getDataTable(list);
    }

    /**
     * 导出主要经营者管理者信息列表
     */
    @PreAuthorize("@ss.hasPermi('basic:manager:export')")
    @Log(title = "主要经营者管理者信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicMainRunManager basicMainRunManager)
    {
        List<BasicMainRunManager> list = basicMainRunManagerService.selectBasicMainRunManagerList(basicMainRunManager);
        ExcelUtil<BasicMainRunManager> util = new ExcelUtil<BasicMainRunManager>(BasicMainRunManager.class);
        return util.exportExcel(list, "manager");
    }

    /**
     * 获取主要经营者管理者信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('basic:manager:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(basicMainRunManagerService.selectBasicMainRunManagerById(id));
    }

    /**
     * 新增主要经营者管理者信息
     */
    @PreAuthorize("@ss.hasPermi('basic:manager:add')")
    @Log(title = "主要经营者管理者信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicMainRunManager basicMainRunManager)
    {
        return toAjax(basicMainRunManagerService.insertBasicMainRunManager(basicMainRunManager));
    }

    /**
     * 修改主要经营者管理者信息
     */
    @PreAuthorize("@ss.hasPermi('basic:manager:edit')")
    @Log(title = "主要经营者管理者信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicMainRunManager basicMainRunManager)
    {
        return toAjax(basicMainRunManagerService.updateBasicMainRunManager(basicMainRunManager));
    }

    /**
     * 删除主要经营者管理者信息
     */
    @PreAuthorize("@ss.hasPermi('basic:manager:remove')")
    @Log(title = "主要经营者管理者信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(basicMainRunManagerService.deleteBasicMainRunManagerByIds(ids));
    }
}
