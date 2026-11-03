package com.ss.android.deviceregister;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
/* compiled from: MigrateDetector.java */
/* loaded from: classes9.dex */
final class l {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18222a = 2;

    /* renamed from: b  reason: collision with root package name */
    private static final int f18223b = 1;

    /* renamed from: c  reason: collision with root package name */
    private static final int f18224c = 0;

    /* renamed from: d  reason: collision with root package name */
    private static final int f18225d = 1;

    /* renamed from: e  reason: collision with root package name */
    private static final String f18226e = "device_register_migrate_detector";

    /* renamed from: f  reason: collision with root package name */
    private static final String f18227f = "component_state";

    /* renamed from: g  reason: collision with root package name */
    private final SharedPreferences f18228g;

    /* renamed from: h  reason: collision with root package name */
    private final PackageManager f18229h;

    /* renamed from: i  reason: collision with root package name */
    private final ComponentName f18230i;

    /* renamed from: j  reason: collision with root package name */
    private final boolean f18231j;

    private static String a(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? "UNKNOWN" : "STATE_DISABLED" : "STATE_ENABLED" : "STATE_DEFAULT";
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public l(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.f18228g = applicationContext.getSharedPreferences(f18226e, 0);
        this.f18229h = applicationContext.getPackageManager();
        this.f18230i = new ComponentName(context, AActivity.class);
        boolean d2 = d();
        this.f18231j = d2;
        u.b(u.f18267a, "MigrateDetector#constructor migrate=" + d2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        u.b(u.f18267a, "MigrateDetector#disableComponent");
        this.f18229h.setComponentEnabledSetting(this.f18230i, 2, 1);
        this.f18228g.edit().putInt("component_state", 2).apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean b() {
        return this.f18231j;
    }

    private int c() {
        return this.f18229h.getComponentEnabledSetting(this.f18230i);
    }

    private boolean d() {
        int c2 = c();
        int i2 = this.f18228g.getInt("component_state", 0);
        u.b(u.f18267a, "MigrateDetector#isMigrateInternal cs=" + a(c2) + " ss=" + a(i2));
        return c2 == 0 && i2 == 2;
    }
}
