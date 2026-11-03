package com.bytedance.common.wschannel;

import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* compiled from: ChannelInfo.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    final int f5372a;

    /* renamed from: b  reason: collision with root package name */
    String f5373b;

    /* renamed from: c  reason: collision with root package name */
    String f5374c;

    /* renamed from: d  reason: collision with root package name */
    Map<String, String> f5375d;

    /* renamed from: e  reason: collision with root package name */
    Map<String, String> f5376e;

    /* renamed from: f  reason: collision with root package name */
    List<String> f5377f;

    /* renamed from: g  reason: collision with root package name */
    int f5378g;

    /* renamed from: h  reason: collision with root package name */
    int f5379h;

    /* renamed from: i  reason: collision with root package name */
    String f5380i;

    /* renamed from: j  reason: collision with root package name */
    int f5381j;
    boolean k;
    List<Integer> l;
    String m;
    k n;
    boolean o;

    public b(int i2, int i3, int i4, String str, int i5, String str2, String str3, List<String> list, Map<String, String> map, boolean z, List<Integer> list2, String str4, k kVar, boolean z2, a aVar) {
        this.f5375d = new HashMap();
        this.f5376e = new HashMap();
        this.f5377f = new ArrayList();
        this.l = new ArrayList();
        this.f5372a = i5;
        this.f5373b = str2;
        this.f5374c = str3;
        this.f5381j = i4;
        if (list != null) {
            this.f5377f.addAll(list);
        }
        if (map != null) {
            this.f5375d.putAll(map);
        }
        if (aVar.f5383b != null) {
            this.f5376e.putAll(aVar.f5383b);
        }
        if (com.bytedance.common.wschannel.server.d.b()) {
            this.f5376e.put("x-support-qos2", "1");
        }
        this.f5378g = i2;
        this.f5379h = i3;
        this.f5380i = str;
        this.l = list2;
        this.k = z;
        this.m = str4;
        this.n = kVar;
        if (TextUtils.isEmpty(str4)) {
            this.k = false;
        }
        this.o = z2;
    }

    public b(b bVar) {
        this.f5375d = new HashMap();
        this.f5376e = new HashMap();
        this.f5377f = new ArrayList();
        this.l = new ArrayList();
        this.f5372a = bVar.f5372a;
        this.f5373b = bVar.f5373b;
        this.f5374c = bVar.f5374c;
        List<String> list = bVar.f5377f;
        if (list != null) {
            this.f5377f.addAll(list);
        }
        Map<String, String> map = bVar.f5375d;
        if (map != null) {
            this.f5375d.putAll(map);
        }
        Map<String, String> map2 = bVar.f5376e;
        if (map2 != null) {
            this.f5376e.putAll(map2);
        }
        this.f5378g = bVar.f5378g;
        this.f5379h = bVar.f5379h;
        this.f5380i = bVar.f5380i;
        this.f5381j = bVar.f5381j;
        this.k = bVar.k;
        this.n = bVar.n;
        this.m = bVar.m;
        List<Integer> list2 = bVar.l;
        if (list2 != null) {
            this.l.addAll(list2);
        }
        this.o = bVar.o;
    }

    public String toString() {
        return "ChannelInfo{channelId = " + this.f5372a + ", deviceId = " + this.f5373b + ", installId = " + this.f5374c + ", fpid = " + this.f5378g + ", aid = " + this.f5379h + ", updateVersionCode = " + this.f5381j + ", appKey = " + this.f5380i + ", header = " + this.f5376e + ", extra = " + this.f5375d + ", urls = " + this.f5377f + "}";
    }

    /* compiled from: ChannelInfo.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: d  reason: collision with root package name */
        boolean f5385d;

        /* renamed from: f  reason: collision with root package name */
        String f5387f;

        /* renamed from: h  reason: collision with root package name */
        boolean f5389h;

        /* renamed from: i  reason: collision with root package name */
        private final int f5390i;

        /* renamed from: j  reason: collision with root package name */
        private int f5391j;
        private int k;
        private int l;
        private String m;
        private String n;
        private String o;

        /* renamed from: a  reason: collision with root package name */
        Map<String, String> f5382a = new HashMap();

        /* renamed from: b  reason: collision with root package name */
        Map<String, String> f5383b = new HashMap();

        /* renamed from: c  reason: collision with root package name */
        List<String> f5384c = new ArrayList();

        /* renamed from: e  reason: collision with root package name */
        List<Integer> f5386e = new ArrayList();

        /* renamed from: g  reason: collision with root package name */
        k f5388g = k.TUDP;

        a(int i2) {
            this.f5390i = i2;
        }

        public static a a(int i2) {
            return new a(i2);
        }

        public a b(int i2) {
            this.k = i2;
            return this;
        }

        public a c(int i2) {
            this.f5391j = i2;
            return this;
        }

        public a d(int i2) {
            this.l = i2;
            return this;
        }

        public a a(String str) {
            this.m = str;
            return this;
        }

        public a b(String str) {
            this.n = str;
            return this;
        }

        public a c(String str) {
            this.o = str;
            return this;
        }

        public a a(String str, String str2) {
            if (!com.bytedance.common.utility.o.isEmpty(str)) {
                this.f5382a.put(str, str2);
            }
            return this;
        }

        public a a(Map<String, String> map) {
            if (map != null) {
                this.f5382a.putAll(map);
            }
            return this;
        }

        public a b(String str, String str2) {
            if (!com.bytedance.common.utility.o.isEmpty(str)) {
                this.f5383b.put(str, str2);
            }
            return this;
        }

        public a b(Map<String, String> map) {
            if (map != null) {
                this.f5383b.putAll(map);
            }
            return this;
        }

        public a a(List<String> list) {
            if (list != null) {
                this.f5384c.addAll(list);
            }
            return this;
        }

        public a a(boolean z) {
            this.f5385d = z;
            return this;
        }

        public a b(List<Integer> list) {
            if (list != null) {
                this.f5386e.addAll(list);
            }
            return this;
        }

        public a d(String str) {
            try {
                if (str.startsWith("wss:")) {
                    str = "https:" + str.substring(4);
                } else if (str.startsWith("ws:")) {
                    str = "http:" + str.substring(3);
                }
                new URI(str);
                this.f5387f = str;
            } catch (Throwable unused) {
                this.f5387f = null;
                Logger.e("ChannelInfo", "The private protocol url is invalid.");
            }
            return this;
        }

        public a a(k kVar) {
            this.f5388g = kVar;
            return this;
        }

        public a b(boolean z) {
            this.f5389h = z;
            return this;
        }

        public b a() {
            return new b(this.k, this.f5391j, this.l, this.m, this.f5390i, this.n, this.o, this.f5384c, this.f5382a, this.f5385d, this.f5386e, this.f5387f, this.f5388g, this.f5389h, this);
        }
    }
}
