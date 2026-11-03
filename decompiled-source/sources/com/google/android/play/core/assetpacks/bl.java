package com.google.android.play.core.assetpacks;

import java.util.Objects;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
final class bl extends AssetLocation {

    /* renamed from: a  reason: collision with root package name */
    private final String f16514a;

    /* renamed from: b  reason: collision with root package name */
    private final long f16515b;

    /* renamed from: c  reason: collision with root package name */
    private final long f16516c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bl(String str, long j2, long j3) {
        Objects.requireNonNull(str, "Null path");
        this.f16514a = str;
        this.f16515b = j2;
        this.f16516c = j3;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AssetLocation) {
            AssetLocation assetLocation = (AssetLocation) obj;
            if (this.f16514a.equals(assetLocation.path()) && this.f16515b == assetLocation.offset() && this.f16516c == assetLocation.size()) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        long j2 = this.f16515b;
        long j3 = j2 ^ (j2 >>> 32);
        long j4 = this.f16516c;
        return ((((this.f16514a.hashCode() ^ 1000003) * 1000003) ^ ((int) j3)) * 1000003) ^ ((int) ((j4 >>> 32) ^ j4));
    }

    @Override // com.google.android.play.core.assetpacks.AssetLocation
    public final long offset() {
        return this.f16515b;
    }

    @Override // com.google.android.play.core.assetpacks.AssetLocation
    public final String path() {
        return this.f16514a;
    }

    @Override // com.google.android.play.core.assetpacks.AssetLocation
    public final long size() {
        return this.f16516c;
    }

    public final String toString() {
        String str = this.f16514a;
        long j2 = this.f16515b;
        long j3 = this.f16516c;
        return "AssetLocation{path=" + str + ", offset=" + j2 + ", size=" + j3 + "}";
    }
}
