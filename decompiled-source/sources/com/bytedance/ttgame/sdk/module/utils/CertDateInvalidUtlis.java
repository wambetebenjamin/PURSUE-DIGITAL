package com.bytedance.ttgame.sdk.module.utils;

import android.text.TextUtils;
import timber.log.Timber;
/* loaded from: classes8.dex */
public class CertDateInvalidUtlis {
    public static boolean isCertDateInvalid(String str) {
        Timber.tag("gsdk_network").e("errorMsg:".concat(String.valueOf(str)), new Object[0]);
        if (!TextUtils.isEmpty(str) && str.contains("net::ERR_CERT_DATE_INVALID") && str.contains("InternalErrorCode=-201")) {
            Timber.tag("ttnet_cert").e("err_cert_date_invalid", new Object[0]);
            return true;
        }
        return false;
    }
}
