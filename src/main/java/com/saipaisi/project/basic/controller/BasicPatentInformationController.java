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
import com.saipaisi.project.basic.domain.BasicPatentInformation;
import com.saipaisi.project.basic.service.BasicPatentInformationService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 专利信息Controller
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@RestController
@RequestMapping("/basic/patent/information")
public class BasicPatentInformationController extends BaseController
{
    @Autowired
    private BasicPatentInformationService basicPatentInformationService;

    /**
     * 查询专利信息列表
     */
    @PreAuthorize("@ss.hasPermi('basic:information:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasicPatentInformation basicPatentInformation)
    {
        startPage();
        List<BasicPatentInformation> list = basicPatentInformationService.selectBasicPatentInformationList(basicPatentInformation);
        return getDataTable(list);
    }

    /**
     * 导出专利信息列表
     */
    @PreAuthorize("@ss.hasPermi('basic:information:export')")
    @Log(title = "专利信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicPatentInformation basicPatentInformation)
    {
        List<BasicPatentInformation> list = basicPatentInformationService.selectBasicPatentInformationList(basicPatentInformation);
        ExcelUtil<BasicPatentInformation> util = new ExcelUtil<BasicPatentInformation>(BasicPatentInformation.class);
        return util.exportExcel(list, "information");
    }

    /**
     * 获取专利信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('basic:information:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(basicPatentInformationService.selectBasicPatentInformationById(id));
    }

    /**
     * 新增专利信息
     */
    @PreAuthorize("@ss.hasPermi('basic:information:add')")
    @Log(title = "专利信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicPatentInformation basicPatentInformation)
    {
        return toAjax(basicPatentInformationService.insertBasicPatentInformation(basicPatentInformation));
    }

    /**
     * 修改专利信息
     */
    @PreAuthorize("@ss.hasPermi('basic:information:edit')")
    @Log(title = "专利信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicPatentInformation basicPatentInformation)
    {
        return toAjax(basicPatentInformationService.updateBasicPatentInformation(basicPatentInformation));
    }

    /**
     * 删除专利信息
     */
    @PreAuthorize("@ss.hasPermi('basic:information:remove')")
    @Log(title = "专利信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(basicPatentInformationService.deleteBasicPatentInformationByIds(ids));
    }
}
