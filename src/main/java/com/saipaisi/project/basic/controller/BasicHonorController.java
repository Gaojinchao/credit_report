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
import com.saipaisi.project.basic.domain.BasicHonor;
import com.saipaisi.project.basic.service.BasicHonorService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 荣誉Controller
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@RestController
@RequestMapping("/basic/honor")
public class BasicHonorController extends BaseController
{
    @Autowired
    private BasicHonorService basicHonorService;

    /**
     * 查询荣誉列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BasicHonor basicHonor)
    {
        startPage();
        List<BasicHonor> list = basicHonorService.selectBasicHonorList(basicHonor);
        return getDataTable(list);
    }

    /**
     * 导出荣誉列表
     */
    @Log(title = "荣誉", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicHonor basicHonor)
    {
        List<BasicHonor> list = basicHonorService.selectBasicHonorList(basicHonor);
        ExcelUtil<BasicHonor> util = new ExcelUtil<BasicHonor>(BasicHonor.class);
        return util.exportExcel(list, "honor");
    }

    /**
     * 获取荣誉详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(basicHonorService.selectBasicHonorById(id));
    }

    /**
     * 新增荣誉
     */
    @Log(title = "荣誉", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicHonor basicHonor)
    {
        return toAjax(basicHonorService.insertBasicHonor(basicHonor));
    }

    /**
     * 修改荣誉
     */
    @Log(title = "荣誉", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicHonor basicHonor)
    {
        return toAjax(basicHonorService.updateBasicHonor(basicHonor));
    }

    /**
     * 删除荣誉
     */
    @Log(title = "荣誉", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(basicHonorService.deleteBasicHonorByIds(ids));
    }
}
