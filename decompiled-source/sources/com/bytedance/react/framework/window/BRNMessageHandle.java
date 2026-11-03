package com.bytedance.react.framework.window;

import android.text.TextUtils;
import com.bytedance.react.framework.core.BRNWindowCallback;
import java.util.HashMap;
/* loaded from: classes2.dex */
public class BRNMessageHandle {
    private static BRNMessageHandle mBRNMessageHandle;
    private HashMap<String, BRNWindowCallback> mBRNWindowMap = new HashMap<>();

    private BRNMessageHandle() {
    }

    public static BRNMessageHandle newInstance() {
        if (mBRNMessageHandle == null) {
            mBRNMessageHandle = new BRNMessageHandle();
        }
        return mBRNMessageHandle;
    }

    public void registEventById(String str, BRNWindowCallback bRNWindowCallback) {
        if (TextUtils.isEmpty(str) || bRNWindowCallback == null) {
            return;
        }
        this.mBRNWindowMap.put(str, bRNWindowCallback);
    }

    public boolean sendMessage(String str, String str2, HashMap hashMap, String str3) {
        BRNWindowCallback bRNWindowCallback;
        if (!this.mBRNWindowMap.containsKey(str) || (bRNWindowCallback = this.mBRNWindowMap.get(str)) == null) {
            return false;
        }
        bRNWindowCallback.onMessage(str, str2, hashMap, str3);
        return true;
    }

    public void removeEventListner(String str) {
        this.mBRNWindowMap.remove(str);
    }

    public void sendCloseMessage(String str) {
        BRNWindowCallback bRNWindowCallback;
        if (!this.mBRNWindowMap.containsKey(str) || (bRNWindowCallback = this.mBRNWindowMap.get(str)) == null) {
            return;
        }
        bRNWindowCallback.onClose(str);
    }
}
