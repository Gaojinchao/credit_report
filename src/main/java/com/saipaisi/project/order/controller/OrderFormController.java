package com.saipaisi.project.order.controller;

import java.util.List;
import com.saipaisi.common.utils.ServletUtils;
import com.saipaisi.framework.security.LoginUser;
import com.saipaisi.framework.security.service.TokenService;
import com.saipaisi.project.order.domain.AllMainInfoReq;
import com.saipaisi.project.system.domain.SysUser;
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
import com.saipaisi.project.order.domain.OrderForm;
import com.saipaisi.project.order.service.OrderFormService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.web.page.TableDataInfo;

/**
 * 订单Controller
 * 
 * @author alfred.zong
 * @date 2021-05-13
 */
@RestController
@RequestMapping("/order/form")
public class OrderFormController extends BaseController
{
    @Autowired
    private OrderFormService orderFormService;

    @Autowired
    private TokenService tokenService;

    /**
     * 查询订单列表
     */
    @GetMapping("/list")
    public TableDataInfo list(OrderForm orderForm)
    {
        startPage();
        List<OrderForm> list = orderFormService.selectOrderFormList(orderForm);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(OrderForm orderForm)
    {
        List<OrderForm> list = orderFormService.selectOrderFormList(orderForm);
        ExcelUtil<OrderForm> util = new ExcelUtil<OrderForm>(OrderForm.class);
        return util.exportExcel(list, "form");
    }

    /**
     * 获取订单详细信息
     */
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(orderFormService.selectOrderFormById(id));
    }

    /**
     * 新增订单
     */
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody OrderForm orderForm)
    {
        LoginUser loginUser =tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser sysUser=loginUser.getUser();
        orderForm.setUserId(sysUser.getUserId());
        orderForm.setDeptId(sysUser.getDeptId());

        return toAjax(orderFormService.insertOrderForm(orderForm));
    }

    /**
     * 修改订单
     */
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody OrderForm orderForm)
    {
        return toAjax(orderFormService.updateOrderForm(orderForm));
    }

    /**
     * 删除订单
     */
    @Log(title = "订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(orderFormService.deleteOrderFormByIds(ids));
    }




}
