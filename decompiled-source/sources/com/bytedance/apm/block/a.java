package com.bytedance.apm.block;

import android.view.FrameMetrics;
/* compiled from: AbsLooperObserver.java */
/* loaded from: classes.dex */
public abstract class a {

    /* renamed from: a  reason: collision with root package name */
    private boolean f693a = false;

    public void a(String str, long j2, int i2) {
    }

    public void a(String str, FrameMetrics frameMetrics, int i2) {
    }

    public void doFrame(String str, long j2, long j3) {
    }

    public void dispatchBegin(String str) {
        this.f693a = true;
    }

    public void dispatchEnd(long j2, long j3, long j4, long j5, boolean z) {
        this.f693a = false;
    }

    public boolean isDispatchBegin() {
        return this.f693a;
    }
}
