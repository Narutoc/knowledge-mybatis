package com.local.naruto.knowledge.common.aspect;

import com.local.naruto.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import java.util.UUID;

/**
 * 链路追踪
 *
 * @author
 */
@Aspect
@Component
@Slf4j
public class LogTraceAspect {

    /**
     * 与 logback.xml 中的变量一致
     */
    private static final String TRACE_ID = "traceId";

    /**
     * 定义切点 切点为
     */
    @Pointcut("@within(com.local.naruto.knowledge.common.annotation.LogTrace)")
    public void printLog() {
    }

    /**
     * 环绕通知
     */
    @Around(value = "printLog()")
    public Object webLogAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 方法执行前加上链路号
        String traceId = UUIDUtils.generateUuid();
        MDC.put(TRACE_ID, traceId);
        Object proceed = joinPoint.proceed();
        MDC.remove(TRACE_ID);
        return proceed;
    }
}