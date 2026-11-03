package com.bytedance.ttgame.module.main.bridge;

import com.bytedance.ttgame.framework.gbridge.GBridgeContext;
import com.bytedance.ttgame.framework.gbridge.annotation.GBridgeMethod;
import com.bytedance.ttgame.framework.gbridge.annotation.GBridgeParam;
import com.bytedance.ttgame.framework.gbridge.module.BaseModule;
import com.bytedance.ttgame.framework.gbridge.plugin.SdkLog;
import com.bytedance.ttgame.framework.gbridge.spi.ServiceManager;
import com.bytedance.ttgame.module.fileupload.api.Callback;
import com.bytedance.ttgame.module.fileupload.api.IFileUploadService;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;
/* loaded from: classes6.dex */
public class FileUploadModule implements BaseModule {
    private String mTunnel;

    public FileUploadModule(String str) {
        this.mTunnel = str;
    }

    @GBridgeMethod(callName = "requestGMUploadFile")
    public void uploadFile(@GBridgeParam("data") JSONObject jSONObject, @GBridgeParam final GBridgeContext gBridgeContext) {
        SdkLog.i(BaseModule.TAG, "uploadFile");
        String optString = jSONObject.optString("type");
        String optString2 = jSONObject.optString("path");
        String optString3 = jSONObject.optString("scene");
        JSONObject optJSONObject = jSONObject.optJSONObject("params");
        HashMap hashMap = new HashMap();
        if (optJSONObject != null) {
            Iterator<String> keys = optJSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                hashMap.put(next, optJSONObject.optString(next));
            }
        }
        ((IFileUploadService) ServiceManager.get().getService(IFileUploadService.class)).uploadFileAtPath(optString, optString3, hashMap, optString2, new Callback() { // from class: com.bytedance.ttgame.module.main.bridge.FileUploadModule.1
            @Override // com.bytedance.ttgame.module.fileupload.api.Callback
            public void onResult(boolean z) {
                JSONObject jSONObject2 = new JSONObject();
                if (z) {
                    BaseModule.add(jSONObject2, "code", 0);
                    BaseModule.add(jSONObject2, "message", "upload file success");
                } else {
                    BaseModule.add(jSONObject2, "code", 1);
                    BaseModule.add(jSONObject2, "message", "upload file failed");
                }
                gBridgeContext.callBackResult(jSONObject2);
            }
        });
    }

    @Override // com.bytedance.ttgame.framework.gbridge.IBridgeModule
    public String getBridgeTunnel() {
        return this.mTunnel;
    }
}
