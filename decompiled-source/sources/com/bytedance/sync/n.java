package com.bytedance.sync;

import java.util.ArrayList;
import java.util.List;
/* compiled from: SyncBiz.java */
/* loaded from: classes3.dex */
public class n {

    /* renamed from: a  reason: collision with root package name */
    final i f13480a;

    /* renamed from: b  reason: collision with root package name */
    final List<com.bytedance.sync.interfaze.r> f13481b;
    public final long bizId;

    public n(a aVar) {
        this.bizId = aVar.f13482a;
        this.f13480a = aVar.f13483b;
        this.f13481b = aVar.f13484c;
    }

    /* compiled from: SyncBiz.java */
    /* loaded from: classes3.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private final long f13482a;

        /* renamed from: b  reason: collision with root package name */
        private i f13483b;

        /* renamed from: c  reason: collision with root package name */
        private final List<com.bytedance.sync.interfaze.r> f13484c = new ArrayList();

        public a(long j2) {
            this.f13482a = j2;
        }

        public a setCommonParam(i iVar) {
            this.f13483b = iVar;
            return this;
        }

        public a addOnUpdateListener(com.bytedance.sync.interfaze.r rVar) {
            this.f13484c.add(rVar);
            return this;
        }

        public n build() {
            if (this.f13482a < 0) {
                throw new IllegalArgumentException("bizId < 0");
            }
            return new n(this);
        }
    }
}
