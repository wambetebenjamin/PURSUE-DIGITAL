package com.vk.api.sdk.requests;

import androidx.exifinterface.media.ExifInterface;
import com.bytedance.apm.perf.traffic.a;
import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
import com.ttnet.org.chromium.base.BaseSwitches;
import com.vk.api.sdk.VKApiConfig;
import com.vk.api.sdk.VKApiManager;
import com.vk.api.sdk.VKApiResponseParser;
import com.vk.api.sdk.VKMethodCall;
import com.vk.api.sdk.exceptions.VKApiCodes;
import com.vk.api.sdk.exceptions.VKApiException;
import com.vk.api.sdk.exceptions.VKApiExecutionException;
import com.vk.api.sdk.internal.ApiCommand;
import java.io.IOException;
import java.util.LinkedHashMap;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;
/* compiled from: VKRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\u0010\u001c\n\u0000\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000 #*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003:\u0001#B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J%\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u000f\u001a\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0012¢\u0006\u0002\u0010\u0013J\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0014J \u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u000f\u001a\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0015J\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0017J\u001c\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0018J\u001e\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u000f\u001a\u00020\u00052\b\u0010\u0016\u001a\u0004\u0018\u00010\u0005J\u0015\u0010\u0019\u001a\u00028\u00002\u0006\u0010\u001a\u001a\u00020\u001bH\u0014¢\u0006\u0002\u0010\u001cJ\u0015\u0010\u001d\u001a\u00028\u00002\u0006\u0010\u001e\u001a\u00020\u0005H\u0016¢\u0006\u0002\u0010\u001fJ\u0015\u0010\u001d\u001a\u00028\u00002\u0006\u0010 \u001a\u00020!H\u0016¢\u0006\u0002\u0010\"R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006R\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006$"}, d2 = {"Lcom/vk/api/sdk/requests/VKRequest;", ExifInterface.GPS_DIRECTION_TRUE, "Lcom/vk/api/sdk/VKApiResponseParser;", "Lcom/vk/api/sdk/internal/ApiCommand;", "method", "", "(Ljava/lang/String;)V", "getMethod", "()Ljava/lang/String;", "setMethod", "params", "Ljava/util/LinkedHashMap;", "getParams", "()Ljava/util/LinkedHashMap;", "addParam", "name", "", "values", "", "(Ljava/lang/CharSequence;[Ljava/lang/Object;)Lcom/vk/api/sdk/requests/VKRequest;", "", "", "value", "", "", "onExecute", "manager", "Lcom/vk/api/sdk/VKApiManager;", "(Lcom/vk/api/sdk/VKApiManager;)Ljava/lang/Object;", "parse", a.TTNET_RESPONSE, "(Ljava/lang/String;)Ljava/lang/Object;", DownloadFileUtils.MODE_READ, "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)Ljava/lang/Object;", "Companion", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public class VKRequest<T> extends ApiCommand<T> implements VKApiResponseParser<T> {
    public static final Companion Companion = new Companion(null);
    public static final int ERROR_MALFORMED_RESPONSE = -2;
    private String method;
    private final LinkedHashMap<String, String> params;

    public VKRequest(String method) {
        Intrinsics.checkParameterIsNotNull(method, "method");
        this.method = method;
        this.params = new LinkedHashMap<>();
    }

    public final String getMethod() {
        return this.method;
    }

    public final void setMethod(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.method = str;
    }

    public final LinkedHashMap<String, String> getParams() {
        return this.params;
    }

    public final VKRequest<T> addParam(String name, String str) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (str != null) {
            this.params.put(name, str);
        }
        return this;
    }

    public final VKRequest<T> addParam(String name, int i2) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (i2 != 0) {
            String num = Integer.toString(i2);
            Intrinsics.checkExpressionValueIsNotNull(num, "Integer.toString(value)");
            this.params.put(name, num);
        }
        return this;
    }

    public final VKRequest<T> addParam(String name, long j2) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        if (j2 != 0) {
            String l = Long.toString(j2);
            Intrinsics.checkExpressionValueIsNotNull(l, "java.lang.Long.toString(value)");
            this.params.put(name, l);
        }
        return this;
    }

    public final VKRequest<T> addParam(CharSequence name, Object[] values) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(values, "values");
        return addParam(name.toString(), ArraysKt.joinToString$default(values, ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
    }

    public final VKRequest<T> addParam(CharSequence name, Iterable<?> values) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(values, "values");
        return addParam(name.toString(), CollectionsKt.joinToString$default(values, ",", null, null, 0, null, null, 62, null));
    }

    public final VKRequest<T> addParam(CharSequence name, int[] values) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(values, "values");
        return addParam(name.toString(), ArraysKt.joinToString$default(values, (CharSequence) ",", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
    }

    @Override // com.vk.api.sdk.VKApiResponseParser
    public T parse(String response) throws VKApiException {
        Intrinsics.checkParameterIsNotNull(response, "response");
        try {
            return parse(new JSONObject(response));
        } catch (Throwable th) {
            throw new VKApiExecutionException(-2, this.method, true, '[' + this.method + "] " + th.getLocalizedMessage(), null, null, null, 112, null);
        }
    }

    public T parse(JSONObject r) throws Exception {
        Intrinsics.checkParameterIsNotNull(r, "r");
        return (T) r;
    }

    @Override // com.vk.api.sdk.internal.ApiCommand
    protected T onExecute(VKApiManager manager) throws InterruptedException, IOException, VKApiException {
        Intrinsics.checkParameterIsNotNull(manager, "manager");
        VKApiConfig config = manager.getConfig();
        this.params.put(VKApiCodes.PARAM_LANG, config.getLang());
        this.params.put("device_id", config.getDeviceId().getValue());
        this.params.put(BaseSwitches.V, config.getVersion());
        return (T) manager.execute(new VKMethodCall.Builder().args(this.params).method(this.method).version(config.getVersion()).build(), this);
    }

    /* compiled from: VKRequest.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/vk/api/sdk/requests/VKRequest$Companion;", "", "()V", "ERROR_MALFORMED_RESPONSE", "", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
    /* loaded from: classes10.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
