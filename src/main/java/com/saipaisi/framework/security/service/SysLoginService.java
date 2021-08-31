package com.saipaisi.framework.security.service;

import javax.annotation.Resource;

import com.saipaisi.framework.web.domain.server.Sys;
import com.saipaisi.project.system.domain.SysUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import com.saipaisi.common.constant.Constants;
import com.saipaisi.common.exception.CustomException;

import com.saipaisi.common.exception.user.UserPasswordNotMatchException;
import com.saipaisi.common.utils.MessageUtils;
import com.saipaisi.framework.manager.AsyncManager;
import com.saipaisi.framework.manager.factory.AsyncFactory;
import com.saipaisi.framework.security.LoginUser;
import org.springframework.util.DigestUtils;

/**
 * 登录校验方法
 * 
 * @author saipaisi
 */
@Component
public class SysLoginService
{
    @Autowired
    private TokenService tokenService;


    @Resource
    private AuthenticationManager authenticationManager;
    /**
     * 登录验证
     * 
     * @param username 用户名
     * @param password 密码

     * @return 结果
     */
    public String login(String username, String password)
    {


//        String verifyKey = Constants.CAPTCHA_CODE_KEY + uuid;
//        String captcha = redisCache.getCacheObject(verifyKey);
//        redisCache.deleteObject(verifyKey);
//        if (captcha == null)
//        {
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.expire")));
//            throw new CaptchaExpireException();
//        }
//        if (!code.equalsIgnoreCase(captcha))
//        {
//            AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.jcaptcha.error")));
//            throw new CaptchaException();
//        }
        // 用户验证
        Authentication authentication = null;
        try
        {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }
        catch (Exception e)
        {
            if (e instanceof BadCredentialsException)
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, MessageUtils.message("user.password.not.match")));
                throw new UserPasswordNotMatchException();
            }
            else
            {
                AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_FAIL, e.getMessage()));
                throw new CustomException(e.getMessage());
            }
        }
        AsyncManager.me().execute(AsyncFactory.recordLogininfor(username, Constants.LOGIN_SUCCESS, MessageUtils.message("user.login.success")));
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        // 生成token
        return tokenService.createToken(loginUser);
    }

//    public String wxLogin(String username, String password)
//    {
//        LoginUser loginUser =new LoginUser();
//        WxUser user =wxUserMapper.selectWxUser(username);
//        if (user!=null){
//            String md5DigestAsHex=DigestUtils.md5DigestAsHex((username+password).getBytes());
//            if (!md5DigestAsHex.equals(user.getPassword())){
//                throw new CustomException("用户/密码不正确");
//            }
//        }else {
//            throw new CustomException("用户不存在");
//        }
//        loginUser.setWxUser(user);
//        SysUser sysUser=new SysUser();
//        sysUser.setUserName(username);
//        sysUser.setPassword(user.getPassword());
//        loginUser.setUser(sysUser);
//        return tokenService.createToken(loginUser);
//
//    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.md5DigestAsHex("admin123456".getBytes()));
    }

}
