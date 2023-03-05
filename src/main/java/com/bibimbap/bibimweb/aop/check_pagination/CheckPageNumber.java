package com.bibimbap.bibimweb.aop.check_pagination;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CheckPageNumber {
}
