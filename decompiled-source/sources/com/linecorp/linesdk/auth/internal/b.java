package com.linecorp.linesdk.auth.internal;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import com.bytedance.librarian.c;
import com.linecorp.linesdk.BuildConfig;
import java.util.StringTokenizer;
/* loaded from: classes9.dex */
final class b {

    /* renamed from: a  reason: collision with root package name */
    final int f17304a;

    /* renamed from: b  reason: collision with root package name */
    final int f17305b;

    /* renamed from: c  reason: collision with root package name */
    final int f17306c;

    public static b a(Context context) {
        try {
            String str = context.getPackageManager().getPackageInfo(BuildConfig.LINE_APP_PACKAGE_NAME, 128).versionName;
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(str, c.a.DOT);
            return new b(Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()), Integer.parseInt(stringTokenizer.nextToken()));
        } catch (PackageManager.NameNotFoundException | NullPointerException | NumberFormatException unused) {
            return null;
        }
    }

    public b(int i2, int i3, int i4) {
        this.f17304a = i2;
        this.f17305b = i3;
        this.f17306c = i4;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        b bVar = (b) obj;
        return this.f17304a == bVar.f17304a && this.f17305b == bVar.f17305b && this.f17306c == bVar.f17306c;
    }

    public final int hashCode() {
        return (((this.f17304a * 31) + this.f17305b) * 31) + this.f17306c;
    }
}
