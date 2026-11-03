package com.bytedance.geckox.interceptors;

import com.bytedance.geckox.b;
import com.bytedance.geckox.policy.request.d;
import java.util.concurrent.Executor;
/* compiled from: CheckServerRetryCallback.java */
/* loaded from: classes2.dex */
public class a implements d.a {

    /* renamed from: a  reason: collision with root package name */
    private Executor f9062a;

    /* renamed from: b  reason: collision with root package name */
    private com.bytedance.pipeline.b f9063b;

    public a(Executor executor, com.bytedance.pipeline.b bVar) {
        this.f9062a = executor;
        this.f9063b = bVar;
    }

    @Override // com.bytedance.geckox.policy.request.d.a
    public void a() {
        if (this.f9063b == null) {
            return;
        }
        if (this.f9062a == null) {
            this.f9062a = com.bytedance.geckox.utils.p.b();
        }
        this.f9062a.execute(new Runnable() { // from class: com.bytedance.geckox.interceptors.a.1
            @Override // java.lang.Runnable
            public void run() {
                String str;
                String str2 = null;
                try {
                    str = (String) a.this.f9063b.a(b.g.f8910c);
                } catch (Exception unused) {
                }
                try {
                    com.bytedance.geckox.logger.b.a(com.bytedance.geckox.d.f8984a, "check request retry start", str);
                    a.this.f9063b.a(b.g.f8908a, 2);
                    a.this.f9063b.b();
                } catch (Exception unused2) {
                    str2 = str;
                    com.bytedance.geckox.logger.b.a(com.bytedance.geckox.d.f8984a, "check request retry failed", str2);
                }
            }
        });
    }
}
