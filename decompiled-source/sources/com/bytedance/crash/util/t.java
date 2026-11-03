package com.bytedance.crash.util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: ListMap.java */
/* loaded from: classes2.dex */
public class t<K, V> extends ConcurrentHashMap<K, List<V>> {

    /* renamed from: a  reason: collision with root package name */
    private static final long f7313a = -8278080958339137414L;

    public List<V> newList() {
        return new ArrayList();
    }

    public void add(K k, V v) {
        getList(k).add(v);
    }

    public void addMulti(V v, K... kArr) {
        if (v == null || kArr == null || kArr.length <= 0) {
            return;
        }
        for (K k : kArr) {
            getList(k).add(v);
        }
    }

    public void removeAll(V v) {
        for (V v2 : values()) {
            v2.remove(v);
        }
    }

    public void removeInList(K k, V v) {
        getList(k).remove(v);
    }

    public List<V> getList(K k) {
        List<V> list = (List) super.get(k);
        if (list == null) {
            List<V> newList = newList();
            put(k, newList);
            return newList;
        }
        return list;
    }
}
