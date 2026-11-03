package com.bytedance.sdk.account.induce;

import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: InduceRecorder.java */
/* loaded from: classes2.dex */
public class c {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12508a = "InduceRecorder";

    /* renamed from: b  reason: collision with root package name */
    private static final String f12509b = "account_sdk_induce_recorder";

    /* renamed from: c  reason: collision with root package name */
    private static final String f12510c = "account_sdk_induce_login_strategy";

    /* renamed from: d  reason: collision with root package name */
    private static final String f12511d = "account_sdk_induce_scene_show_times_";

    /* renamed from: e  reason: collision with root package name */
    private static final String f12512e = "account_sdk_induce_scene_trigger_count_";

    /* renamed from: g  reason: collision with root package name */
    private static final String f12513g = "account_sdk_induce_scene_trigger_long_count_";

    /* renamed from: f  reason: collision with root package name */
    private SharedPreferences f12514f;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c() {
        f();
    }

    private void f() {
        com.ss.android.f configMayNull;
        if (this.f12514f == null && (configMayNull = com.ss.android.account.f.getConfigMayNull()) != null) {
            this.f12514f = configMayNull.getApplicationContext().getSharedPreferences(f12509b, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str, long j2) {
        b(f12513g + str, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long a(String str) {
        int c2 = c(f12512e + str, -1);
        return c2 == -1 ? c(f12513g + str, 0L) : c2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a() {
        Map<String, ?> all;
        Set<String> keySet;
        f();
        SharedPreferences sharedPreferences = this.f12514f;
        if (sharedPreferences == null || (all = sharedPreferences.getAll()) == null || (keySet = all.keySet()) == null) {
            return;
        }
        SharedPreferences.Editor edit = this.f12514f.edit();
        for (String str : keySet) {
            if (str.startsWith(f12512e) || str.startsWith(f12513g)) {
                edit.remove(str);
            }
        }
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public JSONObject b() {
        String c2 = c(f12510c, (String) null);
        if (!TextUtils.isEmpty(c2)) {
            try {
                return new JSONObject(c2);
            } catch (Exception e2) {
                com.ss.android.e.b(f12508a, "", e2);
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            b(f12510c, jSONObject.toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void a(String str, String str2) {
        b(f12511d + str, str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String b(String str) {
        return c(f12511d + str, (String) null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void c() {
        Map<String, ?> all;
        Set<String> keySet;
        f();
        SharedPreferences sharedPreferences = this.f12514f;
        if (sharedPreferences == null || (all = sharedPreferences.getAll()) == null || (keySet = all.keySet()) == null) {
            return;
        }
        SharedPreferences.Editor edit = this.f12514f.edit();
        for (String str : keySet) {
            if (str.startsWith(f12511d)) {
                edit.remove(str);
            }
        }
        edit.apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<String> d() {
        Map<String, ?> all;
        Set<String> keySet;
        f();
        HashSet hashSet = new HashSet();
        SharedPreferences sharedPreferences = this.f12514f;
        if (sharedPreferences != null && (all = sharedPreferences.getAll()) != null && (keySet = all.keySet()) != null) {
            for (String str : keySet) {
                if (str.startsWith(f12511d)) {
                    hashSet.add(str.replaceFirst(f12511d, ""));
                }
            }
        }
        return hashSet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<String> e() {
        Map<String, ?> all;
        Set<String> keySet;
        f();
        HashSet hashSet = new HashSet();
        SharedPreferences sharedPreferences = this.f12514f;
        if (sharedPreferences != null && (all = sharedPreferences.getAll()) != null && (keySet = all.keySet()) != null) {
            for (String str : keySet) {
                if (str.startsWith(f12512e)) {
                    hashSet.add(str.replaceFirst(f12512e, ""));
                } else if (str.startsWith(f12513g)) {
                    hashSet.add(str.replaceFirst(f12513g, ""));
                }
            }
        }
        return hashSet;
    }

    private void b(String str, long j2) {
        f();
        SharedPreferences sharedPreferences = this.f12514f;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putLong(str, j2).apply();
        }
    }

    private long c(String str, long j2) {
        f();
        SharedPreferences sharedPreferences = this.f12514f;
        return sharedPreferences != null ? sharedPreferences.getLong(str, j2) : j2;
    }

    private void b(String str, int i2) {
        f();
        SharedPreferences sharedPreferences = this.f12514f;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt(str, i2).apply();
        }
    }

    private int c(String str, int i2) {
        f();
        SharedPreferences sharedPreferences = this.f12514f;
        return sharedPreferences != null ? sharedPreferences.getInt(str, i2) : i2;
    }

    private void b(String str, String str2) {
        f();
        SharedPreferences sharedPreferences = this.f12514f;
        if (sharedPreferences != null) {
            sharedPreferences.edit().putString(str, str2).apply();
        }
    }

    private String c(String str, String str2) {
        f();
        SharedPreferences sharedPreferences = this.f12514f;
        return sharedPreferences != null ? sharedPreferences.getString(str, str2) : str2;
    }
}
