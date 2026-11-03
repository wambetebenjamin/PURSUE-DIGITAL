package com.ss.android.common.app;

import android.app.Activity;
import com.bytedance.common.utility.collection.e;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/* compiled from: ActivityStackManager.java */
/* loaded from: classes9.dex */
public class a {

    /* renamed from: a  reason: collision with root package name */
    private static Set<String> f18031a = new HashSet();

    /* renamed from: b  reason: collision with root package name */
    private static e<InterfaceC0327a> f18032b = new e<>();

    /* renamed from: c  reason: collision with root package name */
    private static volatile int f18033c;

    /* compiled from: ActivityStackManager.java */
    /* renamed from: com.ss.android.common.app.a$a  reason: collision with other inner class name */
    /* loaded from: classes9.dex */
    public interface InterfaceC0327a {
        String getRecorderKey();

        boolean isFinishing();
    }

    public static String buildKey(Activity activity) {
        StringBuilder append = new StringBuilder().append(activity.getClass().getCanonicalName()).append("@");
        int i2 = f18033c;
        f18033c = i2 + 1;
        return append.append(i2).toString();
    }

    public static void addRecorder(InterfaceC0327a interfaceC0327a) {
        if (interfaceC0327a != null) {
            try {
                f18032b.add(interfaceC0327a);
                f18031a.add(interfaceC0327a.getRecorderKey());
            } catch (Throwable unused) {
            }
        }
    }

    public static void removeRecorder(InterfaceC0327a interfaceC0327a) {
        if (interfaceC0327a != null) {
            try {
                f18031a.remove(interfaceC0327a.getRecorderKey());
            } catch (Throwable unused) {
            }
        }
    }

    public static String getAliveActivitiesString() {
        Set<String> set = f18031a;
        if (set != null && !set.isEmpty()) {
            try {
                StringBuilder sb = new StringBuilder();
                int i2 = 0;
                for (String str : f18031a) {
                    if (i2 < f18031a.size() - 1) {
                        sb.append(str).append("|");
                    } else {
                        sb.append(str);
                    }
                    i2++;
                }
                return sb.toString();
            } catch (Throwable unused) {
            }
        }
        return "";
    }

    public static String getFinishedActivitiesString() {
        e<InterfaceC0327a> eVar = f18032b;
        if (eVar != null && !eVar.isEmpty()) {
            try {
                StringBuilder sb = new StringBuilder();
                int i2 = 0;
                Iterator<InterfaceC0327a> it = f18032b.iterator();
                while (it.hasNext()) {
                    InterfaceC0327a next = it.next();
                    if (next != null && !f18031a.contains(next.getRecorderKey()) && next.isFinishing()) {
                        if (i2 < f18032b.size() - 1) {
                            sb.append(next.getRecorderKey()).append("|");
                        } else {
                            sb.append(next.getRecorderKey());
                        }
                    }
                    i2++;
                }
                return sb.toString();
            } catch (Throwable unused) {
            }
        }
        return "";
    }
}
