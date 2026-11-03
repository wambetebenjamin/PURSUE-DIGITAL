package com.bytedance.crash.dumper.tools;

import android.os.Build;
import androidx.core.view.InputDeviceCompat;
import com.bytedance.crash.util.k;
import java.io.File;
/* compiled from: ThrowableDumper.java */
/* loaded from: classes2.dex */
public class j {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6623a = "throwable.txt";

    /* renamed from: i  reason: collision with root package name */
    private static final int f6631i = 256;

    /* renamed from: j  reason: collision with root package name */
    private static final boolean f6632j;

    /* renamed from: b  reason: collision with root package name */
    private static final char[] f6624b = {'C', 'a', 'u', 's', 'e', 'd', ' ', 'b', 'y', ':', ' '};

    /* renamed from: c  reason: collision with root package name */
    private static final char[] f6625c = {'S', 'u', 'p', 'p', 'r', 'e', 's', 's', 'e', 'd', ':', ' '};

    /* renamed from: e  reason: collision with root package name */
    private static final char[] f6627e = {'\t', 'a', 't', ' '};

    /* renamed from: f  reason: collision with root package name */
    private static final char[] f6628f = {'\t', '.', '.', '.', ' '};

    /* renamed from: d  reason: collision with root package name */
    private static final char f6626d = '\n';

    /* renamed from: g  reason: collision with root package name */
    private static final char[] f6629g = {' ', 'm', 'o', 'r', 'e', f6626d};

    /* renamed from: h  reason: collision with root package name */
    private static final char[] f6630h = {' ', 's', 'k', 'i', 'p', f6626d};

    static {
        f6632j = Build.VERSION.SDK_INT >= 19;
    }

    public static String a(File file) {
        return k.d(new File(file, f6623a));
    }

    public static void a(File file, Throwable th) {
        try {
            i iVar = new i(file.getAbsolutePath() + '/' + f6623a);
            a(new h(iVar), th);
            iVar.g();
        } catch (Throwable th2) {
            com.bytedance.crash.diagnose.b.c("NPTH_DUMP", th2);
        }
    }

    private static void a(h hVar, StackTraceElement[] stackTraceElementArr, int i2) {
        int i3 = 0;
        if (i2 <= 256) {
            while (i3 < i2) {
                hVar.a(f6627e).a(stackTraceElementArr[i3]).a(f6626d);
                i3++;
            }
            return;
        }
        while (i3 < 128) {
            hVar.a(f6627e).a(stackTraceElementArr[i3]).a(f6626d);
            i3++;
        }
        hVar.a(f6628f).a(i2 + InputDeviceCompat.SOURCE_ANY).a(f6630h);
        for (int i4 = i2 - 128; i4 < i2; i4++) {
            hVar.a(f6627e).a(stackTraceElementArr[i4]).a(f6626d);
        }
    }

    private static void a(h hVar, Throwable th) {
        hVar.a(th).a(f6626d);
        StackTraceElement[] stackTrace = th.getStackTrace();
        a(hVar, stackTrace, stackTrace.length);
        if (f6632j) {
            for (Throwable th2 : th.getSuppressed()) {
                a(hVar, f6625c, 1, th2, th);
            }
        }
        Throwable cause = th.getCause();
        if (cause != null) {
            a(hVar, f6624b, 0, cause, th);
        }
    }

    private static void a(h hVar, char[] cArr, int i2, Throwable th, Throwable th2) {
        StackTraceElement[] stackTrace = th2.getStackTrace();
        StackTraceElement[] stackTrace2 = th.getStackTrace();
        int length = stackTrace2.length - 1;
        for (int length2 = stackTrace.length - 1; length >= 0 && length2 >= 0 && stackTrace2[length].equals(stackTrace[length2]); length2--) {
            length--;
        }
        int length3 = (stackTrace2.length - 1) - length;
        hVar.b(i2).a(cArr).a(th).a(f6626d);
        a(hVar, stackTrace2, stackTrace2.length - length3);
        if (length3 != 0) {
            hVar.b(i2).a(f6628f).a(length3).a(f6629g);
        }
        if (f6632j) {
            for (Throwable th3 : th.getSuppressed()) {
                a(hVar, f6625c, i2 + 1, th3, th);
            }
        }
        Throwable cause = th.getCause();
        if (cause != null) {
            a(hVar, f6624b, i2, cause, th);
        }
    }
}
