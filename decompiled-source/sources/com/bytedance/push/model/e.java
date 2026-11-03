package com.bytedance.push.model;

import com.bytedance.push.l;
import java.util.concurrent.ConcurrentLinkedQueue;
/* compiled from: SpreadOutMessage.java */
/* loaded from: classes2.dex */
public class e {

    /* renamed from: a  reason: collision with root package name */
    private final ConcurrentLinkedQueue<l> f11451a = new ConcurrentLinkedQueue<>();

    public synchronized void a(l lVar) {
        this.f11451a.add(lVar);
    }

    public l a() {
        return this.f11451a.peek();
    }

    public synchronized void b(l lVar) {
        this.f11451a.remove(lVar);
    }
}
