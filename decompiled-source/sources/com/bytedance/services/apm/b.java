package com.bytedance.services.apm;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* compiled from: TraceExtraInfoFlagInf.java */
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes3.dex */
public @interface b {
    public static final int GET_IO = 2;
    public static final int GET_NET = 1;
    public static final int GET_THREAD = 3;
}
