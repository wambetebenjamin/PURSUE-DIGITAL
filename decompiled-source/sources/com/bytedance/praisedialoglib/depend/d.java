package com.bytedance.praisedialoglib.depend;

import java.util.Map;
/* compiled from: IPraiseDialogNetworkConfig.java */
/* loaded from: classes2.dex */
public interface d {
    int checkResponseException(Throwable th);

    String executeGet(int i2, String str);

    String executePost(int i2, String str, Map<String, String> map);
}
