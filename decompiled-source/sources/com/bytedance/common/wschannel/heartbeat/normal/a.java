package com.bytedance.common.wschannel.heartbeat.normal;
/* compiled from: NormalHeartBeatMeta.java */
/* loaded from: classes2.dex */
public class a implements com.bytedance.common.wschannel.heartbeat.model.b<a> {

    /* renamed from: a  reason: collision with root package name */
    public static final long f5664a = 270000;

    /* renamed from: b  reason: collision with root package name */
    private long f5665b;

    /* renamed from: c  reason: collision with root package name */
    private long f5666c;

    public a() {
    }

    public a(long j2, long j3) {
        this.f5665b = j2;
        this.f5666c = j3;
    }

    public long b() {
        return this.f5665b;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(long j2) {
        this.f5665b = j2;
    }

    public long c() {
        return this.f5666c;
    }

    @Override // com.bytedance.common.wschannel.heartbeat.model.b
    /* renamed from: d */
    public a a() {
        return new C0082a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: NormalHeartBeatMeta.java */
    /* renamed from: com.bytedance.common.wschannel.heartbeat.normal.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class C0082a extends a {

        /* renamed from: b  reason: collision with root package name */
        private static final long f5667b = -1;

        /* renamed from: c  reason: collision with root package name */
        private static final long f5668c = 5000;

        @Override // com.bytedance.common.wschannel.heartbeat.normal.a, com.bytedance.common.wschannel.heartbeat.model.b
        public /* synthetic */ a a() {
            return super.a();
        }

        private C0082a() {
            super(-1L, 5000L);
        }
    }
}
