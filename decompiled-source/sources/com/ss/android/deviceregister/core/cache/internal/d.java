package com.ss.android.deviceregister.core.cache.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import com.ss.android.deviceregister.u;
/* compiled from: SharePreferenceCacheHandler.java */
/* loaded from: classes9.dex */
public class d extends b {

    /* renamed from: c  reason: collision with root package name */
    private static final String f18185c = "SharePreferenceCacheHandler";

    /* renamed from: a  reason: collision with root package name */
    private final SharedPreferences f18186a;

    /* renamed from: b  reason: collision with root package name */
    private final SharedPreferences f18187b;

    public d(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("context can't be null");
        }
        this.f18186a = context.getSharedPreferences(com.ss.android.deviceregister.base.a.getDeviceParamsSpName(), 0);
        this.f18187b = com.ss.android.deviceregister.base.a.getApplogStatsSp(context);
    }

    @Override // com.ss.android.deviceregister.core.cache.internal.b
    protected void a(String str, String str2) {
        if (Logger.debug()) {
            com.ss.android.common.util.b.w("SharePreferenceCacheHandler cacheString key = " + str + " value = " + str2);
        }
        b(str, str2);
    }

    @Override // com.ss.android.deviceregister.core.cache.internal.b
    protected String a(String str) {
        String b2 = b(str);
        if (Logger.debug()) {
            com.ss.android.common.util.b.w("SharePreferenceCacheHandler getCachedString key = " + str + " value = " + b2);
        }
        return b2;
    }

    private String b(String str) {
        return c(str).getString(str, null);
    }

    private void b(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        SharedPreferences.Editor edit = c(str).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    private SharedPreferences c(String str) {
        return "device_id".equals(str) ? this.f18187b : this.f18186a;
    }

    @Override // com.ss.android.deviceregister.core.cache.internal.b
    public void clear(String str) {
        SharedPreferences c2 = c(str);
        if (c2 != null && c2.contains(str)) {
            c(str).edit().remove(str).commit();
        }
        u.b(u.f18267a, "SharePreferenceCacheHandler#clear key=" + str + " getCachedString(key)=" + a(str));
        super.clear(str);
    }
}
