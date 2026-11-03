package com.bytedance.sdk.bridge;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
/* compiled from: BridgeIndexManager.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final List<String> f13140a = new CopyOnWriteArrayList();

    /* renamed from: b  reason: collision with root package name */
    private static final List<n> f13141b = new CopyOnWriteArrayList();

    static void a() {
    }

    static void b() {
    }

    public static List<String> getClassNameList() {
        List<String> list = f13140a;
        list.clear();
        a();
        return list;
    }

    public static List<n> getIBridgeIndices() {
        List<n> list = f13141b;
        list.clear();
        b();
        return list;
    }
}
