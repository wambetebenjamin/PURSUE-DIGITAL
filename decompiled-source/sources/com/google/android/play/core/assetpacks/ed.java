package com.google.android.play.core.assetpacks;

import android.content.Context;
import android.content.pm.PackageManager;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
final class ed {

    /* renamed from: a  reason: collision with root package name */
    private static final com.google.android.play.core.assetpacks.internal.o f16751a = new com.google.android.play.core.assetpacks.internal.o("PackageStateCache");

    /* renamed from: b  reason: collision with root package name */
    private final Context f16752b;

    /* renamed from: c  reason: collision with root package name */
    private int f16753c = -1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ed(Context context) {
        this.f16752b = context;
    }

    public final synchronized int a() {
        if (this.f16753c == -1) {
            try {
                this.f16753c = this.f16752b.getPackageManager().getPackageInfo(this.f16752b.getPackageName(), 0).versionCode;
            } catch (PackageManager.NameNotFoundException unused) {
                f16751a.b("The current version of the app could not be retrieved", new Object[0]);
            }
        }
        return this.f16753c;
    }
}
