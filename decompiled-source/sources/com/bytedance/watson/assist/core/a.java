package com.bytedance.watson.assist.core;

import android.content.Context;
import com.bytedance.watson.assist.api.c;
import java.util.LinkedHashMap;
import java.util.List;
/* compiled from: AssistStatImp.java */
/* loaded from: classes8.dex */
public class a implements c {

    /* renamed from: a  reason: collision with root package name */
    private static volatile a f16203a;

    /* renamed from: b  reason: collision with root package name */
    private Context f16204b;

    /* renamed from: c  reason: collision with root package name */
    private com.bytedance.watson.assist.core.battery.a f16205c;

    /* renamed from: d  reason: collision with root package name */
    private com.bytedance.watson.assist.core.thermal.a f16206d;

    /* renamed from: e  reason: collision with root package name */
    private com.bytedance.watson.assist.core.cpu.a f16207e;

    /* renamed from: f  reason: collision with root package name */
    private com.bytedance.watson.assist.core.abnormal.a f16208f;

    /* renamed from: g  reason: collision with root package name */
    private com.bytedance.watson.assist.api.a f16209g;

    private a(Context context) {
        this(context, com.bytedance.watson.assist.api.a.DEFAULT);
    }

    private a(Context context, com.bytedance.watson.assist.api.a aVar) {
        this.f16204b = context.getApplicationContext();
        this.f16209g = aVar == null ? com.bytedance.watson.assist.api.a.DEFAULT : aVar;
        this.f16205c = new com.bytedance.watson.assist.core.battery.a(this.f16204b, this);
        this.f16206d = new com.bytedance.watson.assist.core.thermal.a(this.f16204b, this);
        this.f16207e = new com.bytedance.watson.assist.core.cpu.a(this.f16204b, this);
        this.f16208f = new com.bytedance.watson.assist.core.abnormal.a(this);
    }

    public static a getInstance(Context context) {
        if (f16203a == null) {
            synchronized (a.class) {
                if (f16203a == null) {
                    f16203a = new a(context);
                }
            }
        }
        return f16203a;
    }

    @Override // com.bytedance.watson.assist.api.c
    public c start() {
        com.bytedance.watson.assist.utils.b.d("start");
        this.f16205c.start();
        this.f16206d.start();
        this.f16207e.start();
        return this;
    }

    @Override // com.bytedance.watson.assist.api.c
    public void end() {
        com.bytedance.watson.assist.utils.b.d("end");
        this.f16205c.end();
        this.f16206d.end();
        this.f16207e.end();
    }

    @Override // com.bytedance.watson.assist.api.c
    public com.bytedance.watson.assist.api.a getConfig() {
        return this.f16209g;
    }

    @Override // com.bytedance.watson.assist.api.c
    public void updateConfig(com.bytedance.watson.assist.api.a aVar) {
        if (aVar != null) {
            this.f16209g = aVar;
        }
    }

    @Override // com.bytedance.watson.assist.api.c
    public String getCpuHardware() {
        return com.bytedance.watson.assist.utils.a.getCpuHardware();
    }

    @Override // com.bytedance.watson.assist.api.c
    public double getProcCpuUsage() {
        return this.f16207e.getProcCpuUsage();
    }

    @Override // com.bytedance.watson.assist.api.c
    public double getThreadCpuUsage(int i2) {
        return this.f16207e.getThreadCpuUsage(i2);
    }

    @Override // com.bytedance.watson.assist.api.c
    public List<LinkedHashMap<Long, Long>> getProcCpuTimeDetail() {
        return this.f16207e.getProcCpuTimeDetail();
    }

    @Override // com.bytedance.watson.assist.api.c
    public List<LinkedHashMap<Long, Long>> getThreadCpuTimeDetail(int i2) {
        return this.f16207e.getThreadCpuTimeDetail(i2);
    }

    @Override // com.bytedance.watson.assist.api.c
    public List<List<Integer>> getProcessCpuTimeFreqPercent() {
        return this.f16207e.getProcessCpuTimeFreqPercent();
    }

    @Override // com.bytedance.watson.assist.api.c
    public List<List<Integer>> getThreadCpuTimeFreqPercent(int i2) {
        return this.f16207e.getThreadCpuTimeFreqPercent(i2);
    }

    @Override // com.bytedance.watson.assist.api.c
    public c.b getProcessCpuTimePercent() {
        return this.f16207e.getProcessCpuTimePercent();
    }

    @Override // com.bytedance.watson.assist.api.c
    public boolean isCharging() {
        return this.f16205c.isCharging();
    }

    @Override // com.bytedance.watson.assist.api.c
    public int isPowerSaveMode() {
        return this.f16205c.isPowerSaveMode();
    }

    @Override // com.bytedance.watson.assist.api.c
    public int getBatteryLevel() {
        return this.f16205c.getBatteryLevel();
    }

    @Override // com.bytedance.watson.assist.api.c
    public float getBatteryTemperature() {
        return this.f16205c.getBatteryTemperature();
    }

    @Override // com.bytedance.watson.assist.api.c
    public int getCurrentThermalStatus() {
        return this.f16206d.getCurrentThermalStatus();
    }

    @Override // com.bytedance.watson.assist.api.c
    public void refreshCpuStat() {
        this.f16207e.refresh();
    }

    @Override // com.bytedance.watson.assist.api.c
    public boolean isCpuSampleEnvironment() {
        return this.f16208f.isCpuSampleEnvironment();
    }

    @Override // com.bytedance.watson.assist.api.c
    public boolean isCpuAbnormalProcess(float f2) {
        return this.f16208f.isAbnormalProcess(f2);
    }

    @Override // com.bytedance.watson.assist.api.c
    public boolean isCpuAbnormalThread(int i2) {
        return this.f16208f.isAbnormalThread(i2);
    }

    @Override // com.bytedance.watson.assist.api.c
    public c.a getCpuFactorTag() {
        c.a aVar = new c.a();
        aVar.cpuHardware = getCpuHardware();
        aVar.isCharging = isCharging();
        aVar.batteryLevel = getBatteryLevel();
        aVar.thermalStatus = getCurrentThermalStatus();
        aVar.powerSaveMode = isPowerSaveMode();
        aVar.batteryThermal = getBatteryTemperature();
        aVar.processCpuTimeFreqPercent = getProcessCpuTimeFreqPercent();
        return aVar;
    }

    @Override // com.bytedance.watson.assist.api.c
    public void test(int i2) {
        test();
        com.bytedance.watson.assist.utils.b.d("get thread cpu usage :" + getThreadCpuUsage(i2));
        com.bytedance.watson.assist.utils.b.d("get thread cpu time detail :" + getThreadCpuTimeDetail(i2));
        com.bytedance.watson.assist.utils.b.d("get thread cpu time percent :" + getThreadCpuTimeFreqPercent(i2));
    }

    @Override // com.bytedance.watson.assist.api.c
    public void test() {
        refreshCpuStat();
        StringBuilder sb = new StringBuilder();
        sb.append("get cpu hardware:" + getCpuHardware()).append(" is power save mode : " + isPowerSaveMode()).append(" is charging : " + isCharging()).append(" get capacity : " + getBatteryLevel()).append(" get temperature status : " + getCurrentThermalStatus()).append(" get battery temperature : " + getBatteryTemperature()).append(" get battery temperature : " + getBatteryTemperature()).append(" is cpu sample environment : " + isCpuSampleEnvironment()).append(" is cpu abnormal process : " + isCpuAbnormalProcess(9.9f)).append(" get cpu cur freq : " + com.bytedance.watson.assist.utils.a.getClusterCurrentFreq(0)).append(" get cpu max freq: " + com.bytedance.watson.assist.utils.a.getClusterMaxFreq(0)).append(" get cpu min freq: " + com.bytedance.watson.assist.utils.a.getClusterMinFreq(0)).append(" get process cpu usage : " + getProcCpuUsage()).append(" get cluster info list : " + com.bytedance.watson.assist.utils.a.getCpuClusterInfoList()).append(" get cpu scaling max : " + com.bytedance.watson.assist.utils.a.getClusterMaxFreq(1)).append(" get cpu scaling max level: " + com.bytedance.watson.assist.utils.a.getClusterMaxFreqLevel(1)).append(" get process cpu time percent : " + getProcessCpuTimePercent());
        com.bytedance.watson.assist.utils.b.d(sb.toString());
        com.bytedance.watson.assist.utils.b.toast(this.f16204b, sb.toString());
    }
}
