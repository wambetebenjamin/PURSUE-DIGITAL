package com.bytedance.geckox.clean.cache;

import java.io.File;
import java.util.List;
/* compiled from: CachePolicy.java */
/* loaded from: classes2.dex */
public abstract class b {

    /* renamed from: a  reason: collision with root package name */
    protected a f8981a;

    /* renamed from: b  reason: collision with root package name */
    protected File f8982b;

    /* renamed from: c  reason: collision with root package name */
    protected List<String> f8983c;
    public static final b NONE = new d();
    public static final b LRU = new f();
    public static final b FIFO = new e();

    public abstract void append();

    public abstract void visit(String str);

    public void attach(a aVar, File file, List<String> list) {
        this.f8981a = aVar;
        this.f8982b = file;
        this.f8983c = list;
    }
}
