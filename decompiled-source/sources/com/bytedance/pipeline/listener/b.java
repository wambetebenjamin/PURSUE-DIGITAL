package com.bytedance.pipeline.listener;

import com.bytedance.pipeline.d;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* compiled from: EventListenerWrapper.java */
/* loaded from: classes2.dex */
public final class b extends a {

    /* renamed from: a  reason: collision with root package name */
    private List<a> f10034a;

    public b(a... aVarArr) {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        this.f10034a = copyOnWriteArrayList;
        if (aVarArr == null) {
            return;
        }
        copyOnWriteArrayList.addAll(Arrays.asList(aVarArr));
    }

    public void a(a aVar) {
        if (aVar == null) {
            return;
        }
        this.f10034a.add(aVar);
    }

    public void b(a aVar) {
        if (aVar == null) {
            return;
        }
        this.f10034a.remove(aVar);
    }

    @Override // com.bytedance.pipeline.listener.a
    public <T> void c(com.bytedance.pipeline.b<T> bVar, d dVar) {
        for (a aVar : this.f10034a) {
            if (aVar != null) {
                aVar.c(bVar, dVar);
            }
        }
    }

    @Override // com.bytedance.pipeline.listener.a
    public <T> void b(com.bytedance.pipeline.b<T> bVar, d dVar) {
        for (a aVar : this.f10034a) {
            if (aVar != null) {
                aVar.b(bVar, dVar);
            }
        }
    }

    @Override // com.bytedance.pipeline.listener.a
    public <T> void a(com.bytedance.pipeline.b<T> bVar, d dVar) {
        for (a aVar : this.f10034a) {
            if (aVar != null) {
                aVar.a(bVar, dVar);
            }
        }
    }

    @Override // com.bytedance.pipeline.listener.a
    public <T> void a(com.bytedance.pipeline.b<T> bVar, d dVar, Throwable th) {
        for (a aVar : this.f10034a) {
            if (aVar != null) {
                aVar.a(bVar, dVar, th);
            }
        }
    }

    @Override // com.bytedance.pipeline.listener.a
    public <T> void f(com.bytedance.pipeline.b<T> bVar, d dVar, Throwable th) {
        for (a aVar : this.f10034a) {
            if (aVar != null) {
                aVar.f(bVar, dVar, th);
            }
        }
    }

    @Override // com.bytedance.pipeline.listener.a
    public <T> void b(com.bytedance.pipeline.b<T> bVar, d dVar, Throwable th) {
        for (a aVar : this.f10034a) {
            if (aVar != null) {
                aVar.b(bVar, dVar, th);
            }
        }
    }
}
