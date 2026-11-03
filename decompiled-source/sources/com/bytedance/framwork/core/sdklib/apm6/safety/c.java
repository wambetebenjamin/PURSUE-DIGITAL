package com.bytedance.framwork.core.sdklib.apm6.safety;

import com.bytedance.framwork.core.sdklib.apm6.safety.d;
/* compiled from: EnhancedLogger.java */
/* loaded from: classes2.dex */
public class c extends d.a {
    public static c a() {
        return a.f8726a;
    }

    @Override // com.bytedance.framwork.core.sdklib.apm6.safety.d.a, com.bytedance.apm6.util.log.a
    public void e(String str, String str2) {
        super.e(str, str2);
        b.a(str, str2, null);
    }

    @Override // com.bytedance.framwork.core.sdklib.apm6.safety.d.a, com.bytedance.apm6.util.log.a
    public void e(String str, String str2, Throwable th) {
        super.e(str, str2, th);
        b.a(str, str2, th);
    }

    @Override // com.bytedance.framwork.core.sdklib.apm6.safety.d.a, com.bytedance.apm6.util.log.a
    public void wtf(String str, String str2) {
        super.wtf(str, str2);
        b.a(str, str2, null);
    }

    @Override // com.bytedance.framwork.core.sdklib.apm6.safety.d.a, com.bytedance.apm6.util.log.a
    public void wtf(String str, String str2, Throwable th) {
        super.wtf(str, str2, th);
        b.a(str, str2, th);
    }

    /* compiled from: EnhancedLogger.java */
    /* loaded from: classes2.dex */
    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private static final c f8726a = new c();

        private a() {
        }
    }
}
