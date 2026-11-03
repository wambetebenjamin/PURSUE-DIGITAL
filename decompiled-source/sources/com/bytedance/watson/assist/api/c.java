package com.bytedance.watson.assist.api;

import java.util.LinkedHashMap;
import java.util.List;
/* compiled from: IAssistStat.java */
/* loaded from: classes8.dex */
public interface c {
    public static final int CPU_FREQ_LEVEL_HIGH = 3;
    public static final int CPU_FREQ_LEVEL_LOW = 0;
    public static final int CPU_FREQ_LEVEL_LOW_MID = 1;
    public static final int CPU_FREQ_LEVEL_MAX = 4;
    public static final int CPU_FREQ_LEVEL_MID_HIGH = 2;
    public static final int POWERSAVE_CLOSE = 0;
    public static final int POWERSAVE_OPEN = 1;
    public static final int POWERSAVE_UNKNOW = -1;

    /* compiled from: IAssistStat.java */
    /* loaded from: classes8.dex */
    public static class a {
        public int batteryLevel;
        public float batteryThermal;
        public String cpuHardware;
        public boolean isCharging;
        public int powerSaveMode;
        public List<List<Integer>> processCpuTimeFreqPercent;
        public int thermalStatus;
    }

    void end();

    int getBatteryLevel();

    float getBatteryTemperature();

    com.bytedance.watson.assist.api.a getConfig();

    a getCpuFactorTag();

    String getCpuHardware();

    int getCurrentThermalStatus();

    List<LinkedHashMap<Long, Long>> getProcCpuTimeDetail();

    double getProcCpuUsage();

    List<List<Integer>> getProcessCpuTimeFreqPercent();

    b getProcessCpuTimePercent();

    List<LinkedHashMap<Long, Long>> getThreadCpuTimeDetail(int i2);

    List<List<Integer>> getThreadCpuTimeFreqPercent(int i2);

    double getThreadCpuUsage(int i2);

    boolean isCharging();

    boolean isCpuAbnormalProcess(float f2);

    boolean isCpuAbnormalThread(int i2);

    boolean isCpuSampleEnvironment();

    int isPowerSaveMode();

    void refreshCpuStat();

    c start();

    void test();

    void test(int i2);

    void updateConfig(com.bytedance.watson.assist.api.a aVar);

    /* compiled from: IAssistStat.java */
    /* loaded from: classes8.dex */
    public static class b {
        public float bigCoreHigh;
        public float bigCoreLow;
        public float bigCoreMidHigh;
        public float bigCoreMidLow;
        public float bigCoreSum;
        public float middleCoreHigh;
        public float middleCoreLow;
        public float middleCoreMidHigh;
        public float middleCoreMidLow;
        public float middleCoreSum;
        public float smallCoreHigh;
        public float smallCoreLow;
        public float smallCoreMidHigh;
        public float smallCoreMidLow;
        public float smallCoreSum;

        public String toString() {
            return "CpuTimePercent{smallCoreLow=" + this.smallCoreLow + ", smallCoreMidLow=" + this.smallCoreMidLow + ", smallCoreMidHigh=" + this.smallCoreMidHigh + ", smallCoreHigh=" + this.smallCoreHigh + ", smallCoreSum=" + this.smallCoreSum + ", middleCoreLow=" + this.middleCoreLow + ", middleCoreMidLow=" + this.middleCoreMidLow + ", middleCoreMidHigh=" + this.middleCoreMidHigh + ", middleCoreHigh=" + this.middleCoreHigh + ", middleCoreSum=" + this.middleCoreSum + ", bigCoreLow=" + this.bigCoreLow + ", bigCoreMidLow=" + this.bigCoreMidLow + ", bigCoreMidHigh=" + this.bigCoreMidHigh + ", bigCoreHigh=" + this.bigCoreHigh + ", bigCoreSum=" + this.bigCoreSum + '}';
        }
    }
}
