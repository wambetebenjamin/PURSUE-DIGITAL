package com.bytedance.geckox.utils;

import android.content.Context;
/* compiled from: LibLoader.java */
/* loaded from: classes2.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private static Context f9366a;

    public static void a(Context context) {
        if (context != null) {
            f9366a = context;
        }
    }

    public static void loadLib(String str) {
        Context context = f9366a;
        if (context == null) {
            System.loadLibrary(str);
        } else {
            com.bytedance.librarian.b.loadLibraryForModule(str, context);
        }
    }
}
