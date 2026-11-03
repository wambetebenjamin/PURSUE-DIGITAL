package com.bytedance.push.settings.storage;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import com.bytedance.push.interfaze.n;
import com.bytedance.push.settings.SettingsManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* loaded from: classes2.dex */
public class MainProcessSettingsProvider extends ContentProvider {
    public static final String BOOLEAN_TYPE = "boolean";
    public static final String DEFAULT_VAL = "val";
    public static final String FLOAT_TYPE = "float";
    public static final String INT_TYPE = "integer";
    static final String KEY = "key";
    public static final String LONG_TYPE = "long";
    private static final int MATCH_DATA = 65536;
    public static final String STRING_TYPE = "string";
    static final String TYPE = "type";
    public static Uri sBaseUri;
    private static volatile boolean sInProviderProcess;
    private static UriMatcher sMatcher;
    public static String sShareAuthority;

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        if (providerInfo != null) {
            sShareAuthority = providerInfo.authority;
        }
        super.attachInfo(context, providerInfo);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        sInProviderProcess = true;
        if (sMatcher == null) {
            try {
                init(getContext());
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return true;
    }

    public static boolean isInProviderProcess(Context context) {
        return sInProviderProcess || com.bytedance.push.settings.utils.a.a(context);
    }

    private static void init(Context context) {
        if (TextUtils.isEmpty(sShareAuthority)) {
            sShareAuthority = getProviderAuthority(context, MainProcessSettingsProvider.class.getName());
        }
        if (TextUtils.isEmpty(sShareAuthority)) {
            throw new IllegalStateException("Must Set MultiProcessSharedProvider Authority");
        }
        UriMatcher uriMatcher = new UriMatcher(-1);
        sMatcher = uriMatcher;
        uriMatcher.addURI(sShareAuthority, "*/*/*/*", 65536);
        sBaseUri = Uri.parse("content://" + sShareAuthority);
    }

    private static boolean isInitWithFailure() {
        return TextUtils.isEmpty(sShareAuthority) || sMatcher == null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Runnable createNotifyRunnable(final Context context, final String str, final String str2, final String str3) {
        return new Runnable() { // from class: com.bytedance.push.settings.storage.MainProcessSettingsProvider.1
            @Override // java.lang.Runnable
            public void run() {
                Uri contentUri = MainProcessSettingsProvider.getContentUri(context, new b(str, str2, "val", str3));
                if (contentUri == null) {
                    return;
                }
                try {
                    context.getContentResolver().notifyChange(contentUri, null);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        };
    }

    public static synchronized Uri getObserverUri(Context context, String str, String str2, String str3) {
        synchronized (MainProcessSettingsProvider.class) {
            if (sBaseUri == null) {
                try {
                    init(context);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
            if (context != null && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
                return new b(str, str2, "val", str3).a(sBaseUri);
            }
            return sBaseUri;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static synchronized Uri getContentUri(Context context, b bVar) {
        synchronized (MainProcessSettingsProvider.class) {
            if (sBaseUri == null) {
                try {
                    init(context);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
            if (bVar == null) {
                return sBaseUri;
            }
            return bVar.a(sBaseUri);
        }
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return "vnd.android.cursor.item/vnd." + sShareAuthority + ".item";
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        b create;
        Object string;
        if (isInitWithFailure() || sMatcher.match(uri) != 65536 || (create = b.create(uri)) == null || TextUtils.isEmpty(create.f11881a) || TextUtils.isEmpty(create.f11882b) || TextUtils.isEmpty(create.f11884d)) {
            return null;
        }
        i create2 = SettingsManager.getStorageFactory().create(getContext(), true, create.f11881a, "main");
        com.bytedance.push.settings.logger.b.a().a("query on MainProcessSettingsProvider for " + create.f11881a + " storage class name is " + create2.getClass().getName());
        String str3 = create.f11884d;
        str3.hashCode();
        char c2 = 65535;
        switch (str3.hashCode()) {
            case -891985903:
                if (str3.equals("string")) {
                    c2 = 0;
                    break;
                }
                break;
            case 3327612:
                if (str3.equals("long")) {
                    c2 = 1;
                    break;
                }
                break;
            case 64711720:
                if (str3.equals("boolean")) {
                    c2 = 2;
                    break;
                }
                break;
            case 97526364:
                if (str3.equals("float")) {
                    c2 = 3;
                    break;
                }
                break;
            case 1958052158:
                if (str3.equals("integer")) {
                    c2 = 4;
                    break;
                }
                break;
        }
        switch (c2) {
            case 0:
                string = create2.getString(create.f11882b, create.f11883c);
                break;
            case 1:
                string = Long.valueOf(create2.getLong(create.f11882b, Long.parseLong(create.f11883c)));
                break;
            case 2:
                string = Integer.valueOf(create2.getBoolean(create.f11882b, Boolean.parseBoolean(create.f11883c)) ? 1 : 0);
                break;
            case 3:
                string = Float.valueOf(create2.getFloat(create.f11882b, Float.parseFloat(create.f11883c)));
                break;
            case 4:
                string = Integer.valueOf(create2.getInt(create.f11882b, Integer.parseInt(create.f11883c)));
                break;
            default:
                throw new IllegalArgumentException("unknown type");
        }
        MatrixCursor matrixCursor = new MatrixCursor(new String[]{create.f11882b});
        matrixCursor.newRow().add(string);
        return matrixCursor;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        SharedPreferences.Editor edit;
        ArrayList<Runnable> arrayList;
        String str;
        if (isInitWithFailure()) {
            return null;
        }
        if (sMatcher.match(uri) == 65536) {
            List<String> pathSegments = uri.getPathSegments();
            if (pathSegments == null) {
                return null;
            }
            String str2 = pathSegments.get(0);
            try {
                i create = SettingsManager.getStorageFactory().create(getContext(), true, str2, "main");
                com.bytedance.push.settings.logger.b.a().a("insert on MainProcessSettingsProvider for " + str2 + " storage class name is " + create.getClass().getName());
                edit = create.edit();
                arrayList = new ArrayList();
                for (Map.Entry<String, Object> entry : contentValues.valueSet()) {
                    Object value = entry.getValue();
                    String key = entry.getKey();
                    if (value == null) {
                        edit.remove(key);
                        str = null;
                    } else if (value instanceof String) {
                        if (!TextUtils.equals(create.getString(key), (String) value)) {
                            edit.putString(key, (String) value);
                            str = "string";
                        }
                    } else if (value instanceof Boolean) {
                        if (create.getBoolean(key) != ((Boolean) value).booleanValue()) {
                            edit.putBoolean(key, ((Boolean) value).booleanValue());
                            str = "boolean";
                        }
                    } else if (value instanceof Long) {
                        if (create.getLong(key) != ((Long) value).longValue()) {
                            edit.putLong(key, ((Long) value).longValue());
                            str = "long";
                        }
                    } else if (value instanceof Integer) {
                        if (create.getInt(key) != ((Integer) value).intValue()) {
                            edit.putInt(key, ((Integer) value).intValue());
                            str = "integer";
                        }
                    } else if (value instanceof Float) {
                        if (create.getFloat(key) != ((Float) value).floatValue()) {
                            edit.putFloat(key, ((Float) value).floatValue());
                            str = "float";
                        }
                    } else {
                        throw new IllegalArgumentException("Unsupported type " + uri);
                    }
                    if (str != null) {
                        arrayList.add(createNotifyRunnable(getContext(), str2, key, str));
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            if (edit == null) {
                return null;
            }
            edit.apply();
            for (Runnable runnable : arrayList) {
                runnable.run();
            }
            return null;
        }
        throw new IllegalArgumentException("Unsupported uri " + uri);
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        e eVar = f.get(str);
        if (eVar == null) {
            return null;
        }
        return eVar.process(getContext(), str2, bundle, "main");
    }

    private static String getProviderAuthority(Context context, String str) {
        ProviderInfo[] providerInfoArr;
        com.bytedance.push.settings.logger.b.a().a(n.TAG, "getProviderAuthority: " + str);
        if (context == null) {
            return null;
        }
        try {
            if (!TextUtils.isEmpty(str)) {
                for (ProviderInfo providerInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 8).providers) {
                    if (str.equals(providerInfo.name)) {
                        return providerInfo.authority;
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return context.getPackageName() + ".push.settings";
    }
}
