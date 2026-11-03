package com.bytedance.ttgame.channel.utils;

import com.bytedance.ttgame.base.GSDKError;
import com.bytedance.ttgame.sdk.module.core.internal.ServerErrorCodeMapping;
/* loaded from: classes3.dex */
public class ComplianceErrorCodeMapping {
    public static final int COMPLIANCE_NETWORK_ERROR_CODE = -473001;
    public static final int COMPLIANCE_NOT_LOGIN = -479800;
    public static final int COOKIE_ERROR_UNKNOWN = -475999;

    public static GSDKError convertErrorCode(int i2, String str, int i3, String str2) {
        int i4;
        if (i2 == -479800) {
            i4 = -109800;
        } else if (i2 != -475999) {
            i4 = i2 != -473001 ? ServerErrorCodeMapping.convertServerErrorCode(i3) : -103001;
        } else {
            i4 = -105999;
        }
        return new GSDKError(i4, str, i3, str2);
    }
}
