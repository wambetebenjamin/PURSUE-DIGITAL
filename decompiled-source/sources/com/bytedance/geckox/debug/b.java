package com.bytedance.geckox.debug;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.geckox.net.c;
import java.io.File;
import java.util.Arrays;
import java.util.List;
/* compiled from: GeckoDebugConfig.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private final c f9004a;

    /* renamed from: b  reason: collision with root package name */
    private final List<Pair<String, a>> f9005b;

    /* renamed from: c  reason: collision with root package name */
    private final Long f9006c;

    /* renamed from: d  reason: collision with root package name */
    private final String f9007d;

    /* renamed from: e  reason: collision with root package name */
    private final String f9008e;

    /* renamed from: f  reason: collision with root package name */
    private final String f9009f;

    /* renamed from: g  reason: collision with root package name */
    private final String f9010g;

    /* renamed from: h  reason: collision with root package name */
    private final File f9011h;

    /* renamed from: i  reason: collision with root package name */
    private final String[] f9012i;

    /* renamed from: j  reason: collision with root package name */
    private final Application f9013j;
    private final String k;

    private b(C0131b c0131b) {
        Application application = c0131b.f9027i;
        this.f9013j = application;
        if (application == null) {
            throw new IllegalArgumentException("application is empty");
        }
        List<Pair<String, a>> list = c0131b.f9020b;
        this.f9005b = list;
        Long l = c0131b.f9021c;
        this.f9006c = l;
        String str = c0131b.f9022d;
        this.f9007d = str;
        this.f9009f = c0131b.f9025g;
        this.f9010g = c0131b.f9026h;
        this.f9011h = c0131b.f9024f;
        String[] strArr = c0131b.f9028j;
        this.f9012i = strArr;
        String str2 = c0131b.k;
        this.k = str2;
        if (list == null || list.isEmpty()) {
            throw new IllegalArgumentException("access key empty");
        }
        if (l == null) {
            throw new IllegalArgumentException("appId == null");
        }
        if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("version == null");
        }
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("deviceId key empty");
        }
        this.f9004a = c0131b.f9019a;
        String str3 = c0131b.f9023e;
        this.f9008e = str3;
        if (TextUtils.isEmpty(str3)) {
            throw new IllegalArgumentException("host == empty");
        }
        if (strArr == null || strArr.length <= 0) {
            throw new IllegalArgumentException("groupType == empty");
        }
    }

    public Context a() {
        return this.f9013j;
    }

    public String b() {
        return this.k;
    }

    public String[] c() {
        String[] strArr = new String[this.f9005b.size()];
        for (int i2 = 0; i2 < this.f9005b.size(); i2++) {
            strArr[i2] = (String) this.f9005b.get(i2).first;
        }
        return strArr;
    }

    public List<String> d() {
        String[] strArr = new String[this.f9005b.size()];
        for (int i2 = 0; i2 < this.f9005b.size(); i2++) {
            strArr[i2] = (String) this.f9005b.get(i2).first;
        }
        return Arrays.asList(strArr);
    }

    public String[] e() {
        return this.f9012i;
    }

    public List<Pair<String, a>> f() {
        return this.f9005b;
    }

    public a a(String str) {
        for (int i2 = 0; i2 < this.f9005b.size(); i2++) {
            if (TextUtils.equals(str, (CharSequence) this.f9005b.get(i2).first)) {
                return (a) this.f9005b.get(i2).second;
            }
        }
        return null;
    }

    public String b(String str) {
        for (int i2 = 0; i2 < this.f9005b.size(); i2++) {
            if (TextUtils.equals(str, (CharSequence) this.f9005b.get(i2).first)) {
                return ((a) this.f9005b.get(i2).second).a();
            }
        }
        return "";
    }

    public c g() {
        return this.f9004a;
    }

    public String h() {
        return this.f9008e;
    }

    public long i() {
        return this.f9006c.longValue();
    }

    public String j() {
        return this.f9010g;
    }

    public String k() {
        return this.f9009f;
    }

    public File l() {
        return this.f9011h;
    }

    public String m() {
        return this.f9007d;
    }

    /* compiled from: GeckoDebugConfig.java */
    /* loaded from: classes2.dex */
    public enum a {
        ONLINE(1, "online"),
        LOCAL_TEST(0, "local test");
        

        /* renamed from: c  reason: collision with root package name */
        int f9017c;

        /* renamed from: d  reason: collision with root package name */
        String f9018d;

        public String a() {
            return this.f9018d;
        }

        a(int i2, String str) {
            this.f9017c = i2;
            this.f9018d = str;
        }
    }

    /* compiled from: GeckoDebugConfig.java */
    /* renamed from: com.bytedance.geckox.debug.b$b  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class C0131b {

        /* renamed from: a  reason: collision with root package name */
        private c f9019a;

        /* renamed from: b  reason: collision with root package name */
        private List<Pair<String, a>> f9020b;

        /* renamed from: c  reason: collision with root package name */
        private Long f9021c;

        /* renamed from: d  reason: collision with root package name */
        private String f9022d;

        /* renamed from: e  reason: collision with root package name */
        private String f9023e;

        /* renamed from: f  reason: collision with root package name */
        private File f9024f;

        /* renamed from: g  reason: collision with root package name */
        private String f9025g;

        /* renamed from: h  reason: collision with root package name */
        private String f9026h;

        /* renamed from: i  reason: collision with root package name */
        private Application f9027i;

        /* renamed from: j  reason: collision with root package name */
        private String[] f9028j;
        private String k;

        public C0131b(Application application) {
            this.f9027i = application;
        }

        public C0131b a(Pair<String, a>... pairArr) {
            this.f9020b = Arrays.asList(pairArr);
            return this;
        }

        public C0131b a(List<Pair<String, a>> list) {
            this.f9020b = list;
            return this;
        }

        public C0131b a(long j2) {
            this.f9021c = Long.valueOf(j2);
            return this;
        }

        public C0131b a(String str) {
            this.f9022d = str;
            return this;
        }

        public C0131b b(String str) {
            this.k = str;
            return this;
        }

        public C0131b c(String str) {
            this.f9023e = str;
            return this;
        }

        public C0131b d(String str) {
            this.f9026h = str;
            return this;
        }

        public C0131b e(String str) {
            this.f9025g = str;
            return this;
        }

        public C0131b a(String... strArr) {
            this.f9028j = strArr;
            return this;
        }

        public C0131b a(c cVar) {
            this.f9019a = cVar;
            return this;
        }

        public C0131b a(File file) {
            this.f9024f = file;
            return this;
        }

        public b a() {
            return new b(this);
        }
    }
}
