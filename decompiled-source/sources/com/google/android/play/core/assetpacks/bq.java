package com.google.android.play.core.assetpacks;

import java.util.Arrays;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
final class bq extends eu {

    /* renamed from: a  reason: collision with root package name */
    private final String f16536a;

    /* renamed from: b  reason: collision with root package name */
    private final long f16537b;

    /* renamed from: c  reason: collision with root package name */
    private final int f16538c;

    /* renamed from: d  reason: collision with root package name */
    private final boolean f16539d;

    /* renamed from: e  reason: collision with root package name */
    private final boolean f16540e;

    /* renamed from: f  reason: collision with root package name */
    private final byte[] f16541f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bq(String str, long j2, int i2, boolean z, boolean z2, byte[] bArr) {
        this.f16536a = str;
        this.f16537b = j2;
        this.f16538c = i2;
        this.f16539d = z;
        this.f16540e = z2;
        this.f16541f = bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.eu
    public final int a() {
        return this.f16538c;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.eu
    public final long b() {
        return this.f16537b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.eu
    public final String c() {
        return this.f16536a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.eu
    public final boolean d() {
        return this.f16540e;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.eu
    public final boolean e() {
        return this.f16539d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof eu) {
            eu euVar = (eu) obj;
            String str = this.f16536a;
            if (str != null ? str.equals(euVar.c()) : euVar.c() == null) {
                if (this.f16537b == euVar.b() && this.f16538c == euVar.a() && this.f16539d == euVar.e() && this.f16540e == euVar.d()) {
                    if (Arrays.equals(this.f16541f, euVar instanceof bq ? ((bq) euVar).f16541f : euVar.f())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.play.core.assetpacks.eu
    public final byte[] f() {
        return this.f16541f;
    }

    public final String toString() {
        String str = this.f16536a;
        long j2 = this.f16537b;
        int i2 = this.f16538c;
        boolean z = this.f16539d;
        boolean z2 = this.f16540e;
        String arrays = Arrays.toString(this.f16541f);
        return "ZipEntry{name=" + str + ", size=" + j2 + ", compressionMethod=" + i2 + ", isPartial=" + z + ", isEndOfArchive=" + z2 + ", headerBytes=" + arrays + "}";
    }

    public final int hashCode() {
        String str = this.f16536a;
        int hashCode = str == null ? 0 : str.hashCode();
        long j2 = this.f16537b;
        return ((((((((((hashCode ^ 1000003) * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ this.f16538c) * 1000003) ^ (true != this.f16539d ? 1237 : 1231)) * 1000003) ^ (true == this.f16540e ? 1231 : 1237)) * 1000003) ^ Arrays.hashCode(this.f16541f);
    }
}
