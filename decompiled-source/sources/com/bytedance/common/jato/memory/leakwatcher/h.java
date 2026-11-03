package com.bytedance.common.jato.memory.leakwatcher;

import android.os.SystemClock;
import android.util.Log;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: ReferenceQueueLeakTracker.java */
/* loaded from: classes2.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private static final String f5005a = "ReferQueueLeakTracker";

    /* renamed from: b  reason: collision with root package name */
    private final Map<String, g> f5006b = new ConcurrentHashMap();

    /* renamed from: c  reason: collision with root package name */
    private final ReferenceQueue<Object> f5007c = new ReferenceQueue<>();

    public void a(Object obj, String str) {
        if (obj == null) {
            Log.e(f5005a, "Cannot track null objects");
            return;
        }
        String uuid = UUID.randomUUID().toString();
        this.f5006b.put(uuid, new g(obj, uuid, str, SystemClock.elapsedRealtime(), this.f5007c));
    }

    public List<g> a() {
        e();
        ArrayList arrayList = new ArrayList();
        for (g gVar : this.f5006b.values()) {
            if (gVar.d()) {
                arrayList.add(gVar);
            }
        }
        return arrayList;
    }

    public int b() {
        e();
        return this.f5006b.size();
    }

    public int c() {
        e();
        int i2 = 0;
        for (g gVar : this.f5006b.values()) {
            if (gVar.d()) {
                i2++;
            }
        }
        return i2;
    }

    private void e() {
        while (true) {
            g gVar = (g) this.f5007c.poll();
            if (gVar == null) {
                return;
            }
            this.f5006b.remove(gVar.a());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void d() {
        this.f5006b.clear();
    }
}
