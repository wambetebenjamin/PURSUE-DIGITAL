package com.bytedance.ttgame.library.luffy.extensions;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;
import com.bytedance.ttgame.library.luffy.adapter.d;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
/* compiled from: LuffySharePreferenceStorage.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0004H\u0016J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0016J\u001c\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u000b\u001a\u00020\u0004H\u0016J \u0010\u0012\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\rH\u0016J \u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0015H\u0016J \u0010\u0016\u001a\u00020\u00172\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0017H\u0016J \u0010\u0018\u001a\u00020\u00192\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0019H\u0016J \u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u001bH\u0016J\u0012\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0004H\u0002J$\u0010\u001f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u0004H\u0016J,\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00040!2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00040!H\u0016J(\u0010\u0007\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010#\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&H\u0016J \u0010'\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\rH\u0016J \u0010)\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0015H\u0016J \u0010*\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0017H\u0016J \u0010+\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u0019H\u0016J \u0010,\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\u0006\u0010(\u001a\u00020\u001bH\u0016J\"\u0010-\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u00042\b\u0010(\u001a\u0004\u0018\u00010\u0004H\u0016J,\u0010.\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\b\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0010\u0010/\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010!H\u0016J\u0018\u00100\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/bytedance/ttgame/library/luffy/extensions/LuffySharePreferenceStorage;", "Lcom/bytedance/ttgame/library/luffy/adapter/IStorage;", "()V", "TAG", "", "context", "Landroid/content/Context;", "init", "Ljava/util/concurrent/atomic/AtomicBoolean;", "clear", "", "repo", "contains", "", "key", "getAll", "", "", "getBoolean", "defValue", "getBytes", "", "getFloat", "", "getInt", "", "getLong", "", "getRepo", "Landroid/content/SharedPreferences;", "name", "getString", "getStringSet", "", "defValues", "preloads", "", "executor", "Ljava/util/concurrent/Executor;", "putBoolean", "value", "putBytes", "putFloat", "putInt", "putLong", "putString", "putStringSet", "values", "remove", "luffy_extensions_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public final class c implements d {

    /* renamed from: a  reason: collision with root package name */
    private final String f14225a = "LuffySPStorage";

    /* renamed from: b  reason: collision with root package name */
    private final AtomicBoolean f14226b = new AtomicBoolean(false);

    /* renamed from: c  reason: collision with root package name */
    private Context f14227c;

    @Override // com.bytedance.ttgame.library.luffy.adapter.d
    public void init(Context context, List<String> list, Executor executor) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(executor, "executor");
        if (this.f14226b.compareAndSet(false, true)) {
            this.f14227c = context.getApplicationContext();
            if (list == null || !(!list.isEmpty())) {
                return;
            }
            for (String str : list) {
                a(str);
            }
        }
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public Map<String, Object> getAll(String repo) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Map<String, ?> all = a(repo).getAll();
        Objects.requireNonNull(all, "null cannot be cast to non-null type kotlin.collections.Map<kotlin.String, kotlin.Any>");
        return all;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public String getString(String repo, String key, String str) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        String string = a(repo).getString(key, str);
        return string != null ? string : str;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public int getInt(String repo, String key, int i2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        return a(repo).getInt(key, i2);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public long getLong(String repo, String key, long j2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        return a(repo).getLong(key, j2);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public float getFloat(String repo, String key, float f2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        return a(repo).getFloat(key, f2);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public boolean getBoolean(String repo, String key, boolean z) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        return a(repo).getBoolean(key, z);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public byte[] getBytes(String repo, String key, byte[] defValue) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defValue, "defValue");
        String string = getString(repo, key, "");
        if (string != null) {
            if (!(string.length() == 0)) {
                try {
                    byte[] bytes = string.getBytes(Charsets.UTF_8);
                    Intrinsics.checkNotNullExpressionValue(bytes, "(this as java.lang.String).getBytes(charset)");
                    byte[] decode = Base64.decode(bytes, 0);
                    if (decode != null) {
                        return decode;
                    }
                } catch (Throwable th) {
                    Log.e(this.f14225a, "getBytes error " + th);
                }
            }
        }
        return defValue;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public Set<String> getStringSet(String repo, String key, Set<String> defValues) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(defValues, "defValues");
        Set<String> stringSet = a(repo).getStringSet(key, defValues);
        return stringSet != null ? stringSet : defValues;
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putString(String repo, String key, String str) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        a(repo).edit().putString(key, str).apply();
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putInt(String repo, String key, int i2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        a(repo).edit().putInt(key, i2).apply();
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putLong(String repo, String key, long j2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        a(repo).edit().putLong(key, j2).apply();
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putFloat(String repo, String key, float f2) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        a(repo).edit().putFloat(key, f2).apply();
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putBoolean(String repo, String key, boolean z) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        a(repo).edit().putBoolean(key, z).apply();
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putBytes(String repo, String key, byte[] value) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        Intrinsics.checkNotNullParameter(value, "value");
        try {
            byte[] encode = Base64.encode(value, 0);
            Intrinsics.checkNotNullExpressionValue(encode, "Base64.encode(value, 0)");
            putString(repo, key, new String(encode, Charsets.UTF_8));
        } catch (Throwable th) {
            Log.e(this.f14225a, "putBytes error " + th);
        }
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void putStringSet(String repo, String str, Set<String> set) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        a(repo).edit().putStringSet(str, set).apply();
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void remove(String repo, String key) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        a(repo).edit().remove(key).apply();
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public boolean contains(String repo, String key) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        Intrinsics.checkNotNullParameter(key, "key");
        return a(repo).contains(key);
    }

    @Override // com.bytedance.ttgame.library.luffy.adapter.a
    public void clear(String repo) {
        Intrinsics.checkNotNullParameter(repo, "repo");
        a(repo).edit().clear().apply();
    }

    private final SharedPreferences a(String str) {
        Context context = this.f14227c;
        SharedPreferences sharedPreferences = context != null ? context.getSharedPreferences(str, 0) : null;
        Intrinsics.checkNotNull(sharedPreferences);
        return sharedPreferences;
    }
}
