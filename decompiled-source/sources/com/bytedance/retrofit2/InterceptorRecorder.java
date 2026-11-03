package com.bytedance.retrofit2;

import com.bytedance.frameworks.baselib.network.http.a;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.NoSuchElementException;
import org.json.JSONArray;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class InterceptorRecorder {
    public Deque<LogEntry> interceptorLogStack = new ArrayDeque();
    public Deque<LogEntry> interceptorLogTrace = new ArrayDeque();

    /* loaded from: classes2.dex */
    public static class LogEntry {
        public boolean isInternalInterceptor;
        public String name;
        public long reqStartNs;
        public long reqEndNs = -1;
        public long rspStartNs = -1;
        public long rspEndNs = -1;
        public String exception_name = null;

        /* JADX INFO: Access modifiers changed from: package-private */
        public LogEntry(String str, boolean z, long j2) {
            this.reqStartNs = -1L;
            this.name = str;
            this.isInternalInterceptor = z;
            this.reqStartNs = j2;
        }

        public LogEntry deepCopy() {
            LogEntry logEntry = new LogEntry(this.name, this.isInternalInterceptor, this.reqStartNs);
            logEntry.reqEndNs = this.reqEndNs;
            logEntry.rspStartNs = this.rspStartNs;
            logEntry.rspEndNs = this.rspEndNs;
            logEntry.exception_name = this.exception_name;
            return logEntry;
        }
    }

    public void push(LogEntry logEntry) {
        this.interceptorLogStack.push(logEntry);
    }

    public LogEntry peek() {
        return this.interceptorLogStack.peek();
    }

    public LogEntry pop() {
        try {
            LogEntry pop = this.interceptorLogStack.pop();
            this.interceptorLogTrace.push(pop);
            return pop;
        } catch (NoSuchElementException unused) {
            return null;
        }
    }

    public int size() {
        return this.interceptorLogStack.size();
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        new HashMap();
        long j2 = 0;
        long j3 = 0;
        for (LogEntry logEntry : this.interceptorLogTrace) {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("name", logEntry.name);
            long calculateDurationUs = TTMetricsUtil.calculateDurationUs(logEntry.reqStartNs, logEntry.reqEndNs);
            if (calculateDurationUs > 0) {
                j2 += calculateDurationUs;
            }
            jSONObject2.put("req", calculateDurationUs);
            long calculateDurationUs2 = TTMetricsUtil.calculateDurationUs(logEntry.rspStartNs, logEntry.rspEndNs);
            if (calculateDurationUs2 > 0) {
                j3 += calculateDurationUs2;
            }
            jSONObject2.put("rsp", calculateDurationUs2);
            if (logEntry.exception_name != null) {
                jSONObject2.put(a.f7601f, logEntry.exception_name);
            }
            jSONArray.put(jSONObject2);
        }
        jSONObject.put("detail", jSONArray);
        jSONObject.put("req_total", j2);
        jSONObject.put("rsp_total", j3);
        return jSONObject;
    }

    public InterceptorRecorder deepCopy() {
        InterceptorRecorder interceptorRecorder = new InterceptorRecorder();
        interceptorRecorder.interceptorLogStack = new ArrayDeque();
        for (LogEntry logEntry : this.interceptorLogStack) {
            interceptorRecorder.interceptorLogStack.push(logEntry.deepCopy());
        }
        interceptorRecorder.interceptorLogTrace = new ArrayDeque();
        for (LogEntry logEntry2 : this.interceptorLogTrace) {
            interceptorRecorder.interceptorLogTrace.push(logEntry2.deepCopy());
        }
        return interceptorRecorder;
    }
}
