package com.bytedance.applog.util;

import com.bytedance.bdinstall.util.r;
/* compiled from: RomUtils.java */
/* loaded from: classes.dex */
public class m {

    /* renamed from: a  reason: collision with root package name */
    private static final String f3577a = "harmony";

    /* renamed from: b  reason: collision with root package name */
    private static final r<Boolean> f3578b = new r<Boolean>() { // from class: com.bytedance.applog.util.m.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bytedance.bdinstall.util.r
        /* renamed from: a */
        public Boolean b(Object... objArr) {
            try {
                Class<?> cls = Class.forName("com.huawei.system.BuildEx");
                return Boolean.valueOf(m.f3577a.equals(cls.getMethod("getOsBrand", new Class[0]).invoke(cls, new Object[0])));
            } catch (Throwable unused) {
                return false;
            }
        }
    };

    public static boolean a() {
        return f3578b.c(new Object[0]).booleanValue();
    }
}
