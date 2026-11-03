package com.bytedance.apm.battery.dao;

import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import com.bytedance.apm.c;
import com.bytedance.apm.logging.e;
import com.bytedance.apm.util.m;
import com.bytedance.librarian.c;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
/* compiled from: BaseDao.java */
/* loaded from: classes.dex */
public abstract class a<T> {

    /* renamed from: a  reason: collision with root package name */
    protected static final int f591a = 50;

    /* renamed from: b  reason: collision with root package name */
    private final Context f592b;

    /* renamed from: c  reason: collision with root package name */
    private final String f593c;

    /* renamed from: d  reason: collision with root package name */
    private Uri f594d;

    /* renamed from: e  reason: collision with root package name */
    private String f595e;

    /* renamed from: f  reason: collision with root package name */
    private final HashMap<String, Integer> f596f = new HashMap<>();

    /* compiled from: BaseDao.java */
    /* renamed from: com.bytedance.apm.battery.dao.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0013a<T> {
        T get(b bVar);
    }

    public abstract String[] getColumns();

    public abstract ContentValues getContentValues(T t);

    public String getDbName() {
        return com.bytedance.apm.battery.dao.b.DB_NAME;
    }

    public abstract String getTableName();

    public boolean isWeedTakenOver() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public a() {
        Context context = c.getContext();
        this.f592b = context;
        this.f593c = context.getPackageName() + ".apm";
    }

    protected String a() {
        return c.getContext().getDatabasePath(getDbName()).getAbsolutePath();
    }

    public Uri getUri() {
        if (this.f594d == null) {
            this.f594d = Uri.parse("content://" + this.f593c + c.a.SEPARATOR + getDbName() + c.a.SEPARATOR + getTableName());
        }
        return this.f594d;
    }

    public List<T> query(String str, String[] strArr, String str2, InterfaceC0013a<T> interfaceC0013a) {
        int indexOf;
        Cursor cursor = null;
        try {
            Cursor query = this.f592b.getContentResolver().query(getUri(), getColumns(), str, strArr, str2);
            if (query != null) {
                try {
                    if (query.getCount() > 0) {
                        int i2 = Integer.MAX_VALUE;
                        if (!TextUtils.isEmpty(str2) && (indexOf = str2.indexOf("LIMIT")) > 0) {
                            int indexOf2 = str2.indexOf("OFF");
                            if (indexOf2 > 0) {
                                i2 = Integer.valueOf(str2.substring(indexOf + 5, indexOf2).trim()).intValue();
                            } else {
                                i2 = Integer.valueOf(str2.substring(indexOf + 5).trim()).intValue();
                            }
                        }
                        LinkedList linkedList = new LinkedList();
                        for (int i3 = 0; query.moveToNext() && i3 < i2; i3++) {
                            linkedList.add(interfaceC0013a.get(new b(query, this.f596f)));
                        }
                        a(query);
                        return linkedList;
                    }
                } catch (Throwable unused) {
                    cursor = query;
                    a(cursor);
                    return Collections.emptyList();
                }
            }
            List<T> emptyList = Collections.emptyList();
            a(query);
            return emptyList;
        } catch (Throwable unused2) {
        }
    }

    public int update(ContentValues contentValues, String str, String[] strArr) {
        if (contentValues == null) {
            return -1;
        }
        try {
            return com.bytedance.apm.c.getContext().getContentResolver().update(getUri(), contentValues, str, strArr);
        } catch (Exception unused) {
            return -1;
        }
    }

    public synchronized <I extends T> long insert(ContentValues contentValues) {
        if (contentValues == null) {
            return -1L;
        }
        try {
            Uri insert = com.bytedance.apm.c.getContext().getContentResolver().insert(getUri(), contentValues);
            if (insert == null) {
                return -1L;
            }
            try {
                return Long.parseLong(insert.getLastPathSegment());
            } catch (Exception unused) {
                return 1L;
            }
        } catch (Exception unused2) {
            return -1L;
        }
    }

    public synchronized void insertBatch(List<ContentValues> list) {
        if (m.isEmpty(list)) {
            return;
        }
        int size = list.size();
        int i2 = 0;
        while (i2 < size) {
            ArrayList<ContentProviderOperation> arrayList = new ArrayList<>(8);
            for (int i3 = 0; i3 < 50 && i2 < size; i3++) {
                ContentProviderOperation.Builder newInsert = ContentProviderOperation.newInsert(getUri());
                newInsert.withValues(list.get(i2));
                arrayList.add(newInsert.build());
                i2++;
            }
            try {
                ContentProviderResult[] applyBatch = com.bytedance.apm.c.getContext().getContentResolver().applyBatch(this.f593c, arrayList);
                if (com.bytedance.apm.c.isDebugMode()) {
                    int length = applyBatch.length;
                    for (int i4 = 0; i4 < length; i4++) {
                        e.i(com.bytedance.apm.logging.b.TAG_STORE, "insertBatch ret: ", applyBatch[i4].uri.toString());
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    public int delete(String str, String[] strArr) {
        try {
            return this.f592b.getContentResolver().delete(getUri(), str, strArr);
        } catch (Exception unused) {
            return -1;
        }
    }

    public long getTotalRowCount() {
        return getCount(null, null);
    }

    private String b() {
        if (this.f595e == null) {
            this.f595e = "SELECT count(*) FROM " + getTableName();
        }
        return this.f595e;
    }

    public long getCount(String str, String[] strArr) {
        long j2 = -1;
        Cursor cursor = null;
        try {
            cursor = MonitorContentProvider.rawQueryForResolver(com.bytedance.apm.c.getContext(), getUri(), str == null ? b() : b() + " where " + str, strArr);
            if (cursor != null && cursor.moveToNext()) {
                j2 = cursor.getLong(0);
            }
            return j2;
        } catch (Exception unused) {
            return -1L;
        } finally {
            a(cursor);
        }
    }

    public void execSql(String str) {
        MonitorContentProvider.execSQLForResolver(this.f592b, getUri(), str);
    }

    private static void a(Cursor cursor) {
        if (cursor != null) {
            try {
                if (cursor.isClosed()) {
                    return;
                }
                cursor.close();
            } catch (Exception unused) {
            }
        }
    }

    /* compiled from: BaseDao.java */
    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        Cursor f597a;

        /* renamed from: b  reason: collision with root package name */
        private final HashMap<String, Integer> f598b;

        private b(Cursor cursor, HashMap<String, Integer> hashMap) {
            this.f597a = cursor;
            this.f598b = hashMap;
        }

        public long getLong(String str) {
            try {
                return this.f597a.getLong(a(str));
            } catch (Throwable unused) {
                return -1L;
            }
        }

        public int getInt(String str) {
            try {
                return this.f597a.getInt(a(str));
            } catch (Throwable unused) {
                return -1;
            }
        }

        public String getString(String str) {
            try {
                return this.f597a.getString(a(str));
            } catch (Throwable unused) {
                return "";
            }
        }

        public double getDouble(String str) {
            try {
                return this.f597a.getDouble(a(str));
            } catch (Throwable unused) {
                return -1.0d;
            }
        }

        public float getFloat(String str) {
            try {
                return this.f597a.getFloat(a(str));
            } catch (Throwable unused) {
                return -1.0f;
            }
        }

        public short getShort(String str) {
            try {
                return this.f597a.getShort(a(str));
            } catch (Throwable unused) {
                return (short) -1;
            }
        }

        public byte[] getBlob(String str) {
            try {
                return this.f597a.getBlob(a(str));
            } catch (Throwable unused) {
                return new byte[0];
            }
        }

        private int a(String str) {
            Integer num = this.f598b.get(str);
            if (num == null) {
                try {
                    num = Integer.valueOf(this.f597a.getColumnIndex(str));
                } catch (Throwable unused) {
                    num = -1;
                }
                this.f598b.put(str, num);
            }
            return num.intValue();
        }
    }
}
