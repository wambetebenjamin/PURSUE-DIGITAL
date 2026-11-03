package com.bytedance.push.settings.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: SharedPreferenceStorage.java */
/* loaded from: classes2.dex */
public class g implements i {

    /* renamed from: a  reason: collision with root package name */
    private SharedPreferences f11903a;

    /* renamed from: b  reason: collision with root package name */
    private Map<com.bytedance.push.settings.b, SharedPreferences.OnSharedPreferenceChangeListener> f11904b = new ConcurrentHashMap();

    private void a(Exception exc) {
    }

    public g(Context context, String str) {
        this.f11903a = context.getSharedPreferences(str, 0);
    }

    @Override // com.bytedance.push.settings.storage.i
    public String getString(String str) {
        return getString(str, null);
    }

    @Override // com.bytedance.push.settings.storage.i
    public String getString(String str, String str2) {
        try {
            return this.f11903a.getString(str, str2);
        } catch (Exception e2) {
            a(e2);
            return str2;
        }
    }

    @Override // com.bytedance.push.settings.storage.i
    public int getInt(String str) {
        return getInt(str, 0);
    }

    @Override // com.bytedance.push.settings.storage.i
    public int getInt(String str, int i2) {
        try {
            return this.f11903a.getInt(str, i2);
        } catch (Exception e2) {
            a(e2);
            return i2;
        }
    }

    @Override // com.bytedance.push.settings.storage.i
    public long getLong(String str) {
        return getLong(str, 0L);
    }

    @Override // com.bytedance.push.settings.storage.i
    public long getLong(String str, long j2) {
        try {
            return this.f11903a.getLong(str, j2);
        } catch (Exception e2) {
            a(e2);
            return j2;
        }
    }

    @Override // com.bytedance.push.settings.storage.i
    public float getFloat(String str) {
        return getFloat(str, 0.0f);
    }

    @Override // com.bytedance.push.settings.storage.i
    public float getFloat(String str, float f2) {
        try {
            return this.f11903a.getFloat(str, f2);
        } catch (Exception e2) {
            a(e2);
            return f2;
        }
    }

    @Override // com.bytedance.push.settings.storage.i
    public boolean getBoolean(String str) {
        return getBoolean(str, false);
    }

    @Override // com.bytedance.push.settings.storage.i
    public boolean getBoolean(String str, boolean z) {
        try {
            return this.f11903a.getBoolean(str, z);
        } catch (Exception e2) {
            a(e2);
            return z;
        }
    }

    @Override // com.bytedance.push.settings.storage.i
    public boolean contains(String str) {
        return this.f11903a.contains(str);
    }

    @Override // com.bytedance.push.settings.storage.i
    public SharedPreferences.Editor edit() {
        return this.f11903a.edit();
    }

    @Override // com.bytedance.push.settings.storage.i
    public void registerValChanged(Context context, final String str, String str2, final com.bytedance.push.settings.b bVar) {
        if (bVar == null) {
            return;
        }
        SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener = new SharedPreferences.OnSharedPreferenceChangeListener() { // from class: com.bytedance.push.settings.storage.g.1
            @Override // android.content.SharedPreferences.OnSharedPreferenceChangeListener
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String str3) {
                if (TextUtils.equals(str, str3)) {
                    bVar.onChange();
                }
            }
        };
        this.f11904b.put(bVar, onSharedPreferenceChangeListener);
        this.f11903a.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    @Override // com.bytedance.push.settings.storage.i
    public void unregisterValChanged(com.bytedance.push.settings.b bVar) {
        SharedPreferences.OnSharedPreferenceChangeListener remove;
        if (bVar == null || (remove = this.f11904b.remove(bVar)) == null) {
            return;
        }
        this.f11903a.unregisterOnSharedPreferenceChangeListener(remove);
    }
}
