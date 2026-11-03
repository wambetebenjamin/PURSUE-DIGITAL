package com.bytedance.news.common.settings.storage;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.bytedance.news.common.service.manager.e;
import com.bytedance.news.common.settings.SettingsConfigProvider;
import com.bytedance.news.common.settings.api.g;
import com.bytedance.news.common.settings.api.i;
import com.bytedance.services.apm.api.IEnsure;
import java.util.HashSet;
import java.util.Set;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: SharedPreferenceStorage.java */
/* loaded from: classes2.dex */
public class b implements i {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9825a = "SharedPreferenceStorage";

    /* renamed from: b  reason: collision with root package name */
    private SharedPreferences f9826b;

    /* renamed from: c  reason: collision with root package name */
    private SharedPreferences.Editor f9827c;

    /* renamed from: d  reason: collision with root package name */
    private IEnsure f9828d;

    public b(Context context, String str, boolean z) {
        g settingsLogService;
        SettingsConfigProvider settingsConfigProvider = (SettingsConfigProvider) e.getService(SettingsConfigProvider.class);
        if (settingsConfigProvider != null && settingsConfigProvider.getConfig() != null) {
            this.f9826b = settingsConfigProvider.getConfig().getSharedPreferences(context, str + ".sp", 0, z);
        }
        try {
            if (this.f9826b == null) {
                this.f9826b = context.getSharedPreferences(str + ".sp", 0);
            }
        } catch (IllegalStateException e2) {
            if (a(context)) {
                if (Build.VERSION.SDK_INT >= 24) {
                    Context createDeviceProtectedStorageContext = context.createDeviceProtectedStorageContext();
                    if (!createDeviceProtectedStorageContext.moveSharedPreferencesFrom(context, str + ".sp") && settingsConfigProvider != null && settingsConfigProvider.getConfig() != null && (settingsLogService = settingsConfigProvider.getConfig().getSettingsLogService()) != null) {
                        settingsLogService.e(f9825a, "Failed to migrate shared preferences = " + str + ".sp");
                    }
                    if (this.f9826b == null) {
                        this.f9826b = createDeviceProtectedStorageContext.getSharedPreferences(str + ".sp", 0);
                    }
                }
            } else {
                throw e2;
            }
        }
        this.f9827c = this.f9826b.edit();
        this.f9828d = (IEnsure) e.getService(IEnsure.class);
    }

    private static boolean a(Context context) {
        DevicePolicyManager devicePolicyManager;
        if (Build.VERSION.SDK_INT >= 26 && (devicePolicyManager = (DevicePolicyManager) context.getSystemService("device_policy")) != null) {
            try {
                int storageEncryptionStatus = devicePolicyManager.getStorageEncryptionStatus();
                return storageEncryptionStatus == 5 || storageEncryptionStatus == 3;
            } catch (IllegalStateException e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return false;
    }

    @Override // com.bytedance.news.common.settings.api.i
    public void putString(String str, String str2) {
        this.f9827c.putString(str, str2);
    }

    @Override // com.bytedance.news.common.settings.api.i
    public void putInt(String str, int i2) {
        this.f9827c.putInt(str, i2);
    }

    @Override // com.bytedance.news.common.settings.api.i
    public void putLong(String str, long j2) {
        this.f9827c.putLong(str, j2);
    }

    @Override // com.bytedance.news.common.settings.api.i
    public void putFloat(String str, float f2) {
        this.f9827c.putFloat(str, f2);
    }

    @Override // com.bytedance.news.common.settings.api.i
    public void putBoolean(String str, boolean z) {
        this.f9827c.putBoolean(str, z);
    }

    @Override // com.bytedance.news.common.settings.api.i
    public void putStringSet(String str, Set<String> set) {
        this.f9827c.putStringSet(str, set);
    }

    @Override // com.bytedance.news.common.settings.api.i
    public String getString(String str) {
        return getString(str, "");
    }

    @Override // com.bytedance.news.common.settings.api.i
    public String getString(String str, String str2) {
        try {
            return this.f9826b.getString(str, str2);
        } catch (Exception e2) {
            a(e2);
            return str2;
        }
    }

    @Override // com.bytedance.news.common.settings.api.i
    public int getInt(String str) {
        return getInt(str, 0);
    }

    @Override // com.bytedance.news.common.settings.api.i
    public int getInt(String str, int i2) {
        try {
            return this.f9826b.getInt(str, i2);
        } catch (Exception e2) {
            a(e2);
            return i2;
        }
    }

    @Override // com.bytedance.news.common.settings.api.i
    public long getLong(String str) {
        return getLong(str, 0L);
    }

    @Override // com.bytedance.news.common.settings.api.i
    public long getLong(String str, long j2) {
        try {
            return this.f9826b.getLong(str, j2);
        } catch (Exception e2) {
            a(e2);
            return j2;
        }
    }

    @Override // com.bytedance.news.common.settings.api.i
    public float getFloat(String str) {
        return getFloat(str, 0.0f);
    }

    @Override // com.bytedance.news.common.settings.api.i
    public float getFloat(String str, float f2) {
        try {
            return this.f9826b.getFloat(str, f2);
        } catch (Exception e2) {
            a(e2);
            return f2;
        }
    }

    @Override // com.bytedance.news.common.settings.api.i
    public boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    @Override // com.bytedance.news.common.settings.api.i
    public boolean getBoolean(String str, boolean z) {
        try {
            return this.f9826b.getBoolean(str, z);
        } catch (Exception e2) {
            a(e2);
            return z;
        }
    }

    @Override // com.bytedance.news.common.settings.api.i
    public Set<String> getStringSet(String str) {
        return getStringSet(str, new HashSet());
    }

    @Override // com.bytedance.news.common.settings.api.i
    public Set<String> getStringSet(String str, Set<String> set) {
        try {
            return this.f9826b.getStringSet(str, set);
        } catch (Exception e2) {
            a(e2);
            return set;
        }
    }

    @Override // com.bytedance.news.common.settings.api.i
    public void remove(String str) {
        this.f9827c.remove(str);
    }

    @Override // com.bytedance.news.common.settings.api.i
    public void clear() {
        this.f9827c.clear();
    }

    @Override // com.bytedance.news.common.settings.api.i
    public boolean contains(String str) {
        return this.f9826b.contains(str);
    }

    @Override // com.bytedance.news.common.settings.api.i
    public void apply() {
        this.f9827c.apply();
    }

    private void a(Exception exc) {
        IEnsure iEnsure = this.f9828d;
        if (iEnsure != null) {
            iEnsure.reportLogException(exc);
        }
    }
}
