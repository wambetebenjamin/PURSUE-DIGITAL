package com.google.android.play.core.assetpacks;

import android.os.Bundle;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public abstract class AssetPackState {
    public static AssetPackState a(String str, int i2, int i3, long j2, long j3, double d2, int i4, String str2, String str3) {
        return new bn(str, i2, i3, j2, j3, (int) Math.rint(100.0d * d2), i4, str2, str3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AssetPackState b(Bundle bundle, String str, co coVar, eb ebVar, be beVar) {
        int a2 = beVar.a(bundle.getInt(com.google.android.play.core.assetpacks.model.b.a("status", str)), str);
        int i2 = bundle.getInt(com.google.android.play.core.assetpacks.model.b.a("error_code", str));
        long j2 = bundle.getLong(com.google.android.play.core.assetpacks.model.b.a("bytes_downloaded", str));
        long j3 = bundle.getLong(com.google.android.play.core.assetpacks.model.b.a("total_bytes_to_download", str));
        double a3 = coVar.a(str);
        long j4 = bundle.getLong(com.google.android.play.core.assetpacks.model.b.a("pack_version", str));
        long j5 = bundle.getLong(com.google.android.play.core.assetpacks.model.b.a("pack_base_version", str));
        int i3 = 1;
        int i4 = 4;
        if (a2 != 4) {
            i4 = a2;
        } else if (j5 != 0 && j5 != j4) {
            i3 = 2;
        }
        return a(str, i4, i2, j2, j3, a3, i3, bundle.getString(com.google.android.play.core.assetpacks.model.b.a("pack_version_tag", str), String.valueOf(bundle.getInt("app_version_code"))), ebVar.a(str));
    }

    public abstract String availableVersionTag();

    public abstract long bytesDownloaded();

    public abstract int errorCode();

    public abstract String installedVersionTag();

    public abstract String name();

    public abstract int status();

    public abstract long totalBytesToDownload();

    public abstract int transferProgressPercentage();

    public abstract int updateAvailability();
}
