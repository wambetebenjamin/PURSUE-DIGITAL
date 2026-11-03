package com.bytedance.sdk.bridge.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes2.dex */
public @interface BridgeSyncType {
    public static final String ASYNC = "ASYNC";
    public static final String SYNC = "SYNC";
}
