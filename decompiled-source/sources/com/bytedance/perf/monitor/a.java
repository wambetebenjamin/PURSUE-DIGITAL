package com.bytedance.perf.monitor;
/* compiled from: AnrEntry.java */
/* loaded from: classes2.dex */
public class a implements Comparable<a> {
    public String stack;
    public long stackCost;
    public long startMs;

    public a(String str, long j2, long j3) {
        this.stack = str;
        this.startMs = j2;
        this.stackCost = j3;
    }

    public String toString() {
        return "AnrEntry{stack='" + this.stack + "', startMs=" + this.startMs + ", stackCost=" + this.stackCost + '}';
    }

    @Override // java.lang.Comparable
    public int compareTo(a aVar) {
        long j2 = this.stackCost;
        long j3 = aVar.stackCost;
        if (j2 > j3) {
            return -1;
        }
        return j2 < j3 ? 1 : 0;
    }
}
