package com.bytedance.pipeline.listener;

import com.bytedance.pipeline.d;
/* compiled from: EventListener.java */
/* loaded from: classes2.dex */
public abstract class a {
    public <T> void a(com.bytedance.pipeline.b<T> bVar, d dVar) {
    }

    public <T> void a(com.bytedance.pipeline.b<T> bVar, d dVar, Throwable th) {
    }

    public <T> void b(com.bytedance.pipeline.b<T> bVar, d dVar) {
    }

    public <T> void b(com.bytedance.pipeline.b<T> bVar, d dVar, Throwable th) {
    }

    public <T> void c(com.bytedance.pipeline.b<T> bVar, d dVar) {
    }

    public <T> void f(com.bytedance.pipeline.b<T> bVar, d dVar, Throwable th) {
    }

    public final void d(com.bytedance.pipeline.b bVar, d dVar) {
        c(bVar, dVar);
    }

    public final void e(com.bytedance.pipeline.b bVar, d dVar) {
        b(bVar, dVar);
    }

    public final void f(com.bytedance.pipeline.b bVar, d dVar) {
        a(bVar, dVar);
    }

    public final void c(com.bytedance.pipeline.b bVar, d dVar, Throwable th) {
        a(bVar, dVar, th);
    }

    public final void d(com.bytedance.pipeline.b bVar, d dVar, Throwable th) {
        f(bVar, dVar, th);
    }

    public final void e(com.bytedance.pipeline.b bVar, d dVar, Throwable th) {
        b(bVar, dVar, th);
    }
}
