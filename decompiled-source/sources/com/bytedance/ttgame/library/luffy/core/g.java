package com.bytedance.ttgame.library.luffy.core;

import android.content.Context;
import android.os.SystemClock;
import com.bytedance.crash.dumper.Scraps;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;
/* compiled from: InternalMainImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\r\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 >2\u00020\u0001:\u0001>B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001a\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\rH\u0016J\u0010\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\bH\u0016J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\bH\u0016J\u001c\u0010\u001d\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u001f0\u001e2\u0006\u0010\u001a\u001a\u00020\bH\u0016J \u0010 \u001a\u00020\u001c2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010!\u001a\u00020\u001cH\u0016J \u0010\"\u001a\u00020#2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010!\u001a\u00020#H\u0016J \u0010$\u001a\u00020%2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010!\u001a\u00020%H\u0016J \u0010&\u001a\u00020'2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010!\u001a\u00020'H\u0016J \u0010(\u001a\u00020)2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010!\u001a\u00020)H\u0016J$\u0010*\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\b\u0010!\u001a\u0004\u0018\u00010\bH\u0016J,\u0010+\u001a\b\u0012\u0004\u0012\u00020\b0,2\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\b0,H\u0016J \u0010.\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010/\u001a\u00020\u001cH\u0016J \u00100\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010/\u001a\u00020#H\u0016J \u00101\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010/\u001a\u00020%H\u0016J \u00102\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010/\u001a\u00020'H\u0016J \u00103\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\u0006\u0010/\u001a\u00020)H\u0016J\"\u00104\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\b2\b\u0010/\u001a\u0004\u0018\u00010\bH\u0016J,\u00105\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\b2\b\u0010\u0017\u001a\u0004\u0018\u00010\b2\u0010\u00106\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\b\u0018\u00010,H\u0016J\u0018\u00107\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\bH\u0016J\b\u00108\u001a\u00020\u0016H\u0002J\u0018\u00109\u001a\b\u0012\u0004\u0012\u00020\b0:2\b\u0010;\u001a\u0004\u0018\u00010<H\u0002J\b\u0010=\u001a\u00020\u0016H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0012\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0013j\b\u0012\u0004\u0012\u00020\b`\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"Lcom/bytedance/ttgame/library/luffy/core/InternalMainImpl;", "Lcom/bytedance/ttgame/library/luffy/core/IInternalService;", "context", "Landroid/content/Context;", "config", "Lcom/bytedance/ttgame/library/luffy/InitConfig;", "(Landroid/content/Context;Lcom/bytedance/ttgame/library/luffy/InitConfig;)V", "TAG", "", "executor", "Ljava/util/concurrent/Executor;", "listeners", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/bytedance/ttgame/library/luffy/core/CallBack;", "requestCount", "Ljava/util/concurrent/atomic/AtomicInteger;", Scraps.STORAGE, "Lcom/bytedance/ttgame/library/luffy/core/LevelStorage;", "updatedConfigKeys", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "addRemoteUpdateListener", "", "key", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "clear", "repo", "contains", "", "getAll", "", "", "getBoolean", "defValue", "getBytes", "", "getFloat", "", "getInt", "", "getLong", "", "getString", "getStringSet", "", "defValues", "putBoolean", "value", "putBytes", "putFloat", "putInt", "putLong", "putString", "putStringSet", "values", "remove", "removeOfflineConfigs", "storeRequestConfigs", "", "data", "Lcom/bytedance/ttgame/library/luffy/adapter/RequestData;", "update", "Companion", "luffy_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public final class g implements d {
    public static final a Companion = new a(null);

    /* renamed from: i  reason: collision with root package name */
    private static final AtomicBoolean f14180i = new AtomicBoolean(false);

    /* renamed from: a  reason: collision with root package name */
    private final String f14181a;

    /* renamed from: b  reason: collision with root package name */
    private Executor f14182b;

    /* renamed from: c  reason: collision with root package name */
    private j f14183c;

    /* renamed from: d  reason: collision with root package name */
    private final ConcurrentHashMap<String, com.bytedance.ttgame.library.luffy.core.a> f14184d;

    /* renamed from: e  reason: collision with root package name */
    private final AtomicInteger f14185e;

    /* renamed from: f  reason: collision with root package name */
    private final ArrayList<String> f14186f;

    /* renamed from: g  reason: collision with root package name */
    private final Context f14187g;

    /* renamed from: h  reason: collision with root package name */
    private final com.bytedance.ttgame.library.luffy.b f14188h;

    public static final AtomicBoolean getRequested() {
        return f14180i;
    }

    public g(Context context, com.bytedance.ttgame.library.luffy.b config) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(config, "config");
        this.f14187g = context;
        this.f14188h = config;
        this.f14181a = "Internal";
        this.f14184d = new ConcurrentHashMap<>();
        this.f14185e = new AtomicInteger(0);
        this.f14186f = new ArrayList<>();
        com.bytedance.ttgame.library.luffy.adapter.b logger = config.getLogger();
        if (logger != null) {
            k.INSTANCE.set(logger);
        }
        ExecutorService executor = config.getExecutor();
        if (executor == null) {
            ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
            Intrinsics.checkNotNullExpressionValue(newFixedThreadPool, "Executors.newFixedThreadPool(2)");
            executor = newFixedThreadPool;
        }
        this.f14182b = executor;
        com.bytedance.ttgame.library.luffy.adapter.d mainStorage = config.getMainStorage();
        Intrinsics.checkNotNull(mainStorage);
        j jVar = new j(mainStorage, config.getSecondaryStorage());
        this.f14183c = jVar;
        jVar.init(context, config.getPreloads(), this.f14182b);
    }

    /* compiled from: InternalMainImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lcom/bytedance/ttgame/library/luffy/core/InternalMainImpl$Companion;", "", "()V", "requested", "Ljava/util/concurrent/atomic/AtomicBoolean;", "getRequested$annotations", "getRequested", "()Ljava/util/concurrent/atomic/AtomicBoolean;", "luffy_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes3.dex */
    public static final class a {
        @JvmStatic
        public static /* synthetic */ void getRequested$annotations() {
        }

        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final AtomicBoolean getRequested() {
            return g.f14180i;
        }
    }

    /* compiled from: InternalMainImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes3.dex */
    static final class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            long uptimeMillis = SystemClock.uptimeMillis();
            List<com.bytedance.ttgame.library.luffy.adapter.c> request = g.this.f14188h.getRequest();
            if (request != null) {
                g.this.f14185e.set(request.size());
                g.this.f14186f.clear();
                for (com.bytedance.ttgame.library.luffy.adapter.c cVar : request) {
                    com.bytedance.ttgame.library.luffy.adapter.e request2 = cVar.request();
                    if (request2 != null) {
                        g.this.f14186f.addAll(g.this.a(request2));
                        if (g.this.f14185e.decrementAndGet() == 0) {
                            g.this.a();
                        }
                    }
                }
            }
            g.Companion.getRequested().compareAndSet(false, true);
            g.this.f14182b.execute(new Runnable() { // from class: com.bytedance.ttgame.library.luffy.core.g.b.1
                @Override // java.lang.Runnable
                public final void run() {
                    try {
                        for (Map.Entry entry : g.this.f14184d.entrySet()) {
                            ((com.bytedance.ttgame.library.luffy.core.a) entry.getValue()).onUpdate();
                            g.this.f14184d.remove(entry.getKey());
                            k.INSTANCE.v(g.this.f14181a, "call back for " + ((String) entry.getKey()) + " after requested");
                        }
                    } catch (Throwable th) {
                        k.INSTANCE.e(g.this.f14181a, "call back left listener failed, " + th);
                    }
                }
            });
            k.INSTANCE.d(g.this.f14181a, "update configs cost " + (SystemClock.uptimeMillis() - uptimeMillis) + "ms");
        }
    }

    @Override // com.bytedance.ttgame.library.luffy.core.d
    public void update() {
        this.f14182b.execute(new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void a() {
        if (this.f14186f.size() > 0) {
            try {
                Set<String> keySet = this.f14183c.getMainStorage$luffy_release().getAll("_gsdk_remote_config_repo").keySet();
                if (keySet.size() > this.f14186f.size()) {
                    for (String str : keySet) {
                        if (!this.f14186f.contains(str)) {
                            this.f14183c.getMainStorage$luffy_release().remove("_gsdk_remote_config_repo", str);
                            k.INSTANCE.w(this.f14181a, "config " + str + " deleted because of it is offline");
                        }
                    }
                }
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14181a, "removeOfflineConfigs failed " + th);
            }
        }
    }

    @Override // com.bytedance.ttgame.library.luffy.core.d
    public void addRemoteUpdateListener(String key, com.bytedance.ttgame.library.luffy.core.a aVar) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (aVar != null) {
            try {
                if (f14180i.get()) {
                    aVar.onUpdate();
                    k.INSTANCE.v(this.f14181a, "call back for " + key + " directly");
                } else {
                    this.f14184d.put(key, aVar);
                }
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14181a, "addRemoteConfigListener failed, " + th);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<String> a(com.bytedance.ttgame.library.luffy.adapter.e eVar) {
        if (eVar == null) {
            k.INSTANCE.e(this.f14181a, "storeRequestConfigs failed, data is null");
            return CollectionsKt.emptyList();
        }
        boolean component1 = eVar.component1();
        eVar.component2();
        Map<String, Object> component3 = eVar.component3();
        if (component1 && component3 != null && (!component3.isEmpty())) {
            JSONObject jSONObject = new JSONObject(component3);
            Iterator<String> keys = jSONObject.keys();
            Intrinsics.checkNotNullExpressionValue(keys, "json.keys()");
            while (keys.hasNext()) {
                String k = keys.next();
                try {
                    com.bytedance.ttgame.library.luffy.adapter.d mainStorage$luffy_release = this.f14183c.getMainStorage$luffy_release();
                    Intrinsics.checkNotNullExpressionValue(k, "k");
                    mainStorage$luffy_release.putString("_gsdk_remote_config_repo", k, jSONObject.optString(k));
                } catch (Throwable th) {
                    k.INSTANCE.e(this.f14181a, "storeRequestConfigs failed, " + th);
                }
                try {
                    com.bytedance.ttgame.library.luffy.core.a aVar = this.f14184d.get(k);
                    if (aVar != null) {
                        aVar.onUpdate();
                        this.f14184d.remove(k);
                        k.INSTANCE.v(this.f14181a, "call back for " + k + " when requested");
                    }
                } catch (Throwable unused) {
                    k.INSTANCE.e(this.f14181a, "callback failed");
                }
            }
            k.INSTANCE.d(this.f14181a, "successfully store " + component3.size() + " configs.");
            return CollectionsKt.toList(component3.keySet());
        }
        return CollectionsKt.emptyList();
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public Map<String, Object> getAll(String repo) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        return this.f14183c.getAll(repo);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public String getString(String repo, String key, String str) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        return this.f14183c.getString(repo, key, str);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public int getInt(String repo, String key, int i2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        return this.f14183c.getInt(repo, key, i2);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public long getLong(String repo, String key, long j2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        return this.f14183c.getLong(repo, key, j2);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public float getFloat(String repo, String key, float f2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        return this.f14183c.getFloat(repo, key, f2);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public boolean getBoolean(String repo, String key, boolean z) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        return this.f14183c.getBoolean(repo, key, z);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public byte[] getBytes(String repo, String key, byte[] defValue) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defValue, "defValue");
        return this.f14183c.getBytes(repo, key, defValue);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public Set<String> getStringSet(String repo, String key, Set<String> defValues) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defValues, "defValues");
        return this.f14183c.getStringSet(repo, key, defValues);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putString(String repo, String key, String str) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        this.f14183c.putString(repo, key, str);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putInt(String repo, String key, int i2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        this.f14183c.putInt(repo, key, i2);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putLong(String repo, String key, long j2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        this.f14183c.putLong(repo, key, j2);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putFloat(String repo, String key, float f2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        this.f14183c.putFloat(repo, key, f2);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putBoolean(String repo, String key, boolean z) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        this.f14183c.putBoolean(repo, key, z);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putBytes(String repo, String key, byte[] value) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        this.f14183c.putBytes(repo, key, value);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putStringSet(String repo, String str, Set<String> set) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        this.f14183c.putStringSet(repo, str, set);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void remove(String repo, String key) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        this.f14183c.remove(repo, key);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public boolean contains(String repo, String key) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        return this.f14183c.contains(repo, key);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void clear(String repo) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        this.f14183c.clear(repo);
    }
}
