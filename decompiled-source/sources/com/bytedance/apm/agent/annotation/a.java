package com.bytedance.apm.agent.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/* compiled from: AddTrace.java */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Deprecated
/* loaded from: classes.dex */
public @interface a {
    String category() default "page_load";

    boolean enable() default true;
}
