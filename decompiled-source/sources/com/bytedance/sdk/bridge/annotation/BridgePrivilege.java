package com.bytedance.sdk.bridge.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface BridgePrivilege {
    public static final String PRIVATE = "private";
    public static final String PROTECTED = "protected";
    public static final String PUBLIC = "public";
}
