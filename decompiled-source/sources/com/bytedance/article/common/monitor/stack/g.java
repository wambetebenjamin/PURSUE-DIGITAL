package com.bytedance.article.common.monitor.stack;

import android.os.Build;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.Set;
/* compiled from: Stack.java */
/* loaded from: classes.dex */
public final class g {

    /* renamed from: a  reason: collision with root package name */
    private static final String f3650a = "Suppressed: ";

    /* renamed from: b  reason: collision with root package name */
    private static final String f3651b = "Caused by: ";

    /* renamed from: c  reason: collision with root package name */
    private static final int f3652c = 256;

    /* renamed from: d  reason: collision with root package name */
    private static final int f3653d = 128;

    private g() {
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:9:0x001b
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public static java.lang.String getExceptionStack(java.lang.Throwable r2) {
        /*
            java.io.StringWriter r0 = new java.io.StringWriter
            r0.<init>()
            java.io.PrintWriter r1 = new java.io.PrintWriter
            r1.<init>(r0)
            a(r2, r1)     // Catch: java.lang.Throwable -> L15
            java.lang.String r2 = r0.toString()     // Catch: java.lang.Throwable -> L15
            r1.close()
            return r2
        L15:
            java.lang.String r2 = ""
            r1.close()
            return r2
        L1b:
            r2 = move-exception
            r1.close()
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.article.common.monitor.stack.g.getExceptionStack(java.lang.Throwable):java.lang.String");
    }

    private static void a(Throwable th, PrintWriter printWriter) {
        if (th == null || printWriter == null) {
            return;
        }
        Set newSetFromMap = Collections.newSetFromMap(new IdentityHashMap());
        newSetFromMap.add(th);
        printWriter.println(th);
        StackTraceElement[] stackTrace = th.getStackTrace();
        boolean z = stackTrace.length > 384;
        int length = stackTrace.length;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i2 >= length) {
                break;
            }
            StackTraceElement stackTraceElement = stackTrace[i2];
            if (z && i3 > 256) {
                printWriter.println("\t... skip " + ((stackTrace.length - i3) - 128) + " lines");
                break;
            }
            printWriter.println("\tat " + stackTraceElement);
            i3++;
            i2++;
        }
        if (z) {
            for (int length2 = stackTrace.length - 128; length2 < stackTrace.length; length2++) {
                printWriter.println("\tat " + stackTrace[length2]);
            }
        }
        if (Build.VERSION.SDK_INT >= 19) {
            for (Throwable th2 : th.getSuppressed()) {
                a(th2, printWriter, stackTrace, f3650a, "\t", newSetFromMap, 128);
            }
        }
        Throwable cause = th.getCause();
        if (cause != null) {
            a(cause, printWriter, stackTrace, f3651b, "", newSetFromMap, 128);
        }
    }

    private static void a(Throwable th, PrintWriter printWriter, StackTraceElement[] stackTraceElementArr, String str, String str2, Set<Throwable> set, int i2) {
        if (set.contains(th)) {
            printWriter.println("\t[CIRCULAR REFERENCE:" + th + "]");
            return;
        }
        set.add(th);
        StackTraceElement[] stackTrace = th.getStackTrace();
        boolean z = stackTrace.length > i2;
        printWriter.println(str2 + str + th);
        int length = stackTrace.length;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i3 >= length) {
                break;
            }
            StackTraceElement stackTraceElement = stackTrace[i3];
            if (z && i4 > i2) {
                printWriter.println("\t... skip " + ((stackTrace.length - i4) - (i2 / 2)) + " lines");
                break;
            }
            printWriter.println("\tat " + stackTraceElement);
            i4++;
            i3++;
        }
        if (z) {
            for (int length2 = stackTrace.length - (i2 / 2); length2 < stackTrace.length; length2++) {
                printWriter.println("\tat " + stackTrace[length2]);
            }
        }
        if (Build.VERSION.SDK_INT >= 19) {
            for (Throwable th2 : th.getSuppressed()) {
                int i5 = i2 / 2;
                a(th2, printWriter, stackTrace, f3650a, str2 + "\t", set, i5 > 10 ? i5 : 10);
            }
        }
        Throwable cause = th.getCause();
        if (cause != null) {
            int i6 = i2 / 2;
            a(cause, printWriter, stackTrace, f3651b, str2, set, i6 > 10 ? i6 : 10);
        }
    }

    private static boolean a(Throwable th) {
        int i2 = 0;
        while (th != null) {
            if (th instanceof StackOverflowError) {
                return true;
            }
            if (i2 > 20) {
                return false;
            }
            th = th.getCause();
            i2++;
        }
        return false;
    }
}
