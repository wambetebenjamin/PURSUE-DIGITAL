package com.bytedance.applog.monitor.v3;

import android.content.Context;
import android.os.HandlerThread;
import android.util.Pair;
import java.util.Map;
import org.json.JSONArray;
/* compiled from: IV3Monitor.java */
/* loaded from: classes.dex */
public interface d<T> {
    Pair<Map<String, T>, Long> a(long j2);

    void a();

    void a(Context context, HandlerThread handlerThread, String str);

    void a(Map<String, T> map);

    void a(Map<String, T> map, JSONArray jSONArray);
}
