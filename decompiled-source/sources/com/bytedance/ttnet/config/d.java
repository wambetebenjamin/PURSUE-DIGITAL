package com.bytedance.ttnet.config;

import com.bytedance.common.utility.reflect.Reflect;
/* compiled from: SDKConfig.java */
/* loaded from: classes8.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static final int f15084a = 2616;

    /* renamed from: b  reason: collision with root package name */
    private static volatile boolean f15085b = true;

    public static void a() {
        if (f15085b) {
            try {
                Reflect.on(Class.forName("com.bytedance.crash.Npth").newInstance()).call("registerSdk", new Class[]{Integer.TYPE, String.class}, Integer.valueOf((int) f15084a), "4.2.243.51-gsdk");
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    public static void b() {
        f15085b = false;
    }
}
