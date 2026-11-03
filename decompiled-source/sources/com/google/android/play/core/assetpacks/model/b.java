package com.google.android.play.core.assetpacks.model;

import com.facebook.internal.security.CertificateUtil;
/* compiled from: com.google.android.play:asset-delivery@@2.1.0 */
/* loaded from: classes6.dex */
public final class b {
    public static String a(String str, String str2) {
        return str + CertificateUtil.DELIMITER + str2;
    }

    public static String b(String str, String str2, String str3) {
        return str + CertificateUtil.DELIMITER + str2 + CertificateUtil.DELIMITER + str3;
    }
}
