package com.bytedance.apm.monitor.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/* compiled from: AddFragmentTrace.java */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Deprecated
/* loaded from: classes.dex */
public @interface a {
    int viewId() default -1;
}
