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
import com.saipaisi.project.basic.domain.BasicCopyright;
import com.saipaisi.project.basic.service.BasicCopyrightService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 著作权Controller
 * 
 * @author alfredzong
 * @date 2021-05-26
 */
@RestController
@RequestMapping("/basic/copyright")
public class BasicCopyrightController extends BaseController
{
    @Autowired
    private BasicCopyrightService basicCopyrightService;

    /**
     * 查询著作权列表
     */
    @PreAuthorize("@ss.hasPermi('basic:copyright:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasicCopyright basicCopyright)
    {
        startPage();
        List<BasicCopyright> list = basicCopyrightService.selectBasicCopyrightList(basicCopyright);
        return getDataTable(list);
    }

    /**
     * 导出著作权列表
     */
    @PreAuthorize("@ss.hasPermi('basic:copyright:export')")
    @Log(title = "著作权", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicCopyright basicCopyright)
    {
        List<BasicCopyright> list = basicCopyrightService.selectBasicCopyrightList(basicCopyright);
        ExcelUtil<BasicCopyright> util = new ExcelUtil<BasicCopyright>(BasicCopyright.class);
        return util.exportExcel(list, "copyright");
    }

    /**
     * 获取著作权详细信息
     */
    @PreAuthorize("@ss.hasPermi('basic:copyright:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(basicCopyrightService.selectBasicCopyrightById(id));
    }

    /**
     * 新增著作权
     */
    @PreAuthorize("@ss.hasPermi('basic:copyright:add')")
    @Log(title = "著作权", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicCopyright basicCopyright)
    {
        return toAjax(basicCopyrightService.insertBasicCopyright(basicCopyright));
    }

    /**
     * 修改著作权
     */
    @PreAuthorize("@ss.hasPermi('basic:copyright:edit')")
    @Log(title = "著作权", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicCopyright basicCopyright)
    {
        return toAjax(basicCopyrightService.updateBasicCopyright(basicCopyright));
    }

    /**
     * 删除著作权
     */
    @PreAuthorize("@ss.hasPermi('basic:copyright:remove')")
    @Log(title = "著作权", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(basicCopyrightService.deleteBasicCopyrightByIds(ids));
    }
}
