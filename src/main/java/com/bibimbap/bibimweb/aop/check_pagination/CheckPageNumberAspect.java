package com.bibimbap.bibimweb.aop.check_pagination;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class CheckPageNumberAspect {

    private final ApplicationContext applicationContext;
    private final String WHITESPACE = " ";
    private final int RETURN_TYPE = 2;

    @Before(value = "@annotation(com.bibimbap.bibimweb.aop.check_pagination.CheckPageNumber)")
    public void checkPageNumberValidation(JoinPoint jp) {
        Signature signature = jp.getSignature();
        String returnType = signature.toLongString()
                .split(WHITESPACE)[RETURN_TYPE];
    }
}
