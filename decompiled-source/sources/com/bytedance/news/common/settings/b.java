package com.bytedance.news.common.settings;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.bytedance.news.common.settings.api.g;
import com.bytedance.news.common.settings.api.h;
import com.bytedance.news.common.settings.api.j;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
/* compiled from: SettingsConfig.java */
/* loaded from: classes2.dex */
public class b implements com.bytedance.news.common.settings.internal.b {

    /* renamed from: a  reason: collision with root package name */
    private Context f9768a;

    /* renamed from: b  reason: collision with root package name */
    private com.bytedance.news.common.settings.api.c f9769b;

    /* renamed from: c  reason: collision with root package name */
    private C0139b f9770c;

    private b(Context context, com.bytedance.news.common.settings.api.c cVar, C0139b c0139b) {
        this.f9768a = context;
        this.f9769b = cVar;
        this.f9770c = c0139b;
    }

    @Override // com.bytedance.news.common.settings.internal.b
    public Context getContext() {
        return this.f9768a;
    }

    @Override // com.bytedance.news.common.settings.internal.b
    public com.bytedance.news.common.settings.api.c getRequestService() {
        return this.f9769b;
    }

    @Override // com.bytedance.news.common.settings.internal.b
    public j getStorageFactory() {
        return this.f9770c.storageFactory;
    }

    @Override // com.bytedance.news.common.settings.internal.b
    public Executor getExecutor() {
        return this.f9770c.executor;
    }

    @Override // com.bytedance.news.common.settings.internal.b
    public long getUpdateInterval() {
        return this.f9770c.updateInterval;
    }

    @Override // com.bytedance.news.common.settings.internal.b
    public long getRetryInterval() {
        return this.f9770c.retryInterval;
    }

    @Override // com.bytedance.news.common.settings.internal.b
    public String getUpdateVersionCode() {
        return this.f9770c.updateVersionCode;
    }

    @Override // com.bytedance.news.common.settings.internal.b
    public SharedPreferences getSharedPreferences(Context context, String str, int i2, boolean z) {
        if (this.f9770c.preferencesService != null) {
            return this.f9770c.preferencesService.getSharedPreferences(context, str, i2, z);
        }
        return null;
    }

    @Override // com.bytedance.news.common.settings.internal.b
    public g getSettingsLogService() {
        return this.f9770c.settingsLogService;
    }

    @Override // com.bytedance.news.common.settings.internal.b
    public boolean isMainProcess() {
        return this.f9770c.isMainProcess;
    }

    @Override // com.bytedance.news.common.settings.internal.b
    public boolean useReflect() {
        return this.f9770c.useReflect;
    }

    @Override // com.bytedance.news.common.settings.internal.b
    public boolean useOneSpForAppSettings() {
        return this.f9770c.useOneSpForAppSettings;
    }

    @Override // com.bytedance.news.common.settings.internal.b
    public com.bytedance.news.common.settings.api.e getAbReportService() {
        return this.f9770c.settingsAbReportService;
    }

    @Override // com.bytedance.news.common.settings.internal.b
    public int getMaxAppSettingSpCount() {
        return this.f9770c.maxAppSettingSpCount;
    }

    @Override // com.bytedance.news.common.settings.internal.b
    public boolean isReportSettingsStack() {
        return this.f9770c.isReportSettingsStack;
    }

    @Override // com.bytedance.news.common.settings.internal.b
    public String getUID() {
        return this.f9770c.UID;
    }

    @Override // com.bytedance.news.common.settings.internal.b
    public com.bytedance.news.common.settings.api.a getDebugTeller() {
        return this.f9770c.debugTeller;
    }

    public String getId() {
        return this.f9770c.id;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str) {
        this.f9770c.id = str;
    }

    public void setOneSpForAppSettings(boolean z) {
        this.f9770c.useOneSpForAppSettings = z;
    }

    public void setReportSettingsStack(boolean z) {
        this.f9770c.isReportSettingsStack = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: SettingsConfig.java */
    /* renamed from: com.bytedance.news.common.settings.b$b  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class C0139b {
        public String UID;
        public com.bytedance.news.common.settings.api.a debugTeller;
        public Executor executor;
        public String id;
        public boolean isMainProcess;
        public boolean isReportSettingsStack;
        public int maxAppSettingSpCount;
        public h preferencesService;
        public long retryInterval;
        public com.bytedance.news.common.settings.api.e settingsAbReportService;
        public g settingsLogService;
        public j storageFactory;
        public long updateInterval;
        public String updateVersionCode;
        public boolean useOneSpForAppSettings;
        public boolean useReflect;

        private C0139b() {
            this.isMainProcess = true;
            this.useReflect = true;
        }
    }

    /* compiled from: SettingsConfig.java */
    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        private static final long f9771a = 3600000;

        /* renamed from: b  reason: collision with root package name */
        private static final long f9772b = 120000;

        /* renamed from: c  reason: collision with root package name */
        private Context f9773c;

        /* renamed from: d  reason: collision with root package name */
        private j f9774d;

        /* renamed from: e  reason: collision with root package name */
        private com.bytedance.news.common.settings.api.c f9775e;

        /* renamed from: f  reason: collision with root package name */
        private Executor f9776f;

        /* renamed from: i  reason: collision with root package name */
        private String f9779i;

        /* renamed from: j  reason: collision with root package name */
        private h f9780j;
        private g k;
        private com.bytedance.news.common.settings.api.e l;
        private boolean o;
        private int p;
        private boolean q;

        /* renamed from: g  reason: collision with root package name */
        private long f9777g = -1;

        /* renamed from: h  reason: collision with root package name */
        private long f9778h = -1;
        private boolean m = true;
        private boolean n = true;
        private String r = null;
        private com.bytedance.news.common.settings.api.a s = null;

        public a context(Context context) {
            this.f9773c = context;
            return this;
        }

        public a storageFactory(j jVar) {
            this.f9774d = jVar;
            return this;
        }

        public a requestService(com.bytedance.news.common.settings.api.c cVar) {
            this.f9775e = cVar;
            return this;
        }

        public a executor(Executor executor) {
            this.f9776f = executor;
            return this;
        }

        public a updateInterval(long j2) {
            this.f9777g = j2;
            return this;
        }

        public a retryInterval(long j2) {
            this.f9778h = j2;
            return this;
        }

        @Deprecated
        public a updateVersionCode(String str) {
            this.f9779i = str;
            return this;
        }

        public a sharePreferencesService(h hVar) {
            this.f9780j = hVar;
            return this;
        }

        public a settingsLogService(g gVar) {
            this.k = gVar;
            return this;
        }

        public a isMainProcess(boolean z) {
            this.m = z;
            return this;
        }

        public a useReflect(boolean z) {
            this.n = z;
            return this;
        }

        public a useOneSpForAppSettings(boolean z) {
            this.o = z;
            return this;
        }

        public a setAbReportService(com.bytedance.news.common.settings.api.e eVar) {
            this.l = eVar;
            return this;
        }

        public a maxAppSettingSpCount(int i2) {
            this.p = i2;
            return this;
        }

        public a isReportSettingsStack(boolean z) {
            this.q = z;
            return this;
        }

        public a setUID(String str) {
            this.r = str;
            return this;
        }

        public a debugTeller(com.bytedance.news.common.settings.api.a aVar) {
            this.s = aVar;
            return this;
        }

        public b build() {
            if (this.f9773c == null) {
                throw new IllegalArgumentException("context不能为空");
            }
            if (this.f9775e == null) {
                throw new IllegalArgumentException("requestService不能为空");
            }
            if (this.f9774d == null) {
                this.f9774d = new com.bytedance.news.common.settings.storage.a();
            }
            if (this.f9776f == null) {
                this.f9776f = Executors.newCachedThreadPool();
            }
            if (this.f9777g < 0) {
                this.f9777g = 3600000L;
            }
            if (this.f9778h < 0) {
                this.f9778h = 120000L;
            }
            C0139b c0139b = new C0139b();
            c0139b.storageFactory = this.f9774d;
            c0139b.executor = this.f9776f;
            c0139b.updateInterval = this.f9777g;
            c0139b.retryInterval = this.f9778h;
            c0139b.updateVersionCode = this.f9779i;
            c0139b.preferencesService = this.f9780j;
            c0139b.settingsLogService = this.k;
            c0139b.isMainProcess = this.m;
            c0139b.useReflect = this.n;
            c0139b.useOneSpForAppSettings = this.o;
            c0139b.settingsAbReportService = this.l;
            c0139b.maxAppSettingSpCount = this.p;
            c0139b.isReportSettingsStack = this.q;
            c0139b.UID = this.r;
            c0139b.debugTeller = this.s;
            if (this.f9773c instanceof Application) {
                return new b(this.f9773c, this.f9775e, c0139b);
            }
            return new b(this.f9773c.getApplicationContext(), this.f9775e, c0139b);
        }
    }
}
