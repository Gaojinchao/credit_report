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
import com.saipaisi.project.basic.domain.BasicHistoryClass;
import com.saipaisi.project.basic.service.BasicHistoryClassService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 历史等级Controller
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@RestController
@RequestMapping("/basic/class")
public class BasicHistoryClassController extends BaseController
{
    @Autowired
    private BasicHistoryClassService basicHistoryClassService;

    /**
     * 查询历史等级列表
     */
    @PreAuthorize("@ss.hasPermi('basic:class:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasicHistoryClass basicHistoryClass)
    {
        startPage();
        List<BasicHistoryClass> list = basicHistoryClassService.selectBasicHistoryClassList(basicHistoryClass);
        return getDataTable(list);
    }

    /**
     * 导出历史等级列表
     */
    @PreAuthorize("@ss.hasPermi('basic:class:export')")
    @Log(title = "历史等级", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicHistoryClass basicHistoryClass)
    {
        List<BasicHistoryClass> list = basicHistoryClassService.selectBasicHistoryClassList(basicHistoryClass);
        ExcelUtil<BasicHistoryClass> util = new ExcelUtil<BasicHistoryClass>(BasicHistoryClass.class);
        return util.exportExcel(list, "class");
    }

    /**
     * 获取历史等级详细信息
     */
    @PreAuthorize("@ss.hasPermi('basic:class:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(basicHistoryClassService.selectBasicHistoryClassById(id));
    }

    /**
     * 新增历史等级
     */
    @PreAuthorize("@ss.hasPermi('basic:class:add')")
    @Log(title = "历史等级", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicHistoryClass basicHistoryClass)
    {
        return toAjax(basicHistoryClassService.insertBasicHistoryClass(basicHistoryClass));
    }

    /**
     * 修改历史等级
     */
    @PreAuthorize("@ss.hasPermi('basic:class:edit')")
    @Log(title = "历史等级", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicHistoryClass basicHistoryClass)
    {
        return toAjax(basicHistoryClassService.updateBasicHistoryClass(basicHistoryClass));
    }

    /**
     * 删除历史等级
     */
    @PreAuthorize("@ss.hasPermi('basic:class:remove')")
    @Log(title = "历史等级", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(basicHistoryClassService.deleteBasicHistoryClassByIds(ids));
    }
}
