package com.local.naruto.knowledge.common.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.local.naruto.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

@Component
@Slf4j
@Aspect
public class ControllerLogAop {

    private final static String TRACE_ID = "traceId";

    @Pointcut("execution(public * com.local.naruto.knowledge.controller..*.*(..)) ")
    public void recordLog() {

    }

    public void setTraceId() {
        MDC.put(TRACE_ID, UUIDUtils.generateUuid());
    }

    @Around("recordLog()")
    public Object record(ProceedingJoinPoint joinPoint) throws Throwable {
        setTraceId();
        log.info("traceId is " + MDC.get(TRACE_ID));
        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzSimpleName = clazz.getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        String[] parameterNames = methodSignature.getParameterNames();
        Object[] parameters = joinPoint.getArgs();
        Map<String, Object> parameterMap = new LinkedHashMap<>();
        for (int i = 0; i < parameters.length; i++) {
            String parameterName = parameterNames[i];
            Object parameter = parameters[i];
            parameterMap.put(parameterName, parameter);
        }

        String parametersJsonString = JSON.toJSONString(parameterMap,
            SerializerFeature.WriteMapNullValue);
        log.info("{}#{} args:{}", clazzSimpleName, methodName, parametersJsonString);

        Object response = joinPoint.proceed(joinPoint.getArgs());

        String resultJsonString = JSON.toJSONString(response, SerializerFeature.WriteMapNullValue,
            SerializerFeature.DisableCircularReferenceDetect);
        log.info("{}#{} response:{}", clazzSimpleName, methodName, resultJsonString);

        return response;
    }
}
