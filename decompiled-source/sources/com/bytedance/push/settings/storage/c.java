package com.bytedance.push.settings.storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MPProviderProcessStorage.java */
/* loaded from: classes2.dex */
public final class c extends g {

    /* renamed from: a  reason: collision with root package name */
    private final Context f11885a;

    /* renamed from: b  reason: collision with root package name */
    private final String f11886b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public c(Context context, String str) {
        super(context, str);
        this.f11886b = str;
        this.f11885a = context;
    }

    @Override // com.bytedance.push.settings.storage.g, com.bytedance.push.settings.storage.i
    public a edit() {
        return new a(this.f11885a, super.edit());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: MPProviderProcessStorage.java */
    /* loaded from: classes2.dex */
    public final class a implements SharedPreferences.Editor {

        /* renamed from: b  reason: collision with root package name */
        private final Context f11888b;

        /* renamed from: c  reason: collision with root package name */
        private Map<String, String> f11889c = new ConcurrentHashMap();

        /* renamed from: d  reason: collision with root package name */
        private final SharedPreferences.Editor f11890d;

        a(Context context, SharedPreferences.Editor editor) {
            this.f11888b = context;
            this.f11890d = editor;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putString(String str, String str2) {
            this.f11890d.putString(str, str2);
            if (TextUtils.equals(c.this.getString(str), str2)) {
                this.f11889c.remove(str);
            } else {
                this.f11889c.put(str, "string");
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putStringSet(String str, Set<String> set) {
            throw new UnsupportedOperationException("not support putStringSet");
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putInt(String str, int i2) {
            this.f11890d.putInt(str, i2);
            if (i2 == c.this.getInt(str)) {
                this.f11889c.remove(str);
            } else {
                this.f11889c.put(str, "integer");
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putLong(String str, long j2) {
            this.f11890d.putLong(str, j2);
            if (j2 == c.this.getLong(str)) {
                this.f11889c.remove(str);
            } else {
                this.f11889c.put(str, "long");
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putFloat(String str, float f2) {
            this.f11890d.putFloat(str, f2);
            if (f2 == c.this.getFloat(str)) {
                this.f11889c.remove(str);
            } else {
                this.f11889c.put(str, "float");
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putBoolean(String str, boolean z) {
            this.f11890d.putBoolean(str, z);
            if (z == c.this.getBoolean(str)) {
                this.f11889c.remove(str);
            } else {
                this.f11889c.put(str, "boolean");
            }
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor remove(String str) {
            this.f11890d.remove(str);
            this.f11889c.remove(str);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor clear() {
            throw new UnsupportedOperationException("clear not support");
        }

        @Override // android.content.SharedPreferences.Editor
        public boolean commit() {
            boolean commit = this.f11890d.commit();
            a();
            return commit;
        }

        private void a() {
            for (Map.Entry<String, String> entry : this.f11889c.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                if (com.bytedance.push.settings.utils.a.a(this.f11888b)) {
                    MainProcessSettingsProvider.createNotifyRunnable(this.f11888b, c.this.f11886b, key, value).run();
                } else {
                    SmpProcessSettingsProvider.createNotifyRunnable(this.f11888b, c.this.f11886b, key, value).run();
                }
            }
            this.f11889c.clear();
        }

        @Override // android.content.SharedPreferences.Editor
        public void apply() {
            this.f11890d.apply();
            a();
        }
    }
}
