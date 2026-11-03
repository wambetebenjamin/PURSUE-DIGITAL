package com.bytedance.crash.tracker;

import android.text.TextUtils;
import com.bytedance.crash.Global;
import com.bytedance.crash.util.e;
import com.bytedance.crash.util.k;
import com.bytedance.crash.util.m;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
/* compiled from: ProcessTracker.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final int f7181a = 5;

    /* renamed from: b  reason: collision with root package name */
    private static final String f7182b = "processTrack";

    /* renamed from: c  reason: collision with root package name */
    private static volatile boolean f7183c = false;

    /* renamed from: d  reason: collision with root package name */
    private static File f7184d;

    /* renamed from: e  reason: collision with root package name */
    private static File f7185e;

    /* renamed from: f  reason: collision with root package name */
    private static File f7186f;

    /* renamed from: g  reason: collision with root package name */
    private static File f7187g;

    /* renamed from: i  reason: collision with root package name */
    private static c f7188i = new c();

    /* renamed from: h  reason: collision with root package name */
    private boolean f7189h;

    private c() {
    }

    public static c a() {
        return f7188i;
    }

    public void b() {
        if (com.bytedance.crash.util.b.b()) {
            com.bytedance.crash.runtime.b.a(new Runnable() { // from class: com.bytedance.crash.tracker.c.1
                @Override // java.lang.Runnable
                public void run() {
                    c.this.f();
                }
            }, 10000L);
        }
    }

    private void e() {
        if (f7183c) {
            return;
        }
        File file = new File(Global.e(), f7182b);
        f7184d = file;
        k.b(file);
        File file2 = new File(f7184d, String.valueOf(Global.g() / 86400000));
        f7185e = file2;
        k.b(file2);
        File file3 = new File(f7185e, com.bytedance.crash.util.b.a());
        f7186f = file3;
        k.b(file3);
        f7183c = true;
    }

    public void a(boolean z) {
        if (z) {
            this.f7189h = true;
        }
    }

    public boolean c() {
        return this.f7189h;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        File[] listFiles;
        if (com.bytedance.crash.util.b.b()) {
            e();
            File file = f7184d;
            if (file == null || (listFiles = file.listFiles()) == null) {
                return;
            }
            try {
                Arrays.sort(listFiles, new Comparator<File>() { // from class: com.bytedance.crash.tracker.c.2
                    @Override // java.util.Comparator
                    /* renamed from: a */
                    public int compare(File file2, File file3) {
                        return file2.getName().compareTo(file3.getName());
                    }
                });
            } catch (Exception unused) {
            }
            int g2 = (int) (Global.g() / 86400000);
            for (File file2 : listFiles) {
                try {
                    if (Math.abs(g2 - Integer.valueOf(file2.getName()).intValue()) >= 5) {
                        m.deleteFile(file2);
                    }
                } catch (Exception unused2) {
                }
            }
            com.bytedance.crash.runtime.b.a(new Runnable() { // from class: com.bytedance.crash.tracker.c.3
                @Override // java.lang.Runnable
                public void run() {
                    c.this.f();
                }
            }, 120000L);
        }
    }

    public void a(String str, String str2, long j2) {
        try {
            File d2 = d();
            if (d2 != null) {
                m.writeFile(d2, str + ' ' + str2 + ' ' + e.getFileDateInstance().format(new Date(j2)) + '\n', true);
            }
        } catch (Throwable unused) {
        }
    }

    public File d() {
        File file = f7187g;
        if (file != null) {
            return file;
        }
        e();
        if (f7186f != null) {
            File file2 = new File(f7186f, String.valueOf(Global.g()));
            f7187g = file2;
            return file2;
        }
        return f7187g;
    }

    public File a(String str, long j2) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String valueOf = String.valueOf(j2 / 86400000);
        return new File(Global.e(), f7182b + File.separator + valueOf + File.separator + str + File.separator + String.valueOf(j2));
    }
}
