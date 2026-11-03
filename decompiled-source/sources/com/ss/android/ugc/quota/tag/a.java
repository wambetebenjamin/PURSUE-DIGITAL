package com.ss.android.ugc.quota.tag;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
/* compiled from: BDNetworkTagLaunchType.java */
@Retention(RetentionPolicy.SOURCE)
/* loaded from: classes9.dex */
public @interface a {
    public static final int COLD_LAUNCH = 0;
    public static final int HOT_LAUNCH = 2;
    public static final int NORMAL = -1;
    public static final int UNSPECIFIED = -999;
    public static final int WARM_LAUNCH = 1;
}
