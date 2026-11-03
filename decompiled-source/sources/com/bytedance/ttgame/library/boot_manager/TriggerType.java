package com.bytedance.ttgame.library.boot_manager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes3.dex */
public @interface TriggerType {
    public static final int AFTER_DID_AND_IID_GET = 16;
    public static final int AFTER_DID_GET = 2;
    public static final int AFTER_MAIN_ACTIVITY = 4;
    public static final int ATTACH_BASE_CONTEXT = 8;
    public static final int BEFORE_MODULE_INIT = 32;
    public static final int SDK_INIT = 1;
}
