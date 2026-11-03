package com.bytedance.common.utility.persistent;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Base64;
import java.util.Map;
import java.util.Set;
/* compiled from: Preferences.java */
@Deprecated
/* loaded from: classes2.dex */
public class a implements SharedPreferences {
    public static final String PREFERENCE_PREFIX = "pref_id_";

    /* renamed from: a  reason: collision with root package name */
    private SharedPreferences f5339a;

    public static a getById(Context context, String str) {
        return new a(context.getSharedPreferences(PREFERENCE_PREFIX + str, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Deprecated
    public static String b(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        Parcel obtain = Parcel.obtain();
        try {
            bundle.writeToParcel(obtain, 0);
            String encodeToString = Base64.encodeToString(obtain.marshall(), 0);
            obtain.recycle();
            return encodeToString;
        } catch (Throwable unused) {
            obtain.recycle();
            return null;
        }
    }

    @Deprecated
    private static Bundle a(String str) {
        byte[] decode = Base64.decode(str, 0);
        Parcel obtain = Parcel.obtain();
        try {
            obtain.unmarshall(decode, 0, decode.length);
            obtain.setDataPosition(0);
            return (Bundle) Bundle.CREATOR.createFromParcel(obtain);
        } finally {
            obtain.recycle();
        }
    }

    public a(SharedPreferences sharedPreferences) {
        this.f5339a = sharedPreferences;
    }

    @Override // android.content.SharedPreferences
    public boolean contains(String str) {
        return this.f5339a.contains(str);
    }

    @Override // android.content.SharedPreferences
    public SharedPreferences$EditorC0076a edit() {
        return new SharedPreferences$EditorC0076a(this.f5339a.edit());
    }

    @Override // android.content.SharedPreferences
    public Map<String, ?> getAll() {
        return this.f5339a.getAll();
    }

    @Override // android.content.SharedPreferences
    public boolean getBoolean(String str, boolean z) {
        return this.f5339a.getBoolean(str, z);
    }

    public Bundle getBundle(String str, Bundle bundle) {
        String string = this.f5339a.getString(str, null);
        return string == null ? bundle : a(string);
    }

    @Override // android.content.SharedPreferences
    public float getFloat(String str, float f2) {
        return this.f5339a.getFloat(str, f2);
    }

    @Override // android.content.SharedPreferences
    public int getInt(String str, int i2) {
        return this.f5339a.getInt(str, i2);
    }

    @Override // android.content.SharedPreferences
    public long getLong(String str, long j2) {
        return this.f5339a.getLong(str, j2);
    }

    @Override // android.content.SharedPreferences
    public String getString(String str, String str2) {
        return this.f5339a.getString(str, str2);
    }

    @Override // android.content.SharedPreferences
    public Set<String> getStringSet(String str, Set<String> set) {
        return this.f5339a.getStringSet(str, set);
    }

    @Override // android.content.SharedPreferences
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.f5339a.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    @Override // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        this.f5339a.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    /* compiled from: Preferences.java */
    /* renamed from: com.bytedance.common.utility.persistent.a$a  reason: collision with other inner class name */
    /* loaded from: classes2.dex */
    public static class SharedPreferences$EditorC0076a implements SharedPreferences.Editor {

        /* renamed from: a  reason: collision with root package name */
        private SharedPreferences.Editor f5340a;

        public SharedPreferences$EditorC0076a(SharedPreferences.Editor editor) {
            this.f5340a = editor;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor clear() {
            this.f5340a.clear();
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public boolean commit() {
            return this.f5340a.commit();
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putBoolean(String str, boolean z) {
            this.f5340a.putBoolean(str, z);
            return this;
        }

        @Deprecated
        public SharedPreferences.Editor putBundle(String str, Bundle bundle) {
            this.f5340a.putString(str, a.b(bundle));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putFloat(String str, float f2) {
            this.f5340a.putFloat(str, f2);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putInt(String str, int i2) {
            this.f5340a.putInt(str, i2);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putLong(String str, long j2) {
            this.f5340a.putLong(str, j2);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putString(String str, String str2) {
            this.f5340a.putString(str, str2);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor remove(String str) {
            this.f5340a.remove(str);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putStringSet(String str, Set<String> set) {
            this.f5340a.putStringSet(str, set);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public void apply() {
            this.f5340a.apply();
        }
    }
}
