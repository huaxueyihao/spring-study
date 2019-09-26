package com.time.plan.validator.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ComparetorGroup {

    // 字段,日期小的字段
    String beforeField();

    // 日期大的字段
    String afterField();

    // 两个字段差值，0或正数，大值减小值
    long subValue() default 0L;

    String message() default "开始日期不能大于结束日期";


}
