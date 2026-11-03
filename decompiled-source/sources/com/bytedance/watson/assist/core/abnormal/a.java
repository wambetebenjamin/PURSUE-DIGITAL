package com.bytedance.watson.assist.core.abnormal;

import android.os.SystemClock;
import com.bytedance.watson.assist.api.a;
import com.bytedance.watson.assist.api.c;
import com.bytedance.watson.assist.utils.b;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
/* compiled from: AbnormalManager.java */
/* loaded from: classes8.dex */
public class a {

    /* renamed from: d  reason: collision with root package name */
    private static final long f16210d = 5000;

    /* renamed from: a  reason: collision with root package name */
    private c f16211a;

    /* renamed from: b  reason: collision with root package name */
    private boolean f16212b = false;

    /* renamed from: c  reason: collision with root package name */
    private long f16213c;

    public boolean isAbnormalThread(int i2) {
        return false;
    }

    public a(c cVar) {
        this.f16211a = cVar;
    }

    public boolean isCpuSampleEnvironment() {
        a();
        return this.f16212b;
    }

    private void a() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j2 = this.f16213c;
        if (j2 == 0 || elapsedRealtime - j2 >= 5000) {
            this.f16213c = elapsedRealtime;
            float batteryTemperature = this.f16211a.getBatteryTemperature();
            int batteryLevel = this.f16211a.getBatteryLevel();
            int isPowerSaveMode = this.f16211a.isPowerSaveMode();
            boolean z = batteryTemperature <= ((float) this.f16211a.getConfig().getCpuSampleBatteryTemp());
            if (batteryLevel < this.f16211a.getConfig().getCpuSampleBatteryLevel()) {
                z = false;
            }
            boolean z2 = isPowerSaveMode != 1 ? z : false;
            b.d("updateCpuSampleEnvironment:" + z2 + ", temp:" + batteryTemperature + ", level:" + batteryLevel + ", powerSave:" + isPowerSaveMode);
            this.f16212b = z2;
        }
    }

    public boolean isAbnormalProcess(float f2) {
        if (isCpuSampleEnvironment()) {
            a.C0263a cpuAbnormalConfig = this.f16211a.getConfig().getCpuAbnormalConfig();
            if (cpuAbnormalConfig == null) {
                return true;
            }
            if (f2 >= cpuAbnormalConfig.getCpuSpeed()) {
                return ((double) cpuAbnormalConfig.getBigCpuCoreTimePercent()) <= FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE || this.f16211a.getProcessCpuTimePercent().bigCoreSum > cpuAbnormalConfig.getBigCpuCoreTimePercent();
            }
            return false;
        }
        return false;
    }
}
