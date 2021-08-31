package com.saipaisi.project.system.domain.dto;

import com.saipaisi.framework.aspectj.lang.annotation.Excel;
import com.saipaisi.framework.aspectj.lang.annotation.Excels;
import com.saipaisi.framework.web.domain.BaseEntity;
import com.saipaisi.project.system.domain.SysDept;
import com.saipaisi.project.system.domain.SysRole;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: lin.shi
 * @Date: 2020/8/20 11:19 上午
 * @Describe:
 */
@Data
public class SysUserDto  extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long userId;

    /** 部门ID */
    private Long deptId;

    /** 用户账号 */
    @Excel(name = "用户名称")
    private String userName;

    /** 用户昵称 */
    private String nickName;

    /** 用户邮箱 */
    private String email;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private String phonenumber;

    /** 用户性别 */
    private String sex;

    /** 用户头像 */
    private String avatar;

    /** 密码 */
    private String password;

    /** 盐加密 */
    private String salt;

    /** 帐号状态（0正常 1停用） */
    @Excel(name = "帐号状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;


    /** 最后登陆IP */
    private String loginIp;

    /** 最后登陆时间 */
    private Date loginDate;

    /** 部门对象 */
    private SysDept dept;

    /**
     * 公司id
     *
     */
    private Long companyId;

    /** 角色对象 */
    private List<SysRole> roles;

    /** 角色组 */
    private Long[] roleIds;

    /** 岗位组 */
    private Long[] postIds;

    //非数据库字段

    /**
     * 公司名称
     */
    private String companyName;

    private Integer customrCount;

    /**
     * openid
     */
    private String openid;

}
