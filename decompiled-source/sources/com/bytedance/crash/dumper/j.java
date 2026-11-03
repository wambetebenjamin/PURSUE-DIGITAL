package com.bytedance.crash.dumper;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
/* compiled from: GfxInfo.java */
/* loaded from: classes2.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6528a = "gfxinfo.txt";

    public static void a(File file) {
        FileOutputStream fileOutputStream = null;
        try {
            Class<?> cls = Class.forName("android.view.WindowManagerGlobal");
            Object invoke = cls.getDeclaredMethod("getInstance", new Class[0]).invoke(null, new Object[0]);
            Method declaredMethod = cls.getDeclaredMethod("dumpGfxInfo", FileDescriptor.class, String[].class);
            declaredMethod.setAccessible(true);
            FileOutputStream fileOutputStream2 = new FileOutputStream(new File(file, f6528a));
            try {
                declaredMethod.invoke(invoke, fileOutputStream2.getFD(), new String[0]);
                com.bytedance.crash.util.k.a(fileOutputStream2);
            } catch (Throwable unused) {
                fileOutputStream = fileOutputStream2;
                com.bytedance.crash.util.k.a(fileOutputStream);
            }
        } catch (Throwable unused2) {
        }
    }
}
