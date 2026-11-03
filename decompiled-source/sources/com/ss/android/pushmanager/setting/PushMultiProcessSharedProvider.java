package com.ss.android.pushmanager.setting;

import android.app.Application;
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
import android.util.Log;
import com.bytedance.common.utility.o;
import com.bytedance.push.utils.h;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
/* loaded from: classes9.dex */
public class PushMultiProcessSharedProvider extends ContentProvider {
    public static final String ALL_TYPE = "all";
    public static final String BOOLEAN_TYPE = "boolean";
    public static final String FLOAT_TYPE = "float";
    public static final String INT_TYPE = "integer";
    private static final String KEY = "key";
    private static final String KEY_APP_FOREGROUND = "current_app_foreground";
    private static final String KEY_APP_STATE = "current_app_state";
    private static final String KEY_COLUMN = "key_column";
    public static final String LONG_TYPE = "long";
    private static final int MATCH_DATA = 65536;
    public static final String SP_CONFIG_NAME = "push_multi_process_config";
    public static final String STRING_TYPE = "string";
    private static final String TAG = "PushMultiProcessSP";
    private static final String TYPE = "type";
    private static final String TYPE_COLUMN = "type_column";
    private static final String VALUE_COLUMN = "value_column";
    private static boolean mAllowStartOthersProcess = false;
    public static Uri sBaseUri = null;
    public static boolean sCurIsProviderProcess = false;
    private static b sInstance;
    private static UriMatcher sMatcher;
    public static String sShareAuthority;
    private Map<String, Object> mContentValues = new ConcurrentHashMap();
    private volatile boolean mHasCreate = false;
    private final Object mLock = new Object();
    private SharedPreferences mSharedPreferences;

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

    private static boolean isInitWithFailure() {
        return TextUtils.isEmpty(sShareAuthority) || sMatcher == null;
    }

    @Override // android.content.ContentProvider
    public void attachInfo(Context context, ProviderInfo providerInfo) {
        if (providerInfo != null) {
            sShareAuthority = providerInfo.authority;
        }
        sCurIsProviderProcess = true;
        super.attachInfo(context, providerInfo);
    }

    private static void init(Context context) {
        if (TextUtils.isEmpty(sShareAuthority)) {
            sShareAuthority = getProviderAuthority(context, PushMultiProcessSharedProvider.class.getName());
        }
        if (TextUtils.isEmpty(sShareAuthority)) {
            throw new IllegalStateException("Must Set MultiProcessSharedProvider Authority");
        }
        if (h.debug()) {
            h.d("MultiProcessSharedProvider", sShareAuthority);
        }
        UriMatcher uriMatcher = new UriMatcher(-1);
        sMatcher = uriMatcher;
        uriMatcher.addURI(sShareAuthority, "*/*", 65536);
        sBaseUri = Uri.parse("content://" + sShareAuthority);
    }

    public static String getProviderAuthority(Context context, String str) {
        ProviderInfo[] providerInfoArr;
        h.d("MultiProcessSharedProvider", "getProviderAuthority:" + str);
        if (context == null || o.isEmpty(str)) {
            return null;
        }
        try {
            for (ProviderInfo providerInfo : context.getPackageManager().getPackageInfo(context.getPackageName(), 8).providers) {
                if (str.equals(providerInfo.name)) {
                    return providerInfo.authority;
                }
            }
        } catch (Exception unused) {
        }
        return context.getPackageName() + ".push.SHARE_PROVIDER_AUTHORITY";
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        sCurIsProviderProcess = true;
        Context context = getContext();
        if (context != null) {
            com.ss.android.message.a.initApp((Application) context.getApplicationContext());
        }
        if (sMatcher == null) {
            try {
                if (h.debug()) {
                    h.d("MultiProcessSharedProvider", "init form onCreate");
                }
                init(context);
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                return false;
            }
        }
        return true;
    }

    private void delayOnCreate() {
        if (this.mHasCreate) {
            return;
        }
        synchronized (this.mLock) {
            if (!this.mHasCreate) {
                loadValues();
                this.mHasCreate = true;
            }
        }
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        h.d("MultiProcessSharedProvider", "getType:" + uri.toString());
        sCurIsProviderProcess = true;
        delayOnCreate();
        return "vnd.android.cursor.item/vnd." + sShareAuthority + ".item";
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        h.d("MultiProcessSharedProvider", "delete:" + uri.toString() + " " + str);
        sCurIsProviderProcess = true;
        delayOnCreate();
        if (isInitWithFailure() || sMatcher.match(uri) == 65536) {
            return 0;
        }
        throw new IllegalArgumentException("Unsupported uri " + uri);
    }

    /* JADX WARN: Removed duplicated region for block: B:71:0x00a4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:76:0x0042 A[SYNTHETIC] */
    @Override // android.content.ContentProvider
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.net.Uri insert(android.net.Uri r11, android.content.ContentValues r12) {
        /*
            Method dump skipped, instructions count: 420
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider.insert(android.net.Uri, android.content.ContentValues):android.net.Uri");
    }

    private Runnable createNotifyRunnable(final String str, final String str2) {
        return new Runnable() { // from class: com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider.1
            @Override // java.lang.Runnable
            public void run() {
                PushMultiProcessSharedProvider pushMultiProcessSharedProvider = PushMultiProcessSharedProvider.this;
                pushMultiProcessSharedProvider.notifyContentChanged(PushMultiProcessSharedProvider.getContentUri(pushMultiProcessSharedProvider.getContext(), str, str2));
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        Object obj;
        MatrixCursor matrixCursor;
        h.d("MultiProcessSharedProvider", "query:" + uri.toString());
        int i2 = 1;
        sCurIsProviderProcess = true;
        delayOnCreate();
        MatrixCursor matrixCursor2 = null;
        if (isInitWithFailure()) {
            return null;
        }
        if (sMatcher.match(uri) == 65536) {
            try {
                if ("all".equals(uri.getPathSegments().get(1))) {
                    Map<String, ?> all = getMultiProcessSharedPreferences().getAll();
                    matrixCursor = new MatrixCursor(new String[]{KEY_COLUMN, VALUE_COLUMN, TYPE_COLUMN});
                    try {
                        Iterator<Map.Entry<String, ?>> it = all.entrySet().iterator();
                        while (true) {
                            boolean hasNext = it.hasNext();
                            matrixCursor2 = hasNext;
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
                    } catch (Exception unused) {
                        matrixCursor2 = matrixCursor;
                        return matrixCursor2;
                    }
                } else {
                    String str4 = uri.getPathSegments().get(0);
                    boolean equal = o.equal(str4, KEY_APP_STATE);
                    boolean equal2 = o.equal(str4, KEY_APP_FOREGROUND);
                    if (!this.mContentValues.containsKey(str4) && !equal && !equal2) {
                        return null;
                    }
                    MatrixCursor matrixCursor3 = new MatrixCursor(new String[]{str4});
                    try {
                        if (equal) {
                            obj = Boolean.valueOf(com.bytedance.common.push.b.getIns().isApplicationActive());
                        } else if (equal2) {
                            obj = Boolean.valueOf(!com.bytedance.common.push.b.getIns().isBackGround());
                        } else {
                            obj = this.mContentValues.get(str4);
                        }
                        MatrixCursor.RowBuilder newRow2 = matrixCursor3.newRow();
                        if (obj instanceof Boolean) {
                            if (!((Boolean) obj).booleanValue()) {
                                i2 = 0;
                            }
                            obj = Integer.valueOf(i2);
                        }
                        if (h.debug()) {
                            h.d("PushService", "MultiProcessShareProvider reallly get key = " + str4 + " value = " + obj.toString());
                        }
                        newRow2.add(obj);
                        matrixCursor = matrixCursor3;
                        matrixCursor2 = newRow2;
                    } catch (Exception unused2) {
                        matrixCursor2 = matrixCursor3;
                        return matrixCursor2;
                    }
                }
                return matrixCursor;
            } catch (Exception unused3) {
            }
        } else {
            throw new IllegalArgumentException("Unsupported uri " + uri);
        }
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        sCurIsProviderProcess = true;
        throw new UnsupportedOperationException();
    }

    private void loadValues() {
        SharedPreferences multiProcessSharedPreferences = getMultiProcessSharedPreferences();
        if (multiProcessSharedPreferences == null) {
            return;
        }
        for (Map.Entry<String, ?> entry : multiProcessSharedPreferences.getAll().entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            if (key != null && value != null) {
                this.mContentValues.put(key, value);
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
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider.getStringValue(android.database.Cursor, java.lang.String):java.lang.String");
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
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider.getBooleanValue(android.database.Cursor, boolean):boolean");
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
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider.getIntValue(android.database.Cursor, int):int");
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
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider.getLongValue(android.database.Cursor, long):long");
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
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.pushmanager.setting.PushMultiProcessSharedProvider.getFloatValue(android.database.Cursor, float):float");
    }

    public static a edit(Context context) {
        return new a(context);
    }

    public static synchronized b getMultiprocessShared(Context context) {
        b bVar;
        synchronized (PushMultiProcessSharedProvider.class) {
            if (sInstance == null) {
                sInstance = new b(context);
            }
            bVar = sInstance;
        }
        return bVar;
    }

    /* loaded from: classes9.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        Context f18505a;

        /* renamed from: b  reason: collision with root package name */
        private ContentValues f18506b;

        /* renamed from: c  reason: collision with root package name */
        private SharedPreferences f18507c;

        private a(Context context) {
            this.f18506b = new ContentValues();
            Context applicationContext = context.getApplicationContext();
            this.f18505a = applicationContext;
            this.f18507c = applicationContext.getApplicationContext().getSharedPreferences(PushMultiProcessSharedProvider.SP_CONFIG_NAME, 4);
        }

        public synchronized void apply() {
            if (PushMultiProcessSharedProvider.allowStartOthersProcess(this.f18505a)) {
                try {
                    this.f18505a.getContentResolver().insert(PushMultiProcessSharedProvider.getContentUri(this.f18505a, "key", "type"), this.f18506b);
                } catch (Throwable unused) {
                }
            } else {
                SharedPreferences.Editor edit = this.f18507c.edit();
                Set<String> keySet = this.f18506b.keySet();
                if (keySet != null) {
                    Object[] array = keySet.toArray();
                    if (array == null) {
                        return;
                    }
                    for (Object obj : array) {
                        String str = (String) obj;
                        Object obj2 = this.f18506b.get(str);
                        if (obj2 instanceof String) {
                            edit.putString(str, (String) obj2);
                        } else if (obj2 instanceof Long) {
                            edit.putLong(str, ((Long) obj2).longValue());
                        } else if (obj2 instanceof Boolean) {
                            edit.putBoolean(str, ((Boolean) obj2).booleanValue());
                        } else if (obj2 instanceof Integer) {
                            edit.putInt(str, ((Integer) obj2).intValue());
                        } else if (obj2 instanceof Float) {
                            edit.putFloat(str, ((Float) obj2).floatValue());
                        }
                    }
                    edit.apply();
                }
            }
        }

        public synchronized void commit() {
            apply();
        }

        public a putString(String str, String str2) {
            this.f18506b.put(str, str2);
            return this;
        }

        public a putLong(String str, long j2) {
            this.f18506b.put(str, Long.valueOf(j2));
            return this;
        }

        public a putBoolean(String str, boolean z) {
            this.f18506b.put(str, Boolean.valueOf(z));
            return this;
        }

        public a putInt(String str, int i2) {
            this.f18506b.put(str, Integer.valueOf(i2));
            return this;
        }

        public a putFloat(String str, float f2) {
            this.f18506b.put(str, Float.valueOf(f2));
            return this;
        }

        public void remove(String str) {
            this.f18506b.putNull(str);
        }
    }

    public static void setAllowStartOthersProcess(boolean z) {
        mAllowStartOthersProcess = z;
    }

    public static boolean allowStartOthersProcess(Context context) {
        boolean isSmpProcess = com.ss.android.message.util.b.isSmpProcess(context);
        h.d(TAG, "mAllowStartOthersProcess  is " + mAllowStartOthersProcess + " isSmpProcess is " + isSmpProcess);
        if (isSmpProcess) {
            boolean isMainProcessStart = com.ss.android.message.util.b.isMainProcessStart(context);
            h.d(TAG, "isMainProcessStartEd is " + isMainProcessStart);
            if (isMainProcessStart) {
                return true;
            }
            return mAllowStartOthersProcess;
        }
        return true;
    }

    /* loaded from: classes9.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        private Context f18508a;

        /* renamed from: c  reason: collision with root package name */
        private SharedPreferences f18509c;

        private b(Context context) {
            Context applicationContext = context.getApplicationContext();
            this.f18508a = applicationContext;
            this.f18509c = applicationContext.getApplicationContext().getSharedPreferences(PushMultiProcessSharedProvider.SP_CONFIG_NAME, 4);
            if (h.debug()) {
                h.d("PushService", "MultiProcessShared create");
            }
        }

        public a edit() {
            return new a(this.f18508a);
        }

        public String getString(String str, String str2) {
            try {
                if (!PushMultiProcessSharedProvider.sCurIsProviderProcess && PushMultiProcessSharedProvider.allowStartOthersProcess(this.f18508a)) {
                    return PushMultiProcessSharedProvider.getStringValue(this.f18508a.getContentResolver().query(PushMultiProcessSharedProvider.getContentUri(this.f18508a, str, "string"), null, null, null, null), str2);
                }
                return this.f18509c.getString(str, str2);
            } catch (Throwable unused) {
                return str2;
            }
        }

        public long getLong(String str, long j2) {
            try {
                if (!PushMultiProcessSharedProvider.sCurIsProviderProcess && PushMultiProcessSharedProvider.allowStartOthersProcess(this.f18508a)) {
                    return PushMultiProcessSharedProvider.getLongValue(this.f18508a.getContentResolver().query(PushMultiProcessSharedProvider.getContentUri(this.f18508a, str, "long"), null, null, null, null), j2);
                }
                return this.f18509c.getLong(str, j2);
            } catch (Throwable unused) {
                return j2;
            }
        }

        public float getFloat(String str, float f2) {
            try {
                if (!PushMultiProcessSharedProvider.sCurIsProviderProcess && PushMultiProcessSharedProvider.allowStartOthersProcess(this.f18508a)) {
                    return PushMultiProcessSharedProvider.getFloatValue(this.f18508a.getContentResolver().query(PushMultiProcessSharedProvider.getContentUri(this.f18508a, str, "float"), null, null, null, null), f2);
                }
                return this.f18509c.getFloat(str, f2);
            } catch (Throwable unused) {
                return f2;
            }
        }

        public boolean getBoolean(String str, boolean z) {
            try {
                if (!PushMultiProcessSharedProvider.sCurIsProviderProcess && PushMultiProcessSharedProvider.allowStartOthersProcess(this.f18508a)) {
                    return PushMultiProcessSharedProvider.getBooleanValue(this.f18508a.getContentResolver().query(PushMultiProcessSharedProvider.getContentUri(this.f18508a, str, "boolean"), null, null, null, null), z);
                }
                return this.f18509c.getBoolean(str, z);
            } catch (Throwable unused) {
                return z;
            }
        }

        public int getInt(String str, int i2) {
            try {
                if (!PushMultiProcessSharedProvider.sCurIsProviderProcess && PushMultiProcessSharedProvider.allowStartOthersProcess(this.f18508a)) {
                    return PushMultiProcessSharedProvider.getIntValue(this.f18508a.getContentResolver().query(PushMultiProcessSharedProvider.getContentUri(this.f18508a, str, "integer"), null, null, null, null), i2);
                }
                return this.f18509c.getInt(str, i2);
            } catch (Throwable unused) {
                return i2;
            }
        }

        public boolean isApplicationActive() {
            if (PushMultiProcessSharedProvider.sCurIsProviderProcess || !PushMultiProcessSharedProvider.allowStartOthersProcess(this.f18508a)) {
                return a();
            }
            return getBoolean(PushMultiProcessSharedProvider.KEY_APP_STATE, false);
        }

        private boolean a() {
            return com.bytedance.common.push.b.getIns().isApplicationActive();
        }

        public boolean isApplicationForeground() {
            if (PushMultiProcessSharedProvider.sCurIsProviderProcess || !PushMultiProcessSharedProvider.allowStartOthersProcess(this.f18508a)) {
                return b();
            }
            return getBoolean(PushMultiProcessSharedProvider.KEY_APP_FOREGROUND, false);
        }

        private boolean b() {
            return !com.bytedance.common.push.b.getIns().isBackGround();
        }
    }

    public static final synchronized Uri getContentUri(Context context, String str, String str2) {
        Uri build;
        synchronized (PushMultiProcessSharedProvider.class) {
            Log.d(TAG, "getContentUri: key is " + str + " type is " + str2);
            if (sBaseUri == null) {
                try {
                    if (h.debug()) {
                        h.d("MultiProcessSharedProvider", "init form getContentUri");
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
