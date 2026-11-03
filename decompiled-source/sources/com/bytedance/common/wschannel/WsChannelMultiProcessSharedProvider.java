package com.bytedance.common.wschannel;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.UriMatcher;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes2.dex */
public class WsChannelMultiProcessSharedProvider extends ContentProvider {
    public static final String ALL_TYPE = "all";
    public static final String BOOLEAN_TYPE = "boolean";
    public static final String FLOAT_TYPE = "float";
    public static final String INT_TYPE = "integer";
    private static final String KEY = "key";
    private static final String KEY_COLUMN = "key_column";
    public static final String LONG_TYPE = "long";
    private static final int MATCH_DATA = 65536;
    private static final String SP_CONFIG_NAME = "wschannel_multi_process_config";
    public static final String STRING_TYPE = "string";
    private static final String TYPE = "type";
    private static final String TYPE_COLUMN = "type_column";
    private static final String VALUE_COLUMN = "value_column";
    public static Uri sBaseUri = null;
    private static b sInstance = null;
    private static boolean sIsRegisterInMainProcess = false;
    private static volatile boolean sLoadValuesInit = false;
    private static UriMatcher sMatcher;
    public static String sShareAuthority;
    private SharedPreferences mSharedPreferences;
    private Map<String, Object> mContentValues = new ConcurrentHashMap();
    private final Object sLoadValuesInitLock = new Object();

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyContentChanged(Uri uri) {
        getContext().getContentResolver().notifyChange(uri, null);
    }

    private synchronized SharedPreferences getMultiProcessSharedPreferences() {
        SharedPreferences sharedPreferences = this.mSharedPreferences;
        if (sharedPreferences == null) {
            SharedPreferences sharedPreferences2 = getContext().getApplicationContext().getSharedPreferences(SP_CONFIG_NAME, Build.VERSION.SDK_INT >= 11 ? 4 : 0);
            this.mSharedPreferences = sharedPreferences2;
            return sharedPreferences2;
        }
        return sharedPreferences;
    }

    private static void init(Context context) {
        if (TextUtils.isEmpty(sShareAuthority)) {
            sShareAuthority = getProviderAuthority(context, WsChannelMultiProcessSharedProvider.class.getName());
        }
        if (TextUtils.isEmpty(sShareAuthority)) {
            throw new IllegalStateException("Must Set MultiProcessSharedProvider Authority");
        }
        if (Logger.debug()) {
            Logger.v("MultiProcessSharedProvider", sShareAuthority);
        }
        UriMatcher uriMatcher = new UriMatcher(-1);
        sMatcher = uriMatcher;
        uriMatcher.addURI(sShareAuthority, "*/*", 65536);
        sBaseUri = Uri.parse("content://" + sShareAuthority);
    }

    public static String getProviderAuthority(Context context, String str) {
        ProviderInfo[] providerInfoArr;
        if (context != null && !com.bytedance.common.utility.o.isEmpty(str)) {
            try {
                for (ProviderInfo providerInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 8).providers) {
                    if (str.equals(providerInfo.name)) {
                        return providerInfo.authority;
                    }
                }
            } catch (Exception unused) {
            }
        }
        return null;
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
        if (com.bytedance.common.wschannel.utils.e.a(getContext())) {
            sIsRegisterInMainProcess = true;
        }
        o.a(getContext());
        if (sMatcher == null) {
            try {
                if (Logger.debug()) {
                    Logger.v("MultiProcessSharedProvider", "init form onCreate");
                }
                init(getContext());
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return "vnd.android.cursor.item/vnd." + sShareAuthority + ".item";
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        if (sMatcher.match(uri) == 65536) {
            try {
                getMultiProcessSharedPreferences().edit().clear().commit();
                this.mContentValues.clear();
                notifyContentChanged(getContentUri(getContext(), "key", "type"));
                return 0;
            } catch (Exception unused) {
                return 0;
            }
        }
        throw new IllegalArgumentException("Unsupported uri " + uri);
    }

    /* JADX WARN: Removed duplicated region for block: B:69:0x007f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:73:0x001c A[SYNTHETIC] */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.net.Uri insert(android.net.Uri r11, android.content.ContentValues r12) {
        /*
            Method dump skipped, instructions count: 378
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.wschannel.WsChannelMultiProcessSharedProvider.insert(android.net.Uri, android.content.ContentValues):android.net.Uri");
    }

    private Runnable createNotifyRunnable(final String str, final String str2) {
        return new Runnable() { // from class: com.bytedance.common.wschannel.WsChannelMultiProcessSharedProvider.1
            @Override // java.lang.Runnable
            public void run() {
                WsChannelMultiProcessSharedProvider wsChannelMultiProcessSharedProvider = WsChannelMultiProcessSharedProvider.this;
                wsChannelMultiProcessSharedProvider.notifyContentChanged(WsChannelMultiProcessSharedProvider.getContentUri(wsChannelMultiProcessSharedProvider.getContext(), str, str2));
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v13, types: [java.lang.Integer] */
    /* JADX WARN: Type inference failed for: r6v15, types: [boolean] */
    /* JADX WARN: Type inference failed for: r7v8, types: [boolean] */
    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        int i2;
        ?? equals;
        MatrixCursor matrixCursor;
        if (sMatcher.match(uri) == 65536) {
            Cursor cursor = null;
            try {
                i2 = 1;
                equals = "all".equals(uri.getPathSegments().get(1));
            } catch (Exception unused) {
            }
            try {
                if (equals != 0) {
                    Map<String, ?> all = getMultiProcessSharedPreferences().getAll();
                    matrixCursor = new MatrixCursor(new String[]{KEY_COLUMN, VALUE_COLUMN, TYPE_COLUMN});
                    Iterator<Map.Entry<String, ?>> it = all.entrySet().iterator();
                    while (true) {
                        ?? hasNext = it.hasNext();
                        cursor = hasNext;
                        if (hasNext != 0) {
                            Map.Entry<String, ?> next = it.next();
                            String key = next.getKey();
                            Object value = next.getValue();
                            MatrixCursor.RowBuilder newRow = matrixCursor.newRow();
                            String str3 = "string";
                            if (!(value instanceof String)) {
                                if (value instanceof Boolean) {
                                    str3 = "boolean";
                                    value = Integer.valueOf(((Boolean) value).booleanValue() ? 1 : 0);
                                } else if (value instanceof Integer) {
                                    str3 = "integer";
                                } else if (value instanceof Long) {
                                    str3 = "long";
                                } else if (value instanceof Float) {
                                    str3 = "float";
                                }
                            }
                            newRow.add(key);
                            newRow.add(value);
                            newRow.add(str3);
                        }
                    }
                } else {
                    String str4 = uri.getPathSegments().get(0);
                    loadValues();
                    if (!this.mContentValues.containsKey(str4)) {
                        return null;
                    }
                    matrixCursor = new MatrixCursor(new String[]{str4});
                    Object obj = this.mContentValues.get(str4);
                    MatrixCursor.RowBuilder newRow2 = matrixCursor.newRow();
                    boolean z = obj instanceof Boolean;
                    Cursor cursor2 = obj;
                    if (z) {
                        if (!((Boolean) obj).booleanValue()) {
                            i2 = 0;
                        }
                        cursor2 = Integer.valueOf(i2);
                    }
                    if (Logger.debug()) {
                        Logger.v("PushService", "MultiProcessShareProvider reallly get key = " + str4 + " value = " + cursor2.toString());
                    }
                    newRow2.add(cursor2);
                    cursor = cursor2;
                }
                return matrixCursor;
            } catch (Exception unused2) {
                cursor = equals;
                return cursor;
            }
        }
        throw new IllegalArgumentException("Unsupported uri " + uri);
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException();
    }

    private void loadValues() {
        if (sLoadValuesInit) {
            return;
        }
        synchronized (this.sLoadValuesInitLock) {
            if (!sLoadValuesInit) {
                SharedPreferences multiProcessSharedPreferences = getMultiProcessSharedPreferences();
                if (multiProcessSharedPreferences == null) {
                    sLoadValuesInit = true;
                    return;
                }
                for (Map.Entry<String, ?> entry : multiProcessSharedPreferences.getAll().entrySet()) {
                    Object value = entry.getValue();
                    this.mContentValues.put(entry.getKey(), value);
                }
                sLoadValuesInit = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0014, code lost:
        if (r1 != null) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0017, code lost:
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x000e, code lost:
        if (r1 != null) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0010, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getStringValue(android.database.Cursor r1, java.lang.String r2) {
        /*
            if (r1 != 0) goto L3
            return r2
        L3:
            boolean r0 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L14
            if (r0 == 0) goto Le
            r0 = 0
            java.lang.String r2 = r1.getString(r0)     // Catch: java.lang.Throwable -> L14
        Le:
            if (r1 == 0) goto L17
        L10:
            r1.close()     // Catch: java.lang.Exception -> L17
            goto L17
        L14:
            if (r1 == 0) goto L17
            goto L10
        L17:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.wschannel.WsChannelMultiProcessSharedProvider.getStringValue(android.database.Cursor, java.lang.String):java.lang.String");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0013, code lost:
        if (r1 != null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0015, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x0019, code lost:
        if (r1 != null) goto L14;
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x001c, code lost:
        return r2;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean getBooleanValue(android.database.Cursor r1, boolean r2) {
        /*
            if (r1 != 0) goto L3
            return r2
        L3:
            boolean r0 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L19
            if (r0 == 0) goto L13
            r0 = 0
            int r2 = r1.getInt(r0)     // Catch: java.lang.Throwable -> L19
            if (r2 <= 0) goto L12
            r2 = 1
            goto L13
        L12:
            r2 = r0
        L13:
            if (r1 == 0) goto L1c
        L15:
            r1.close()     // Catch: java.lang.Exception -> L1c
            goto L1c
        L19:
            if (r1 == 0) goto L1c
            goto L15
        L1c:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.wschannel.WsChannelMultiProcessSharedProvider.getBooleanValue(android.database.Cursor, boolean):boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0014, code lost:
        if (r1 != null) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0017, code lost:
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x000e, code lost:
        if (r1 != null) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0010, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static int getIntValue(android.database.Cursor r1, int r2) {
        /*
            if (r1 != 0) goto L3
            return r2
        L3:
            boolean r0 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L14
            if (r0 == 0) goto Le
            r0 = 0
            int r2 = r1.getInt(r0)     // Catch: java.lang.Throwable -> L14
        Le:
            if (r1 == 0) goto L17
        L10:
            r1.close()     // Catch: java.lang.Exception -> L17
            goto L17
        L14:
            if (r1 == 0) goto L17
            goto L10
        L17:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.wschannel.WsChannelMultiProcessSharedProvider.getIntValue(android.database.Cursor, int):int");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0014, code lost:
        if (r1 != null) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0017, code lost:
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x000e, code lost:
        if (r1 != null) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0010, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static long getLongValue(android.database.Cursor r1, long r2) {
        /*
            if (r1 != 0) goto L3
            return r2
        L3:
            boolean r0 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L14
            if (r0 == 0) goto Le
            r0 = 0
            long r2 = r1.getLong(r0)     // Catch: java.lang.Throwable -> L14
        Le:
            if (r1 == 0) goto L17
        L10:
            r1.close()     // Catch: java.lang.Exception -> L17
            goto L17
        L14:
            if (r1 == 0) goto L17
            goto L10
        L17:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.wschannel.WsChannelMultiProcessSharedProvider.getLongValue(android.database.Cursor, long):long");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0014, code lost:
        if (r1 != null) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0017, code lost:
        return r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x000e, code lost:
        if (r1 != null) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0010, code lost:
        r1.close();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static float getFloatValue(android.database.Cursor r1, float r2) {
        /*
            if (r1 != 0) goto L3
            return r2
        L3:
            boolean r0 = r1.moveToFirst()     // Catch: java.lang.Throwable -> L14
            if (r0 == 0) goto Le
            r0 = 0
            float r2 = r1.getFloat(r0)     // Catch: java.lang.Throwable -> L14
        Le:
            if (r1 == 0) goto L17
        L10:
            r1.close()     // Catch: java.lang.Exception -> L17
            goto L17
        L14:
            if (r1 == 0) goto L17
            goto L10
        L17:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.common.wschannel.WsChannelMultiProcessSharedProvider.getFloatValue(android.database.Cursor, float):float");
    }

    public static a edit(Context context) {
        return new a(context);
    }

    public static synchronized b getMultiprocessShared(Context context) {
        b bVar;
        synchronized (WsChannelMultiProcessSharedProvider.class) {
            if (sInstance == null) {
                sInstance = new b(context);
            }
            bVar = sInstance;
        }
        return bVar;
    }

    /* loaded from: classes2.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        Context f5359a;

        /* renamed from: b  reason: collision with root package name */
        private ContentValues f5360b;

        private a(Context context) {
            this.f5360b = new ContentValues();
            this.f5359a = context.getApplicationContext();
        }

        public synchronized void a() {
            try {
                this.f5359a.getContentResolver().insert(WsChannelMultiProcessSharedProvider.getContentUri(this.f5359a, "key", "type"), this.f5360b);
            } catch (Throwable unused) {
            }
        }

        public synchronized void b() {
            a();
        }

        public a a(String str, String str2) {
            this.f5360b.put(str, str2);
            return this;
        }

        public a a(String str, long j2) {
            this.f5360b.put(str, Long.valueOf(j2));
            return this;
        }

        public a a(String str, boolean z) {
            this.f5360b.put(str, Boolean.valueOf(z));
            return this;
        }

        public a a(String str, int i2) {
            this.f5360b.put(str, Integer.valueOf(i2));
            return this;
        }

        public a a(String str, float f2) {
            this.f5360b.put(str, Float.valueOf(f2));
            return this;
        }

        public void a(String str) {
            this.f5360b.putNull(str);
        }

        public void c() {
            try {
                this.f5359a.getContentResolver().delete(WsChannelMultiProcessSharedProvider.getContentUri(this.f5359a, "key", "type"), null, null);
            } catch (Throwable unused) {
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private Context f5361a;

        /* renamed from: b  reason: collision with root package name */
        private boolean f5362b;

        /* renamed from: c  reason: collision with root package name */
        private SharedPreferences f5363c;

        private b(Context context) {
            boolean z = false;
            this.f5362b = false;
            if (com.bytedance.common.wschannel.utils.e.a(context) && WsChannelMultiProcessSharedProvider.sIsRegisterInMainProcess) {
                z = true;
            }
            this.f5362b = z;
            Context applicationContext = context.getApplicationContext();
            this.f5361a = applicationContext;
            this.f5363c = applicationContext.getApplicationContext().getSharedPreferences(WsChannelMultiProcessSharedProvider.SP_CONFIG_NAME, 4);
            if (Logger.debug()) {
                Logger.v("PushService", "MultiProcessShared create");
            }
        }

        public a a() {
            return new a(this.f5361a);
        }

        public String a(String str, String str2) {
            try {
                if (!this.f5362b) {
                    return WsChannelMultiProcessSharedProvider.getStringValue(this.f5361a.getContentResolver().query(WsChannelMultiProcessSharedProvider.getContentUri(this.f5361a, str, "string"), null, null, null, null), str2);
                }
                return this.f5363c.getString(str, str2);
            } catch (Throwable unused) {
                return str2;
            }
        }

        public long a(String str, long j2) {
            try {
                if (!this.f5362b) {
                    return WsChannelMultiProcessSharedProvider.getLongValue(this.f5361a.getContentResolver().query(WsChannelMultiProcessSharedProvider.getContentUri(this.f5361a, str, "long"), null, null, null, null), j2);
                }
                return this.f5363c.getLong(str, j2);
            } catch (Throwable unused) {
                return j2;
            }
        }

        public float a(String str, float f2) {
            try {
                if (!this.f5362b) {
                    return WsChannelMultiProcessSharedProvider.getFloatValue(this.f5361a.getContentResolver().query(WsChannelMultiProcessSharedProvider.getContentUri(this.f5361a, str, "float"), null, null, null, null), f2);
                }
                return this.f5363c.getFloat(str, f2);
            } catch (Throwable unused) {
                return f2;
            }
        }

        public boolean a(String str, boolean z) {
            try {
                if (!this.f5362b) {
                    return WsChannelMultiProcessSharedProvider.getBooleanValue(this.f5361a.getContentResolver().query(WsChannelMultiProcessSharedProvider.getContentUri(this.f5361a, str, "boolean"), null, null, null, null), z);
                }
                return this.f5363c.getBoolean(str, z);
            } catch (Throwable unused) {
                return z;
            }
        }

        public int a(String str, int i2) {
            try {
                if (!this.f5362b) {
                    return WsChannelMultiProcessSharedProvider.getIntValue(this.f5361a.getContentResolver().query(WsChannelMultiProcessSharedProvider.getContentUri(this.f5361a, str, "integer"), null, null, null, null), i2);
                }
                return this.f5363c.getInt(str, i2);
            } catch (Throwable unused) {
                return i2;
            }
        }
    }

    public static final synchronized Uri getContentUri(Context context, String str, String str2) {
        Uri build;
        synchronized (WsChannelMultiProcessSharedProvider.class) {
            if (sBaseUri == null) {
                try {
                    if (Logger.debug()) {
                        Logger.d("MultiProcessSharedProvider", "init form getContentUri");
                    }
                    init(context);
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return null;
                }
            }
            build = sBaseUri.buildUpon().appendPath(str).appendPath(str2).build();
        }
        return build;
    }
}
