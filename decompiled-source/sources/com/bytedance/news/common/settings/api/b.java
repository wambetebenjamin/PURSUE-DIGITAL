package com.bytedance.news.common.settings.api;

import java.util.Set;
/* compiled from: Migration.java */
/* loaded from: classes2.dex */
public interface b {
    boolean contains(String str);

    boolean getBoolean(String str);

    float getFloat(String str);

    int getInt(String str);

    long getLong(String str);

    String getString(String str);

    Set<String> getStringSet(String str);
}
