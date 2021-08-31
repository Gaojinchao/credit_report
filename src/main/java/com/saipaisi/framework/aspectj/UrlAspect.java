package com.saipaisi.framework.aspectj;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @Author: lin.shi
 * @Date: 2020/8/19 10:19 上午
 * @Describe: 请求接口拦截打印信息
 */
@Aspect
@Component
@Slf4j
public class UrlAspect {
    @Pointcut("execution(* com.saipaisi.project..*Controller.*(..) )") //要处理的方法，包名+类名+方法名
    public void cut() {
    }

    @Before("cut()")//在调用上面 @Pointcut标注的方法前执行以下方法
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        // 记录下请求内容
        log.info("路径 : " + request.getRequestURL().toString());
        log.info("方法 : " + request.getMethod());
        log.info("ip : " + request.getRemoteAddr());
        log.info("方法名 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
    }
}
