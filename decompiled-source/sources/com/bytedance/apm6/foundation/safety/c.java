package com.bytedance.apm6.foundation.safety;

import com.bytedance.apm6.util.log.b;
/* compiled from: EnhancedLogger.java */
/* loaded from: classes.dex */
public class c extends b.a {
    @Override // com.bytedance.apm6.util.log.b.a, com.bytedance.apm6.util.log.a
    public void e(String str, String str2) {
        super.e(str, str2);
        b.a(str, str2, null);
    }

    @Override // com.bytedance.apm6.util.log.b.a, com.bytedance.apm6.util.log.a
    public void e(String str, String str2, Throwable th) {
        super.e(str, str2, th);
        b.a(str, str2, th);
    }

    @Override // com.bytedance.apm6.util.log.b.a, com.bytedance.apm6.util.log.a
    public void wtf(String str, String str2) {
        super.wtf(str, str2);
        b.a(str, str2, null);
    }

    @Override // com.bytedance.apm6.util.log.b.a, com.bytedance.apm6.util.log.a
    public void wtf(String str, String str2, Throwable th) {
        super.wtf(str, str2, th);
        b.a(str, str2, th);
    }
}
