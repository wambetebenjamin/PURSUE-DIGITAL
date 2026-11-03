package com.bytedance.frameworks.baselib.network.http.cronet.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
/* compiled from: StreamTrafficObservable.java */
/* loaded from: classes2.dex */
public class h extends Observable {

    /* renamed from: a  reason: collision with root package name */
    public static final String f7742a = "url";

    /* renamed from: b  reason: collision with root package name */
    public static final String f7743b = "sent_bytes";

    /* renamed from: c  reason: collision with root package name */
    public static final String f7744c = "received_bytes";

    /* renamed from: d  reason: collision with root package name */
    public static final String f7745d = "request_log";

    /* renamed from: e  reason: collision with root package name */
    public static final String f7746e = "content_type";

    /* renamed from: f  reason: collision with root package name */
    private static volatile h f7747f;

    public static h a() {
        if (f7747f == null) {
            synchronized (h.class) {
                if (f7747f == null) {
                    f7747f = new h();
                }
            }
        }
        return f7747f;
    }

    public void a(String str, long j2, long j3, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        hashMap.put("sent_bytes", Long.valueOf(j2));
        hashMap.put("received_bytes", Long.valueOf(j3));
        hashMap.put("content_type", str2);
        hashMap.put("request_log", str3);
        a(hashMap);
    }

    private void a(Map<String, Object> map) {
        setChanged();
        notifyObservers(map);
    }
}
