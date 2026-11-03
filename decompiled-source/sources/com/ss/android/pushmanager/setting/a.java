package com.ss.android.pushmanager.setting;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.bytedance.common.utility.o;
import com.bytedance.push.utils.h;
import com.ss.android.pushmanager.e;
import com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/* compiled from: PushCommonSetting.java */
/* loaded from: classes9.dex */
public class a {
    public static final String SSIDS = "ssids";

    /* renamed from: a  reason: collision with root package name */
    private static final String f18510a = "push_multi_process_config";

    /* renamed from: b  reason: collision with root package name */
    private static a f18511b;

    /* renamed from: d  reason: collision with root package name */
    private SharedPreferences f18513d = null;

    /* renamed from: c  reason: collision with root package name */
    private PushMultiProcessSharedProvider.b f18512c = PushMultiProcessSharedProvider.getMultiprocessShared(com.ss.android.message.a.getApp());

    public static synchronized void init(Context context) {
        synchronized (a.class) {
            com.ss.android.message.a.initApp((Application) context.getApplicationContext());
        }
    }

    public static synchronized a getInstance() {
        a aVar;
        synchronized (a.class) {
            if (f18511b == null) {
                synchronized (a.class) {
                    if (f18511b == null) {
                        f18511b = new a();
                    }
                }
            }
            aVar = f18511b;
        }
        return aVar;
    }

    private a() {
    }

    public void saveSSIDs(Map<String, String> map) {
        if (map == null) {
            return;
        }
        if (h.debug()) {
            h.d("PushService", "saveSSIDs start");
        }
        try {
            this.f18512c.edit().putString("ssids", o.mapToString(map)).apply();
        } catch (Exception unused) {
        }
    }

    public String getSsids() {
        return this.f18512c.getString("ssids", "");
    }

    public void getSSIDs(Map<String, String> map) {
        if (map == null) {
            return;
        }
        if (h.debug()) {
            h.d("PushService", "getSSIDs start");
        }
        try {
            String ssids = getSsids();
            h.d("PushService", "getSSIDs result is " + ssids);
            if (o.isEmpty(ssids)) {
                return;
            }
            o.stringToMap(ssids, map);
        } catch (Exception unused) {
        }
    }

    public String getDeviceId() {
        HashMap hashMap = new HashMap();
        getSSIDs(hashMap);
        return hashMap.get("device_id");
    }

    public String b() {
        HashMap hashMap = new HashMap();
        getSSIDs(hashMap);
        return hashMap.get("user_id");
    }

    public String getAlias() {
        HashMap hashMap = new HashMap();
        getSSIDs(hashMap);
        return hashMap.get(e.KEY_ALIAS);
    }

    private SharedPreferences a() {
        if (this.f18513d == null) {
            this.f18513d = com.ss.android.message.a.getApp().getSharedPreferences("push_multi_process_config", 4);
        }
        return this.f18513d;
    }

    @Deprecated
    public void setPref(String str, boolean z) {
        SharedPreferences.Editor edit = a().edit();
        edit.putBoolean(str, z);
        edit.apply();
    }

    @Deprecated
    public void setPref(String str, String str2) {
        SharedPreferences.Editor edit = a().edit();
        edit.putString(str, str2);
        edit.apply();
    }

    @Deprecated
    public void setPref(String str, Set<String> set) {
        SharedPreferences.Editor edit = a().edit();
        edit.putStringSet(str, set);
        edit.apply();
    }

    @Deprecated
    public void setPref(String str, float f2) {
        SharedPreferences.Editor edit = a().edit();
        edit.putFloat(str, f2);
        edit.apply();
    }

    @Deprecated
    public void setPref(String str, int i2) {
        SharedPreferences.Editor edit = a().edit();
        edit.putInt(str, i2);
        edit.apply();
    }

    @Deprecated
    public void setPref(String str, long j2) {
        SharedPreferences.Editor edit = a().edit();
        edit.putLong(str, j2);
        edit.apply();
    }

    @Deprecated
    public boolean getPref(String str, Boolean bool) {
        return a().getBoolean(str, bool.booleanValue());
    }

    @Deprecated
    public String getPref(String str, String str2) {
        return a().getString(str, str2);
    }

    @Deprecated
    public Set<String> getPref(String str, Set<String> set) {
        return a().getStringSet(str, set);
    }

    @Deprecated
    public int getPref(String str, int i2) {
        return a().getInt(str, i2);
    }

    @Deprecated
    public long getPref(String str, long j2) {
        return a().getLong(str, j2);
    }

    @Deprecated
    public float getPref(String str, float f2) {
        return a().getFloat(str, f2);
    }

    @Deprecated
    public boolean hasPrefWithKey(String str) {
        return a().contains(str);
    }

    @Deprecated
    public boolean removePref(String str) {
        SharedPreferences.Editor edit = a().edit();
        edit.remove(str);
        return edit.commit();
    }

    @Deprecated
    public void clear() {
        SharedPreferences.Editor edit = a().edit();
        edit.clear();
        edit.commit();
    }

    public boolean isApplicationActive() {
        return this.f18512c.isApplicationActive();
    }

    public boolean isAppForeground() {
        return this.f18512c.isApplicationForeground();
    }
}
