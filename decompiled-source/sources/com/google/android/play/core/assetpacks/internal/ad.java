package com.google.android.play.core.assetpacks.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class ad {

    /* renamed from: a  reason: collision with root package name */
    private static final o f16807a = new o("SplitInstallInfoProvider");

    /* renamed from: b  reason: collision with root package name */
    private final Context f16808b;

    /* renamed from: c  reason: collision with root package name */
    private final String f16809c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ad(Context context) {
        this.f16808b = context;
        this.f16809c = context.getPackageName();
    }

    public final Set a() {
        PackageInfo packageInfo;
        try {
            packageInfo = this.f16808b.getPackageManager().getPackageInfo(this.f16809c, 128);
        } catch (PackageManager.NameNotFoundException unused) {
            f16807a.b("App is not found in PackageManager", new Object[0]);
            packageInfo = null;
        }
        if (packageInfo == null || packageInfo.applicationInfo == null) {
            return new HashSet();
        }
        HashSet hashSet = new HashSet();
        Bundle bundle = packageInfo.applicationInfo.metaData;
        HashSet<String> hashSet2 = new HashSet();
        if (bundle != null) {
            String string = bundle.getString("com.android.dynamic.apk.fused.modules");
            if (string == null || string.isEmpty()) {
                f16807a.a("App has no fused modules.", new Object[0]);
            } else {
                Collections.addAll(hashSet2, string.split(",", -1));
                hashSet2.remove("");
                hashSet2.remove(com.bytedance.apm.constant.r.STATS_BASE_MODULE);
            }
        }
        if (Build.VERSION.SDK_INT >= 21) {
            String[] strArr = packageInfo.splitNames;
            if (strArr != null) {
                f16807a.a("Adding splits from package manager: %s", Arrays.toString(strArr));
                Collections.addAll(hashSet2, strArr);
            } else {
                f16807a.a("No splits are found or app cannot be found in package manager.", new Object[0]);
            }
            ab a2 = ac.a();
            if (a2 != null) {
                hashSet2.addAll(a2.a());
            }
        }
        for (String str : hashSet2) {
            if (!str.startsWith("config.") && !str.contains(".config.")) {
                hashSet.add(str);
            }
        }
        return hashSet;
    }
}
