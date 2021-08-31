package com.saipaisi.framework.aspectj.lang.annotation;

import java.lang.annotation.*;

/**
 * 数据权限过滤注解
 * 
 * @author saipaisi
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataScopeCustmer
{
    /**
     * 表的别名
     */
    public String deptAlias() default "";

    /**
     * 用户表的别名
     */
    public String userAlias() default "";

    /**
     * 部门列名
     * @return
     */
    public String deptColumn() default "";

    /**
     * 用户列名
     * @return
     */
    public String userColum() default "";
}
