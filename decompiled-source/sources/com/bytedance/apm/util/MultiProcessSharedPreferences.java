package com.bytedance.apm.util;

import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Pair;
import com.bytedance.librarian.c;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/* loaded from: classes.dex */
public class MultiProcessSharedPreferences extends ContentProvider implements SharedPreferences {
    private static String MONITOR_CONFIG_SP_NAME = "apm_config";
    public static String MONITOR_PROVIDER_SUFFIX = ".apm6";
    private static final String TAG = "MultiProcessSharedPref";
    private Application mApp;
    private ContentResolver mContentResolver;
    private Uri mDefaultUri;
    private String mUriString;

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return -1;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.SharedPreferences
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
    }

    @Override // android.content.SharedPreferences
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return -1;
    }

    public MultiProcessSharedPreferences() {
    }

    public static Uri getSpUri(Context context) {
        Uri parse;
        String packageName = context.getPackageName();
        String providerAuthority = com.ss.android.common.util.d.getProviderAuthority(context, MultiProcessSharedPreferences.class.getName());
        if (TextUtils.isEmpty(providerAuthority)) {
            parse = Uri.parse("content://" + packageName + MONITOR_PROVIDER_SUFFIX);
        } else {
            parse = Uri.parse("content://" + providerAuthority);
            if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
                com.bytedance.apm6.util.log.b.d(TAG, "authority is " + parse);
            }
        }
        return parse.buildUpon().appendPath("sp").appendPath(MONITOR_CONFIG_SP_NAME).build();
    }

    private MultiProcessSharedPreferences(Application application) {
        this.mApp = application;
        Uri spUri = getSpUri(application);
        this.mDefaultUri = spUri;
        this.mUriString = spUri.toString();
        if (com.bytedance.apm6.foundation.context.a.isDebugMode()) {
            com.bytedance.apm6.util.log.b.d(TAG, "getSpUri is " + this.mDefaultUri);
        }
    }

    @Override // android.content.SharedPreferences
    public Map<String, ?> getAll() {
        ArrayList<af> pairs = getPairs(null, null);
        if (pairs == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        Iterator<af> it = pairs.iterator();
        while (it.hasNext()) {
            af next = it.next();
            hashMap.put(next.mKey, next.mValue);
        }
        return hashMap;
    }

    @Override // android.content.SharedPreferences
    public String getString(String str, String str2) {
        ArrayList<af> pairs = getPairs(str, str2);
        if (pairs == null) {
            return null;
        }
        return (String) pairs.get(0).mValue;
    }

    @Override // android.content.SharedPreferences
    public Set<String> getStringSet(String str, Set<String> set) {
        ArrayList<af> pairs = getPairs(str, set);
        if (pairs == null || pairs.get(0).mValue == null) {
            return null;
        }
        String[] strArr = (String[]) pairs.get(0).mValue;
        HashSet hashSet = new HashSet(strArr.length);
        hashSet.addAll(Arrays.asList(strArr));
        return hashSet;
    }

    @Override // android.content.SharedPreferences
    public int getInt(String str, int i2) {
        ArrayList<af> pairs = getPairs(str, String.valueOf(i2));
        if (pairs == null) {
            return i2;
        }
        Object obj = pairs.get(0).mValue;
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return obj instanceof String ? Integer.decode((String) obj).intValue() : i2;
    }

    @Override // android.content.SharedPreferences
    public long getLong(String str, long j2) {
        ArrayList<af> pairs = getPairs(str, String.valueOf(j2));
        if (pairs == null) {
            return j2;
        }
        Object obj = pairs.get(0).mValue;
        if (obj instanceof Long) {
            return ((Long) obj).longValue();
        }
        return obj instanceof String ? Long.decode((String) obj).longValue() : j2;
    }

    @Override // android.content.SharedPreferences
    public float getFloat(String str, float f2) {
        ArrayList<af> pairs = getPairs(str, String.valueOf(f2));
        if (pairs == null) {
            return f2;
        }
        Object obj = pairs.get(0).mValue;
        if (obj instanceof Float) {
            return ((Float) obj).floatValue();
        }
        return obj instanceof String ? Float.valueOf((String) obj).floatValue() : f2;
    }

    @Override // android.content.SharedPreferences
    public boolean getBoolean(String str, boolean z) {
        ArrayList<af> pairs = getPairs(str, String.valueOf(z));
        if (pairs == null) {
            return z;
        }
        Object obj = pairs.get(0).mValue;
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return obj instanceof String ? Boolean.valueOf((String) obj).booleanValue() : z;
    }

    @Override // android.content.SharedPreferences
    public boolean contains(String str) {
        if (this.mDefaultUri == null) {
            return false;
        }
        Bundle bundle = null;
        try {
            bundle = this.mApp.getContentResolver().call(this.mDefaultUri, str != null ? this.mUriString + c.a.SEPARATOR + str : this.mUriString, "contains", (Bundle) null);
        } catch (Throwable th) {
            com.bytedance.apm6.util.log.b.e(com.bytedance.apm6.util.timetask.d.TAG, "contains", th);
        }
        return bundle != null && bundle.getBoolean("contains");
    }

    @Override // android.content.SharedPreferences
    public SharedPreferences.Editor edit() {
        return new a();
    }

    /* loaded from: classes.dex */
    private class a implements SharedPreferences.Editor {

        /* renamed from: b  reason: collision with root package name */
        private boolean f1638b;

        /* renamed from: c  reason: collision with root package name */
        private HashMap<String, Object> f1639c;

        private a() {
            this.f1638b = false;
            this.f1639c = new HashMap<>();
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putString(String str, String str2) {
            this.f1639c.put(str, str2);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putStringSet(String str, Set<String> set) {
            this.f1639c.put(str, com.bytedance.apm6.util.g.toArray(set));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putInt(String str, int i2) {
            this.f1639c.put(str, Integer.valueOf(i2));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putLong(String str, long j2) {
            this.f1639c.put(str, Long.valueOf(j2));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putFloat(String str, float f2) {
            this.f1639c.put(str, Float.valueOf(f2));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor putBoolean(String str, boolean z) {
            this.f1639c.put(str, Boolean.valueOf(z));
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor remove(String str) {
            this.f1639c.put(str, null);
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public SharedPreferences.Editor clear() {
            this.f1639c.clear();
            this.f1638b = true;
            return this;
        }

        @Override // android.content.SharedPreferences.Editor
        public boolean commit() {
            apply();
            return true;
        }

        @Override // android.content.SharedPreferences.Editor
        public void apply() {
            if (this.f1638b || !this.f1639c.isEmpty()) {
                Bundle bundle = new Bundle();
                if (this.f1638b) {
                    bundle.putBoolean("clear", true);
                }
                ArrayList<? extends Parcelable> arrayList = new ArrayList<>(this.f1639c.size());
                for (Map.Entry<String, Object> entry : this.f1639c.entrySet()) {
                    arrayList.add(new af(entry.getKey(), entry.getValue()));
                }
                bundle.putParcelableArrayList("edit", arrayList);
                try {
                    MultiProcessSharedPreferences.this.mApp.getContentResolver().call(MultiProcessSharedPreferences.this.mDefaultUri, MultiProcessSharedPreferences.this.mUriString, "edit", bundle);
                } catch (Exception e2) {
                    com.bytedance.apm6.util.log.b.e(MultiProcessSharedPreferences.TAG, "apply exception: ", e2);
                }
            }
        }
    }

    private ArrayList<af> getPairs(String str, Object obj) {
        Bundle bundle;
        Bundle bundle2 = new Bundle();
        if (obj != null) {
            bundle2.putParcelable("sp", new af(str, obj));
        }
        try {
            ContentResolver contentResolver = this.mApp.getContentResolver();
            Uri uri = this.mDefaultUri;
            String str2 = str != null ? this.mUriString + c.a.SEPARATOR + str : this.mUriString;
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

    public static SharedPreferences getSharedPreferences(Context context) {
        return getProviderSharedPreferences(context);
    }

    private static SharedPreferences getProviderSharedPreferences(Context context) {
        Application application;
        if (context instanceof Application) {
            application = (Application) context;
        } else {
            application = (Application) context.getApplicationContext();
        }
        return new MultiProcessSharedPreferences(application);
    }

    private synchronized Pair<SharedPreferences, String> parseMethod(Uri uri) {
        List<String> pathSegments = uri.getPathSegments();
        if (pathSegments == null || pathSegments.size() < 2 || !"sp".equals(pathSegments.get(0))) {
            return null;
        }
        return new Pair<>(getContext().getSharedPreferences(pathSegments.get(1), 0), pathSegments.size() > 2 ? pathSegments.get(2) : null);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        this.mContentResolver = getContext().getContentResolver();
        return false;
    }

    private Bundle querySP(SharedPreferences sharedPreferences, String str, Object obj) {
        Bundle bundle = new Bundle();
        ArrayList<? extends Parcelable> arrayList = new ArrayList<>();
        if (str == null) {
            for (Map.Entry<String, ?> entry : sharedPreferences.getAll().entrySet()) {
                arrayList.add(new af(entry.getKey(), entry.getValue()));
            }
            bundle.putParcelableArrayList("sp", arrayList);
        } else {
            Object obj2 = sharedPreferences.getAll().get(str);
            if (obj2 != null) {
                obj = obj2;
            }
            if (obj instanceof Set) {
                obj = com.bytedance.apm6.util.g.toArray((Set) obj);
            }
            arrayList.add(new af(str, obj));
            bundle.putParcelableArrayList("sp", arrayList);
        }
        return bundle;
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        af afVar;
        if (bundle != null) {
            bundle.setClassLoader(getClass().getClassLoader());
        }
        Pair<SharedPreferences, String> parseMethod = parseMethod(Uri.parse(str));
        Object obj = null;
        if (parseMethod == null) {
            return null;
        }
        if ("query".equals(str2)) {
            if (bundle != null && (afVar = (af) bundle.getParcelable("sp")) != null) {
                obj = afVar.mValue;
            }
            return querySP((SharedPreferences) parseMethod.first, (String) parseMethod.second, obj);
        } else if ("contains".equals(str2)) {
            return containsSP((SharedPreferences) parseMethod.first, (String) parseMethod.second);
        } else {
            if ("edit".equals(str2) && bundle != null) {
                try {
                    editSP((SharedPreferences) parseMethod.first, bundle);
                    long clearCallingIdentity = Binder.clearCallingIdentity();
                    this.mContentResolver.notifyChange(Uri.parse(str), null);
                    Binder.restoreCallingIdentity(clearCallingIdentity);
                } catch (Throwable th) {
                    com.bytedance.apm6.util.log.b.e(TAG, "edit", th);
                }
            }
            return null;
        }
    }

    private Bundle containsSP(SharedPreferences sharedPreferences, String str) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("contains", sharedPreferences.contains(str));
        return bundle;
    }

    private void editSP(SharedPreferences sharedPreferences, Bundle bundle) {
        SharedPreferences.Editor edit = sharedPreferences.edit();
        if (bundle.getBoolean("clear")) {
            edit.clear();
        }
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("edit");
        if (parcelableArrayList == null) {
            return;
        }
        Iterator it = parcelableArrayList.iterator();
        while (it.hasNext()) {
            af afVar = (af) it.next();
            if (afVar.mValue == null) {
                edit.remove(afVar.mKey);
            } else {
                edit(edit, afVar.mKey, afVar.mValue);
            }
        }
        edit.commit();
    }

    private static void edit(SharedPreferences.Editor editor, String str, Object obj) {
        if (obj instanceof String) {
            editor.putString(str, (String) obj);
        } else if (obj instanceof Integer) {
            editor.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Long) {
            editor.putLong(str, ((Long) obj).longValue());
        } else if (obj instanceof Float) {
            editor.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Boolean) {
            editor.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof String[]) {
            editor.putStringSet(str, new HashSet(Arrays.asList((String[]) obj)));
        }
    }
}
