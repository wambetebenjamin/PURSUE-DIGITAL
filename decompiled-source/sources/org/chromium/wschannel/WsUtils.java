package org.chromium.wschannel;

import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.utility.reflect.Reflect;
import com.bytedance.common.wschannel.model.i;
import com.bytedance.frameworks.baselib.network.http.a;
import com.bytedance.frameworks.baselib.network.http.util.p;
import com.bytedance.librarian.c;
import java.net.CookieHandler;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/* loaded from: classes10.dex */
public class WsUtils {
    public static final String METHOD_ID_HEADER = "method_id";
    private static final String MSG_ID_HEADER = "msg_id";
    private static final String MSG_SERVER_TIMING_HEADER = "server_timing";
    public static final String PAYLOAD_ENCODING_HEADER = "payload_encoding";
    public static final String PAYLOAD_TYPE_HEADER = "payload_type";
    private static final String TAG = "TTFrontierClient";
    private static final String TTNET_INIT_CLASS = "com.bytedance.ttnet.TTNetInit";

    public static int cronetToWsStateAdapter(int i2) {
        if (i2 != -1) {
            if (i2 != 0) {
                if (i2 != 1) {
                    return i2;
                }
                return 6;
            }
            return 1;
        }
        return 0;
    }

    public static URI parsePrivateProtocolUrl(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            if (str.startsWith("wss:")) {
                str = "https:" + str.substring(4);
            } else if (str.startsWith("ws:")) {
                str = "http:" + str.substring(3);
            }
            return p.a(str);
        } catch (Throwable unused) {
            Logger.e(TAG, "parsePrivateProtocolUrl failed, url=" + str);
            return null;
        }
    }

    public static String getCookieHeader(URI uri) {
        if (uri == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        StringBuilder sb = new StringBuilder();
        try {
            CookieHandler cookieHandler = CookieHandler.getDefault();
            Map<String, List<String>> map = cookieHandler != null ? cookieHandler.get(uri, hashMap) : null;
            if (map != null && !map.isEmpty()) {
                for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                    if (a.f7602g.equalsIgnoreCase(entry.getKey()) && !entry.getValue().isEmpty()) {
                        int i2 = 0;
                        for (String str : entry.getValue()) {
                            if (i2 > 0) {
                                sb.append("; ");
                            }
                            sb.append(str);
                            i2++;
                        }
                    }
                }
            }
            return sb.toString();
        } catch (Exception unused) {
            return null;
        }
    }

    public static String[] constructMessageMetaInfo(i iVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(METHOD_ID_HEADER);
        arrayList.add(String.valueOf(iVar.l()));
        if (!TextUtils.isEmpty(iVar.p())) {
            arrayList.add(PAYLOAD_TYPE_HEADER);
            arrayList.add(iVar.p());
        }
        if (!TextUtils.isEmpty(iVar.o())) {
            arrayList.add(PAYLOAD_ENCODING_HEADER);
            arrayList.add(iVar.o());
        }
        if (iVar.m() == null) {
            return (String[]) arrayList.toArray(new String[0]);
        }
        for (i.b bVar : iVar.m()) {
            if (!TextUtils.isEmpty(bVar.a()) && !TextUtils.isEmpty(bVar.b())) {
                arrayList.add(bVar.a());
                arrayList.add(bVar.b());
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static i constructWsChannelMsg(int i2, Map<String, String> map, byte[] bArr) {
        i iVar = new i();
        iVar.a(i2);
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!TextUtils.isEmpty(entry.getKey()) && !TextUtils.isEmpty(entry.getValue())) {
                if (entry.getKey().equalsIgnoreCase(METHOD_ID_HEADER)) {
                    try {
                        iVar.b(Integer.valueOf(entry.getValue()).intValue());
                    } catch (NumberFormatException e2) {
                        e2.printStackTrace();
                    }
                } else if (entry.getKey().equalsIgnoreCase(PAYLOAD_ENCODING_HEADER)) {
                    iVar.e(entry.getValue());
                } else if (entry.getKey().equalsIgnoreCase(PAYLOAD_TYPE_HEADER)) {
                    iVar.f(entry.getValue());
                } else if (entry.getKey().equalsIgnoreCase("msg_id")) {
                    iVar.b(entry.getValue());
                } else if (entry.getKey().equalsIgnoreCase(MSG_SERVER_TIMING_HEADER)) {
                    iVar.c(entry.getValue());
                } else {
                    i.b bVar = new i.b();
                    bVar.a(entry.getKey());
                    bVar.b(entry.getValue());
                    arrayList.add(bVar);
                }
            }
        }
        iVar.a(arrayList);
        iVar.a(bArr);
        if (iVar.o() == null) {
            iVar.e("");
        }
        if (iVar.p() == null) {
            iVar.f("");
        }
        return iVar;
    }

    public static void loadCronetKernel() {
        try {
            Object newInstance = Class.forName(TTNET_INIT_CLASS).newInstance();
            Reflect.on(newInstance).call("getInitCompletedLatch");
            Reflect.on(newInstance).call("preInitCronetKernel");
        } catch (Throwable th) {
            th.printStackTrace();
            if (Logger.debug()) {
                Logger.d(TAG, "TTNet init failed, cronet engine is null.");
            }
        }
    }

    public static boolean isFrontierDomainEnabled(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            URI uri = new URI(str);
            if (c.a.SEPARATOR.equals(uri.getPath())) {
                return false;
            }
            return com.bytedance.frameworks.baselib.network.http.i.b(uri.getHost());
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
