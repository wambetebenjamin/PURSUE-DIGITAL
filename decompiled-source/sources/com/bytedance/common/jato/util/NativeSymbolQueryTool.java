package com.bytedance.common.jato.util;
/* loaded from: classes2.dex */
public class NativeSymbolQueryTool {

    /* renamed from: a  reason: collision with root package name */
    private static final int f5037a = 0;

    /* renamed from: b  reason: collision with root package name */
    private static final int f5038b = 1;

    /* renamed from: c  reason: collision with root package name */
    private static final int f5039c = 2;

    private static native int nativeDlsymResult(String str, String str2);

    public static int a(String str, String str2) {
        if (str == null || str.isEmpty() || str2 == null || str2.isEmpty() || !com.bytedance.common.jato.d.loadLibrary()) {
            return 0;
        }
        return nativeDlsymResult(str, str2);
    }
}
