package com.linecorp.linesdk.a.a.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import com.bytedance.librarian.c;
import java.util.Locale;
/* loaded from: classes9.dex */
final class e {

    /* renamed from: a  reason: collision with root package name */
    private final PackageInfo f17251a;

    /* renamed from: b  reason: collision with root package name */
    private final String f17252b;

    /* renamed from: c  reason: collision with root package name */
    private String f17253c;

    /* JADX INFO: Access modifiers changed from: package-private */
    public e(Context context, String str) {
        this.f17251a = a(context);
        this.f17252b = str;
    }

    public final String a() {
        String str = this.f17253c;
        if (str != null) {
            return str;
        }
        PackageInfo packageInfo = this.f17251a;
        String str2 = packageInfo == null ? "UNK" : packageInfo.packageName;
        PackageInfo packageInfo2 = this.f17251a;
        String str3 = packageInfo2 != null ? packageInfo2.versionName : "UNK";
        Locale locale = Locale.getDefault();
        String str4 = str2 + c.a.SEPARATOR + str3 + " ChannelSDK/" + this.f17252b + " (Linux; U; Android " + Build.VERSION.RELEASE + "; " + locale.getLanguage() + "-" + locale.getCountry() + "; " + Build.MODEL + " Build/" + Build.ID + ")";
        this.f17253c = str4;
        return str4;
    }

    private static PackageInfo a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException unused) {
            throw null;
        }
    }
}
