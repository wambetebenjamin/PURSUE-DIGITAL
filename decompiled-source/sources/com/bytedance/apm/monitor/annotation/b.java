package com.bytedance.apm.monitor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/* compiled from: AddMethodTrace.java */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes.dex */
public @interface b {
    String category() default "method_trace";

    boolean enable() default true;

    String scene() default "";
}
