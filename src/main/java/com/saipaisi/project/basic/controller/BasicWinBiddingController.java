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
import com.saipaisi.project.basic.domain.BasicWinBidding;
import com.saipaisi.project.basic.service.BasicWinBiddingService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 中标履约Controller
 * 
 * @author saipaisi
 * @date 2021-05-20
 */
@RestController
@RequestMapping("/basic/bidding")
public class BasicWinBiddingController extends BaseController
{
    @Autowired
    private BasicWinBiddingService basicWinBiddingService;

    /**
     * 查询中标履约列表
     */
    @PreAuthorize("@ss.hasPermi('basic:bidding:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasicWinBidding basicWinBidding)
    {
        startPage();
        List<BasicWinBidding> list = basicWinBiddingService.selectBasicWinBiddingList(basicWinBidding);
        return getDataTable(list);
    }

    /**
     * 导出中标履约列表
     */
    @PreAuthorize("@ss.hasPermi('basic:bidding:export')")
    @Log(title = "中标履约", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicWinBidding basicWinBidding)
    {
        List<BasicWinBidding> list = basicWinBiddingService.selectBasicWinBiddingList(basicWinBidding);
        ExcelUtil<BasicWinBidding> util = new ExcelUtil<BasicWinBidding>(BasicWinBidding.class);
        return util.exportExcel(list, "bidding");
    }

    /**
     * 获取中标履约详细信息
     */
    @PreAuthorize("@ss.hasPermi('basic:bidding:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(basicWinBiddingService.selectBasicWinBiddingById(id));
    }

    /**
     * 新增中标履约
     */
    @PreAuthorize("@ss.hasPermi('basic:bidding:add')")
    @Log(title = "中标履约", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicWinBidding basicWinBidding)
    {
        return toAjax(basicWinBiddingService.insertBasicWinBidding(basicWinBidding));
    }

    /**
     * 修改中标履约
     */
    @PreAuthorize("@ss.hasPermi('basic:bidding:edit')")
    @Log(title = "中标履约", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicWinBidding basicWinBidding)
    {
        return toAjax(basicWinBiddingService.updateBasicWinBidding(basicWinBidding));
    }

    /**
     * 删除中标履约
     */
    @PreAuthorize("@ss.hasPermi('basic:bidding:remove')")
    @Log(title = "中标履约", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(basicWinBiddingService.deleteBasicWinBiddingByIds(ids));
    }
}
