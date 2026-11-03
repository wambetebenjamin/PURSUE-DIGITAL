package com.bytedance.ttnet.priority;

import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import com.bytedance.retrofit2.client.Request;
import com.bytedance.retrofit2.ttnet.IHttpCallThrottleControl;
import com.bytedance.ttnet.TTNetInit;
import com.bytedance.ttnet.config.j;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import org.json.JSONArray;
import org.json.JSONObject;
/* compiled from: TTHttpCallPriorityControl.java */
/* loaded from: classes8.dex */
public class i implements IHttpCallThrottleControl {

    /* renamed from: a  reason: collision with root package name */
    private static volatile i f15430a = null;

    /* renamed from: b  reason: collision with root package name */
    private static String f15431b = "TTHttpCallPriorityControl";

    /* renamed from: c  reason: collision with root package name */
    private final AtomicBoolean f15432c = new AtomicBoolean(false);

    /* renamed from: d  reason: collision with root package name */
    private final Map<String, a> f15433d = new ConcurrentHashMap();

    /* renamed from: e  reason: collision with root package name */
    private a f15434e = new com.bytedance.ttnet.priority.a();

    /* renamed from: f  reason: collision with root package name */
    private final ReadWriteLock f15435f = new ReentrantReadWriteLock();

    public static i a() {
        if (f15430a == null) {
            synchronized (j.class) {
                if (f15430a == null) {
                    f15430a = new i();
                }
            }
        }
        return f15430a;
    }

    /* compiled from: TTHttpCallPriorityControl.java */
    /* loaded from: classes8.dex */
    public enum b {
        DEFAULT(0),
        NORMAL_DELAY(1),
        RUNTIME_DELAY(2),
        PRIORITY(3),
        NOT_REACHED(4);
        

        /* renamed from: f  reason: collision with root package name */
        private final int f15448f;

        b(int i2) {
            this.f15448f = i2;
        }

        public int a() {
            return this.f15448f;
        }

        public static b a(int i2) {
            b[] values;
            for (b bVar : values()) {
                if (bVar.a() == i2) {
                    return bVar;
                }
            }
            throw new IllegalArgumentException("Invalid value: " + i2);
        }
    }

    public void a(String str) {
        a(this.f15433d.get(str));
    }

    public void a(c cVar) {
        a((a) cVar);
    }

    public void b() {
        a((a) null);
    }

    /* compiled from: TTHttpCallPriorityControl.java */
    /* loaded from: classes8.dex */
    public static abstract class a implements IHttpCallThrottleControl {

        /* renamed from: a  reason: collision with root package name */
        protected final e f15436a = new e();

        /* renamed from: b  reason: collision with root package name */
        protected String f15437b = "";

        /* renamed from: c  reason: collision with root package name */
        private int f15438c = -1;

        /* renamed from: d  reason: collision with root package name */
        private int f15439d = 1000;

        /* renamed from: e  reason: collision with root package name */
        private com.bytedance.frameworks.baselib.network.asynctask.a f15440e = null;

        /* JADX INFO: Access modifiers changed from: package-private */
        public boolean a(JSONObject jSONObject) {
            String optString = jSONObject.optString("state_name", "");
            this.f15437b = optString;
            if (TextUtils.isEmpty(optString)) {
                return false;
            }
            this.f15438c = jSONObject.optInt("state", -1);
            int optInt = jSONObject.optInt("delay_disable_time_ms", 1000);
            this.f15439d = optInt;
            this.f15439d = optInt > 0 ? optInt : 1000;
            return true;
        }

        public void a() {
            this.f15440e = new com.bytedance.frameworks.baselib.network.asynctask.a(this.f15439d, 0L) { // from class: com.bytedance.ttnet.priority.i.a.1
                @Override // java.lang.Runnable
                public void run() {
                    i.a().a("");
                }
            };
            com.bytedance.frameworks.baselib.network.asynctask.b.a(com.bytedance.frameworks.baselib.network.asynctask.c.NETWORK).a(this.f15440e);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public void b() {
            if (this.f15440e != null) {
                com.bytedance.frameworks.baselib.network.asynctask.b.a(com.bytedance.frameworks.baselib.network.asynctask.c.NETWORK).b(this.f15440e);
            }
        }

        @Override // com.bytedance.retrofit2.ttnet.IHttpCallThrottleControl
        public void notifyRequestCompleted(Request request) {
            this.f15436a.a(request);
        }

        b c() {
            return b.NOT_REACHED;
        }

        public void a(String str) {
            this.f15437b = str;
            this.f15436a.a(str);
        }

        public void a(int i2) {
            this.f15439d = i2;
        }
    }

    private void a(a aVar) {
        this.f15435f.writeLock().lock();
        try {
            this.f15434e.b();
            if (aVar == null) {
                aVar = new com.bytedance.ttnet.priority.a();
            }
            this.f15434e = aVar;
            aVar.a();
            if (Logger.debug()) {
                Logger.d(f15431b, "set mode " + this.f15434e.c());
            }
        } finally {
            this.f15435f.writeLock().unlock();
        }
    }

    public b c() {
        this.f15435f.writeLock().lock();
        try {
            return this.f15434e.c();
        } finally {
            this.f15435f.writeLock().unlock();
        }
    }

    @Override // com.bytedance.retrofit2.ttnet.IHttpCallThrottleControl
    public void maybeDropRequestOrBlockingDelay(Request request) {
        if (Logger.debug()) {
            Logger.d(f15431b, "current mode is " + c());
        }
        if ((this.f15432c.get() || this.f15434e.c() == b.DEFAULT) && a(request)) {
            this.f15434e.maybeDropRequestOrBlockingDelay(request);
        }
    }

    @Override // com.bytedance.retrofit2.ttnet.IHttpCallThrottleControl
    public boolean maybeDropRequestOrAsyncDelay(Request request, Executor executor, Runnable runnable) {
        if (Logger.debug()) {
            Logger.d(f15431b, "current mode is " + c());
        }
        if ((this.f15432c.get() || this.f15434e.c() == b.DEFAULT) && a(request)) {
            return this.f15434e.maybeDropRequestOrAsyncDelay(request, executor, runnable);
        }
        return false;
    }

    @Override // com.bytedance.retrofit2.ttnet.IHttpCallThrottleControl
    public void notifyRequestCompleted(Request request) {
        if (!this.f15432c.get() || request == null) {
            return;
        }
        this.f15434e.notifyRequestCompleted(request);
    }

    @Override // com.bytedance.retrofit2.ttnet.IHttpCallThrottleControl
    public Request setOriginalRequestProperties(Request request) {
        return (!this.f15432c.get() || request == null) ? request : this.f15434e.setOriginalRequestProperties(request);
    }

    public void a(JSONObject jSONObject) {
        this.f15432c.set(false);
        this.f15433d.clear();
        JSONArray optJSONArray = jSONObject.optJSONArray("tt_req_delay_config");
        boolean z = jSONObject.optInt("state_delay_enabled") > 0;
        boolean z2 = TTNetInit.getStateDelayNewStrategyEnabled() && (jSONObject.optInt("state_new_strategy_enabled", 1) > 0);
        if (!z || optJSONArray == null || !z2) {
            a("");
            return;
        }
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
            String optString = optJSONObject.optString("state_name", "");
            if (optJSONObject != null && !TextUtils.isEmpty(optString)) {
                b a2 = b.a(optJSONObject.optInt("type", b.NOT_REACHED.a()));
                a aVar = null;
                if (a2 == b.NORMAL_DELAY) {
                    aVar = new com.bytedance.ttnet.priority.b();
                } else if (a2 == b.RUNTIME_DELAY) {
                    aVar = new d();
                } else if (a2 == b.PRIORITY) {
                    aVar = new c();
                }
                if (aVar != null && aVar.a(optJSONObject)) {
                    if (Logger.debug()) {
                        Logger.d(f15431b, "mode name is " + optString);
                    }
                    concurrentHashMap.put(optString, aVar);
                }
            }
        }
        if (Logger.debug()) {
            Logger.d(f15431b, "mode size is " + concurrentHashMap.size());
        }
        this.f15433d.putAll(concurrentHashMap);
        this.f15432c.set(true);
    }

    public static Integer a(Map<String, Integer> map, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        for (String str2 : map.keySet()) {
            if (str.startsWith(str2)) {
                return map.get(str2);
            }
        }
        return null;
    }

    public static boolean a(Set<String> set, String str) {
        if (TextUtils.isEmpty(str) || set == null) {
            return false;
        }
        for (String str2 : set) {
            if (str.startsWith(str2)) {
                return true;
            }
        }
        return false;
    }

    public static Set<String> a(JSONObject jSONObject, String str) {
        JSONArray optJSONArray;
        CopyOnWriteArraySet copyOnWriteArraySet = new CopyOnWriteArraySet();
        if (jSONObject == null || (optJSONArray = jSONObject.optJSONArray(str)) == null) {
            return copyOnWriteArraySet;
        }
        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
            String optString = optJSONArray.optString(i2);
            if (!TextUtils.isEmpty(optString)) {
                copyOnWriteArraySet.add(optString);
            }
        }
        return copyOnWriteArraySet;
    }

    private boolean a(Request request) {
        return (request == null || TextUtils.isEmpty(request.getPath())) ? false : true;
    }
}
