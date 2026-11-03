package com.bytedance.push.settings.storage;

import android.content.Context;
import android.content.SharedPreferences;
/* compiled from: Storage.java */
/* loaded from: classes2.dex */
public interface i {
    boolean contains(String str);

    SharedPreferences.Editor edit();

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

    void registerValChanged(Context context, String str, String str2, com.bytedance.push.settings.b bVar);

    void unregisterValChanged(com.bytedance.push.settings.b bVar);
}
