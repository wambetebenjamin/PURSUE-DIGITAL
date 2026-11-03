package com.bytedance.common.wschannel.heartbeat.smart;

import com.bytedance.common.wschannel.heartbeat.smart.state.g;
/* compiled from: SmartHeartBeatMeta.java */
/* loaded from: classes2.dex */
public class a implements com.bytedance.common.wschannel.heartbeat.model.b<a> {

    /* renamed from: a  reason: collision with root package name */
    private long f5676a;

    /* renamed from: b  reason: collision with root package name */
    private long f5677b;

    /* renamed from: c  reason: collision with root package name */
    private int f5678c;

    /* renamed from: d  reason: collision with root package name */
    private int f5679d;

    /* renamed from: e  reason: collision with root package name */
    private long f5680e;

    /* renamed from: f  reason: collision with root package name */
    private long f5681f;

    /* renamed from: g  reason: collision with root package name */
    private long f5682g;

    /* renamed from: h  reason: collision with root package name */
    private long f5683h;

    /* renamed from: i  reason: collision with root package name */
    private g f5684i;

    /* renamed from: j  reason: collision with root package name */
    private long f5685j;

    public a() {
    }

    public a(long j2, long j3, int i2, int i3, long j4, long j5, long j6) {
        this.f5676a = j2;
        this.f5677b = j3;
        this.f5678c = i2;
        this.f5679d = i3;
        this.f5680e = j4;
        this.f5681f = j5;
        this.f5682g = j6;
        this.f5683h = j2;
        this.f5685j = j2;
    }

    public long b() {
        return this.f5676a;
    }

    public long c() {
        return this.f5677b;
    }

    public int d() {
        return this.f5678c;
    }

    public int e() {
        return this.f5679d;
    }

    public long f() {
        return this.f5680e;
    }

    public long g() {
        return this.f5681f;
    }

    public long h() {
        return this.f5682g;
    }

    public long i() {
        return this.f5683h;
    }

    public void a(long j2) {
        this.f5683h = j2;
    }

    public g j() {
        return this.f5684i;
    }

    public void a(g gVar) {
        this.f5684i = gVar;
    }

    public long k() {
        return this.f5685j;
    }

    public void b(long j2) {
        this.f5685j = j2;
    }

    @Override // com.bytedance.common.wschannel.heartbeat.model.b
    /* renamed from: l */
    public a a() {
        return new C0083a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SmartHeartBeatMeta.java */
    /* renamed from: com.bytedance.common.wschannel.heartbeat.smart.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class C0083a extends a {

        /* renamed from: a  reason: collision with root package name */
        private static final long f5686a = 210000;

        /* renamed from: b  reason: collision with root package name */
        private static final long f5687b = 600000;

        /* renamed from: c  reason: collision with root package name */
        private static final int f5688c = 2;

        /* renamed from: d  reason: collision with root package name */
        private static final int f5689d = 3;

        /* renamed from: e  reason: collision with root package name */
        private static final long f5690e = 60000;

        /* renamed from: f  reason: collision with root package name */
        private static final long f5691f = 20000;

        /* renamed from: g  reason: collision with root package name */
        private static final long f5692g = 5000;

        @Override // com.bytedance.common.wschannel.heartbeat.smart.a, com.bytedance.common.wschannel.heartbeat.model.b
        public /* synthetic */ a a() {
            return super.a();
        }

        public C0083a() {
            super(f5686a, 600000L, 2, 3, 60000L, 20000L, 5000L);
        }
    }
}
