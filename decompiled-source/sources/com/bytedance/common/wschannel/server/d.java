package com.bytedance.common.wschannel.server;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.utility.reflect.Reflect;
import com.bytedance.common.wschannel.WsConstants;
import com.bytedance.common.wschannel.model.i;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONObject;
/* compiled from: FrontierMessageManager.java */
/* loaded from: classes2.dex */
public class d {

    /* renamed from: a  reason: collision with root package name */
    private static volatile e f5933a = null;

    /* renamed from: b  reason: collision with root package name */
    private static Context f5934b = null;

    /* renamed from: c  reason: collision with root package name */
    private static volatile boolean f5935c = false;

    /* renamed from: e  reason: collision with root package name */
    private static final int f5937e = 3000;

    /* renamed from: g  reason: collision with root package name */
    private static final String f5939g = "x_frontier_qos";

    /* renamed from: h  reason: collision with root package name */
    private static final String f5940h = "2";

    /* renamed from: i  reason: collision with root package name */
    private static final String f5941i = "x_frontier_msgid";

    /* renamed from: j  reason: collision with root package name */
    private static final String f5942j = "x_frontier_ttl";
    private static final String k = "x_frontier_is_ack";
    private static final String l = "1";
    private static final String m = "x_frontier_ack_msgid";
    private static final String n = "x_frontier_qos_ack";
    private static final String o = "1";
    private static final String p = "com.bytedance.ttnet.TTNetInit";
    private static a q = null;
    private static Object r = null;
    private static final String t = "frontier_qos2";

    /* renamed from: d  reason: collision with root package name */
    private static final AtomicBoolean f5936d = new AtomicBoolean(false);

    /* renamed from: f  reason: collision with root package name */
    private static CountDownLatch f5938f = new CountDownLatch(1);
    private static final String s = "d";

    /* compiled from: FrontierMessageManager.java */
    /* loaded from: classes2.dex */
    public interface a {
        void a(String str, JSONObject jSONObject);
    }

    public static void a(boolean z, Context context, a aVar) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        f5935c = z;
        f5934b = context;
        q = aVar;
    }

    public static void a(JSONObject jSONObject) {
        a aVar = q;
        if (aVar != null) {
            aVar.a(t, jSONObject);
        }
        try {
            Object obj = r;
            if (obj != null) {
                Reflect.on(obj).call("monitorLogSend", new Class[]{String.class, JSONObject.class}, t, jSONObject);
            }
        } catch (Throwable unused) {
        }
    }

    private static void e() {
        if (f5936d.get()) {
            return;
        }
        try {
            f5938f.await(WsConstants.EXIT_DELAY_TIME, TimeUnit.MILLISECONDS);
            if (f5938f.getCount() == 1) {
                f5938f.countDown();
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void a() {
        f5936d.set(true);
        f5938f.countDown();
    }

    private static Map<String, String> a(List<i.b> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (i.b bVar : list) {
            if (!TextUtils.isEmpty(bVar.a()) && !TextUtils.isEmpty(bVar.b())) {
                hashMap.put(bVar.a(), bVar.b());
            }
        }
        return hashMap;
    }

    private static boolean a(Map<String, String> map) {
        if (map.containsKey(f5939g) && map.containsKey("x_frontier_msgid") && map.containsKey(f5942j) && map.get(f5939g) != null && map.get(f5939g).equals("2")) {
            String str = map.get("x_frontier_msgid");
            try {
                if (!TextUtils.isEmpty(str)) {
                    return str.getBytes("UTF-8").length == 32 && Long.valueOf(map.get(f5942j)).longValue() > 0;
                }
            } catch (UnsupportedEncodingException | Exception unused) {
            }
        }
        return false;
    }

    private static boolean b(Map<String, String> map) {
        return map.containsKey(f5939g) && map.containsKey(n) && map.containsKey(k) && map.containsKey(m) && map.get(f5939g) != null && map.get(f5939g).equals("2") && map.get(n) != null && map.get(n).equals("1") && map.get(k) != null && map.get(k).equals("1") && !TextUtils.isEmpty(map.get(m));
    }

    private static String[] c(Map<String, String> map) {
        if (map.containsKey(k) && map.containsKey(m) && map.get(k).equals("1") && !TextUtils.isEmpty(map.get(m))) {
            return map.get(m).split(",");
        }
        return null;
    }

    private static boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.equals(f5939g) || str.equals("x_frontier_msgid") || str.equals(f5942j) || str.equals(m) || str.equals(k);
    }

    private static void b(com.bytedance.common.wschannel.model.i iVar) {
        Iterator<i.b> it;
        if (iVar == null || iVar.m() == null || (it = iVar.m().iterator()) == null) {
            return;
        }
        while (it.hasNext()) {
            if (a(it.next().a())) {
                it.remove();
            }
        }
    }

    private static void f() {
        if (r != null) {
            return;
        }
        try {
            r = Class.forName(p).newInstance();
            if (Logger.debug()) {
                Logger.d(s, "Get TTNetInit instance success.");
            }
        } catch (Throwable th) {
            Logger.e(s, "Get TTNetInit instance failed.");
            th.printStackTrace();
        }
    }

    public static boolean a(com.bytedance.common.wschannel.model.i iVar) {
        if (f5935c) {
            try {
                Map<String, String> a2 = a(iVar.m());
                if (a2 != null && !a2.isEmpty()) {
                    boolean b2 = b(a2);
                    boolean a3 = a(a2);
                    if (b2 || a3) {
                        AtomicBoolean atomicBoolean = f5936d;
                        if (!atomicBoolean.get()) {
                            f();
                            com.bytedance.common.wschannel.utils.f.a().a(new com.bytedance.common.wschannel.utils.g() { // from class: com.bytedance.common.wschannel.server.d.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    e unused = d.f5933a = new e(d.f5934b);
                                    d.f5933a.a();
                                }
                            });
                            e();
                        }
                        if (atomicBoolean.get() && f5933a != null) {
                            if (Logger.debug()) {
                                Logger.d(s, "origin wschannel msg:" + iVar.toString());
                            }
                            if (b2) {
                                f5933a.a(a2.get(m).split(","));
                                return true;
                            }
                            f5933a.a(c(a2));
                            b(iVar);
                            long longValue = Long.valueOf(a2.get(f5942j)).longValue();
                            return f5933a.a(a2.get("x_frontier_msgid"), longValue);
                        }
                        return false;
                    }
                    return false;
                }
                return false;
            } catch (Throwable th) {
                th.printStackTrace();
                return false;
            }
        }
        return false;
    }

    public static boolean b() {
        return f5935c;
    }
}
