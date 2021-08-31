package com.saipaisi.project.system.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.saipaisi.project.system.domain.dto.SysUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.saipaisi.common.constant.UserConstants;
import com.saipaisi.common.utils.SecurityUtils;
import com.saipaisi.common.utils.ServletUtils;
import com.saipaisi.common.utils.StringUtils;
import com.saipaisi.common.utils.poi.ExcelUtil;
import com.saipaisi.framework.aspectj.lang.annotation.Log;
import com.saipaisi.framework.aspectj.lang.enums.BusinessType;
import com.saipaisi.framework.security.LoginUser;
import com.saipaisi.framework.security.service.TokenService;
import com.saipaisi.framework.web.controller.BaseController;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.framework.web.page.TableDataInfo;
import com.saipaisi.project.system.domain.SysRole;
import com.saipaisi.project.system.domain.SysUser;
import com.saipaisi.project.system.service.ISysPostService;
import com.saipaisi.project.system.service.ISysRoleService;
import com.saipaisi.project.system.service.ISysUserService;

/**
 * 用户信息
 * 
 * @author saipaisi
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysRoleService roleService;

    @Autowired
    private ISysPostService postService;

    @Autowired
    private TokenService tokenService;

    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysUser user)
    {
        startPage();
        List<SysUserDto> list = userService.selectUserListDto(user);
        //查看坐席验证状态
//        list.forEach(u->{
//            //是否开通坐席(开通)
//            if(Constant.SEAT_ON.equals(u.getOpenStatus())){
//                //是否已经验证过了(未验证)
//                if(Constant.SEAT_NO_VERIFY.equals(u.getVerify())){
//                    RYSeatInfoFResp seatInfo = client.getSeatInfo(u.getPhonenumber());
//                    if(seatInfo!=null && seatInfo.getIsVail() == 1){
//                        //修改当前用户的坐席状态
//                        u.setVerify(Constant.SEAT_VERIFY);
//                        //修改数据库信息
//                        userService.updateUser(u);
//                    }
//                }
//            }
//        });
        return getDataTable(list);
    }

    /**
     * 列表导出
     * @return
     */
    @RequestMapping(value = "/list/export",method = RequestMethod.GET)
    public AjaxResult exportList(SysUser user){
        List<SysUserDto> list = userService.selectUserListDto(user);
        ExcelUtil excelUtil=new ExcelUtil(SysUserDto.class);
        return excelUtil.exportExcel(list,"用户列表");
    }

    /**
     * 获取所有的用户列表
     */
    @GetMapping("/alllist")
    public TableDataInfo alllist(SysUser user)
    {
        List<SysUser> list = userService.selectUserList(user);
        return getDataTable(list);
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PreAuthorize("@ss.hasPermi('system:user:export')")
    @GetMapping("/export")
    public AjaxResult export(SysUser user)
    {
        List<SysUser> list = userService.selectUserList(user);
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.exportExcel(list, "用户数据");
    }

    @Log(title = "用户管理", businessType = BusinessType.IMPORT)
    @PreAuthorize("@ss.hasPermi('system:user:import')")
    @PostMapping("/importData")
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        List<SysUser> userList = util.importExcel(file.getInputStream());
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String operName = loginUser.getUsername();
        String message = userService.importUser(userList, updateSupport, operName);
        return AjaxResult.success(message);
    }

    @GetMapping("/importTemplate")
    public AjaxResult importTemplate()
    {
        ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        return util.importTemplateExcel("用户数据");
    }

    /**
     * 根据用户编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:user:query')")
    @GetMapping(value = { "/", "/{userId}" })
    public AjaxResult getInfo(@PathVariable(value = "userId", required = false) Long userId)
    {
        AjaxResult ajax = AjaxResult.success();
        List<SysRole> roles = roleService.selectRoleAll();
        ajax.put("roles", SysUser.isAdmin(userId) ? roles : roles.stream().filter(r -> !r.isAdmin()).collect(Collectors.toList()));
        ajax.put("posts", postService.selectPostAll());
        if (StringUtils.isNotNull(userId))
        {
            ajax.put(AjaxResult.DATA_TAG, userService.selectUserById(userId));
            ajax.put("postIds", postService.selectPostListByUserId(userId));
            ajax.put("roleIds", roleService.selectRoleListByUserId(userId));
        }
        return ajax;
    }

    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysUser user)
    {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName())))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        }
        else if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，手机号码已存在");
        }
        user.setCreateBy(SecurityUtils.getUsername());
        SysUser entity=tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));

        return toAjax(userService.insertUser(user,entity));
    }
    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysUser user)
    {
        userService.checkUserAllowed(user);
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUniqueUIfo(user)))
        {
            return AjaxResult.error("新增用户'" + user.getUserName() + "'失败，登录账号已存在");
        }
        if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return AjaxResult.error("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        }


        user.setUpdateBy(SecurityUtils.getUsername());

        return toAjax(userService.updateUser(user));
    }


    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('system:user:remove')")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        Long aLong=tokenService.getLoginUser(ServletUtils.getRequest()).getUser().getUserId();
        for (Long l:userIds) {
            if (aLong.equals(l)){
                return new AjaxResult(500,"不能删除自己");
            }
        }
        return toAjax(userService.deleteUserByIds(userIds));
    }

    /**
     * 重置密码
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/resetPwd")
    public AjaxResult resetPwd(@RequestBody SysUser user)
    {
        userService.checkUserAllowed(user);
        user.setPassword(SecurityUtils.encryptPassword(user.getPassword()));
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.resetPwd(user));
    }

    /**
     * 状态修改
     */
    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysUser user)
    {
        userService.checkUserAllowed(user);
        user.setUpdateBy(SecurityUtils.getUsername());
        return toAjax(userService.updateUserStatus(user));
    }

    /**
     * 更新openid
     * @param userName
     * @param openid
     * @return
     */
    @GetMapping("/update/openid")
    public AjaxResult updateOpenid(@RequestParam("userName")String userName,@RequestParam("openid") String openid){
        return toAjax(userService.updateOpenid(userName,openid));
    }


}