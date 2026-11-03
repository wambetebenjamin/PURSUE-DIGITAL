package com.bytedance.ttgame.framework.gbridge.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes3.dex */
public @interface GBridgeMethod {
    String callName() default "";

    boolean mixCall() default false;

    boolean sync() default false;

    boolean threading() default false;
}
