package com.bytedance.ttnet.diagnosis;

import com.bytedance.frameworks.baselib.network.http.cronet.impl.g;
import com.bytedance.ttnet.TTNetInit;
import com.bytedance.ttnet.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes8.dex */
public class TTNetDiagnosisService {
    public static final int ACCELERATE_TARGET = 2;
    public static final int ACTION_FORCE_CELLULAR = 1;
    public static final int ACTION_FORCE_DEFAULT = 3;
    public static final int ACTION_FORCE_WIFI = 2;
    public static final int ACTION_UNSPECIFIED = 0;
    public static final int DIAGNOSE_TARGET = 3;
    public static final int DIAGNOSE_V2_TARGET = 4;
    public static final int DNS_RESOLVE_TARGET = 0;
    public static final int NET_DETECT_ALL = -1;
    public static final int NET_DETECT_FULL_DNS = 128;
    public static final int NET_DETECT_HTTP_GET = 1;
    public static final int NET_DETECT_HTTP_ISP = 4096;
    public static final int NET_DETECT_ICMP_PING = 2;
    public static final int NET_DETECT_LOCAL_DNS = 8;
    public static final int NET_DETECT_TCP_CONNECT = 256;
    public static final int NET_DETECT_TCP_ECHO = 512;
    public static final int NET_DETECT_TCP_PERF = 2048;
    public static final int NET_DETECT_TRACEROUTE = 4;
    public static final int NET_DETECT_UDP_PERF = 1024;
    public static final int NET_DETECT_UDP_PING = 64;
    public static final int RACE_TARGETS = 1;
    public static final int RAW_DETECT_TARGETS = 5;
    private static final Map<Integer, String> sTypeStringMap;

    static {
        HashMap hashMap = new HashMap();
        sTypeStringMap = hashMap;
        hashMap.put(0, "DNS_RESOLVE_TARGET");
        hashMap.put(1, "RACE_TARGETS");
        hashMap.put(2, "ACCELERATE_TARGET");
        hashMap.put(3, "DIAGNOSE_TARGET");
        hashMap.put(4, "DIAGNOSE_V2_TARGET");
        hashMap.put(5, "RAW_DETECT_TARGETS");
    }

    public static IDiagnosisRequest createRequest(int i2, String str, int i3, int i4) {
        return createRequest(i2, str, i3, 0, i4);
    }

    public static IDiagnosisRequest createRequest(int i2, String str, int i3, int i4, int i5) {
        if (!isOneTargetRequestType(i2)) {
            throw new IllegalArgumentException("Type " + requestTypeToString(i2) + " of request should be created with multiple targets.");
        }
        if (isValidNetDetect(i2, i3)) {
            if (i2 == 0 || i2 == 2 || i2 == 3 || i2 == 4) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(str);
                return new a(i2, arrayList, i3, i4, i5);
            }
            return null;
        }
        throw new IllegalArgumentException("Illegal netDetectType: " + i3);
    }

    public static IDiagnosisRequest createRequest(int i2, List<String> list, int i3, int i4) {
        if (isOneTargetRequestType(i2)) {
            throw new IllegalArgumentException("Type " + requestTypeToString(i2) + " of request should be created with only one target.");
        }
        if (isValidNetDetect(i2, i3)) {
            if (i2 == 1 || i2 == 5) {
                return new a(i2, list, i3, 0, i4);
            }
            return null;
        }
        throw new IllegalArgumentException("Illegal netDetectType: " + i3);
    }

    public static void reportUserLog(String str) {
        if (str == null || str.isEmpty()) {
            return;
        }
        try {
            g cronetHttpClient = getCronetHttpClient();
            if (cronetHttpClient != null) {
                cronetHttpClient.j(str);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private static boolean isOneTargetRequestType(int i2) {
        if (i2 != 0) {
            if (i2 == 1) {
                return false;
            }
            if (i2 != 2 && i2 != 3 && i2 != 4) {
                if (i2 == 5) {
                    return false;
                }
                throw new IllegalArgumentException("Illegal request type: " + i2);
            }
        }
        return true;
    }

    private static String requestTypeToString(int i2) {
        return sTypeStringMap.get(Integer.valueOf(i2));
    }

    private static boolean isValidNetDetect(int i2, long j2) {
        if (i2 != 0) {
            if (i2 != 1) {
                if (i2 == 2) {
                    return j2 == 1 || j2 == 2 || j2 == 64;
                } else if (i2 == 3 || i2 == 4) {
                    return true;
                } else {
                    if (i2 != 5) {
                        throw new IllegalArgumentException("Illegal request type: " + i2);
                    }
                }
            }
            return ((-1) & j2) != 0 && (j2 & 0) == 0;
        }
        return true;
    }

    private static g getCronetHttpClient() {
        if (d.b()) {
            return g.a(TTNetInit.getTTNetDepend().getContext());
        }
        throw new UnsupportedOperationException("Cronet is not enabled");
    }
}
