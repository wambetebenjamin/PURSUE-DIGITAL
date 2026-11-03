package com.bytedance.applog.log;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: AbstractAppLogLogger.java */
/* loaded from: classes.dex */
public abstract class b implements h {

    /* renamed from: a  reason: collision with root package name */
    private static final char f2723a = ' ';

    /* renamed from: d  reason: collision with root package name */
    private static final Map<String, h> f2724d = new ConcurrentHashMap();

    /* renamed from: b  reason: collision with root package name */
    private String f2725b;

    /* renamed from: c  reason: collision with root package name */
    private final List<String> f2726c;

    public b() {
        ArrayList arrayList = new ArrayList();
        this.f2726c = arrayList;
        arrayList.add("logger@" + hashCode());
    }

    @Override // com.bytedance.applog.log.h
    public void a(String str) {
        this.f2725b = str;
        f2724d.put(str, this);
        d("Current logger bind to appId {}", str);
    }

    public static h b(String str) {
        return f2724d.get(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void a(int i2, int i3, List<String> list, Throwable th, String str, Object... objArr) {
        if (m.a()) {
            return;
        }
        j a2 = j.a().a(this.f2725b).b(i2).a(i3).b(Thread.currentThread().getName()).a(th).a(a(list)).c(a(str, objArr)).a();
        m.a(a2);
        i a3 = m.a(this.f2725b);
        if (a3 != null) {
            a3.a(a2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public List<String> a(List<String> list) {
        if (list != null && list.size() > 0) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.f2726c);
            arrayList.addAll(list);
            return arrayList;
        }
        return this.f2726c;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String a(String str, Object... objArr) {
        try {
            StringBuilder sb = new StringBuilder();
            if (objArr != null && objArr.length != 0 && str.contains("{}")) {
                int length = str.length();
                int i2 = 0;
                int i3 = 0;
                while (i2 < length) {
                    char charAt = str.charAt(i2);
                    char charAt2 = i2 < length + (-1) ? str.charAt(i2 + 1) : f2723a;
                    if (charAt == '{' && charAt2 == '}') {
                        if (i3 < objArr.length) {
                            sb.append(a(objArr[i3]));
                            i3++;
                        }
                        i2++;
                    } else {
                        sb.append(charAt);
                    }
                    i2++;
                }
                return sb.toString();
            }
            sb.append(str);
            return sb.toString();
        } catch (Throwable unused) {
            return str;
        }
    }

    private String a(Object obj) {
        return obj != null ? obj.toString() : "";
    }
}
