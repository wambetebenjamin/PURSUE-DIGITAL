package com.bytedance.push.settings.storage;

import android.net.Uri;
import java.util.List;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Item.java */
/* loaded from: classes2.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    String f11881a;

    /* renamed from: b  reason: collision with root package name */
    String f11882b;

    /* renamed from: c  reason: collision with root package name */
    String f11883c;

    /* renamed from: d  reason: collision with root package name */
    String f11884d;

    /* JADX INFO: Access modifiers changed from: package-private */
    public b(String str, String str2, String str3, String str4) {
        this.f11881a = str;
        this.f11882b = str2;
        this.f11883c = str3;
        this.f11884d = str4;
    }

    public static b create(Uri uri) {
        List<String> pathSegments;
        if (uri == null || (pathSegments = uri.getPathSegments()) == null || pathSegments.size() < 4) {
            return null;
        }
        return new b(pathSegments.get(0), pathSegments.get(1), pathSegments.get(2), pathSegments.get(3));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Uri a(Uri uri) {
        return uri.buildUpon().appendPath(this.f11881a).appendPath(this.f11882b).appendPath(this.f11883c).appendPath(this.f11884d).build();
    }
}
