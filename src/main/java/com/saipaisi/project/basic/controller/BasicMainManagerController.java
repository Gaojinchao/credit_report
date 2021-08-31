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
import com.saipaisi.project.basic.domain.BasicMainManager;
import com.saipaisi.project.basic.service.BasicMainManagerService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 主要管理者信息Controller
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@RestController
@RequestMapping("/basic/main/manager")
public class BasicMainManagerController extends BaseController
{
    @Autowired
    private BasicMainManagerService basicMainManagerService;

    /**
     * 查询主要管理者信息列表
     */
    @PreAuthorize("@ss.hasPermi('basic:manager:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasicMainManager basicMainManager)
    {
        startPage();
        List<BasicMainManager> list = basicMainManagerService.selectBasicMainManagerList(basicMainManager);
        return getDataTable(list);
    }

    /**
     * 导出主要管理者信息列表
     */
    @PreAuthorize("@ss.hasPermi('basic:manager:export')")
    @Log(title = "主要管理者信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicMainManager basicMainManager)
    {
        List<BasicMainManager> list = basicMainManagerService.selectBasicMainManagerList(basicMainManager);
        ExcelUtil<BasicMainManager> util = new ExcelUtil<BasicMainManager>(BasicMainManager.class);
        return util.exportExcel(list, "manager");
    }

    /**
     * 获取主要管理者信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('basic:manager:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(basicMainManagerService.selectBasicMainManagerById(id));
    }

    /**
     * 新增主要管理者信息
     */
    @PreAuthorize("@ss.hasPermi('basic:manager:add')")
    @Log(title = "主要管理者信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicMainManager basicMainManager)
    {
        return toAjax(basicMainManagerService.insertBasicMainManager(basicMainManager));
    }

    /**
     * 修改主要管理者信息
     */
    @PreAuthorize("@ss.hasPermi('basic:manager:edit')")
    @Log(title = "主要管理者信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicMainManager basicMainManager)
    {
        return toAjax(basicMainManagerService.updateBasicMainManager(basicMainManager));
    }

    /**
     * 删除主要管理者信息
     */
    @PreAuthorize("@ss.hasPermi('basic:manager:remove')")
    @Log(title = "主要管理者信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(basicMainManagerService.deleteBasicMainManagerByIds(ids));
    }
}
