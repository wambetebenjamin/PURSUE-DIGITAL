package com.bytedance.applog.priority;

import android.text.TextUtils;
import com.bytedance.librarian.c;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* compiled from: EventPriorityItem.java */
/* loaded from: classes.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    int f3111a;

    /* renamed from: b  reason: collision with root package name */
    int f3112b;

    /* renamed from: c  reason: collision with root package name */
    int f3113c;

    /* renamed from: d  reason: collision with root package name */
    int f3114d;

    /* renamed from: e  reason: collision with root package name */
    long f3115e;

    /* renamed from: f  reason: collision with root package name */
    Map<String, List<Set<String>>> f3116f;

    /* renamed from: g  reason: collision with root package name */
    Map<String, List<Map<String, Object>>> f3117g;

    /* renamed from: h  reason: collision with root package name */
    String f3118h;

    /* renamed from: i  reason: collision with root package name */
    String f3119i;

    /* renamed from: j  reason: collision with root package name */
    String[] f3120j = null;
    boolean k = false;
    com.bytedance.applog.throttle.a l;

    public int a() {
        return this.f3111a;
    }

    public int b() {
        return this.f3112b;
    }

    public int c() {
        return this.f3113c;
    }

    public long d() {
        return this.f3115e;
    }

    public com.bytedance.applog.throttle.a e() {
        return this.l;
    }

    public String[] a(String[] strArr, String str) {
        String[] strArr2;
        if (TextUtils.isEmpty(this.f3118h) && TextUtils.isEmpty(this.f3119i)) {
            return null;
        }
        if (this.f3120j == null) {
            this.f3120j = new String[strArr.length];
            for (int i2 = 0; i2 < strArr.length; i2++) {
                int indexOf = strArr[i2].indexOf(str);
                if (indexOf > 0) {
                    String substring = strArr[i2].substring(0, indexOf);
                    String substring2 = strArr[i2].substring(indexOf);
                    if (!TextUtils.isEmpty(this.f3118h) && this.f3118h.startsWith("https://") && !this.f3118h.endsWith(c.a.SEPARATOR)) {
                        substring = this.f3118h;
                    }
                    if (!TextUtils.isEmpty(this.f3119i) && this.f3119i.startsWith(c.a.SEPARATOR) && this.f3119i.endsWith(c.a.SEPARATOR)) {
                        substring2 = this.f3119i;
                    }
                    this.f3120j[i2] = substring + substring2;
                } else {
                    this.f3120j[i2] = strArr[i2];
                }
            }
        }
        if (this.k || (strArr2 = this.f3120j) == null || strArr2.length <= 0) {
            return null;
        }
        return strArr2;
    }

    public void a(int i2) {
        this.k = com.bytedance.applog.server.a.b(i2);
    }

    public boolean f() {
        return this.f3111a == -1;
    }
}
