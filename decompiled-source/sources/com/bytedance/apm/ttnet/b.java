package com.bytedance.apm.ttnet;

import android.text.TextUtils;
import com.bytedance.apm.constant.p;
import com.bytedance.frameworks.baselib.network.http.exception.c;
import com.bytedance.frameworks.baselib.network.http.util.f;
import com.bytedance.geckox.b;
import com.vk.api.sdk.exceptions.VKApiCodes;
import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLProtocolException;
import org.json.JSONObject;
/* compiled from: TTNetUtils.java */
/* loaded from: classes.dex */
public final class b {
    public static final String TAG = "TTNetUtils";

    /* renamed from: a  reason: collision with root package name */
    private static Map<String, Map<String, Integer>> f1636a = new HashMap();

    private static void a() {
        if (f1636a.isEmpty()) {
            HashMap hashMap = new HashMap();
            hashMap.put(" Bad Request", 101);
            hashMap.put(" Connection timed out", 102);
            hashMap.put(" Forbidden", 103);
            hashMap.put(" Gateway Time-out", 104);
            hashMap.put(" Internal Server Error", 105);
            hashMap.put(" Not Found", 106);
            hashMap.put(" Request Time-out", 107);
            hashMap.put(" Request-URI Too Large", 108);
            f1636a.put("c", hashMap);
            HashMap hashMap2 = new HashMap();
            hashMap2.put("ERR_ABORTED", 301);
            hashMap2.put("ERR_ADDRESS_UNREACHABLE", 302);
            hashMap2.put("ERR_CERT_AUTHORITY_INVALID", 303);
            hashMap2.put("ERR_CERT_COMMON_NAME_INVALID", 304);
            hashMap2.put("ERR_CERT_DATE_INVALID", Integer.valueOf((int) f.V));
            hashMap2.put("ERR_CONNECTION_ABORTED", 306);
            hashMap2.put("ERR_CONNECTION_CLOSED", 307);
            hashMap2.put("ERR_CONNECTION_REFUSED", 308);
            hashMap2.put("ERR_CONNECTION_RESET", 309);
            hashMap2.put("ERR_CONNECTION_TIMED_OUT", 310);
            hashMap2.put("ERR_EMPTY_RESPONSE", 311);
            hashMap2.put("ERR_HTTP2_PING_FAILED", 312);
            hashMap2.put("ERR_INCOMPLETE_CHUNKED_ENCODING", 313);
            hashMap2.put("ERR_INTERNET_DISCONNECTED", 314);
            hashMap2.put("ERR_NAME_NOT_RESOLVED", 315);
            hashMap2.put("ERR_NETWORK_ACCESS_DENIED", 316);
            hashMap2.put("ERR_PROXY_CONNECTION_FAILED", 317);
            hashMap2.put("ERR_SOCKET_NOT_CONNECTED", 318);
            hashMap2.put("ERR_SSL_BAD_RECORD_MAC_ALERT", 319);
            hashMap2.put("ERR_SSL_PROTOCOL_ERROR", 320);
            hashMap2.put("ERR_TIMED_OUT", 321);
            hashMap2.put("ERR_TTNET_APP_TIMED_OUT", 322);
            hashMap2.put("ERR_TTNET_APP_UPLOAD_EXCEPTION", 323);
            hashMap2.put("ERR_TUNNEL_CONNECTION_FAILED", 324);
            f1636a.put("NetworkExceptionImpl", hashMap2);
            HashMap hashMap3 = new HashMap();
            hashMap3.put("ERR_NETWORK_CHANGED", 401);
            f1636a.put("QuicExceptionImpl", hashMap3);
            HashMap hashMap4 = new HashMap();
            hashMap4.put(" Unexpected end of ZLIB input stream", 501);
            f1636a.put(EOFException.class.getSimpleName(), hashMap4);
            HashMap hashMap5 = new HashMap();
            hashMap5.put(" unexpected end of stream on Connection", Integer.valueOf((int) b.InterfaceC0127b.f8891b));
            hashMap5.put(" Unexpected request usage", 602);
            f1636a.put(IOException.class.getSimpleName(), hashMap5);
            HashMap hashMap6 = new HashMap();
            hashMap6.put("Cronet internal request fail", 701);
            f1636a.put(Exception.class.getSimpleName(), hashMap6);
            HashMap hashMap7 = new HashMap();
            hashMap7.put(" Content received is less than Content-Length", Integer.valueOf((int) VKApiCodes.CODE_ERROR_VIDEO_COMMENTS_CLOSED));
            hashMap7.put(" expected bytes but received", 802);
            hashMap7.put(" unexpected end of stream", 803);
            f1636a.put(ProtocolException.class.getSimpleName(), hashMap7);
            HashMap hashMap8 = new HashMap();
            hashMap8.put(" Connection closed by peer", Integer.valueOf((int) VKApiCodes.CODE_MSG_SEND_RECIPIENT_FORBID_GROUPS_MSGS));
            hashMap8.put(" Read error", Integer.valueOf((int) VKApiCodes.CODE_MSG_SEND_VIOLATION_OF_PRIVACY_SETTINGS));
            hashMap8.put(" SSL handshake aborted", 903);
            f1636a.put(SSLException.class.getSimpleName(), hashMap8);
            HashMap hashMap9 = new HashMap();
            hashMap9.put(" Connection closed by peer", 1001);
            hashMap9.put(" Handshake failed", 1002);
            hashMap9.put(" SSL handshake aborted", 1003);
            f1636a.put(SSLHandshakeException.class.getSimpleName(), hashMap9);
            HashMap hashMap10 = new HashMap();
            hashMap10.put(" Hostname not verified", Integer.valueOf((int) com.bytedance.sdk.account.api.b.CAPTCHA_MISSING));
            f1636a.put(SSLPeerUnverifiedException.class.getSimpleName(), hashMap10);
            HashMap hashMap11 = new HashMap();
            hashMap11.put(" Read error", Integer.valueOf((int) com.bytedance.sdk.account.api.b.SMS_CODE_MISSING));
            f1636a.put(SSLProtocolException.class.getSimpleName(), hashMap11);
        }
    }

    public static void updateNetAllExtraInfo(com.bytedance.ttnet.http.b bVar, JSONObject jSONObject) {
        a(bVar, jSONObject);
    }

    public static void updateNetErrorExtraInfo(com.bytedance.ttnet.http.b bVar, Throwable th, JSONObject jSONObject) {
        if (jSONObject != null && th != null) {
            try {
                jSONObject.put(p.EXT_SUBSTATUS, getSubStatus(th));
            } catch (Throwable unused) {
            }
        }
        a(bVar, jSONObject);
    }

    private static void a(com.bytedance.ttnet.http.b bVar, JSONObject jSONObject) {
        if (jSONObject == null || bVar == null) {
            return;
        }
        try {
            jSONObject.put(p.EXT_REQUEST_HEADER, bVar.I);
            jSONObject.put(p.EXT_RESPONSE_HEADER, bVar.f7603J);
        } catch (Throwable unused) {
        }
    }

    public static int getSubStatus(Throwable th) {
        int i2 = -1;
        if (th == null) {
            return -1;
        }
        a();
        String message = th.getMessage();
        if (TextUtils.isEmpty(message)) {
            return -1;
        }
        int indexOf = message.indexOf(44);
        if (indexOf > 0) {
            message = message.substring(0, indexOf);
        }
        Map<String, Integer> map = f1636a.get(th.getClass().getSimpleName());
        if (map != null && !map.isEmpty()) {
            Iterator<Map.Entry<String, Integer>> it = map.entrySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Map.Entry<String, Integer> next = it.next();
                if (message.contains(next.getKey())) {
                    Integer value = next.getValue();
                    if (value != null) {
                        i2 = value.intValue();
                    }
                }
            }
        }
        if (i2 < 0) {
            if (th instanceof c) {
                return 199;
            }
            if (th instanceof EOFException) {
                return 599;
            }
            if (th instanceof IOException) {
                return 699;
            }
            return i2;
        }
        return i2;
    }
}
