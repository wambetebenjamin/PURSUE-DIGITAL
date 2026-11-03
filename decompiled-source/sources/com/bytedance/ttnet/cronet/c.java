package com.bytedance.ttnet.cronet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.CopyOnWriteArrayList;
/* compiled from: CronetDropReasonAccess.java */
/* loaded from: classes8.dex */
public class c extends Observable {

    /* renamed from: a  reason: collision with root package name */
    public static final String f15243a = "user_blocked_state";

    /* renamed from: b  reason: collision with root package name */
    public static final String f15244b = "drop_reason_list";

    /* renamed from: c  reason: collision with root package name */
    private static volatile c f15245c;

    /* renamed from: d  reason: collision with root package name */
    private volatile boolean f15246d;

    /* renamed from: e  reason: collision with root package name */
    private CopyOnWriteArrayList<Integer> f15247e = new CopyOnWriteArrayList<>();

    public static c a() {
        if (f15245c == null) {
            synchronized (c.class) {
                if (f15245c == null) {
                    f15245c = new c();
                }
            }
        }
        return f15245c;
    }

    public void a(boolean z, List<Integer> list) {
        this.f15246d = z;
        this.f15247e.clear();
        this.f15247e.addAll(list);
        HashMap hashMap = new HashMap();
        hashMap.put(f15243a, Boolean.valueOf(this.f15246d));
        hashMap.put(f15244b, this.f15247e);
        a(hashMap);
    }

    private void a(Map<String, Object> map) {
        setChanged();
        notifyObservers(map);
    }

    public boolean b() {
        return this.f15246d;
    }

    public List<Integer> c() {
        return this.f15247e;
    }
}
