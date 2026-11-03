package com.unity3d.player;

import java.util.Set;
/* renamed from: com.unity3d.player.g  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
final class RunnableC0407g implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private Set f18972a;

    /* renamed from: b  reason: collision with root package name */
    private String f18973b;

    /* renamed from: c  reason: collision with root package name */
    private int f18974c;

    /* renamed from: d  reason: collision with root package name */
    private long f18975d;

    /* renamed from: e  reason: collision with root package name */
    private long f18976e;

    /* renamed from: f  reason: collision with root package name */
    private int f18977f;

    /* renamed from: g  reason: collision with root package name */
    private int f18978g;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0407g(Set set, String str, int i2, long j2, long j3, int i3, int i4) {
        this.f18972a = set;
        this.f18973b = str;
        this.f18974c = i2;
        this.f18975d = j2;
        this.f18976e = j3;
        this.f18977f = i3;
        this.f18978g = i4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        for (IAssetPackManagerDownloadStatusCallback iAssetPackManagerDownloadStatusCallback : this.f18972a) {
            iAssetPackManagerDownloadStatusCallback.onStatusUpdate(this.f18973b, this.f18974c, this.f18975d, this.f18976e, this.f18977f, this.f18978g);
        }
    }
}
