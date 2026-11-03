package com.monitor.cloudmessage.entity;

import java.util.HashMap;
/* compiled from: ConsumerResult.java */
/* loaded from: classes9.dex */
public class b {
    public String getErrMsg() {
        return "";
    }

    public HashMap<String, String> getSpecificParams() {
        return null;
    }

    public boolean isSuccess() {
        return false;
    }

    private b(boolean z, String str, HashMap<String, String> hashMap) {
    }

    public static b build(boolean z, String str, HashMap<String, String> hashMap) {
        return new b(z, str, hashMap);
    }
}
