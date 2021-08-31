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
import com.saipaisi.project.basic.domain.BasicHistoricEvolution;
import com.saipaisi.project.basic.service.BasicHistoricEvolutionService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 历史沿革Controller
 * 
 * @author alfredzong
 * @date 2021-05-20
 */
@RestController
@RequestMapping("/basic/evolution")
public class BasicHistoricEvolutionController extends BaseController
{
    @Autowired
    private BasicHistoricEvolutionService basicHistoricEvolutionService;

    /**
     * 查询历史沿革列表
     */
    @PreAuthorize("@ss.hasPermi('basic:evolution:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasicHistoricEvolution basicHistoricEvolution)
    {
        startPage();
        List<BasicHistoricEvolution> list = basicHistoricEvolutionService.selectBasicHistoricEvolutionList(basicHistoricEvolution);
        return getDataTable(list);
    }

    /**
     * 导出历史沿革列表
     */
    @PreAuthorize("@ss.hasPermi('basic:evolution:export')")
    @Log(title = "历史沿革", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicHistoricEvolution basicHistoricEvolution)
    {
        List<BasicHistoricEvolution> list = basicHistoricEvolutionService.selectBasicHistoricEvolutionList(basicHistoricEvolution);
        ExcelUtil<BasicHistoricEvolution> util = new ExcelUtil<BasicHistoricEvolution>(BasicHistoricEvolution.class);
        return util.exportExcel(list, "evolution");
    }

    /**
     * 获取历史沿革详细信息
     */
    @PreAuthorize("@ss.hasPermi('basic:evolution:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(basicHistoricEvolutionService.selectBasicHistoricEvolutionById(id));
    }

    /**
     * 新增历史沿革
     */
    @PreAuthorize("@ss.hasPermi('basic:evolution:add')")
    @Log(title = "历史沿革", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicHistoricEvolution basicHistoricEvolution)
    {
        return toAjax(basicHistoricEvolutionService.insertBasicHistoricEvolution(basicHistoricEvolution));
    }

    /**
     * 修改历史沿革
     */
    @PreAuthorize("@ss.hasPermi('basic:evolution:edit')")
    @Log(title = "历史沿革", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicHistoricEvolution basicHistoricEvolution)
    {
        return toAjax(basicHistoricEvolutionService.updateBasicHistoricEvolution(basicHistoricEvolution));
    }

    /**
     * 删除历史沿革
     */
    @PreAuthorize("@ss.hasPermi('basic:evolution:remove')")
    @Log(title = "历史沿革", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(basicHistoricEvolutionService.deleteBasicHistoricEvolutionByIds(ids));
    }
}
