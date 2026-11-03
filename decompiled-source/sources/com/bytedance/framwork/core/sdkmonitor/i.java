package com.bytedance.framwork.core.sdkmonitor;

import android.content.Context;
import com.bytedance.framwork.core.sdklib.util.f;
import com.bytedance.librarian.c;
import com.facebook.internal.security.CertificateUtil;
/* compiled from: MonitorHelper.java */
/* loaded from: classes2.dex */
public class i {

    /* renamed from: a  reason: collision with root package name */
    private static String f8824a;

    /* renamed from: b  reason: collision with root package name */
    private static String f8825b;

    public static String getShortProcessName(Context context) {
        if (f8825b == null) {
            String replace = f.getCurProcessName(context).replace(context.getPackageName(), "p").replace(CertificateUtil.DELIMITER, "_");
            f8825b = replace;
            f8825b = replace.replace(c.a.DOT, "_");
        }
        return f8825b;
    }

    public static String getProcessName(Context context) {
        if (f8824a == null) {
            f8824a = f.getCurProcessName(context);
        }
        return f8824a;
    }
}
