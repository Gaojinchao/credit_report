package com.saipaisi.project.system.controller;

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
import com.saipaisi.project.system.domain.BehavioralIndicator;
import com.saipaisi.project.system.service.BehavioralIndicatorService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 行业指标Controller
 * 
 * @author alfredzong
 * @date 2021-05-25
 */
@RestController
@RequestMapping("/system/indicator")
public class BehavioralIndicatorController extends BaseController
{
    @Autowired
    private BehavioralIndicatorService behavioralIndicatorService;

    /**
     * 查询行业指标列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BehavioralIndicator behavioralIndicator)
    {
        startPage();
        List<BehavioralIndicator> list = behavioralIndicatorService.selectBehavioralIndicatorList(behavioralIndicator);
        return getDataTable(list);
    }

    /**
     * 导出行业指标列表
     */
    @Log(title = "行业指标", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BehavioralIndicator behavioralIndicator)
    {
        List<BehavioralIndicator> list = behavioralIndicatorService.selectBehavioralIndicatorList(behavioralIndicator);
        ExcelUtil<BehavioralIndicator> util = new ExcelUtil<BehavioralIndicator>(BehavioralIndicator.class);
        return util.exportExcel(list, "indicator");
    }

    /**
     * 获取行业指标详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(behavioralIndicatorService.selectBehavioralIndicatorById(id));
    }

    /**
     * 新增行业指标
     */
    @Log(title = "行业指标", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BehavioralIndicator behavioralIndicator)
    {
        return toAjax(behavioralIndicatorService.insertBehavioralIndicator(behavioralIndicator));
    }

    /**
     * 修改行业指标
     */
    @Log(title = "行业指标", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BehavioralIndicator behavioralIndicator)
    {
        return toAjax(behavioralIndicatorService.updateBehavioralIndicator(behavioralIndicator));
    }

    /**
     * 删除行业指标
     */
    @Log(title = "行业指标", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(behavioralIndicatorService.deleteBehavioralIndicatorByIds(ids));
    }
}
