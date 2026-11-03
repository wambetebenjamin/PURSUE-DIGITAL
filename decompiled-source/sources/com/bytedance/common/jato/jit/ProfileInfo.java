package com.bytedance.common.jato.jit;

import android.os.Build;
import com.bytedance.apm.constant.r;
import com.bytedance.common.jato.d;
import com.bytedance.librarian.c;
import dalvik.annotation.optimization.FastNative;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/* loaded from: classes.dex */
public class ProfileInfo {

    /* renamed from: a  reason: collision with root package name */
    static ExecutorService f4773a = Executors.newSingleThreadExecutor();

    /* loaded from: classes.dex */
    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public String f4776a;

        /* renamed from: b  reason: collision with root package name */
        public String f4777b;

        /* renamed from: c  reason: collision with root package name */
        public String f4778c;

        /* renamed from: d  reason: collision with root package name */
        public int[] f4779d;

        /* renamed from: e  reason: collision with root package name */
        public String[] f4780e;
    }

    @FastNative
    public static native boolean createProfileForMethod(Method method, int[] iArr, Class[] clsArr);

    public static native void forceSaveProfile();

    public static native Method getMethod(String str, String str2, String str3);

    public static native synchronized boolean init();

    @FastNative
    public static native void jitMethod(Method method);

    static {
        d.loadLibrary();
    }

    public static void a(final ArrayList<a> arrayList, final long j2) {
        if (Build.VERSION.SDK_INT >= 29 && init()) {
            f4773a.execute(new Runnable() { // from class: com.bytedance.common.jato.jit.ProfileInfo.1
                @Override // java.lang.Runnable
                public void run() {
                    Method method;
                    try {
                        Thread.sleep(r.APP_START_MAX_LIMIT_MS);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        a aVar = (a) arrayList.get(i2);
                        if (aVar.f4780e != null && aVar.f4779d != null && aVar.f4780e.length == aVar.f4779d.length && (method = ProfileInfo.getMethod(aVar.f4776a, aVar.f4777b, aVar.f4778c)) != null) {
                            Class[] clsArr = new Class[aVar.f4780e.length];
                            for (int i3 = 0; i3 < aVar.f4780e.length; i3++) {
                                try {
                                    clsArr[i3] = Class.forName(aVar.f4780e[i3].replaceAll(c.a.SEPARATOR, c.a.DOT));
                                } catch (ClassNotFoundException e3) {
                                    e3.printStackTrace();
                                }
                            }
                            System.out.println("jato create profile for :" + aVar.f4776a + "|" + aVar.f4777b + "|" + aVar.f4778c);
                            b bVar = new b();
                            bVar.f4781a = method;
                            bVar.f4782b = aVar.f4779d;
                            bVar.f4783c = clsArr;
                            arrayList2.add(bVar);
                        }
                    }
                    JitCodeCacheGc.disable();
                    for (int i4 = 0; i4 < arrayList2.size(); i4++) {
                        b bVar2 = (b) arrayList2.get(i4);
                        ProfileInfo.createProfileForMethod(bVar2.f4781a, bVar2.f4782b, bVar2.f4783c);
                    }
                    ProfileInfo.forceSaveProfile();
                    JitCodeCacheGc.enable();
                    for (int i5 = 0; i5 < arrayList2.size(); i5++) {
                        ProfileInfo.jitMethod(((b) arrayList2.get(i5)).f4781a);
                        long j3 = j2;
                        if (j3 > 0) {
                            try {
                                Thread.sleep(j3);
                            } catch (InterruptedException e4) {
                                e4.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
    }

    /* loaded from: classes.dex */
    private static class b {

        /* renamed from: a  reason: collision with root package name */
        public Method f4781a;

        /* renamed from: b  reason: collision with root package name */
        public int[] f4782b;

        /* renamed from: c  reason: collision with root package name */
        public Class[] f4783c;

        private b() {
        }
    }
}
