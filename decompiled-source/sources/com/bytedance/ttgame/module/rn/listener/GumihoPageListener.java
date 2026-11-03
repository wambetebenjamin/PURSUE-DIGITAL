package com.bytedance.ttgame.module.rn.listener;

import java.util.HashMap;
/* loaded from: classes6.dex */
public interface GumihoPageListener {
    void onClose(String str, String str2);

    void onFailed(String str);

    void onMessage(String str, String str2, HashMap<String, Object> hashMap, String str3);

    void onPageCreated();

    void onWindowCreated(String str);
}
