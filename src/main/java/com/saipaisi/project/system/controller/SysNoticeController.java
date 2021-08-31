package com.saipaisi.project.system.controller;

import java.util.List;

import com.saipaisi.common.utils.ServletUtils;
import com.saipaisi.framework.security.service.TokenService;
import com.saipaisi.project.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.saipaisi.common.utils.SecurityUtils;
import com.saipaisi.framework.aspectj.lang.annotation.Log;
import com.saipaisi.framework.aspectj.lang.enums.BusinessType;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.framework.web.page.TableDataInfo;
import com.saipaisi.project.system.domain.SysNotice;
import com.saipaisi.project.system.service.ISysNoticeService;

/**
 * 公告 信息操作处理
 * 
 * @author saipaisi
 */
@RestController
@RequestMapping("/system/notice")
public class SysNoticeController extends BaseController
{
    @Autowired
    private ISysNoticeService noticeService;

    @Autowired
    private TokenService tokenService;

    /**
     * 获取通知公告列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysNotice notice)
    {
        startPage();
        List<SysNotice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

    /**
     * 获取個人通知公告列表
     */
    @GetMapping("/all/list")
    public TableDataInfo allList()
    {
        SysUser sysUser=tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        startPage();
        List<SysNotice> list = noticeService.selectAllNoticeList(sysUser.getUserId());
        return getDataTable(list);
    }

    /**
     * 根据通知公告编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:notice:query')")
    @GetMapping(value = "/{noticeId}")
    public AjaxResult getInfo(@PathVariable Long noticeId)
    {
        return AjaxResult.success(noticeService.selectNoticeById(noticeId));
    }

    /**
     * 新增通知公告
     */
    @PreAuthorize("@ss.hasPermi('system:notice:add')")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysNotice notice)
    {
        notice.setCreateBy(SecurityUtils.getUsername());
        notice.setDeptId(SecurityUtils.getLoginUser().getUser().getDeptId());
        return toAjax(noticeService.insertNotice(notice));
    }

    /**
     * 修改通知公告
     */
    @PreAuthorize("@ss.hasPermi('system:notice:edit')")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysNotice notice)
    {
        notice.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(noticeService.updateNotice(notice));
    }

    /**
     * 删除通知公告
     */
    @PreAuthorize("@ss.hasPermi('system:notice:remove')")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @DeleteMapping("/{noticeIds}")
    public AjaxResult remove(@PathVariable Long[] noticeIds)
    {
        return toAjax(noticeService.deleteNoticeByIds(noticeIds));
    }

    /**
     * 修改公告用戶
     * @return
     */
    @RequestMapping(value = "/update/notice/user/{id}",method = RequestMethod.GET)
    public AjaxResult updateNoticeUser(@PathVariable("id")Long id){
        SysUser sysUser =tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        return toAjax(noticeService.updateNoticeUser(id,sysUser.getUserId()));
    }
}
