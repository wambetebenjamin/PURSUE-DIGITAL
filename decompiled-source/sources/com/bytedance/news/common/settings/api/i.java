package com.bytedance.news.common.settings.api;

import java.util.Set;
/* compiled from: Storage.java */
/* loaded from: classes2.dex */
public interface i {
    void apply();

    void clear();

    boolean contains(String str);

    boolean getBoolean(String str);

    boolean getBoolean(String str, boolean z);

    float getFloat(String str);

    float getFloat(String str, float f2);

    int getInt(String str);

    int getInt(String str, int i2);

    long getLong(String str);

    long getLong(String str, long j2);

    String getString(String str);

    String getString(String str, String str2);

    Set<String> getStringSet(String str);

    Set<String> getStringSet(String str, Set<String> set);

    void putBoolean(String str, boolean z);

    void putFloat(String str, float f2);

    void putInt(String str, int i2);

    void putLong(String str, long j2);

    void putString(String str, String str2);

    void putStringSet(String str, Set<String> set);

    void remove(String str);
}
