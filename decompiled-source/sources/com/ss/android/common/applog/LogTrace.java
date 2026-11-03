package com.ss.android.common.applog;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.ss.android.common.util.b;
import java.util.List;
/* loaded from: classes9.dex */
public class LogTrace {
    private static final String TAG = "LogTrace";
    public static final int TRACE_LOG_DATA_STATE_CHANGE = 4;
    public static final int TRACE_LOG_DISCARD = 1;
    public static final int TRACE_LOG_EVENT_SIZE_OVERFLOW = 5;
    public static final int TRACE_LOG_EXPIRED = 3;
    public static final int TRACE_LOG_INSERT_RESULT = 2;
    public static final int TRACE_LOG_PACK_SIZE_OVERFLOW = 6;
    public static final int TRACE_LOG_REQUEST_RESULT = 0;
    private static volatile LogTraceHandler sHandler;
    private static volatile LogRequestTraceCallback sTraceCallback;

    /* loaded from: classes9.dex */
    public interface LogRequestTraceCallback {
        public static final int CATE_OR_TAG_IS_EMPTY = 0;
        public static final int DROP_EVENT_DUE_TO_HIT_BLOCK_LIST = 2;
        public static final int DROP_EVENT_DUE_TO_NOT_INIT_OK = 3;
        public static final int DROP_EVENT_DUE_TO_NO_INSTANCE_AND_CACHE_FULL = 1;
        public static final int DROP_EVENT_DUE_TO_NO_SESSION = 4;

        void onEventDiscard(int i2);

        void onEventExpired(List<Long> list);

        void onEventInsertResult(boolean z, long j2);

        void onEventSizeOverflow(String str, String str2, String str3, int i2);

        void onLogDataStateChange(String str, String str2, String str3, boolean z, String str4);

        void onLogRequestResult(boolean z, List<Long> list);

        void onPackSizeOverflow(String str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class LogTraceHandler extends Handler {
        public LogTraceHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                LogRequestTraceCallback logRequestTraceCallback = LogTrace.sTraceCallback;
                switch (message.what) {
                    case 0:
                        if (logRequestTraceCallback != null) {
                            Object[] objArr = (Object[]) message.obj;
                            logRequestTraceCallback.onLogRequestResult(((Boolean) objArr[0]).booleanValue(), (List) objArr[1]);
                            return;
                        }
                        return;
                    case 1:
                        if (logRequestTraceCallback != null) {
                            logRequestTraceCallback.onEventDiscard(message.arg1);
                            return;
                        }
                        return;
                    case 2:
                        if (logRequestTraceCallback != null) {
                            Object[] objArr2 = (Object[]) message.obj;
                            logRequestTraceCallback.onEventInsertResult(((Boolean) objArr2[0]).booleanValue(), ((Long) objArr2[1]).longValue());
                            return;
                        }
                        return;
                    case 3:
                        if (logRequestTraceCallback != null) {
                            logRequestTraceCallback.onEventExpired((List) ((Object[]) message.obj)[0]);
                            return;
                        }
                        return;
                    case 4:
                        if (logRequestTraceCallback != null) {
                            for (String str : (List) ((Object[]) message.obj)[0]) {
                                logRequestTraceCallback.onLogDataStateChange("terminate", str, null, false, "");
                            }
                            return;
                        }
                        return;
                    case 5:
                        if (logRequestTraceCallback != null) {
                            Object[] objArr3 = (Object[]) message.obj;
                            logRequestTraceCallback.onEventSizeOverflow((String) objArr3[0], (String) objArr3[1], (String) objArr3[2], ((Integer) objArr3[3]).intValue());
                            return;
                        }
                        return;
                    case 6:
                        if (logRequestTraceCallback != null) {
                            logRequestTraceCallback.onPackSizeOverflow((String) ((Object[]) message.obj)[0]);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            } catch (Throwable th) {
                b.e("LogTrace handle log trace exception", th);
            }
        }
    }

    public static void notifyLogTrace(int i2, int i3, Object[] objArr) {
        Handler handler = getHandler();
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage(i2);
            obtainMessage.arg1 = i3;
            obtainMessage.obj = objArr;
            handler.sendMessage(obtainMessage);
        }
    }

    private static Handler getHandler() {
        if (sHandler == null) {
            synchronized (LogTrace.class) {
                if (sHandler == null) {
                    HandlerThread handlerThread = new HandlerThread("LogTraceThread");
                    handlerThread.start();
                    sHandler = new LogTraceHandler(handlerThread.getLooper());
                }
            }
        }
        return sHandler;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void registerLogRequestCallback(LogRequestTraceCallback logRequestTraceCallback) {
        sTraceCallback = logRequestTraceCallback;
    }
}
