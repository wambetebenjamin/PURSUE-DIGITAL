package org.chromium.wschannel;

import android.text.TextUtils;
import com.bytedance.common.utility.Logger;
import com.bytedance.common.wschannel.WsConstants;
import com.bytedance.frameworks.baselib.network.http.e;
import java.util.Map;
/* loaded from: classes10.dex */
public class DestroyTimeoutControl {
    private static final String DESTROY_TIME_OUT_MILLS = "destroy_timeout_millis";
    private static final String FALLBACK_TIMEOUT_MILLS = "fallback_timeout_mills";
    private static final int FALL_BACK_SECONDS = 10;
    private static final long MIN_TIMEOUT_MILLS = 1000;
    private static final String PP_ERROR_MSG = "\\\"internal_error\\\":67";
    private static final String PP_QUIC_ERROR_CODE = "\"error_code\":-356";
    private static final String PP_TLS_ERROR_CODE = "\"error_code\":-193";
    private static final String TAG = "CronetFrontierConnection";
    private static final String WS_ERROR_CODE = "\\\"net_error\\\":-7";
    private static final String WS_ERROR_MSG = "WebSocket opening handshake timed out";
    private static long sDestroyDeltaMillis = 100;
    private static volatile boolean sEnabled;
    private volatile boolean mFallbackToWebsocket;
    private long mPrivateProtocolTimeout;
    private long mWebsocketTimeout;

    public DestroyTimeoutControl(Map<String, Object> map, boolean z) {
        this.mFallbackToWebsocket = true;
        if (e.s() <= 0) {
            if (Logger.debug()) {
                Logger.d(TAG, "Destroy delta disable: " + e.s());
                return;
            }
            return;
        }
        sDestroyDeltaMillis = e.s();
        long configTimeoutMilliSeconds = getConfigTimeoutMilliSeconds(map, DESTROY_TIME_OUT_MILLS, 0L);
        if (configTimeoutMilliSeconds < 1000) {
            if (Logger.debug()) {
                Logger.d(TAG, "Destroy timeout disable: " + configTimeoutMilliSeconds);
                return;
            }
            return;
        }
        sEnabled = true;
        if (z) {
            this.mWebsocketTimeout = configTimeoutMilliSeconds - sDestroyDeltaMillis;
            if (Logger.debug()) {
                Logger.d(TAG, "Destroy ws timeout: " + this.mWebsocketTimeout);
                return;
            }
            return;
        }
        long configTimeoutMilliSeconds2 = getConfigTimeoutMilliSeconds(map, FALLBACK_TIMEOUT_MILLS, 10000L);
        if (Logger.debug()) {
            Logger.d(TAG, "fallback timeout:" + configTimeoutMilliSeconds2 + " destroy timeout:" + configTimeoutMilliSeconds);
        }
        if (configTimeoutMilliSeconds2 >= configTimeoutMilliSeconds) {
            this.mFallbackToWebsocket = false;
            if (Logger.debug()) {
                Logger.d(TAG, "Disable fallback to websocket, fallback >= destroy timeout");
            }
            this.mPrivateProtocolTimeout = configTimeoutMilliSeconds - sDestroyDeltaMillis;
            return;
        }
        long j2 = sDestroyDeltaMillis;
        this.mPrivateProtocolTimeout = configTimeoutMilliSeconds2 - j2;
        long j3 = (configTimeoutMilliSeconds - configTimeoutMilliSeconds2) - j2;
        this.mWebsocketTimeout = j3;
        if (j3 < 0) {
            if (Logger.debug()) {
                Logger.d(TAG, "Disable fallback to websocket, timeout <= 0:" + this.mWebsocketTimeout);
            }
            this.mFallbackToWebsocket = false;
        }
    }

    public boolean shouldFallbackToWebSocket() {
        if (sEnabled) {
            return this.mFallbackToWebsocket;
        }
        return true;
    }

    public long getPrivateProtocolTimeout() {
        if (sEnabled) {
            return this.mPrivateProtocolTimeout;
        }
        return 0L;
    }

    public long getWebsocketTimeout() {
        if (sEnabled) {
            return this.mWebsocketTimeout;
        }
        return 0L;
    }

    private static long getConfigTimeoutMilliSeconds(Map<String, Object> map, String str, long j2) {
        Map map2 = (Map) map.get(WsConstants.KEY_HEADERS);
        if (map2 == null || map2.isEmpty() || !map2.containsKey(str)) {
            return j2;
        }
        String str2 = (String) map2.get(str);
        if (TextUtils.isEmpty(str2)) {
            return j2;
        }
        try {
            int intValue = Integer.valueOf(str2).intValue();
            return intValue > 0 ? intValue : j2;
        } catch (Exception unused) {
            return j2;
        }
    }

    public static boolean isConnectionTimeout(String str, boolean z) {
        if (!sEnabled || TextUtils.isEmpty(str)) {
            return false;
        }
        return z ? str.contains(WS_ERROR_CODE) && str.contains(WS_ERROR_MSG) : str.contains(PP_TLS_ERROR_CODE) || (str.contains(PP_QUIC_ERROR_CODE) && str.contains(PP_ERROR_MSG));
    }
}
