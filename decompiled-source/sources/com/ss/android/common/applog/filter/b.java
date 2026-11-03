package com.ss.android.common.applog.filter;

import java.util.HashMap;
import java.util.HashSet;
/* compiled from: BlockEventFilter.java */
/* loaded from: classes9.dex */
public class b extends a {
    public b(HashSet<String> hashSet, HashMap<String, HashSet<String>> hashMap) {
        super(hashSet, hashMap);
    }

    @Override // com.ss.android.common.applog.filter.a
    protected boolean a(String str) {
        return this.f18039a.contains(str);
    }

    @Override // com.ss.android.common.applog.filter.a
    protected boolean a(HashSet<String> hashSet, String str) {
        return hashSet.contains(str);
    }
}
