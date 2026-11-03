package com.bytedance.retrofit2.ttnet;

import java.io.IOException;
/* loaded from: classes2.dex */
public class TTNetExceptionStorage extends IOException {
    private static final long serialVersionUID = -1606114814728652693L;
    public Object infoObj;
    public boolean reportMonitorError;
    public boolean reportMonitorOk;
    public String traceCode;
    public String url;

    public TTNetExceptionStorage() {
        this.reportMonitorOk = false;
        this.reportMonitorError = false;
        this.url = "empty url";
        this.traceCode = "empty traceCode";
        this.infoObj = null;
    }

    public TTNetExceptionStorage(String str) {
        super(str);
        this.reportMonitorOk = false;
        this.reportMonitorError = false;
        this.url = "empty url";
        this.traceCode = "empty traceCode";
        this.infoObj = null;
    }

    public TTNetExceptionStorage(String str, Throwable th) {
        super(str, th);
        this.reportMonitorOk = false;
        this.reportMonitorError = false;
        this.url = "empty url";
        this.traceCode = "empty traceCode";
        this.infoObj = null;
    }

    public void setInfo(boolean z, boolean z2, String str, String str2, Object obj) {
        this.reportMonitorOk = z;
        this.reportMonitorError = z2;
        this.url = str;
        this.traceCode = str2;
        this.infoObj = obj;
    }

    public String getTraceCode() {
        return this.traceCode;
    }

    public static TTNetExceptionStorage wrapIfNeeded(Exception exc, String str, String str2, Object obj) {
        if (exc instanceof TTNetExceptionStorage) {
            return (TTNetExceptionStorage) exc;
        }
        TTNetExceptionStorage tTNetExceptionStorage = new TTNetExceptionStorage(exc.getMessage(), exc);
        tTNetExceptionStorage.setInfo(false, true, str, str2, obj);
        return tTNetExceptionStorage;
    }
}
