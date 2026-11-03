package com.bytedance.applog.holder;

import com.bytedance.applog.l;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
/* compiled from: EventObserverHolder.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private final Map<Integer, CopyOnWriteArraySet<l>> f2713a = new ConcurrentHashMap();

    public void a(int i2, String str, String str2, String str3, long j2, long j3, String str4) {
        CopyOnWriteArraySet<l> copyOnWriteArraySet;
        if (a(i2) || (copyOnWriteArraySet = this.f2713a.get(Integer.valueOf(i2))) == null) {
            return;
        }
        Iterator<l> it = copyOnWriteArraySet.iterator();
        while (it.hasNext()) {
            it.next().onEvent(str, str2, str3, j2, j3, str4);
        }
    }

    public void a(int i2, String str, String str2) {
        CopyOnWriteArraySet<l> copyOnWriteArraySet;
        if (a(i2) || (copyOnWriteArraySet = this.f2713a.get(Integer.valueOf(i2))) == null) {
            return;
        }
        Iterator<l> it = copyOnWriteArraySet.iterator();
        while (it.hasNext()) {
            it.next().onEventV3(str, str2);
        }
    }

    public void b(int i2, String str, String str2) {
        CopyOnWriteArraySet<l> copyOnWriteArraySet;
        if (a(i2) || (copyOnWriteArraySet = this.f2713a.get(Integer.valueOf(i2))) == null) {
            return;
        }
        Iterator<l> it = copyOnWriteArraySet.iterator();
        while (it.hasNext()) {
            it.next().onMiscEvent(str, str2);
        }
    }

    public void a(int i2, l lVar) {
        CopyOnWriteArraySet<l> copyOnWriteArraySet;
        if (a(i2)) {
            this.f2713a.put(Integer.valueOf(i2), new CopyOnWriteArraySet<>());
        }
        if (lVar == null || (copyOnWriteArraySet = this.f2713a.get(Integer.valueOf(i2))) == null) {
            return;
        }
        copyOnWriteArraySet.add(lVar);
    }

    public void a(l lVar) {
        if (lVar != null) {
            for (Map.Entry<Integer, CopyOnWriteArraySet<l>> entry : this.f2713a.entrySet()) {
                CopyOnWriteArraySet<l> value = entry.getValue();
                if (value != null && value.contains(lVar)) {
                    value.remove(lVar);
                }
            }
        }
    }

    public int a() {
        return this.f2713a.values().size();
    }

    public boolean a(int i2) {
        return !this.f2713a.containsKey(Integer.valueOf(i2));
    }
}
