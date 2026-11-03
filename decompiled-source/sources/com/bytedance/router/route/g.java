package com.bytedance.router.route;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
/* compiled from: SysComponentRoute.java */
/* loaded from: classes2.dex */
public abstract class g extends b {
    public abstract void openComponent(Context context, Intent intent);

    @Override // com.bytedance.router.route.d
    public void open(Context context) {
        ComponentName componentName;
        com.bytedance.router.c a2 = a();
        if (a2 == null) {
            com.bytedance.router.util.b.e("SysComponentRoute open routeIntent but the routeIntent is null!!!");
            return;
        }
        String b2 = b();
        if (TextUtils.isEmpty(b2)) {
            com.bytedance.router.util.b.e("SysComponentRoute open routeIntent but the classPath is null!!!");
            return;
        }
        Intent extra = a2.getExtra();
        if (extra == null) {
            com.bytedance.router.util.b.e("SysComponentRoute open routeIntent but the intent is null!!!");
            return;
        }
        Class<?> cls = null;
        try {
            cls = Class.forName(b2);
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        }
        if (cls != null) {
            componentName = new ComponentName(context, cls);
        } else {
            componentName = new ComponentName(context.getPackageName(), b2);
        }
        extra.setComponent(componentName);
        extra.setPackage(context.getPackageName());
        openComponent(context, extra);
    }
}
