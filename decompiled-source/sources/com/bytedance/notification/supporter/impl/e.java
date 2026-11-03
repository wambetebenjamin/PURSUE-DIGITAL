package com.bytedance.notification.supporter.impl;

import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.content.Context;
import android.os.UserHandle;
import com.bytedance.push.utils.h;
import com.bytedance.push.utils.t;
/* compiled from: SystemServiceImpl.java */
/* loaded from: classes2.dex */
public class e implements com.bytedance.notification.supporter.service.e {

    /* renamed from: a  reason: collision with root package name */
    private final String f9939a = "SystemServiceImpl";

    /* renamed from: b  reason: collision with root package name */
    private Object f9940b = null;

    /* renamed from: c  reason: collision with root package name */
    private int f9941c = -1;

    /* renamed from: d  reason: collision with root package name */
    private KeyguardManager f9942d;

    public e(Context context) {
        this.f9942d = (KeyguardManager) context.getSystemService("keyguard");
    }

    @Override // com.bytedance.notification.supporter.service.e
    public Object a() {
        if (this.f9940b == null) {
            try {
                this.f9940b = t.b(NotificationManager.class, "getService", new Class[0]).invoke(null, new Object[0]);
            } catch (Throwable th) {
                h.e("SystemServiceImpl", "error when get mNotificationService ", th);
            }
        }
        return this.f9940b;
    }

    @Override // com.bytedance.notification.supporter.service.e
    public int a(Context context) {
        if (this.f9941c == -1) {
            try {
                this.f9941c = ((Integer) t.b(UserHandle.class, "getIdentifier", new Class[0]).invoke(t.b(Context.class, "getUser", new Class[0]).invoke(context, new Object[0]), new Object[0])).intValue();
            } catch (Throwable th) {
                h.e("SystemServiceImpl", "error when get mCurUid ", th);
            }
        }
        return this.f9941c;
    }

    @Override // com.bytedance.notification.supporter.service.e
    public boolean b() {
        return this.f9942d.inKeyguardRestrictedInputMode();
    }
}
