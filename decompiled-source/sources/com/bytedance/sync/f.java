package com.bytedance.sync;

import android.text.TextUtils;
/* compiled from: Configuration.java */
/* loaded from: classes3.dex */
public class f {
    public final com.bytedance.sync.interfaze.b accountService;
    public final String aid;
    public final int channelId;
    public final i commonParamProvider;
    public final boolean debug;
    public final String host;
    public final com.bytedance.sync.interfaze.g logger;
    public final String monitorHost;
    public final boolean oversea;
    public final boolean switchToV2;
    public final int upStreamServiceId;
    public final com.bytedance.sync.interfaze.p wsService;

    private f(a aVar) {
        this.aid = aVar.f13432a;
        this.commonParamProvider = new e(aVar.f13435d);
        this.wsService = aVar.f13436e;
        this.accountService = aVar.f13437f;
        this.channelId = aVar.f13433b;
        this.upStreamServiceId = aVar.f13434c;
        this.host = aVar.f13438g;
        this.monitorHost = aVar.f13439h;
        this.oversea = aVar.f13440i;
        this.logger = aVar.logger;
        this.switchToV2 = aVar.f13441j;
        this.debug = aVar.k;
    }

    /* compiled from: Configuration.java */
    /* loaded from: classes3.dex */
    public static final class a {

        /* renamed from: a  reason: collision with root package name */
        private final String f13432a;

        /* renamed from: b  reason: collision with root package name */
        private final int f13433b;

        /* renamed from: c  reason: collision with root package name */
        private final int f13434c;

        /* renamed from: d  reason: collision with root package name */
        private i f13435d;

        /* renamed from: e  reason: collision with root package name */
        private com.bytedance.sync.interfaze.p f13436e;

        /* renamed from: f  reason: collision with root package name */
        private com.bytedance.sync.interfaze.b f13437f;

        /* renamed from: g  reason: collision with root package name */
        private String f13438g;

        /* renamed from: h  reason: collision with root package name */
        private String f13439h;

        /* renamed from: i  reason: collision with root package name */
        private boolean f13440i;

        /* renamed from: j  reason: collision with root package name */
        private boolean f13441j;
        private boolean k;
        public com.bytedance.sync.interfaze.g logger;

        public a(String str, int i2, int i3) {
            this.f13432a = str;
            this.f13433b = i2;
            this.f13434c = i3;
        }

        @Deprecated
        public a(String str, String str2, int i2, int i3) {
            this(str2, i2, i3);
        }

        public a commonParam(i iVar) {
            this.f13435d = iVar;
            return this;
        }

        public a wsService(com.bytedance.sync.interfaze.p pVar) {
            this.f13436e = pVar;
            return this;
        }

        public a accountService(com.bytedance.sync.interfaze.b bVar) {
            this.f13437f = bVar;
            return this;
        }

        public a host(String str) {
            this.f13438g = str;
            return this;
        }

        public a monitorHost(String str) {
            this.f13439h = str;
            return this;
        }

        public a isOversea(boolean z) {
            this.f13440i = z;
            return this;
        }

        public a logger(com.bytedance.sync.interfaze.g gVar) {
            this.logger = gVar;
            return this;
        }

        public a isDebug(boolean z) {
            this.k = z;
            return this;
        }

        public a switchToV2(boolean z) {
            this.f13441j = z;
            return this;
        }

        public f build() {
            if (TextUtils.isEmpty(this.f13438g)) {
                throw new IllegalArgumentException("please set host before build");
            }
            if (TextUtils.isEmpty(this.f13432a)) {
                throw new IllegalArgumentException("please set aid before build");
            }
            if (this.f13434c < 0) {
                throw new IllegalArgumentException("please set upStreamServiceId before build");
            }
            if (this.f13435d == null) {
                throw new IllegalArgumentException("please set commonParamProvider before build");
            }
            if (this.f13436e == null) {
                throw new IllegalArgumentException("please set wsService before build");
            }
            if (this.f13437f == null) {
                this.f13437f = new com.bytedance.sync.user.b();
            }
            return new f(this);
        }
    }
}
