package com.bytedance.ttgame.library.luffy.extensions;

import android.content.Context;
import android.util.Log;
import com.bytedance.keva.Keva;
import com.bytedance.keva.KevaBuilder;
import com.bytedance.ttgame.library.luffy.adapter.d;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: LuffyKevaStorage.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0004H\u0016J\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0016J\u0012\u0010\u0010\u001a\u00020\u00072\b\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0002J\u0012\u0010\u0011\u001a\u00020\u00072\b\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0002J\u001c\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\f\u001a\u00020\u0004H\u0016J \u0010\u0015\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u000eH\u0016J \u0010\u0017\u001a\u00020\u00182\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u0018H\u0016J \u0010\u0019\u001a\u00020\u001a2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u001aH\u0016J \u0010\u001b\u001a\u00020\u001c2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u001cH\u0016J \u0010\u001d\u001a\u00020\u001e2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u001eH\u0016J\u0012\u0010\u001f\u001a\u00020\u00072\b\u0010\f\u001a\u0004\u0018\u00010\u0004H\u0002J$\u0010 \u001a\u0004\u0018\u00010\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\b\u0010\u0016\u001a\u0004\u0018\u00010\u0004H\u0016J,\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00040\"2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00040\"H\u0016J(\u0010\b\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020%2\u000e\u0010&\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010'2\u0006\u0010(\u001a\u00020)H\u0016J \u0010*\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u000eH\u0016J \u0010,\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u0018H\u0016J \u0010-\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u001aH\u0016J \u0010.\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u001cH\u0016J \u0010/\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010+\u001a\u00020\u001eH\u0016J\"\u00100\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00042\b\u0010+\u001a\u0004\u0018\u00010\u0004H\u0016J,\u00101\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u00042\u0010\u00102\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\"H\u0016J\u0018\u00103\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lcom/bytedance/ttgame/library/luffy/extensions/LuffyKevaStorage;", "Lcom/bytedance/ttgame/library/luffy/adapter/IStorage;", "()V", "TAG", "", "cache", "Ljava/util/concurrent/ConcurrentHashMap;", "Lcom/bytedance/keva/Keva;", "init", "Ljava/util/concurrent/atomic/AtomicBoolean;", "clear", "", "repo", "contains", "", "key", "createRepo", "createRepoSync", "getAll", "", "", "getBoolean", "defValue", "getBytes", "", "getFloat", "", "getInt", "", "getLong", "", "getRepo", "getString", "getStringSet", "", "defValues", "context", "Landroid/content/Context;", "preloads", "", "executor", "Ljava/util/concurrent/Executor;", "putBoolean", "value", "putBytes", "putFloat", "putInt", "putLong", "putString", "putStringSet", "values", "remove", "luffy_extensions_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public final class b implements d {

    /* renamed from: a  reason: collision with root package name */
    private final String f14222a = "LuffyKevaStorage";

    /* renamed from: b  reason: collision with root package name */
    private final AtomicBoolean f14223b = new AtomicBoolean(false);

    /* renamed from: c  reason: collision with root package name */
    private final ConcurrentHashMap<String, Keva> f14224c = new ConcurrentHashMap<>();

    @Override // com.bytedance.ttgame.library.luffy.adapter.d
    public void init(Context context, List<String> list, Executor executor) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(executor, "executor");
        if (this.f14223b.compareAndSet(false, true)) {
            KevaBuilder.getInstance().setContext(context).setExecutor(executor);
            if (list == null || !(!list.isEmpty())) {
                return;
            }
            for (String str : list) {
                b(str);
            }
        }
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public Map<String, Object> getAll(String repo) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Map<String, ?> all = c(repo).getAll();
        Objects.requireNonNull(all, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any>");
        return all;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public String getString(String repo, String key, String str) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        return c(repo).getString(key, str);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public int getInt(String repo, String key, int i2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        return c(repo).getInt(key, i2);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public long getLong(String repo, String key, long j2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        return c(repo).getLong(key, j2);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public float getFloat(String repo, String key, float f2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        return c(repo).getFloat(key, f2);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public boolean getBoolean(String repo, String key, boolean z) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        return c(repo).getBoolean(key, z);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public byte[] getBytes(String repo, String key, byte[] defValue) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defValue, "defValue");
        byte[] bytes = c(repo).getBytes(key, defValue);
        Intrinsics.checkNotNullExpressionValue(bytes, "getRepo(repo).getBytes(key, defValue)");
        return bytes;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public Set<String> getStringSet(String repo, String key, Set<String> defValues) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defValues, "defValues");
        Set<String> stringSet = c(repo).getStringSet(key, defValues);
        Intrinsics.checkNotNullExpressionValue(stringSet, "getRepo(repo).getStringSet(key, defValues)");
        return stringSet;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putString(String repo, String key, String str) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        c(repo).storeString(key, str);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putInt(String repo, String key, int i2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        c(repo).storeInt(key, i2);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putLong(String repo, String key, long j2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        c(repo).storeLong(key, j2);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putFloat(String repo, String key, float f2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        c(repo).storeFloat(key, f2);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putBoolean(String repo, String key, boolean z) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        c(repo).storeBoolean(key, z);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putBytes(String repo, String key, byte[] value) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        c(repo).storeBytes(key, value);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putStringSet(String repo, String str, Set<String> set) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        c(repo).storeStringSet(str, set);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void remove(String repo, String key) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        c(repo).erase(key);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public boolean contains(String repo, String key) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        return c(repo).contains(key);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void clear(String repo) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        c(repo).clear();
    }

    private final Keva a(String str) {
        Keva result = Keva.getRepoSync(str, 1);
        Intrinsics.checkNotNull(str);
        Intrinsics.checkNotNullExpressionValue(result, "result");
        this.f14224c.put(str, result);
        Log.d(this.f14222a, "create keva repo " + str);
        return result;
    }

    private final Keva b(String str) {
        Keva result = Keva.getRepo(str, 1);
        Intrinsics.checkNotNull(str);
        Intrinsics.checkNotNullExpressionValue(result, "result");
        this.f14224c.put(str, result);
        Log.d(this.f14222a, "create keva repo " + str);
        return result;
    }

    private final Keva c(String str) {
        Keva keva = this.f14224c.get(str);
        return keva != null ? keva : a(str);
    }
}
