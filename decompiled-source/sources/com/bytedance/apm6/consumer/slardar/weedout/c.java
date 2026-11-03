package com.bytedance.apm6.consumer.slardar.weedout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/* compiled from: WeedOutManager.java */
/* loaded from: classes.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final long f1932a = 14400000;

    /* renamed from: b  reason: collision with root package name */
    private static volatile c f1933b = new c();

    /* renamed from: h  reason: collision with root package name */
    private static final int f1934h = 80;

    /* renamed from: d  reason: collision with root package name */
    private b f1936d;

    /* renamed from: c  reason: collision with root package name */
    private Set<d> f1935c = new HashSet();

    /* renamed from: e  reason: collision with root package name */
    private int f1937e = 80;

    /* renamed from: f  reason: collision with root package name */
    private int f1938f = 5;

    /* renamed from: g  reason: collision with root package name */
    private int f1939g = -1;

    public static c getInstance() {
        return f1933b;
    }

    public void a(int i2, int i3, int i4) {
        if (i2 <= 0 || i3 <= 0) {
            return;
        }
        this.f1937e = i2;
        this.f1938f = i3;
        this.f1939g = i4 != -1 ? Math.max(i4, 80) : -1;
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            com.bytedance.apm6.util.log.b.d(com.bytedance.apm6.consumer.slardar.b.TAG, "weed out config:maxSizeMB:" + i2 + " keepDays:" + i3);
        }
    }

    public synchronized void addStrategy(d dVar) {
        if (dVar == null) {
            return;
        }
        this.f1935c.add(dVar);
    }

    public synchronized void register(b bVar) {
        this.f1936d = bVar;
    }

    public void init() {
        if (com.bytedance.apm6.foundation.context.a.isMainProcess()) {
            com.bytedance.apm6.util.timetask.b.getInstance(com.bytedance.apm6.util.timetask.c.IO).sendTask(new com.bytedance.apm6.util.timetask.a(0L, f1932a) { // from class: com.bytedance.apm6.consumer.slardar.weedout.c.1
                @Override // java.lang.Runnable
                public void run() {
                    long size;
                    long j2;
                    Iterator it;
                    long currentTimeMillis = System.currentTimeMillis();
                    int i2 = c.this.f1938f;
                    long j3 = c.this.f1937e * 1024 * 1024;
                    HashMap hashMap = new HashMap();
                    if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                        com.bytedance.apm6.util.log.b.d(com.bytedance.apm6.consumer.slardar.b.TAG, "start weedOut:" + (currentTimeMillis - (i2 * 86400000)));
                    }
                    Iterator it2 = c.this.f1935c.iterator();
                    long j4 = 0;
                    while (it2.hasNext()) {
                        d dVar = (d) it2.next();
                        a aVar = new a();
                        hashMap.put(dVar.getName(), aVar);
                        aVar.a(dVar.getName());
                        aVar.a(dVar.getSize());
                        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                            it = it2;
                            j2 = j3;
                            com.bytedance.apm6.util.log.b.d(com.bytedance.apm6.consumer.slardar.b.TAG, "weedOut:name:" + aVar.getName() + " beforeSize:" + aVar.getBeforeSize());
                        } else {
                            j2 = j3;
                            it = it2;
                        }
                        dVar.doWeedOut(currentTimeMillis - (i2 * 86400000));
                        long size2 = dVar.getSize();
                        aVar.b(size2);
                        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                            com.bytedance.apm6.util.log.b.d(com.bytedance.apm6.consumer.slardar.b.TAG, "weedOut:name:" + aVar.getName() + " afterSize:" + aVar.getAfterSize());
                        }
                        j4 += size2;
                        it2 = it;
                        j3 = j2;
                    }
                    long j5 = j3;
                    for (int i3 = i2 - 1; j4 > j5 && i3 > 0; i3--) {
                        j4 = 0;
                        for (d dVar2 : c.this.f1935c) {
                            dVar2.doWeedOut(currentTimeMillis - (i3 * 86400000));
                            long size3 = dVar2.getSize();
                            a aVar2 = (a) hashMap.get(dVar2.getName());
                            if (aVar2 != null) {
                                aVar2.b(size3);
                            }
                            j4 += dVar2.getSize();
                        }
                    }
                    if (c.this.f1939g > 0) {
                        long j6 = c.this.f1939g * 1024 * 1024;
                        if (j4 > j6) {
                            for (d dVar3 : c.this.f1935c) {
                                if (dVar3.getSize() > 0) {
                                    dVar3.a(j6);
                                    long size4 = dVar3.getSize();
                                    a aVar3 = (a) hashMap.get(dVar3.getName());
                                    if (aVar3 != null) {
                                        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                                            com.bytedance.apm6.util.log.b.d(com.bytedance.apm6.consumer.slardar.b.TAG, "weedOut:name:" + aVar3.getName() + " afterSize:" + size4 + " maxBytesToday clean: " + (size - size4));
                                        }
                                        aVar3.a(true);
                                        aVar3.b(size4);
                                    }
                                }
                            }
                        }
                    }
                    if (c.this.f1936d != null) {
                        c.this.f1936d.onWeedOut(new ArrayList(hashMap.values()));
                    }
                }
            });
        }
    }
}
