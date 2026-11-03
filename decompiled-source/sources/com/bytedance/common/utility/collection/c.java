package com.bytedance.common.utility.collection;

import java.util.LinkedHashMap;
import java.util.Map;
/* compiled from: MaxSizeLinkedHashMap.java */
/* loaded from: classes2.dex */
public class c<K, V> extends LinkedHashMap<K, V> {

    /* renamed from: b  reason: collision with root package name */
    private static final long f5210b = 3805937866184666407L;

    /* renamed from: c  reason: collision with root package name */
    private static final float f5211c = 0.75f;

    /* renamed from: a  reason: collision with root package name */
    final int f5212a;

    public c(int i2, int i3) {
        this(i2, i3, false);
    }

    public c(int i2, int i3, boolean z) {
        super(i3, 0.75f, true);
        this.f5212a = i2;
        if (i2 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
    }

    @Override // java.util.LinkedHashMap
    protected boolean removeEldestEntry(Map.Entry<K, V> entry) {
        return size() > this.f5212a;
    }
}
