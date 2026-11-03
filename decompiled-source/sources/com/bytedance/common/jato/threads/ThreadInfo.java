package com.bytedance.common.jato.threads;
/* loaded from: classes2.dex */
public class ThreadInfo {
    private int lockTid;
    private long nativePeer;
    private int stm;
    private String threadName;
    private int tid;
    private int utm;

    public int getLockTid() {
        return this.lockTid;
    }

    public long getNativePeer() {
        return this.nativePeer;
    }

    public String getThreadName() {
        return this.threadName;
    }

    public int getUtm() {
        return this.utm;
    }

    public int getStm() {
        return this.stm;
    }

    public int getTid() {
        return this.tid;
    }

    public String toString() {
        return "ThreadInfo{tid=" + this.tid + ", lock_tid=" + this.lockTid + ", threadPeer=" + this.nativePeer + ", threadName='" + this.threadName + "', utm=" + this.utm + ", stm=" + this.stm + '}';
    }
}
