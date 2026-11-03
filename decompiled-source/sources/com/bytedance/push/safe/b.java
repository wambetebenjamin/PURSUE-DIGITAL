package com.bytedance.push.safe;

import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.push.g;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: PassThoughMsgCache.java */
/* loaded from: classes2.dex */
public class b implements a {

    /* renamed from: a  reason: collision with root package name */
    private final Map<Long, Pair<g, Integer>> f11695a = new ConcurrentHashMap();

    @Override // com.bytedance.push.safe.a
    public boolean isFromPassThough(String str) {
        for (Map.Entry<Long, Pair<g, Integer>> entry : this.f11695a.entrySet()) {
            Pair<g, Integer> value = entry.getValue();
            if (value != null && value.first != null && TextUtils.equals(((g) value.first).msgData.toString(), str)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.bytedance.push.safe.a
    public g getRevokedMsg(long j2) {
        Pair<g, Integer> pair = this.f11695a.get(Long.valueOf(j2));
        if (pair != null) {
            return (g) pair.first;
        }
        return null;
    }

    @Override // com.bytedance.push.safe.a
    public int getMsgFrom(g gVar) {
        Pair<g, Integer> pair;
        if (gVar == null || (pair = this.f11695a.get(Long.valueOf(gVar.id))) == null) {
            return 0;
        }
        return ((Integer) pair.second).intValue();
    }

    @Override // com.bytedance.push.safe.a
    public void cacheMsg(g gVar, int i2) {
        this.f11695a.put(Long.valueOf(gVar.id), new Pair<>(gVar, Integer.valueOf(i2)));
    }
}
