package com.bytedance.applog.log;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: EventBus.java */
/* loaded from: classes.dex */
public final class f extends Thread implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    public static com.bytedance.applog.log.a<f> f2737a = new com.bytedance.applog.log.a<f>() { // from class: com.bytedance.applog.log.f.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.bytedance.applog.log.a
        /* renamed from: a */
        public f create(Object... objArr) {
            return new f();
        }
    };

    /* renamed from: b  reason: collision with root package name */
    private static final int f2738b = 1;

    /* renamed from: c  reason: collision with root package name */
    private final Map<String, List<c>> f2739c = new ConcurrentHashMap();

    /* renamed from: d  reason: collision with root package name */
    private int f2740d = 1000;

    /* renamed from: e  reason: collision with root package name */
    private final LinkedList<b> f2741e = new LinkedList<>();

    /* renamed from: f  reason: collision with root package name */
    private Handler f2742f;

    /* compiled from: EventBus.java */
    /* loaded from: classes.dex */
    public interface a {
        Object fetch();
    }

    /* compiled from: EventBus.java */
    /* loaded from: classes.dex */
    public interface c {
        void a(Object obj);
    }

    public f() {
        start();
    }

    public void a(int i2) {
        this.f2740d = i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: EventBus.java */
    /* loaded from: classes.dex */
    public static class b {

        /* renamed from: a  reason: collision with root package name */
        String f2743a;

        /* renamed from: b  reason: collision with root package name */
        Object f2744b;

        b(String str, Object obj) {
            this.f2743a = str;
            this.f2744b = obj;
        }
    }

    public void a(String str, Object obj) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        b bVar = new b(str, obj);
        if (!this.f2739c.containsKey(str)) {
            c(bVar);
        } else {
            a(bVar);
        }
    }

    public void a(String str, a aVar) {
        if (TextUtils.isEmpty(str) || aVar == null) {
            return;
        }
        b bVar = new b(str, aVar.fetch());
        if (!this.f2739c.containsKey(str)) {
            c(bVar);
        } else {
            a(bVar);
        }
    }

    public synchronized void a(String str, c cVar) {
        List<c> list = this.f2739c.get(str);
        if (list == null) {
            list = new ArrayList<>();
        }
        list.add(cVar);
        this.f2739c.put(str, list);
        a();
    }

    public synchronized void b(String str, c cVar) {
        List<c> list = this.f2739c.get(str);
        if (list != null && list.contains(cVar)) {
            list.remove(cVar);
            if (list.size() == 0) {
                this.f2739c.remove(str);
            } else {
                this.f2739c.put(str, list);
            }
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() {
        super.run();
        Looper.prepare();
        this.f2742f = new Handler(this);
        Looper.loop();
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what == 1) {
            b((b) message.obj);
        }
        return true;
    }

    private void a(b bVar) {
        Handler handler = this.f2742f;
        if (handler != null) {
            handler.sendMessage(handler.obtainMessage(1, bVar));
        } else {
            b(bVar);
        }
    }

    private void b(b bVar) {
        List<c> list = this.f2739c.get(bVar.f2743a);
        if (list == null) {
            return;
        }
        HashSet hashSet = new HashSet();
        byte[] bArr = null;
        try {
            if ("applog_event_upload_eid".equals(bVar.f2743a) && (bVar.f2744b instanceof JSONObject) && ((JSONObject) bVar.f2744b).has("$$EVENT_LOCAL_IDS")) {
                Object opt = ((JSONObject) bVar.f2744b).opt("$$EVENT_LOCAL_IDS");
                if (opt instanceof Collection) {
                    hashSet.addAll((Collection) opt);
                }
            } else if ("applog_do_request_end".equals(bVar.f2743a) && (bVar.f2744b instanceof JSONObject) && ((JSONObject) bVar.f2744b).has("responseByte")) {
                Object opt2 = ((JSONObject) bVar.f2744b).opt("responseByte");
                if (opt2 instanceof byte[]) {
                    bArr = (byte[]) ((byte[]) opt2).clone();
                }
            }
            for (c cVar : list) {
                if (bVar.f2744b instanceof JSONObject) {
                    try {
                        JSONObject jSONObject = new JSONObject(bVar.f2744b.toString());
                        if (!hashSet.isEmpty()) {
                            jSONObject.put("$$EVENT_LOCAL_IDS", hashSet);
                        }
                        if (bArr != null) {
                            jSONObject.put("responseByte", bArr);
                        }
                        cVar.a(jSONObject);
                    } catch (JSONException unused) {
                        cVar.a(bVar.f2744b);
                    }
                } else {
                    cVar.a(bVar.f2744b);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void c(b bVar) {
        synchronized (this.f2741e) {
            if (this.f2741e.size() >= this.f2740d) {
                this.f2741e.poll();
            }
            this.f2741e.add(bVar);
        }
    }

    private void a() {
        synchronized (this.f2741e) {
            ArrayList<b> arrayList = new ArrayList(this.f2741e);
            this.f2741e.clear();
            for (b bVar : arrayList) {
                a(bVar);
            }
        }
    }
}
