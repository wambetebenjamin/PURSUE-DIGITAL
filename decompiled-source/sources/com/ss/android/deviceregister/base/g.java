package com.ss.android.deviceregister.base;

import android.content.Context;
/* compiled from: OaidWrapper.java */
/* loaded from: classes9.dex */
public final class g {

    /* renamed from: a  reason: collision with root package name */
    private static volatile g f18162a;

    public void a() {
    }

    public static g a(Context context) {
        if (f18162a == null) {
            synchronized (g.class) {
                if (f18162a == null) {
                    f18162a = new g(context);
                }
            }
        }
        return f18162a;
    }

    private g(Context context) {
    }
}
