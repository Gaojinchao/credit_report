package com.saipaisi.project.basic.controller;

import java.util.List;
import com.saipaisi.framework.aspectj.lang.annotation.Log;
import com.saipaisi.framework.aspectj.lang.enums.BusinessType;
import com.saipaisi.project.basic.domain.BaiscContractDisputes;
import com.saipaisi.project.basic.service.BaiscContractDisputesService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 合同纠纷Controller
 * 
 * @author sss
 * @date 2021-05-20
 */
@RestController
@RequestMapping("/basic/disputes")
public class BaiscContractDisputesController extends BaseController
{
    @Autowired
    private BaiscContractDisputesService baiscContractDisputesService;

    /**
     * 查询合同纠纷列表
     */
    @GetMapping("/list")
    public TableDataInfo list(BaiscContractDisputes baiscContractDisputes)
    {
        startPage();
        List<BaiscContractDisputes> list = baiscContractDisputesService.selectBaiscContractDisputesList(baiscContractDisputes);
        return getDataTable(list);
    }

    /**
     * 导出合同纠纷列表
     */
    @Log(title = "合同纠纷", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(BaiscContractDisputes baiscContractDisputes)
    {
        List<BaiscContractDisputes> list = baiscContractDisputesService.selectBaiscContractDisputesList(baiscContractDisputes);
        ExcelUtil<BaiscContractDisputes> util = new ExcelUtil<BaiscContractDisputes>(BaiscContractDisputes.class);
        return util.exportExcel(list, "disputes");
    }

    /**
     * 获取合同纠纷详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(baiscContractDisputesService.selectBaiscContractDisputesById(id));
    }

    /**
     * 新增合同纠纷
     */
    @Log(title = "合同纠纷", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody BaiscContractDisputes baiscContractDisputes)
    {
        return toAjax(baiscContractDisputesService.insertBaiscContractDisputes(baiscContractDisputes));
    }

    /**
     * 修改合同纠纷
     */
    @Log(title = "合同纠纷", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody BaiscContractDisputes baiscContractDisputes)
    {
        return toAjax(baiscContractDisputesService.updateBaiscContractDisputes(baiscContractDisputes));
    }

    /**
     * 删除合同纠纷
     */
    @Log(title = "合同纠纷", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(baiscContractDisputesService.deleteBaiscContractDisputesByIds(ids));
    }
}
