package com.unity3d.player;
/* renamed from: com.unity3d.player.l  reason: case insensitive filesystem */
/* loaded from: classes10.dex */
final class RunnableC0417l implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    private IAssetPackManagerStatusQueryCallback f19007a;

    /* renamed from: b  reason: collision with root package name */
    private long f19008b;

    /* renamed from: c  reason: collision with root package name */
    private String[] f19009c;

    /* renamed from: d  reason: collision with root package name */
    private int[] f19010d;

    /* renamed from: e  reason: collision with root package name */
    private int[] f19011e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC0417l(IAssetPackManagerStatusQueryCallback iAssetPackManagerStatusQueryCallback, long j2, String[] strArr, int[] iArr, int[] iArr2) {
        this.f19007a = iAssetPackManagerStatusQueryCallback;
        this.f19008b = j2;
        this.f19009c = strArr;
        this.f19010d = iArr;
        this.f19011e = iArr2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f19007a.onStatusResult(this.f19008b, this.f19009c, this.f19010d, this.f19011e);
    }
}
