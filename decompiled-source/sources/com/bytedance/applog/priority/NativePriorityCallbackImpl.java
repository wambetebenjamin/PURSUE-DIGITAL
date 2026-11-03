package com.bytedance.applog.priority;

import android.webkit.ValueCallback;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
/* compiled from: NativePriorityCallbackImpl.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\f\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0018\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00070\u0006¢\u0006\u0002\u0010\nJ\u001e\u0010\u0015\u001a\u0004\u0018\u00010\t2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J$\u0010\u0015\u001a\u0004\u0018\u00010\t2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001a\u001a\u00020\b2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019J\n\u0010\u001b\u001a\u0004\u0018\u00010\u0017H\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u001dH\u0016J\b\u0010\u001f\u001a\u00020\u001dH\u0016J\u001c\u0010 \u001a\u00020!2\b\u0010\"\u001a\u0004\u0018\u00010\u00172\b\u0010#\u001a\u0004\u0018\u00010\u0017H\u0016J\u001a\u0010$\u001a\u00020!2\b\u0010%\u001a\u0004\u0018\u00010\u00172\u0006\u0010&\u001a\u00020'H\u0016JH\u0010(\u001a\u00020!2\b\u0010)\u001a\u0004\u0018\u00010\u00172\b\u0010*\u001a\u0004\u0018\u00010\u00172\u0006\u0010+\u001a\u00020'2\u0006\u0010,\u001a\u00020'2\b\u0010-\u001a\u0004\u0018\u00010\u00172\b\u0010.\u001a\u0004\u0018\u00010\u00172\u0006\u0010/\u001a\u00020\bH\u0016J\u001a\u00100\u001a\u00020!2\u0006\u00101\u001a\u00020'2\b\u00102\u001a\u0004\u0018\u00010\u0017H\u0016R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R#\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u00063"}, d2 = {"Lcom/bytedance/applog/priority/NativePriorityCallbackImpl;", "Lcom/bytedance/applog/priority/PriorityCallback;", "priorityCallback", "logger", "Lcom/bytedance/applog/priority/Logger;", "onHttpCallback", "Landroid/webkit/ValueCallback;", "Lkotlin/Pair;", "", "Lcom/bytedance/applog/priority/PriorityHttpResponse;", "(Lcom/bytedance/applog/priority/PriorityCallback;Lcom/bytedance/applog/priority/Logger;Landroid/webkit/ValueCallback;)V", "httpThreadPool", "Ljava/util/concurrent/ExecutorService;", "getHttpThreadPool", "()Ljava/util/concurrent/ExecutorService;", "getLogger", "()Lcom/bytedance/applog/priority/Logger;", "getOnHttpCallback", "()Landroid/webkit/ValueCallback;", "getPriorityCallback", "()Lcom/bytedance/applog/priority/PriorityCallback;", "doHttpPost", "url", "", "buffer", "", "reqId", "getHeaderJson", "isMonitorEnabled", "", "isNetworkAvailable", "isStageEnabled", "monitorError", "", "s", "stackInfo", "monitorIncreaseStats", "key", "count", "", "monitorStage", "stage", "priorityName", "groupId", "eventType", "event", "monitorId", "localTimeMs", "printLog", "level", "message", "priority_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class NativePriorityCallbackImpl implements PriorityCallback {
    private final ExecutorService httpThreadPool;
    private final f logger;
    private final ValueCallback<Pair<Long, PriorityHttpResponse>> onHttpCallback;
    private final PriorityCallback priorityCallback;

    @Override // com.bytedance.applog.priority.PriorityCallback
    public void printLog(int i2, String str) {
    }

    public NativePriorityCallbackImpl(PriorityCallback priorityCallback, f logger, ValueCallback<Pair<Long, PriorityHttpResponse>> onHttpCallback) {
        Intrinsics.checkNotNullParameter(priorityCallback, "priorityCallback");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(onHttpCallback, "onHttpCallback");
        this.priorityCallback = priorityCallback;
        this.logger = logger;
        this.onHttpCallback = onHttpCallback;
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(3);
        Intrinsics.checkNotNullExpressionValue(newFixedThreadPool, "newFixedThreadPool(3)");
        this.httpThreadPool = newFixedThreadPool;
    }

    public final PriorityCallback getPriorityCallback() {
        return this.priorityCallback;
    }

    public final f getLogger() {
        return this.logger;
    }

    public final ValueCallback<Pair<Long, PriorityHttpResponse>> getOnHttpCallback() {
        return this.onHttpCallback;
    }

    public final ExecutorService getHttpThreadPool() {
        return this.httpThreadPool;
    }

    @Override // com.bytedance.applog.priority.PriorityCallback
    public String getHeaderJson() {
        return this.priorityCallback.getHeaderJson();
    }

    @Override // com.bytedance.applog.priority.PriorityCallback
    public PriorityHttpResponse doHttpPost(String str, byte[] bArr) {
        this.logger.a("[NativePriorityCallbackImpl] native doHttpPost submitted with url: {}!", str);
        return null;
    }

    public final PriorityHttpResponse doHttpPost(final String str, final long j2, final byte[] bArr) {
        this.httpThreadPool.submit(new Runnable() { // from class: com.bytedance.applog.priority.NativePriorityCallbackImpl$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                NativePriorityCallbackImpl.m98doHttpPost$lambda0(NativePriorityCallbackImpl.this, j2, str, bArr);
            }
        });
        return new PriorityHttpResponse(200, "", "");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: doHttpPost$lambda-0  reason: not valid java name */
    public static final void m98doHttpPost$lambda0(NativePriorityCallbackImpl this$0, long j2, String str, byte[] bArr) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.logger.a("[NativePriorityCallbackImpl] native doHttpPost[reqId] submitted with req_id: {}!", Long.valueOf(j2));
        this$0.onHttpCallback.onReceiveValue(new Pair<>(Long.valueOf(j2), this$0.priorityCallback.doHttpPost(str, bArr)));
    }

    @Override // com.bytedance.applog.priority.PriorityCallback
    public boolean isNetworkAvailable() {
        return this.priorityCallback.isNetworkAvailable();
    }

    @Override // com.bytedance.applog.priority.PriorityCallback
    public void monitorIncreaseStats(String str, int i2) {
        this.priorityCallback.monitorIncreaseStats(str, i2);
    }

    @Override // com.bytedance.applog.priority.PriorityCallback
    public boolean isMonitorEnabled() {
        return this.priorityCallback.isMonitorEnabled();
    }

    @Override // com.bytedance.applog.priority.PriorityCallback
    public void monitorStage(String str, String str2, int i2, int i3, String str3, String str4, long j2) {
        this.priorityCallback.monitorStage(str, str2, i2, i3, str3, str4, j2);
    }

    @Override // com.bytedance.applog.priority.PriorityCallback
    public boolean isStageEnabled() {
        return this.priorityCallback.isStageEnabled();
    }

    @Override // com.bytedance.applog.priority.PriorityCallback
    public void monitorError(String str, String str2) {
        this.priorityCallback.monitorError(str, str2);
    }
}
