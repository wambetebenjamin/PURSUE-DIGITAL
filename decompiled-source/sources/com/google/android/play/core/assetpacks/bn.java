package com.google.android.play.core.assetpacks;

import java.util.Objects;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
final class bn extends AssetPackState {

    /* renamed from: a  reason: collision with root package name */
    private final String f16520a;

    /* renamed from: b  reason: collision with root package name */
    private final int f16521b;

    /* renamed from: c  reason: collision with root package name */
    private final int f16522c;

    /* renamed from: d  reason: collision with root package name */
    private final long f16523d;

    /* renamed from: e  reason: collision with root package name */
    private final long f16524e;

    /* renamed from: f  reason: collision with root package name */
    private final int f16525f;

    /* renamed from: g  reason: collision with root package name */
    private final int f16526g;

    /* renamed from: h  reason: collision with root package name */
    private final String f16527h;

    /* renamed from: i  reason: collision with root package name */
    private final String f16528i;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bn(String str, int i2, int i3, long j2, long j3, int i4, int i5, String str2, String str3) {
        Objects.requireNonNull(str, "Null name");
        this.f16520a = str;
        this.f16521b = i2;
        this.f16522c = i3;
        this.f16523d = j2;
        this.f16524e = j3;
        this.f16525f = i4;
        this.f16526g = i5;
        Objects.requireNonNull(str2, "Null availableVersionTag");
        this.f16527h = str2;
        Objects.requireNonNull(str3, "Null installedVersionTag");
        this.f16528i = str3;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    public final String availableVersionTag() {
        return this.f16527h;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    public final long bytesDownloaded() {
        return this.f16523d;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof AssetPackState) {
            AssetPackState assetPackState = (AssetPackState) obj;
            if (this.f16520a.equals(assetPackState.name()) && this.f16521b == assetPackState.status() && this.f16522c == assetPackState.errorCode() && this.f16523d == assetPackState.bytesDownloaded() && this.f16524e == assetPackState.totalBytesToDownload() && this.f16525f == assetPackState.transferProgressPercentage() && this.f16526g == assetPackState.updateAvailability() && this.f16527h.equals(assetPackState.availableVersionTag()) && this.f16528i.equals(assetPackState.installedVersionTag())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    public final int errorCode() {
        return this.f16522c;
    }

    public final int hashCode() {
        int hashCode = ((((this.f16520a.hashCode() ^ 1000003) * 1000003) ^ this.f16521b) * 1000003) ^ this.f16522c;
        long j2 = this.f16523d;
        long j3 = this.f16524e;
        return (((((((((((hashCode * 1000003) ^ ((int) (j2 ^ (j2 >>> 32)))) * 1000003) ^ ((int) ((j3 >>> 32) ^ j3))) * 1000003) ^ this.f16525f) * 1000003) ^ this.f16526g) * 1000003) ^ this.f16527h.hashCode()) * 1000003) ^ this.f16528i.hashCode();
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    public final String installedVersionTag() {
        return this.f16528i;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    public final String name() {
        return this.f16520a;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    public final int status() {
        return this.f16521b;
    }

    public final String toString() {
        String str = this.f16520a;
        int i2 = this.f16521b;
        int i3 = this.f16522c;
        long j2 = this.f16523d;
        long j3 = this.f16524e;
        int i4 = this.f16525f;
        int i5 = this.f16526g;
        String str2 = this.f16527h;
        String str3 = this.f16528i;
        return "AssetPackState{name=" + str + ", status=" + i2 + ", errorCode=" + i3 + ", bytesDownloaded=" + j2 + ", totalBytesToDownload=" + j3 + ", transferProgressPercentage=" + i4 + ", updateAvailability=" + i5 + ", availableVersionTag=" + str2 + ", installedVersionTag=" + str3 + "}";
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    public final long totalBytesToDownload() {
        return this.f16524e;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    public final int transferProgressPercentage() {
        return this.f16525f;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackState
    public final int updateAvailability() {
        return this.f16526g;
    }
}
