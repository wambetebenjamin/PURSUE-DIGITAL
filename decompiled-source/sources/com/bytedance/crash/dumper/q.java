package com.bytedance.crash.dumper;

import java.io.File;
/* compiled from: RepairInfo.java */
/* loaded from: classes2.dex */
public class q {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6597a = "signal_monitor.txt";

    public static void a(File file) {
        try {
            com.bytedance.crash.repair.a.b(2, new File(file, f6597a).getAbsolutePath());
        } catch (Throwable unused) {
        }
    }
}
