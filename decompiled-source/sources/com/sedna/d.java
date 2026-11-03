package com.sedna;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/* compiled from: RawInsertMethod.java */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes9.dex */
public @interface d {
    a[] accessFlags();

    String[] artumentTypes();

    String classNameRegex() default "";

    boolean includeOverride() default false;

    String methodName();

    String returnType();

    String targetClass();
}
