package com.bluewind.mock.common.annotation;

import java.lang.annotation.*;

/**
 * @author liuxingyu01
 * @date 2022-03-11-16:49
 * @description 60秒最多访问两次
 *
 * 使用方法：@AccessLimit(seconds = 60, maxCount = 2, msg = "操作频率过高，请稍后再试")
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AccessLimit {

    /**
     * 限制周期(秒)
     */
    int seconds();

    /**
     * 规定周期内限制次数
     */
    int maxCount();

    /**
     * 触发限制时的消息提示
     */
    String msg() default "操作频率过高！";

}
