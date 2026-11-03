package com.bytedance.ttgame.library.api_common.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes3.dex */
public @interface InternalApi {
    String value() default "你使用到了 GSDK 的内部 API，这些 API 仅仅用于内部使用，游戏请不要调用。";
}
