package com.bytedance.applog.log;

import java.util.Iterator;
import java.util.List;
/* compiled from: GlobalLoggerImpl.java */
/* loaded from: classes.dex */
public final class g extends o {
    @Override // com.bytedance.applog.log.b, com.bytedance.applog.log.h
    public void a(String str) {
        e("You should not set appId on global logger instance", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.applog.log.b
    public void a(int i2, int i3, List<String> list, Throwable th, String str, Object... objArr) {
        if (m.a()) {
            return;
        }
        j a2 = j.a().b(i2).a(i3).b(Thread.currentThread().getName()).a(th).a(a(list)).c(a(str, objArr)).a();
        m.a(a2);
        Iterator<i> c2 = m.c();
        while (c2.hasNext()) {
            c2.next().a(a2);
        }
    }
}
