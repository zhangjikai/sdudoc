package com.sdudoc.annotation;

import java.lang.annotation.*;

/**
 * 用于权限控制，在Action中的方法上面添加注解，就可标明该方法是哪些用户可以访问
 * @author zhangjk
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Permission {
	public int value() default 1;
}
