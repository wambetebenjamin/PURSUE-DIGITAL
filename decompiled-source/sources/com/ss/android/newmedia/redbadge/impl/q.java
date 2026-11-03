package com.ss.android.newmedia.redbadge.impl;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import java.util.Collections;
import java.util.List;
/* compiled from: XOSLauncherHomeBadger.java */
/* loaded from: classes9.dex */
public class q implements com.ss.android.newmedia.redbadge.a {
    @Override // com.ss.android.newmedia.redbadge.a
    public void executeBadge(Context context, ComponentName componentName, int i2) {
        Bundle bundle = new Bundle();
        try {
            bundle.putString("package", context.getPackageName());
            bundle.putString("class", componentName.getClassName());
            bundle.putInt("badgenumber", i2);
            if (Build.VERSION.SDK_INT >= 11) {
                context.getContentResolver().call(Uri.parse("content://com.transsion.XOSLauncher.unreadprovider"), "change_badge", (String) null, bundle);
                return;
            }
            throw new com.ss.android.newmedia.redbadge.d("this device is not support to show badge", new UnsupportedOperationException("this device is not support to show badge"));
        } catch (Exception e2) {
            e2.printStackTrace();
            throw new com.ss.android.newmedia.redbadge.d(e2.getMessage(), e2);
        }
    }

    @Override // com.ss.android.newmedia.redbadge.a
    public List<String> getSupportLaunchers() {
        return Collections.singletonList("com.transsion.XOSLauncher");
    }
}
