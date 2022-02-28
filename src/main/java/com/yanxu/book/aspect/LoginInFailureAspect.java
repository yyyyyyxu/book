package com.yanxu.book.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoginInFailureAspect {

    @Pointcut(value = "execution(* org.springframework.security.authentication.ProviderManager.authenticate(..))")
    public void pointcut(){}

    @AfterThrowing(value = "pointcut()",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        Object[] authenticationTokenClass = joinPoint.getArgs();
        Authentication authentication = (Authentication)authenticationTokenClass[0];
        String principal=(String)authentication.getPrincipal();

    }


}
