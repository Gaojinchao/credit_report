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
import com.saipaisi.project.basic.domain.BasicScholarshipPrize;
import com.saipaisi.project.basic.service.BasicScholarshipPrizeService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 获奖情况Controller
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@RestController
@RequestMapping("/basic/prize")
public class BasicScholarshipPrizeController extends BaseController
{
    @Autowired
    private BasicScholarshipPrizeService basicScholarshipPrizeService;

    /**
     * 查询获奖情况列表
     */
    @PreAuthorize("@ss.hasPermi('basic:prize:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasicScholarshipPrize basicScholarshipPrize)
    {
        startPage();
        List<BasicScholarshipPrize> list = basicScholarshipPrizeService.selectBasicScholarshipPrizeList(basicScholarshipPrize);
        return getDataTable(list);
    }

    /**
     * 导出获奖情况列表
     */
    @PreAuthorize("@ss.hasPermi('basic:prize:export')")
    @Log(title = "获奖情况", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicScholarshipPrize basicScholarshipPrize)
    {
        List<BasicScholarshipPrize> list = basicScholarshipPrizeService.selectBasicScholarshipPrizeList(basicScholarshipPrize);
        ExcelUtil<BasicScholarshipPrize> util = new ExcelUtil<BasicScholarshipPrize>(BasicScholarshipPrize.class);
        return util.exportExcel(list, "prize");
    }

    /**
     * 获取获奖情况详细信息
     */
    @PreAuthorize("@ss.hasPermi('basic:prize:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(basicScholarshipPrizeService.selectBasicScholarshipPrizeById(id));
    }

    /**
     * 新增获奖情况
     */
    @PreAuthorize("@ss.hasPermi('basic:prize:add')")
    @Log(title = "获奖情况", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicScholarshipPrize basicScholarshipPrize)
    {
        return toAjax(basicScholarshipPrizeService.insertBasicScholarshipPrize(basicScholarshipPrize));
    }

    /**
     * 修改获奖情况
     */
    @PreAuthorize("@ss.hasPermi('basic:prize:edit')")
    @Log(title = "获奖情况", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicScholarshipPrize basicScholarshipPrize)
    {
        return toAjax(basicScholarshipPrizeService.updateBasicScholarshipPrize(basicScholarshipPrize));
    }

    /**
     * 删除获奖情况
     */
    @PreAuthorize("@ss.hasPermi('basic:prize:remove')")
    @Log(title = "获奖情况", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(basicScholarshipPrizeService.deleteBasicScholarshipPrizeByIds(ids));
    }
}
