package com.saipaisi.common.exception.user;

import com.saipaisi.common.exception.BaseException;

/**
 * 用户信息异常类
 * 
 * @author saipaisi
 */
public class UserException extends BaseException
{
    private static final long serialVersionUID = 1L;

    public UserException(String code, Object[] args)
    {
        super("user", code, args, null);
    }
}
