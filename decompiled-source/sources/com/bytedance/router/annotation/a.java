package com.bytedance.router.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/* compiled from: Autowired.java */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
/* loaded from: classes2.dex */
public @interface a {
    String desc() default "No desc.";

    String name() default "";

    boolean required() default false;
}
