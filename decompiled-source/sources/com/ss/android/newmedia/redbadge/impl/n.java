package com.ss.android.newmedia.redbadge.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import java.util.Arrays;
import java.util.List;
/* compiled from: SmartisanHomeBadger.java */
/* loaded from: classes9.dex */
public class n implements com.ss.android.newmedia.redbadge.a {
    @Override // com.ss.android.newmedia.redbadge.a
    public void executeBadge(Context context, ComponentName componentName, int i2) {
        if (context == null || componentName == null) {
            return;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        Intent intent = new Intent("com.smartisanos.launcher.new_message");
        intent.putExtra("extra_packagename", componentName.getPackageName());
        intent.putExtra("extra_componentname", componentName.getClassName());
        intent.putExtra("extra_message_count", i2);
        if (com.ss.android.newmedia.redbadge.util.a.canResolveBroadcast(context, intent)) {
            try {
                context.sendBroadcast(intent);
                return;
            } catch (Throwable th) {
                th.printStackTrace();
                throw new com.ss.android.newmedia.redbadge.d(th.getMessage());
            }
        }
        Bundle bundle = new Bundle();
        bundle.putInt("badge_num", i2);
        context.getContentResolver().call(Uri.parse("content://com.smartisanos.launcher.badge"), "updateMessageBadge", (String) null, bundle);
    }

    @Override // com.ss.android.newmedia.redbadge.a
    public List<String> getSupportLaunchers() {
        return Arrays.asList("com.smartisanos.launcher", "com.smartisanos.home");
    }
}
