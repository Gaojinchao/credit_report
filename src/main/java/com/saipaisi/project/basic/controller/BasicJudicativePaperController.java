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
import com.saipaisi.project.basic.domain.BasicJudicativePaper;
import com.saipaisi.project.basic.service.BasicJudicativePaperService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 裁判文书Controller
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@RestController
@RequestMapping("/basic/paper")
public class BasicJudicativePaperController extends BaseController
{
    @Autowired
    private BasicJudicativePaperService basicJudicativePaperService;

    /**
     * 查询裁判文书列表
     */
    @PreAuthorize("@ss.hasPermi('basic:paper:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasicJudicativePaper basicJudicativePaper)
    {
        startPage();
        List<BasicJudicativePaper> list = basicJudicativePaperService.selectBasicJudicativePaperList(basicJudicativePaper);
        return getDataTable(list);
    }

    /**
     * 导出裁判文书列表
     */
    @PreAuthorize("@ss.hasPermi('basic:paper:export')")
    @Log(title = "裁判文书", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicJudicativePaper basicJudicativePaper)
    {
        List<BasicJudicativePaper> list = basicJudicativePaperService.selectBasicJudicativePaperList(basicJudicativePaper);
        ExcelUtil<BasicJudicativePaper> util = new ExcelUtil<BasicJudicativePaper>(BasicJudicativePaper.class);
        return util.exportExcel(list, "paper");
    }

    /**
     * 获取裁判文书详细信息
     */
    @PreAuthorize("@ss.hasPermi('basic:paper:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(basicJudicativePaperService.selectBasicJudicativePaperById(id));
    }

    /**
     * 新增裁判文书
     */
    @PreAuthorize("@ss.hasPermi('basic:paper:add')")
    @Log(title = "裁判文书", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicJudicativePaper basicJudicativePaper)
    {
        return toAjax(basicJudicativePaperService.insertBasicJudicativePaper(basicJudicativePaper));
    }

    /**
     * 修改裁判文书
     */
    @PreAuthorize("@ss.hasPermi('basic:paper:edit')")
    @Log(title = "裁判文书", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicJudicativePaper basicJudicativePaper)
    {
        return toAjax(basicJudicativePaperService.updateBasicJudicativePaper(basicJudicativePaper));
    }

    /**
     * 删除裁判文书
     */
    @PreAuthorize("@ss.hasPermi('basic:paper:remove')")
    @Log(title = "裁判文书", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(basicJudicativePaperService.deleteBasicJudicativePaperByIds(ids));
    }
}
