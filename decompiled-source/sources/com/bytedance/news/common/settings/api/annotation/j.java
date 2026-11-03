package com.bytedance.news.common.settings.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/* compiled from: Settings.java */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface j {
    Class<? extends com.bytedance.news.common.settings.api.b>[] migrations() default {};

    String settingsId() default "";

    String storageKey();
}
