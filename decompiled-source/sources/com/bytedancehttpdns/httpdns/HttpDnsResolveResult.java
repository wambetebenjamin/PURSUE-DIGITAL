package com.bytedancehttpdns.httpdns;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.bytedancehttpdns.httpdns.util.WeakHandler;
/* loaded from: classes8.dex */
public class HttpDnsResolveResult {
    public static final String HTTPDNS_RESULT_HOST_KEY = "httpdns_resolve_result";
    private static final int HTTPDNS_RESULT_REMOVE_TIME = 5;
    public static final int MSG_REMOVE_HTTPDNS_RESOLVE_RESULT = 21;
    private final int mErrorCode;
    private final Handler mHandler = new WeakHandler(HttpDns.getService().getHttpDnsHandler().getLooper(), HttpDns.getService());
    private final String mHost;

    public HttpDnsResolveResult(String str, int i2) {
        this.mHost = str;
        this.mErrorCode = i2;
        addHttpDnsResultRemoveTask();
    }

    private void addHttpDnsResultRemoveTask() {
        Message obtain = Message.obtain();
        obtain.obj = this;
        obtain.what = 21;
        Bundle bundle = new Bundle();
        bundle.putString(HTTPDNS_RESULT_HOST_KEY, this.mHost);
        obtain.setData(bundle);
        this.mHandler.sendMessageDelayed(obtain, 5000L);
    }

    public void removeHttpDnsResultRemoveTask() {
        if (this.mHandler.hasMessages(21, this)) {
            this.mHandler.removeMessages(21, this);
        }
    }

    public int getResult() {
        return this.mErrorCode;
    }
}
