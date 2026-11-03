package com.ss.android;

import android.content.Context;
import com.bytedance.sdk.account.n;
/* compiled from: TTAccountConfig.java */
/* loaded from: classes9.dex */
public interface f {
    Context getApplicationContext();

    com.ss.android.account.dbtring.b getIBdTruing();

    com.ss.android.account.sec.a getISec();

    com.bytedance.sdk.account.utils.d getMonitor();

    n getNetwork();

    String host();

    boolean isLocalTest();

    boolean isSaveLoginInfo();

    @Deprecated
    boolean isSecureCaptchaEnabled();

    boolean isSupportMultiLogin();
}
