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
import com.saipaisi.project.basic.domain.BasicCreditRecord;
import com.saipaisi.project.basic.service.BasicCreditRecordService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 信用记录Controller
 * 
 * @author alfredzong
 * @date 2021-05-20
 */
@RestController
@RequestMapping("/basic/record")
public class BasicCreditRecordController extends BaseController
{
    @Autowired
    private BasicCreditRecordService basicCreditRecordService;

    /**
     * 查询信用记录列表
     */
    @PreAuthorize("@ss.hasPermi('basic:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(BasicCreditRecord basicCreditRecord)
    {
        startPage();
        List<BasicCreditRecord> list = basicCreditRecordService.selectBasicCreditRecordList(basicCreditRecord);
        return getDataTable(list);
    }

    /**
     * 导出信用记录列表
     */
    @PreAuthorize("@ss.hasPermi('basic:record:export')")
    @Log(title = "信用记录", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BasicCreditRecord basicCreditRecord)
    {
        List<BasicCreditRecord> list = basicCreditRecordService.selectBasicCreditRecordList(basicCreditRecord);
        ExcelUtil<BasicCreditRecord> util = new ExcelUtil<BasicCreditRecord>(BasicCreditRecord.class);
        return util.exportExcel(list, "record");
    }

    /**
     * 获取信用记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('basic:record:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(basicCreditRecordService.selectBasicCreditRecordById(id));
    }

    /**
     * 新增信用记录
     */
    @PreAuthorize("@ss.hasPermi('basic:record:add')")
    @Log(title = "信用记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BasicCreditRecord basicCreditRecord)
    {
        return toAjax(basicCreditRecordService.insertBasicCreditRecord(basicCreditRecord));
    }

    /**
     * 修改信用记录
     */
    @PreAuthorize("@ss.hasPermi('basic:record:edit')")
    @Log(title = "信用记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BasicCreditRecord basicCreditRecord)
    {
        return toAjax(basicCreditRecordService.updateBasicCreditRecord(basicCreditRecord));
    }

    /**
     * 删除信用记录
     */
    @PreAuthorize("@ss.hasPermi('basic:record:remove')")
    @Log(title = "信用记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(basicCreditRecordService.deleteBasicCreditRecordByIds(ids));
    }
}
