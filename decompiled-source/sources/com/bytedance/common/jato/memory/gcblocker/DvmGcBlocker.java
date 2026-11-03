package com.bytedance.common.jato.memory.gcblocker;
/* loaded from: classes2.dex */
public class DvmGcBlocker extends a {
    private static native void nativeDalvikStartBlockGc();

    private static native void nativeDalvikStopBlockGc();

    private static native void nativeInit();

    @Override // com.bytedance.common.jato.memory.gcblocker.a
    public void a(int i2) {
    }

    @Override // com.bytedance.common.jato.memory.gcblocker.a
    public void a(long j2) {
    }

    @Override // com.bytedance.common.jato.memory.gcblocker.a
    public void a(boolean z, int i2) {
    }

    public DvmGcBlocker() {
        try {
            nativeInit();
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    @Override // com.bytedance.common.jato.memory.gcblocker.a
    public void a(String str) {
        try {
            nativeDalvikStartBlockGc();
        } catch (UnsatisfiedLinkError unused) {
        }
    }

    @Override // com.bytedance.common.jato.memory.gcblocker.a
    public void b(String str) {
        try {
            nativeDalvikStopBlockGc();
        } catch (UnsatisfiedLinkError unused) {
        }
    }
}
