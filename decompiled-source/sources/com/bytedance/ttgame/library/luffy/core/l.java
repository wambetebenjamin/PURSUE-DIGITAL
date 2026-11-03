package com.bytedance.ttgame.library.luffy.core;

import android.content.Context;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: LuffyImpl.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0080\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000 ?2\u00020\u0001:\u0001?B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0016J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0004H\u0016J\u0018\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004H\u0016J\u001c\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0012\u001a\u00020\u0004H\u0016J \u0010\u0017\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\bH\u0016J \u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u001aH\u0016J \u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u001cH\u0016J \u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020\u001eH\u0016J \u0010\u001f\u001a\u00020 2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u0018\u001a\u00020 H\u0016J\n\u0010!\u001a\u0004\u0018\u00010\nH\u0002J$\u0010\"\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\b\u0010\u0018\u001a\u0004\u0018\u00010\u0004H\u0016J,\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00040$2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00040$H\u0016J\u001c\u0010&\u001a\u00020\f2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010'\u001a\u0004\u0018\u00010(H\u0016J\u0018\u0010)\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\bH\u0016J\u0018\u0010+\u001a\u00020,2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010*\u001a\u00020,H\u0016J\u0018\u0010-\u001a\u00020\u001e2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u001eH\u0016J\u0010\u0010.\u001a\u00020/2\u0006\u0010\r\u001a\u00020\u0004H\u0016J\u0010\u00100\u001a\u0002012\u0006\u0010\r\u001a\u00020\u0004H\u0016J\u0018\u00102\u001a\u00020 2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010*\u001a\u00020 H\u0016J\u0018\u00103\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u0004H\u0016J \u00104\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u00105\u001a\u00020\bH\u0016J \u00106\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u001aH\u0016J \u00107\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u001cH\u0016J \u00108\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u00105\u001a\u00020\u001eH\u0016J \u00109\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u00105\u001a\u00020 H\u0016J\"\u0010:\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\b\u00105\u001a\u0004\u0018\u00010\u0004H\u0016J,\u0010;\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\u00042\u0010\u0010<\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010$H\u0016J\u0018\u0010=\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004H\u0016J\b\u0010>\u001a\u00020\fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006@"}, d2 = {"Lcom/bytedance/ttgame/library/luffy/core/LuffyImpl;", "Lcom/bytedance/ttgame/library/luffy/Luffy;", "()V", "TAG", "", "context", "Landroid/content/Context;", "main", "", "remoteService", "Lcom/bytedance/ttgame/library/luffy/core/IInternalService;", "addRemoteUpdateListener", "", "key", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/bytedance/ttgame/library/luffy/core/RemoteConfigListener;", "callbackOnMain", "clear", "repo", "contains", "getAll", "", "", "getBoolean", "defValue", "getBytes", "", "getFloat", "", "getInt", "", "getLong", "", "getRemoteService", "getString", "getStringSet", "", "defValues", "init", "config", "Lcom/bytedance/ttgame/library/luffy/InitConfig;", "optBoolean", "fallback", "optDouble", "", "optInt", "optJsonArray", "Lorg/json/JSONArray;", "optJsonObject", "Lorg/json/JSONObject;", "optLong", "optString", "putBoolean", "value", "putBytes", "putFloat", "putInt", "putLong", "putString", "putStringSet", "values", "remove", "update", "Companion", "luffy_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public final class l extends com.bytedance.ttgame.library.luffy.c {
    public static final a Companion = new a(null);

    /* renamed from: e  reason: collision with root package name */
    private static final l f14201e = new l();

    /* renamed from: a  reason: collision with root package name */
    private Context f14202a;

    /* renamed from: b  reason: collision with root package name */
    private final String f14203b = "LuffyImpl";

    /* renamed from: c  reason: collision with root package name */
    private boolean f14204c = true;

    /* renamed from: d  reason: collision with root package name */
    private volatile d f14205d;

    /* compiled from: LuffyImpl.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/bytedance/ttgame/library/luffy/core/LuffyImpl$Companion;", "", "()V", "instance", "Lcom/bytedance/ttgame/library/luffy/core/LuffyImpl;", "getInstance", "()Lcom/bytedance/ttgame/library/luffy/core/LuffyImpl;", "luffy_release"}, k = 1, mv = {1, 4, 0})
    /* loaded from: classes3.dex */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final l getInstance() {
            return l.f14201e;
        }
    }

    private final d a() {
        return this.f14205d;
    }

    @Override // com.bytedance.ttgame.library.luffy.core.c
    public void init(Context context, com.bytedance.ttgame.library.luffy.b bVar) {
        Intrinsics.checkNotNull(context);
        this.f14202a = context.getApplicationContext();
        Intrinsics.checkNotNull(bVar);
        boolean mainProcess = bVar.getMainProcess();
        this.f14204c = mainProcess;
        if (mainProcess) {
            this.f14205d = new g(context, bVar);
            k.INSTANCE.d(this.f14203b, "do sdk init on main process");
            return;
        }
        this.f14205d = new h(context, bVar);
        k.INSTANCE.w(this.f14203b, "do sdk init on sub process");
    }

    @Override // com.bytedance.ttgame.library.luffy.core.c
    public void update() {
        d a2 = a();
        if (a2 != null) {
            a2.update();
        }
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public Map<String, Object> getAll(String repo) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        HashMap hashMap = new HashMap();
        if (a() != null) {
            try {
                d a2 = a();
                Map<String, Object> all = a2 != null ? a2.getAll(repo) : null;
                if (all != null) {
                    return all;
                }
                throw new NullPointerException("null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any>");
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14203b, String.valueOf(th));
            }
        } else {
            k.INSTANCE.e(this.f14203b, "service died or null", "getAll", "repo: " + repo);
        }
        return hashMap;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public String getString(String repo, String key, String str) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        if (a() != null) {
            try {
                d a2 = a();
                if (a2 != null) {
                    String string = a2.getString(repo, key, str);
                    return string != null ? string : str;
                }
                return str;
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14203b, String.valueOf(th));
                return str;
            }
        }
        k.INSTANCE.e(this.f14203b, "service died or null", "getString", repo + '+' + key);
        return str;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public int getInt(String repo, String key, int i2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        if (a() != null) {
            try {
                d a2 = a();
                return a2 != null ? a2.getInt(repo, key, i2) : i2;
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14203b, String.valueOf(th));
                return i2;
            }
        }
        k.INSTANCE.e(this.f14203b, "service died or null", "getInt", repo + '+' + key);
        return i2;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public long getLong(String repo, String key, long j2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        if (a() != null) {
            try {
                d a2 = a();
                return a2 != null ? a2.getLong(repo, key, j2) : j2;
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14203b, String.valueOf(th));
                return j2;
            }
        }
        k.INSTANCE.e(this.f14203b, "service died or null", "getLong", repo + '+' + key);
        return j2;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public float getFloat(String repo, String key, float f2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        if (a() != null) {
            try {
                d a2 = a();
                return a2 != null ? a2.getFloat(repo, key, f2) : f2;
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14203b, String.valueOf(th));
                return f2;
            }
        }
        k.INSTANCE.e(this.f14203b, "service died or null", "getFloat", repo + '+' + key);
        return f2;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public boolean getBoolean(String repo, String key, boolean z) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        if (a() != null) {
            try {
                d a2 = a();
                return a2 != null ? a2.getBoolean(repo, key, z) : z;
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14203b, String.valueOf(th));
                return z;
            }
        }
        k.INSTANCE.e(this.f14203b, "service died or null", "getBoolean", repo + '+' + key);
        return z;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public byte[] getBytes(String repo, String key, byte[] defValue) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defValue, "defValue");
        if (a() != null) {
            try {
                d a2 = a();
                if (a2 != null) {
                    byte[] bytes = a2.getBytes(repo, key, defValue);
                    return bytes != null ? bytes : defValue;
                }
                return defValue;
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14203b, String.valueOf(th));
                return defValue;
            }
        }
        k.INSTANCE.e(this.f14203b, "service died or null", "getBytes", repo + '+' + key);
        return defValue;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public Set<String> getStringSet(String repo, String key, Set<String> defValues) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defValues, "defValues");
        if (a() != null) {
            try {
                d a2 = a();
                if (a2 != null) {
                    Set<String> stringSet = a2.getStringSet(repo, key, defValues);
                    return stringSet != null ? stringSet : defValues;
                }
                return defValues;
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14203b, String.valueOf(th));
                return defValues;
            }
        }
        k.INSTANCE.e(this.f14203b, "service died or null", "getStringSet", repo + '+' + key);
        return defValues;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putString(String repo, String key, String str) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        if (a() != null) {
            try {
                d a2 = a();
                if (a2 != null) {
                    a2.putString(repo, key, str);
                    return;
                }
                return;
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14203b, String.valueOf(th));
                return;
            }
        }
        k.INSTANCE.e(this.f14203b, "service died or null", "putString", repo + '+' + key);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putInt(String repo, String key, int i2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        if (a() != null) {
            try {
                d a2 = a();
                if (a2 != null) {
                    a2.putInt(repo, key, i2);
                    return;
                }
                return;
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14203b, String.valueOf(th));
                return;
            }
        }
        k.INSTANCE.e(this.f14203b, "service died or null", "putInt", repo + '+' + key);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putLong(String repo, String key, long j2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        if (a() != null) {
            try {
                d a2 = a();
                if (a2 != null) {
                    a2.putLong(repo, key, j2);
                    return;
                }
                return;
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14203b, String.valueOf(th));
                return;
            }
        }
        k.INSTANCE.e(this.f14203b, "service died or null", "putLong", repo + '+' + key);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putFloat(String repo, String key, float f2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        if (a() != null) {
            try {
                d a2 = a();
                if (a2 != null) {
                    a2.putFloat(repo, key, f2);
                    return;
                }
                return;
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14203b, String.valueOf(th));
                return;
            }
        }
        k.INSTANCE.e(this.f14203b, "service died or null", "putFloat", repo + '+' + key);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putBoolean(String repo, String key, boolean z) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        if (a() != null) {
            try {
                d a2 = a();
                if (a2 != null) {
                    a2.putBoolean(repo, key, z);
                    return;
                }
                return;
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14203b, String.valueOf(th));
                return;
            }
        }
        k.INSTANCE.e(this.f14203b, "service died or null", "putBoolean", repo + '+' + key);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putBytes(String repo, String key, byte[] value) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        if (a() != null) {
            try {
                d a2 = a();
                if (a2 != null) {
                    a2.putBytes(repo, key, value);
                    return;
                }
                return;
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14203b, String.valueOf(th));
                return;
            }
        }
        k.INSTANCE.e(this.f14203b, "service died or null", "putBytes", repo + '+' + key);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putStringSet(String repo, String str, Set<String> set) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        if (a() != null) {
            try {
                d a2 = a();
                if (a2 != null) {
                    a2.putStringSet(repo, str, set);
                    return;
                }
                return;
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14203b, String.valueOf(th));
                return;
            }
        }
        k.INSTANCE.e(this.f14203b, "service died or null", "putStringSet", repo + '+' + str);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void remove(String repo, String key) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        if (a() != null) {
            try {
                d a2 = a();
                if (a2 != null) {
                    a2.remove(repo, key);
                    return;
                }
                return;
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14203b, String.valueOf(th));
                return;
            }
        }
        k.INSTANCE.e(this.f14203b, "service died or null", "remove", repo + '+' + key);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public boolean contains(String repo, String key) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        if (a() != null) {
            try {
                d a2 = a();
                if (a2 != null) {
                    return a2.contains(repo, key);
                }
                return false;
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14203b, String.valueOf(th));
            }
        } else {
            k.INSTANCE.e(this.f14203b, "service died or null", "contains", repo + '+' + key);
        }
        return false;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void clear(String repo) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        try {
            d a2 = a();
            if (a2 != null) {
                a2.clear(repo);
            }
        } catch (Throwable th) {
            k.INSTANCE.e(this.f14203b, String.valueOf(th));
        }
    }

    @Override // com.bytedance.ttgame.library.luffy.core.e
    public void addRemoteUpdateListener(String key, m mVar, boolean z) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (a() != null) {
            try {
                d a2 = a();
                if (a2 != null) {
                    a2.addRemoteUpdateListener(key, new com.bytedance.ttgame.library.luffy.core.a(mVar, z));
                    return;
                }
                return;
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14203b, String.valueOf(th));
                return;
            }
        }
        k.INSTANCE.e(this.f14203b, "service died or null", "addRemoteUpdateListener");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002f A[Catch: all -> 0x003c, TryCatch #0 {all -> 0x003c, blocks: (B:4:0x000e, B:6:0x0014, B:8:0x001e, B:10:0x0023, B:16:0x002f, B:17:0x0035), top: B:25:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0035 A[Catch: all -> 0x003c, TRY_LEAVE, TryCatch #0 {all -> 0x003c, blocks: (B:4:0x000e, B:6:0x0014, B:8:0x001e, B:10:0x0023, B:16:0x002f, B:17:0x0035), top: B:25:0x000e }] */
    @Override // com.bytedance.ttgame.library.luffy.core.f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.json.JSONObject optJsonObject(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = "key"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            com.bytedance.ttgame.library.luffy.core.d r0 = r6.a()
            r1 = 2
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L4f
            com.bytedance.ttgame.library.luffy.core.d r0 = r6.a()     // Catch: java.lang.Throwable -> L3c
            if (r0 == 0) goto L1d
            java.lang.String r4 = "_gsdk_remote_config_repo"
            java.lang.String r5 = ""
            java.lang.String r7 = r0.getString(r4, r7, r5)     // Catch: java.lang.Throwable -> L3c
            goto L1e
        L1d:
            r7 = 0
        L1e:
            r0 = r7
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0     // Catch: java.lang.Throwable -> L3c
            if (r0 == 0) goto L2c
            int r0 = r0.length()     // Catch: java.lang.Throwable -> L3c
            if (r0 != 0) goto L2a
            goto L2c
        L2a:
            r0 = r3
            goto L2d
        L2c:
            r0 = r2
        L2d:
            if (r0 == 0) goto L35
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L3c
            r7.<init>()     // Catch: java.lang.Throwable -> L3c
            goto L3b
        L35:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch: java.lang.Throwable -> L3c
            r0.<init>(r7)     // Catch: java.lang.Throwable -> L3c
            r7 = r0
        L3b:
            return r7
        L3c:
            r7 = move-exception
            com.bytedance.ttgame.library.luffy.core.k r0 = com.bytedance.ttgame.library.luffy.core.k.INSTANCE
            java.lang.String[] r1 = new java.lang.String[r1]
            java.lang.String r4 = r6.f14203b
            r1[r3] = r4
            java.lang.String r7 = java.lang.String.valueOf(r7)
            r1[r2] = r7
            r0.e(r1)
            goto L63
        L4f:
            com.bytedance.ttgame.library.luffy.core.k r7 = com.bytedance.ttgame.library.luffy.core.k.INSTANCE
            r0 = 3
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.String r4 = r6.f14203b
            r0[r3] = r4
            java.lang.String r3 = "service died or null"
            r0[r2] = r3
            java.lang.String r2 = "optJsonObject"
            r0[r1] = r2
            r7.e(r0)
        L63:
            org.json.JSONObject r7 = new org.json.JSONObject
            r7.<init>()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.ttgame.library.luffy.core.l.optJsonObject(java.lang.String):org.json.JSONObject");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x002f A[Catch: all -> 0x003c, TryCatch #0 {all -> 0x003c, blocks: (B:4:0x000e, B:6:0x0014, B:8:0x001e, B:10:0x0023, B:16:0x002f, B:17:0x0035), top: B:25:0x000e }] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0035 A[Catch: all -> 0x003c, TRY_LEAVE, TryCatch #0 {all -> 0x003c, blocks: (B:4:0x000e, B:6:0x0014, B:8:0x001e, B:10:0x0023, B:16:0x002f, B:17:0x0035), top: B:25:0x000e }] */
    @Override // com.bytedance.ttgame.library.luffy.core.f
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public org.json.JSONArray optJsonArray(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = "key"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            com.bytedance.ttgame.library.luffy.core.d r0 = r6.a()
            r1 = 2
            r2 = 1
            r3 = 0
            if (r0 == 0) goto L4f
            com.bytedance.ttgame.library.luffy.core.d r0 = r6.a()     // Catch: java.lang.Throwable -> L3c
            if (r0 == 0) goto L1d
            java.lang.String r4 = "_gsdk_remote_config_repo"
            java.lang.String r5 = ""
            java.lang.String r7 = r0.getString(r4, r7, r5)     // Catch: java.lang.Throwable -> L3c
            goto L1e
        L1d:
            r7 = 0
        L1e:
            r0 = r7
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0     // Catch: java.lang.Throwable -> L3c
            if (r0 == 0) goto L2c
            int r0 = r0.length()     // Catch: java.lang.Throwable -> L3c
            if (r0 != 0) goto L2a
            goto L2c
        L2a:
            r0 = r3
            goto L2d
        L2c:
            r0 = r2
        L2d:
            if (r0 == 0) goto L35
            org.json.JSONArray r7 = new org.json.JSONArray     // Catch: java.lang.Throwable -> L3c
            r7.<init>()     // Catch: java.lang.Throwable -> L3c
            goto L3b
        L35:
            org.json.JSONArray r0 = new org.json.JSONArray     // Catch: java.lang.Throwable -> L3c
            r0.<init>(r7)     // Catch: java.lang.Throwable -> L3c
            r7 = r0
        L3b:
            return r7
        L3c:
            r7 = move-exception
            com.bytedance.ttgame.library.luffy.core.k r0 = com.bytedance.ttgame.library.luffy.core.k.INSTANCE
            java.lang.String[] r1 = new java.lang.String[r1]
            java.lang.String r4 = r6.f14203b
            r1[r3] = r4
            java.lang.String r7 = java.lang.String.valueOf(r7)
            r1[r2] = r7
            r0.e(r1)
            goto L63
        L4f:
            com.bytedance.ttgame.library.luffy.core.k r7 = com.bytedance.ttgame.library.luffy.core.k.INSTANCE
            r0 = 3
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.String r4 = r6.f14203b
            r0[r3] = r4
            java.lang.String r3 = "service died or null"
            r0[r2] = r3
            java.lang.String r2 = "optJsonArray"
            r0[r1] = r2
            r7.e(r0)
        L63:
            org.json.JSONArray r7 = new org.json.JSONArray
            r7.<init>()
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.ttgame.library.luffy.core.l.optJsonArray(java.lang.String):org.json.JSONArray");
    }

    @Override // com.bytedance.ttgame.library.luffy.core.f
    public String optString(String key, String fallback) {
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(fallback, "fallback");
        if (a() != null) {
            try {
                d a2 = a();
                String string = a2 != null ? a2.getString("_gsdk_remote_config_repo", key, "") : null;
                return string != null ? string : fallback;
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14203b, String.valueOf(th));
            }
        } else {
            k.INSTANCE.e(this.f14203b, "service died or null", "optString");
        }
        return fallback;
    }

    @Override // com.bytedance.ttgame.library.luffy.core.f
    public boolean optBoolean(String key, boolean z) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (a() != null) {
            try {
                d a2 = a();
                Boolean bool = i.INSTANCE.toBoolean(a2 != null ? a2.getString("_gsdk_remote_config_repo", key, "") : null);
                return bool != null ? bool.booleanValue() : z;
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14203b, String.valueOf(th));
            }
        } else {
            k.INSTANCE.e(this.f14203b, "service died or null", "optBoolean");
        }
        return z;
    }

    @Override // com.bytedance.ttgame.library.luffy.core.f
    public double optDouble(String key, double d2) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (a() != null) {
            try {
                d a2 = a();
                Double d3 = i.INSTANCE.toDouble(a2 != null ? a2.getString("_gsdk_remote_config_repo", key, "") : null);
                return d3 != null ? d3.doubleValue() : d2;
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14203b, String.valueOf(th));
            }
        } else {
            k.INSTANCE.e(this.f14203b, "service died or null", "optDouble");
        }
        return d2;
    }

    @Override // com.bytedance.ttgame.library.luffy.core.f
    public int optInt(String key, int i2) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (a() != null) {
            try {
                d a2 = a();
                Integer integer = i.INSTANCE.toInteger(a2 != null ? a2.getString("_gsdk_remote_config_repo", key, "") : null);
                return integer != null ? integer.intValue() : i2;
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14203b, String.valueOf(th));
            }
        } else {
            k.INSTANCE.e(this.f14203b, "service died or null", "optInt");
        }
        return i2;
    }

    @Override // com.bytedance.ttgame.library.luffy.core.f
    public long optLong(String key, long j2) {
        Intrinsics.checkNotNullParameter(key, "key");
        if (a() != null) {
            try {
                d a2 = a();
                Long l = i.INSTANCE.toLong(a2 != null ? a2.getString("_gsdk_remote_config_repo", key, "") : null);
                return l != null ? l.longValue() : j2;
            } catch (Throwable th) {
                k.INSTANCE.e(this.f14203b, String.valueOf(th));
            }
        } else {
            k.INSTANCE.e(this.f14203b, "service died or null", "optLong");
        }
        return j2;
    }
}
