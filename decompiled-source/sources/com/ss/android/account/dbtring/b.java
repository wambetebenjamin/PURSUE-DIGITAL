package com.ss.android.account.dbtring;

import android.content.Context;
/* compiled from: IBdTruing.java */
/* loaded from: classes9.dex */
public interface b {

    /* compiled from: IBdTruing.java */
    /* loaded from: classes9.dex */
    public interface a {
        void onFail();

        void onSuccess();
    }

    boolean a();

    boolean forceDisable();

    boolean init(Context context);

    void showVerifyDialog(int i2, String str, a aVar);
}
