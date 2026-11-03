package com.bytedance.watson.assist.core.battery;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.PowerManager;
import android.os.SystemClock;
import com.bytedance.apm.constant.d;
import com.bytedance.watson.assist.api.c;
import com.bytedance.watson.assist.core.b;
/* compiled from: BatteryInfoManager.java */
/* loaded from: classes8.dex */
public class a extends b {

    /* renamed from: c  reason: collision with root package name */
    private static final long f16216c = 5000;

    /* renamed from: d  reason: collision with root package name */
    private PowerManager f16217d;

    /* renamed from: e  reason: collision with root package name */
    private BatteryManager f16218e;

    /* renamed from: f  reason: collision with root package name */
    private boolean f16219f;

    /* renamed from: g  reason: collision with root package name */
    private int f16220g;

    /* renamed from: h  reason: collision with root package name */
    private int f16221h;

    /* renamed from: i  reason: collision with root package name */
    private float f16222i;

    /* renamed from: j  reason: collision with root package name */
    private long f16223j;

    public a(Context context, c cVar) {
        super(context, cVar);
        this.f16219f = false;
        this.f16220g = -1;
        this.f16221h = 0;
        this.f16222i = 0.0f;
        this.f16223j = 0L;
        this.f16217d = (PowerManager) this.f16214a.getSystemService(com.bytedance.apm.battery.config.c.BATTERY_POWER_LOCK);
        this.f16218e = (BatteryManager) this.f16214a.getSystemService("batterymanager");
    }

    public boolean isCharging() {
        b();
        return this.f16219f;
    }

    public int isPowerSaveMode() {
        return a();
    }

    private int a() {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.f16217d.isPowerSaveMode() ? 1 : 0;
        }
        return -1;
    }

    public int getBatteryLevel() {
        b();
        return this.f16221h;
    }

    public float getBatteryTemperature() {
        b();
        return this.f16222i;
    }

    private void b() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j2 = this.f16223j;
        if (j2 == 0 || elapsedRealtime - j2 >= 5000) {
            this.f16223j = elapsedRealtime;
            Intent registerReceiver = this.f16214a.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
            int intExtra = registerReceiver.getIntExtra("status", -1);
            this.f16220g = intExtra;
            this.f16219f = intExtra == 2 || (intExtra == 5 && c());
            this.f16221h = registerReceiver.getIntExtra("level", 0);
            int intExtra2 = registerReceiver.getIntExtra(d.MONITOR_TYPE_TEMPERATURE, 0);
            if (intExtra2 > 0) {
                this.f16222i = intExtra2 / 10.0f;
            }
        }
    }

    private boolean c() {
        if (Build.VERSION.SDK_INT >= 23) {
            return this.f16218e.isCharging();
        }
        return false;
    }
}
