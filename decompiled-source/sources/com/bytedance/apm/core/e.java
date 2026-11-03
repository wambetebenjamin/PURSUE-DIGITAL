package com.bytedance.apm.core;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Process;
import com.bytedance.apm.util.m;
import com.bytedance.librarian.c;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
/* compiled from: MonitorSharedPreferences.java */
/* loaded from: classes.dex */
public class e implements SharedPreferences {

    /* renamed from: d  reason: collision with root package name */
    private static int f961d = -1;

    /* renamed from: a  reason: collision with root package name */
    private Application f962a;

    /* renamed from: b  reason: collision with root package name */
    private String f963b;

    /* renamed from: c  reason: collision with root package name */
    private Uri f964c;

    @Override // android.content.SharedPreferences
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
    }

    @Override // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
    }

    private e(Application application, String str) {
        this.f962a = application;
        String str2 = "content://" + application.getPackageName() + ".apm/sp/" + str;
        this.f963b = str2;
        this.f964c = Uri.parse(str2);
    }

    @Override // android.content.SharedPreferences
    public Map<String, ?> getAll() {
        ArrayList<g> a2 = a((String) null, (Object) null);
        if (a2 == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator<g> it = a2.iterator();
        while (it.hasNext()) {
            g next = it.next();
            hashMap.put(next.mKey, next.mValue);
        }
        return hashMap;
    }

    @Override // android.content.SharedPreferences
    public String getString(String str, String str2) {
        ArrayList<g> a2 = a(str, str2);
        if (a2 == null) {
            return null;
        }
        return (String) a2.get(0).mValue;
    }

    @Override // android.content.SharedPreferences
    public Set<String> getStringSet(String str, Set<String> set) {
        ArrayList<g> a2 = a(str, set);
        if (a2 == null || a2.get(0).mValue == null) {
            return null;
        }
        String[] strArr = (String[]) a2.get(0).mValue;
        HashSet hashSet = new HashSet(strArr.length);
        hashSet.addAll(Arrays.asList(strArr));
        return hashSet;
    }

    @Override // android.content.SharedPreferences
    public int getInt(String str, int i2) {
        ArrayList<g> a2 = a(str, String.valueOf(i2));
        if (a2 == null) {
            return i2;
        }
        Object obj = a2.get(0).mValue;
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return obj instanceof String ? Integer.decode((String) obj).intValue() : i2;
    }

    @Override // android.content.SharedPreferences
    public long getLong(String str, long j2) {
        ArrayList<g> a2 = a(str, String.valueOf(j2));
        if (a2 == null) {
            return j2;
        }
        Object obj = a2.get(0).mValue;
        if (obj instanceof Long) {
            return ((Long) obj).longValue();
        }
        return obj instanceof String ? Long.decode((String) obj).longValue() : j2;
    }

    @Override // android.content.SharedPreferences
    public float getFloat(String str, float f2) {
        ArrayList<g> a2 = a(str, String.valueOf(f2));
        if (a2 == null) {
            return f2;
        }
        Object obj = a2.get(0).mValue;
        if (obj instanceof Float) {
            return ((Float) obj).floatValue();
        }
        return obj instanceof String ? Float.valueOf((String) obj).floatValue() : f2;
    }

    @Override // android.content.SharedPreferences
    public boolean getBoolean(String str, boolean z) {
        ArrayList<g> a2 = a(str, String.valueOf(z));
        if (a2 == null) {
            return z;
        }
        Object obj = a2.get(0).mValue;
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return obj instanceof String ? Boolean.valueOf((String) obj).booleanValue() : z;
    }

    @Override // android.content.SharedPreferences
    public boolean contains(String str) {
        Bundle bundle = null;
        try {
            bundle = this.f962a.getContentResolver().call(this.f964c, str != null ? this.f963b + c.a.SEPARATOR + str : this.f963b, "contains", (Bundle) null);
        } catch (Exception unused) {
        }
        return bundle != null && bundle.getBoolean("contains");
    }

    @Override // android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        return new a();
    }

    /* compiled from: MonitorSharedPreferences.java */
    /* loaded from: classes.dex */
    private class a implements SharedPreferences.Editor {

        /* renamed from: b  reason: collision with root package name */
        private boolean f966b;

        /* renamed from: c  reason: collision with root package name */
        private HashMap<String, Object> f967c;

        private a() {
            this.f966b = false;
            this.f967c = new HashMap<>();
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putString(String str, String str2) {
            this.f967c.put(str, str2);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putStringSet(String str, Set<String> set) {
            this.f967c.put(str, m.toArray(set));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putInt(String str, int i2) {
            this.f967c.put(str, Integer.valueOf(i2));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putLong(String str, long j2) {
            this.f967c.put(str, Long.valueOf(j2));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putFloat(String str, float f2) {
            this.f967c.put(str, Float.valueOf(f2));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putBoolean(String str, boolean z) {
            this.f967c.put(str, Boolean.valueOf(z));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor remove(String str) {
            this.f967c.put(str, null);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor clear() {
            this.f967c.clear();
            this.f966b = true;
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public boolean commit() {
            apply();
            return true;
        }

        @Override // android.content.SharedPreferences.Editor
        public void apply() {
            if (this.f966b || !this.f967c.isEmpty()) {
                Bundle bundle = new Bundle();
                if (this.f966b) {
                    bundle.putBoolean("clear", true);
                }
                ArrayList<? extends Parcelable> arrayList = new ArrayList<>(this.f967c.size());
                for (Map.Entry<String, Object> entry : this.f967c.entrySet()) {
                    arrayList.add(new g(entry.getKey(), entry.getValue()));
                }
                bundle.putParcelableArrayList("edit", arrayList);
                try {
                    e.this.f962a.getContentResolver().call(e.this.f964c, e.this.f963b, "edit", bundle);
                } catch (Exception unused) {
                }
            }
        }
    }

    private ArrayList<g> a(String str, Object obj) {
        Bundle bundle;
        Bundle bundle2 = new Bundle();
        if (obj != null) {
            bundle2.putParcelable("sp", new g(str, obj));
        }
        try {
            ContentResolver contentResolver = this.f962a.getContentResolver();
            Uri uri = this.f964c;
            String str2 = str != null ? this.f963b + c.a.SEPARATOR + str : this.f963b;
            if (obj == null) {
                bundle2 = null;
            }
            bundle = contentResolver.call(uri, str2, "query", bundle2);
        } catch (Exception unused) {
            bundle = null;
        }
        if (bundle == null) {
            return null;
        }
        bundle.setClassLoader(getClass().getClassLoader());
        return bundle.getParcelableArrayList("sp");
    }

    private static int a(Context context) {
        try {
            Bundle call = context.getContentResolver().call(Uri.parse("content://" + context.getPackageName() + ".apm"), "getPid", (String) null, (Bundle) null);
            if (call != null) {
                return call.getInt("Pid", -1);
            }
        } catch (Exception unused) {
        }
        return -1;
    }

    public static SharedPreferences getSharedPreferences(Context context, String str) {
        if (isProviderProcess(context)) {
            return context.getSharedPreferences(str, 0);
        }
        return a(context, str);
    }

    static SharedPreferences a(Context context, String str) {
        Application application;
        if (context instanceof Application) {
            application = (Application) context;
        } else {
            application = (Application) context.getApplicationContext();
        }
        return new e(application, str);
    }

    public static boolean isProviderProcess(Context context) {
        if (f961d == -1) {
            f961d = a(context);
        }
        return f961d == Process.myPid();
    }
}
