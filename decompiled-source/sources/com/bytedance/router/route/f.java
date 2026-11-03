package com.bytedance.router.route;

import android.content.Context;
import android.content.Intent;
/* compiled from: ServiceRoute.java */
/* loaded from: classes2.dex */
public class f extends g {
    @Override // com.bytedance.router.route.g
    public void openComponent(Context context, Intent intent) {
        context.startService(intent);
    }
}
