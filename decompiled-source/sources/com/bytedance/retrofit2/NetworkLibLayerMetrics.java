package com.bytedance.retrofit2;

import com.bytedance.ttgame.channelapi.monitor.MonitorConstants;
import java.util.ArrayDeque;
import java.util.Deque;
import org.json.JSONException;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class NetworkLibLayerMetrics {
    public static int BIZ_SPAN_MASK = 64;
    public static int EVENT_TIMESTAMP_MASK = 32;
    public static int IMPORTANT_INNER_SPAN_MASK = 128;
    public static int MISC_MASK = 256;
    private static volatile int reportDetail = -1;
    public EventTimestamp eventTs = new EventTimestamp();
    public BizSpan bizSpan = new BizSpan();
    public ImportantInnerSpan innerSpan = new ImportantInnerSpan();
    public ImportantKernelSpan kernelSpan = new ImportantKernelSpan();
    public Misc misc = new Misc();
    public String networkKernelRawRequestLog = "";

    public static void setReportDetail(int i2) {
        reportDetail = i2;
    }

    /* loaded from: classes2.dex */
    public static class EventTimestamp {
        public long prepareConnectionStart = -1;
        public long prepareConnectionEnd = -1;
        public long executeConnectionStart = -1;
        public long executeConnectionEnd = -1;
        public long processResponseBodyStart = -1;
        public long processResponseBodyEnd = -1;
        public long firstReadResponseBodyStart = -1;
        public long latestReadResponseBodyEnd = -1;

        public JSONObject toJson() {
            if ((NetworkLibLayerMetrics.reportDetail & NetworkLibLayerMetrics.EVENT_TIMESTAMP_MASK) == 0) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("prp_conn_s", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.prepareConnectionStart)));
            jSONObject.put("prp_conn_e", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.prepareConnectionEnd)));
            jSONObject.put("exe_conn_s", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.executeConnectionStart)));
            jSONObject.put("exe_conn_e", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.executeConnectionEnd)));
            jSONObject.put("prc_rsp_bdy_s", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.processResponseBodyStart)));
            jSONObject.put("prc_rsp_bdy_e", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.processResponseBodyEnd)));
            jSONObject.put("frs_rd_rsp_bdy_s", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.firstReadResponseBodyStart)));
            jSONObject.put("lst_rd_rsp_bdy_e", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.latestReadResponseBodyEnd)));
            return jSONObject;
        }

        public EventTimestamp deepCopy() {
            EventTimestamp eventTimestamp = new EventTimestamp();
            eventTimestamp.prepareConnectionStart = this.prepareConnectionStart;
            eventTimestamp.prepareConnectionEnd = this.prepareConnectionEnd;
            eventTimestamp.executeConnectionStart = this.executeConnectionStart;
            eventTimestamp.executeConnectionEnd = this.executeConnectionEnd;
            eventTimestamp.processResponseBodyStart = this.processResponseBodyStart;
            eventTimestamp.processResponseBodyEnd = this.processResponseBodyEnd;
            eventTimestamp.firstReadResponseBodyStart = this.firstReadResponseBodyStart;
            eventTimestamp.latestReadResponseBodyEnd = this.latestReadResponseBodyEnd;
            return eventTimestamp;
        }
    }

    /* loaded from: classes2.dex */
    public static class BizSpan {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private long bizCallbackStart = -1;
        public long bodyEncrypt = -1;
        public long onTryInit = -1;
        public long tryInitCookieManager = -1;
        public long bdTuringDuration = -1;
        public long accountRetryDuration = -1;
        public long rotationRetryAccumulatedDuration = -1;
        public long waitReadResponseBody = -1;
        public long readResponseBodyInterval = -1;
        public long inputStreamProcessDuration = -1;

        public void recordCallbackStart() {
            this.bizCallbackStart = System.nanoTime();
        }

        public long computeDurationNs() {
            long nanoTime = System.nanoTime() - this.bizCallbackStart;
            this.bizCallbackStart = -1L;
            return nanoTime;
        }

        public JSONObject toJson() {
            if ((NetworkLibLayerMetrics.reportDetail & NetworkLibLayerMetrics.BIZ_SPAN_MASK) == 0) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("bdy_encrypt", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.bodyEncrypt)));
            jSONObject.put("on_try_init", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.onTryInit)));
            jSONObject.put("init_cookie", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.tryInitCookieManager)));
            jSONObject.put("bd_turing", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.bdTuringDuration)));
            jSONObject.put("acnt_rty", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.accountRetryDuration)));
            jSONObject.put("rot_rty", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.rotationRetryAccumulatedDuration)));
            jSONObject.put("wait_rd_rsp_bdy", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.waitReadResponseBody)));
            jSONObject.put("rd_rsp_bdy_intvl", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.readResponseBodyInterval)));
            jSONObject.put("input_stream_prc", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.inputStreamProcessDuration)));
            return jSONObject;
        }

        public BizSpan deepCopy() {
            BizSpan bizSpan = new BizSpan();
            bizSpan.bizCallbackStart = this.bizCallbackStart;
            bizSpan.bodyEncrypt = this.bodyEncrypt;
            bizSpan.onTryInit = this.onTryInit;
            bizSpan.tryInitCookieManager = this.tryInitCookieManager;
            bizSpan.bdTuringDuration = this.bdTuringDuration;
            bizSpan.accountRetryDuration = this.accountRetryDuration;
            bizSpan.rotationRetryAccumulatedDuration = this.rotationRetryAccumulatedDuration;
            bizSpan.waitReadResponseBody = this.waitReadResponseBody;
            bizSpan.readResponseBodyInterval = this.readResponseBodyInterval;
            bizSpan.inputStreamProcessDuration = this.inputStreamProcessDuration;
            return bizSpan;
        }
    }

    /* loaded from: classes2.dex */
    public static class ImportantInnerSpan {
        private Deque<Long> innerSpanStart = new ArrayDeque();
        public long queryFilter = -1;
        public long constructConnection = -1;
        public long configureConnection = -1;
        public long reflection = -1;
        public long executeConnection = -1;
        public long constructConfigureAndRetryConnection = -1;
        public long processResponseBody = -1;
        public long postProcessBody = -1;
        public long readResponseBody = -1;

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
            if ((NetworkLibLayerMetrics.reportDetail & NetworkLibLayerMetrics.IMPORTANT_INNER_SPAN_MASK) == 0) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("qry_flt", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.queryFilter)));
            jSONObject.put("last_cons_conn", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.constructConnection)));
            jSONObject.put("last_conf_conn", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.configureConnection)));
            jSONObject.put("last_exe_conn", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.executeConnection)));
            jSONObject.put("cons_conf_n_rty_conn", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.constructConfigureAndRetryConnection)));
            jSONObject.put("prc_rsp_bdy", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.processResponseBody)));
            jSONObject.put("post_prc_rsp_bdy", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.postProcessBody)));
            jSONObject.put("total_rd_rsp_bdy", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.readResponseBody)));
            jSONObject.put("reflect", String.valueOf(TTMetricsUtil.nanoSecondsToMicroSeconds(this.reflection)));
            return jSONObject;
        }

        public ImportantInnerSpan deepCopy() {
            ImportantInnerSpan importantInnerSpan = new ImportantInnerSpan();
            importantInnerSpan.queryFilter = this.queryFilter;
            importantInnerSpan.constructConnection = this.constructConnection;
            importantInnerSpan.configureConnection = this.configureConnection;
            importantInnerSpan.executeConnection = this.executeConnection;
            importantInnerSpan.constructConfigureAndRetryConnection = this.constructConfigureAndRetryConnection;
            importantInnerSpan.processResponseBody = this.processResponseBody;
            importantInnerSpan.postProcessBody = this.postProcessBody;
            importantInnerSpan.readResponseBody = this.readResponseBody;
            importantInnerSpan.reflection = this.reflection;
            if (!this.innerSpanStart.isEmpty()) {
                importantInnerSpan.innerSpanStart = new ArrayDeque();
                for (Long l : this.innerSpanStart) {
                    importantInnerSpan.innerSpanStart.push(l);
                }
            }
            return importantInnerSpan;
        }
    }

    /* loaded from: classes2.dex */
    public static class ImportantKernelSpan {
        public long dns = -1;
        public long tcpConnect = -1;
        public long tls = -1;
        public long send = -1;
        public long push = -1;
        public long ttfb = -1;
        public long receiveBody = -1;
        public long total = -1;
        public long upperAddCookieDurationUs = -1;
        public long upperSaveCookieDurationUs = -1;

        public JSONObject toJson() {
            if ((NetworkLibLayerMetrics.reportDetail & NetworkLibLayerMetrics.IMPORTANT_INNER_SPAN_MASK) == 0) {
                return null;
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("add_cookie", String.valueOf(this.upperAddCookieDurationUs));
            jSONObject.put("save_cookie", String.valueOf(this.upperSaveCookieDurationUs));
            return jSONObject;
        }

        public ImportantKernelSpan deepCopy() {
            ImportantKernelSpan importantKernelSpan = new ImportantKernelSpan();
            importantKernelSpan.dns = this.dns;
            importantKernelSpan.tcpConnect = this.tcpConnect;
            importantKernelSpan.tls = this.tls;
            importantKernelSpan.send = this.send;
            importantKernelSpan.push = this.push;
            importantKernelSpan.ttfb = this.ttfb;
            importantKernelSpan.receiveBody = this.receiveBody;
            importantKernelSpan.total = this.total;
            importantKernelSpan.upperAddCookieDurationUs = this.upperAddCookieDurationUs;
            importantKernelSpan.upperSaveCookieDurationUs = this.upperSaveCookieDurationUs;
            return importantKernelSpan;
        }
    }

    /* loaded from: classes2.dex */
    public static class Misc {
        public boolean isConcurrentRequest = false;
        public int requestCookieSourceType = -1;
        public JSONObject rotationHostRetryInfo = null;
        public long responseBodyReadTimes = -1;
        public boolean readResponseBodyInternal = false;
        public Object requestExtraInfo = null;

        public JSONObject toJson() {
            return new JSONObject();
        }

        public Misc deepCopy() {
            Misc misc = new Misc();
            misc.isConcurrentRequest = this.isConcurrentRequest;
            misc.requestCookieSourceType = this.requestCookieSourceType;
            misc.responseBodyReadTimes = this.responseBodyReadTimes;
            misc.readResponseBodyInternal = this.readResponseBodyInternal;
            misc.requestExtraInfo = this.requestExtraInfo;
            try {
                if (this.rotationHostRetryInfo != null) {
                    misc.rotationHostRetryInfo = new JSONObject(this.rotationHostRetryInfo.toString());
                }
            } catch (JSONException unused) {
            }
            return misc;
        }
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("event_ts", this.eventTs.toJson());
        jSONObject.put("biz_span", this.bizSpan.toJson());
        jSONObject.put("inner_span", this.innerSpan.toJson());
        jSONObject.put(MonitorConstants.EVENT.MISC, this.misc.toJson());
        return jSONObject;
    }

    public NetworkLibLayerMetrics deepCopy() {
        NetworkLibLayerMetrics networkLibLayerMetrics = new NetworkLibLayerMetrics();
        networkLibLayerMetrics.eventTs = this.eventTs.deepCopy();
        networkLibLayerMetrics.bizSpan = this.bizSpan.deepCopy();
        networkLibLayerMetrics.innerSpan = this.innerSpan.deepCopy();
        networkLibLayerMetrics.kernelSpan = this.kernelSpan.deepCopy();
        networkLibLayerMetrics.misc = this.misc.deepCopy();
        networkLibLayerMetrics.networkKernelRawRequestLog = this.networkKernelRawRequestLog;
        return networkLibLayerMetrics;
    }
}
