package com.bytedance.news.common.settings.storage;

import com.bytedance.news.common.settings.api.i;
import com.bytedance.news.common.settings.api.j;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: SharedFreferenceStorageFactory.java */
/* loaded from: classes2.dex */
public class a implements j {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9823a = "SharedFreferenceStorage";

    /* renamed from: b  reason: collision with root package name */
    private static final ConcurrentHashMap<String, b> f9824b = new ConcurrentHashMap<>();

    @Override // com.bytedance.news.common.settings.api.j
    public i create(String str) {
        return create(str, false);
    }

    @Override // com.bytedance.news.common.settings.api.j
    public i create(String str, boolean z) {
        ConcurrentHashMap<String, b> concurrentHashMap = f9824b;
        b bVar = concurrentHashMap.get(str);
        if (bVar != null) {
            return bVar;
        }
        b bVar2 = new b(com.bytedance.news.common.settings.internal.a.getContext(), str, z);
        concurrentHashMap.put(str, bVar2);
        return bVar2;
    }
}
