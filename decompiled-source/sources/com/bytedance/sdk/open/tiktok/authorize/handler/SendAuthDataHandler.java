package com.bytedance.sdk.open.tiktok.authorize.handler;

import android.os.Bundle;
import com.bytedance.sdk.open.tiktok.authorize.model.Authorization;
import com.bytedance.sdk.open.tiktok.common.handler.IApiEventHandler;
import com.bytedance.sdk.open.tiktok.common.handler.IDataHandler;
import com.bytedance.sdk.open.tiktok.common.model.BaseResp;
/* loaded from: classes3.dex */
public class SendAuthDataHandler implements IDataHandler {
    @Override // com.bytedance.sdk.open.tiktok.common.handler.IDataHandler
    public boolean handle(int i2, Bundle bundle, IApiEventHandler iApiEventHandler) {
        if (bundle != null && iApiEventHandler != null) {
            if (i2 == 1) {
                Authorization.Request request = new Authorization.Request(bundle);
                if (request.checkArgs()) {
                    if (request.scope != null) {
                        request.scope = request.scope.replace(" ", "");
                    }
                    if (request.optionalScope1 != null) {
                        request.optionalScope1 = request.optionalScope1.replace(" ", "");
                    }
                    if (request.optionalScope0 != null) {
                        request.optionalScope0 = request.optionalScope0.replace(" ", "");
                    }
                    iApiEventHandler.onReq(request);
                    return true;
                }
                return false;
            } else if (i2 == 2) {
                BaseResp response = new Authorization.Response(bundle);
                if (response.checkArgs()) {
                    iApiEventHandler.onResp(response);
                    return true;
                }
            }
        }
        return false;
    }
}
