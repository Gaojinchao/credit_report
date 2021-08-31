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
import com.saipaisi.project.basic.domain.BasicShareholder;
import com.saipaisi.project.basic.service.BasicShareholderService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 股东组成Controller
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@RestController
@RequestMapping("/basic/shareholder")
public class BasicShareholderController extends BaseController
{
    @Autowired
    private BasicShareholderService basicShareholderService;

    /**
     * 查询股东组成列表
     */
    @PreAuthorize("@ss.hasPermi('basic:shareholder:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasicShareholder basicShareholder)
    {
        startPage();
        List<BasicShareholder> list = basicShareholderService.selectBasicShareholderList(basicShareholder);
        return getDataTable(list);
    }

    /**
     * 导出股东组成列表
     */
    @PreAuthorize("@ss.hasPermi('basic:shareholder:export')")
    @Log(title = "股东组成", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicShareholder basicShareholder)
    {
        List<BasicShareholder> list = basicShareholderService.selectBasicShareholderList(basicShareholder);
        ExcelUtil<BasicShareholder> util = new ExcelUtil<BasicShareholder>(BasicShareholder.class);
        return util.exportExcel(list, "shareholder");
    }

    /**
     * 获取股东组成详细信息
     */
    @PreAuthorize("@ss.hasPermi('basic:shareholder:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(basicShareholderService.selectBasicShareholderById(id));
    }

    /**
     * 新增股东组成
     */
    @PreAuthorize("@ss.hasPermi('basic:shareholder:add')")
    @Log(title = "股东组成", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicShareholder basicShareholder)
    {
        return toAjax(basicShareholderService.insertBasicShareholder(basicShareholder));
    }

    /**
     * 修改股东组成
     */
    @PreAuthorize("@ss.hasPermi('basic:shareholder:edit')")
    @Log(title = "股东组成", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicShareholder basicShareholder)
    {
        return toAjax(basicShareholderService.updateBasicShareholder(basicShareholder));
    }

    /**
     * 删除股东组成
     */
    @PreAuthorize("@ss.hasPermi('basic:shareholder:remove')")
    @Log(title = "股东组成", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(basicShareholderService.deleteBasicShareholderByIds(ids));
    }
}
