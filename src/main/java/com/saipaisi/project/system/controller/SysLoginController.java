package com.saipaisi.project.system.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

//import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.saipaisi.common.constant.Constants;
import com.saipaisi.common.utils.ServletUtils;
import com.saipaisi.framework.security.LoginBody;
import com.saipaisi.framework.security.LoginUser;
import com.saipaisi.framework.security.service.SysLoginService;
import com.saipaisi.framework.security.service.SysPermissionService;
import com.saipaisi.framework.security.service.TokenService;
import com.saipaisi.framework.web.domain.AjaxResult;
import com.saipaisi.project.system.domain.SysMenu;
import com.saipaisi.project.system.domain.SysUser;
import com.saipaisi.project.system.service.ISysMenuService;

/**
 * 登录验证
 * 
 * @author saipaisi
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

//    @Autowired
//    private RabbitTemplate rabbitTemplate;

    /**
     * 登录方法
     * 
     * @param loginBody 登陆信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }


    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

//    @GetMapping("/sendDirectMessage")
//    public String sendDirectMessage() {
//        String messageId = String.valueOf(UUID.randomUUID());
//        String messageData = "test message, hello!";
//        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        Map<String,Object> map=new HashMap<>();
//        map.put("messageId",messageId);
//        map.put("messageData",messageData);
//        map.put("createTime",createTime);
//        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
//        rabbitTemplate.convertAndSend("lonelyDirectExchange", "TestDirectRouting", map);
//        return "ok";
//    }


    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
