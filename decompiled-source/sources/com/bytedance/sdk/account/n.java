package com.bytedance.sdk.account;

import android.content.Context;
import java.util.List;
import java.util.Map;
/* compiled from: INetWork.java */
/* loaded from: classes2.dex */
public interface n {
    int checkResponseException(Context context, Throwable th);

    com.ss.android.i executeGet(int i2, String str, List<com.ss.android.h> list);

    com.ss.android.i executePost(int i2, String str, Map<String, String> map, List<com.ss.android.h> list);

    com.ss.android.i postFile(int i2, String str, Map<String, String> map, String str2, String str3, List<com.ss.android.h> list);
}
