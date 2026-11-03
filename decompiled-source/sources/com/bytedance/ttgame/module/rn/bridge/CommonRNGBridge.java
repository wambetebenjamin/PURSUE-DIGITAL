package com.bytedance.ttgame.module.rn.bridge;

import android.app.Activity;
import android.content.Intent;
import com.bytedance.ttgame.framework.gbridge.rn.RNBridgeManager;
import com.bytedance.ttgame.sdk.module.bridge.ICommonBridgeInterface;
import com.bytedance.ttgame.sdk.module.bridge.IResultCallback;
import java.util.HashMap;
/* loaded from: classes6.dex */
public class CommonRNGBridge extends ICommonBridgeInterface {
    @Override // com.bytedance.ttgame.sdk.module.bridge.ICommonBridgeInterface
    public String getName() {
        return "RNGBridgeModule";
    }

    @Override // com.bytedance.ttgame.sdk.module.bridge.ICommonBridgeInterface
    public boolean onActivityResult(Activity activity, int i2, int i3, Intent intent) {
        return false;
    }

    @Override // com.bytedance.ttgame.sdk.module.bridge.ICommonBridgeInterface
    public void onBridgeCalled(String str, HashMap hashMap, final IResultCallback iResultCallback) {
        if (str != null) {
            RNBridgeManager.handleMethodCall(str, hashMap, new com.bytedance.ttgame.framework.gbridge.rn.IResultCallback() { // from class: com.bytedance.ttgame.module.rn.bridge.CommonRNGBridge.1
                @Override // com.bytedance.ttgame.framework.gbridge.rn.IResultCallback
                public void onSuccess(HashMap hashMap2) {
                    IResultCallback iResultCallback2 = iResultCallback;
                    if (iResultCallback2 != null) {
                        iResultCallback2.onSuccess(hashMap2);
                    }
                }

                @Override // com.bytedance.ttgame.framework.gbridge.rn.IResultCallback
                public void onFailed(HashMap hashMap2) {
                    IResultCallback iResultCallback2 = iResultCallback;
                    if (iResultCallback2 != null) {
                        iResultCallback2.onFailed(hashMap2);
                    }
                }
            });
        } else if (iResultCallback != null) {
            iResultCallback.onFailed(new HashMap());
        }
    }
}
