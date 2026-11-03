package com.bytedance.bdinstall.util;

import java.util.UUID;
/* compiled from: RequestIdGenerator.java */
/* loaded from: classes.dex */
public class o {

    /* renamed from: a  reason: collision with root package name */
    private static r<String> f4066a = c();

    private static r<String> c() {
        return new r<String>() { // from class: com.bytedance.bdinstall.util.o.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.bytedance.bdinstall.util.r
            /* renamed from: a */
            public String b(Object... objArr) {
                return UUID.randomUUID().toString();
            }
        };
    }

    public static String a() {
        return f4066a.c(new Object[0]);
    }

    public static void b() {
        f4066a = c();
    }
}
