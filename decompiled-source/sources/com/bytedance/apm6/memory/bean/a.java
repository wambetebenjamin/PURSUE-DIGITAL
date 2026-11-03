package com.bytedance.apm6.memory.bean;
/* compiled from: MemoryCollectInfo.java */
/* loaded from: classes.dex */
public class a {
    public boolean background;
    public long blockingGcCount;
    public long blockingGcTime;
    public long dalvikUsedSize;
    public long gcCount;
    public long gcTime;
    public long graphics;
    public boolean isMemoryReachTop;
    public long javaUsedMemory;
    public long nativePss;
    public long totalPss;
    public long vmSize;

    public String toString() {
        return "MemoryCollectInfo{gcCount=" + this.gcCount + ", gcTime=" + this.gcTime + ", blockingGcCount=" + this.blockingGcCount + ", blockingGcTime=" + this.blockingGcTime + ", background=" + this.background + ", nativePss=" + this.nativePss + ", totalPss=" + this.totalPss + ", javaUsedMemory=" + this.javaUsedMemory + ", dalvikUsedSize=" + this.dalvikUsedSize + ", graphics=" + this.graphics + ", vmSize=" + this.vmSize + ", isMemoryReachTop=" + this.isMemoryReachTop + '}';
    }
}
