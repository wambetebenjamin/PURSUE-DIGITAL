package com.bytedance.geckox.loader;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.geckox.d;
import com.bytedance.librarian.c;
import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
/* compiled from: VersionedResLoader.java */
/* loaded from: classes2.dex */
public class b {

    /* renamed from: a  reason: collision with root package name */
    private final Map<String, a> f9198a;

    /* renamed from: b  reason: collision with root package name */
    private String f9199b;

    /* renamed from: c  reason: collision with root package name */
    private AtomicBoolean f9200c;

    /* renamed from: d  reason: collision with root package name */
    private String f9201d;

    public b(Context context, String str) {
        this(context, str, new File(context.getFilesDir(), d.GECKO_ROOT_DIR));
    }

    public b(Context context, String str, File file) {
        this.f9198a = new HashMap();
        this.f9200c = new AtomicBoolean(false);
        if (TextUtils.isEmpty(str)) {
            throw new RuntimeException("access key empty");
        }
        this.f9199b = str;
        if (file == null) {
            this.f9201d = new File(context.getFilesDir(), d.GECKO_ROOT_DIR + File.separator + str).getAbsolutePath();
        } else {
            this.f9201d = new File(file, str).getAbsolutePath();
        }
    }

    public String getResRootDir() {
        return this.f9201d;
    }

    public InputStream getInputStream(String str) {
        if (this.f9200c.get()) {
            throw new RuntimeException("released");
        }
        if (TextUtils.isEmpty(str)) {
            throw new RuntimeException("relativePath empty");
        }
        return a(str.trim()).a(str);
    }

    public boolean exist(String str) {
        if (this.f9200c.get()) {
            throw new RuntimeException("released");
        }
        if (TextUtils.isEmpty(str)) {
            throw new RuntimeException("relativePath empty");
        }
        return a(str.trim()).exist(str);
    }

    private a a(String str) {
        a aVar;
        int indexOf = str.indexOf(c.a.SEPARATOR);
        if (indexOf == -1) {
            new RuntimeException("channel is needed：" + str);
        }
        String substring = str.substring(0, indexOf);
        synchronized (this.f9198a) {
            aVar = this.f9198a.get(substring);
            if (aVar == null) {
                aVar = new a(this.f9199b, this.f9201d, substring);
                this.f9198a.put(substring, aVar);
            }
        }
        return aVar;
    }

    public Map<String, Long> getChannelVersion() {
        HashMap hashMap = new HashMap();
        synchronized (this.f9198a) {
            Collection<a> values = this.f9198a.values();
            if (values == null) {
                return hashMap;
            }
            for (a aVar : values) {
                hashMap.put(aVar.b(), aVar.a());
            }
            return hashMap;
        }
    }

    public void release() {
        if (this.f9200c.getAndSet(true)) {
            return;
        }
        com.bytedance.geckox.logger.b.a("Loader", "release version res loader");
        a();
    }

    private void a() {
        synchronized (this.f9198a) {
            for (a aVar : this.f9198a.values()) {
                aVar.release();
            }
            this.f9198a.clear();
        }
    }
}
