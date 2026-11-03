package com.bytedance.ttgame.library.luffy.db;

import android.util.Log;
import androidx.exifinterface.media.ExifInterface;
import com.bytedance.ttgame.module.share.api.utils.ConstantKt;
import gsdk.impl.account.toutiao.dh;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: LuffyDatabase.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0013\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005¢\u0006\u0002\u0010\u0006J\u0017\u0010\u000e\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u000f\u001a\u00020\u0010H\u0002¢\u0006\u0002\u0010\u0011J\u0015\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0014J@\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00162\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\f2\"\u0010\u0017\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00190\u0018j\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0019`\u001aH\u0002J\u000e\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\fH\u0016J\u001d\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0002¢\u0006\u0002\u0010\u001fJ\u001d\u0010 \u001a\u00020\u00192\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010!\u001a\u00020\tH\u0002¢\u0006\u0002\u0010\"J\u0015\u0010#\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010$J\u0015\u0010%\u001a\u00020&2\u0006\u0010\u000f\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010'J\u0016\u0010%\u001a\u00020&2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\fH\u0016J\u001e\u0010(\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010)\u001a\u00020\u00132\u0006\u0010*\u001a\u00020\u0013H\u0016J\u001e\u0010+\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\u0006\u0010!\u001a\u00020\t2\u0006\u0010,\u001a\u00020-H\u0016J\u000e\u0010.\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0016J\u0017\u0010/\u001a\u0004\u0018\u00010\u00102\u0006\u00100\u001a\u00028\u0000H\u0002¢\u0006\u0002\u00101J%\u00102\u001a\u00020&2\u0006\u0010\u000f\u001a\u00028\u00002\u0006\u0010!\u001a\u00020\t2\u0006\u00103\u001a\u00020\u0019H\u0002¢\u0006\u0002\u00104JP\u00105\u001a\u00020&2\"\u00106\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00190\u0018j\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0019`\u001a2\"\u0010\u0017\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00190\u0018j\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0019`\u001aH\u0016J\u0010\u00107\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\fH\u0016J2\u0010\u0017\u001a\b\u0012\u0004\u0012\u00028\u00000\u00032\"\u0010\u0017\u001a\u001e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00190\u0018j\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0019`\u001aH\u0016R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f0\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000¨\u00068"}, d2 = {"Lcom/bytedance/ttgame/library/luffy/db/LuffyDatabase;", ExifInterface.GPS_DIRECTION_TRUE, "Ljava/io/Serializable;", "Lcom/bytedance/ttgame/library/luffy/db/ILuffyDataBase;", "entityClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)V", "entityClazz", "primaryKey", "", "query", "Ljava/lang/ThreadLocal;", "", "repo", "deSerial", "data", "", "([B)Ljava/io/Serializable;", "delete", "", "(Ljava/io/Serializable;)I", "filterByWheres", "", "where", "Ljava/util/HashMap;", "", "Lkotlin/collections/HashMap;", "getAll", "getDeclaredSUID", "", "cl", "(Ljava/lang/Class;)Ljava/lang/Long;", "getFieldValue", "key", "(Ljava/io/Serializable;Ljava/lang/String;)Ljava/lang/Object;", "getPrimaryValue", "(Ljava/io/Serializable;)Ljava/lang/String;", "insert", "", "(Ljava/io/Serializable;)V", "limit", "from", "to", "orderBy", ConstantKt.DESC, "", "selectAll", "serial", "obj", "(Ljava/io/Serializable;)[B", "setFieldValue", "value", "(Ljava/io/Serializable;Ljava/lang/String;Ljava/lang/Object;)V", "update", dh.z, "values", "luffy_db_release"}, k = 1, mv = {1, 4, 0})
/* loaded from: classes3.dex */
public final class d<T extends Serializable> implements com.bytedance.ttgame.library.luffy.db.b<T> {

    /* renamed from: a  reason: collision with root package name */
    private String f14212a;

    /* renamed from: b  reason: collision with root package name */
    private String f14213b;

    /* renamed from: c  reason: collision with root package name */
    private final Class<T> f14214c;

    /* renamed from: d  reason: collision with root package name */
    private final ThreadLocal<List<T>> f14215d;

    public d(Class<T> entityClass) {
        Intrinsics.checkNotNullParameter(entityClass, "entityClass");
        this.f14212a = "";
        this.f14213b = "";
        this.f14215d = new ThreadLocal<>();
        if (a((Class) entityClass) == null) {
            throw new RuntimeException("you must add explicit serialVersionUID field, or data migration would be failed~");
        }
        TableName tableName = (TableName) entityClass.getAnnotation(TableName.class);
        if (tableName == null) {
            throw new RuntimeException("entity should add TableName annotation!");
        }
        String value = tableName.value();
        this.f14212a = value;
        if (value.length() == 0) {
            throw new RuntimeException("table name cannot be empty!");
        }
        this.f14212a += ".table";
        Field[] declaredFields = entityClass.getDeclaredFields();
        Intrinsics.checkNotNullExpressionValue(declaredFields, "entityClass.declaredFields");
        boolean z = false;
        for (Field it : declaredFields) {
            if (it.isAnnotationPresent(PrimaryKeyName.class)) {
                Intrinsics.checkNotNullExpressionValue(it, "it");
                String name = it.getName();
                Intrinsics.checkNotNullExpressionValue(name, "it.name");
                this.f14213b = name;
                z = true;
            }
        }
        if (!z) {
            throw new RuntimeException("your entity should has a primary key!");
        }
        if (this.f14213b.length() == 0) {
            throw new RuntimeException("your primary key filed is empty!");
        }
        this.f14214c = entityClass;
    }

    @Override // com.bytedance.ttgame.library.luffy.db.b
    public void a(T data) {
        Intrinsics.checkNotNullParameter(data, "data");
        byte[] c2 = c(data);
        if (c2 != null) {
            e.f14220a.a(this.f14212a, d(data), c2);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bytedance.ttgame.library.luffy.db.b
    public void a(List<? extends T> data) {
        Intrinsics.checkNotNullParameter(data, "data");
        Iterator<T> it = data.iterator();
        while (it.hasNext()) {
            a((d<T>) ((Serializable) it.next()));
        }
    }

    @Override // com.bytedance.ttgame.library.luffy.db.b
    public int b(T data) {
        Intrinsics.checkNotNullParameter(data, "data");
        if (e.f14220a.a(this.f14212a, d(data))) {
            e.f14220a.b(this.f14212a, d(data));
            return 1;
        }
        return 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.bytedance.ttgame.library.luffy.db.b
    public void a(HashMap<String, Object> set, HashMap<String, Object> where) {
        Intrinsics.checkNotNullParameter(set, "set");
        Intrinsics.checkNotNullParameter(where, "where");
        for (Serializable serializable : a(a(), where)) {
            for (Map.Entry<String, Object> entry : set.entrySet()) {
                a((d<T>) serializable, entry.getKey(), entry.getValue());
            }
            byte[] c2 = c(serializable);
            if (c2 != null) {
                e.f14220a.a(this.f14212a, d(serializable), c2);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final List<T> a(List<? extends T> list, HashMap<String, Object> hashMap) {
        boolean z;
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            Serializable serializable = (Serializable) it.next();
            Iterator<Map.Entry<String, Object>> it2 = hashMap.entrySet().iterator();
            while (true) {
                z = true;
                if (!it2.hasNext()) {
                    break;
                }
                Map.Entry<String, Object> next = it2.next();
                if (!Intrinsics.areEqual(a((d<T>) serializable, next.getKey()), next.getValue())) {
                    z = false;
                    break;
                }
            }
            if (z) {
                arrayList.add(serializable);
            }
        }
        return arrayList;
    }

    /* compiled from: Comparisons.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I", "kotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes3.dex */
    public static final class a<T> implements Comparator<T> {

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ String f14217b;

        public a(String str) {
            this.f14217b = str;
        }

        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            return ComparisonsKt.compareValues(d.this.a((d) ((Serializable) t), this.f14217b).toString(), d.this.a((d) ((Serializable) t2), this.f14217b).toString());
        }
    }

    @Override // com.bytedance.ttgame.library.luffy.db.b
    public List<T> a() {
        ArrayList arrayList = new ArrayList();
        try {
            for (Map.Entry<String, Object> entry : e.f14220a.a(this.f14212a).entrySet()) {
                if (entry.getValue() != null) {
                    Object value = entry.getValue();
                    if (value == null) {
                        throw new NullPointerException("null cannot be cast to non-null type kotlin.ByteArray");
                    }
                    T a2 = a((byte[]) value);
                    if (a2 != null) {
                        arrayList.add(a2);
                    }
                }
            }
        } catch (Throwable th) {
            Log.e(com.bytedance.ttgame.library.luffy.core.b.TAG, "getAll failed " + th);
            th.printStackTrace();
        }
        return arrayList;
    }

    /* compiled from: Comparisons.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u000e\u0010\u0003\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u00022\u000e\u0010\u0005\u001a\n \u0004*\u0004\u0018\u0001H\u0002H\u0002H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "a", "kotlin.jvm.PlatformType", "b", "compare", "(Ljava/lang/Object;Ljava/lang/Object;)I", "kotlin/comparisons/ComparisonsKt__ComparisonsKt$compareByDescending$1"}, k = 3, mv = {1, 4, 0})
    /* loaded from: classes3.dex */
    public static final class b<T> implements Comparator<T> {

        /* renamed from: b  reason: collision with root package name */
        final /* synthetic */ String f14219b;

        public b(String str) {
            this.f14219b = str;
        }

        @Override // java.util.Comparator
        public final int compare(T t, T t2) {
            return ComparisonsKt.compareValues(d.this.a((d) ((Serializable) t2), this.f14219b).toString(), d.this.a((d) ((Serializable) t), this.f14219b).toString());
        }
    }

    @Override // com.bytedance.ttgame.library.luffy.db.b
    public com.bytedance.ttgame.library.luffy.db.b<T> b() {
        this.f14215d.set(a());
        return this;
    }

    @Override // com.bytedance.ttgame.library.luffy.db.b
    public com.bytedance.ttgame.library.luffy.db.b<T> a(HashMap<String, Object> where) {
        Intrinsics.checkNotNullParameter(where, "where");
        List<T> list = this.f14215d.get();
        if (list != null) {
            this.f14215d.set(a(list, where));
        }
        return this;
    }

    @Override // com.bytedance.ttgame.library.luffy.db.b
    public com.bytedance.ttgame.library.luffy.db.b<T> a(String key, boolean z) {
        List<T> sortedWith;
        List<T> sortedWith2;
        Intrinsics.checkNotNullParameter(key, "key");
        List<T> list = this.f14215d.get();
        if (z) {
            if (list != null && (sortedWith2 = CollectionsKt.sortedWith(list, new b(key))) != null) {
                this.f14215d.set(sortedWith2);
            }
        } else if (list != null && (sortedWith = CollectionsKt.sortedWith(list, new a(key))) != null) {
            this.f14215d.set(sortedWith);
        }
        return this;
    }

    @Override // com.bytedance.ttgame.library.luffy.db.b
    public com.bytedance.ttgame.library.luffy.db.b<T> a(int i2, int i3) {
        try {
            List<T> list = this.f14215d.get();
            List<T> subList = list != null ? list.subList(i2, i3) : null;
            ThreadLocal<List<T>> threadLocal = this.f14215d;
            if (subList == null) {
                subList = CollectionsKt.emptyList();
            }
            threadLocal.set(subList);
        } catch (IndexOutOfBoundsException unused) {
            StringBuilder append = new StringBuilder().append("your query result length can not match your need, current length is ");
            List<T> list2 = this.f14215d.get();
            Log.w(com.bytedance.ttgame.library.luffy.core.b.TAG, append.append(list2 != null ? Integer.valueOf(list2.size()) : null).toString());
        } catch (Throwable th) {
            Log.w(com.bytedance.ttgame.library.luffy.core.b.TAG, th);
        }
        return this;
    }

    @Override // com.bytedance.ttgame.library.luffy.db.b
    public List<T> c() {
        List<T> list = this.f14215d.get();
        this.f14215d.remove();
        return list;
    }

    private final byte[] c(T t) {
        ByteArrayOutputStream byteArrayOutputStream;
        ObjectOutputStream objectOutputStream;
        StringBuilder sb;
        byte[] bArr = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            } catch (Throwable th) {
                th = th;
                objectOutputStream = null;
            }
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
            objectOutputStream = null;
        }
        try {
            objectOutputStream.writeObject(t);
            objectOutputStream.flush();
            bArr = byteArrayOutputStream.toByteArray();
            try {
                objectOutputStream.close();
            } catch (Throwable th3) {
                Log.e(com.bytedance.ttgame.library.luffy.core.b.TAG, "close stream error " + th3);
                th3.printStackTrace();
            }
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th4) {
                th = th4;
                sb = new StringBuilder();
                Log.e(com.bytedance.ttgame.library.luffy.core.b.TAG, sb.append("close stream error ").append(th).toString());
                th.printStackTrace();
                return bArr;
            }
        } catch (Throwable th5) {
            th = th5;
            try {
                Log.e(com.bytedance.ttgame.library.luffy.core.b.TAG, "serial error " + th);
                th.printStackTrace();
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (Throwable th6) {
                        Log.e(com.bytedance.ttgame.library.luffy.core.b.TAG, "close stream error " + th6);
                        th6.printStackTrace();
                    }
                }
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th7) {
                        th = th7;
                        sb = new StringBuilder();
                        Log.e(com.bytedance.ttgame.library.luffy.core.b.TAG, sb.append("close stream error ").append(th).toString());
                        th.printStackTrace();
                        return bArr;
                    }
                }
                return bArr;
            } catch (Throwable th8) {
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (Throwable th9) {
                        Log.e(com.bytedance.ttgame.library.luffy.core.b.TAG, "close stream error " + th9);
                        th9.printStackTrace();
                    }
                }
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th10) {
                        Log.e(com.bytedance.ttgame.library.luffy.core.b.TAG, "close stream error " + th10);
                        th10.printStackTrace();
                    }
                }
                throw th8;
            }
        }
        return bArr;
    }

    private final T a(byte[] bArr) {
        ObjectInputStream objectInputStream;
        ByteArrayInputStream byteArrayInputStream;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                objectInputStream = new ObjectInputStream(byteArrayInputStream);
                try {
                    Object readObject = objectInputStream.readObject();
                    if (readObject == null) {
                        throw new NullPointerException("null cannot be cast to non-null type T");
                    }
                    T t = (T) readObject;
                    try {
                        byteArrayInputStream.close();
                    } catch (Throwable th) {
                        Log.e(com.bytedance.ttgame.library.luffy.core.b.TAG, "close stream error " + th);
                        th.printStackTrace();
                    }
                    try {
                        objectInputStream.close();
                    } catch (Throwable th2) {
                        Log.e(com.bytedance.ttgame.library.luffy.core.b.TAG, "close stream error " + th2);
                        th2.printStackTrace();
                    }
                    return t;
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        Log.e(com.bytedance.ttgame.library.luffy.core.b.TAG, "deSerial error " + th);
                        th.printStackTrace();
                        if (byteArrayInputStream != null) {
                            try {
                                byteArrayInputStream.close();
                            } catch (Throwable th4) {
                                Log.e(com.bytedance.ttgame.library.luffy.core.b.TAG, "close stream error " + th4);
                                th4.printStackTrace();
                            }
                        }
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                                return null;
                            } catch (Throwable th5) {
                                Log.e(com.bytedance.ttgame.library.luffy.core.b.TAG, "close stream error " + th5);
                                th5.printStackTrace();
                                return null;
                            }
                        }
                        return null;
                    } catch (Throwable th6) {
                        if (byteArrayInputStream != null) {
                            try {
                                byteArrayInputStream.close();
                            } catch (Throwable th7) {
                                Log.e(com.bytedance.ttgame.library.luffy.core.b.TAG, "close stream error " + th7);
                                th7.printStackTrace();
                            }
                        }
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (Throwable th8) {
                                Log.e(com.bytedance.ttgame.library.luffy.core.b.TAG, "close stream error " + th8);
                                th8.printStackTrace();
                            }
                        }
                        throw th6;
                    }
                }
            } catch (Throwable th9) {
                th = th9;
                objectInputStream = null;
            }
        } catch (Throwable th10) {
            th = th10;
            objectInputStream = null;
            byteArrayInputStream = null;
        }
    }

    private final Long a(Class<T> cls) {
        try {
            Field f2 = cls.getDeclaredField("serialVersionUID");
            Intrinsics.checkNotNullExpressionValue(f2, "f");
            if ((f2.getModifiers() & 24) == 24) {
                f2.setAccessible(true);
                return Long.valueOf(f2.getLong(null));
            }
        } catch (Exception unused) {
        }
        return null;
    }

    private final String d(T t) {
        String obj;
        Object obj2 = this.f14214c.getDeclaredField(this.f14213b).get(t);
        return (obj2 == null || (obj = obj2.toString()) == null) ? "" : obj;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object a(T t, String str) {
        Object obj = this.f14214c.getDeclaredField(str).get(t);
        Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.Any");
        return obj;
    }

    private final void a(T t, String str, Object obj) {
        this.f14214c.getDeclaredField(str).set(t, obj);
    }
}
