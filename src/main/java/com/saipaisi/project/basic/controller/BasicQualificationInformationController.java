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
import com.saipaisi.project.basic.domain.BasicQualificationInformation;
import com.saipaisi.project.basic.service.BasicQualificationInformationService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 资质信息Controller
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@RestController
@RequestMapping("/basic/pualification/information")
public class BasicQualificationInformationController extends BaseController
{
    @Autowired
    private BasicQualificationInformationService basicQualificationInformationService;

    /**
     * 查询资质信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BasicQualificationInformation basicQualificationInformation)
    {
        startPage();
        List<BasicQualificationInformation> list = basicQualificationInformationService.selectBasicQualificationInformationList(basicQualificationInformation);
        return getDataTable(list);
    }

    /**
     * 导出资质信息列表
     */
    @Log(title = "资质信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicQualificationInformation basicQualificationInformation)
    {
        List<BasicQualificationInformation> list = basicQualificationInformationService.selectBasicQualificationInformationList(basicQualificationInformation);
        ExcelUtil<BasicQualificationInformation> util = new ExcelUtil<BasicQualificationInformation>(BasicQualificationInformation.class);
        return util.exportExcel(list, "information");
    }

    /**
     * 获取资质信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(basicQualificationInformationService.selectBasicQualificationInformationById(id));
    }

    /**
     * 新增资质信息
     */
    @Log(title = "资质信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicQualificationInformation basicQualificationInformation)
    {
        return toAjax(basicQualificationInformationService.insertBasicQualificationInformation(basicQualificationInformation));
    }

    /**
     * 修改资质信息
     */
    @Log(title = "资质信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicQualificationInformation basicQualificationInformation)
    {
        return toAjax(basicQualificationInformationService.updateBasicQualificationInformation(basicQualificationInformation));
    }

    /**
     * 删除资质信息
     */
    @Log(title = "资质信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(basicQualificationInformationService.deleteBasicQualificationInformationByIds(ids));
    }
}
