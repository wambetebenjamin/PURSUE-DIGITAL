package com.google.android.play.core.assetpacks;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
final class bm extends AssetPackLocation {

    /* renamed from: a  reason: collision with root package name */
    private final int f16517a;

    /* renamed from: b  reason: collision with root package name */
    private final String f16518b;

    /* renamed from: c  reason: collision with root package name */
    private final String f16519c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public bm(int i2, String str, String str2) {
        this.f16517a = i2;
        this.f16518b = str;
        this.f16519c = str2;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackLocation
    public final String assetsPath() {
        return this.f16519c;
    }

    public final boolean equals(Object obj) {
        String str;
        String str2;
        if (obj == this) {
            return true;
        }
        if (obj instanceof AssetPackLocation) {
            AssetPackLocation assetPackLocation = (AssetPackLocation) obj;
            if (this.f16517a == assetPackLocation.packStorageMethod() && ((str = this.f16518b) != null ? str.equals(assetPackLocation.path()) : assetPackLocation.path() == null) && ((str2 = this.f16519c) != null ? str2.equals(assetPackLocation.assetsPath()) : assetPackLocation.assetsPath() == null)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackLocation
    public final int packStorageMethod() {
        return this.f16517a;
    }

    @Override // com.google.android.play.core.assetpacks.AssetPackLocation
    public final String path() {
        return this.f16518b;
    }

    public final String toString() {
        int i2 = this.f16517a;
        String str = this.f16518b;
        String str2 = this.f16519c;
        return "AssetPackLocation{packStorageMethod=" + i2 + ", path=" + str + ", assetsPath=" + str2 + "}";
    }

    public final int hashCode() {
        int i2 = this.f16517a ^ 1000003;
        String str = this.f16518b;
        int hashCode = ((i2 * 1000003) ^ (str == null ? 0 : str.hashCode())) * 1000003;
        String str2 = this.f16519c;
        return hashCode ^ (str2 != null ? str2.hashCode() : 0);
    }
}
