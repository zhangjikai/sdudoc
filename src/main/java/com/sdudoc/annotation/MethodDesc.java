package com.sdudoc.annotation;

import java.lang.annotation.*;

import com.sdudoc.utils.Constants;

/**
 * 用于描述方法多对应的具体描述，在Action中的方法上面添加该注解，
 * 会自动将操作写入到日志中
 * @author zhangjk
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface MethodDesc {
	
	/** 方法描述 */
	public String description() default "no description";
	
	/** 操作类型 */
	public int opType() default Constants.OP_COMMON;
}
