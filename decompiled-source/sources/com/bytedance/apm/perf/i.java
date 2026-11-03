package com.bytedance.apm.perf;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
/* compiled from: TemperatureDataManager.java */
/* loaded from: classes.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private BroadcastReceiver f1309a;

    /* renamed from: b  reason: collision with root package name */
    private IntentFilter f1310b;

    /* renamed from: c  reason: collision with root package name */
    private boolean f1311c;

    /* renamed from: d  reason: collision with root package name */
    private float f1312d;

    /* renamed from: e  reason: collision with root package name */
    private int f1313e;

    /* renamed from: f  reason: collision with root package name */
    private int f1314f;

    private i() {
        a();
    }

    /* compiled from: TemperatureDataManager.java */
    /* loaded from: classes.dex */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private static final i f1315a = new i();

        private a() {
        }
    }

    public static i getInstance() {
        return a.f1315a;
    }

    private void a() {
        this.f1309a = new BroadcastReceiver() { // from class: com.bytedance.apm.perf.TemperatureDataManager$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent == null) {
                    return;
                }
                i.this.f1312d = intent.getIntExtra(com.bytedance.apm.constant.d.MONITOR_TYPE_TEMPERATURE, 0) / 10.0f;
                i.this.f1313e = intent.getIntExtra("status", 1);
                i.this.f1314f = intent.getIntExtra("plugged", -1);
            }
        };
        this.f1310b = new IntentFilter("android.intent.action.BATTERY_CHANGED");
    }

    public synchronized void registerTemperatureReceiver() {
        if (this.f1311c) {
            return;
        }
        try {
            this.f1311c = true;
            com.bytedance.apm.c.getContext().registerReceiver(this.f1309a, this.f1310b);
        } catch (Exception unused) {
        }
    }

    public synchronized void unRegisterTemperatureReceiver() {
        if (this.f1311c) {
            try {
                com.bytedance.apm.c.getContext().unregisterReceiver(this.f1309a);
                this.f1311c = false;
            } catch (Exception unused) {
            }
        }
    }

    public float getTemperature() {
        return this.f1312d;
    }

    public boolean isRegistered() {
        return this.f1311c;
    }

    public int getStatus() {
        return this.f1313e;
    }

    public int getPlugged() {
        return this.f1314f;
    }
}
