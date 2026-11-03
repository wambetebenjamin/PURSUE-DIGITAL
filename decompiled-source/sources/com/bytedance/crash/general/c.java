package com.bytedance.crash.general;

import android.os.Build;
import android.text.TextUtils;
import com.bytedance.librarian.c;
import java.io.File;
import java.util.Locale;
import java.util.UUID;
/* compiled from: RomInfoHelper.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6764a = "unknown";

    /* renamed from: b  reason: collision with root package name */
    private static i f6765b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a() {
        String[] strArr = {"/data/local/su", "/data/local/bin/su", "/data/local/xbin/su", "/system/xbin/su", "/system/bin/su", "/system/bin/.ext/su", "/system/bin/failsafe/su", "/system/sd/xbin/su", "/system/usr/we-need-root/su", "/sbin/su", "/su/bin/su"};
        for (int i2 = 0; i2 < 11; i2++) {
            if (new File(strArr[i2]).exists()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean c(String str) {
        try {
            Class.forName(str);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static String b() {
        String str = Build.VERSION.RELEASE;
        return str.contains(c.a.DOT) ? str : str + ".0";
    }

    public static int c() {
        return Build.VERSION.SDK_INT;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String d() {
        try {
            String uuid = UUID.randomUUID().toString();
            return !TextUtils.isEmpty(uuid) ? uuid : "0000-1111-2222-3333-4444";
        } catch (Throwable unused) {
            return "0000-1111-2222-3333-4444";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String e() {
        try {
            return System.getProperty("os.version");
        } catch (Throwable unused) {
            return "unknown";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean f() {
        return c("ohos.utils.system.SystemCapability");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String g() {
        return Build.VERSION.INCREMENTAL == null ? "unknown" : Build.VERSION.INCREMENTAL;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String h() {
        return j().b();
    }

    private static i j() {
        if (f6765b == null) {
            synchronized (i.class) {
                if (f6765b == null) {
                    f6765b = k();
                }
            }
        }
        return f6765b;
    }

    public static boolean i() {
        return j() instanceof g;
    }

    private static i k() {
        C0095c c0095c = new C0095c();
        if (c0095c.a()) {
            return c0095c;
        }
        g gVar = new g();
        if (gVar.a()) {
            return gVar;
        }
        f fVar = new f();
        if (fVar.a()) {
            return fVar;
        }
        b bVar = new b();
        if (bVar.a()) {
            return bVar;
        }
        e eVar = new e();
        if (eVar.a()) {
            return eVar;
        }
        d dVar = new d();
        if (dVar.a()) {
            return dVar;
        }
        j jVar = new j();
        if (jVar.a()) {
            return jVar;
        }
        a aVar = new a();
        if (aVar.a()) {
            return aVar;
        }
        h hVar = new h();
        return hVar.a() ? hVar : new k();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RomInfoHelper.java */
    /* loaded from: classes2.dex */
    public static abstract class i {

        /* renamed from: a  reason: collision with root package name */
        protected static String f6795a = null;

        /* renamed from: b  reason: collision with root package name */
        protected static String f6796b = null;

        /* renamed from: c  reason: collision with root package name */
        protected static final String f6797c = "_";

        /* renamed from: d  reason: collision with root package name */
        protected static final Locale f6798d = Locale.getDefault();

        abstract boolean a();

        i(String str, String str2) {
            f6795a = str;
            f6796b = str2;
        }

        String c() {
            return f6795a;
        }

        String d() {
            return f6796b;
        }

        String b() {
            return Build.DISPLAY == null ? "unknown" : Build.DISPLAY;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RomInfoHelper.java */
    /* loaded from: classes2.dex */
    public static class k extends i {

        /* renamed from: e  reason: collision with root package name */
        private static final String f6801e = "unknown";

        /* renamed from: f  reason: collision with root package name */
        private static final String f6802f = "unknown";

        @Override // com.bytedance.crash.general.c.i
        boolean a() {
            return true;
        }

        k() {
            super("unknown", "unknown");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RomInfoHelper.java */
    /* renamed from: com.bytedance.crash.general.c$c  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class C0095c extends i {

        /* renamed from: e  reason: collision with root package name */
        private static final String f6774e = "huawei";

        /* renamed from: f  reason: collision with root package name */
        private static final String f6775f = "emotionui";

        /* renamed from: g  reason: collision with root package name */
        private static final String f6776g = "magicui";

        /* renamed from: h  reason: collision with root package name */
        private static final String f6777h = "ro.build.version.emui";

        /* renamed from: i  reason: collision with root package name */
        private final String f6778i;

        C0095c() {
            super(f6774e, "emotionui");
            this.f6778i = c.d(f6777h);
        }

        @Override // com.bytedance.crash.general.c.i
        boolean a() {
            if (!TextUtils.isEmpty(this.f6778i)) {
                String lowerCase = this.f6778i.toLowerCase(f6798d);
                if (lowerCase.startsWith("emotionui") || lowerCase.startsWith(f6776g)) {
                    return true;
                }
            }
            try {
                String str = Build.BRAND;
                if (!TextUtils.isEmpty(str)) {
                    if (str.toLowerCase(f6798d).startsWith(f6774e)) {
                        return true;
                    }
                }
            } catch (Throwable unused) {
            }
            try {
                String str2 = Build.MANUFACTURER;
                if (TextUtils.isEmpty(str2)) {
                    return false;
                }
                return str2.toLowerCase(f6798d).startsWith(f6774e);
            } catch (Throwable unused2) {
                return false;
            }
        }

        @Override // com.bytedance.crash.general.c.i
        String b() {
            if (TextUtils.isEmpty(this.f6778i) || !this.f6778i.toLowerCase(f6798d).contains("emotionui")) {
                return Build.DISPLAY == null ? "unknown" : Build.DISPLAY;
            }
            return this.f6778i + "_" + Build.DISPLAY;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RomInfoHelper.java */
    /* loaded from: classes2.dex */
    public static class g extends i {

        /* renamed from: e  reason: collision with root package name */
        private static final String f6788e = "xiaomi";

        /* renamed from: f  reason: collision with root package name */
        private static final String f6789f = "miui";

        /* renamed from: g  reason: collision with root package name */
        private final String f6790g;

        g() {
            super(f6788e, "miui");
            this.f6790g = c.d("ro.miui.ui.version.name");
        }

        @Override // com.bytedance.crash.general.c.i
        boolean a() {
            return c.c("miui.os.Build");
        }

        @Override // com.bytedance.crash.general.c.i
        String b() {
            return "miui_" + this.f6790g + "_" + Build.VERSION.INCREMENTAL;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RomInfoHelper.java */
    /* loaded from: classes2.dex */
    public static class f extends i {

        /* renamed from: e  reason: collision with root package name */
        private static final String f6784e = "vivo";

        /* renamed from: f  reason: collision with root package name */
        private static final String f6785f = "funtouch";

        /* renamed from: g  reason: collision with root package name */
        private final String f6786g;

        /* renamed from: h  reason: collision with root package name */
        private final String f6787h;

        f() {
            super(f6784e, f6785f);
            this.f6786g = c.d("ro.vivo.os.build.display.id");
            this.f6787h = c.d("ro.vivo.product.version");
        }

        @Override // com.bytedance.crash.general.c.i
        boolean a() {
            return !TextUtils.isEmpty(this.f6786g) && this.f6786g.toLowerCase(f6798d).contains(f6785f);
        }

        @Override // com.bytedance.crash.general.c.i
        String b() {
            return this.f6786g + "_" + this.f6787h;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RomInfoHelper.java */
    /* loaded from: classes2.dex */
    public static class b extends i {

        /* renamed from: e  reason: collision with root package name */
        private static final String f6770e = "oppo";

        /* renamed from: f  reason: collision with root package name */
        private static final String f6771f = "coloros";

        /* renamed from: g  reason: collision with root package name */
        private static final String f6772g = "ro.build.version.opporom";

        /* renamed from: h  reason: collision with root package name */
        private final String f6773h;

        b() {
            super(f6770e, "coloros");
            this.f6773h = c.d("ro.build.version.opporom");
        }

        @Override // com.bytedance.crash.general.c.i
        boolean a() {
            return !TextUtils.isEmpty(Build.MANUFACTURER) && Build.MANUFACTURER.toLowerCase(f6798d).contains(f6770e);
        }

        @Override // com.bytedance.crash.general.c.i
        String b() {
            return "coloros_" + this.f6773h + "_" + Build.DISPLAY;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RomInfoHelper.java */
    /* loaded from: classes2.dex */
    public static class e extends i {

        /* renamed from: e  reason: collision with root package name */
        private static final String f6782e = "meizu";

        /* renamed from: f  reason: collision with root package name */
        private static final String f6783f = "flyme";

        e() {
            super(f6782e, "flyme");
        }

        @Override // com.bytedance.crash.general.c.i
        boolean a() {
            if (TextUtils.isEmpty(Build.DISPLAY) || !Build.DISPLAY.toLowerCase(f6798d).contains("flyme")) {
                return !TextUtils.isEmpty(Build.USER) && Build.USER.equals("flyme");
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RomInfoHelper.java */
    /* loaded from: classes2.dex */
    public static class d extends i {

        /* renamed from: e  reason: collision with root package name */
        private static final String f6779e = "letv";

        /* renamed from: f  reason: collision with root package name */
        private static final String f6780f = "eui";

        /* renamed from: g  reason: collision with root package name */
        private final String f6781g;

        d() {
            super(f6779e, f6780f);
            this.f6781g = c.d("ro.letv.release.version");
        }

        @Override // com.bytedance.crash.general.c.i
        boolean a() {
            return !TextUtils.isEmpty(this.f6781g);
        }

        @Override // com.bytedance.crash.general.c.i
        String b() {
            return "eui_" + this.f6781g + "_" + Build.DISPLAY;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RomInfoHelper.java */
    /* loaded from: classes2.dex */
    public static class j extends i {

        /* renamed from: e  reason: collision with root package name */
        private static final String f6799e = "sony";

        /* renamed from: f  reason: collision with root package name */
        private static final String f6800f = "sony";

        j() {
            super("sony", "sony");
        }

        @Override // com.bytedance.crash.general.c.i
        boolean a() {
            String str = Build.BRAND + Build.MANUFACTURER;
            return !TextUtils.isEmpty(str) && str.toLowerCase(f6798d).contains("sony");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RomInfoHelper.java */
    /* loaded from: classes2.dex */
    public static class a extends i {

        /* renamed from: e  reason: collision with root package name */
        private static final String f6766e = "gionee";

        /* renamed from: f  reason: collision with root package name */
        private static final String f6767f = "amigo";

        /* renamed from: g  reason: collision with root package name */
        private static final String f6768g = "ro.gn.sv.version";

        /* renamed from: h  reason: collision with root package name */
        private final String f6769h;

        a() {
            super(f6766e, f6767f);
            this.f6769h = c.d(f6768g);
        }

        @Override // com.bytedance.crash.general.c.i
        boolean a() {
            return !TextUtils.isEmpty(Build.DISPLAY) && Build.DISPLAY.toLowerCase(f6798d).contains(f6767f);
        }

        @Override // com.bytedance.crash.general.c.i
        String b() {
            return Build.DISPLAY + "_" + this.f6769h;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RomInfoHelper.java */
    /* loaded from: classes2.dex */
    public static class h extends i {

        /* renamed from: e  reason: collision with root package name */
        private static final String f6791e = "qiku";

        /* renamed from: f  reason: collision with root package name */
        private static final String f6792f = "360";

        /* renamed from: g  reason: collision with root package name */
        private static final String f6793g = "ro.build.uiversion";

        /* renamed from: h  reason: collision with root package name */
        private final String f6794h;

        h() {
            super(f6791e, f6792f);
            this.f6794h = c.d(f6793g);
        }

        @Override // com.bytedance.crash.general.c.i
        boolean a() {
            String str = Build.MANUFACTURER + Build.BRAND;
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            String lowerCase = str.toLowerCase(f6798d);
            return lowerCase.contains(f6792f) || lowerCase.contains(f6791e);
        }

        @Override // com.bytedance.crash.general.c.i
        String b() {
            return this.f6794h + "_" + Build.DISPLAY;
        }
    }

    public static String a(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", String.class, String.class).invoke(null, str, str2);
        } catch (Exception e2) {
            e2.printStackTrace();
            return str2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String d(String str) {
        return a(str, "");
    }
}
