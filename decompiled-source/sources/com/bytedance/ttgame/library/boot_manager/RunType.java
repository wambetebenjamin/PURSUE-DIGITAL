package com.bytedance.ttgame.library.boot_manager;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes3.dex */
public @interface RunType {
    public static final int ASYNC = 1;
    public static final int IDLE = 3;
    public static final int MAIN = 0;
}
