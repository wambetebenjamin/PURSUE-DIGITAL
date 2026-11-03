package com.ss.android.ugc.aweme.framework.services.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes9.dex */
public @interface ServiceImpl {
    Class[] a();

    boolean b() default true;

    boolean c() default false;
}
