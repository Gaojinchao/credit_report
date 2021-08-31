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
import com.saipaisi.project.system.domain.SysCrmConfig;
import com.saipaisi.project.system.service.ISysCrmConfigService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * crm系统配置Controller
 * 
 * @author saipaisi
 * @date 2020-08-19
 */
@RestController
@RequestMapping("/system/crmConfig")
public class SysCrmConfigController extends BaseController
{
    @Autowired
    private ISysCrmConfigService sysCrmConfigService;

    /**
     * 查询crm系统配置列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysCrmConfig sysCrmConfig)
    {
        startPage();
        List<SysCrmConfig> list = sysCrmConfigService.selectSysCrmConfigList(sysCrmConfig);
        return getDataTable(list);
    }

    /**
     * 导出crm系统配置列表
     */
    @PreAuthorize("@ss.hasPermi('system:crmConfig:export')")
    @Log(title = "crm系统配置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysCrmConfig sysCrmConfig)
    {
        List<SysCrmConfig> list = sysCrmConfigService.selectSysCrmConfigList(sysCrmConfig);
        ExcelUtil<SysCrmConfig> util = new ExcelUtil<SysCrmConfig>(SysCrmConfig.class);
        return util.exportExcel(list, "crmConfig");
    }

    /**
     * 获取crm系统配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:crmConfig:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sysCrmConfigService.selectSysCrmConfigById(id));
    }

    /**
     * 新增crm系统配置
     */
    @Log(title = "crm系统配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCrmConfig sysCrmConfig)
    {
        return toAjax(sysCrmConfigService.insertSysCrmConfig(sysCrmConfig));
    }

    /**
     * 修改crm系统配置
     */
    @Log(title = "crm系统配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCrmConfig sysCrmConfig)
    {
        return toAjax(sysCrmConfigService.updateSysCrmConfig(sysCrmConfig));
    }

    /**
     * 删除crm系统配置
     */
    @Log(title = "crm系统配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysCrmConfigService.deleteSysCrmConfigByIds(ids));
    }
}
