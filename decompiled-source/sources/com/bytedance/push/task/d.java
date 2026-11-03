package com.bytedance.push.task;

import android.content.Context;
import com.bytedance.push.settings.LocalFrequencySettings;
import com.bytedance.push.settings.SettingsManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* compiled from: TokenStorage.java */
/* loaded from: classes2.dex */
final class d {

    /* renamed from: a  reason: collision with root package name */
    private static Map<Integer, com.bytedance.push.model.d> f11975a;

    d() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized com.bytedance.push.model.d a(Context context, int i2) {
        synchronized (d.class) {
            a(context);
            Map<Integer, com.bytedance.push.model.d> map = f11975a;
            if (map == null) {
                return null;
            }
            return map.get(Integer.valueOf(i2));
        }
    }

    private static void a(Context context) {
        if (f11975a != null) {
            return;
        }
        List<com.bytedance.push.model.d> tokenCache = ((LocalFrequencySettings) SettingsManager.obtain(context, LocalFrequencySettings.class)).getTokenCache();
        if (tokenCache == null) {
            f11975a = new HashMap();
            return;
        }
        HashMap hashMap = new HashMap();
        for (com.bytedance.push.model.d dVar : tokenCache) {
            if (dVar != null) {
                hashMap.put(Integer.valueOf(dVar.type), dVar);
            }
        }
        f11975a = hashMap;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized void a(Context context, com.bytedance.push.model.d dVar) {
        Map<Integer, com.bytedance.push.model.d> map;
        synchronized (d.class) {
            a(context);
            if (dVar != null && (map = f11975a) != null) {
                map.put(Integer.valueOf(dVar.type), dVar);
                ((LocalFrequencySettings) SettingsManager.obtain(context, LocalFrequencySettings.class)).setTokenCache(new ArrayList(f11975a.values()));
            }
        }
    }

    public static synchronized Map<Integer, com.bytedance.push.model.d> getAllToken(Context context) {
        synchronized (d.class) {
            a(context);
            Map<Integer, com.bytedance.push.model.d> map = f11975a;
            if (map == null) {
                return null;
            }
            return Collections.unmodifiableMap(map);
        }
    }
}
