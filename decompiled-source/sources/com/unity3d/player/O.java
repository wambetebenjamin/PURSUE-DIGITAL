package com.unity3d.player;
/* loaded from: classes10.dex */
final class O implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final long f18860a;

    /* renamed from: b  reason: collision with root package name */
    final long f18861b;

    public O(long j2, long j3) {
        this.f18860a = j2;
        this.f18861b = j3;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (ReflectionHelper.beginProxyCall(this.f18860a)) {
            try {
                ReflectionHelper.nativeProxyFinalize(this.f18861b);
            } finally {
                ReflectionHelper.endProxyCall();
            }
        }
    }
}
