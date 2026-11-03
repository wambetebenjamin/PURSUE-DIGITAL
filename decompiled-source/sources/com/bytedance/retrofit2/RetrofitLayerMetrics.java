package com.bytedance.retrofit2;

import com.bytedance.ttgame.channelapi.monitor.MonitorConstants;
import java.lang.ref.WeakReference;
import java.util.ArrayDeque;
import java.util.Deque;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class RetrofitLayerMetrics {
    public static int BIZ_SPAN_MASK = 2;
    public static int EVENT_TIMESTAMP_MASK = 1;
    public static int IMPORTANT_INNER_SPAN_MASK = 4;
    public static int INTERCEPTOR_MASK = 8;
    public static int MISC_MASK = 16;
    private static volatile int reportDetail = -1;
    public EventTimestamp eventTs = new EventTimestamp();
    public BizSpan bizSpan = new BizSpan();
    public ImportantInnerSpan innerSpan = new ImportantInnerSpan();
    InterceptorRecorder interceptorRecorder = new InterceptorRecorder();
    public Misc misc = new Misc();

    public static void setReportDetail(int i2) {
        reportDetail = i2;
    }

    public RetrofitLayerMetrics() {
        if (RetrofitMetrics.sAppStartTimeProvider != null) {
            this.eventTs.appStart = RetrofitMetrics.sAppStartTimeProvider.getAppStartCPUTimeStamp();
            this.eventTs.appStartTime = RetrofitMetrics.sAppStartTimeProvider.getAppStartWallClockTimeStamp();
        }
    }

    /* loaded from: classes2.dex */
    public static class EventTimestamp {
        public long appStart = -1;
        public long appStartTime = -1;
        public long cronetInitStart = -1;
        public long cronetInitEnd = -1;
        public long bizInvokeRetrofitRequestInterfaceStartTime = -1;
        public long bizInvokeRetrofitRequestInterfaceStart = -1;
        public long bizInvokeRetrofitRequestInterfaceEnd = -1;
        public long toRequestStart = -1;
        public long toRequestEnd = -1;
        public long requestRealStart = -1;
        public long ssHttpCallApiStart = -1;
        public long ssHttpCallApiStartTime = -1;
        public long ssHttpCallApiEnd = -1;
        public long requestInterceptorStart = -1;
        public long requestInterceptorEnd = -1;
        public long executeSsCallStart = -1;
        public long executeSsCallEnd = -1;
        public long responseInterceptorStart = -1;
        public long responseInterceptorEnd = -1;
        public long bizReceiveResponseHeader = -1;
        public long bizReceiveResponseHeaderTime = -1;
        public long requestEnd = -1;
        public long requestEndTime = -1;
        public long logReport = -1;

        public JSONObject toJson() {
            if ((RetrofitLayerMetrics.reportDetail & RetrofitLayerMetrics.EVENT_TIMESTAMP_MASK) == 0) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("app_start", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.appStart)));
            jSONObject.put("app_start_wc", String.valueOf(this.appStartTime));
            jSONObject.put("cronet_init_s", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.cronetInitStart)));
            jSONObject.put("cronet_init_e", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.cronetInitEnd)));
            jSONObject.put("create_hcall_s_wc", String.valueOf(this.bizInvokeRetrofitRequestInterfaceStartTime));
            jSONObject.put("create_hcall_s", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.bizInvokeRetrofitRequestInterfaceStart)));
            jSONObject.put("create_hcall_e", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.bizInvokeRetrofitRequestInterfaceEnd)));
            jSONObject.put("hcall_api_s", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.ssHttpCallApiStart)));
            jSONObject.put("hcall_api_s_ws", String.valueOf(this.ssHttpCallApiStartTime));
            jSONObject.put("hcall_api_e", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.ssHttpCallApiEnd)));
            jSONObject.put("req_real_s", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.requestRealStart)));
            jSONObject.put("req_itcpt_s", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.requestInterceptorStart)));
            jSONObject.put("req_itcpt_e", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.requestInterceptorEnd)));
            jSONObject.put("sscall_s", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.executeSsCallStart)));
            jSONObject.put("sscall_e", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.executeSsCallEnd)));
            jSONObject.put("rsp_itcpt_s", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.responseInterceptorStart)));
            jSONObject.put("rsp_itcpt_e", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.responseInterceptorEnd)));
            jSONObject.put("biz_rcv_rsp_hdr_wc", String.valueOf(this.bizReceiveResponseHeaderTime));
            jSONObject.put("biz_rcv_rsp_hdr", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.bizReceiveResponseHeader)));
            jSONObject.put("req_e_wc", String.valueOf(this.requestEndTime));
            jSONObject.put("req_e", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.requestEnd)));
            jSONObject.put("log_rpt", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.logReport)));
            return jSONObject;
        }

        public EventTimestamp deepCopy() {
            EventTimestamp eventTimestamp = new EventTimestamp();
            eventTimestamp.appStart = this.appStart;
            eventTimestamp.appStartTime = this.appStartTime;
            eventTimestamp.cronetInitStart = this.cronetInitStart;
            eventTimestamp.cronetInitEnd = this.cronetInitEnd;
            eventTimestamp.bizInvokeRetrofitRequestInterfaceStartTime = this.bizInvokeRetrofitRequestInterfaceStartTime;
            eventTimestamp.bizInvokeRetrofitRequestInterfaceStart = this.bizInvokeRetrofitRequestInterfaceStart;
            eventTimestamp.bizInvokeRetrofitRequestInterfaceEnd = this.bizInvokeRetrofitRequestInterfaceEnd;
            eventTimestamp.ssHttpCallApiStartTime = this.ssHttpCallApiStartTime;
            eventTimestamp.requestRealStart = this.requestRealStart;
            eventTimestamp.ssHttpCallApiStart = this.ssHttpCallApiStart;
            eventTimestamp.ssHttpCallApiEnd = this.ssHttpCallApiEnd;
            eventTimestamp.requestInterceptorStart = this.requestInterceptorStart;
            eventTimestamp.requestInterceptorEnd = this.requestInterceptorEnd;
            eventTimestamp.executeSsCallStart = this.executeSsCallStart;
            eventTimestamp.executeSsCallEnd = this.executeSsCallEnd;
            eventTimestamp.responseInterceptorStart = this.responseInterceptorStart;
            eventTimestamp.responseInterceptorEnd = this.responseInterceptorEnd;
            eventTimestamp.bizReceiveResponseHeader = this.bizReceiveResponseHeader;
            eventTimestamp.bizReceiveResponseHeaderTime = this.bizReceiveResponseHeaderTime;
            eventTimestamp.requestEnd = this.requestEnd;
            eventTimestamp.requestEndTime = this.requestEndTime;
            eventTimestamp.logReport = this.logReport;
            return eventTimestamp;
        }
    }

    /* loaded from: classes2.dex */
    public static class BizSpan {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private long bizCallbackStart = -1;
        public long waitSsHttpCallApiExecute = -1;
        public long monitorApiHttpBeforeFilter = -1;
        public long filterUrl = -1;
        public long monitorApiHttpAfterFilter = -1;
        public long addCommonParam = -1;
        public long encryptRequest = -1;
        public long requestVerify = -1;
        public long commandListener = -1;
        public long checkReqTicket = -1;
        public long postCdnCacheVerify = -1;
        public long bizRequestInterceptor = -1;
        public long bizResponseInterceptor = -1;
        public long enqueueCallback = -1;

        public void recordCallbackStart() {
            this.bizCallbackStart = System.nanoTime();
        }

        public long computeDurationNs() {
            long nanoTime = System.nanoTime() - this.bizCallbackStart;
            this.bizCallbackStart = -1L;
            return nanoTime;
        }

        public JSONObject toJson() {
            if ((RetrofitLayerMetrics.reportDetail & RetrofitLayerMetrics.BIZ_SPAN_MASK) == 0) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("wait_hcall_exe", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.waitSsHttpCallApiExecute)));
            jSONObject.put("mon_http_bfr_flt", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.monitorApiHttpBeforeFilter)));
            jSONObject.put("flt_url", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.filterUrl)));
            jSONObject.put("mon_http_aft_flt", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.monitorApiHttpAfterFilter)));
            jSONObject.put("add_com_prm", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.addCommonParam)));
            jSONObject.put("encrypt_req", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.encryptRequest)));
            jSONObject.put("req_vfy", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.requestVerify)));
            jSONObject.put("cmd_lst", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.commandListener)));
            jSONObject.put("chk_req_tkt", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.checkReqTicket)));
            jSONObject.put("post_cdn_cache_vfy", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.postCdnCacheVerify)));
            jSONObject.put("biz_req_itcpt", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.bizRequestInterceptor)));
            jSONObject.put("biz_rsp_itcpt", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.bizResponseInterceptor)));
            jSONObject.put("hcall_async_cb", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.enqueueCallback)));
            return jSONObject;
        }

        public BizSpan deepCopy() {
            BizSpan bizSpan = new BizSpan();
            bizSpan.bizCallbackStart = this.bizCallbackStart;
            bizSpan.waitSsHttpCallApiExecute = this.waitSsHttpCallApiExecute;
            bizSpan.monitorApiHttpBeforeFilter = this.monitorApiHttpBeforeFilter;
            bizSpan.filterUrl = this.filterUrl;
            bizSpan.monitorApiHttpAfterFilter = this.monitorApiHttpAfterFilter;
            bizSpan.addCommonParam = this.addCommonParam;
            bizSpan.encryptRequest = this.encryptRequest;
            bizSpan.requestVerify = this.requestVerify;
            bizSpan.commandListener = this.commandListener;
            bizSpan.checkReqTicket = this.checkReqTicket;
            bizSpan.postCdnCacheVerify = this.postCdnCacheVerify;
            bizSpan.bizRequestInterceptor = this.bizRequestInterceptor;
            bizSpan.bizResponseInterceptor = this.bizResponseInterceptor;
            bizSpan.enqueueCallback = this.enqueueCallback;
            return bizSpan;
        }
    }

    /* loaded from: classes2.dex */
    public static class ImportantInnerSpan {
        private Deque<Long> innerSpanStart = new ArrayDeque();
        public long createRequestFactory = -1;
        public long createHttpServiceMethod = -1;
        public long toRequest = -1;
        public long reqDelay = -1;
        public long filterDupQuery = -1;
        public long genReqTicket = -1;
        public long preCdnCacheVerify = -1;
        public long addClientKey = -1;
        public long toSsResponse = -1;
        public long updateClientKey = -1;

        public void recordInnerSpanStart() {
            this.innerSpanStart.push(Long.valueOf(System.nanoTime()));
        }

        public long computeDurationNs() {
            try {
                return System.nanoTime() - this.innerSpanStart.pop().longValue();
            } catch (Exception e2) {
                e2.printStackTrace();
                return -1L;
            }
        }

        public JSONObject toJson() {
            if ((RetrofitLayerMetrics.reportDetail & RetrofitLayerMetrics.IMPORTANT_INNER_SPAN_MASK) == 0) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("crt_req_fact", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.createRequestFactory)));
            jSONObject.put("crt_http_svc_meth", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.createHttpServiceMethod)));
            jSONObject.put("to_retrofit_req", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.toRequest)));
            jSONObject.put("req_delay", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.reqDelay)));
            jSONObject.put("flt_dup_qry", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.filterDupQuery)));
            jSONObject.put("gen_req_tkt", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.genReqTicket)));
            jSONObject.put("pre_cdn_cache_vrf", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.preCdnCacheVerify)));
            jSONObject.put("add_cli_key", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.addClientKey)));
            jSONObject.put("to_ss_rsp", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.toSsResponse)));
            jSONObject.put("upd_cli_key", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.updateClientKey)));
            return jSONObject;
        }

        public ImportantInnerSpan deepCopy() {
            ImportantInnerSpan importantInnerSpan = new ImportantInnerSpan();
            if (!this.innerSpanStart.isEmpty()) {
                importantInnerSpan.innerSpanStart = new ArrayDeque();
                for (Long l : this.innerSpanStart) {
                    importantInnerSpan.innerSpanStart.push(l);
                }
            }
            importantInnerSpan.createRequestFactory = this.createRequestFactory;
            importantInnerSpan.createHttpServiceMethod = this.createHttpServiceMethod;
            importantInnerSpan.toRequest = this.toRequest;
            importantInnerSpan.filterDupQuery = this.filterDupQuery;
            importantInnerSpan.genReqTicket = this.genReqTicket;
            importantInnerSpan.preCdnCacheVerify = this.preCdnCacheVerify;
            importantInnerSpan.addClientKey = this.addClientKey;
            importantInnerSpan.toSsResponse = this.toSsResponse;
            importantInnerSpan.updateClientKey = this.updateClientKey;
            return importantInnerSpan;
        }
    }

    /* loaded from: classes2.dex */
    public static class Misc {
        public boolean executeSsCallSuccess = false;
        public int callServerExecutedTimes = 0;
        public transient Throwable interceptorResponseThrowable = null;
        public int blockingQueueSize = 0;
        public transient WeakReference<Throwable> requestException = null;
        public long requestStartAppResumeState = -1;
        public long requestEndAppResumeState = -1;

        public JSONObject toJson() {
            if ((RetrofitLayerMetrics.reportDetail & RetrofitLayerMetrics.MISC_MASK) == 0) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("call_svr_times", this.callServerExecutedTimes);
            jSONObject.put("app_rsm_ssp_req_s", this.requestStartAppResumeState);
            jSONObject.put("app_rsm_ssp_req_e", this.requestEndAppResumeState);
            return jSONObject;
        }

        public Misc deepCopy() {
            Misc misc = new Misc();
            misc.executeSsCallSuccess = this.executeSsCallSuccess;
            misc.callServerExecutedTimes = this.callServerExecutedTimes;
            misc.blockingQueueSize = this.blockingQueueSize;
            misc.requestStartAppResumeState = this.requestStartAppResumeState;
            misc.requestEndAppResumeState = this.requestEndAppResumeState;
            if (this.interceptorResponseThrowable != null) {
                Throwable th = new Throwable(this.interceptorResponseThrowable.getMessage());
                misc.interceptorResponseThrowable = th;
                th.setStackTrace(this.interceptorResponseThrowable.getStackTrace());
            }
            WeakReference<Throwable> weakReference = this.requestException;
            if (weakReference != null && weakReference.get() != null) {
                WeakReference<Throwable> weakReference2 = new WeakReference<>(new Throwable(this.requestException.get().getMessage()));
                misc.requestException = weakReference2;
                weakReference2.get().setStackTrace(this.requestException.get().getStackTrace());
            }
            return misc;
        }
    }

    public long getRequestDurationMs() {
        return this.eventTs.requestEndTime - this.eventTs.ssHttpCallApiStartTime;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("event_ts", this.eventTs.toJson());
        jSONObject.put("biz_span", this.bizSpan.toJson());
        jSONObject.put("inner_span", this.innerSpan.toJson());
        if ((reportDetail & INTERCEPTOR_MASK) != 0) {
            jSONObject.put("intcptr", this.interceptorRecorder.toJson());
        }
        jSONObject.put(MonitorConstants.EVENT.MISC, this.misc.toJson());
        return jSONObject;
    }

    public RetrofitLayerMetrics deepCopy() {
        RetrofitLayerMetrics retrofitLayerMetrics = new RetrofitLayerMetrics();
        retrofitLayerMetrics.eventTs = this.eventTs.deepCopy();
        retrofitLayerMetrics.bizSpan = this.bizSpan.deepCopy();
        retrofitLayerMetrics.innerSpan = this.innerSpan.deepCopy();
        retrofitLayerMetrics.interceptorRecorder = this.interceptorRecorder.deepCopy();
        retrofitLayerMetrics.misc = this.misc.deepCopy();
        return retrofitLayerMetrics;
    }
}
