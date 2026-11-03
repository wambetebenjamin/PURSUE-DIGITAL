package com.bytedance.crash.config;

import android.content.Context;
import com.bytedance.crash.Global;
import java.io.File;
/* compiled from: LogPathConfig.java */
/* loaded from: classes2.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6351a = "asdawd";

    /* renamed from: b  reason: collision with root package name */
    public static final String f6352b = "selflib";

    /* renamed from: c  reason: collision with root package name */
    public static final String f6353c = "PriorityMonitor";

    /* renamed from: d  reason: collision with root package name */
    private static final f f6354d = new f();

    /* renamed from: e  reason: collision with root package name */
    private final String f6355e = f();

    /* renamed from: f  reason: collision with root package name */
    private String f6356f = "npth";

    /* renamed from: g  reason: collision with root package name */
    private File f6357g;

    /* renamed from: h  reason: collision with root package name */
    private File f6358h;

    /* renamed from: i  reason: collision with root package name */
    private File f6359i;

    /* renamed from: j  reason: collision with root package name */
    private File f6360j;

    private static f e() {
        return f6354d;
    }

    private void a(String str) {
        this.f6356f = str;
        g();
    }

    private f() {
        g();
        if (this.f6357g.exists()) {
            return;
        }
        this.f6357g.mkdirs();
    }

    private String f() {
        try {
            Context c2 = Global.c();
            return c2 != null ? c2.getFilesDir().getAbsolutePath() : "";
        } catch (Throwable unused) {
            return "";
        }
    }

    private void g() {
        this.f6357g = new File(this.f6355e, this.f6356f);
        this.f6358h = new File(this.f6357g, f6351a);
        this.f6359i = new File(this.f6357g, f6352b);
        this.f6360j = new File(this.f6357g, f6353c);
    }

    public static File a() {
        return e().f6357g;
    }

    public static File b() {
        return e().f6358h;
    }

    public static File c() {
        return e().f6359i;
    }

    public static File d() {
        return e().f6360j;
    }
}
