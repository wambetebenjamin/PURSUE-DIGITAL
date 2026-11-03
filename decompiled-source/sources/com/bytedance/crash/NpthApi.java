package com.bytedance.crash;

import java.io.File;
import java.util.Map;
import org.json.JSONObject;
/* loaded from: classes2.dex */
public class NpthApi {

    /* renamed from: a  reason: collision with root package name */
    private static NpthApi f6085a;

    /* renamed from: b  reason: collision with root package name */
    private static RetraceDataCallback f6086b;

    /* renamed from: c  reason: collision with root package name */
    private static IANRInfoCallback f6087c;

    /* loaded from: classes2.dex */
    public interface CustomDataCallback {
        Map<String, String> getData();
    }

    /* loaded from: classes2.dex */
    public interface IANRInfoCallback {
        File a();
    }

    /* loaded from: classes2.dex */
    public interface RetraceDataCallback {
        JSONObject getData();
    }

    protected void a() {
    }

    protected void a(CustomDataCallback customDataCallback) {
    }

    protected void a(CustomDataCallback customDataCallback, String str) {
    }

    protected void a(IANRInfoCallback iANRInfoCallback) {
    }

    protected void a(String str) {
    }

    protected void a(String str, String str2) {
    }

    protected void a(Map<String, String> map) {
    }

    protected String b() {
        return "";
    }

    protected void b(String str, String str2) {
    }

    protected void c() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NpthApi() {
        f6085a = this;
        IANRInfoCallback iANRInfoCallback = f6087c;
        if (iANRInfoCallback != null) {
            a(iANRInfoCallback);
        }
    }

    public static void startMonitor() {
        NpthApi npthApi = f6085a;
        if (npthApi != null) {
            npthApi.a();
        }
    }

    public static void addTags(Map<String, String> map) {
        NpthApi npthApi = f6085a;
        if (npthApi != null) {
            npthApi.a(map);
        }
    }

    public static void addCustomData(CustomDataCallback customDataCallback) {
        NpthApi npthApi = f6085a;
        if (npthApi != null) {
            npthApi.a(customDataCallback);
        }
    }

    public static void b(CustomDataCallback customDataCallback, String str) {
        NpthApi npthApi = f6085a;
        if (npthApi == null || customDataCallback == null) {
            return;
        }
        npthApi.a(customDataCallback, str);
    }

    public static void b(IANRInfoCallback iANRInfoCallback) {
        NpthApi npthApi = f6085a;
        if (npthApi != null) {
            npthApi.a(iANRInfoCallback);
        } else {
            f6087c = iANRInfoCallback;
        }
    }

    public static void uploadKilledHistory() {
        NpthApi npthApi = f6085a;
        if (npthApi != null) {
            npthApi.c();
        }
    }

    public static void setRetraceData(RetraceDataCallback retraceDataCallback) {
        f6086b = retraceDataCallback;
    }

    public static JSONObject getRetraceParams() {
        RetraceDataCallback retraceDataCallback = f6086b;
        if (retraceDataCallback == null) {
            return null;
        }
        return retraceDataCallback.getData();
    }

    public static String getByTraceID() {
        NpthApi npthApi = f6085a;
        return npthApi == null ? "" : npthApi.b();
    }

    public static void checkInnerSoFile(String str) {
        NpthApi npthApi = f6085a;
        if (npthApi == null) {
            return;
        }
        npthApi.a(str);
    }

    public static void checkInnerSoFile(String str, String str2) {
        NpthApi npthApi = f6085a;
        if (npthApi == null) {
            return;
        }
        npthApi.b(str, str2);
    }

    public static void registerSDKVersion(String str, String str2) {
        NpthApi npthApi = f6085a;
        if (npthApi == null) {
            return;
        }
        npthApi.a(str, str2);
    }
}
