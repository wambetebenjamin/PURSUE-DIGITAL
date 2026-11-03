package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class p {

    /* renamed from: a  reason: collision with root package name */
    private final Context f16876a;

    public p(Context context) {
        this.f16876a = context;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String b(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle == null) {
                return null;
            }
            return bundle.getString("local_testing_dir");
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Context a() {
        return this.f16876a;
    }
}
