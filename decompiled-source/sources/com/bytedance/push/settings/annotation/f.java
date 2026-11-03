package com.bytedance.push.settings.annotation;

import com.bytedance.push.settings.k;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/* compiled from: Settings.java */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface f {
    Class<? extends k>[] migrations() default {};

    String storageKey();

    boolean supportMultiProcess() default false;
}
