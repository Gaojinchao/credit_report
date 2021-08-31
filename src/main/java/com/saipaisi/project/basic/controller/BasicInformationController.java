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
import com.saipaisi.project.basic.domain.BasicInformation;
import com.saipaisi.project.basic.service.BasicInformationService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 基本信息Controller
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@RestController
@RequestMapping("/basic/infor/mation")
public class BasicInformationController extends BaseController
{
    @Autowired
    private BasicInformationService basicInformationService;

    /**
     * 查询基本信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BasicInformation basicInformation)
    {
        startPage();
        List<BasicInformation> list = basicInformationService.selectBasicInformationList(basicInformation);
        return getDataTable(list);
    }

    /**
     * 导出基本信息列表
     */
    @Log(title = "基本信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicInformation basicInformation)
    {
        List<BasicInformation> list = basicInformationService.selectBasicInformationList(basicInformation);
        ExcelUtil<BasicInformation> util = new ExcelUtil<BasicInformation>(BasicInformation.class);
        return util.exportExcel(list, "information");
    }

    /**
     * 获取基本信息详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(basicInformationService.selectBasicInformationById(id));
    }

    /**
     * 新增基本信息
     */
    @Log(title = "基本信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicInformation basicInformation)
    {
        return toAjax(basicInformationService.insertBasicInformation(basicInformation));
    }

    /**
     * 修改基本信息
     */
    @Log(title = "基本信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicInformation basicInformation)
    {
        return toAjax(basicInformationService.updateBasicInformation(basicInformation));
    }

    /**
     * 删除基本信息
     */
    @Log(title = "基本信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(basicInformationService.deleteBasicInformationByIds(ids));
    }
}
