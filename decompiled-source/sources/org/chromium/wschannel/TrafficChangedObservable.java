package org.chromium.wschannel;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
/* loaded from: classes10.dex */
public class TrafficChangedObservable extends Observable {
    public static final String TRAFFICCHANGED_IS_HEARTBEAT_FRAME = "is_heartbeat_frame";
    public static final String TRAFFICCHANGED_RECEIVEDBYTES = "received_bytes";
    public static final String TRAFFICCHANGED_SENTBYTES = "sent_bytes";
    public static final String TRAFFICCHANGED_URL = "url";
    private static volatile TrafficChangedObservable sInstance;

    public static TrafficChangedObservable inst() {
        if (sInstance == null) {
            synchronized (TrafficChangedObservable.class) {
                if (sInstance == null) {
                    sInstance = new TrafficChangedObservable();
                }
            }
        }
        return sInstance;
    }

    public void onTrafficChanged(String str, long j2, long j3, boolean z) {
        HashMap hashMap = new HashMap();
        hashMap.put("url", str);
        hashMap.put("sent_bytes", Long.valueOf(j2));
        hashMap.put("received_bytes", Long.valueOf(j3));
        hashMap.put(TRAFFICCHANGED_IS_HEARTBEAT_FRAME, Boolean.valueOf(z));
        notifyTrafficChangedObservers(hashMap);
    }

    private void notifyTrafficChangedObservers(Map<String, Object> map) {
        setChanged();
        notifyObservers(map);
    }
}
