package com.bytedance.ug.sdk.share.api.depend;

import android.app.Activity;
/* compiled from: IShareQrScanConfig.java */
/* loaded from: classes8.dex */
public interface m {
    String getQrDecodeStr(String str);

    void handleQrScanResult(Activity activity, String str);
}
