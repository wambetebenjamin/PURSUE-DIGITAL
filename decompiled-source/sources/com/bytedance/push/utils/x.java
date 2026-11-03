package com.bytedance.push.utils;

import com.google.gson.Gson;
/* compiled from: GsonUtils.java */
/* loaded from: classes2.dex */
public class x {

    /* renamed from: a  reason: collision with root package name */
    private static final Gson f12082a = new Gson();

    public static <T> T a(String str, Class<T> cls) {
        return (T) f12082a.fromJson(str, (Class<Object>) cls);
    }

    public static String a(Object obj) {
        return f12082a.toJson(obj);
    }
}
