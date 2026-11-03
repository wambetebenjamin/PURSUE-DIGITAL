package com.bytedance.react.framework.core;

import java.util.HashMap;
/* loaded from: classes2.dex */
public interface BRNWindowCallback {
    void onClose(String str);

    void onFailed(String str);

    void onMessage(String str, String str2, HashMap<String, Object> hashMap, String str3);

    void onSuccess(String str);
}
