package com.ss.android.newmedia.redbadge.impl;

import android.content.ComponentName;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
/* compiled from: ZTEHomeBadger.java */
/* loaded from: classes9.dex */
public class s implements com.ss.android.newmedia.redbadge.a {
    @Override // com.ss.android.newmedia.redbadge.a
    public void executeBadge(Context context, ComponentName componentName, int i2) {
        if (context == null || componentName == null) {
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("app_badge_count", i2);
        bundle.putString("app_badge_component_name", componentName.flattenToString());
        try {
            context.getContentResolver().call(Uri.parse("content://com.android.launcher3.cornermark.unreadbadge"), "setAppUnreadCount", (String) null, bundle);
        } catch (Throwable th) {
            th.printStackTrace();
            throw new com.ss.android.newmedia.redbadge.d(th.getMessage());
        }
    }

    @Override // com.ss.android.newmedia.redbadge.a
    public List<String> getSupportLaunchers() {
        return new ArrayList(0);
    }
}
