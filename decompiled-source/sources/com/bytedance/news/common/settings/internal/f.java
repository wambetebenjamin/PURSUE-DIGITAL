package com.bytedance.news.common.settings.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.bytedance.news.common.settings.SettingsConfigProvider;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: LocalCache.java */
/* loaded from: classes2.dex */
public class f {

    /* renamed from: a  reason: collision with root package name */
    private static volatile f f9801a = null;

    /* renamed from: b  reason: collision with root package name */
    private static final String f9802b = "key_last_update_token";

    /* renamed from: c  reason: collision with root package name */
    private static final String f9803c = "key_local_app_settings_data";

    /* renamed from: d  reason: collision with root package name */
    private static final String f9804d = "key_local_user_settings_data";

    /* renamed from: e  reason: collision with root package name */
    private static final String f9805e = "timestamp";

    /* renamed from: f  reason: collision with root package name */
    private static final String f9806f = "diff_settings.sp";

    /* renamed from: g  reason: collision with root package name */
    private SharedPreferences f9807g;

    /* renamed from: h  reason: collision with root package name */
    private HashMap<String, com.bytedance.news.common.settings.api.f> f9808h = new HashMap<>();

    /* renamed from: i  reason: collision with root package name */
    private final com.bytedance.news.common.settings.api.f f9809i = new com.bytedance.news.common.settings.api.f(null, null, "", false);

    /* renamed from: j  reason: collision with root package name */
    private SharedPreferences f9810j;
    private JSONObject k;
    private Context l;

    public static f getInstance(Context context) {
        if (f9801a == null) {
            synchronized (f.class) {
                if (f9801a == null) {
                    f9801a = new f(context);
                }
            }
        }
        return f9801a;
    }

    private f(Context context) {
        this.l = context;
        SettingsConfigProvider settingsConfigProvider = (SettingsConfigProvider) com.bytedance.news.common.service.manager.e.getService(SettingsConfigProvider.class);
        if (settingsConfigProvider != null && settingsConfigProvider.getConfig() != null) {
            this.f9807g = settingsConfigProvider.getConfig().getSharedPreferences(context, "__local_settings_data.sp", 0, false);
        }
        if (this.f9807g == null) {
            this.f9807g = context.getSharedPreferences("__local_settings_data.sp", 0);
        }
    }

    public synchronized com.bytedance.news.common.settings.api.f getLocalSettingsData(String str) {
        JSONObject jSONObject;
        com.bytedance.news.common.settings.api.f fVar = this.f9808h.get(str);
        if (fVar != null) {
            if (fVar == this.f9809i) {
                fVar = null;
            }
            return fVar;
        }
        String string = this.f9807g.getString(a(f9803c, str), "");
        if (!TextUtils.isEmpty(string)) {
            try {
                JSONObject jSONObject2 = new JSONObject(string);
                String string2 = this.f9807g.getString(a(f9804d, str), "");
                if (!TextUtils.isEmpty(string2)) {
                    jSONObject = new JSONObject(string2);
                } else {
                    jSONObject = new JSONObject();
                }
                com.bytedance.news.common.settings.api.f fVar2 = new com.bytedance.news.common.settings.api.f(jSONObject2, jSONObject, this.f9807g.getString(a(f9802b, str), ""), false);
                this.f9808h.put(str, fVar2);
                return fVar2;
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        this.f9808h.put(str, this.f9809i);
        return null;
    }

    public synchronized void updateSingleSettingsData(JSONObject jSONObject, String str) {
        if (com.bytedance.news.common.settings.api.exposed.a.sDebugTeller != null && com.bytedance.news.common.settings.api.exposed.a.sDebugTeller.isDebug().booleanValue()) {
            String next = jSONObject.keys().next();
            com.bytedance.news.common.settings.api.f localSettingsData = getLocalSettingsData(str);
            if (localSettingsData != null) {
                JSONObject appSettings = localSettingsData.getAppSettings();
                try {
                    appSettings.put(next, jSONObject.opt(next));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                this.f9808h.put(str, localSettingsData);
                SharedPreferences.Editor edit = this.f9807g.edit();
                try {
                    edit.putString(a(f9803c, str), appSettings.toString());
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
                edit.apply();
            }
        }
    }

    public synchronized void setLocalSettingsData(com.bytedance.news.common.settings.api.f fVar, String str) {
        JSONObject appSettings = fVar.getAppSettings();
        JSONObject userSettings = fVar.getUserSettings();
        com.bytedance.news.common.settings.api.f localSettingsData = getLocalSettingsData(str);
        if (localSettingsData != null) {
            JSONObject appSettings2 = localSettingsData.getAppSettings();
            JSONObject userSettings2 = localSettingsData.getUserSettings();
            if (appSettings2 == null) {
                appSettings2 = new JSONObject();
            }
            if (userSettings2 == null) {
                userSettings2 = new JSONObject();
            }
            if (appSettings != null) {
                Iterator<String> keys = appSettings.keys();
                while (keys.hasNext()) {
                    String next = keys.next();
                    try {
                        Object opt = appSettings.opt(next);
                        if (!TextUtils.equals(String.valueOf(opt), String.valueOf(appSettings2.opt(next)))) {
                            if (this.k == null) {
                                this.k = new JSONObject();
                            }
                            this.k.put(next, opt);
                            appSettings2.put(next, opt);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            }
            if (userSettings != null) {
                Iterator<String> keys2 = userSettings.keys();
                while (keys2.hasNext()) {
                    String next2 = keys2.next();
                    try {
                        Object opt2 = userSettings.opt(next2);
                        if (!TextUtils.equals(String.valueOf(opt2), String.valueOf(userSettings2.opt(next2)))) {
                            if (this.k == null) {
                                this.k = new JSONObject();
                            }
                            this.k.put(next2, opt2);
                            userSettings2.put(next2, opt2);
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                    }
                }
            }
            this.f9808h.put(str, localSettingsData);
            SharedPreferences.Editor edit = this.f9807g.edit();
            try {
                edit.putString(a(f9802b, str), fVar.getToken());
            } catch (Exception e4) {
                e4.printStackTrace();
            }
            try {
                edit.putString(a(f9803c, str), appSettings != null ? appSettings2.toString() : "");
            } catch (Exception e5) {
                e5.printStackTrace();
            }
            try {
                edit.putString(a(f9804d, str), userSettings != null ? userSettings2.toString() : "");
            } catch (Exception e6) {
                e6.printStackTrace();
            }
            edit.apply();
            a();
        } else {
            this.f9808h.put(str, fVar);
            SharedPreferences.Editor edit2 = this.f9807g.edit();
            try {
                edit2.putString(a(f9802b, str), fVar.getToken());
            } catch (Exception e7) {
                e7.printStackTrace();
            }
            try {
                edit2.putString(a(f9803c, str), appSettings != null ? appSettings.toString() : "");
            } catch (Exception e8) {
                e8.printStackTrace();
            }
            try {
                edit2.putString(a(f9804d, str), userSettings != null ? userSettings.toString() : "");
            } catch (Exception e9) {
                e9.printStackTrace();
            }
            edit2.apply();
        }
    }

    private void a() {
        Context context;
        if (this.k == null || (context = this.l) == null) {
            return;
        }
        if (this.f9810j == null) {
            this.f9810j = context.getSharedPreferences(f9806f, 0);
        }
        SharedPreferences.Editor edit = this.f9810j.edit();
        edit.clear();
        try {
            edit.putLong("timestamp", System.currentTimeMillis());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        try {
            edit.putString(f9803c, this.k.toString());
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        edit.apply();
        this.k = null;
    }

    public synchronized com.bytedance.news.common.settings.api.model.a getLocalDiffSettingsData() {
        SharedPreferences sharedPreferences = this.f9810j;
        if (sharedPreferences == null) {
            return null;
        }
        String string = sharedPreferences.getString(f9803c, "");
        long j2 = this.f9810j.getLong("timestamp", 0L);
        if (!TextUtils.isEmpty(string)) {
            try {
                return new com.bytedance.news.common.settings.api.model.a(new JSONObject(string), j2);
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return null;
    }

    private static String a(String str, String str2) {
        return TextUtils.isEmpty(str2) ? str : str + "_" + str2;
    }
}
