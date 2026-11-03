package com.bytedance.crash.monitor;

import com.bytedance.crash.Global;
import com.bytedance.crash.UserDataCenter;
import java.util.Map;
/* compiled from: AppMonitor.java */
/* loaded from: classes2.dex */
public class a extends d {
    private static final UserDataCenter n = new UserDataCenter();

    @Override // com.bytedance.crash.monitor.f
    public Map<String, Object> c() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static UserDataCenter a() {
        return n;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a(String str, String str2, long j2) {
        super(n, str, str2, j2);
        Global.a(j2, 0L, 0L, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public a() {
        super(n);
    }

    /* compiled from: AppMonitor.java */
    /* renamed from: com.bytedance.crash.monitor.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    private static class C0099a {

        /* renamed from: a  reason: collision with root package name */
        public static final a f6879a = new a("4444", "0", 0);

        private C0099a() {
        }
    }

    public static a b() {
        return C0099a.f6879a;
    }

    public c a(long j2) {
        return this.f6895a.g(j2);
    }

    @Override // com.bytedance.crash.monitor.f
    public com.bytedance.crash.event.e d() {
        com.bytedance.crash.event.e b2 = h.b();
        b2.a(this);
        return b2;
    }
}
