package com.bytedance.retrofit2;

import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.text.TextUtils;
import com.bytedance.apm.constant.r;
import com.bytedance.apm.perf.traffic.a;
import com.bytedance.bdturing.methods.l;
import com.bytedance.pipo.payment.common.lib.support.impl.g;
import com.bytedance.retrofit2.InterceptorRecorder;
import com.bytedance.retrofit2.RetrofitLayerMetrics;
import com.bytedance.retrofit2.intercept.Interceptor;
import com.bytedance.retrofit2.ttnet.TTInternalInterceptor;
import com.bytedance.retrofit2.ttnet.TTNetExceptionStorage;
import com.facebook.devicerequests.internal.DeviceRequestsHelper;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class RetrofitMetrics {
    private static volatile Handler HANDLER = null;
    private static HandlerThread HANDLER_THREAD = null;
    public static final int REPORT_ALL_DETAILS = -1;
    private static volatile AsyncLogReporter logReporter = null;
    public static AppLaunchTimeStampProvider sAppStartTimeProvider = null;
    private static volatile boolean sReportNewMetrics = false;
    private static IRequestLogReporterBridge sRequestLogReporterBridge = null;
    private static volatile boolean sSyncReport = false;
    @Deprecated
    public long addClientKeyDuration;
    @Deprecated
    public long addCommonParamDuration;
    @Deprecated
    public long appCreateRetrofitStart;
    @Deprecated
    public long appCreateRetrofitStartUpTime;
    @Deprecated
    public long appLevelRequestStart;
    public long asyncDelayCount;
    @Deprecated
    public long beforeAllInterceptors;
    public Map<String, Object> bizExtraInfo;
    @Deprecated
    public int blockingQueueSize;
    @Deprecated
    public long bodyEncryptDuration;
    @Deprecated
    public long callExecuteStartTime;
    @Deprecated
    public long callServerInterceptorTime;
    @Deprecated
    public long checkReqTicketDuration;
    @Deprecated
    public long commandListenerDuration;
    public JSONObject concurrentRequest;
    @Deprecated
    public long createSsHttpCallTime;
    public long delayStartTimeStamp;
    public int delayState;
    public String delayStateName;
    public boolean delayedWithState;
    public JSONArray dispatchQueryActionInfo;
    @Deprecated
    public long encryptRequestDuration;
    @Deprecated
    public long enqueueCallbackEndTime;
    @Deprecated
    public long enqueueCallbackStartTime;
    @Deprecated
    public long enqueueTime;
    @Deprecated
    public long executeCallEndTime;
    @Deprecated
    public long executeCallStartTime;
    @Deprecated
    public long executeEndTime;
    @Deprecated
    public long executeTime;
    @Deprecated
    public Map<String, Long> extra;
    public String fallbackMessage;
    public int fallbackReason;
    @Deprecated
    public long filterDupQueryDuration;
    @Deprecated
    public long filterUrlDuration;
    @Deprecated
    public long genReqTicketDuration;
    private boolean hasNetworkKernelLog;
    private boolean hasRetrofitLog;
    @Deprecated
    public List<InterceptorInfo> interceptorRequestInfos;
    @Deprecated
    public List<InterceptorInfo> interceptorResponseInfos;
    @Deprecated
    public transient Throwable interceptorResponseThrowable;
    public boolean isConcurrent;
    public boolean isResponseStreaming;
    public NetworkLibLayerMetrics networklib;
    @Deprecated
    public long openConnectionDuration;
    public int postBodyBufferSize;
    @Deprecated
    public long postCdnCacheVerifyDuration;
    @Deprecated
    public long preCdnCacheVerifyDuration;
    @Deprecated
    public long prepareRequestDuration;
    public int priorityLevel;
    @Deprecated
    public long queryFilterDuration;
    public long randomSendTime;
    private boolean readingResponseBody;
    public JSONObject requestCompressInfo;
    public String requestCompressType;
    @Deprecated
    public int requestCookieSourceType;
    private EncryptType requestEncryptType;
    public String requestFallbackMessage;
    public int requestFallbackReason;
    public transient WeakReference<LegacyLogRecorder> requestInfo;
    @Deprecated
    public Map<String, Long> requestInterceptDuration;
    public int requestPriorityLevel;
    @Deprecated
    public long requestVerifyDuration;
    @Deprecated
    public long responseChainTime;
    public String responseCompressType;
    public boolean responseConvertSuccess;
    @Deprecated
    public Map<String, Long> responseInterceptDuration;
    public int responseReadType;
    public RetrofitLayerMetrics retrofit;
    @Deprecated
    public long retrofitLogReportTime;
    @Deprecated
    public long retrofitMethodInvokeTime;
    @Deprecated
    public JSONObject rotationHostRetryInfo;
    @Deprecated
    public long setThrottleNetSpeedDuration;
    public long stateDelayTime;
    public int streamParserCopyCount;
    public long syncDelayCount;
    @Deprecated
    public long toRequestEndTime;
    @Deprecated
    public long toRequestStartTime;
    @Deprecated
    public long toResponseEndTime;
    @Deprecated
    public long toResponseStartTime;
    public String traceCode;
    public String transactionId;
    @Deprecated
    public long tryNecessaryInitDuration;
    public String ttnetVersion;
    @Deprecated
    public long updateClientKeyDuration;
    @Deprecated
    public long upperAddCookieDurationUs;
    @Deprecated
    public long upperSaveCookieDurationUs;
    public URL url;

    @Deprecated
    /* loaded from: classes2.dex */
    public interface ExtraKeys {
        public static final String RECEIVED_BYTE_COUNT = "receivedByteCount";
        public static final String SENT_BYTE_COUNT = "sentByteCount";
        public static final String STREAM_READ_BYTE_COUNT = "streamReadByteCount";
        public static final String STREAM_READ_TIME = "streamReadTime";
    }

    /* loaded from: classes2.dex */
    public interface IRequestLogReporterBridge {
        boolean isAllErrorReport();

        void monitorApiError(String str, Object obj, Throwable th);

        void monitorApiOk(String str, Object obj);
    }

    /* loaded from: classes2.dex */
    public enum EncryptType {
        ENCRYPT_NONE(0),
        ENCRYPT_QUERY(1),
        ENCRYPT_BODY(2),
        ENCRYPT_BOTH_QUERY_AND_BODY(3);
        
        final int type;

        EncryptType(int i2) {
            this.type = i2;
        }

        public int getValue() {
            return this.type;
        }
    }

    public void setEncryptType(EncryptType encryptType) {
        this.requestEncryptType = encryptType;
    }

    public EncryptType getEncryptType() {
        return this.requestEncryptType;
    }

    public RetrofitMetrics(LegacyLogRecorder legacyLogRecorder) {
        this.url = null;
        this.requestInfo = null;
        this.streamParserCopyCount = -1;
        this.responseReadType = -1;
        this.requestEncryptType = EncryptType.ENCRYPT_NONE;
        this.fallbackReason = -1;
        this.fallbackMessage = "";
        this.requestFallbackReason = -1;
        this.requestFallbackMessage = "";
        this.transactionId = "";
        this.isConcurrent = false;
        this.delayState = -1;
        this.delayStateName = null;
        this.stateDelayTime = -1L;
        this.syncDelayCount = -1L;
        this.asyncDelayCount = -1L;
        this.delayStartTimeStamp = -1L;
        this.randomSendTime = -1L;
        this.interceptorResponseThrowable = null;
        this.requestInterceptDuration = new ConcurrentHashMap();
        this.responseInterceptDuration = new ConcurrentHashMap();
        this.filterUrlDuration = -1L;
        this.addCommonParamDuration = -1L;
        this.requestVerifyDuration = -1L;
        this.filterDupQueryDuration = -1L;
        this.encryptRequestDuration = -1L;
        this.genReqTicketDuration = -1L;
        this.checkReqTicketDuration = -1L;
        this.preCdnCacheVerifyDuration = -1L;
        this.addClientKeyDuration = -1L;
        this.updateClientKeyDuration = -1L;
        this.postCdnCacheVerifyDuration = -1L;
        this.commandListenerDuration = -1L;
        this.queryFilterDuration = -1L;
        this.bodyEncryptDuration = -1L;
        this.extra = new HashMap();
        this.ttnetVersion = "";
        this.traceCode = "";
        this.responseConvertSuccess = true;
        this.priorityLevel = -1;
        this.requestPriorityLevel = -1;
        this.responseCompressType = "";
        this.requestCompressType = "";
        this.setThrottleNetSpeedDuration = -1L;
        this.tryNecessaryInitDuration = -1L;
        this.openConnectionDuration = -1L;
        this.prepareRequestDuration = -1L;
        this.upperAddCookieDurationUs = -1L;
        this.upperSaveCookieDurationUs = -1L;
        this.requestCookieSourceType = -1;
        this.bizExtraInfo = new HashMap();
        this.rotationHostRetryInfo = null;
        this.blockingQueueSize = 0;
        this.interceptorRequestInfos = new ArrayList();
        this.interceptorResponseInfos = new ArrayList();
        this.retrofit = new RetrofitLayerMetrics();
        this.networklib = new NetworkLibLayerMetrics();
        this.readingResponseBody = false;
        this.hasNetworkKernelLog = false;
        this.hasRetrofitLog = false;
        this.requestInfo = new WeakReference<>(legacyLogRecorder);
    }

    public RetrofitMetrics() {
        this.url = null;
        this.requestInfo = null;
        this.streamParserCopyCount = -1;
        this.responseReadType = -1;
        this.requestEncryptType = EncryptType.ENCRYPT_NONE;
        this.fallbackReason = -1;
        this.fallbackMessage = "";
        this.requestFallbackReason = -1;
        this.requestFallbackMessage = "";
        this.transactionId = "";
        this.isConcurrent = false;
        this.delayState = -1;
        this.delayStateName = null;
        this.stateDelayTime = -1L;
        this.syncDelayCount = -1L;
        this.asyncDelayCount = -1L;
        this.delayStartTimeStamp = -1L;
        this.randomSendTime = -1L;
        this.interceptorResponseThrowable = null;
        this.requestInterceptDuration = new ConcurrentHashMap();
        this.responseInterceptDuration = new ConcurrentHashMap();
        this.filterUrlDuration = -1L;
        this.addCommonParamDuration = -1L;
        this.requestVerifyDuration = -1L;
        this.filterDupQueryDuration = -1L;
        this.encryptRequestDuration = -1L;
        this.genReqTicketDuration = -1L;
        this.checkReqTicketDuration = -1L;
        this.preCdnCacheVerifyDuration = -1L;
        this.addClientKeyDuration = -1L;
        this.updateClientKeyDuration = -1L;
        this.postCdnCacheVerifyDuration = -1L;
        this.commandListenerDuration = -1L;
        this.queryFilterDuration = -1L;
        this.bodyEncryptDuration = -1L;
        this.extra = new HashMap();
        this.ttnetVersion = "";
        this.traceCode = "";
        this.responseConvertSuccess = true;
        this.priorityLevel = -1;
        this.requestPriorityLevel = -1;
        this.responseCompressType = "";
        this.requestCompressType = "";
        this.setThrottleNetSpeedDuration = -1L;
        this.tryNecessaryInitDuration = -1L;
        this.openConnectionDuration = -1L;
        this.prepareRequestDuration = -1L;
        this.upperAddCookieDurationUs = -1L;
        this.upperSaveCookieDurationUs = -1L;
        this.requestCookieSourceType = -1;
        this.bizExtraInfo = new HashMap();
        this.rotationHostRetryInfo = null;
        this.blockingQueueSize = 0;
        this.interceptorRequestInfos = new ArrayList();
        this.interceptorResponseInfos = new ArrayList();
        this.retrofit = new RetrofitLayerMetrics();
        this.networklib = new NetworkLibLayerMetrics();
        this.readingResponseBody = false;
        this.hasNetworkKernelLog = false;
        this.hasRetrofitLog = false;
        new RetrofitMetrics(null);
    }

    public RetrofitMetrics cloneMetrics() {
        RetrofitMetrics retrofitMetrics = new RetrofitMetrics();
        retrofitMetrics.appCreateRetrofitStartUpTime = this.appCreateRetrofitStartUpTime;
        retrofitMetrics.appCreateRetrofitStart = this.appLevelRequestStart;
        retrofitMetrics.retrofitMethodInvokeTime = this.retrofitMethodInvokeTime;
        retrofitMetrics.createSsHttpCallTime = this.createSsHttpCallTime;
        retrofitMetrics.isResponseStreaming = this.isResponseStreaming;
        retrofitMetrics.retrofit.eventTs.bizInvokeRetrofitRequestInterfaceStartTime = this.retrofit.eventTs.bizInvokeRetrofitRequestInterfaceStartTime;
        retrofitMetrics.retrofit.eventTs.bizInvokeRetrofitRequestInterfaceStart = this.retrofit.eventTs.bizInvokeRetrofitRequestInterfaceStart;
        retrofitMetrics.retrofit.eventTs.bizInvokeRetrofitRequestInterfaceEnd = this.retrofit.eventTs.bizInvokeRetrofitRequestInterfaceEnd;
        retrofitMetrics.retrofit.innerSpan.createRequestFactory = this.retrofit.innerSpan.createRequestFactory;
        retrofitMetrics.retrofit.innerSpan.createHttpServiceMethod = this.retrofit.innerSpan.createHttpServiceMethod;
        return retrofitMetrics;
    }

    public RetrofitMetrics(long j2, long j3) {
        this.url = null;
        this.requestInfo = null;
        this.streamParserCopyCount = -1;
        this.responseReadType = -1;
        this.requestEncryptType = EncryptType.ENCRYPT_NONE;
        this.fallbackReason = -1;
        this.fallbackMessage = "";
        this.requestFallbackReason = -1;
        this.requestFallbackMessage = "";
        this.transactionId = "";
        this.isConcurrent = false;
        this.delayState = -1;
        this.delayStateName = null;
        this.stateDelayTime = -1L;
        this.syncDelayCount = -1L;
        this.asyncDelayCount = -1L;
        this.delayStartTimeStamp = -1L;
        this.randomSendTime = -1L;
        this.interceptorResponseThrowable = null;
        this.requestInterceptDuration = new ConcurrentHashMap();
        this.responseInterceptDuration = new ConcurrentHashMap();
        this.filterUrlDuration = -1L;
        this.addCommonParamDuration = -1L;
        this.requestVerifyDuration = -1L;
        this.filterDupQueryDuration = -1L;
        this.encryptRequestDuration = -1L;
        this.genReqTicketDuration = -1L;
        this.checkReqTicketDuration = -1L;
        this.preCdnCacheVerifyDuration = -1L;
        this.addClientKeyDuration = -1L;
        this.updateClientKeyDuration = -1L;
        this.postCdnCacheVerifyDuration = -1L;
        this.commandListenerDuration = -1L;
        this.queryFilterDuration = -1L;
        this.bodyEncryptDuration = -1L;
        this.extra = new HashMap();
        this.ttnetVersion = "";
        this.traceCode = "";
        this.responseConvertSuccess = true;
        this.priorityLevel = -1;
        this.requestPriorityLevel = -1;
        this.responseCompressType = "";
        this.requestCompressType = "";
        this.setThrottleNetSpeedDuration = -1L;
        this.tryNecessaryInitDuration = -1L;
        this.openConnectionDuration = -1L;
        this.prepareRequestDuration = -1L;
        this.upperAddCookieDurationUs = -1L;
        this.upperSaveCookieDurationUs = -1L;
        this.requestCookieSourceType = -1;
        this.bizExtraInfo = new HashMap();
        this.rotationHostRetryInfo = null;
        this.blockingQueueSize = 0;
        this.interceptorRequestInfos = new ArrayList();
        this.interceptorResponseInfos = new ArrayList();
        this.retrofit = new RetrofitLayerMetrics();
        this.networklib = new NetworkLibLayerMetrics();
        this.readingResponseBody = false;
        this.hasNetworkKernelLog = false;
        this.hasRetrofitLog = false;
        this.appLevelRequestStart = j2;
        this.beforeAllInterceptors = j3;
    }

    @Deprecated
    public String getRetrofitLog() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (this.fallbackReason != -1) {
                jSONObject.put(DeviceRequestsHelper.DEVICE_INFO_MODEL, getModelInfoWhenFallback());
            }
            JSONObject jSONObject2 = this.concurrentRequest;
            if (jSONObject2 != null) {
                jSONObject.put("concurrentRequest", jSONObject2);
            }
            jSONObject.put("concurrent", this.isConcurrent);
            jSONObject.put(r.STATS_BASE_MODULE, getBaseTimingInfo());
            jSONObject.put(l.VALUE_CALLBACK, getCallbackTimingInfo());
            jSONObject.put("interceptor", getInterceptorTimingInfo());
            jSONObject.put("ttnetVersion", this.ttnetVersion);
            JSONArray jSONArray = this.dispatchQueryActionInfo;
            if (jSONArray != null && jSONArray.length() > 0) {
                jSONObject.put("actionInfo", this.dispatchQueryActionInfo);
            }
            JSONObject jSONObject3 = this.requestCompressInfo;
            if (jSONObject3 != null) {
                jSONObject.put("compress", jSONObject3);
            }
            int i2 = this.postBodyBufferSize;
            if (i2 > 0) {
                jSONObject.put("postBufferSize", i2);
            }
            int i3 = this.blockingQueueSize;
            if (i3 > 0) {
                jSONObject.put("blockingQueueSize", i3);
            }
            if (this.networklib.misc.responseBodyReadTimes > 0) {
                jSONObject.put("apiStreamReadCount", this.networklib.misc.responseBodyReadTimes);
            }
            jSONObject.put("stateDelayed", this.delayedWithState);
            jSONObject.put("stateDelayTime", this.stateDelayTime);
            jSONObject.put("syncCount", this.syncDelayCount);
            jSONObject.put("asyncCount", this.asyncDelayCount);
            jSONObject.put("delayState", this.delayState);
            jSONObject.put("random", this.randomSendTime);
            if (!TextUtils.isEmpty(this.delayStateName)) {
                jSONObject.put("stateName", this.delayStateName);
            }
            if (this.addClientKeyDuration >= 0) {
                jSONObject.put("cli_key", true);
            }
            int i4 = this.streamParserCopyCount;
            if (i4 >= 0) {
                jSONObject.put("cp_cnt", i4);
            }
            int i5 = this.responseReadType;
            if (i5 >= 0) {
                jSONObject.put("read_type", i5);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject.toString();
    }

    private JSONObject getModelInfoWhenFallback() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(DeviceRequestsHelper.DEVICE_INFO_MODEL, Build.MODEL);
            String str = "";
            if (Build.VERSION.SDK_INT < 21) {
                str = Build.CPU_ABI;
            } else {
                String[] strArr = Build.SUPPORTED_ABIS;
                if (strArr != null && strArr.length > 0) {
                    str = Arrays.asList(strArr).toString();
                }
            }
            jSONObject.put("abis", str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    @Deprecated
    private JSONObject getBaseTimingInfo() {
        boolean validateAndSetTimingValue;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("fallback", this.fallbackReason);
            if (!TextUtils.isEmpty(this.fallbackMessage)) {
                jSONObject.put("fallbackMessage", this.fallbackMessage);
            }
            jSONObject.put("reqFallback", this.requestFallbackReason);
            if (!TextUtils.isEmpty(this.requestFallbackMessage)) {
                jSONObject.put("reqFallbackMsg", this.requestFallbackMessage);
            }
            jSONObject.put("createRetrofitTime", this.appCreateRetrofitStart);
            jSONObject.put("appRequestStartTime", this.appLevelRequestStart);
            jSONObject.put("beforeAllInterceptTime", this.beforeAllInterceptors);
            jSONObject.put("callServerInterceptTime", this.callServerInterceptorTime);
            jSONObject.put("callExecuteStartTime", this.callExecuteStartTime);
            jSONObject.put("reportTime", this.retrofitLogReportTime);
            if (this.requestEncryptType != EncryptType.ENCRYPT_NONE) {
                jSONObject.put(g.f10977i, this.requestEncryptType.getValue());
            }
            if (!TextUtils.isEmpty(this.transactionId)) {
                jSONObject.put("transactionId", this.transactionId);
            }
            long j2 = this.upperAddCookieDurationUs;
            if (j2 >= 0) {
                jSONObject.put("upper_add_cookie_us", j2);
            }
            long j3 = this.upperSaveCookieDurationUs;
            if (j3 >= 0) {
                jSONObject.put("upper_save_cookie_us", j3);
            }
            jSONObject.put("request_cookie_source", this.requestCookieSourceType);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        boolean validateAndSetTimingValue2 = validateAndSetTimingValue(jSONObject, "loadServiceMethod", this.retrofitMethodInvokeTime, this.createSsHttpCallTime, true);
        long j4 = this.enqueueTime;
        if (j4 > 0) {
            validateAndSetTimingValue = validateAndSetTimingValue(jSONObject, "enqueueWait", j4, this.responseChainTime, validateAndSetTimingValue2);
        } else {
            validateAndSetTimingValue = validateAndSetTimingValue(jSONObject, "executeWait", this.executeTime, this.responseChainTime, validateAndSetTimingValue2);
        }
        validateAndSetTimingValue(jSONObject, "biz_start_after_cronet_start", this.retrofit.eventTs.cronetInitStart / 1000, this.retrofit.eventTs.ssHttpCallApiStart / 1000, true);
        validateAndSetTimingValue(jSONObject, "biz_start_before_cronet_end", this.retrofit.eventTs.ssHttpCallApiEnd / 1000, this.retrofit.eventTs.cronetInitEnd / 1000, true);
        validateAndSetTimingValue(jSONObject, "responseParse", this.toResponseStartTime, this.toResponseEndTime, validateAndSetTimingValue(jSONObject, "requestParse", this.toRequestStartTime, this.toRequestEndTime, validateAndSetTimingValue(jSONObject, "executeCall", this.executeCallStartTime, this.executeCallEndTime, validateAndSetTimingValue)));
        return jSONObject;
    }

    @Deprecated
    private JSONObject getCallbackTimingInfo() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("filterUrl", this.filterUrlDuration);
            jSONObject.put("addCommonParam", this.addCommonParamDuration);
            jSONObject.put("requestVerify", this.requestVerifyDuration);
            jSONObject.put("encryptRequest", this.encryptRequestDuration);
            jSONObject.put("genReqTicket", this.genReqTicketDuration);
            jSONObject.put("checkReqTicket", this.checkReqTicketDuration);
            jSONObject.put("preCdnVerify", this.preCdnCacheVerifyDuration);
            jSONObject.put("postCdnVerify", this.postCdnCacheVerifyDuration);
            jSONObject.put("addClientKey", this.addClientKeyDuration);
            jSONObject.put("updateClientKey", this.updateClientKeyDuration);
            jSONObject.put("commandListener", this.commandListenerDuration);
            jSONObject.put("filterDupQuery", this.filterDupQueryDuration);
            jSONObject.put("queryFilter", this.queryFilterDuration);
            long j2 = this.bodyEncryptDuration;
            if (j2 >= 0) {
                jSONObject.put("bodyEncrypt", j2);
            }
            jSONObject.put("setSpeed", this.setThrottleNetSpeedDuration);
            jSONObject.put("tryInit", this.tryNecessaryInitDuration);
            jSONObject.put("openConn", this.openConnectionDuration);
            jSONObject.put("prepareReq", this.prepareRequestDuration);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    @Deprecated
    private JSONObject getInterceptorTimingInfo() {
        JSONObject jSONObject = new JSONObject();
        try {
            if (!this.requestInterceptDuration.isEmpty()) {
                JSONObject jSONObject2 = new JSONObject();
                for (Map.Entry<String, Long> entry : this.requestInterceptDuration.entrySet()) {
                    jSONObject2.put(entry.getKey(), entry.getValue());
                }
                jSONObject.put("request", jSONObject2);
            }
            if (!this.responseInterceptDuration.isEmpty()) {
                JSONObject jSONObject3 = new JSONObject();
                for (Map.Entry<String, Long> entry2 : this.responseInterceptDuration.entrySet()) {
                    jSONObject3.put(entry2.getKey(), entry2.getValue());
                }
                jSONObject.put(a.TTNET_RESPONSE, jSONObject3);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }

    private boolean validateAndSetTimingValue(JSONObject jSONObject, String str, long j2, long j3, boolean z) {
        try {
            if (!z || j2 > j3) {
                jSONObject.put(str, -1);
                return false;
            }
            jSONObject.put(str, j3 - j2);
            return true;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @Deprecated
    /* loaded from: classes2.dex */
    public class InterceptorInfo {
        public long end;
        public String exception_name = null;
        public String name;
        public long start;

        public InterceptorInfo(String str, long j2) {
            this.name = str;
            this.start = j2;
            this.end = j2;
        }
    }

    @Deprecated
    public void recordInterceptorRequestStart(Interceptor interceptor) {
        String name = interceptor.getClass().getName();
        if (TextUtils.isEmpty(name)) {
            return;
        }
        this.interceptorRequestInfos.add(new InterceptorInfo(name.substring(name.lastIndexOf(46) + 1), SystemClock.uptimeMillis()));
    }

    @Deprecated
    public void recordInterceptorRequestEnd() {
        if (this.interceptorRequestInfos.size() <= 0) {
            return;
        }
        List<InterceptorInfo> list = this.interceptorRequestInfos;
        list.get(list.size() - 1).end = SystemClock.uptimeMillis();
    }

    @Deprecated
    public void recordInterceptorResponseStart(Interceptor interceptor) {
        String name = interceptor.getClass().getName();
        if (TextUtils.isEmpty(name)) {
            return;
        }
        this.interceptorResponseInfos.add(new InterceptorInfo(name.substring(name.lastIndexOf(46) + 1), SystemClock.uptimeMillis()));
    }

    @Deprecated
    public void recordInterceptorResponseEnd(Exception exc) {
        if (this.interceptorResponseInfos.size() <= 0) {
            return;
        }
        List<InterceptorInfo> list = this.interceptorResponseInfos;
        list.get(list.size() - 1).end = SystemClock.uptimeMillis();
        if (exc != null) {
            List<InterceptorInfo> list2 = this.interceptorResponseInfos;
            list2.get(list2.size() - 1).exception_name = exc.getClass().getSimpleName();
        }
    }

    @Deprecated
    public JSONObject getRealChainInterceptorTimingInfo() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            for (InterceptorInfo interceptorInfo : this.interceptorRequestInfos) {
                jSONObject.put(interceptorInfo.name, interceptorInfo.end - interceptorInfo.start);
            }
            for (InterceptorInfo interceptorInfo2 : this.interceptorResponseInfos) {
                jSONObject2.put(interceptorInfo2.name, interceptorInfo2.end - interceptorInfo2.start);
            }
            jSONObject3.put("interceptorRequestInfo", jSONObject);
            jSONObject3.put("interceptorResponseInfo", jSONObject2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject3;
    }

    public void bizInvokeRetrofitRequestInterfaceStart(boolean z) {
        RetrofitLayerMetrics.EventTimestamp eventTimestamp = this.retrofit.eventTs;
        long currentTimeMillis = System.currentTimeMillis();
        eventTimestamp.bizInvokeRetrofitRequestInterfaceStartTime = currentTimeMillis;
        this.appCreateRetrofitStart = currentTimeMillis;
        this.retrofit.eventTs.bizInvokeRetrofitRequestInterfaceStart = System.nanoTime();
        long nanoSecondsToMilliSeconds = TTMetricsUtil.nanoSecondsToMilliSeconds(this.retrofit.eventTs.bizInvokeRetrofitRequestInterfaceStart);
        this.appCreateRetrofitStartUpTime = nanoSecondsToMilliSeconds;
        if (z) {
            this.createSsHttpCallTime = nanoSecondsToMilliSeconds;
            this.retrofitMethodInvokeTime = nanoSecondsToMilliSeconds;
        }
    }

    public void bizInvokeRetrofitRequestInterfaceEnd() {
        this.retrofit.eventTs.bizInvokeRetrofitRequestInterfaceEnd = System.nanoTime();
    }

    public void ssHttpCallApiStart(boolean z) {
        RetrofitLayerMetrics.EventTimestamp eventTimestamp = this.retrofit.eventTs;
        long currentTimeMillis = System.currentTimeMillis();
        eventTimestamp.ssHttpCallApiStartTime = currentTimeMillis;
        this.appLevelRequestStart = currentTimeMillis;
        this.retrofit.eventTs.ssHttpCallApiStart = System.nanoTime();
        if (z) {
            this.enqueueTime = TTMetricsUtil.nanoSecondsToMilliSeconds(this.retrofit.eventTs.ssHttpCallApiStart);
        } else {
            this.executeTime = TTMetricsUtil.nanoSecondsToMilliSeconds(this.retrofit.eventTs.ssHttpCallApiStart);
        }
        this.retrofit.misc.requestStartAppResumeState = AppActivityResumeSuspendRecorder.GetAppActivityResumeState();
        this.retrofit.bizSpan.waitSsHttpCallApiExecute = this.retrofit.eventTs.ssHttpCallApiStart - this.retrofit.eventTs.bizInvokeRetrofitRequestInterfaceEnd;
        if (this.retrofit.eventTs.toRequestEnd == -1 || this.retrofit.eventTs.toRequestEnd >= this.retrofit.eventTs.ssHttpCallApiStart || this.retrofit.innerSpan.toRequest == -1) {
            return;
        }
        this.retrofit.bizSpan.waitSsHttpCallApiExecute -= this.retrofit.innerSpan.toRequest;
    }

    public void requestRealStart() {
        long nanoTime = System.nanoTime();
        this.retrofit.eventTs.requestRealStart = nanoTime;
        this.retrofit.innerSpan.reqDelay = nanoTime - this.retrofit.eventTs.ssHttpCallApiStart;
        if (this.retrofit.eventTs.toRequestStart <= this.retrofit.eventTs.ssHttpCallApiStart || this.retrofit.innerSpan.toRequest == -1) {
            return;
        }
        this.retrofit.innerSpan.reqDelay -= this.retrofit.innerSpan.toRequest;
    }

    public void ssHttpCallApiEnd() {
        this.retrofit.eventTs.ssHttpCallApiEnd = System.nanoTime();
        this.executeEndTime = TTMetricsUtil.nanoSecondsToMilliSeconds(this.retrofit.eventTs.ssHttpCallApiEnd);
    }

    public void executeSsCallStart() {
        this.retrofit.eventTs.executeSsCallStart = System.nanoTime();
        this.executeCallStartTime = TTMetricsUtil.nanoSecondsToMilliSeconds(this.retrofit.eventTs.executeSsCallStart);
        this.callExecuteStartTime = ConvertToWallClockTimeMs(this.retrofit.eventTs.executeSsCallStart);
    }

    public void executeSsCallEnd() {
        this.retrofit.eventTs.executeSsCallEnd = System.nanoTime();
        this.executeCallEndTime = TTMetricsUtil.nanoSecondsToMilliSeconds(this.retrofit.eventTs.executeSsCallEnd);
    }

    public void bizReceiveResponseHeader() {
        this.retrofit.eventTs.bizReceiveResponseHeaderTime = System.currentTimeMillis();
        this.retrofit.eventTs.bizReceiveResponseHeader = System.nanoTime();
    }

    private boolean isRetrofitRequestEnd() {
        return this.retrofit.eventTs.requestEnd != -1;
    }

    public void requestEnd() {
        this.retrofit.eventTs.requestEnd = System.nanoTime();
        this.retrofit.eventTs.requestEndTime = System.currentTimeMillis();
        WeakReference<LegacyLogRecorder> weakReference = this.requestInfo;
        if (weakReference != null) {
            weakReference.get().setRequestEnd(this.retrofit.eventTs.requestEndTime);
        }
    }

    public void logReport() {
        this.retrofit.eventTs.logReport = System.nanoTime();
        this.retrofitLogReportTime = ConvertToWallClockTimeMs(this.retrofit.eventTs.logReport);
    }

    public void monitorApiHttpBeforeFilterStart() {
        this.retrofit.bizSpan.recordCallbackStart();
    }

    public void monitorApiHttpBeforeFilterEnd() {
        this.retrofit.bizSpan.monitorApiHttpBeforeFilter = this.retrofit.bizSpan.computeDurationNs();
    }

    public void filterUrlStart() {
        this.retrofit.bizSpan.recordCallbackStart();
    }

    public void filterUrlEnd() {
        this.retrofit.bizSpan.filterUrl = this.retrofit.bizSpan.computeDurationNs();
        this.filterUrlDuration = TTMetricsUtil.nanoSecondsToMilliSeconds(this.retrofit.bizSpan.filterUrl);
    }

    public void monitorApiHttpAfterFilterStart() {
        this.retrofit.bizSpan.recordCallbackStart();
    }

    public void monitorApiHttpAfterFilterEnd() {
        this.retrofit.bizSpan.monitorApiHttpAfterFilter = this.retrofit.bizSpan.computeDurationNs();
    }

    public void addCommonParamStart() {
        this.retrofit.bizSpan.recordCallbackStart();
    }

    public void addCommonParamEnd() {
        this.retrofit.bizSpan.addCommonParam = this.retrofit.bizSpan.computeDurationNs();
        this.addCommonParamDuration = TTMetricsUtil.nanoSecondsToMilliSeconds(this.retrofit.bizSpan.addCommonParam);
    }

    public void encryptRequestStart() {
        this.retrofit.bizSpan.recordCallbackStart();
    }

    public void encryptRequestEnd() {
        this.retrofit.bizSpan.encryptRequest = this.retrofit.bizSpan.computeDurationNs();
        this.encryptRequestDuration = TTMetricsUtil.nanoSecondsToMilliSeconds(this.retrofit.bizSpan.encryptRequest);
    }

    public void requestVerifyStart() {
        this.retrofit.bizSpan.recordCallbackStart();
    }

    public void requestVerifyEnd() {
        this.retrofit.bizSpan.requestVerify = this.retrofit.bizSpan.computeDurationNs();
        this.requestVerifyDuration = TTMetricsUtil.nanoSecondsToMilliSeconds(this.retrofit.bizSpan.requestVerify);
    }

    public void commandListenerStart() {
        this.retrofit.bizSpan.recordCallbackStart();
    }

    public void commandListenerEnd() {
        this.retrofit.bizSpan.commandListener = this.retrofit.bizSpan.computeDurationNs();
        this.commandListenerDuration = TTMetricsUtil.nanoSecondsToMilliSeconds(this.retrofit.bizSpan.commandListener);
    }

    public void checkReqTicketStart() {
        this.retrofit.bizSpan.recordCallbackStart();
    }

    public void checkReqTicketEnd() {
        this.retrofit.bizSpan.checkReqTicket = this.retrofit.bizSpan.computeDurationNs();
        this.checkReqTicketDuration = TTMetricsUtil.nanoSecondsToMilliSeconds(this.retrofit.bizSpan.checkReqTicket);
    }

    public void postCdnCacheVerifyStart() {
        this.retrofit.bizSpan.recordCallbackStart();
    }

    public void postCdnCacheVerifyEnd() {
        this.retrofit.bizSpan.postCdnCacheVerify = this.retrofit.bizSpan.computeDurationNs();
        this.postCdnCacheVerifyDuration = TTMetricsUtil.nanoSecondsToMilliSeconds(this.retrofit.bizSpan.postCdnCacheVerify);
    }

    public void enqueueCallbackStart() {
        this.retrofit.bizSpan.recordCallbackStart();
        this.enqueueCallbackStartTime = SystemClock.uptimeMillis();
    }

    public void enqueueCallbackEnd() {
        this.retrofit.bizSpan.enqueueCallback = this.retrofit.bizSpan.computeDurationNs();
        this.enqueueCallbackEndTime = SystemClock.uptimeMillis();
    }

    public void createRequestFactoryStart() {
        this.retrofit.innerSpan.recordInnerSpanStart();
        this.retrofitMethodInvokeTime = SystemClock.uptimeMillis();
    }

    public void createRequestFactoryEnd() {
        this.retrofit.innerSpan.createRequestFactory = this.retrofit.innerSpan.computeDurationNs();
    }

    public void createHttpServiceMethodStart() {
        this.retrofit.innerSpan.recordInnerSpanStart();
        this.createSsHttpCallTime = SystemClock.uptimeMillis();
    }

    public void createHttpServiceMethodEnd() {
        this.retrofit.innerSpan.createHttpServiceMethod = this.retrofit.innerSpan.computeDurationNs();
    }

    public void toRequestStart() {
        this.retrofit.innerSpan.recordInnerSpanStart();
        this.retrofit.eventTs.toRequestStart = System.nanoTime();
        this.toRequestStartTime = TTMetricsUtil.nanoSecondsToMilliSeconds(this.retrofit.eventTs.toRequestStart);
    }

    public void toRequestEnd() {
        this.retrofit.innerSpan.toRequest = this.retrofit.innerSpan.computeDurationNs();
        this.retrofit.eventTs.toRequestEnd = System.nanoTime();
        this.toRequestEndTime = TTMetricsUtil.nanoSecondsToMilliSeconds(this.retrofit.eventTs.toRequestEnd);
    }

    public void filterDupQueryStart() {
        this.retrofit.innerSpan.recordInnerSpanStart();
    }

    public void filterDupQueryEnd() {
        this.retrofit.innerSpan.filterDupQuery = this.retrofit.innerSpan.computeDurationNs();
        this.filterDupQueryDuration = TTMetricsUtil.nanoSecondsToMilliSeconds(this.retrofit.innerSpan.filterDupQuery);
    }

    public void genReqTicketStart() {
        this.retrofit.innerSpan.recordInnerSpanStart();
    }

    public void genReqTicketEnd() {
        this.retrofit.innerSpan.genReqTicket = this.retrofit.innerSpan.computeDurationNs();
        this.genReqTicketDuration = TTMetricsUtil.nanoSecondsToMilliSeconds(this.retrofit.innerSpan.genReqTicket);
    }

    public void preCdnCacheVerifyStart() {
        this.retrofit.innerSpan.recordInnerSpanStart();
    }

    public void preCdnCacheVerifyEnd() {
        this.retrofit.innerSpan.preCdnCacheVerify = this.retrofit.innerSpan.computeDurationNs();
        this.preCdnCacheVerifyDuration = TTMetricsUtil.nanoSecondsToMilliSeconds(this.retrofit.innerSpan.preCdnCacheVerify);
    }

    public void addClientKeyStart() {
        this.retrofit.innerSpan.recordInnerSpanStart();
    }

    public void addClientKeyEnd() {
        this.retrofit.innerSpan.addClientKey = this.retrofit.innerSpan.computeDurationNs();
        this.addClientKeyDuration = TTMetricsUtil.nanoSecondsToMilliSeconds(this.retrofit.innerSpan.addClientKey);
    }

    public void toSsResponseStart() {
        this.retrofit.innerSpan.recordInnerSpanStart();
        this.toResponseStartTime = SystemClock.uptimeMillis();
    }

    public void toSsResponseEnd() {
        this.retrofit.innerSpan.toSsResponse = this.retrofit.innerSpan.computeDurationNs();
        this.toResponseEndTime = SystemClock.uptimeMillis();
    }

    public void updateClientKeyStart() {
        this.retrofit.innerSpan.recordInnerSpanStart();
    }

    public void updateClientKeyEnd() {
        this.retrofit.innerSpan.updateClientKey = this.retrofit.innerSpan.computeDurationNs();
        this.updateClientKeyDuration = TTMetricsUtil.nanoSecondsToMilliSeconds(this.retrofit.innerSpan.updateClientKey);
    }

    public void beforeInterceptorExecute(Interceptor interceptor) {
        String name = interceptor.getClass().getName();
        if (TextUtils.isEmpty(name)) {
            return;
        }
        long nanoTime = System.nanoTime();
        InterceptorRecorder.LogEntry peek = this.retrofit.interceptorRecorder.peek();
        if (peek != null) {
            if (peek.reqEndNs == -1) {
                peek.reqEndNs = nanoTime;
            }
            if (!peek.isInternalInterceptor) {
                if (this.retrofit.bizSpan.bizRequestInterceptor == -1) {
                    this.retrofit.bizSpan.bizRequestInterceptor = 0L;
                }
                this.retrofit.bizSpan.bizRequestInterceptor += peek.reqEndNs - peek.reqStartNs;
            }
        } else {
            this.retrofit.eventTs.requestInterceptorStart = nanoTime;
            this.responseChainTime = TTMetricsUtil.nanoSecondsToMilliSeconds(nanoTime);
            this.beforeAllInterceptors = ConvertToWallClockTimeMs(nanoTime);
        }
        this.retrofit.interceptorRecorder.push(new InterceptorRecorder.LogEntry(name.substring(name.lastIndexOf(46) + 1), interceptor instanceof TTInternalInterceptor, nanoTime));
    }

    public void lowestInterceptorRequestEnd() {
        long nanoTime = System.nanoTime();
        this.retrofit.eventTs.requestInterceptorEnd = nanoTime;
        InterceptorRecorder.LogEntry peek = this.retrofit.interceptorRecorder.peek();
        if (peek != null) {
            peek.reqEndNs = nanoTime;
        }
    }

    public void lowestInterceptorResponseStart() {
        long nanoTime = System.nanoTime();
        this.retrofit.eventTs.responseInterceptorStart = nanoTime;
        InterceptorRecorder.LogEntry peek = this.retrofit.interceptorRecorder.peek();
        if (peek != null) {
            peek.rspStartNs = nanoTime;
        }
    }

    public void afterInterceptorExecute(Throwable th) {
        long nanoTime = System.nanoTime();
        InterceptorRecorder.LogEntry pop = this.retrofit.interceptorRecorder.pop();
        if (pop != null) {
            if (pop.reqEndNs == -1) {
                pop.reqEndNs = nanoTime;
                if (th != null) {
                    pop.exception_name = th.getClass().getSimpleName();
                }
            } else if (pop.rspStartNs != -1) {
                pop.rspEndNs = nanoTime;
                if (th != null) {
                    pop.exception_name = th.getClass().getSimpleName();
                }
            }
            if (!pop.isInternalInterceptor && pop.rspStartNs != -1 && pop.rspEndNs != -1) {
                if (this.retrofit.bizSpan.bizResponseInterceptor == -1) {
                    this.retrofit.bizSpan.bizResponseInterceptor = 0L;
                }
                this.retrofit.bizSpan.bizResponseInterceptor += pop.rspEndNs - pop.rspStartNs;
            }
        }
        InterceptorRecorder.LogEntry peek = this.retrofit.interceptorRecorder.peek();
        if (peek == null) {
            this.retrofit.eventTs.responseInterceptorEnd = nanoTime;
        } else if (th == null) {
            peek.rspStartNs = nanoTime;
        }
    }

    public void setInterceptorResponseThrowable(Throwable th) {
        this.retrofit.misc.interceptorResponseThrowable = th;
        this.interceptorResponseThrowable = th;
    }

    public void setBlockingQueueSize(int i2) {
        this.retrofit.misc.blockingQueueSize = i2;
        this.blockingQueueSize = i2;
    }

    public void setRequestEndAppResumeState(long j2) {
        this.retrofit.misc.requestEndAppResumeState = j2;
        WeakReference<LegacyLogRecorder> weakReference = this.requestInfo;
        if (weakReference != null) {
            weakReference.get().setRequestEndAppResumeState(j2);
        }
    }

    public void callServerInterceptorStart() {
        this.retrofit.misc.callServerExecutedTimes++;
        this.callServerInterceptorTime = SystemClock.uptimeMillis();
    }

    public void prepareConnectionStart() {
        this.networklib.eventTs.prepareConnectionStart = System.nanoTime();
    }

    public void prepareConnectionEnd() {
        this.networklib.eventTs.prepareConnectionEnd = System.nanoTime();
    }

    public void bodyEncryptStart() {
        this.networklib.bizSpan.recordCallbackStart();
    }

    public void bodyEncryptEnd() {
        this.networklib.bizSpan.bodyEncrypt = this.networklib.bizSpan.computeDurationNs();
        this.bodyEncryptDuration = TTMetricsUtil.nanoSecondsToMilliSeconds(this.networklib.bizSpan.bodyEncrypt);
    }

    public void onTryInitStart() {
        this.networklib.bizSpan.recordCallbackStart();
    }

    public void onTryInitEnd() {
        this.networklib.bizSpan.onTryInit = this.networklib.bizSpan.computeDurationNs();
        if (this.tryNecessaryInitDuration == -1) {
            this.tryNecessaryInitDuration = 0L;
        }
        this.tryNecessaryInitDuration += TTMetricsUtil.nanoSecondsToMilliSeconds(this.networklib.bizSpan.onTryInit);
    }

    public void tryInitCookieManagerStart() {
        this.networklib.bizSpan.recordCallbackStart();
    }

    public void tryInitCookieManagerEnd() {
        this.networklib.bizSpan.tryInitCookieManager = this.networklib.bizSpan.computeDurationNs();
        if (this.tryNecessaryInitDuration == -1) {
            this.tryNecessaryInitDuration = 0L;
        }
        this.tryNecessaryInitDuration += TTMetricsUtil.nanoSecondsToMilliSeconds(this.networklib.bizSpan.tryInitCookieManager);
    }

    public void bdTuringStart() {
        this.networklib.bizSpan.recordCallbackStart();
    }

    public void bdTuringEnd() {
        this.networklib.bizSpan.bdTuringDuration = this.networklib.bizSpan.computeDurationNs();
        WeakReference<LegacyLogRecorder> weakReference = this.requestInfo;
        if (weakReference != null) {
            weakReference.get().setBdTuringCallbackDuration(TTMetricsUtil.nanoSecondsToMilliSeconds(this.networklib.bizSpan.bdTuringDuration));
        }
    }

    public void accountRetryStart() {
        this.networklib.bizSpan.recordCallbackStart();
    }

    public void accountRetryEnd() {
        this.networklib.bizSpan.accountRetryDuration = this.networklib.bizSpan.computeDurationNs();
        WeakReference<LegacyLogRecorder> weakReference = this.requestInfo;
        if (weakReference != null) {
            weakReference.get().setAccountRetryCallbackDuration(TTMetricsUtil.nanoSecondsToMilliSeconds(this.networklib.bizSpan.accountRetryDuration));
        }
    }

    public void rotationRetryStart() {
        this.networklib.bizSpan.recordCallbackStart();
    }

    public void rotationRetryEnd() {
        if (this.networklib.bizSpan.rotationRetryAccumulatedDuration == -1) {
            this.networklib.bizSpan.rotationRetryAccumulatedDuration = 0L;
        }
        this.networklib.bizSpan.rotationRetryAccumulatedDuration += this.networklib.bizSpan.computeDurationNs();
    }

    public void inputStreamProcessStart() {
        this.networklib.bizSpan.recordCallbackStart();
    }

    public void inputStreamProcessEnd() {
        this.networklib.bizSpan.inputStreamProcessDuration = this.networklib.bizSpan.computeDurationNs();
    }

    public void readResponseBodyStart() {
        this.readingResponseBody = true;
        if (this.networklib.misc.responseBodyReadTimes == -1) {
            this.networklib.misc.responseBodyReadTimes = 0L;
        }
        this.networklib.misc.responseBodyReadTimes++;
        if (this.networklib.eventTs.firstReadResponseBodyStart == -1) {
            this.networklib.eventTs.firstReadResponseBodyStart = System.nanoTime();
        }
        if (this.networklib.misc.readResponseBodyInternal) {
            return;
        }
        if (this.networklib.bizSpan.waitReadResponseBody == -1 && this.retrofit.eventTs.bizReceiveResponseHeader != -1 && this.networklib.eventTs.firstReadResponseBodyStart > this.retrofit.eventTs.bizReceiveResponseHeader) {
            this.networklib.bizSpan.waitReadResponseBody = this.networklib.eventTs.firstReadResponseBodyStart - this.retrofit.eventTs.bizReceiveResponseHeader;
        }
        if (this.networklib.eventTs.latestReadResponseBodyEnd != -1) {
            if (this.networklib.bizSpan.readResponseBodyInterval == -1) {
                this.networklib.bizSpan.readResponseBodyInterval = 0L;
            }
            this.networklib.bizSpan.readResponseBodyInterval += System.nanoTime() - this.networklib.eventTs.latestReadResponseBodyEnd;
        }
        this.networklib.innerSpan.recordInnerSpanStart();
    }

    public void readResponseBodyEnd() {
        this.readingResponseBody = false;
        this.networklib.eventTs.latestReadResponseBodyEnd = System.nanoTime();
        if (this.networklib.misc.readResponseBodyInternal) {
            return;
        }
        if (this.networklib.innerSpan.readResponseBody == -1) {
            this.networklib.innerSpan.readResponseBody = 0L;
        }
        this.networklib.innerSpan.readResponseBody += this.networklib.innerSpan.computeDurationNs();
        if (isRetrofitRequestEnd()) {
            maybeReportRequestLog();
        }
    }

    public void queryFilterStart() {
        this.networklib.innerSpan.recordInnerSpanStart();
    }

    public void queryFilterEnd() {
        this.networklib.innerSpan.queryFilter = this.networklib.innerSpan.computeDurationNs();
        this.queryFilterDuration = TTMetricsUtil.nanoSecondsToMilliSeconds(this.networklib.innerSpan.queryFilter);
    }

    public long constructConnectionStart() {
        return System.nanoTime();
    }

    public void constructConnectionEnd(long j2) {
        this.networklib.innerSpan.constructConnection = System.nanoTime() - j2;
        this.openConnectionDuration = TTMetricsUtil.nanoSecondsToMilliSeconds(this.networklib.innerSpan.constructConnection);
    }

    public long configureConnectionStart() {
        return System.nanoTime();
    }

    public void configureConnectionEnd(long j2) {
        this.networklib.innerSpan.configureConnection = System.nanoTime() - j2;
        this.prepareRequestDuration = TTMetricsUtil.nanoSecondsToMilliSeconds(this.networklib.innerSpan.configureConnection);
    }

    public long executeConnectionStart() {
        long nanoTime = System.nanoTime();
        this.networklib.eventTs.executeConnectionStart = nanoTime;
        return nanoTime;
    }

    public void executeConnectionEnd(long j2) {
        long nanoTime = System.nanoTime();
        this.networklib.eventTs.executeConnectionEnd = nanoTime;
        this.networklib.innerSpan.executeConnection = nanoTime - j2;
    }

    public void processResponseBodyStart() {
        this.networklib.eventTs.processResponseBodyStart = System.nanoTime();
        this.networklib.innerSpan.recordInnerSpanStart();
    }

    public void processResponseBodyEnd() {
        this.networklib.eventTs.processResponseBodyEnd = System.nanoTime();
        this.networklib.innerSpan.processResponseBody = this.networklib.innerSpan.computeDurationNs();
    }

    public void postProcessBodyStart() {
        this.networklib.innerSpan.recordInnerSpanStart();
    }

    public void postProcessBodyEnd() {
        this.networklib.innerSpan.postProcessBody = this.networklib.innerSpan.computeDurationNs();
    }

    @Deprecated
    public void setConstructConfigureAndRetryConnection(long j2) {
        this.networklib.innerSpan.constructConfigureAndRetryConnection = System.nanoTime() - j2;
    }

    public void setKernelDns(long j2) {
        this.networklib.kernelSpan.dns = j2;
        WeakReference<LegacyLogRecorder> weakReference = this.requestInfo;
        if (weakReference != null) {
            weakReference.get().setKernelDns(j2);
        }
    }

    public void setKernelTCPConnect(long j2) {
        this.networklib.kernelSpan.tcpConnect = j2;
        WeakReference<LegacyLogRecorder> weakReference = this.requestInfo;
        if (weakReference != null) {
            weakReference.get().setKernelTCPConnect(j2);
        }
    }

    public void setKernelTls(long j2) {
        this.networklib.kernelSpan.tls = j2;
        WeakReference<LegacyLogRecorder> weakReference = this.requestInfo;
        if (weakReference != null) {
            weakReference.get().setKernelTls(j2);
        }
    }

    public void setKernelSend(long j2) {
        this.networklib.kernelSpan.send = j2;
        WeakReference<LegacyLogRecorder> weakReference = this.requestInfo;
        if (weakReference != null) {
            weakReference.get().setKernelSend(j2);
        }
    }

    public void setKernelPush(long j2) {
        this.networklib.kernelSpan.push = j2;
        WeakReference<LegacyLogRecorder> weakReference = this.requestInfo;
        if (weakReference != null) {
            weakReference.get().setKernelPush(j2);
        }
    }

    public void setKernelTTFB(long j2) {
        this.networklib.kernelSpan.ttfb = j2;
        WeakReference<LegacyLogRecorder> weakReference = this.requestInfo;
        if (weakReference != null) {
            weakReference.get().setKernelTTFB(j2);
        }
    }

    public void setKernelReceiveBody(long j2) {
        this.networklib.kernelSpan.receiveBody = j2;
        WeakReference<LegacyLogRecorder> weakReference = this.requestInfo;
        if (weakReference != null) {
            weakReference.get().setKernelReceiveBody(j2);
        }
    }

    public void setKernelTotal(long j2) {
        this.networklib.kernelSpan.total = j2;
        WeakReference<LegacyLogRecorder> weakReference = this.requestInfo;
        if (weakReference != null) {
            weakReference.get().setKernelTotal(j2);
        }
    }

    public void setUpperAddCookieDurationUs(long j2) {
        this.networklib.kernelSpan.upperAddCookieDurationUs = j2;
        this.upperAddCookieDurationUs = j2;
    }

    public void setUpperSaveCookieDurationUs(long j2) {
        this.networklib.kernelSpan.upperSaveCookieDurationUs = j2;
        this.upperSaveCookieDurationUs = j2;
    }

    public void setCronetReadResponseBodyMetrics(long j2, long j3, long j4, long j5, long j6) {
        this.networklib.eventTs.firstReadResponseBodyStart = j3;
        this.networklib.eventTs.latestReadResponseBodyEnd = j4;
        this.networklib.misc.responseBodyReadTimes = j2;
        if (this.networklib.misc.readResponseBodyInternal) {
            return;
        }
        if (this.networklib.bizSpan.waitReadResponseBody == -1 && this.retrofit.eventTs.bizReceiveResponseHeader != -1 && this.networklib.eventTs.firstReadResponseBodyStart > this.retrofit.eventTs.bizReceiveResponseHeader) {
            this.networklib.bizSpan.waitReadResponseBody = this.networklib.eventTs.firstReadResponseBodyStart - this.retrofit.eventTs.bizReceiveResponseHeader;
        }
        this.networklib.bizSpan.readResponseBodyInterval = j6;
        this.networklib.innerSpan.readResponseBody = j5;
    }

    public void markReadResponseBodyInternal() {
        this.networklib.misc.readResponseBodyInternal = true;
    }

    public void setRequestCookieSourceType(int i2) {
        this.networklib.misc.requestCookieSourceType = i2;
        this.requestCookieSourceType = i2;
    }

    public void setRotationHostRetryInfo(JSONObject jSONObject) {
        this.networklib.misc.rotationHostRetryInfo = jSONObject;
        this.rotationHostRetryInfo = jSONObject;
    }

    public void setNetworkKernelRawRequestLog(String str) {
        this.networklib.networkKernelRawRequestLog = str;
        WeakReference<LegacyLogRecorder> weakReference = this.requestInfo;
        if (weakReference != null) {
            weakReference.get().setNetworkKernelRawRequestLog(str);
        }
    }

    public void setRequestExtraInfo(Object obj) {
        this.networklib.misc.requestExtraInfo = obj;
    }

    public long ConvertToWallClockTimeMs(long j2) {
        return TTMetricsUtil.calculateWallClockTimeMs(this.retrofit.eventTs.bizInvokeRetrofitRequestInterfaceStartTime, this.retrofit.eventTs.bizInvokeRetrofitRequestInterfaceStart, j2);
    }

    public void markNetworkKernelLogCollected() {
        this.hasNetworkKernelLog = true;
        maybeReportRequestLog();
    }

    public void markRetrofitLayerLogCollected() {
        this.hasRetrofitLog = true;
        maybeReportRequestLog();
    }

    public static void setRequestLogReporterBridge(IRequestLogReporterBridge iRequestLogReporterBridge) {
        sRequestLogReporterBridge = iRequestLogReporterBridge;
    }

    public static void setReportNewMetrics(boolean z) {
        sReportNewMetrics = z;
    }

    public static boolean getReportNewMetrics() {
        return sReportNewMetrics;
    }

    public static void setSyncReport(boolean z) {
        sSyncReport = z;
    }

    public static void setLogReporter(AsyncLogReporter asyncLogReporter) {
        logReporter = asyncLogReporter;
    }

    public static void setReportDetail(int i2) {
        RetrofitLayerMetrics.setReportDetail(i2);
        NetworkLibLayerMetrics.setReportDetail(i2);
    }

    private void maybeReportRequestLog() {
        WeakReference<LegacyLogRecorder> weakReference;
        if (this.hasRetrofitLog && this.hasNetworkKernelLog) {
            requestEnd();
            if (this.readingResponseBody) {
                return;
            }
            setRequestEndAppResumeState(AppActivityResumeSuspendRecorder.GetAppActivityResumeState());
            if (sRequestLogReporterBridge == null || (weakReference = this.requestInfo) == null || weakReference.get() == null) {
                return;
            }
            final LegacyLogRecorder legacyLogRecorder = this.requestInfo.get();
            final Throwable th = this.retrofit.misc.requestException != null ? this.retrofit.misc.requestException.get() : null;
            if (sSyncReport) {
                doReportRequestLog(legacyLogRecorder, th);
            } else if (logReporter != null) {
                logReporter.postDelayed(new Runnable() { // from class: com.bytedance.retrofit2.RetrofitMetrics.1
                    @Override // java.lang.Runnable
                    public void run() {
                        RetrofitMetrics.this.doReportRequestLog(legacyLogRecorder, th);
                    }
                }, 100L);
            } else {
                if (HANDLER == null) {
                    synchronized (RetrofitMetrics.class) {
                        if (HANDLER == null) {
                            HandlerThread handlerThread = new HandlerThread("TTNet-Log-Report");
                            HANDLER_THREAD = handlerThread;
                            handlerThread.start();
                            HANDLER = new Handler(HANDLER_THREAD.getLooper());
                        }
                    }
                }
                HANDLER.postDelayed(new Runnable() { // from class: com.bytedance.retrofit2.RetrofitMetrics.2
                    @Override // java.lang.Runnable
                    public void run() {
                        RetrofitMetrics.this.doReportRequestLog(legacyLogRecorder, th);
                    }
                }, 100L);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doReportRequestLog(Object obj, Throwable th) {
        URL url = this.url;
        String url2 = url != null ? url.toString() : "";
        if (th == null) {
            sRequestLogReporterBridge.monitorApiOk(url2, obj);
        } else if (th instanceof TTNetExceptionStorage) {
            TTNetExceptionStorage tTNetExceptionStorage = (TTNetExceptionStorage) th;
            if (tTNetExceptionStorage.reportMonitorOk) {
                sRequestLogReporterBridge.monitorApiOk(url2, obj);
            }
            if (tTNetExceptionStorage.reportMonitorError) {
                sRequestLogReporterBridge.monitorApiError(url2, obj, th);
            }
        } else if (this.retrofit.misc.executeSsCallSuccess) {
            setInterceptorResponseThrowable(th);
            sRequestLogReporterBridge.monitorApiOk(url2, obj);
        } else if (sRequestLogReporterBridge.isAllErrorReport()) {
            sRequestLogReporterBridge.monitorApiError(url2, obj, th);
        }
    }

    public void toJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        WeakReference<LegacyLogRecorder> weakReference = this.requestInfo;
        if (weakReference != null) {
            weakReference.get().generateOldFormatLog(jSONObject);
        }
        if (sReportNewMetrics) {
            generateTTNetLog(jSONObject);
        }
    }

    private void generateTTNetLog(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("retrofit", this.retrofit.toJson());
        jSONObject2.put("networklib", this.networklib.toJson());
        jSONObject.put("ttnet", jSONObject2);
    }

    public RetrofitMetrics deepCopy() {
        RetrofitMetrics retrofitMetrics = new RetrofitMetrics();
        copyTo(retrofitMetrics);
        return retrofitMetrics;
    }

    public void copyTo(RetrofitMetrics retrofitMetrics) {
        if (retrofitMetrics == null) {
            return;
        }
        try {
            if (this.url != null) {
                retrofitMetrics.url = new URL(this.url.toString());
            }
        } catch (Exception unused) {
        }
        retrofitMetrics.isResponseStreaming = this.isResponseStreaming;
        retrofitMetrics.streamParserCopyCount = this.streamParserCopyCount;
        retrofitMetrics.responseReadType = this.responseReadType;
        retrofitMetrics.requestEncryptType = this.requestEncryptType;
        retrofitMetrics.fallbackReason = this.fallbackReason;
        retrofitMetrics.fallbackMessage = this.fallbackMessage;
        retrofitMetrics.requestFallbackReason = this.requestFallbackReason;
        retrofitMetrics.requestFallbackMessage = this.requestFallbackMessage;
        try {
            retrofitMetrics.concurrentRequest = this.concurrentRequest != null ? new JSONObject(this.concurrentRequest.toString()) : null;
        } catch (JSONException unused2) {
        }
        retrofitMetrics.transactionId = this.transactionId;
        retrofitMetrics.isConcurrent = this.isConcurrent;
        retrofitMetrics.appCreateRetrofitStart = this.appCreateRetrofitStart;
        retrofitMetrics.appLevelRequestStart = this.appLevelRequestStart;
        retrofitMetrics.beforeAllInterceptors = this.beforeAllInterceptors;
        retrofitMetrics.callServerInterceptorTime = this.callServerInterceptorTime;
        retrofitMetrics.callExecuteStartTime = this.callExecuteStartTime;
        retrofitMetrics.retrofitLogReportTime = this.retrofitLogReportTime;
        retrofitMetrics.retrofitMethodInvokeTime = this.retrofitMethodInvokeTime;
        retrofitMetrics.createSsHttpCallTime = this.createSsHttpCallTime;
        retrofitMetrics.appCreateRetrofitStartUpTime = this.appCreateRetrofitStartUpTime;
        retrofitMetrics.enqueueTime = this.enqueueTime;
        retrofitMetrics.executeTime = this.executeTime;
        retrofitMetrics.delayState = this.delayState;
        retrofitMetrics.delayStateName = this.delayStateName;
        retrofitMetrics.delayedWithState = this.delayedWithState;
        retrofitMetrics.stateDelayTime = this.stateDelayTime;
        retrofitMetrics.syncDelayCount = this.syncDelayCount;
        retrofitMetrics.asyncDelayCount = this.asyncDelayCount;
        retrofitMetrics.delayStartTimeStamp = this.delayStartTimeStamp;
        retrofitMetrics.randomSendTime = this.randomSendTime;
        retrofitMetrics.responseChainTime = this.responseChainTime;
        retrofitMetrics.toRequestStartTime = this.toRequestStartTime;
        retrofitMetrics.toRequestEndTime = this.toRequestEndTime;
        retrofitMetrics.executeCallStartTime = this.executeCallStartTime;
        retrofitMetrics.executeCallEndTime = this.executeCallEndTime;
        retrofitMetrics.toResponseStartTime = this.toResponseStartTime;
        retrofitMetrics.toResponseEndTime = this.toResponseEndTime;
        if (this.interceptorResponseThrowable != null) {
            Throwable th = new Throwable(this.interceptorResponseThrowable.getMessage());
            th.setStackTrace(this.interceptorResponseThrowable.getStackTrace());
            retrofitMetrics.interceptorResponseThrowable = th;
        }
        if (this.requestInterceptDuration != null) {
            retrofitMetrics.requestInterceptDuration = new ConcurrentHashMap();
            for (Map.Entry<String, Long> entry : this.requestInterceptDuration.entrySet()) {
                retrofitMetrics.requestInterceptDuration.put(entry.getKey(), entry.getValue());
            }
        }
        if (this.responseInterceptDuration != null) {
            retrofitMetrics.responseInterceptDuration = new ConcurrentHashMap();
            for (Map.Entry<String, Long> entry2 : this.responseInterceptDuration.entrySet()) {
                retrofitMetrics.responseInterceptDuration.put(entry2.getKey(), entry2.getValue());
            }
        }
        retrofitMetrics.filterUrlDuration = this.filterUrlDuration;
        retrofitMetrics.addCommonParamDuration = this.addCommonParamDuration;
        retrofitMetrics.requestVerifyDuration = this.requestVerifyDuration;
        retrofitMetrics.filterDupQueryDuration = this.filterDupQueryDuration;
        retrofitMetrics.encryptRequestDuration = this.encryptRequestDuration;
        retrofitMetrics.genReqTicketDuration = this.genReqTicketDuration;
        retrofitMetrics.checkReqTicketDuration = this.checkReqTicketDuration;
        retrofitMetrics.preCdnCacheVerifyDuration = this.preCdnCacheVerifyDuration;
        retrofitMetrics.addClientKeyDuration = this.addClientKeyDuration;
        retrofitMetrics.updateClientKeyDuration = this.updateClientKeyDuration;
        retrofitMetrics.postCdnCacheVerifyDuration = this.postCdnCacheVerifyDuration;
        retrofitMetrics.commandListenerDuration = this.commandListenerDuration;
        retrofitMetrics.queryFilterDuration = this.queryFilterDuration;
        retrofitMetrics.bodyEncryptDuration = this.bodyEncryptDuration;
        if (this.extra != null) {
            retrofitMetrics.extra = new ConcurrentHashMap();
            for (Map.Entry<String, Long> entry3 : this.extra.entrySet()) {
                retrofitMetrics.extra.put(entry3.getKey(), entry3.getValue());
            }
        }
        retrofitMetrics.ttnetVersion = this.ttnetVersion;
        try {
            if (this.dispatchQueryActionInfo != null) {
                retrofitMetrics.dispatchQueryActionInfo = new JSONArray(this.dispatchQueryActionInfo.toString());
            }
        } catch (JSONException unused3) {
        }
        retrofitMetrics.traceCode = this.traceCode;
        retrofitMetrics.responseConvertSuccess = this.responseConvertSuccess;
        retrofitMetrics.enqueueCallbackStartTime = this.enqueueCallbackStartTime;
        retrofitMetrics.enqueueCallbackEndTime = this.enqueueCallbackEndTime;
        retrofitMetrics.executeEndTime = this.executeEndTime;
        retrofitMetrics.priorityLevel = this.priorityLevel;
        retrofitMetrics.requestPriorityLevel = this.requestPriorityLevel;
        retrofitMetrics.responseCompressType = this.responseCompressType;
        retrofitMetrics.requestCompressType = this.requestCompressType;
        try {
            retrofitMetrics.requestCompressInfo = this.requestCompressInfo != null ? new JSONObject(this.requestCompressInfo.toString()) : null;
        } catch (JSONException unused4) {
        }
        retrofitMetrics.postBodyBufferSize = this.postBodyBufferSize;
        retrofitMetrics.setThrottleNetSpeedDuration = this.setThrottleNetSpeedDuration;
        retrofitMetrics.tryNecessaryInitDuration = this.tryNecessaryInitDuration;
        retrofitMetrics.openConnectionDuration = this.openConnectionDuration;
        retrofitMetrics.prepareRequestDuration = this.prepareRequestDuration;
        retrofitMetrics.upperAddCookieDurationUs = this.upperAddCookieDurationUs;
        retrofitMetrics.upperSaveCookieDurationUs = this.upperSaveCookieDurationUs;
        retrofitMetrics.requestCookieSourceType = this.requestCookieSourceType;
        if (this.bizExtraInfo != null) {
            retrofitMetrics.bizExtraInfo = new HashMap();
            for (Map.Entry<String, Object> entry4 : this.bizExtraInfo.entrySet()) {
                retrofitMetrics.bizExtraInfo.put(entry4.getKey(), entry4.getValue());
            }
        }
        try {
            retrofitMetrics.rotationHostRetryInfo = this.rotationHostRetryInfo != null ? new JSONObject(this.rotationHostRetryInfo.toString()) : null;
        } catch (JSONException unused5) {
        }
        retrofitMetrics.blockingQueueSize = this.blockingQueueSize;
        List<InterceptorInfo> list = this.interceptorRequestInfos;
        if (list != null) {
            for (InterceptorInfo interceptorInfo : list) {
                InterceptorInfo interceptorInfo2 = new InterceptorInfo(interceptorInfo.name, interceptorInfo.start);
                interceptorInfo2.end = interceptorInfo.end;
                interceptorInfo2.exception_name = interceptorInfo.exception_name;
                retrofitMetrics.interceptorRequestInfos.add(interceptorInfo2);
            }
        }
        List<InterceptorInfo> list2 = this.interceptorResponseInfos;
        if (list2 != null) {
            for (InterceptorInfo interceptorInfo3 : list2) {
                InterceptorInfo interceptorInfo4 = new InterceptorInfo(interceptorInfo3.name, interceptorInfo3.start);
                interceptorInfo4.end = interceptorInfo3.end;
                interceptorInfo4.exception_name = interceptorInfo3.exception_name;
                retrofitMetrics.interceptorResponseInfos.add(interceptorInfo4);
            }
        }
        retrofitMetrics.hasNetworkKernelLog = this.hasNetworkKernelLog;
        retrofitMetrics.hasRetrofitLog = this.hasRetrofitLog;
        retrofitMetrics.requestInfo = this.requestInfo;
        retrofitMetrics.retrofit = this.retrofit.deepCopy();
        retrofitMetrics.networklib = this.networklib.deepCopy();
    }

    public void ResetNetworkLibMetrics() {
        this.networklib = new NetworkLibLayerMetrics();
    }
}
