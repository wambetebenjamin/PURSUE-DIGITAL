package com.bytedance.ttnet;

import android.os.Handler;
import android.os.Looper;
import com.bytedance.ttnet.utils.TTNetProtocolUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
/* loaded from: classes8.dex */
public class TTWiFiCellSwitcher {
    private static final String ACTION_NAME = "wifi-to-cell";
    private static final String ACTION_PATH_START = "/start";
    private static final String ACTION_PATH_STOP = "/stop";
    private static final String ACTION_PATH_TRIGGER = "/trigger-switch";
    private static final String TAG = "TTWiFiCellSwitchManager";
    private volatile d mCurrentState = d.STOPPED;
    private final Map<b, Handler> mObservers = new HashMap();

    /* loaded from: classes8.dex */
    public interface b {
        void a(d dVar, d dVar2);
    }

    /* loaded from: classes8.dex */
    public enum d {
        STOPPED(-1),
        NO_NETWORK(0),
        DEFAULT_CELLULAR(1),
        DEFAULT_WIFI_WITH_CELLULAR_DOWN(2),
        DEFAULT_WIFI_WITH_CELLULAR_UP(3),
        WAIT_CELLULAR_ALWAYS_UP(4),
        WAIT_USER_ALLOW_TO_CELLULAR(5),
        WIFI_WITH_CELLULAR_TRANS_DATA(6),
        EVALUATE_CELLULAR(7),
        WAIT_USER_ALLOW_TO_WIFI(8);
        
        final int k;

        d(int i2) {
            this.k = i2;
        }

        int a() {
            return this.k;
        }

        static d a(int i2) {
            return values()[i2 - STOPPED.a()];
        }
    }

    public d getCurrentState() {
        return this.mCurrentState;
    }

    /* loaded from: classes8.dex */
    public enum a {
        WIFI(0),
        CELL(1);
        

        /* renamed from: c  reason: collision with root package name */
        final int f15012c;

        a(int i2) {
            this.f15012c = i2;
        }

        int a() {
            return this.f15012c;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes8.dex */
    public static final class c {

        /* renamed from: a  reason: collision with root package name */
        static final TTWiFiCellSwitcher f15013a = new TTWiFiCellSwitcher();

        private c() {
        }
    }

    public static TTWiFiCellSwitcher inst() {
        return c.f15013a;
    }

    public void registerObserver(b bVar, Handler handler) {
        Objects.requireNonNull(bVar, "observer == null");
        synchronized (this) {
            if (!this.mObservers.containsKey(bVar)) {
                if (handler == null) {
                    this.mObservers.put(bVar, new Handler(Looper.getMainLooper()));
                } else {
                    this.mObservers.put(bVar, handler);
                }
            }
        }
    }

    public void unregisterObserver(b bVar) {
        Objects.requireNonNull(bVar, "observer == null");
        synchronized (this) {
            this.mObservers.remove(bVar);
        }
    }

    public void start() {
        try {
            TTNetProtocolUtil.a(ACTION_NAME, ACTION_PATH_START, null, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void stop() {
        try {
            TTNetProtocolUtil.a(ACTION_NAME, ACTION_PATH_STOP, null, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void triggerSwitchingToNetwork(a aVar) {
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("network-type", Integer.toString(aVar.a()));
            TTNetProtocolUtil.a(ACTION_NAME, ACTION_PATH_TRIGGER, hashMap, null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static void notifyWiFiToCellState(int i2, int i3) {
        inst().handleWiFiToCellState(i2, i3);
    }

    private void handleWiFiToCellState(final int i2, final int i3) {
        this.mCurrentState = d.a(i3);
        synchronized (this) {
            for (Map.Entry<b, Handler> entry : this.mObservers.entrySet()) {
                final b key = entry.getKey();
                Handler value = entry.getValue();
                if (value != null) {
                    value.post(new Runnable() { // from class: com.bytedance.ttnet.TTWiFiCellSwitcher.1
                        @Override // java.lang.Runnable
                        public void run() {
                            key.a(d.a(i2), d.a(i3));
                        }
                    });
                }
            }
        }
    }
}
