package com.bytedance.common.utility;

import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.util.Printer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
/* compiled from: LooperPrinterUtils.java */
/* loaded from: classes2.dex */
public final class j {

    /* renamed from: a  reason: collision with root package name */
    private static final int f5277a = 5;

    /* renamed from: b  reason: collision with root package name */
    private static int f5278b = 5;

    /* renamed from: c  reason: collision with root package name */
    private static b f5279c;

    /* renamed from: d  reason: collision with root package name */
    private static a f5280d;

    /* renamed from: e  reason: collision with root package name */
    private static boolean f5281e;

    /* renamed from: f  reason: collision with root package name */
    private static Printer f5282f;

    /* compiled from: LooperPrinterUtils.java */
    /* loaded from: classes2.dex */
    public interface a {
        void onDuration(long j2);
    }

    private j() {
    }

    /* compiled from: LooperPrinterUtils.java */
    /* loaded from: classes2.dex */
    static class b implements Printer {

        /* renamed from: f  reason: collision with root package name */
        private static final char f5283f = '>';

        /* renamed from: g  reason: collision with root package name */
        private static final char f5284g = '<';

        /* renamed from: a  reason: collision with root package name */
        List<Printer> f5285a = new ArrayList();

        /* renamed from: b  reason: collision with root package name */
        List<Printer> f5286b = new ArrayList();

        /* renamed from: c  reason: collision with root package name */
        List<Printer> f5287c = new ArrayList();

        /* renamed from: d  reason: collision with root package name */
        boolean f5288d = false;

        /* renamed from: e  reason: collision with root package name */
        boolean f5289e = false;

        b() {
        }

        @Override // android.util.Printer
        public void println(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            long currentTimeMillis = j.f5280d != null ? System.currentTimeMillis() : 0L;
            if (str.charAt(0) == '>' && this.f5289e) {
                for (Printer printer : this.f5287c) {
                    if (!this.f5285a.contains(printer)) {
                        this.f5285a.add(printer);
                    }
                }
                this.f5287c.clear();
                this.f5289e = false;
            }
            if (this.f5285a.size() > j.f5278b) {
                Log.e("LooperPrinterUtils", "wrapper contains too many printer,please check if the useless printer have been removed");
            }
            for (Printer printer2 : this.f5285a) {
                if (printer2 != null) {
                    printer2.println(str);
                }
            }
            if (str.charAt(0) == '<' && this.f5288d) {
                for (Printer printer3 : this.f5286b) {
                    this.f5285a.remove(printer3);
                    this.f5287c.remove(printer3);
                }
                this.f5286b.clear();
                this.f5288d = false;
            }
            if (j.f5280d == null || currentTimeMillis <= 0) {
                return;
            }
            j.f5280d.onDuration(System.currentTimeMillis() - currentTimeMillis);
        }
    }

    public static void init() {
        if (f5281e) {
            return;
        }
        f5281e = true;
        f5279c = new b();
        Printer c2 = c();
        f5282f = c2;
        if (c2 != null) {
            f5279c.f5285a.add(f5282f);
        }
        Looper.getMainLooper().setMessageLogging(f5279c);
    }

    public static void release() {
        if (f5281e) {
            f5281e = false;
            Looper.getMainLooper().setMessageLogging(f5282f);
            f5279c = null;
        }
    }

    public static void addMessageLogging(Printer printer) {
        if (printer == null || f5279c.f5287c.contains(printer)) {
            return;
        }
        f5279c.f5287c.add(printer);
        f5279c.f5289e = true;
    }

    public static void removeMessageLogging(Printer printer) {
        if (printer == null || f5279c.f5286b.contains(printer)) {
            return;
        }
        f5279c.f5286b.add(printer);
        f5279c.f5288d = true;
    }

    private static Printer c() {
        try {
            Field declaredField = Class.forName("android.os.Looper").getDeclaredField("mLogging");
            declaredField.setAccessible(true);
            return (Printer) declaredField.get(Looper.getMainLooper());
        } catch (Exception unused) {
            return null;
        }
    }

    public static void setMaxCount(int i2) {
        f5278b = i2;
    }

    public static List<Printer> getPrinters() {
        b bVar = f5279c;
        if (bVar != null) {
            return bVar.f5285a;
        }
        return null;
    }

    public static void setPrinterLisnter(a aVar) {
        f5280d = aVar;
    }
}
