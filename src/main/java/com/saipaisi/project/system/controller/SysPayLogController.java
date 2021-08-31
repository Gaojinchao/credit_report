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
import com.saipaisi.project.system.domain.SysPayLog;
import com.saipaisi.project.system.service.SysPayLogService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 付款记录Controller
 * 
 * @author alfred.zong
 * @date 2020-12-11
 */
@RestController
@RequestMapping("/system/pay/log")
public class SysPayLogController extends BaseController
{
    @Autowired
    private SysPayLogService sysPayLogService;

    /**
     * 查询付款记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:pay/log:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysPayLog sysPayLog)
    {
        startPage();
        List<SysPayLog> list = sysPayLogService.selectSysPayLogList(sysPayLog);
        return getDataTable(list);
    }

    /**
     * 导出付款记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:pay/log:export')")
    @Log(title = "付款记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysPayLog sysPayLog)
    {
        List<SysPayLog> list = sysPayLogService.selectSysPayLogList(sysPayLog);
        ExcelUtil<SysPayLog> util = new ExcelUtil<SysPayLog>(SysPayLog.class);
        return util.exportExcel(list, "pay/log");
    }

    /**
     * 获取付款记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:pay/log:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysPayLogService.selectSysPayLogById(id));
    }

    /**
     * 新增付款记录
     */
    @PreAuthorize("@ss.hasPermi('system:pay/log:add')")
    @Log(title = "付款记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysPayLog sysPayLog)
    {
        return toAjax(sysPayLogService.insertSysPayLog(sysPayLog));
    }

    /**
     * 修改付款记录
     */
    @PreAuthorize("@ss.hasPermi('system:pay/log:edit')")
    @Log(title = "付款记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysPayLog sysPayLog)
    {
        return toAjax(sysPayLogService.updateSysPayLog(sysPayLog));
    }

    /**
     * 删除付款记录
     */
    @PreAuthorize("@ss.hasPermi('system:pay/log:remove')")
    @Log(title = "付款记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysPayLogService.deleteSysPayLogByIds(ids));
    }
}
