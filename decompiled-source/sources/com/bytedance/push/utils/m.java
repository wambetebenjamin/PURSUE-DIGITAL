package com.bytedance.push.utils;

import android.os.SystemProperties;
import android.text.TextUtils;
import com.bytedance.librarian.c;
/* compiled from: RomUtils.java */
/* loaded from: classes2.dex */
public class m {
    private static volatile m m;

    /* renamed from: a  reason: collision with root package name */
    String f12050a;

    /* renamed from: b  reason: collision with root package name */
    String f12051b;

    /* renamed from: c  reason: collision with root package name */
    String f12052c;

    /* renamed from: d  reason: collision with root package name */
    String f12053d;
    private volatile Object l;

    /* renamed from: e  reason: collision with root package name */
    private String f12054e = "RomUtils";
    public final String RUNTIME_OP = "ro.build.version.opporom";

    /* renamed from: f  reason: collision with root package name */
    private final String f12055f = "ro.vivo.os.build.display.id";

    /* renamed from: g  reason: collision with root package name */
    private final String f12056g = "ro.build.version.emui";

    /* renamed from: h  reason: collision with root package name */
    private final String f12057h = "oppo";

    /* renamed from: i  reason: collision with root package name */
    private double f12058i = -1.0d;

    /* renamed from: j  reason: collision with root package name */
    private double f12059j = -1.0d;
    private double k = -1.0d;

    public static m getInstance() {
        if (m == null) {
            synchronized (m.class) {
                if (m == null) {
                    m = new m();
                }
            }
        }
        return m;
    }

    private m() {
    }

    private Object a() {
        if (this.l == null) {
            synchronized (m.class) {
                if (this.l == null) {
                    this.l = Class.forName("android.os.SystemProperties").newInstance();
                }
            }
        }
        return this.l;
    }

    public boolean isMiOS() {
        if (TextUtils.isEmpty(this.f12050a)) {
            this.f12050a = SystemProperties.get("ro.miui.ui.version.name", "");
        }
        return !TextUtils.isEmpty(this.f12050a);
    }

    public boolean isMi12() {
        return b() == 12;
    }

    public boolean isHigherMi12() {
        return b() > 12;
    }

    private int b() {
        if (isMiOS()) {
            try {
                if (!TextUtils.isEmpty(this.f12050a) && this.f12050a.length() >= 2) {
                    String substring = this.f12050a.substring(1);
                    if (substring.indexOf(c.a.DOT) > 0) {
                        substring = substring.substring(0, substring.indexOf(c.a.DOT));
                    }
                    int parseInt = Integer.parseInt(substring);
                    return parseInt >= 100 ? parseInt / 10 : parseInt;
                }
            } catch (Throwable unused) {
            }
            return -1;
        }
        return -1;
    }

    public boolean isColorOS() {
        if (TextUtils.isEmpty(this.f12051b)) {
            this.f12051b = a("ro.build.version.opporom");
        }
        return !TextUtils.isEmpty(this.f12051b);
    }

    public boolean isHigherColor7() {
        try {
            if (isColorOS()) {
                if (this.f12059j == -1.0d && !TextUtils.isEmpty(this.f12051b) && this.f12051b.length() >= 2) {
                    String substring = this.f12051b.substring(1);
                    if (substring.indexOf(c.a.DOT) > 0) {
                        substring = substring.substring(0, substring.indexOf(c.a.DOT));
                    }
                    this.f12059j = Double.parseDouble(substring);
                }
                return this.f12059j >= 7.0d;
            }
            return false;
        } catch (Throwable unused) {
            return false;
        }
    }

    public boolean isFuntouchOS() {
        if (TextUtils.isEmpty(this.f12052c)) {
            this.f12052c = a("ro.vivo.os.build.display.id");
        }
        return !TextUtils.isEmpty(this.f12052c);
    }

    public boolean isLessFuntouch4() {
        if (isFuntouchOS()) {
            try {
                if (this.k == -1.0d && !TextUtils.isEmpty(this.f12052c) && this.f12052c.length() >= 2) {
                    String str = this.f12052c;
                    String substring = str.substring(str.indexOf("_") + 1);
                    if (substring.indexOf(c.a.DOT) > 0) {
                        substring = substring.substring(0, substring.indexOf(c.a.DOT));
                    }
                    this.k = Double.parseDouble(substring);
                }
                return this.k <= 4.0d;
            } catch (Throwable unused) {
                return false;
            }
        }
        return false;
    }

    public boolean isEMUI() {
        if (TextUtils.isEmpty(this.f12053d)) {
            this.f12053d = a("ro.build.version.emui");
        }
        return !TextUtils.isEmpty(this.f12053d);
    }

    public boolean isLessEmui5() {
        if (isEMUI()) {
            try {
                if (this.f12058i == -1.0d) {
                    if (TextUtils.isEmpty(this.f12053d)) {
                        return false;
                    }
                    String str = this.f12053d;
                    String substring = str.substring(str.indexOf("_") + 1);
                    if (substring.indexOf(c.a.DOT) > 0) {
                        substring = substring.substring(0, substring.indexOf(c.a.DOT));
                    }
                    this.f12058i = Double.parseDouble(substring);
                }
                return this.f12058i < 5.0d;
            } catch (Throwable unused) {
                return false;
            }
        }
        return false;
    }

    public boolean isHigherEmui10() {
        if (isEMUI()) {
            try {
                if (this.f12058i == -1.0d) {
                    if (TextUtils.isEmpty(this.f12053d)) {
                        return false;
                    }
                    String str = this.f12053d;
                    String substring = str.substring(str.indexOf("_") + 1);
                    if (substring.indexOf(c.a.DOT) > 0) {
                        substring = substring.substring(0, substring.indexOf(c.a.DOT));
                    }
                    this.f12058i = Double.parseDouble(substring);
                }
                return this.f12058i >= 10.0d;
            } catch (Throwable unused) {
                return false;
            }
        }
        return false;
    }

    public String a(String str) {
        try {
            return SystemProperties.get(str);
        } catch (Throwable th) {
            th.printStackTrace();
            try {
                Object a2 = a();
                return (String) a2.getClass().getMethod("get", String.class).invoke(a2, str);
            } catch (IllegalArgumentException e2) {
                throw e2;
            } catch (Throwable unused) {
                return "";
            }
        }
    }
}
