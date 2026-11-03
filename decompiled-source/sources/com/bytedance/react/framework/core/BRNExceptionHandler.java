package com.bytedance.react.framework.core;

import android.text.TextUtils;
import com.bytedance.react.framework.RNConfig;
import com.facebook.react.bridge.NativeModuleCallExceptionHandler;
import java.util.HashMap;
/* loaded from: classes2.dex */
public class BRNExceptionHandler implements NativeModuleCallExceptionHandler {
    private BRNExceptionLisener mBRNExceptionLisener;
    public Exception mException;
    public boolean mHashHappend = false;
    private HashMap<String, String> mHashMap;

    public BRNExceptionHandler(HashMap<String, String> hashMap) {
        this.mHashMap = hashMap;
    }

    public void setBRNExceptionLisener(BRNExceptionLisener bRNExceptionLisener) {
        this.mBRNExceptionLisener = bRNExceptionLisener;
    }

    public void updateVersionCode(String str, String str2) {
        if (this.mHashMap != null) {
            if (TextUtils.isEmpty(str)) {
                this.mHashMap.put(RNConfig.MODULE_VERSION, str);
            }
            if (TextUtils.isEmpty(str2)) {
                this.mHashMap.put(RNConfig.MODULE_NAME, str2);
            }
        }
    }

    public void clearStatus() {
        this.mBRNExceptionLisener = null;
        this.mHashMap.clear();
    }

    @Override // com.facebook.react.bridge.NativeModuleCallExceptionHandler
    public void handleException(Exception exc) {
        if (exc != null) {
            HashMap<String, String> hashMap = this.mHashMap;
            if (hashMap != null) {
                if (hashMap.containsKey(RNConfig.MODULE_NAME)) {
                    BRNLogger.d(BRNLoaderManager.TAG, "postException 1 " + exc + " " + this.mHashMap.toString());
                    BRNManager.newInstance().postReactException(exc, this.mHashMap);
                }
            } else {
                BRNLogger.d(BRNLoaderManager.TAG, "postException 2 " + exc + " " + this.mHashMap.toString());
                BRNManager.newInstance().postReactException(exc, null);
            }
            BRNExceptionLisener bRNExceptionLisener = this.mBRNExceptionLisener;
            if (bRNExceptionLisener != null) {
                bRNExceptionLisener.onExcepton(exc);
                return;
            }
            this.mHashHappend = true;
            this.mException = exc;
        }
    }
}
