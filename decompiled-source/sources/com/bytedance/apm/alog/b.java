package com.bytedance.apm.alog;

import com.bytedance.apm.util.ag;
import org.json.JSONObject;
/* compiled from: AlogMonitor.java */
/* loaded from: classes.dex */
public class b {
    public static final String ALOG_RESULT = "success";
    public static final String ALOG_RESULT_CODE = "code";
    public static final int ALOG_RESULT_CODE_CONTEXT = 0;
    public static final int ALOG_RESULT_CODE_FILE_NULL = 2;
    public static final int ALOG_RESULT_CODE_IO_FAIL = 8;
    public static final int ALOG_RESULT_CODE_JSON_FAIL = 7;
    public static final int ALOG_RESULT_CODE_NET_FAIL = 6;
    public static final int ALOG_RESULT_CODE_NOT_ALLOW_ALOG = 9;
    public static final int ALOG_RESULT_CODE_NO_ALOG = 4;
    public static final int ALOG_RESULT_CODE_NO_CONFIG_FILE = 1;
    public static final int ALOG_RESULT_CODE_NO_FLUSH = 3;
    public static final int ALOG_RESULT_CODE_NO_PARAM = 5;
    public static final int ALOG_RESULT_CODE_SUCCESS = -1;
    public static final String ALOG_RESULT_EXCEPTION = "exception";
    public static final String ALOG_RESULT_EXCEPTION_OBJECT = "exception_object";
    public static final String ALOG_RESULT_INFO = "info";
    public static final String ALOG_UPLOAD = "AlogUploadTag";

    /* renamed from: a  reason: collision with root package name */
    public static final int f373a = 10;

    /* renamed from: b  reason: collision with root package name */
    public static final int f374b = 11;

    /* renamed from: c  reason: collision with root package name */
    public static final int f375c = 12;

    public static void log(String str, Throwable th) {
        if (com.bytedance.apm.c.isDebugMode()) {
            String[] strArr = new String[2];
            strArr[0] = str;
            strArr[1] = th != null ? th.getMessage() : "";
            com.bytedance.apm.logging.e.i(ALOG_UPLOAD, strArr);
        }
    }

    public static void log(String str) {
        log(str, null);
    }

    public static JSONObject getResult(boolean z, int i2, Exception exc, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        try {
            jSONObject.put("success", z);
            jSONObject.put("code", i2);
            if (exc != null) {
                jSONObject.put("exception", exc.getMessage() + ag.getStack(exc));
                jSONObject.put(ALOG_RESULT_EXCEPTION_OBJECT, exc);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }
}
