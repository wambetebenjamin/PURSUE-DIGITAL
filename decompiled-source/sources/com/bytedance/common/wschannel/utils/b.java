package com.bytedance.common.wschannel.utils;
/* compiled from: RomVersionParamHelper.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6004a = "ro.miui.ui.version.name";

    /* renamed from: b  reason: collision with root package name */
    private static final c f6005b = new c();

    public static boolean a() {
        if (com.ss.android.common.util.d.isMiui()) {
            try {
                return Integer.parseInt(a("ro.miui.ui.version.name").substring(1)) >= 12;
            } catch (Exception unused) {
                return false;
            }
        }
        return false;
    }

    private static String a(String str) {
        return f6005b.a(str);
    }
}
