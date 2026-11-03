package com.bytedance.notification.helper;

import android.text.TextUtils;
import com.bytedance.push.utils.h;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/* compiled from: NotificationGroupHelper.java */
/* loaded from: classes2.dex */
public class a {

    /* renamed from: b  reason: collision with root package name */
    private static volatile a f9878b;

    /* renamed from: a  reason: collision with root package name */
    private final String f9879a = "NotificationGroupHelper";

    /* renamed from: c  reason: collision with root package name */
    private final Map<String, Boolean> f9880c = new ConcurrentHashMap();

    /* renamed from: d  reason: collision with root package name */
    private final Map<String, Integer> f9881d = new ConcurrentHashMap();

    /* renamed from: e  reason: collision with root package name */
    private final Map<String, Integer> f9882e = new ConcurrentHashMap();

    public static a a() {
        if (f9878b == null) {
            synchronized (a.class) {
                if (f9878b == null) {
                    f9878b = new a();
                }
            }
        }
        return f9878b;
    }

    private a() {
    }

    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        h.d("NotificationGroupHelper", "on notification show , group is " + str);
        synchronized (this.f9881d) {
            Integer num = this.f9881d.get(str);
            if (num == null) {
                num = 0;
            }
            this.f9881d.put(str, Integer.valueOf(num.intValue() + 1));
        }
    }

    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        h.d("NotificationGroupHelper", "on notification delete , group is " + str);
        synchronized (this.f9881d) {
            Integer num = this.f9881d.get(str);
            if (num == null) {
                num = 1;
            }
            this.f9881d.put(str, Integer.valueOf(num.intValue() - 1));
        }
    }

    public void c(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        h.d("NotificationGroupHelper", "on summary notification delete , group is " + str);
        this.f9880c.put(str, false);
    }

    public boolean a(String str, int i2) {
        Boolean bool;
        Integer num = this.f9881d.get(str);
        if (num != null && num.intValue() == i2 && ((bool = this.f9880c.get(str)) == null || !bool.booleanValue())) {
            this.f9880c.put(str, true);
            h.d("NotificationGroupHelper", "need show summary notification for  " + str);
            return true;
        }
        h.d("NotificationGroupHelper", "need't show summary notification for  " + str + " groupCount is " + num + " groupFoldNum is " + i2);
        return false;
    }
}
