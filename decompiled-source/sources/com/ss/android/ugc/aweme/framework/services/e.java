package com.ss.android.ugc.aweme.framework.services;

import android.text.TextUtils;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: DowngradeImplManager.java */
/* loaded from: classes9.dex */
public class e {

    /* renamed from: d  reason: collision with root package name */
    private Map<String, j> f18647d = new ConcurrentHashMap();

    /* renamed from: e  reason: collision with root package name */
    private Map<String, Object> f18648e = new ConcurrentHashMap();

    /* renamed from: a  reason: collision with root package name */
    final Map<String, String> f18644a = new ConcurrentHashMap();

    /* renamed from: b  reason: collision with root package name */
    final Set<String> f18645b = Collections.synchronizedSet(new HashSet());

    /* renamed from: c  reason: collision with root package name */
    final Set<String> f18646c = Collections.synchronizedSet(new HashSet());

    private void a(String str, String str2) {
        this.f18644a.put(str, str2);
    }

    /* compiled from: DowngradeImplManager.java */
    /* loaded from: classes9.dex */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private static final e f18649a = new e();

        private a() {
        }
    }

    public static e a() {
        return a.f18649a;
    }

    public <T> T a(Class<T> cls) {
        String name = cls.getName();
        j jVar = this.f18647d.get(name);
        if (jVar != null) {
            return (T) jVar.a();
        }
        T t = (T) this.f18648e.get(name);
        return (t != null || this.f18646c.contains(name)) ? t : (T) c(cls);
    }

    private <T> T c(Class<T> cls) {
        this.f18646c.add(cls.getName());
        return null;
    }

    private void a(String str, Object obj) {
        this.f18648e.put(str, obj);
    }

    public <T> void a(Class<T> cls, j<T> jVar) {
        this.f18647d.put(cls.getName(), jVar);
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str) || this.f18645b.contains(str)) {
            return;
        }
        this.f18645b.add(str);
    }

    public boolean b(Class cls) {
        String str = this.f18644a.get(cls.getName());
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return this.f18645b.contains(str);
    }
}
