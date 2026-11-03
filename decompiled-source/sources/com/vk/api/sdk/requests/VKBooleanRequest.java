package com.vk.api.sdk.requests;

import com.ss.android.socialbase.downloader.file.DownloadFileUtils;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;
/* compiled from: VKBooleanRequest.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0015\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0016¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"Lcom/vk/api/sdk/requests/VKBooleanRequest;", "Lcom/vk/api/sdk/requests/VKRequest;", "", "method", "", "(Ljava/lang/String;)V", "parse", DownloadFileUtils.MODE_READ, "Lorg/json/JSONObject;", "(Lorg/json/JSONObject;)Ljava/lang/Boolean;", "libapi-sdk-core_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes10.dex */
public class VKBooleanRequest extends VKRequest<Boolean> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public VKBooleanRequest(String method) {
        super(method);
        Intrinsics.checkParameterIsNotNull(method, "method");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.vk.api.sdk.requests.VKRequest
    public Boolean parse(JSONObject r) {
        Intrinsics.checkParameterIsNotNull(r, "r");
        return true;
    }
}
