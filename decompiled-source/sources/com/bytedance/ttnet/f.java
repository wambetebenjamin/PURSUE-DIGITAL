package com.bytedance.ttnet;

import android.os.Handler;
import android.os.Looper;
import com.bytedance.common.utility.Logger;
import com.bytedance.retrofit2.Call;
import com.bytedance.retrofit2.Callback;
import com.bytedance.retrofit2.SsResponse;
import com.bytedance.retrofit2.client.Header;
import com.bytedance.retrofit2.mime.TypedInput;
import com.bytedance.ttnet.utils.TTNetProtocolUtil;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
/* compiled from: TTMultiNetwork.java */
/* loaded from: classes8.dex */
public class f {
    private static final String HEADER_ACTIVE_RESULT = "ActiveResult";
    private static final String HOST_MULTINET = "multinet";
    private static final String PATH_ACTIVATE_CELL = "/activatecell";
    private static final String k = "TTMultiNetwork";
    private static volatile c sCurrentState = c.STOPPED;
    private static volatile a sActiveResult = a.DEFAULT;
    private static Map<b, Handler> sObservers = new HashMap();

    /* compiled from: TTMultiNetwork.java */
    /* loaded from: classes8.dex */
    public interface b {
        void a(c cVar, c cVar2, long j2);

        void a(boolean z, long j2);
    }

    /* compiled from: TTMultiNetwork.java */
    /* loaded from: classes8.dex */
    public enum c {
        STOPPED(-1),
        NO_NETWORK(0),
        DEFAULT_CELLULAR_WITH_WIFI_DOWN(1),
        DEFAULT_WIFI_WITH_CELLULAR_DOWN(2),
        DEFAULT_WIFI_WITH_CELLULAR_UP(3),
        WAIT_CELLULAR_ALWAYS_UP(4),
        DEFAULT_VPN(5);
        

        /* renamed from: h  reason: collision with root package name */
        final int f15296h;

        c(int i2) {
            this.f15296h = i2;
        }

        int a() {
            return this.f15296h;
        }

        static c a(int i2) {
            return values()[i2 - STOPPED.a()];
        }
    }

    /* compiled from: TTMultiNetwork.java */
    /* loaded from: classes8.dex */
    public enum a {
        DEFAULT(0),
        SUCCESS(1),
        FAILED(2),
        DEFERRED_UNSUITABLE_NETWORK(3),
        ACTIVATION_IN_PROGRESS(4);
        

        /* renamed from: f  reason: collision with root package name */
        private final int f15287f;

        a(int i2) {
            this.f15287f = i2;
        }

        int a() {
            return this.f15287f;
        }

        static a a(String str) {
            try {
                return values()[Integer.parseInt(str) - DEFAULT.a()];
            } catch (NumberFormatException unused) {
                return DEFAULT;
            }
        }

        public int b() {
            return this.f15287f;
        }
    }

    public static c getCurrentState() {
        return sCurrentState;
    }

    public static a getActiveResult() {
        return sActiveResult;
    }

    public static void registerMultiNetObserver(b bVar, Handler handler) {
        Objects.requireNonNull(bVar, "observer == null");
        synchronized (f.class) {
            if (!sObservers.containsKey(bVar)) {
                if (handler == null) {
                    sObservers.put(bVar, new Handler(Looper.getMainLooper()));
                } else {
                    sObservers.put(bVar, handler);
                }
            }
        }
    }

    public static void unregisterMultiNetObserver(b bVar) {
        Objects.requireNonNull(bVar, "observer == null");
        synchronized (f.class) {
            sObservers.remove(bVar);
        }
    }

    public static void tryActivateAlternativeNet() {
        try {
            TTNetProtocolUtil.a(HOST_MULTINET, PATH_ACTIVATE_CELL, null, null, new Callback<TypedInput>() { // from class: com.bytedance.ttnet.f.1
                @Override // com.bytedance.retrofit2.Callback
                public void onResponse(Call<TypedInput> call, SsResponse<TypedInput> ssResponse) {
                    try {
                        String str = null;
                        Iterator<Header> it = ssResponse.headers().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            Header next = it.next();
                            if (f.HEADER_ACTIVE_RESULT.equalsIgnoreCase(next.getName())) {
                                str = next.getValue();
                                break;
                            }
                        }
                        a unused = f.sActiveResult = a.a(str);
                        f.notifyUserSpecifiedNetworkEnabled("1".equals(str));
                    } finally {
                        try {
                        } finally {
                        }
                    }
                }

                @Override // com.bytedance.retrofit2.Callback
                public void onFailure(Call<TypedInput> call, Throwable th) {
                    f.notifyUserSpecifiedNetworkEnabled(false);
                    th.printStackTrace();
                    call.cancel();
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void notifyMultiNetworkState(final int i2, final int i3) {
        if (Logger.debug()) {
            Logger.d(k, "notifyMultiNetworkState, prev: " + i2 + ", curr: " + i3);
        }
        sCurrentState = c.a(i3);
        final long currentTimeMillis = System.currentTimeMillis();
        synchronized (f.class) {
            for (Map.Entry<b, Handler> entry : sObservers.entrySet()) {
                final b key = entry.getKey();
                Handler value = entry.getValue();
                if (value != null) {
                    value.post(new Runnable() { // from class: com.bytedance.ttnet.f.2
                        @Override // java.lang.Runnable
                        public void run() {
                            b.this.a(c.a(i2), c.a(i3), currentTimeMillis);
                        }
                    });
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void notifyUserSpecifiedNetworkEnabled(final boolean z) {
        final long currentTimeMillis = System.currentTimeMillis();
        synchronized (f.class) {
            for (Map.Entry<b, Handler> entry : sObservers.entrySet()) {
                final b key = entry.getKey();
                Handler value = entry.getValue();
                if (value != null) {
                    value.post(new Runnable() { // from class: com.bytedance.ttnet.f.3
                        @Override // java.lang.Runnable
                        public void run() {
                            b.this.a(z, currentTimeMillis);
                        }
                    });
                }
            }
        }
    }
}
