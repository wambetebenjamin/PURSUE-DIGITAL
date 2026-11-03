package com.google.android.play.core.assetpacks;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
final class bp extends em {

    /* renamed from: a  reason: collision with root package name */
    private final int f16531a;

    /* renamed from: b  reason: collision with root package name */
    private final String f16532b;

    /* renamed from: c  reason: collision with root package name */
    private final long f16533c;

    /* renamed from: d  reason: collision with root package name */
    private final long f16534d;

    /* renamed from: e  reason: collision with root package name */
    private final int f16535e;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bp(int i2, String str, long j2, long j3, int i3) {
        this.f16531a = i2;
        this.f16532b = str;
        this.f16533c = j2;
        this.f16534d = j3;
        this.f16535e = i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.em
    public final int a() {
        return this.f16531a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.em
    public final int b() {
        return this.f16535e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.em
    public final long c() {
        return this.f16533c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.em
    public final long d() {
        return this.f16534d;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.em
    public final String e() {
        return this.f16532b;
    }

    public final boolean equals(Object obj) {
        String str;
        if (obj == this) {
            return true;
        }
        if (obj instanceof em) {
            em emVar = (em) obj;
            if (this.f16531a == emVar.a() && ((str = this.f16532b) != null ? str.equals(emVar.e()) : emVar.e() == null) && this.f16533c == emVar.c() && this.f16534d == emVar.d() && this.f16535e == emVar.b()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i2 = this.f16531a ^ 1000003;
        String str = this.f16532b;
        int hashCode = str == null ? 0 : str.hashCode();
        long j2 = this.f16533c;
        long j3 = this.f16534d;
        return (((((((i2 * 1000003) ^ hashCode) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ ((int) (j3 ^ (j3 >>> 32)))) * 1000003) ^ this.f16535e;
    }

    public final String toString() {
        int i2 = this.f16531a;
        String str = this.f16532b;
        long j2 = this.f16533c;
        long j3 = this.f16534d;
        int i3 = this.f16535e;
        return "SliceCheckpoint{fileExtractionStatus=" + i2 + ", filePath=" + str + ", fileOffset=" + j2 + ", remainingBytes=" + j3 + ", previousChunk=" + i3 + "}";
    }
}
