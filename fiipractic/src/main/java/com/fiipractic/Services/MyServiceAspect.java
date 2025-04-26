package com.fiipractic.Services;

import com.fiipractic.Entity.RequestHistory;
import com.fiipractic.Entity.User;
import com.fiipractic.Util.SecurityUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyServiceAspect {
    @Pointcut("execution(* com.fiipractic.Services.WeatherService.getDetails(..))")
    public void createRequestHistory() {}

    @Autowired
    private RequestHistoryService requestHistoryService;

    @Autowired
    private SecurityUtil securityUtil;

    @Around("createRequestHistory()")
    public Object aroundGetDetails(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result;

        result = joinPoint.proceed();

        Object[] args = joinPoint.getArgs();
        String lat = (String) args[0];
        String lon = (String) args[1];
        String aqi = (String) args[2];
        String days = (String) args[3];
        String alerts = (String) args[4];

        User currentUser = securityUtil.getCurrentUser();

        requestHistoryService.createRequestHistory(new RequestHistory(
                null,
                lat,
                lon,
                true,
                aqi.equals("yes"),
                alerts.equals("yes"),
                Integer.parseInt(days),
                result.toString(),
                currentUser
        ));

        return result;
    }

    @After("execution(* com.fiipractic.Services.*.*(..))")
    public void logExecution(JoinPoint joinPoint) {
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

        logger.info("S-a executat metoda: " + joinPoint.getSignature());
    }
}

