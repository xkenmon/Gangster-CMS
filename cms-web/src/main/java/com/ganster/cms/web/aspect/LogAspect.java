package com.ganster.cms.web.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ganster.cms.core.constant.CmsConst;
import com.ganster.cms.core.pojo.LogEntry;
import com.ganster.cms.core.service.LogService;
import com.ganster.cms.core.util.IPUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger("Access log");

    private final
    LogService logService;

    @Autowired
    public LogAspect(LogService logService) {
        this.logService = logService;
    }

    @Pointcut("execution(* com.ganster.cms.web.controller..*.*(..))")
    public void loggerService() {
    }

    @Pointcut("execution(* com.ganster.cms.web.controller..*.show(..))")
    public void countService(){
    }

    @Pointcut("execution(* com.ganster.cms.web.controller.CategoryController.show())")
    public void categoryCountService(){
    }

    @Pointcut("execution(* com.ganster.cms.web.controller.SiteController.show())")
    public void siteCountService(){
    }

    @Before("countService()")
    public void count(JoinPoint point){
        String fileName = point.getSourceLocation().getFileName().toUpperCase();
        if (fileName.contains("SITE")){

        }else if (fileName.contains("CATEGORY")){

        }else if (fileName.contains("ARTICLE")){

        }
    }

    @Before("loggerService()")
    public void doBeforeAdvice() { //获取RequestAttributes
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        //从获取RequestAttributes中获取HttpServletRequest的信息
        HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
        String path = request.getRequestURI();
        String ip = getIpAddress(request);

        Map<String,Object> map = new HashMap<>();
        Map<String, String[]> paramMap = request.getParameterMap();
        map.put("path", path);
        map.put("ip", ip);
        map.put("param",paramMap);
        map.put("addr", IPUtil.getAddr(ip));

        LogEntry logEntry = new LogEntry();
        logEntry.setLogTime(Calendar.getInstance().getTime());
        logEntry.setLogType(CmsConst.LOG_ACCESS);
        logEntry.setLogLevel(CmsConst.LOG_INFO);
        try {
            logEntry.setLogInfo(new ObjectMapper().writeValueAsString(map));
            logger.info(logEntry.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.info(e.getMessage());
        }

        logService.insert(logEntry);
    }


    private static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
