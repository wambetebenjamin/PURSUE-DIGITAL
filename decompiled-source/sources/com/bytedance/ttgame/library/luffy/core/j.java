package com.bytedance.ttgame.library.luffy.core;

import android.content.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: LevelStorage.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0001\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nH\u0016J,\u0010\u000e\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u00100\u000fj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0010`\u00112\u0006\u0010\t\u001a\u00020\nH\u0016J \u0010\u0012\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\fH\u0016J \u0010\u0014\u001a\u00020\u00152\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0015H\u0016J \u0010\u0016\u001a\u00020\u00172\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0017H\u0016J \u0010\u0018\u001a\u00020\u00192\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u0019H\u0016J \u0010\u001a\u001a\u00020\u001b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010\u0013\u001a\u00020\u001bH\u0016J\r\u0010\u001c\u001a\u00020\u0001H\u0000¢\u0006\u0002\b\u001dJ$\u0010\u001e\u001a\u0004\u0018\u00010\n2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\b\u0010\u0013\u001a\u0004\u0018\u00010\nH\u0016J,\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\n0 2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\n0 H\u0016J(\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020$2\u000e\u0010%\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00052\u0006\u0010&\u001a\u00020'H\u0016J \u0010(\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010)\u001a\u00020\fH\u0016J \u0010*\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010)\u001a\u00020\u0015H\u0016J \u0010+\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010)\u001a\u00020\u0017H\u0016J \u0010,\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010)\u001a\u00020\u0019H\u0016J \u0010-\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\u0006\u0010)\u001a\u00020\u001bH\u0016J\"\u0010.\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\n2\b\u0010)\u001a\u0004\u0018\u00010\nH\u0016J,\u0010/\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\n2\u0010\u00100\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\n\u0018\u00010 H\u0016J\u0018\u00101\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\nH\u0016R\u000e\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00062"}, d2 = {"Lcom/bytedance/ttgame/library/luffy/core/LevelStorage;", "Lcom/bytedance/ttgame/library/luffy/adapter/IStorage;", "Lcom/bytedance/ttgame/library/luffy/adapter/IConfig;", "mainStorage", "secondaryStorage", "", "(Lcom/bytedance/ttgame/library/luffy/adapter/IStorage;Ljava/util/List;)V", "clear", "", "repo", "", "contains", "", "key", "getAll", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "getBoolean", "defValue", "getBytes", "", "getFloat", "", "getInt", "", "getLong", "", "getMainStorage", "getMainStorage$luffy_release", "getString", "getStringSet", "", "defValues", "init", "context", "Landroid/content/Context;", "preloads", "executor", "Ljava/util/concurrent/Executor;", "putBoolean", "value", "putBytes", "putFloat", "putInt", "putLong", "putString", "putStringSet", "values", "remove", "luffy_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public final class j implements com.bytedance.ttgame.library.luffy.adapter.a, com.bytedance.ttgame.library.luffy.adapter.d {

    /* renamed from: a  reason: collision with root package name */
    private final com.bytedance.ttgame.library.luffy.adapter.d f14198a;

    /* renamed from: b  reason: collision with root package name */
    private final List<com.bytedance.ttgame.library.luffy.adapter.d> f14199b;

    /* JADX WARN: Multi-variable type inference failed */
    public j(com.bytedance.ttgame.library.luffy.adapter.d mainStorage, List<? extends com.bytedance.ttgame.library.luffy.adapter.d> list) {
        Intrinsics.checkNotNullParameter(mainStorage, "mainStorage");
        this.f14198a = mainStorage;
        this.f14199b = list;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.d
    public void init(Context context, List<String> list, Executor executor) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(executor, "executor");
        this.f14198a.init(context, list, executor);
        List<com.bytedance.ttgame.library.luffy.adapter.d> list2 = this.f14199b;
        if (list2 != null) {
            for (com.bytedance.ttgame.library.luffy.adapter.d dVar : list2) {
                dVar.init(context, list, executor);
            }
        }
    }

    public final com.bytedance.ttgame.library.luffy.adapter.d getMainStorage$luffy_release() {
        return this.f14198a;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public HashMap<String, Object> getAll(String repo) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Map<String, Object> all = this.f14198a.getAll(repo);
        Objects.requireNonNull(all, "null cannot be cast to non-null type kotlin.collections.HashMap<kotlin.String, kotlin.Any> /* = java.util.HashMap<kotlin.String, kotlin.Any> */");
        return (HashMap) all;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public String getString(String repo, String key, String str) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        if (this.f14198a.contains(repo, key)) {
            return this.f14198a.getString(repo, key, str);
        }
        List<com.bytedance.ttgame.library.luffy.adapter.d> list = this.f14199b;
        if (list != null) {
            for (com.bytedance.ttgame.library.luffy.adapter.d dVar : list) {
                if (dVar.contains(repo, key)) {
                    String string = dVar.getString(repo, key, str);
                    this.f14198a.putString(repo, key, string);
                    return string;
                }
            }
            return str;
        }
        return str;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public int getInt(String repo, String key, int i2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        if (this.f14198a.contains(repo, key)) {
            return this.f14198a.getInt(repo, key, i2);
        }
        List<com.bytedance.ttgame.library.luffy.adapter.d> list = this.f14199b;
        if (list != null) {
            for (com.bytedance.ttgame.library.luffy.adapter.d dVar : list) {
                if (dVar.contains(repo, key)) {
                    int i3 = dVar.getInt(repo, key, i2);
                    this.f14198a.putInt(repo, key, i3);
                    return i3;
                }
            }
            return i2;
        }
        return i2;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public long getLong(String repo, String key, long j2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        if (this.f14198a.contains(repo, key)) {
            return this.f14198a.getLong(repo, key, j2);
        }
        List<com.bytedance.ttgame.library.luffy.adapter.d> list = this.f14199b;
        if (list != null) {
            for (com.bytedance.ttgame.library.luffy.adapter.d dVar : list) {
                if (dVar.contains(repo, key)) {
                    long j3 = dVar.getLong(repo, key, j2);
                    this.f14198a.putLong(repo, key, j3);
                    return j3;
                }
            }
            return j2;
        }
        return j2;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public float getFloat(String repo, String key, float f2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        if (this.f14198a.contains(repo, key)) {
            return this.f14198a.getFloat(repo, key, f2);
        }
        List<com.bytedance.ttgame.library.luffy.adapter.d> list = this.f14199b;
        if (list != null) {
            for (com.bytedance.ttgame.library.luffy.adapter.d dVar : list) {
                if (dVar.contains(repo, key)) {
                    float f3 = dVar.getFloat(repo, key, f2);
                    this.f14198a.putFloat(repo, key, f3);
                    return f3;
                }
            }
            return f2;
        }
        return f2;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public boolean getBoolean(String repo, String key, boolean z) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        if (this.f14198a.contains(repo, key)) {
            return this.f14198a.getBoolean(repo, key, z);
        }
        List<com.bytedance.ttgame.library.luffy.adapter.d> list = this.f14199b;
        if (list != null) {
            for (com.bytedance.ttgame.library.luffy.adapter.d dVar : list) {
                if (dVar.contains(repo, key)) {
                    boolean z2 = dVar.getBoolean(repo, key, z);
                    this.f14198a.putBoolean(repo, key, z2);
                    return z2;
                }
            }
            return z;
        }
        return z;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public byte[] getBytes(String repo, String key, byte[] defValue) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defValue, "defValue");
        if (this.f14198a.contains(repo, key)) {
            return this.f14198a.getBytes(repo, key, defValue);
        }
        List<com.bytedance.ttgame.library.luffy.adapter.d> list = this.f14199b;
        if (list != null) {
            for (com.bytedance.ttgame.library.luffy.adapter.d dVar : list) {
                if (dVar.contains(repo, key)) {
                    byte[] bytes = dVar.getBytes(repo, key, defValue);
                    this.f14198a.putBytes(repo, key, bytes);
                    return bytes;
                }
            }
            return defValue;
        }
        return defValue;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public Set<String> getStringSet(String repo, String key, Set<String> defValues) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defValues, "defValues");
        if (this.f14198a.contains(repo, key)) {
            return this.f14198a.getStringSet(repo, key, defValues);
        }
        List<com.bytedance.ttgame.library.luffy.adapter.d> list = this.f14199b;
        if (list != null) {
            for (com.bytedance.ttgame.library.luffy.adapter.d dVar : list) {
                if (dVar.contains(repo, key)) {
                    Set<String> stringSet = dVar.getStringSet(repo, key, defValues);
                    this.f14198a.putStringSet(repo, key, stringSet);
                    return stringSet;
                }
            }
            return defValues;
        }
        return defValues;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putString(String repo, String key, String str) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        this.f14198a.putString(repo, key, str);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putInt(String repo, String key, int i2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        this.f14198a.putInt(repo, key, i2);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putLong(String repo, String key, long j2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        this.f14198a.putLong(repo, key, j2);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putFloat(String repo, String key, float f2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        this.f14198a.putFloat(repo, key, f2);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putBoolean(String repo, String key, boolean z) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        this.f14198a.putBoolean(repo, key, z);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putBytes(String repo, String key, byte[] value) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        this.f14198a.putBytes(repo, key, value);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putStringSet(String repo, String str, Set<String> set) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        this.f14198a.putStringSet(repo, str, set);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void remove(String repo, String key) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        this.f14198a.remove(repo, key);
        List<com.bytedance.ttgame.library.luffy.adapter.d> list = this.f14199b;
        if (list != null) {
            for (com.bytedance.ttgame.library.luffy.adapter.d dVar : list) {
                dVar.remove(repo, key);
            }
        }
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public boolean contains(String repo, String key) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        if (this.f14198a.contains(repo, key)) {
            return true;
        }
        List<com.bytedance.ttgame.library.luffy.adapter.d> list = this.f14199b;
        if (list != null) {
            for (com.bytedance.ttgame.library.luffy.adapter.d dVar : list) {
                if (dVar.contains(repo, key)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void clear(String repo) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        this.f14198a.clear(repo);
        List<com.bytedance.ttgame.library.luffy.adapter.d> list = this.f14199b;
        if (list != null) {
            for (com.bytedance.ttgame.library.luffy.adapter.d dVar : list) {
                dVar.clear(repo);
            }
        }
    }
}
