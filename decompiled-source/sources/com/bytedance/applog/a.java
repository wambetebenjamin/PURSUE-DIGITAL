package com.bytedance.applog;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
/* compiled from: AppLogHelper.java */
/* loaded from: classes.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    public static b f2435a = new b() { // from class: com.bytedance.applog.a.1
        @Override // com.bytedance.applog.a.b
        public boolean a(com.bytedance.applog.b bVar) {
            return bVar.h() != null && bVar.h().D();
        }
    };

    /* compiled from: AppLogHelper.java */
    /* renamed from: com.bytedance.applog.a$a  reason: collision with other inner class name */
    /* loaded from: classes.dex */
    public interface InterfaceC0035a {
        void a(com.bytedance.applog.b bVar);
    }

    /* compiled from: AppLogHelper.java */
    /* loaded from: classes.dex */
    public interface b {
        boolean a(com.bytedance.applog.b bVar);
    }

    /* compiled from: AppLogHelper.java */
    /* loaded from: classes.dex */
    public interface c {
        com.bytedance.applog.store.a a();
    }

    public static boolean a(final String str) {
        return !TextUtils.isEmpty(str) && b(new b() { // from class: com.bytedance.applog.a.2
            @Override // com.bytedance.applog.a.b
            public boolean a(com.bytedance.applog.b bVar) {
                return str.equals(bVar.b());
            }
        });
    }

    public static com.bytedance.applog.b b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (com.bytedance.applog.b bVar : com.bytedance.applog.b.a()) {
            if (str.equals(bVar.b())) {
                return bVar;
            }
        }
        return null;
    }

    public static j c(String str) {
        com.bytedance.applog.b b2 = b(str);
        return b2 != null ? b2 : AppLog.a();
    }

    public static void a(com.bytedance.applog.store.a aVar) {
        for (com.bytedance.applog.b bVar : com.bytedance.applog.b.a()) {
            if (!com.bytedance.applog.util.q.a(aVar.b()) || !com.bytedance.applog.util.q.a(bVar.b()) || com.bytedance.applog.util.q.a(bVar.b(), aVar.b())) {
                bVar.a(aVar.clone());
            }
        }
    }

    public static void a(String[] strArr) {
        for (com.bytedance.applog.b bVar : com.bytedance.applog.b.a()) {
            bVar.a((String[]) strArr.clone());
        }
    }

    public static void a(com.bytedance.applog.store.a aVar, b bVar) {
        for (com.bytedance.applog.b bVar2 : com.bytedance.applog.b.a()) {
            if (bVar.a(bVar2)) {
                bVar2.a(aVar.clone());
            }
        }
    }

    public static void a(c cVar, b bVar) {
        com.bytedance.applog.store.a aVar = null;
        for (com.bytedance.applog.b bVar2 : com.bytedance.applog.b.a()) {
            if (bVar.a(bVar2)) {
                if (aVar == null) {
                    aVar = cVar.a();
                }
                bVar2.a(aVar.clone());
            }
        }
    }

    public static List<com.bytedance.applog.b> a(b bVar) {
        ArrayList arrayList = new ArrayList();
        for (com.bytedance.applog.b bVar2 : com.bytedance.applog.b.a()) {
            if (bVar.a(bVar2)) {
                arrayList.add(bVar2);
            }
        }
        return arrayList;
    }

    public static boolean b(b bVar) {
        for (com.bytedance.applog.b bVar2 : com.bytedance.applog.b.a()) {
            if (bVar.a(bVar2)) {
                return true;
            }
        }
        return false;
    }

    public static void a(InterfaceC0035a interfaceC0035a) {
        for (com.bytedance.applog.b bVar : com.bytedance.applog.b.a()) {
            interfaceC0035a.a(bVar);
        }
    }

    public static boolean a(j jVar) {
        return AppLog.a() == jVar;
    }

    public static boolean a() {
        return !TextUtils.isEmpty(AppLog.a().b());
    }

    public static String a(j jVar, String str) {
        return a(jVar) ? str : str + "_" + jVar.b();
    }
}
