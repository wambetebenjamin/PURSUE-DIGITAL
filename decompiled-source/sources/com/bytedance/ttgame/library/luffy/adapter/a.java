package com.bytedance.ttgame.library.luffy.adapter;

import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
/* compiled from: IConfig.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\f\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H&J\u001c\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\n2\u0006\u0010\u0004\u001a\u00020\u0005H&J \u0010\u000b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0007H&J \u0010\r\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u000eH&J \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0010H&J \u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0012H&J \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0014H&J$\u0010\u0015\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\b\u0010\f\u001a\u0004\u0018\u00010\u0005H&J,\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00050\u00172\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u0017H&J \u0010\u0019\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0007H&J \u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u000eH&J \u0010\u001c\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0010H&J \u0010\u001d\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0012H&J \u0010\u001e\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u0014H&J\"\u0010\u001f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\b\u0010\u001a\u001a\u0004\u0018\u00010\u0005H&J,\u0010 \u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\b\u001a\u0004\u0018\u00010\u00052\u0010\u0010!\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0005\u0018\u00010\u0017H&J\u0018\u0010\"\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H&¨\u0006#"}, d2 = {"Lcom/bytedance/ttgame/library/luffy/adapter/IConfig;", "", "clear", "", "repo", "", "contains", "", "key", "getAll", "", "getBoolean", "defValue", "getBytes", "", "getFloat", "", "getInt", "", "getLong", "", "getString", "getStringSet", "", "defValues", "putBoolean", "value", "putBytes", "putFloat", "putInt", "putLong", "putString", "putStringSet", "values", "remove", "luffy_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public interface a {
    void clear(String str);

    boolean contains(String str, String str2);

    Map<String, Object> getAll(String str);

    boolean getBoolean(String str, String str2, boolean z);

    byte[] getBytes(String str, String str2, byte[] bArr);

    float getFloat(String str, String str2, float f2);

    int getInt(String str, String str2, int i2);

    long getLong(String str, String str2, long j2);

    String getString(String str, String str2, String str3);

    Set<String> getStringSet(String str, String str2, Set<String> set);

    void putBoolean(String str, String str2, boolean z);

    void putBytes(String str, String str2, byte[] bArr);

    void putFloat(String str, String str2, float f2);

    void putInt(String str, String str2, int i2);

    void putLong(String str, String str2, long j2);

    void putString(String str, String str2, String str3);

    void putStringSet(String str, String str2, Set<String> set);

    void remove(String str, String str2);
}
