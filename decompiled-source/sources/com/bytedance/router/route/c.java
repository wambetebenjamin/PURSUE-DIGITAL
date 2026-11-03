package com.bytedance.router.route;

import android.content.Context;
import android.content.Intent;
/* compiled from: BroadcastRoute.java */
/* loaded from: classes2.dex */
public class c extends g {
    @Override // com.bytedance.router.route.g
    public void openComponent(Context context, Intent intent) {
        context.sendBroadcast(intent);
    }
}
