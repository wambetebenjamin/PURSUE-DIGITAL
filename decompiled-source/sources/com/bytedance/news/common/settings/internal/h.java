package com.bytedance.news.common.settings.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.bytedance.news.common.settings.SettingsConfigProvider;
/* compiled from: MetaInfo.java */
/* loaded from: classes2.dex */
public class h {

    /* renamed from: a  reason: collision with root package name */
    private static volatile h f9812a = null;

    /* renamed from: b  reason: collision with root package name */
    private static final String f9813b = "key_latest_update_token";

    /* renamed from: c  reason: collision with root package name */
    private static final String f9814c = "key_prefix_version_";

    /* renamed from: d  reason: collision with root package name */
    private static final String f9815d = "key_one_sp_migrate_";

    /* renamed from: e  reason: collision with root package name */
    private SharedPreferences f9816e;

    public static h getInstance(Context context) {
        if (f9812a == null) {
            synchronized (h.class) {
                if (f9812a == null) {
                    f9812a = new h(context);
                }
            }
        }
        return f9812a;
    }

    private h(Context context) {
        SettingsConfigProvider settingsConfigProvider = (SettingsConfigProvider) com.bytedance.news.common.service.manager.e.getService(SettingsConfigProvider.class);
        if (settingsConfigProvider != null && settingsConfigProvider.getConfig() != null) {
            this.f9816e = settingsConfigProvider.getConfig().getSharedPreferences(context, "__settings_meta.sp", 0, false);
        }
        if (this.f9816e == null) {
            this.f9816e = context.getSharedPreferences("__settings_meta.sp", 0);
        }
    }

    public String getLatestUpdateToken(String str) {
        return this.f9816e.getString(a(f9813b, str), "");
    }

    public void setLatestUpdateToken(String str, String str2) {
        this.f9816e.edit().putString(a(f9813b, str2), str).apply();
    }

    public String getStorageKeyUpdateToken(String str) {
        return this.f9816e.getString(str, "");
    }

    public void setStorageKeyUpdateToken(String str, String str2) {
        this.f9816e.edit().putString(str, str2).apply();
    }

    public int getSettingsVersion(String str) {
        int i2 = 0;
        try {
            return this.f9816e.getInt(f9814c + str, 0);
        } catch (ClassCastException e2) {
            try {
                i2 = Integer.valueOf(this.f9816e.getString(f9814c + str, "0")).intValue();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
            e2.printStackTrace();
            return i2;
        }
    }

    public void setSettingsVersion(String str, int i2) {
        try {
            this.f9816e.edit().putInt(f9814c + str, i2).apply();
        } catch (Exception e2) {
            e2.printStackTrace();
            this.f9816e.edit().putString(f9814c + str, String.valueOf(i2)).apply();
        }
    }

    public void setOneSpMigrateDone(String str) {
        try {
            this.f9816e.edit().putBoolean(f9815d + str, true).apply();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public boolean isOneSpMigrateDone(String str) {
        return this.f9816e.getBoolean(f9815d + str, false);
    }

    public boolean needUpdate(String str, String str2) {
        return !getStorageKeyUpdateToken(str).equals(getLatestUpdateToken(str2));
    }

    private static String a(String str, String str2) {
        return TextUtils.isEmpty(str2) ? str : str + "_" + str2;
    }
}
