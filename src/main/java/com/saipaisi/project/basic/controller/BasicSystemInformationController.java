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
import com.saipaisi.project.basic.domain.BasicSystemInformation;
import com.saipaisi.project.basic.service.BasicSystemInformationService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 体系信息Controller
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@RestController
@RequestMapping("/basic/information")
public class BasicSystemInformationController extends BaseController
{
    @Autowired
    private BasicSystemInformationService basicSystemInformationService;

    /**
     * 查询体系信息列表
     */
    @PreAuthorize("@ss.hasPermi('basic:information:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasicSystemInformation basicSystemInformation)
    {
        startPage();
        List<BasicSystemInformation> list = basicSystemInformationService.selectBasicSystemInformationList(basicSystemInformation);
        return getDataTable(list);
    }

    /**
     * 导出体系信息列表
     */
    @PreAuthorize("@ss.hasPermi('basic:information:export')")
    @Log(title = "体系信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicSystemInformation basicSystemInformation)
    {
        List<BasicSystemInformation> list = basicSystemInformationService.selectBasicSystemInformationList(basicSystemInformation);
        ExcelUtil<BasicSystemInformation> util = new ExcelUtil<BasicSystemInformation>(BasicSystemInformation.class);
        return util.exportExcel(list, "information");
    }

    /**
     * 获取体系信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('basic:information:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(basicSystemInformationService.selectBasicSystemInformationById(id));
    }

    /**
     * 新增体系信息
     */
    @PreAuthorize("@ss.hasPermi('basic:information:add')")
    @Log(title = "体系信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicSystemInformation basicSystemInformation)
    {
        return toAjax(basicSystemInformationService.insertBasicSystemInformation(basicSystemInformation));
    }

    /**
     * 修改体系信息
     */
    @PreAuthorize("@ss.hasPermi('basic:information:edit')")
    @Log(title = "体系信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicSystemInformation basicSystemInformation)
    {
        return toAjax(basicSystemInformationService.updateBasicSystemInformation(basicSystemInformation));
    }

    /**
     * 删除体系信息
     */
    @PreAuthorize("@ss.hasPermi('basic:information:remove')")
    @Log(title = "体系信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(basicSystemInformationService.deleteBasicSystemInformationByIds(ids));
    }
}
