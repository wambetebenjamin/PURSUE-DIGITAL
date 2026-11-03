package com.bytedance.apm.agent.instrumentation.transaction;

import com.bytedance.apm.util.am;
import org.json.JSONException;
import org.json.JSONObject;
/* compiled from: TransactionState.java */
/* loaded from: classes.dex */
public final class b {

    /* renamed from: a  reason: collision with root package name */
    private static final com.bytedance.apm.agent.logging.a f291a = com.bytedance.apm.agent.logging.b.getAgentLog();

    /* renamed from: b  reason: collision with root package name */
    private String f292b;

    /* renamed from: d  reason: collision with root package name */
    private long f294d;

    /* renamed from: e  reason: collision with root package name */
    private long f295e;

    /* renamed from: f  reason: collision with root package name */
    private int f296f;

    /* renamed from: g  reason: collision with root package name */
    private int f297g;

    /* renamed from: h  reason: collision with root package name */
    private long f298h;

    /* renamed from: i  reason: collision with root package name */
    private long f299i;
    private String k;
    private String m;
    private com.bytedance.apm.agent.instrumentation.transaction.a o;

    /* renamed from: c  reason: collision with root package name */
    private String f293c = "unknown";

    /* renamed from: j  reason: collision with root package name */
    private String f300j = "unknown";
    private a n = a.READY;
    private JSONObject l = new JSONObject();

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TransactionState.java */
    /* loaded from: classes.dex */
    public enum a {
        READY,
        SENT,
        COMPLETE
    }

    public long getStartTime() {
        return this.f294d;
    }

    public void setStartTime(long j2) {
        this.f294d = j2;
    }

    public void setUrl(String str) {
        if (this.m == null) {
            this.m = str;
        }
        String sanitizeUrl = am.sanitizeUrl(str);
        if (sanitizeUrl == null) {
            return;
        }
        if (!isSent()) {
            this.f292b = sanitizeUrl;
        } else {
            f291a.warning("setUrl(...) called on TransactionState in " + this.n.toString() + " state");
        }
    }

    public String getUrl() {
        return this.f292b;
    }

    public void setCarrier(String str) {
        if (!isSent()) {
            this.f293c = str;
        } else {
            f291a.warning("setCarrier(...) called on TransactionState in " + this.n.toString() + " state");
        }
    }

    public void setMethod(String str) {
        if (!isSent()) {
            this.k = str;
        } else {
            f291a.warning("setHttpMethod(...) called on TransactionState in " + this.n.toString() + " state");
        }
    }

    public void setWanType(String str) {
        if (!isSent()) {
            this.f300j = str;
        } else {
            f291a.warning("setWanType(...) called on TransactionState in " + this.n.toString() + " state");
        }
    }

    public void addAssistData(String str, String str2) {
        if (isComplete()) {
            f291a.warning("addAssistData(...) called on TransactionState in " + this.n.toString() + " state");
        }
        try {
            this.l.put(str, str2);
        } catch (JSONException e2) {
            f291a.error("Caught error while addAssistData: ", e2);
        }
    }

    public boolean isSent() {
        return this.n.ordinal() >= a.SENT.ordinal();
    }

    public boolean isComplete() {
        return this.n.ordinal() >= a.COMPLETE.ordinal();
    }

    public void setStatusCode(int i2) {
        if (!isComplete()) {
            this.f296f = i2;
            return;
        }
        if (this.f296f == 0 && i2 != 0) {
            this.f296f = i2;
        }
        f291a.warning("setStatusCode(...) called on TransactionState in " + this.n.toString() + " state");
    }

    public int getStatusCode() {
        return this.f296f;
    }

    public void setErrorCode(int i2) {
        if (!isComplete()) {
            this.f297g = i2;
            return;
        }
        com.bytedance.apm.agent.instrumentation.transaction.a aVar = this.o;
        if (aVar != null) {
            aVar.setErrorCode(i2);
        }
        f291a.warning("setErrorCode(...) called on TransactionState in " + this.n.toString() + " state");
    }

    public int getErrorCode() {
        return this.f297g;
    }

    public void setBytesSent(long j2) {
        if (!isComplete()) {
            this.f298h = j2;
            this.n = a.SENT;
            return;
        }
        f291a.warning("setBytesSent(...) called on TransactionState in " + this.n.toString() + " state");
    }

    public void setBytesReceived(long j2) {
        if (!isComplete()) {
            this.f299i = j2;
        } else {
            f291a.warning("setBytesReceived(...) called on TransactionState in " + this.n.toString() + " state");
        }
    }

    public long getReceivedBytes() {
        return this.f299i;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" StartTime ").append(String.valueOf(this.f294d)).append(" BytesReceived ").append(String.valueOf(this.f299i)).append(" BytesSent ").append(String.valueOf(this.f298h)).append(" Url ").append(this.f292b);
        return sb.toString();
    }

    public com.bytedance.apm.agent.instrumentation.transaction.a end() {
        addAssistData("startTime", this.f294d + "");
        if (!isComplete()) {
            this.n = a.COMPLETE;
            this.f295e = System.currentTimeMillis();
        }
        return a();
    }

    private com.bytedance.apm.agent.instrumentation.transaction.a a() {
        if (!isComplete()) {
            f291a.warning("toTransactionData() called on incomplete TransactionState");
        }
        if (this.f292b == null) {
            f291a.error("Attempted to convert TransactionData TransactionState instance with no URL into TransactionData TransactionData");
            return null;
        }
        if (this.o == null) {
            com.bytedance.apm.agent.instrumentation.transaction.a aVar = new com.bytedance.apm.agent.instrumentation.transaction.a(this.f292b, this.f293c, this.f295e - this.f294d, this.f296f, this.f297g, this.f298h, this.f299i, this.f300j, this.k, this.l);
            this.o = aVar;
            aVar.setRequestEnd(this.f295e);
            this.o.setErrorCode(this.f297g);
        }
        return this.o;
    }
}
