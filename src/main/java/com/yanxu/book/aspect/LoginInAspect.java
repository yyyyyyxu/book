package com.yanxu.book.aspect;

import com.yanxu.book.entity.UserLoginHistory;
import com.yanxu.book.mapper.UserLoginHistoryMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class LoginInAspect {

    @Autowired
    UserLoginHistoryMapper userLoginHistoryMapper;

    @Pointcut(value = "execution(* org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider.authenticate(..))")
    public void pointcut(){}

    @AfterReturning(value = "pointcut()",returning = "ret")
    public void BeforeLoginInHistory(JoinPoint joinPoint,Object ret){
        Object[] authenticationTokenClass = joinPoint.getArgs();
        UsernamePasswordAuthenticationToken authenticationTokens = (UsernamePasswordAuthenticationToken) authenticationTokenClass[0];
        UserLoginHistory userLoginHistory = new UserLoginHistory();
        userLoginHistory.setUserName((String) authenticationTokens.getPrincipal());
        userLoginHistory.setCreatTime(new Date());
        userLoginHistoryMapper.insert(userLoginHistory);
    }
}
