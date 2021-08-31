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
import com.saipaisi.project.basic.domain.BasicEmployeeCondition;
import com.saipaisi.project.basic.service.BasicEmployeeConditionService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 从业人员情况Controller
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@RestController
@RequestMapping("/basic/condition")
public class BasicEmployeeConditionController extends BaseController
{
    @Autowired
    private BasicEmployeeConditionService basicEmployeeConditionService;

    /**
     * 查询从业人员情况列表
     */
    @PreAuthorize("@ss.hasPermi('basic:condition:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasicEmployeeCondition basicEmployeeCondition)
    {
        startPage();
        List<BasicEmployeeCondition> list = basicEmployeeConditionService.selectBasicEmployeeConditionList(basicEmployeeCondition);
        return getDataTable(list);
    }

    /**
     * 导出从业人员情况列表
     */
    @PreAuthorize("@ss.hasPermi('basic:condition:export')")
    @Log(title = "从业人员情况", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicEmployeeCondition basicEmployeeCondition)
    {
        List<BasicEmployeeCondition> list = basicEmployeeConditionService.selectBasicEmployeeConditionList(basicEmployeeCondition);
        ExcelUtil<BasicEmployeeCondition> util = new ExcelUtil<BasicEmployeeCondition>(BasicEmployeeCondition.class);
        return util.exportExcel(list, "condition");
    }

    /**
     * 获取从业人员情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('basic:condition:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(basicEmployeeConditionService.selectBasicEmployeeConditionById(id));
    }

    /**
     * 新增从业人员情况
     */
    @PreAuthorize("@ss.hasPermi('basic:condition:add')")
    @Log(title = "从业人员情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicEmployeeCondition basicEmployeeCondition)
    {
        return toAjax(basicEmployeeConditionService.insertBasicEmployeeCondition(basicEmployeeCondition));
    }

    /**
     * 修改从业人员情况
     */
    @PreAuthorize("@ss.hasPermi('basic:condition:edit')")
    @Log(title = "从业人员情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicEmployeeCondition basicEmployeeCondition)
    {
        return toAjax(basicEmployeeConditionService.updateBasicEmployeeCondition(basicEmployeeCondition));
    }

    /**
     * 删除从业人员情况
     */
    @PreAuthorize("@ss.hasPermi('basic:condition:remove')")
    @Log(title = "从业人员情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(basicEmployeeConditionService.deleteBasicEmployeeConditionByIds(ids));
    }
}
