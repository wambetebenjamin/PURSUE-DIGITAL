package com.bytedance.push.settings.storage;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.push.settings.SettingsManager;
/* compiled from: ContainsProcessor.java */
/* loaded from: classes2.dex */
final class a implements e {

    /* renamed from: a  reason: collision with root package name */
    static final String f11877a = "_contains";

    /* renamed from: b  reason: collision with root package name */
    private static final String f11878b = "_result";

    /* renamed from: c  reason: collision with root package name */
    private static final String f11879c = "_storage_key";

    /* renamed from: d  reason: collision with root package name */
    private static final String f11880d = "_key";

    @Override // com.bytedance.push.settings.storage.e
    public String getMethodName() {
        return f11877a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Bundle a(String str, String str2) {
        Bundle bundle = new Bundle();
        bundle.putString(f11879c, str);
        bundle.putString(f11880d, str2);
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a(Bundle bundle) {
        if (bundle == null) {
            return false;
        }
        return bundle.getBoolean(f11878b);
    }

    @Override // com.bytedance.push.settings.storage.e
    public Bundle process(Context context, String str, Bundle bundle, String str2) {
        if (bundle == null) {
            return null;
        }
        String string = bundle.getString(f11879c);
        String string2 = bundle.getString(f11880d);
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
            return null;
        }
        Bundle bundle2 = new Bundle();
        com.bytedance.push.settings.logger.b.a().a("process for " + string);
        bundle2.putBoolean(f11878b, SettingsManager.getStorageFactory().create(context, true, string, str2).contains(string2));
        return bundle2;
    }
}
