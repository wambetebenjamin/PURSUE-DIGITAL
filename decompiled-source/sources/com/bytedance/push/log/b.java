package com.bytedance.push.log;

import com.bytedance.push.utils.h;
/* compiled from: LoggerImpl.java */
/* loaded from: classes2.dex */
public class b implements a {
    @Override // com.bytedance.push.log.a
    public void d(String str) {
        h.d(str);
    }

    @Override // com.bytedance.push.log.a
    public void d(String str, String str2) {
        h.d(str, str2);
    }

    @Override // com.bytedance.push.log.a
    public void e(String str) {
        h.e(str);
    }

    @Override // com.bytedance.push.log.a
    public void e(String str, String str2) {
        h.e(str, str2);
    }

    @Override // com.bytedance.push.log.a
    public void a(String str, String str2, Throwable th) {
        h.e(str, str2, th);
    }

    @Override // com.bytedance.push.log.a
    public boolean debug() {
        return h.debug();
    }

    @Override // com.bytedance.push.log.a
    public void i(String str) {
        h.i(str);
    }

    @Override // com.bytedance.push.log.a
    public void i(String str, String str2) {
        h.i(str, str2);
    }

    @Override // com.bytedance.push.log.a
    public void i(String str, String str2, Throwable th) {
        h.i(str, str2, th);
    }

    @Override // com.bytedance.push.log.a
    public void v(String str, String str2) {
        h.v(str, str2);
    }

    @Override // com.bytedance.push.log.a
    public void w(String str, String str2) {
        h.w(str, str2);
    }
}
