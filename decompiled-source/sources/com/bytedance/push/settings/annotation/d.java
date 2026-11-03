package com.bytedance.push.settings.annotation;
/* compiled from: LocalSettingGetter.java */
/* loaded from: classes2.dex */
public @interface d {
    boolean defaultBoolean() default false;

    float defaultFloat() default 0.0f;

    int defaultInt() default 0;

    long defaultLong() default 0;

    String defaultString() default "";

    String key();
}
