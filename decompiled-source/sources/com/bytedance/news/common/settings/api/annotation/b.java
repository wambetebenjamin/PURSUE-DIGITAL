package com.bytedance.news.common.settings.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/* compiled from: AppSettingGetter.java */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes2.dex */
public @interface b {
    boolean defaultBoolean() default false;

    float defaultFloat() default 0.0f;

    int defaultInt() default 0;

    long defaultLong() default 0;

    String defaultString() default "";

    String desc();

    boolean isSensitiveToUser() default false;

    boolean isSticky() default false;

    boolean isTransient() default false;

    String key();

    String owner();
}
