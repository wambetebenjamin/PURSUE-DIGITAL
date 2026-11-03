package com.bytedance.geckox.listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: PipelineChannelUpdateListenerManager.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static Map<String, Map<String, List<a>>> f9162a;

    /* renamed from: b  reason: collision with root package name */
    private static c f9163b;

    private c() {
        f9162a = new ConcurrentHashMap();
    }

    public static c a() {
        if (f9163b == null) {
            synchronized (c.class) {
                if (f9163b == null) {
                    f9163b = new c();
                }
            }
        }
        return f9163b;
    }

    public synchronized void a(String str, String str2, a aVar) {
        List<a> synchronizedList;
        Map<String, List<a>> map = f9162a.get(str);
        if (map != null) {
            synchronizedList = map.get(str2);
            if (synchronizedList == null) {
                synchronizedList = Collections.synchronizedList(new ArrayList());
            }
        } else {
            map = new ConcurrentHashMap<>();
            synchronizedList = Collections.synchronizedList(new ArrayList());
        }
        synchronizedList.add(aVar);
        map.put(str2, synchronizedList);
        f9162a.put(str, map);
    }

    public List<a> a(String str, String str2) {
        Map<String, List<a>> map = f9162a.get(str);
        if (map != null) {
            return map.get(str2);
        }
        return null;
    }

    public synchronized List<a> b(String str, String str2) {
        Map<String, List<a>> map = f9162a.get(str);
        if (map != null) {
            return map.remove(str2);
        }
        return null;
    }
}
