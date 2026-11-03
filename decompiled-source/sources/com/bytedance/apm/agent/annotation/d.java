package com.bytedance.apm.agent.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/* compiled from: TraceTime.java */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
@Deprecated
/* loaded from: classes.dex */
public @interface d {
    String category() default "method_trace";

    boolean enable() default true;

    String scene() default "";
}
