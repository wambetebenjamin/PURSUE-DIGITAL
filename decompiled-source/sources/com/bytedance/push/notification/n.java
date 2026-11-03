package com.bytedance.push.notification;

import java.util.LinkedHashMap;
import java.util.Map;
/* compiled from: MaxSizeHashMap.java */
/* loaded from: classes2.dex */
public class n<K, V> extends LinkedHashMap<K, V> {

    /* renamed from: a  reason: collision with root package name */
    private final int f11650a;

    public n(int i2) {
        this.f11650a = i2;
    }

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
        return size() > this.f11650a;
    }
}
