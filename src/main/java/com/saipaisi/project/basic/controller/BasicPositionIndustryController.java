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
import com.saipaisi.project.basic.domain.BasicPositionIndustry;
import com.saipaisi.project.basic.service.BasicPositionIndustryService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 行业地位Controller
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@RestController
@RequestMapping("/basic/industry")
public class BasicPositionIndustryController extends BaseController
{
    @Autowired
    private BasicPositionIndustryService basicPositionIndustryService;

    /**
     * 查询行业地位列表
     */
    @PreAuthorize("@ss.hasPermi('basic:industry:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasicPositionIndustry basicPositionIndustry)
    {
        startPage();
        List<BasicPositionIndustry> list = basicPositionIndustryService.selectBasicPositionIndustryList(basicPositionIndustry);
        return getDataTable(list);
    }

    /**
     * 导出行业地位列表
     */
    @PreAuthorize("@ss.hasPermi('basic:industry:export')")
    @Log(title = "行业地位", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicPositionIndustry basicPositionIndustry)
    {
        List<BasicPositionIndustry> list = basicPositionIndustryService.selectBasicPositionIndustryList(basicPositionIndustry);
        ExcelUtil<BasicPositionIndustry> util = new ExcelUtil<BasicPositionIndustry>(BasicPositionIndustry.class);
        return util.exportExcel(list, "industry");
    }

    /**
     * 获取行业地位详细信息
     */
    @PreAuthorize("@ss.hasPermi('basic:industry:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(basicPositionIndustryService.selectBasicPositionIndustryById(id));
    }

    /**
     * 新增行业地位
     */
    @PreAuthorize("@ss.hasPermi('basic:industry:add')")
    @Log(title = "行业地位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicPositionIndustry basicPositionIndustry)
    {
        return toAjax(basicPositionIndustryService.insertBasicPositionIndustry(basicPositionIndustry));
    }

    /**
     * 修改行业地位
     */
    @PreAuthorize("@ss.hasPermi('basic:industry:edit')")
    @Log(title = "行业地位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicPositionIndustry basicPositionIndustry)
    {
        return toAjax(basicPositionIndustryService.updateBasicPositionIndustry(basicPositionIndustry));
    }

    /**
     * 删除行业地位
     */
    @PreAuthorize("@ss.hasPermi('basic:industry:remove')")
    @Log(title = "行业地位", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(basicPositionIndustryService.deleteBasicPositionIndustryByIds(ids));
    }
}
