package com.google.android.play.core.assetpacks;

import java.util.Map;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class bo extends AssetPackStates {

    /* renamed from: a  reason: collision with root package name */
    private final long f16529a;

    /* renamed from: b  reason: collision with root package name */
    private final Map f16530b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bo(long j2, Map map) {
        this.f16529a = j2;
        this.f16530b = map;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AssetPackStates) {
            AssetPackStates assetPackStates = (AssetPackStates) obj;
            if (this.f16529a == assetPackStates.totalBytes() && this.f16530b.equals(assetPackStates.packStates())) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        long j2 = this.f16529a;
        return ((((int) (j2 ^ (j2 >>> 32))) ^ 1000003) * 1000003) ^ this.f16530b.hashCode();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackStates
    public final Map<String, AssetPackState> packStates() {
        return this.f16530b;
    }

    public final String toString() {
        long j2 = this.f16529a;
        String obj = this.f16530b.toString();
        return "AssetPackStates{totalBytes=" + j2 + ", packStates=" + obj + "}";
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackStates
    public final long totalBytes() {
        return this.f16529a;
    }
}
