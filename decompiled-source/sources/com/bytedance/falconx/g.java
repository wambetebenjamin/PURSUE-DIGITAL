package com.bytedance.falconx;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
/* compiled from: WebOfflineConfig.java */
/* loaded from: classes2.dex */
public class g {
    private static int l;

    /* renamed from: a  reason: collision with root package name */
    private final Context f7368a;

    /* renamed from: b  reason: collision with root package name */
    private final String f7369b;

    /* renamed from: c  reason: collision with root package name */
    private final List<Pattern> f7370c;

    /* renamed from: d  reason: collision with root package name */
    private final List<Uri> f7371d;

    /* renamed from: e  reason: collision with root package name */
    private final List<e> f7372e;

    /* renamed from: f  reason: collision with root package name */
    private final String f7373f;

    /* renamed from: g  reason: collision with root package name */
    private final com.bytedance.falconx.statistic.a f7374g;

    /* renamed from: h  reason: collision with root package name */
    private final boolean f7375h;

    /* renamed from: i  reason: collision with root package name */
    private final String f7376i;

    /* renamed from: j  reason: collision with root package name */
    private final String f7377j;
    private final String k;

    private g(a aVar) {
        if (aVar.f7378a == null) {
            throw new IllegalArgumentException("context == null");
        }
        Context applicationContext = aVar.f7378a.getApplicationContext();
        if (applicationContext != null) {
            this.f7368a = applicationContext;
        } else {
            this.f7368a = aVar.f7378a;
        }
        if (TextUtils.isEmpty(aVar.f7380c)) {
            this.f7376i = com.bytedance.geckox.utils.a.getVersion(this.f7368a);
        } else {
            this.f7376i = aVar.f7380c;
        }
        if (TextUtils.isEmpty(aVar.f7379b)) {
            throw new IllegalArgumentException("accessKey empty");
        }
        this.f7369b = aVar.f7379b;
        if (TextUtils.isEmpty(aVar.f7387j)) {
            throw new IllegalArgumentException("host empty");
        }
        this.f7377j = aVar.f7387j;
        if (aVar.l != 0) {
            l = aVar.l;
        }
        this.f7370c = aVar.f7384g;
        this.f7372e = aVar.f7381d;
        if (aVar.f7385h == null) {
            this.f7371d = Arrays.asList(Uri.fromFile(new File(this.f7368a.getFilesDir(), com.bytedance.geckox.d.GECKO_ROOT_DIR)));
        } else {
            this.f7371d = aVar.f7385h;
        }
        this.f7373f = aVar.f7386i;
        this.f7374g = aVar.f7382e;
        String str = aVar.k;
        this.k = str;
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("region == null");
        }
        this.f7375h = aVar.f7383f;
    }

    public Context a() {
        return this.f7368a;
    }

    public List<Pattern> b() {
        return this.f7370c;
    }

    public List<Uri> c() {
        return this.f7371d;
    }

    public String d() {
        return this.f7369b;
    }

    public boolean e() {
        return this.f7375h;
    }

    public String f() {
        return this.f7377j;
    }

    public com.bytedance.falconx.statistic.a g() {
        return this.f7374g;
    }

    public List<e> h() {
        return this.f7372e;
    }

    public String i() {
        return this.f7373f;
    }

    public String j() {
        return this.f7376i;
    }

    public String k() {
        return this.k;
    }

    public static int l() {
        return l;
    }

    /* compiled from: WebOfflineConfig.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private Context f7378a;

        /* renamed from: b  reason: collision with root package name */
        private String f7379b;

        /* renamed from: c  reason: collision with root package name */
        private String f7380c;

        /* renamed from: d  reason: collision with root package name */
        private List<e> f7381d;

        /* renamed from: e  reason: collision with root package name */
        private com.bytedance.falconx.statistic.a f7382e;

        /* renamed from: f  reason: collision with root package name */
        private boolean f7383f = true;

        /* renamed from: g  reason: collision with root package name */
        private List<Pattern> f7384g;

        /* renamed from: h  reason: collision with root package name */
        private List<Uri> f7385h;

        /* renamed from: i  reason: collision with root package name */
        private String f7386i;

        /* renamed from: j  reason: collision with root package name */
        private String f7387j;
        private String k;
        private int l;

        public a(Context context) {
            this.f7378a = context;
        }

        public a a(List<Pattern> list) {
            this.f7384g = list;
            return this;
        }

        public a b(List<Uri> list) {
            this.f7385h = list;
            return this;
        }

        public a c(List<e> list) {
            this.f7381d = list;
            return this;
        }

        public a a(com.bytedance.falconx.statistic.a aVar) {
            this.f7382e = aVar;
            return this;
        }

        public a a(boolean z) {
            this.f7383f = z;
            return this;
        }

        public a a(String str) {
            this.f7380c = str;
            return this;
        }

        public a b(String str) {
            this.f7387j = str;
            return this;
        }

        public a c(String str) {
            this.f7379b = str;
            return this;
        }

        public a d(String str) {
            this.f7386i = str;
            return this;
        }

        public a e(String str) {
            this.k = str;
            return this;
        }

        public a a(int i2) {
            this.l = i2;
            return this;
        }

        public g a() {
            return new g(this);
        }
    }
}
