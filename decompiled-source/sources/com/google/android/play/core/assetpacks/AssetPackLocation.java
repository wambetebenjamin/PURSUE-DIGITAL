package com.google.android.play.core.assetpacks;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public abstract class AssetPackLocation {

    /* renamed from: a  reason: collision with root package name */
    private static final AssetPackLocation f16403a = new bm(1, null, null);

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AssetPackLocation a() {
        return f16403a;
    }

    public abstract String assetsPath();

    public abstract int packStorageMethod();

    public abstract String path();
}
