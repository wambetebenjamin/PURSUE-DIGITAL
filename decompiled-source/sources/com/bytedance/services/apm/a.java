package com.bytedance.services.apm;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* compiled from: LaunchTypeInf.java */
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes3.dex */
public @interface a {
    public static final int FIRST_INSTALL_COLD_LAUNCH = 1;
    public static final int NOT_FIRST_INSTALL_COLD_LAUNCH = 2;
}
