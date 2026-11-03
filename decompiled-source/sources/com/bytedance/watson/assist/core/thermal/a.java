package com.bytedance.watson.assist.core.thermal;

import android.content.Context;
import android.os.PowerManager;
import com.bytedance.watson.assist.api.c;
import com.bytedance.watson.assist.core.b;
/* compiled from: ThermalInfoManager.java */
/* loaded from: classes8.dex */
public class a extends b {

    /* renamed from: c  reason: collision with root package name */
    private PowerManager f16250c;

    public a(Context context, c cVar) {
        super(context, cVar);
        this.f16250c = (PowerManager) this.f16214a.getSystemService(com.bytedance.apm.battery.config.c.BATTERY_POWER_LOCK);
    }

    public int getCurrentThermalStatus() {
        PowerManager powerManager = this.f16250c;
        if (powerManager != null) {
            return powerManager.getCurrentThermalStatus();
        }
        return 0;
    }
}
