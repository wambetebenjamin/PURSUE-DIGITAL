package com.bytedance.push.settings;

import android.content.Context;
/* loaded from: classes2.dex */
public interface ILocalSettings {
    void registerValChanged(Context context, String str, String str2, b bVar);

    void unregisterValChanged(b bVar);
}
