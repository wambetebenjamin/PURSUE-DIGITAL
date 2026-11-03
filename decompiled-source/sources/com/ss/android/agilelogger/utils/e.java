package com.ss.android.agilelogger.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.UnknownHostException;
/* compiled from: StackTraceUtils.java */
/* loaded from: classes9.dex */
public class e {
    public static String getStackTraceString(Throwable th) {
        if (th == null) {
            return "";
        }
        for (Throwable th2 = th; th2 != null; th2 = th2.getCause()) {
            if (th2 instanceof UnknownHostException) {
                return "ALOG:UnknownHostException";
            }
        }
        a aVar = new a();
        PrintWriter printWriter = new PrintWriter(aVar);
        try {
            th.printStackTrace(printWriter);
        } catch (Throwable unused) {
        }
        printWriter.flush();
        return aVar.toString();
    }

    /* compiled from: StackTraceUtils.java */
    /* loaded from: classes9.dex */
    private static class a extends StringWriter {

        /* renamed from: a  reason: collision with root package name */
        private static final int f18010a = 4000;

        /* renamed from: b  reason: collision with root package name */
        private int f18011b = 0;

        /* renamed from: c  reason: collision with root package name */
        private boolean f18012c = false;

        @Override // java.io.StringWriter, java.io.Writer
        public void write(String str, int i2, int i3) {
            if (this.f18012c) {
                return;
            }
            int i4 = this.f18011b;
            if (i4 + i3 > 4000) {
                this.f18012c = true;
                return;
            }
            this.f18011b = i4 + i3;
            super.write(str, i2, i3);
        }
    }
}
