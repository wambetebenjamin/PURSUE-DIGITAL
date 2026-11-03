package com.bytedance.push.settings.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/* compiled from: DefaultValueProvider.java */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes2.dex */
public @interface c {
    Class<? extends com.bytedance.push.settings.c> value();
}
