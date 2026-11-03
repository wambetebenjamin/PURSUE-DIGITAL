package com.ss.android.newmedia.redbadge.impl;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import java.util.Arrays;
import java.util.List;
/* compiled from: HWHomeBadger.java */
/* loaded from: classes9.dex */
public class f extends com.ss.android.newmedia.redbadge.h {

    /* renamed from: a  reason: collision with root package name */
    private final String f18415a = "HWHomeBadger";

    @Override // com.ss.android.newmedia.redbadge.a
    public void executeBadge(Context context, ComponentName componentName, int i2) {
        if (context == null || componentName == null) {
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        Bundle bundle = new Bundle();
        bundle.putString("package", context.getPackageName());
        bundle.putString("class", componentName.getClassName());
        bundle.putInt("badgenumber", i2);
        try {
            com.bytedance.push.utils.h.d("HWHomeBadger", "set badge number to " + i2);
            context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "change_badge", (String) null, bundle);
        } catch (Throwable th) {
            th.printStackTrace();
            throw new com.ss.android.newmedia.redbadge.d(th.getMessage());
        }
    }

    @Override // com.ss.android.newmedia.redbadge.a
    public List<String> getSupportLaunchers() {
        return Arrays.asList("com.huawei.android.launcher", "com.hihonor.android.launcher");
    }

    @Override // com.ss.android.newmedia.redbadge.h
    public int a(Context context, ComponentName componentName) {
        Bundle call;
        if (context == null || componentName == null) {
            com.bytedance.push.utils.h.d("HWHomeBadger", "args is null");
            return -1;
        }
        Bundle bundle = new Bundle();
        bundle.putString("package", context.getPackageName());
        bundle.putString("class", componentName.getClassName());
        try {
            if (Build.VERSION.SDK_INT >= 11 && (call = context.getContentResolver().call(Uri.parse("content://com.huawei.android.launcher.settings/badge/"), "getbadgeNumber", (String) null, bundle)) != null) {
                return call.getInt("badgenumber");
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return -1;
    }
}
