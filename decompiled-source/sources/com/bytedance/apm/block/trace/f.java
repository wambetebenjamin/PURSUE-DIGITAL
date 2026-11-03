package com.bytedance.apm.block.trace;

import android.os.SystemClock;
/* compiled from: MainThreadInfo.java */
/* loaded from: classes.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private static final int f781a = 0;

    /* renamed from: b  reason: collision with root package name */
    private static final int f782b = 1;

    /* renamed from: c  reason: collision with root package name */
    private static final int f783c = 2;

    /* renamed from: d  reason: collision with root package name */
    private static final int f784d = 3;
    public long[] mainThreadInfo = new long[4];
    public boolean isValid = false;

    public void reset() {
        long[] jArr = this.mainThreadInfo;
        jArr[0] = 0;
        jArr[1] = 0;
        jArr[2] = Long.MAX_VALUE;
        jArr[3] = 0;
        this.isValid = false;
    }

    public boolean isInputDelayed() {
        long[] jArr = this.mainThreadInfo;
        return jArr[0] - jArr[2] > 100;
    }

    public void addInputEventInfo(long j2) {
        long[] jArr = this.mainThreadInfo;
        if (jArr[0] == 0) {
            jArr[0] = SystemClock.uptimeMillis();
        }
        long[] jArr2 = this.mainThreadInfo;
        jArr2[1] = jArr2[1] + 1;
        if (j2 < jArr2[2]) {
            jArr2[2] = j2;
        }
        if (j2 > jArr2[3]) {
            jArr2[3] = j2;
        }
        this.isValid = true;
    }
}
